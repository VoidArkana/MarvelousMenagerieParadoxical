package net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntityBaseEntryManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();

    public EntityBaseEntryManager(String directory) {
        super(GSON_INSTANCE, directory);
    }
}
