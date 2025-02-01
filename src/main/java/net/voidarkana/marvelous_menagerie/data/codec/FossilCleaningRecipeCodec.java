package net.voidarkana.marvelous_menagerie.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.ArrayList;
import java.util.List;

public class FossilCleaningRecipeCodec {

    private List<WeightedItemCodec> itemWeightedPairs;
    private List<ExtraCodecs.TagOrElementLocation> input;
    protected final List<ResourceLocation> spawnItems;
    protected final List<ResourceLocation> spawnItemTags;

    public FossilCleaningRecipeCodec(List<ExtraCodecs.TagOrElementLocation> inputItems, List<WeightedItemCodec> itemWeightedPairs) {
        List<ResourceLocation> spawnItemTags = new ArrayList<>();
        List<ResourceLocation> spawnItems = new ArrayList<>();
        for(ExtraCodecs.TagOrElementLocation tagOrElementLocation : inputItems){
            if(tagOrElementLocation.tag()){
                spawnItemTags.add(tagOrElementLocation.id());
            } else {
                spawnItems.add(tagOrElementLocation.id());
            }
        }
        this.spawnItems = spawnItems;
        this.spawnItemTags = spawnItemTags;
        this.itemWeightedPairs = itemWeightedPairs;
    }

    public static Codec<FossilCleaningRecipeCodec> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(
                    ExtraCodecs.TAG_OR_ELEMENT_ID.listOf().fieldOf("input").forGetter(i -> i.input),
                    WeightedItemCodec.CODEC.listOf().fieldOf("entries").forGetter(e -> e.itemWeightedPairs)
            ).apply(inst, FossilCleaningRecipeCodec::new)
    );

    public void addToList(List<WeightedItemCodec> itemWeightedPairs) {
        this.itemWeightedPairs.addAll(itemWeightedPairs);
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (WeightedItemCodec itemWeightedPair : getItemWeightedPairs()) {
            totalWeight += itemWeightedPair.getWeight();
        }
        return totalWeight;
    }

    public List<WeightedItemCodec> getItemWeightedPairs() {
        return itemWeightedPairs;
    }

    public List<ResourceLocation> getInputItemTags() {
        return spawnItemTags;
    }

    public List<ResourceLocation> getInputItems() {
        return spawnItems;
    }
}
