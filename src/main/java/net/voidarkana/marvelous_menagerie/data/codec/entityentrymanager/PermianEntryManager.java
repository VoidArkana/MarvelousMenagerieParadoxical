
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

public class PermianEntryManager extends EntityBaseEntryManager{

    public PermianEntryManager() {
        super("paleonomicon/permian");
    }

    public static final List<PermianEntryManager.EntityCodec> DATA = Lists.newArrayList();

    @Override
    public void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, PermianEntryManager.EntityCodec> encyclopedia = new HashMap<>();

        object.forEach((resourceLocation, jsonElement) -> {
            PermianEntryManager.EntityCodec entryData = PermianEntryManager.EntityCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(entryData);
        });
    }

    public record EntityCodec(String entityName, String icon, String link) {

        public static final Codec<PermianEntryManager.EntityCodec> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.STRING.fieldOf("entity_name").forGetter(PermianEntryManager.EntityCodec::entityName),
                        Codec.STRING.fieldOf("icon").forGetter(PermianEntryManager.EntityCodec::icon),
                        Codec.STRING.fieldOf("link").forGetter(PermianEntryManager.EntityCodec::link)
                ).apply(instance, PermianEntryManager.EntityCodec::new));


        public String getIcon() {
            return icon;
        }
    }
}
