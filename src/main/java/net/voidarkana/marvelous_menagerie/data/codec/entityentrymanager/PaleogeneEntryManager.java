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
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.indexes.PaleogeneIndexSyncPacket;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaleogeneEntryManager extends EntityBaseEntryManager{

    public PaleogeneEntryManager() {
        super("paleonomicon/paleogene");
    }

    public static final List<PaleogeneEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        DATA.clear();
        jsons.forEach((resourceLocation, jsonElement) -> {
            PaleogeneEntryManager.EntityCodec entryData = PaleogeneEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }


    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, PaleogeneEntryManager.EntityCodec> registryMap = HashBiMap.create();
        for (PaleogeneEntryManager.EntityCodec data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.entityName);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new PaleogeneIndexSyncPacket(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new PaleogeneIndexSyncPacket(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, PaleogeneEntryManager.EntityCodec> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record EntityCodec(EntityType entityName, String icon, String link) {
        public static final Codec<PaleogeneEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_name").forGetter(PaleogeneEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(PaleogeneEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(PaleogeneEntryManager.EntityCodec::link)
                ).apply(instance, PaleogeneEntryManager.EntityCodec::new));

        public String getIcon() {
            return icon;
        }

        public static PaleogeneEntryManager.EntityCodec fromNetwork(FriendlyByteBuf buf) {
            EntityType<?> entity = buf.readById(BuiltInRegistries.ENTITY_TYPE);
            String icon = buf.readJsonWithCodec(Codec.STRING);
            String link = buf.readJsonWithCodec(Codec.STRING);
            return new PaleogeneEntryManager.EntityCodec(entity, icon, link);
        }

        public void toNetwork(FriendlyByteBuf buf) {
            buf.writeId(BuiltInRegistries.ENTITY_TYPE, this.entityName);
            buf.writeJsonWithCodec(Codec.STRING, this.icon);
            buf.writeJsonWithCodec(Codec.STRING, this.link);
        }
    }
}
