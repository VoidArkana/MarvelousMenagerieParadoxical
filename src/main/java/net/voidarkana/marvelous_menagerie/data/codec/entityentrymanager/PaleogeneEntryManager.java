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

public class PaleogeneEntryManager extends EntityBaseEntryManager{

    public PaleogeneEntryManager() {
        super("paleonomicon/paleogene");
    }

    public static final List<PaleogeneEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, PaleogeneEntryManager.EntityCodec> encyclopedia = new HashMap<>();

        object.forEach((resourceLocation, jsonElement) -> {
            PaleogeneEntryManager.EntityCodec entryData = PaleogeneEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<PaleogeneEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(PaleogeneEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(PaleogeneEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(PaleogeneEntryManager.EntityCodec::link)
                ).apply(instance, PaleogeneEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
