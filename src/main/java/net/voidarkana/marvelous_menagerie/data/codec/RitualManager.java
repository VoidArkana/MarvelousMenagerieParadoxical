package net.voidarkana.marvelous_menagerie.data.codec;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<RitualProcessData> DATA = Lists.newArrayList();

    public RitualManager() {
        super(GSON_INSTANCE, "gameplay/summoning_rituals");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        object.forEach((resourceLocation, jsonElement) -> {
            RitualProcessData summoningData = RitualProcessData.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(summoningData);
        });
    }

    public record RitualProcessData(Item input1, Item input2, Item input3, Item input4, EntityType output) {
        public static final Codec<RitualProcessData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("input1").forGetter(RitualProcessData::input1),
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("input2").forGetter(RitualProcessData::input2),
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("input3").forGetter(RitualProcessData::input3),
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("input4").forGetter(RitualProcessData::input4),
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("output").forGetter(RitualProcessData::output)
                ).apply(instance, RitualProcessData::new));
    }
}
