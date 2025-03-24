package net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;

public abstract class EntityBaseEntryManager extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();

    public EntityBaseEntryManager(String directory) {
        super(GSON_INSTANCE, directory);
    }
}
