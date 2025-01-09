package net.voidarkana.marvelous_menagerie.common.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.item.custom.PaleoToolkit;

public class MMItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MODID);

    public static final RegistryObject<Item> PALEO_TOOLKIT = ITEMS.register("paleo_toolkit",
            ()-> new PaleoToolkit(new Item.Properties()));

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

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
