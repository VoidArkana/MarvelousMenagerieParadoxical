package net.voidarkana.marvelous_menagerie.data.manager;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.message.SyncSummoningRituals;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class RitualManager extends SimpleJsonResourceReloadListener {
    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<RitualProcessData> DATA = Lists.newArrayList();

    public RitualManager() {
        super(GSON_INSTANCE, "gameplay/summoning_rituals");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        DATA.clear();
        object.forEach((resourceLocation, jsonElement) -> {
            try {
                JsonObject json = jsonElement.getAsJsonObject();
                RitualProcessData data = RitualProcessData.fromJson(json);
                DATA.add(data);
            } catch (Exception e) {
                MarvelousMenagerie.LOGGER.error("Failed to read Fractured recipe for resource {}", resourceLocation, e);
            }
        });
        MarvelousMenagerie.LOGGER.info("Loaded {} summoning rituals", DATA.size());
    }

    public void onDatapackSync(@Nullable ServerPlayer player) {
        if (DATA.isEmpty()) return;

        BiMap<ResourceLocation, RitualManager.RitualProcessData> registryMap = HashBiMap.create();
        for (RitualManager.RitualProcessData data : DATA) {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(data.output);
            if (id != null) {
                registryMap.put(id, data);
            }
        }

        if (player == null) {
            MMMessages.CHANNEL.send(PacketDistributor.ALL.noArg(), new SyncSummoningRituals(registryMap));
        } else {
            MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new SyncSummoningRituals(registryMap));
        }
    }

    public void synchronizeRegistryForClient(BiMap<ResourceLocation, RitualManager.RitualProcessData> newData) {
        DATA.clear();
        DATA.addAll(newData.values());
    }

    public record RitualProcessData(Ingredient input1, Ingredient input2, Ingredient input3, Ingredient input4, EntityType output) {

        public static RitualProcessData fromJson(JsonObject json) {
            Ingredient input1 = Ingredient.fromJson(json.get("input1"));
            Ingredient input2 = Ingredient.fromJson(json.get("input2"));
            Ingredient input3 = Ingredient.fromJson(json.get("input3"));
            Ingredient input4 = Ingredient.fromJson(json.get("input4"));
            EntityType output = BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(json.get("output").getAsString()));
            return new RitualProcessData(input1, input2, input3, input4, output);
        }

        public static RitualManager.RitualProcessData fromNetwork(FriendlyByteBuf buf) {
            Ingredient input1 = Ingredient.fromNetwork(buf);
            Ingredient input2 = Ingredient.fromNetwork(buf);
            Ingredient input3 = Ingredient.fromNetwork(buf);
            Ingredient input4 = Ingredient.fromNetwork(buf);
            EntityType<?> entity = buf.readJsonWithCodec(BuiltInRegistries.ENTITY_TYPE.byNameCodec());
            return new RitualManager.RitualProcessData(input1, input2, input3, input4, entity);
        }

        public void toNetwork(FriendlyByteBuf buf, RitualManager.RitualProcessData recipe) {
            recipe.input1.toNetwork(buf);
            recipe.input2.toNetwork(buf);
            recipe.input3.toNetwork(buf);
            recipe.input4.toNetwork(buf);
            buf.writeJsonWithCodec(BuiltInRegistries.ENTITY_TYPE.byNameCodec(), recipe.output);
        }
    }
}
