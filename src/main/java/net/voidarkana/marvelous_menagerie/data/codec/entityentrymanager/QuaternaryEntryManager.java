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
import net.voidarkana.marvelous_menagerie.util.network.indexes.QuaternaryIndexSyncPacket;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuaternaryEntryManager extends EntityBaseEntryManager{

    public QuaternaryEntryManager() {
        super("paleonomicon/quaternary");
    }
    
    public static final List<QuaternaryEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        DATA.clear();
        jsons.forEach((resourceLocation, jsonElement) -> {
            QuaternaryEntryManager.EntityCodec entryData = QuaternaryEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }


    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, QuaternaryEntryManager.EntityCodec> registryMap = HashBiMap.create();
        for (QuaternaryEntryManager.EntityCodec data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.entityName);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new QuaternaryIndexSyncPacket(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new QuaternaryIndexSyncPacket(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, QuaternaryEntryManager.EntityCodec> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record EntityCodec(EntityType entityName, String icon, String link) {
        public static final Codec<QuaternaryEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_name").forGetter(QuaternaryEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(QuaternaryEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(QuaternaryEntryManager.EntityCodec::link)
                ).apply(instance, QuaternaryEntryManager.EntityCodec::new));

        public String getIcon() {
            return icon;
        }

        public static QuaternaryEntryManager.EntityCodec fromNetwork(FriendlyByteBuf buf) {
            EntityType<?> entity = buf.readById(BuiltInRegistries.ENTITY_TYPE);
            String icon = buf.readJsonWithCodec(Codec.STRING);
            String link = buf.readJsonWithCodec(Codec.STRING);
            return new QuaternaryEntryManager.EntityCodec(entity, icon, link);
        }

        public void toNetwork(FriendlyByteBuf buf) {
            buf.writeId(BuiltInRegistries.ENTITY_TYPE, this.entityName);
            buf.writeJsonWithCodec(Codec.STRING, this.icon);
            buf.writeJsonWithCodec(Codec.STRING, this.link);
        }
    }
}
