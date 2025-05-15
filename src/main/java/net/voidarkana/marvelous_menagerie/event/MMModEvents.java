package net.voidarkana.marvelous_menagerie.event;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.villager.MMVillagerProfessions;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID)
public class MMModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){

        if (event.getType() == MMVillagerProfessions.PALEONTOLOGIST.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 15),
                    new ItemStack(MMItems.PALEONOMICON.get(), 1),
                    3, 8, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(MMItems.PALEO_TOOLKIT.get(), 1),
                    3, 8, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(Items.IRON_PICKAXE, 1),
                    3, 8, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(Items.BRUSH, 1),
                    3, 8, 0.02f));


            // Level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.SHALE_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.GABBRO_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.SILTSTONE_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.MUDSTONE_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.LIMESTONE_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.CHALK_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.DIATOMITE_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.BRECCIA_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.PERMAFROST_FOSSIL.get(), 10),
                    new ItemStack(Items.EMERALD, 5),
                    10, 8, 0.02f));


            // Level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.SHALE_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.SILTSTONE_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.GABBRO_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.LIMESTONE_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.MUDSTONE_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.CHALK_FOSSIL.get(), 6),
                    3, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.BRECCIA_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.DIATOMITE_FOSSIL.get(), 6),
                    5, 8, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.PERMAFROST_FOSSIL.get(), 6),
                    5, 8, 0.02f));


            // Level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.CHRONOTITE.get(), 1),
                    new ItemStack(Items.EMERALD, 3),
                    6, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MMItems.OPAL.get(), 1),
                    new ItemStack(Items.EMERALD, 3),
                    6, 8, 0.02f));


            //level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.CHRONOTITE.get(), 3),
                    2, 8, 0.02f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(MMItems.OPAL.get(), 3),
                    2, 8, 0.02f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 30),
                    new ItemStack(MMBlocks.CHRONO_ALTAR.get(), 1),
                    2, 8, 0.02f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 25),
                    new ItemStack(MMBlocks.CHRONO_PEDESTAL.get(), 2),
                    2, 8, 0.02f));

        }
    }


    /**
     * We use FMLServerAboutToStartEvent as the dynamic registry exists now and all JSON worldgen files were parsed.
     * Mod compat is best done here.
     */
    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();

        // Adds our piece to all village houses pool
        // Note, the resourcelocation is getting the pool files from the data folder. Not assets folder.
        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/plains/houses"),
                "marvelous_menagerie:plains_fossil_1", 250);

        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/plains/houses"),
                "marvelous_menagerie:plains_fossil_2", 250);

        addBuildingToPool(templatePoolRegistry, processorListRegistry,
                new ResourceLocation("minecraft:village/plains/houses"),
                "marvelous_menagerie:plains_fossil_3", 250);
    }

    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(
            Registries.PROCESSOR_LIST, new ResourceLocation("minecraft:empty"));

    /**
     * Adds the building to the targeted pool.
     * We will call this in addNewVillageBuilding method further down to add to every village.
     *
     * Note: This is an additive operation which means multiple mods can do this and they stack with each other safely.
     */
    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
                                          Registry<StructureProcessorList> processorListRegistry,
                                          ResourceLocation poolRL,
                                          String nbtPieceRL,
                                          int weight) {

        // Grabs the processor list we want to use along with our piece.
        // This is a requirement as using the ProcessorLists.EMPTY field will cause the game to throw errors.
        // The reason why is the empty processor list in the world's registry is not the same instance as in that field once the world is started up.
        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        // Grab the pool we want to add to
        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        // Grabs the nbt piece and creates a SinglePoolElement of it that we can add to a structure's pool.
        // Use .legacy( for villages/outposts and .single( for everything else
        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL,
                emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's templates field public for us to see.
        // Weight is handled by how many times the entry appears in this list.
        // We do not need to worry about immutability as this field is created using Lists.newArrayList(); which makes a mutable list.
        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        // Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's rawTemplates field public for us to see.
        // This list of pairs of pieces and weights is not used by vanilla by default but another mod may need it for efficiency.
        // So lets add to this list for completeness. We need to make a copy of the array as it can be an immutable list.

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

}
