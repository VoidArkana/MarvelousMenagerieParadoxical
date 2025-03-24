package net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

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
        object.forEach((resourceLocation, jsonElement) -> {
            CarboniferousEntryManager.EntityCodec entryData = CarboniferousEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<CarboniferousEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(CarboniferousEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(CarboniferousEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(CarboniferousEntryManager.EntityCodec::link)
                ).apply(instance, CarboniferousEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
