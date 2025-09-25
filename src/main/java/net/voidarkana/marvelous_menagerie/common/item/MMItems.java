package net.voidarkana.marvelous_menagerie.common.item;

import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMBoatEntity;
import net.voidarkana.marvelous_menagerie.common.item.custom.*;

public class MMItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MODID);

    public static final RegistryObject<Item> PALEO_TOOLKIT = ITEMS.register("paleo_toolkit",
            ()-> new PaleoToolkit(new Item.Properties().durability(30)));

    public static final RegistryObject<Item> PALEONOMICON = ITEMS.register("paleonomicon",
            Paleonomicon::new);

    public static final RegistryObject<Item> OPAL = ITEMS.register("opal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHRONO_WATCH = ITEMS.register("chrono_watch",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(16)));

    //April Fools
    public static final RegistryObject<Item> RAW_CHUD = ITEMS.register("raw_chud",
            ()-> new Item(new Item.Properties().food(MMFoods.RAW_SACA)));
    public static final RegistryObject<Item> COOKED_CHUD = ITEMS.register("cooked_chud",
            ()-> new Item(new Item.Properties().food(MMFoods.GOLDEN_SACA)));


    //Chud
    public static final RegistryObject<Item> CHUD_SPAWN_EGG = ITEMS.register("chud_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.CHUD, 0xb39a98, 0x4c2f33, new Item.Properties()));

    //Beholder
    public static final RegistryObject<Item> BEHOLDER_SPAWN_EGG = ITEMS.register("beholder_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.BEHOLDER, 0x46b06a, 0x194d3f, new Item.Properties()));

    //Sacabambaspis
    public static final RegistryObject<Item> SACA_SPAWN_EGG = ITEMS.register("saca_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.SACABAMBASPIS, 0xDFD5B7, 0xAE9C79, new Item.Properties()));
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
    public static final RegistryObject<Item> ARANDASPIS_SPAWN_EGG = ITEMS.register("arandaspis_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.ARANDASPIS, 0x637377, 0x88a5b9, new Item.Properties()));
    public static final RegistryObject<Item> ARANDASPIS_BUCKET = ITEMS.register("arandaspis_bucket",
            () -> new FishBucketItem(MMEntities.ARANDASPIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> ARANDASPIS_FOSSIL = ITEMS.register("arandaspis_fossil",
            ()-> new Item(new Item.Properties()));

    //Trilobite
    public static final RegistryObject<Item> TRILO_FOSSIL = ITEMS.register("trilo_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRILO_BITE = ITEMS.register("trilo_bite",
            ()-> new Item(new Item.Properties().food(MMFoods.TRILO_BITE)));
    public static final RegistryObject<Item> TRILOBITE_SPAWN_EGG = ITEMS.register("trilobite_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.TRILOBITE, 0x4F281E, 0x745750, new Item.Properties()));
    public static final RegistryObject<Item> TRILO_BUCKET = ITEMS.register("trilo_bucket",
            () -> new PatternedAnimalBucketItem(MMEntities.TRILOBITE, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Anomalocaris
    public static final RegistryObject<Item> CARIS_SPAWN_EGG = ITEMS.register("anomalocaris_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.ANOMALOCARIS, 0x984B4B, 0x722430, new Item.Properties()));
    public static final RegistryObject<Item> CARIS_BUCKET = ITEMS.register("anomalocaris_bucket",
            () -> new FishBucketItem(MMEntities.ANOMALOCARIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CARIS_FOSSIL = ITEMS.register("caris_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARIS_LENS = ITEMS.register("anomalocaris_lens",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARIS_SCUTE = ITEMS.register("anomalocaris_scute",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANOMALOUS_GOGGLES = ITEMS.register("anomalous_goggles",
            ()-> new AnomalousGogglesItem(MMArmorMaterials.CARIS_SCUTE, ArmorItem.Type.HELMET, new Item.Properties()));

    //Pikaia
    public static final RegistryObject<Item> PIKAIA_FOSSIL = ITEMS.register("pikaia_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIKAIA_SPAWN_EGG = ITEMS.register("pikaia_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.PIKAIA, 0xc7d3d2, 0x9db7b7, new Item.Properties()));
    public static final RegistryObject<Item> PIKAIA_BUCKET = ITEMS.register("pikaia_bucket",
            () -> new FishBucketItem(MMEntities.PIKAIA, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Hallucigenia
    public static final RegistryObject<Item> HALLU_FOSSIL = ITEMS.register("hallu_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HALLUCIGENIA_SPAWN_EGG = ITEMS.register("hallu_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.HALLUCIGENIA, 0xcd90cd, 0xcd90cd, new Item.Properties()));
    public static final RegistryObject<Item> HALLUCIGENIA_BUCKET = ITEMS.register("hallu_bucket",
            () -> new FishBucketItem(MMEntities.HALLUCIGENIA, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> HALLUCIGENIC_SLIME = ITEMS.register("hallu_slime",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WARNING = ITEMS.register("warning",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_ROLL = ITEMS.register("magic_roll",
            ()-> new Item(new Item.Properties().food(MMFoods.MAGIC_ROLL).rarity(Rarity.RARE)));

    //Falcatus
    public static final RegistryObject<Item> FALCA_FOSSIL = ITEMS.register("falca_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FALCA_SPAWN_EGG = ITEMS.register("falcatus_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.FALCATUS, 0x9bcbaf, 0xc0d3ca, new Item.Properties()));
    public static final RegistryObject<Item> FALCA_BUCKET = ITEMS.register("falcatus_bucket",
            () -> new FishBucketItem(MMEntities.FALCATUS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Apthoroblattina
    public static final RegistryObject<Item> ROACH_FOSSIL = ITEMS.register("roach_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROACH_SPAWN_EGG = ITEMS.register("roach_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.ROACH, 0x201e24, 0x453638, new Item.Properties()));

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
    public static final RegistryObject<Item> OPHTHALMO_ARMOR = ITEMS.register("ophthalmo_armor",
            ()-> new OphthalmoArmorItem(new Item.Properties().durability(300),
                    new ItemStack(MMItems.CARIS_SCUTE.get())));

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
    public static final RegistryObject<Item> DAWN_HORSE_SPAWN_EGG = ITEMS.register("dawn_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.DAWN_HORSE, 0xb89268, 0xffe5b1, new Item.Properties()));
    public static final RegistryObject<Item> SKELETON_DAWN_HORSE_SPAWN_EGG = ITEMS.register("skeleton_dawn_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.SKELETON_DAWN_HORSE, 0xf4d4a2, 0xffeec6, new Item.Properties()));
    public static final RegistryObject<Item> ZOMBIE_DAWN_HORSE_SPAWN_EGG = ITEMS.register("zombie_dawn_horse_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.ZOMBIE_DAWN_HORSE, 0x574d3c, 0x8a8667, new Item.Properties()));
    public static final RegistryObject<Item> RAW_DAWN_HORSE = ITEMS.register("raw_dawn_horse",
            ()-> new Item(new Item.Properties().food(MMFoods.RAW_DAWN_HORSE)));
    public static final RegistryObject<Item> COOKED_DAWN_HORSE = ITEMS.register("cooked_dawn_horse",
            ()-> new Item(new Item.Properties().food(MMFoods.COOKED_DAWN_HORSE)));

    //Leptictidium
    public static final RegistryObject<Item> LEPTI_FOSSIL = ITEMS.register("lepti_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEPTICTIDIUM_SPAWN_EGG = ITEMS.register("leptictidium_spawn_egg",
            () -> new ForgeSpawnEggItem(MMEntities.LEPTICTIDIUM, 0xc09251, 0xe28554, new Item.Properties()));

    //Eolactoria
    public static final RegistryObject<Item> EOLACTORIA_FOSSIL = ITEMS.register("eolactoria_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EOLACTORIA_SPAWN_EGG = ITEMS.register("eolactoria_spawn_egg",
            ()-> new FishSpawnEggItem(MMEntities.EOLACTORIA, 0xa86640, 0xf1d87f, new Item.Properties()));
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
            ()-> new FishSpawnEggItem(MMEntities.SLOVENICUS, 0x713126, 0x9de444, new Item.Properties()));
    public static final RegistryObject<Item> SLOVENICUS_BUCKET = ITEMS.register("slovenicus_bucket",
            () -> new FishBucketItem(MMEntities.SLOVENICUS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));

    //Dodo
    public static final RegistryObject<Item> DODO_FOSSIL = ITEMS.register("dodo_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.DODO, 0x7f929a, 0x374454, new Item.Properties()));

    //Thylacine
    public static final RegistryObject<Item> THYLACINE_FOSSIL = ITEMS.register("thylacine_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THYLA_SPAWN_EGG = ITEMS.register("thylacine_spawn_egg",
            ()-> new ForgeSpawnEggItem(MMEntities.THYLACINE, 0xe6a25f, 0x443429, new Item.Properties()));

    //Steller's Sea Cow
    public static final RegistryObject<Item> STELLER_FOSSIL = ITEMS.register("steller_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STELLER_MILK = ITEMS.register("steller_sea_cow_milk_bucket",
            ()-> new SeaCowMilkBucketItem((new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> STELLER_ICE_CREAM = ITEMS.register("steller_ice_cream",
            ()-> new StackableBowlFoodItem(new Item.Properties().food(MMFoods.STELLER_ICE_CREAM).stacksTo(16)));


    //Elephant Bird
    public static final RegistryObject<Item> ELEBIRD_FOSSIL = ITEMS.register("elebird_fossil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_ELEPHANT_EGG = ITEMS.register("cracked_elephant_egg",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EGG_SHELL_FRAGMENT = ITEMS.register("egg_shell_fragment",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOILED_ELEPHANT_EGG = ITEMS.register("boiled_elephant_egg",
            ()-> new EggInShellFoodItem(new Item.Properties().craftRemainder(MMItems.EGG_SHELL_FRAGMENT.get()).food(MMFoods.BOILED_ELEPHANT_EGG)));
    public static final RegistryObject<Item> JUMBO_OMELETTE = ITEMS.register("jumbo_omelette",
            ()-> new Item(new Item.Properties().food(MMFoods.JUMBO_OMELETTE)));
    public static final RegistryObject<Item> EGG_SHELLMET = ITEMS.register("egg_shellmet",
            ()-> new EggShellmetItem(MMArmorMaterials.EGGSHELL, ArmorItem.Type.HELMET, new Item.Properties()));

    //Doedicurus
    public static final RegistryObject<Item> DOEDICURUS_FOSSIL = ITEMS.register("doedicurus_fossil",
            ()-> new Item(new Item.Properties()));



    //Plants

    //Sigillaria
    public static final RegistryObject<Item> SIGILLARIA_SIGN = ITEMS.register("sigillaria_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), MMBlocks.SIGILLARIA_SIGN.get(), MMBlocks.SIGILLARIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> SIGILLARIA_HANGING_SIGN = ITEMS.register("sigillaria_hanging_sign",
            ()-> new HangingSignItem(MMBlocks.SIGILLARIA_HANGING_SIGN.get(), MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SIGILLARIA_BOAT = ITEMS.register("sigillaria_boat",
            () -> new MMBoatItem(false, MMBoatEntity.Type.SIGILLARIA, new Item.Properties()));
    public static final RegistryObject<Item> SIGILLARIA_CHEST_BOAT = ITEMS.register("sigillaria_chest_boat",
            () -> new MMBoatItem(true, MMBoatEntity.Type.SIGILLARIA, new Item.Properties()));

    //Prototaxites
    public static final RegistryObject<Item> PROTOTAXITES_SIGN = ITEMS.register("prototaxites_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), MMBlocks.PROTOTAXITES_SIGN.get(), MMBlocks.PROTOTAXITES_WALL_SIGN.get()));
    public static final RegistryObject<Item> PROTOTAXITES_HANGING_SIGN = ITEMS.register("prototaxites_hanging_sign",
            ()-> new HangingSignItem(MMBlocks.PROTOTAXITES_HANGING_SIGN.get(), MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PROTOTAXITES_BOAT = ITEMS.register("prototaxites_boat",
            () -> new MMBoatItem(false, MMBoatEntity.Type.PROTOTAXITES, new Item.Properties()));
    public static final RegistryObject<Item> PROTOTAXITES_CHEST_BOAT = ITEMS.register("prototaxites_chest_boat",
            () -> new MMBoatItem(true, MMBoatEntity.Type.PROTOTAXITES, new Item.Properties()));

    //Calamites
    public static final RegistryObject<Item> CALAMITES_SIGN = ITEMS.register("calamites_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), MMBlocks.CALAMITES_SIGN.get(), MMBlocks.CALAMITES_WALL_SIGN.get()));
    public static final RegistryObject<Item> CALAMITES_HANGING_SIGN = ITEMS.register("calamites_hanging_sign",
            ()-> new HangingSignItem(MMBlocks.CALAMITES_HANGING_SIGN.get(), MMBlocks.CALAMITES_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CALAMITES_BOAT = ITEMS.register("calamites_boat",
            () -> new MMBoatItem(false, MMBoatEntity.Type.CALAMITES, new Item.Properties()));
    public static final RegistryObject<Item> CALAMITES_CHEST_BOAT = ITEMS.register("calamites_chest_boat",
            () -> new MMBoatItem(true, MMBoatEntity.Type.CALAMITES, new Item.Properties()));

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
