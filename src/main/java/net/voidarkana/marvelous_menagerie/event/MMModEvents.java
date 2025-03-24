package net.voidarkana.marvelous_menagerie.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.villager.MMVillagerProfessions;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

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

}
