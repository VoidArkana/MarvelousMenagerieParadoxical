package net.voidarkana.marvelous_menagerie.data.codec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.core.Registry;

import java.util.*;

public class FossilCleaningManager extends SimpleJsonResourceReloadListener {
    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final Logger LOGGER = LogManager.getLogger();

    protected static Map<Item, List<WeightedItemCodec>> recipeList = new HashMap<>();
    protected static Map<ResourceLocation, FossilCleaningRecipeCodec> recipeListRl = new HashMap<>();
    private final String folderName;

    public FossilCleaningManager(){
        this(GSON_INSTANCE, "gameplay/fossil_cleaning");
    }

    public FossilCleaningManager(Gson pGson, String pDirectory) {
        super(pGson, pDirectory);
        this.folderName = pDirectory;
    }

    public static Map<Item, List<WeightedItemCodec>> getRecipes() {
        return recipeList;
    }

    public static void setRecipeList(Map<Item, List<WeightedItemCodec>> recipeList) {
        FossilCleaningManager.recipeList = recipeList;
    }

    public static List<WeightedItemCodec> getItems(Item item) {
        return recipeList.getOrDefault(item, Collections.emptyList());
    }

    public static boolean containsRecipe(Item item) {
        return recipeList.containsKey(item);
    }

    public static ItemStack getRandomItemStack(Item input, Level level) {
        if (null == level) {
            return ItemStack.EMPTY;
        }

        Item outputItem = null;

        List<WeightedItemCodec> outputs = recipeList.get(input);

        int totalWeight = 0;
        for (WeightedItemCodec itemWeightedPair : outputs) {
            totalWeight += itemWeightedPair.getWeight();
        }

        System.out.println("total: " + totalWeight);

        int randomNr = level.random.nextInt(totalWeight);
        int cumulativeWeight = 0;
        System.out.println("randomNR: " + randomNr);

        for (WeightedItemCodec itemWeightedPair : outputs) {
            cumulativeWeight += itemWeightedPair.getWeight();
            System.out.println("cumulative: " + cumulativeWeight);
            if (randomNr < cumulativeWeight) {
                outputItem = itemWeightedPair.getItem();
                break;
            }
        }

        return new ItemStack(outputItem);
    }

    public static void populateRecipeMap(Level level) {
        recipeList.clear();
        Map<Item, List<WeightedItemCodec>> weightedPairsTemp = new HashMap<>();
        for (ResourceLocation resourceLocation : recipeListRl.keySet()) {
            FossilCleaningRecipeCodec analyzerRecipeCodec = recipeListRl.get(resourceLocation);

            List<ResourceLocation> tagsRL = analyzerRecipeCodec.getInputItemTags();
            List<ResourceLocation> itemsRL = analyzerRecipeCodec.getInputItems();

            for (ResourceLocation tag : tagsRL) {
                TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM, tag);
                level.registryAccess().registry(Registries.ITEM).ifPresent(reg -> {
                    Iterable<Holder<Item>> itemHolder = reg.getTagOrEmpty(itemTagKey);

                    for (Holder<Item> tempItemHolder : itemHolder) {
                        Item item = tempItemHolder.get();
                        if(weightedPairsTemp.containsKey(item)) LOGGER.warn("Item {} for resourceLocation {} already has items assigned, adding more", item, resourceLocation);
                        List<WeightedItemCodec> weightedPairsList = weightedPairsTemp.getOrDefault(item, new ArrayList<>());
                        weightedPairsList.addAll(analyzerRecipeCodec.getItemWeightedPairs());
                        weightedPairsTemp.put(item, weightedPairsList);

                    }
                    if (!itemHolder.iterator().hasNext()) {
                        LOGGER.error("Tag for {} does not have any items!", itemTagKey);
                        LOGGER.error("Analyzer recipe for {} might not have any items assigned, or it uses items from a mod, but the mod isn't installed!", resourceLocation);
                    }
                });
            }

            for (ResourceLocation items : itemsRL) {
                level.registryAccess().registry(Registries.ITEM).ifPresent(reg -> {
                    Item item = reg.get(items);
                    if(item == null) {
                        LOGGER.error("Item {} does not exist, currently parsing through {}!", items, resourceLocation);
                        LOGGER.error("Recipe for {} might not have any items assigned, or it uses items from a mod, but the mod isn't installed!", resourceLocation);
                        return;
                    }
                    if(weightedPairsTemp.containsKey(item)) LOGGER.warn("Item {} for resourceLocation {} already has items assigned, adding more", item, resourceLocation);
                    List<WeightedItemCodec> weightedPairsList = weightedPairsTemp.getOrDefault(item, new ArrayList<>());
                    weightedPairsList.addAll(analyzerRecipeCodec.getItemWeightedPairs());
                    weightedPairsTemp.put(item, weightedPairsList);
                });
            }
        }
        LOGGER.info("Registered {} jsons with items!", weightedPairsTemp.keySet().size());
        recipeList.putAll(weightedPairsTemp);
        weightedPairsTemp.clear();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        FossilCleaningManager.recipeList.clear();
        Map<ResourceLocation, FossilCleaningRecipeCodec> recipeList = new HashMap();
        Iterator var5 = jsons.entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<ResourceLocation, JsonElement> entry = (Map.Entry)var5.next();
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();
            FossilCleaningRecipeCodec.CODEC.decode(JsonOps.INSTANCE, element).get().ifLeft((result) -> {
                FossilCleaningRecipeCodec analyzerRecipe = result.getFirst();
                recipeList.put(key, analyzerRecipe);
            }).ifRight((partial) -> {
                LOGGER.error("Failed to parse recipe JSON for {} due to: {}", this.folderName, partial.message());
            });
        }

        recipeListRl = recipeList;
        LOGGER.info("Data loader for {} loaded {} jsons", this.folderName, recipeListRl.size());
    }
}
