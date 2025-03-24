package net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbominationEntryManager extends EntityBaseEntryManager{

    public AbominationEntryManager() {
        super("paleonomicon/abomination");
    }

    public static Map<ResourceLocation, AbominationEntryManager.EntityCodec> encyclopediaEntries = new HashMap();

    public static Map<ResourceLocation, AbominationEntryManager.EntityCodec> getEncyclopediaEntries() {
        return encyclopediaEntries;
    }

    public static void setEncyclopediaEntries(Map<ResourceLocation, AbominationEntryManager.EntityCodec> encyclopediaEntries) {
        AbominationEntryManager.encyclopediaEntries = encyclopediaEntries;
    }

    public static final List<AbominationEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        Map<ResourceLocation, AbominationEntryManager.EntityCodec> encyclopedia = new HashMap<>();

        for(Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation key = (ResourceLocation)entry.getKey();
            JsonElement element = (JsonElement)entry.getValue();
            AbominationEntryManager.EntityCodec.CODEC.decode(JsonOps.INSTANCE, element).get().ifLeft((result) -> {
                AbominationEntryManager.EntityCodec encyclopediaCodec = (AbominationEntryManager.EntityCodec)result.getFirst();

                encyclopedia.put(key, encyclopediaCodec);

            }).ifRight((partial) -> MarvelousMenagerie.LOGGER.error("Failed to parse recipe JSON for {} due to: {}", "paleonomicon/abomination", partial.message()));
        }

        encyclopediaEntries = encyclopedia;

//        jsons.forEach((resourceLocation, jsonElement) -> {
//            AbominationEntryManager.EntityCodec entryData = AbominationEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
//            DATA.add(entryData);
//        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<AbominationEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(AbominationEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(AbominationEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(AbominationEntryManager.EntityCodec::link)
                ).apply(instance, AbominationEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
