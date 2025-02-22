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
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbominationEntryManager extends SimpleJsonResourceReloadListener {


    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<AbominationEntryCodec> DATA = Lists.newArrayList();

    public AbominationEntryManager() {
        super(GSON_INSTANCE, "paleonomicon/abomination");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, AbominationEntryCodec> encyclopedia = new HashMap<>();

        object.forEach((resourceLocation, jsonElement) -> {
            AbominationEntryCodec entryData = AbominationEntryCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });


    }


    protected static Map<ResourceLocation, AbominationEntryCodec> encyclopediaEntries = new HashMap<>();

    public static Map<ResourceLocation, AbominationEntryCodec> getEncyclopediaEntries() {
        return encyclopediaEntries;
    }

    public static void setEncyclopediaEntries(Map<ResourceLocation, AbominationEntryCodec> encyclopediaEntries) {
        AbominationEntryManager.encyclopediaEntries = encyclopediaEntries;
    }

    public record AbominationEntryCodec(String entityName, String icon, String link) {

        public static final Codec<AbominationEntryCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(AbominationEntryCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(AbominationEntryCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(AbominationEntryCodec::link)
                ).apply(instance, AbominationEntryCodec::new));


        public String getPlant() {
            return icon;
        }
    }
}
