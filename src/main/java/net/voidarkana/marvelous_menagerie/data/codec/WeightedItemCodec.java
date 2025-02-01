package net.voidarkana.marvelous_menagerie.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedItemCodec {

    private Item item;
    private int weight;
    private int amount;

    public WeightedItemCodec(Item item, int weight, int amount) {
        this.item = item;
        this.weight = weight;
        this.amount = amount;
    }

    public static final Codec<WeightedItemCodec> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(i->i.item),
                    Codec.INT.fieldOf("weight").forGetter(w -> w.weight),
                    Codec.INT.optionalFieldOf("amount", 1).forGetter(a -> a.amount)
            ).apply(instance, WeightedItemCodec::new));

    public static <T> Map<T, List<WeightedItemCodec>> convertToMap(Map<T, List<WeightedItemCodec>> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                        .map(itemWeightedPairCodec -> new WeightedItemCodec(
                                itemWeightedPairCodec.getItem(),
                                itemWeightedPairCodec.getWeight(),
                                itemWeightedPairCodec.getAmount()
                        ))
                        .collect(Collectors.toList())
        ));
    }

    public static <T> Map<T, List<WeightedItemCodec>> convertFromMap(Map<T, List<WeightedItemCodec>> map) {
        return map.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                        .map(itemWeightedPair -> new WeightedItemCodec(
                                itemWeightedPair.getItem(),
                                itemWeightedPair.getWeight(),
                                itemWeightedPair.getAmount()
                        ))
                        .collect(Collectors.toList())
        ));
    }

    public int getWeight() {
        return weight;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
