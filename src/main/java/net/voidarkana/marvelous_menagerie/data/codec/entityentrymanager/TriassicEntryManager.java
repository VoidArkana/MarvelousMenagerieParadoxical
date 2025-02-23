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

public class TriassicEntryManager extends EntityBaseEntryManager{

    public TriassicEntryManager() {
        super("paleonomicon/triassic");
    }
    
    public static final List<TriassicEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, TriassicEntryManager.EntityCodec> encyclopedia = new HashMap<>();

        object.forEach((resourceLocation, jsonElement) -> {
            TriassicEntryManager.EntityCodec entryData = TriassicEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<TriassicEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(TriassicEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(TriassicEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(TriassicEntryManager.EntityCodec::link)
                ).apply(instance, TriassicEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
