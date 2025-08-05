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
import net.voidarkana.marvelous_menagerie.util.network.indexes.AbominationIndexSyncPacket;
import net.voidarkana.marvelous_menagerie.util.network.indexes.CarboniferousIndexSyncPacket;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarboniferousEntryManager extends EntityBaseEntryManager{

    public CarboniferousEntryManager() {
        super("paleonomicon/carboniferous");
    }

    public static final List<CarboniferousEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        DATA.clear();
        object.forEach((resourceLocation, jsonElement) -> {
            CarboniferousEntryManager.EntityCodec entryData = CarboniferousEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, CarboniferousEntryManager.EntityCodec> registryMap = HashBiMap.create();
        for (CarboniferousEntryManager.EntityCodec data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.entityName);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new CarboniferousIndexSyncPacket(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new CarboniferousIndexSyncPacket(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, CarboniferousEntryManager.EntityCodec> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record EntityCodec(EntityType entityName, String icon, String link) {

        public static final Codec<CarboniferousEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_name").forGetter(CarboniferousEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(CarboniferousEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(CarboniferousEntryManager.EntityCodec::link)
                ).apply(instance, CarboniferousEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }


        public static CarboniferousEntryManager.EntityCodec fromNetwork(FriendlyByteBuf buf) {
            EntityType<?> entity = buf.readById(BuiltInRegistries.ENTITY_TYPE);
            String icon = buf.readJsonWithCodec(Codec.STRING);
            String link = buf.readJsonWithCodec(Codec.STRING);
            return new CarboniferousEntryManager.EntityCodec(entity, icon, link);
        }

        public void toNetwork(FriendlyByteBuf buf) {
            buf.writeId(BuiltInRegistries.ENTITY_TYPE, this.entityName);
            buf.writeJsonWithCodec(Codec.STRING, this.icon);
            buf.writeJsonWithCodec(Codec.STRING, this.link);
        }
    }
}
