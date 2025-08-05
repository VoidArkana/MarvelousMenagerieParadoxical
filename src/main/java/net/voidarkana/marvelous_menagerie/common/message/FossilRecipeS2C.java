package net.voidarkana.marvelous_menagerie.common.message;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.FossilCleaningManager;
import net.voidarkana.marvelous_menagerie.data.codec.WeightedItemCodec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FossilRecipeS2C {
    private static final Codec<Map<Item, List<WeightedItemCodec>>> MAPPER =
            Codec.unboundedMap(BuiltInRegistries.ITEM.byNameCodec(), WeightedItemCodec.CODEC.listOf()).xmap(WeightedItemCodec::convertToMap, WeightedItemCodec::convertFromMap)
                    .orElse(e -> {
                                MarvelousMenagerie.LOGGER.error("Failed to parse Analyzer Entries can't send packet! Due to " + e);},
                            new HashMap<>());
    public static Map<Item, List<WeightedItemCodec>> SYNCED_DATA = new HashMap<>();
    private final Map<Item, List<WeightedItemCodec>> map;

    public FossilRecipeS2C(Map<Item, List<WeightedItemCodec>> map) {
        this.map = map;
    }

    public void encode(FriendlyByteBuf buffer) {
        CompoundTag encodedTag = (CompoundTag) (MAPPER.encodeStart(NbtOps.INSTANCE, this.map).result().orElse(new CompoundTag()));
        buffer.writeNbt(encodedTag);
    }

    public static FossilRecipeS2C decode(FriendlyByteBuf buffer) {
        CompoundTag receivedTag = buffer.readNbt();
        Map<Item, List<WeightedItemCodec>> decodedMap = MAPPER.parse(NbtOps.INSTANCE, receivedTag).result().orElse(new HashMap<>());
        return new FossilRecipeS2C(decodedMap);
    }

    public void onPacketReceived(Supplier<NetworkEvent.Context> contextGetter) {
        NetworkEvent.Context context = contextGetter.get();
        context.enqueueWork(this::handlePacketOnMainThread);
        context.setPacketHandled(true);
    }

    private void handlePacketOnMainThread() {
        SYNCED_DATA = this.map;
        FossilCleaningManager.setRecipeList(SYNCED_DATA);
    }
}
