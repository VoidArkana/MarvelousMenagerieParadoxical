package net.voidarkana.marvelous_menagerie.data.codec;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RitualManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<RitualProcessData> DATA = Lists.newArrayList();

    public RitualManager() {
        super(GSON_INSTANCE, "gameplay/summoning_rituals");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        DATA.clear();
        object.forEach((resourceLocation, jsonElement) -> {
            try {
                JsonObject json = jsonElement.getAsJsonObject();
                RitualProcessData data = RitualProcessData.fromJson(json);
                DATA.add(data);
            } catch (Exception e) {
                MarvelousMenagerie.LOGGER.error("Failed to read Fractured recipe for resource {}", resourceLocation, e);
            }

        });
        MarvelousMenagerie.LOGGER.info("Loaded {} summoning rituals", DATA.size());
//        object.forEach((resourceLocation, jsonElement) -> {
//            RitualProcessData summoningData = RitualProcessData.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
//            DATA.add(summoningData);
//        });
    }

    public record RitualProcessData(Ingredient input1, Ingredient input2, Ingredient input3, Ingredient input4, EntityType output) {

//        public static final Codec<RitualItem> RITUAL_ITEM_CODEC = Codec.either(ResourceKey.codec(Registries.ITEM),
//                TagKey.hashedCodec(Registries.ITEM)).xmap(RitualItem::new, RitualItem::get);
//        public static final Codec<RitualProcessData> CODEC = RecordCodecBuilder.create(instance ->
//                instance.group(
//                        RITUAL_ITEM_CODEC.fieldOf("input1").forGetter(RitualProcessData::input1),
//                        RITUAL_ITEM_CODEC.fieldOf("input2").forGetter(RitualProcessData::input2),
//                        RITUAL_ITEM_CODEC.fieldOf("input3").forGetter(RitualProcessData::input3),
//                        RITUAL_ITEM_CODEC.fieldOf("input4").forGetter(RitualProcessData::input4),
//                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("output").forGetter(RitualProcessData::output)
//                ).apply(instance, RitualProcessData::new));
//    }

        public static RitualProcessData fromJson(JsonObject json) {
            Ingredient input1 = Ingredient.fromJson(json.get("input1"));
            Ingredient input2 = Ingredient.fromJson(json.get("input2"));
            Ingredient input3 = Ingredient.fromJson(json.get("input3"));
            Ingredient input4 = Ingredient.fromJson(json.get("input4"));
            EntityType output = BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(json.get("output").getAsString()));
            return new RitualProcessData(input1, input2, input3, input4, output);
        }
    }


}
