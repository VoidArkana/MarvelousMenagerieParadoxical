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

import java.util.List;
import java.util.Map;

public class BookEntityManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<BookInteractLink> DATA = Lists.newArrayList();

    public BookEntityManager() {
        super(GSON_INSTANCE, "paleonomicon/entity_links");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        object.forEach((resourceLocation, jsonElement) -> {
            BookInteractLink summoningData = BookInteractLink.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(summoningData);
        });
    }

    public record BookInteractLink(EntityType entity, String link) {
        public static final Codec<BookInteractLink> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity").forGetter(BookInteractLink::entity),
                        Codec.STRING.fieldOf("link").forGetter(BookInteractLink::link)
                ).apply(instance, BookInteractLink::new));
    }
}
