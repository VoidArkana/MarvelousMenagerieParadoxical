package net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.data.codec.IndexTagsCodec;
import net.voidarkana.marvelous_menagerie.util.network.indexes.AbominationIndexSyncPacket;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class AbominationEntryManager extends EntityBaseEntryManager{

    public AbominationEntryManager() {
        super("paleonomicon/abomination");
    }

    public static final List<AbominationEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        DATA.clear();
        jsons.forEach((resourceLocation, jsonElement) -> {
            AbominationEntryManager.EntityCodec entryData = AbominationEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
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
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new AbominationIndexSyncPacket(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new AbominationIndexSyncPacket(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, EntityCodec> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record EntityCodec(EntityType entityName, String icon, String link, List<IndexTagsCodec> tags) {

        public static final Codec<AbominationEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_name").forGetter(AbominationEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(AbominationEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(AbominationEntryManager.EntityCodec::link),
                        IndexTagsCodec.CODEC.listOf().fieldOf("indexes").forGetter(AbominationEntryManager.EntityCodec::tags)
                ).apply(instance, AbominationEntryManager.EntityCodec::new));

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
