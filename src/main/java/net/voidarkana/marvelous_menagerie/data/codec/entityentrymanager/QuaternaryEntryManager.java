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

public class QuaternaryEntryManager extends EntityBaseEntryManager{

    public QuaternaryEntryManager() {
        super("paleonomicon/quaternary");
    }
    
    public static final List<QuaternaryEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, QuaternaryEntryManager.EntityCodec> encyclopedia = new HashMap<>();

        object.forEach((resourceLocation, jsonElement) -> {
            QuaternaryEntryManager.EntityCodec entryData = QuaternaryEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<QuaternaryEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(QuaternaryEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(QuaternaryEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(QuaternaryEntryManager.EntityCodec::link)
                ).apply(instance, QuaternaryEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
