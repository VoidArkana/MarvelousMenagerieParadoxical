package net.voidarkana.marvelous_menagerie.data.manager;

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
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.message.SyncSummoningRituals;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<RitualProcessData> DATA = Lists.newArrayList();

    public RitualManager() {
        super(GSON_INSTANCE, "gameplay/summoning_rituals");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        DATA.clear();
        jsons.forEach((resourceLocation, jsonElement) -> {
            RitualManager.RitualProcessData entryData = RitualManager.RitualProcessData.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
        MarvelousMenagerie.LOGGER.info("Loaded {} summoning rituals", DATA.size());
    }

    public record RitualProcessData(String input1, String input2,
                                    String input3, String input4, EntityType output) {

        public static final Codec<RitualProcessData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("input1").forGetter(RitualProcessData::input1),
                        Codec.STRING.fieldOf("input2").forGetter(RitualProcessData::input2),
                        Codec.STRING.fieldOf("input3").forGetter(RitualProcessData::input3),
                        Codec.STRING.fieldOf("input4").forGetter(RitualProcessData::input4),
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("output").forGetter(RitualProcessData::output)
                ).apply(instance, RitualProcessData::new));

        public static RitualProcessData fromNetwork(FriendlyByteBuf buf) {
            String input1 = buf.readJsonWithCodec(Codec.STRING);
            String input2 = buf.readJsonWithCodec(Codec.STRING);
            String input3 = buf.readJsonWithCodec(Codec.STRING);
            String input4 = buf.readJsonWithCodec(Codec.STRING);
            EntityType<?> entity = buf.readById(BuiltInRegistries.ENTITY_TYPE);
            return new RitualProcessData(input1, input2, input3, input4, entity);
        }

        public void toNetwork(FriendlyByteBuf buf) {
            buf.writeJsonWithCodec(Codec.STRING, this.input1);
            buf.writeJsonWithCodec(Codec.STRING, this.input2);
            buf.writeJsonWithCodec(Codec.STRING, this.input3);
            buf.writeJsonWithCodec(Codec.STRING, this.input4);
            buf.writeId(BuiltInRegistries.ENTITY_TYPE, this.output);
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, RitualManager.RitualProcessData> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, RitualManager.RitualProcessData> registryMap = HashBiMap.create();
        for (RitualManager.RitualProcessData data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.output);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new SyncSummoningRituals(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new SyncSummoningRituals(registryMap));
        }
    }
}
