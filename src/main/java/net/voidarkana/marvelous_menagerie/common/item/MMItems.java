package net.voidarkana.marvelous_menagerie.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.item.custom.FishBucketItem;
import net.voidarkana.marvelous_menagerie.common.item.custom.PaleoToolkit;

public class MMItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MODID);

    public static final RegistryObject<Item> PALEO_TOOLKIT = ITEMS.register("paleo_toolkit",
            ()-> new PaleoToolkit(new Item.Properties()));

    //Arandaspis
    public static final RegistryObject<Item> ARANDASPIS_FOSSIL = ITEMS.register("arandaspis_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_SPAWN_EGG = ITEMS.register("arandaspis_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.ARANDASPIS, 0x637377, 0x88a5b9, new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_BUCKET = ITEMS.register("arandaspis_bucket",
            () -> new FishBucketItem(MMEntities.ARANDASPIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Sacabambaspis
    public static final RegistryObject<Item> SACA_SPAWN_EGG = ITEMS.register("saca_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.SACABAMBASPIS, 0xDFD5B7, 0xAE9C79, new Item.Properties()));
    public static final RegistryObject<Item> SACA_BUCKET = ITEMS.register("saca_bucket",
            () -> new FishBucketItem(MMEntities.SACABAMBASPIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> SACABAMBASPIS = ITEMS.register("raw_saca",
            ()-> new Item(new Item.Properties().food(MMFoods.RAW_SACA)));
    public static final RegistryObject<Item> COOKED_SACA = ITEMS.register("cooked_saca",
            ()-> new Item(new Item.Properties().food(MMFoods.COOKED_SACA)));
    public static final RegistryObject<Item> GOLDEN_SACA = ITEMS.register("golden_saca",
            ()-> new Item(new Item.Properties().food(MMFoods.GOLDEN_SACA)));
    public static final RegistryObject<Item> SACA_FOSSIL = ITEMS.register("saca_fossil",
            ()-> new Item(new Item.Properties()));


    public static final RegistryObject<Item> SHALE_FOSSIL = ITEMS.register("shale_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILTSTONE_FOSSIL = ITEMS.register("siltstone_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GABBRO_FOSSIL = ITEMS.register("gabbro_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUDSTONE_FOSSIL = ITEMS.register("mudstone_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIMESTONE_FOSSIL = ITEMS.register("limestone_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHALK_FOSSIL = ITEMS.register("chalk_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRECCIA_FOSSIL = ITEMS.register("breccia_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIATOMITE_FOSSIL = ITEMS.register("diatomite_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERMAFROST_FOSSIL = ITEMS.register("permafrost_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHRONOTITE = ITEMS.register("chronotite",
            ()-> new Item(new Item.Properties()));



    //fossils
    public static final RegistryObject<Item> SNIFFER_FOSSIL = ITEMS.register("sniffer_fossil",
            ()-> new Item(new Item.Properties()));



    //spawn eggs
    public static final RegistryObject<Item> CHUD_SPAWN_EGG = ITEMS.register("chud_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.CHUD, 0xb39a98, 0x4c2f33, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
