package net.voidarkana.marvelous_menagerie.data.codec;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.util.network.indexes.PaleonomiconIndexSyncPacket;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PaleonomiconIndexManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();

    public PaleonomiconIndexManager() {
        super(GSON_INSTANCE, "gameplay/paleonomicon_indexes");
    }

    public static final List<PaleonomiconIndexManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        DATA.clear();
        jsons.forEach((resourceLocation, jsonElement) -> {
            PaleonomiconIndexManager.EntityCodec entryData = PaleonomiconIndexManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }


    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, EntityCodec> registryMap = HashBiMap.create();
        for (EntityCodec data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.entityName);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new PaleonomiconIndexSyncPacket(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new PaleonomiconIndexSyncPacket(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, EntityCodec> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record EntityCodec(EntityType entityName, String icon, String link, List<IndexTagsCodec> tags) {

        public static final Codec<PaleonomiconIndexManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_name").forGetter(PaleonomiconIndexManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(PaleonomiconIndexManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(PaleonomiconIndexManager.EntityCodec::link),
                        IndexTagsCodec.CODEC.listOf().fieldOf("indexes").forGetter(PaleonomiconIndexManager.EntityCodec::tags)
                ).apply(instance, PaleonomiconIndexManager.EntityCodec::new));

        public String getIcon() {
            return icon;
        }

        public static EntityCodec fromNetwork(FriendlyByteBuf buf) {
            EntityType<?> entity = buf.readById(BuiltInRegistries.ENTITY_TYPE);
            String icon = buf.readJsonWithCodec(Codec.STRING);
            String link = buf.readJsonWithCodec(Codec.STRING);
            List<IndexTagsCodec> tags = buf.readJsonWithCodec(IndexTagsCodec.CODEC.listOf());
            return new EntityCodec(entity, icon, link, tags);
        }

        public void toNetwork(FriendlyByteBuf buf) {
            buf.writeId(BuiltInRegistries.ENTITY_TYPE, this.entityName);
            buf.writeJsonWithCodec(Codec.STRING, this.icon);
            buf.writeJsonWithCodec(Codec.STRING, this.link);
            buf.writeJsonWithCodec(IndexTagsCodec.CODEC.listOf(), this.tags);
        }
    }
}
