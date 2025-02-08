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

    //Chud
    public static final RegistryObject<Item> CHUD_SPAWN_EGG = ITEMS.register("chud_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.CHUD, 0xb39a98, 0x4c2f33, new Item.Properties()));

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

    //Arandaspis
    public static final RegistryObject<Item> ARANDASPIS_FOSSIL = ITEMS.register("arandaspis_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_SPAWN_EGG = ITEMS.register("arandaspis_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.ARANDASPIS, 0x637377, 0x88a5b9, new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_BUCKET = ITEMS.register("arandaspis_bucket",
            () -> new FishBucketItem(MMEntities.ARANDASPIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Trilobite
    public static final RegistryObject<Item> TRILO_FOSSIL = ITEMS.register("trilo_fossil",
            ()-> new Item(new Item.Properties()));

    //Anomalocaris
    public static final RegistryObject<Item> CARIS_FOSSIL = ITEMS.register("caris_fossil",
            ()-> new Item(new Item.Properties()));

    //Pikaia
    public static final RegistryObject<Item> PIKAIA_FOSSIL = ITEMS.register("pikaia_fossil",
            ()-> new Item(new Item.Properties()));

    //Hallucigenia
    public static final RegistryObject<Item> HALLU_FOSSIL = ITEMS.register("hallu_fossil",
            ()-> new Item(new Item.Properties()));

    //Falcatus
    public static final RegistryObject<Item> FALCA_FOSSIL = ITEMS.register("falca_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FALCA_SPAWN_EGG = ITEMS.register("falcatus_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.FALCATUS, 0x9bcbaf, 0xc0d3ca, new Item.Properties()));
    public static final RegistryObject<Item> FALCA_BUCKET = ITEMS.register("falcatus_bucket",
            () -> new FishBucketItem(MMEntities.FALCATUS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Apthoroblattina
    public static final RegistryObject<Item> ROACH_FOSSIL = ITEMS.register("roach_fossil",
            ()-> new Item(new Item.Properties()));

    //Inostrancevia
    public static final RegistryObject<Item> GORGON_FOSSIL = ITEMS.register("gorgon_fossil",
            ()-> new Item(new Item.Properties()));

    //Lystrosaurus
    public static final RegistryObject<Item> LYSTRO_FOSSIL = ITEMS.register("lystro_fossil",
            ()-> new Item(new Item.Properties()));

    //Sniffer
    public static final RegistryObject<Item> SNIFFER_FOSSIL = ITEMS.register("sniffer_fossil",
            ()-> new Item(new Item.Properties()));

    //Ophthalmosaurus
    public static final RegistryObject<Item> OPHTHALMO_FOSSIL = ITEMS.register("ophthalmo_fossil",
            ()-> new Item(new Item.Properties()));

    //Therizinosaurus
    public static final RegistryObject<Item> THERI_FOSSIL = ITEMS.register("theri_fossil",
            ()-> new Item(new Item.Properties()));

    //Tlatolophus
    public static final RegistryObject<Item> TLATO_FOSSIL = ITEMS.register("tlato_fossil",
            ()-> new Item(new Item.Properties()));

    //Borealopelta
    public static final RegistryObject<Item> PELTA_FOSSIL = ITEMS.register("pelta_fossil",
            ()-> new Item(new Item.Properties()));

    //Daeodon
    public static final RegistryObject<Item> DAEODON_FOSSIL = ITEMS.register("daeodon_fossil",
            ()-> new Item(new Item.Properties()));

    //Dawn Horse
    public static final RegistryObject<Item> DAWN_FOSSIL = ITEMS.register("dawn_fossil",
            ()-> new Item(new Item.Properties()));

    //Leptictidium
    public static final RegistryObject<Item> LEPTI_FOSSIL = ITEMS.register("lepti_fossil",
            ()-> new Item(new Item.Properties()));

    //Eolactoria
    public static final RegistryObject<Item> EOLACTORIA_FOSSIL = ITEMS.register("eolactoria_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EOLACTORIA_SPAWN_EGG = ITEMS.register("eolactoria_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.EOLACTORIA, 0xa86640, 0xf1d87f, new Item.Properties()));
    public static final RegistryObject<Item> EOLACTORIA_BUCKET = ITEMS.register("eolactoria_bucket",
            () -> new FishBucketItem(MMEntities.EOLACTORIA, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Kelenken
    public static final RegistryObject<Item> KELENKEN_FOSSIL = ITEMS.register("kelenken_fossil",
            ()-> new Item(new Item.Properties()));

    //Josephoartigasia
    public static final RegistryObject<Item> JOSEPHO_FOSSIL = ITEMS.register("josepho_fossil",
            ()-> new Item(new Item.Properties()));

    //Slovenicus
    public static final RegistryObject<Item> SLOV_FOSSIL = ITEMS.register("slov_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SLOVENICUS_SPAWN_EGG = ITEMS.register("slovenicus_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.SLOVENICUS, 0x713126, 0x9de444, new Item.Properties()));
    public static final RegistryObject<Item> SLOVENICUS_BUCKET = ITEMS.register("slovenicus_bucket",
            () -> new FishBucketItem(MMEntities.SLOVENICUS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Dodo
    public static final RegistryObject<Item> DODO_FOSSIL = ITEMS.register("dodo_fossil",
            ()-> new Item(new Item.Properties()));

    //Thylacine
    public static final RegistryObject<Item> THYLACINE_FOSSIL = ITEMS.register("thylacine_fossil",
            ()-> new Item(new Item.Properties()));

    //Steller's Sea Cow
    public static final RegistryObject<Item> STELLER_FOSSIL = ITEMS.register("steller_fossil",
            ()-> new Item(new Item.Properties()));

    //Elephant Bird
    public static final RegistryObject<Item> ELEBIRD_FOSSIL = ITEMS.register("elebird_fossil",
            ()-> new Item(new Item.Properties()));

    //Doedicurus
    public static final RegistryObject<Item> DOEDICURUS_FOSSIL = ITEMS.register("doedicurus_fossil",
            ()-> new Item(new Item.Properties()));

    //Fossils
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

    //Materials
    public static final RegistryObject<Item> CHRONOTITE = ITEMS.register("chronotite",
            ()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
