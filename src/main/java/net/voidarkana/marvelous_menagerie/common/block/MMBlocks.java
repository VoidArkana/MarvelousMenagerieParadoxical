package net.voidarkana.marvelous_menagerie.common.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.*;
import net.voidarkana.marvelous_menagerie.common.block.custom.flammable.FlammableWoodLogBlock;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.*;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.CalamitesTreeGrower;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.SigillariaTreeGrower;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import net.voidarkana.marvelous_menagerie.util.MMWoodTypes;

import java.util.function.Function;
import java.util.function.Supplier;

public class MMBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MarvelousMenagerie.MOD_ID);

    public static final DeferredRegister<PaintingVariant> PAINTINGS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MarvelousMenagerie.MOD_ID);


    public static final RegistryObject<PaintingVariant> CHUD_PAINTING = PAINTINGS.register("chud",
            () -> new PaintingVariant(64, 48));

    public static final RegistryObject<PaintingVariant> OVERWORLDLINGS_PAINTING = PAINTINGS.register("two_overworldlings",
            () -> new PaintingVariant(48, 48));

    public static final RegistryObject<PaintingVariant> DODO_PAINTING = PAINTINGS.register("louisses_dodo",
            () -> new PaintingVariant(48, 32));

    //Borealopelta
    public static final RegistryObject<Block> BOREALOPELTA_EGG = registerBlock("borealopelta_egg",
            () -> new SingleEggBlock(BlockBehaviour.Properties.copy(Blocks.SNIFFER_EGG).randomTicks(),
                    MMEntities.BOREALOPELTA, MMTags.Blocks.DINOSAUR_NEST));

    //Sigillaria plank blocks
    public static final RegistryObject<Block> SIGILLARIA_PLANKS = registerBlock("sigillaria_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SIGILLARIA_STAIRS = registerBlock("sigillaria_stairs",
            () -> new StairBlock(() -> MMBlocks.SIGILLARIA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .ignitedByLava().sound(SoundType.NETHER_WOOD)));
    public static final RegistryObject<Block> SIGILLARIA_SLAB = registerBlock("sigillaria_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SIGILLARIA_BUTTON = registerBlock("sigillaria_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.NETHER_WOOD).ignitedByLava()
                    , BlockSetType.CRIMSON, 25, true));
    public static final RegistryObject<Block> SIGILLARIA_PRESSURE_PLATE = registerBlock("sigillaria_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy
                    (Blocks.OAK_PRESSURE_PLATE).sound(SoundType.NETHER_WOOD).ignitedByLava(), BlockSetType.CRIMSON));
    public static final RegistryObject<Block> SIGILLARIA_FENCE = registerBlock("sigillaria_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SIGILLARIA_FENCE_GATE = registerBlock("sigillaria_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).ignitedByLava().sound(SoundType.NETHER_WOOD),
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE));


    //Sigillaria Door and Trapdoor
    public static final RegistryObject<Block> SIGILLARIA_DOOR = registerBlock("sigillaria_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));
    public static final RegistryObject<Block> SIGILLARIA_TRAPDOOR = registerBlock("sigillaria_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));

    //Sigillaria signs
    public static final RegistryObject<Block> SIGILLARIA_SIGN = BLOCKS.register("sigillaria_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), MMWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_WALL_SIGN = BLOCKS.register("sigillaria_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), MMWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_HANGING_SIGN = BLOCKS.register("sigillaria_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), MMWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_WALL_HANGING_SIGN = BLOCKS.register("sigillaria_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), MMWoodTypes.SIGILLARIA));

    //Sigillaria Mosaic Blocks
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC = registerBlock("sigillaria_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC_STAIRS = registerBlock("sigillaria_mosaic_stairs",
            () -> new StairBlock(() -> MMBlocks.SIGILLARIA_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC_SLAB = registerBlock("sigillaria_mosaic_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    //Sigillaria logs and wood
    public static final RegistryObject<Block> SIGILLARIA_STEM = registerBlock("sigillaria_stem",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> SIGILLARIA_WOOD = registerBlock("sigillaria_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> STRIPPED_SIGILLARIA_STEM = registerBlock("stripped_sigillaria_stem",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> STRIPPED_SIGILLARIA_WOOD = registerBlock("stripped_sigillaria_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.STEM)));

    //Sigillaria Leaves
    public static final RegistryObject<Block> SIGILLARIA_LEAVES = registerBlock("sigillaria_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //Sigillaria Sapling Blocks
    public static final RegistryObject<Block> SIGILLARIA_SAPLING = registerBlock("sigillaria_sapling",
            () -> new SaplingBlock(new SigillariaTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_SIGILLARIA_SAPLING = registerBlock("potted_sigillaria_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), MMBlocks.SIGILLARIA_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    //Cooksonia
    public static final RegistryObject<Block> COOKSONIA = registerBlock("cooksonia",
            () -> new CooksoniaBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE).noOcclusion().noCollission()));

    //Dickinsonia
    public static final RegistryObject<Block> DICKINSONIA = registerBlock("dickinsonia",
            () -> new DickinsoniaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).noOcclusion().instabreak().lightLevel((p_152684_) -> {
                return 6;
            })));



    //prototaxites blocks
    public static final RegistryObject<Block> PROTOTAXITES = registerBlock("prototaxites",
            () -> new PrototaxitesBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_GREEN).noOcclusion(),
                    ModConfiguredFeatures.PROTOTAXITES_KEY));
    public static final RegistryObject<Block> POTTED_PROTOTAXITES = registerBlock("potted_prototaxites",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), MMBlocks.PROTOTAXITES,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_CACTUS).noOcclusion()));

    public static final RegistryObject<Block> PROTOTAXITES_BLOCK = registerBlock("prototaxites_block",
            () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_GREEN)));



    public static final RegistryObject<Block> PROTOTAXITES_PLANKS = registerBlock("prototaxites_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_STAIRS = registerBlock("prototaxites_stairs",
            () -> new StairBlock(() -> MMBlocks.PROTOTAXITES_PLANKS.get().defaultBlockState(),

                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> PROTOTAXITES_SLAB = registerBlock("prototaxites_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_BUTTON = registerBlock("prototaxites_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON, 25, true));

    public static final RegistryObject<Block> PROTOTAXITES_PRESSURE_PLATE = registerBlock("prototaxites_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON));

    public static final RegistryObject<Block> PROTOTAXITES_FENCE = registerBlock("prototaxites_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_FENCE_GATE = registerBlock("prototaxites_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE));


    //Prototaxites Door and Trapdoor
    public static final RegistryObject<Block> PROTOTAXITES_DOOR = registerBlock("prototaxites_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));

    public static final RegistryObject<Block> PROTOTAXITES_TRAPDOOR = registerBlock("prototaxites_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));

    //Prototaxites signs
    public static final RegistryObject<Block> PROTOTAXITES_SIGN = BLOCKS.register("prototaxites_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), MMWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_WALL_SIGN = BLOCKS.register("prototaxites_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), MMWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_HANGING_SIGN = BLOCKS.register("prototaxites_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), MMWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_WALL_HANGING_SIGN = BLOCKS.register("prototaxites_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), MMWoodTypes.PROTOTAXITES));

    //Prototaxites Mosaic Blocks
    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC = registerBlock("prototaxites_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC_STAIRS = registerBlock("prototaxites_mosaic_stairs",
            () -> new StairBlock(() -> MMBlocks.PROTOTAXITES_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC_SLAB = registerBlock("prototaxites_mosaic_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));



    //Calamites
    public static final RegistryObject<Block> CALAMITES_SAPLING = registerBlock("calamites_sapling",
            () -> new SaplingBlock(new CalamitesTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_CALAMITES_SAPLING = registerBlock("potted_calamites_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), MMBlocks.CALAMITES_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> CALAMITES_BRANCH = registerBlock("calamites_branch",
            ()-> new CalamitesBranchBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).sound(SoundType.BAMBOO_WOOD).strength(0.1f)));
    public static final RegistryObject<Block> CALAMITES_LOG = registerBlock ("calamites_log",
            () -> new CalamitesLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> STRIPPED_CALAMITES_LOG = registerBlock ("stripped_calamites_log",
            () -> new CalamitesLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> CALAMITES_BARK = registerBlock ("calamites_bark",
            () -> new CalamitesLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> STRIPPED_CALAMITES_BARK = registerBlock ("stripped_calamites_bark",
            () -> new CalamitesLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    //Woodset
    public static final RegistryObject<Block> CALAMITES_BUNDLE = registerBlock("calamites_bundle",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> CALAMITES_BUNDLED_BARK = registerBlock("calamites_bundled_bark",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> STRIPPED_CALAMITES_BUNDLE = registerBlock("stripped_calamites_bundle",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> STRIPPED_CALAMITES_BUNDLED_BARK = registerBlock("stripped_calamites_bundled_bark",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.BAMBOO_WOOD)));


    public static final RegistryObject<Block> CALAMITES_PLANKS = registerBlock("calamites_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> CALAMITES_STAIRS = registerBlock("calamites_stairs",
            () -> new StairBlock(() -> MMBlocks.CALAMITES_PLANKS.get().defaultBlockState(),

                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> CALAMITES_SLAB = registerBlock("calamites_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> CALAMITES_BUTTON = registerBlock("calamites_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON, 25, true));

    public static final RegistryObject<Block> CALAMITES_PRESSURE_PLATE = registerBlock("calamites_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON));

    public static final RegistryObject<Block> CALAMITES_FENCE = registerBlock("calamites_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> CALAMITES_FENCE_GATE = registerBlock("calamites_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN, SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE));


    //Calamites Door and Trapdoor
    public static final RegistryObject<Block> CALAMITES_DOOR = registerBlock("calamites_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).noOcclusion(), BlockSetType.BAMBOO));

    public static final RegistryObject<Block> CALAMITES_TRAPDOOR = registerBlock("calamites_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).noOcclusion(), BlockSetType.BAMBOO));

    //Calamites signs
    public static final RegistryObject<Block> CALAMITES_SIGN = BLOCKS.register("calamites_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), MMWoodTypes.CALAMITES));

    public static final RegistryObject<Block> CALAMITES_WALL_SIGN = BLOCKS.register("calamites_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), MMWoodTypes.CALAMITES));

    public static final RegistryObject<Block> CALAMITES_HANGING_SIGN = BLOCKS.register("calamites_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), MMWoodTypes.CALAMITES));

    public static final RegistryObject<Block> CALAMITES_WALL_HANGING_SIGN = BLOCKS.register("calamites_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), MMWoodTypes.CALAMITES));

    //Calamites Mosaic Blocks
    public static final RegistryObject<Block> CALAMITES_MOSAIC = registerBlock("calamites_mosaic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> CALAMITES_MOSAIC_STAIRS = registerBlock("calamites_mosaic_stairs",
            () -> new StairBlock(() -> MMBlocks.CALAMITES_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> CALAMITES_MOSAIC_SLAB = registerBlock("calamites_mosaic_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));


    //Wiwaxia
    public static final RegistryObject<Block> WIWAXIA = registerBlock("wiwaxia",
            ()-> new WiwaxiaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_PURPLE).noOcclusion().instabreak().lightLevel((p_152684_) -> {return 6;})));

    //Charnia
    public static final RegistryObject<Block> CHARNIA = registerBlock("charnia",
            ()-> new CharniaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_ORANGE)
                    .noOcclusion().sound(SoundType.MOSS).instabreak().noCollission()
                    .lightLevel((pState) -> {return 3 + 3 * pState.getValue(CharniaBlock.PICKLES);})));


    //April Fools
    public static final RegistryObject<Block> CHUD_BLOCK = registerBlock("chud_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.FROGSPAWN)));

    //opal

    public static final RegistryObject<Block> OPAL_BLOCK = registerBlock("opal_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).sound(SoundType.AMETHYST)));

    //shale
    public static final RegistryObject<Block> SHALE = registerBlock("shale",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_SLAB = registerBlock("shale_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_STAIRS = registerBlock("shale_stairs",
            ()-> new StairBlock(() -> MMBlocks.SHALE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_WALL = registerBlock("shale_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> SHALE_FOSSIL_BLOCK = registerBlock("shale_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE), "shale"));


    public static final RegistryObject<Block> POLISHED_SHALE = registerBlock("polished_shale",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_SHALE_SLAB = registerBlock("polished_shale_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_SHALE_STAIRS = registerBlock("polished_shale_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_SHALE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> POLISHED_SHALE_WALL = registerBlock("polished_shale_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> SHALE_BRICKS = registerBlock("shale_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_BRICKS_SLAB = registerBlock("shale_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_BRICKS_STAIRS = registerBlock("shale_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.SHALE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_BRICKS_WALL = registerBlock("shale_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> SHALE_MOSAIC = registerBlock("shale_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_MOSAIC_SLAB = registerBlock("shale_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_MOSAIC_STAIRS = registerBlock("shale_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.SHALE_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHALE_MOSAIC_WALL = registerBlock("shale_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> CHISELED_SHALE = registerBlock("chiseled_shale",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));





    //siltstone
    public static final RegistryObject<Block> SILTSTONE = registerBlock("siltstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_SLAB = registerBlock("siltstone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_STAIRS = registerBlock("siltstone_stairs",
            ()-> new StairBlock(() -> MMBlocks.SILTSTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_WALL = registerBlock("siltstone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> SILTSTONE_FOSSIL_BLOCK = registerBlock("siltstone_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "siltstone"));


    public static final RegistryObject<Block> POLISHED_SILTSTONE = registerBlock("polished_siltstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_SILTSTONE_SLAB = registerBlock("polished_siltstone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_SILTSTONE_STAIRS = registerBlock("polished_siltstone_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_SILTSTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_SILTSTONE_WALL = registerBlock("polished_siltstone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> SILTSTONE_BRICKS = registerBlock("siltstone_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_BRICKS_SLAB = registerBlock("siltstone_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_BRICKS_STAIRS = registerBlock("siltstone_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.SILTSTONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_BRICKS_WALL = registerBlock("siltstone_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> SILTSTONE_MOSAIC = registerBlock("siltstone_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_MOSAIC_SLAB = registerBlock("siltstone_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_MOSAIC_STAIRS = registerBlock("siltstone_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.SILTSTONE_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> SILTSTONE_MOSAIC_WALL = registerBlock("siltstone_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_SILTSTONE = registerBlock("chiseled_siltstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //gabbro
    public static final RegistryObject<Block> GABBRO = registerBlock("gabbro",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_SLAB = registerBlock("gabbro_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_STAIRS = registerBlock("gabbro_stairs",
            ()-> new StairBlock(() -> MMBlocks.GABBRO.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_WALL = registerBlock("gabbro_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> GABBRO_FOSSIL_BLOCK = registerBlock("gabbro_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "gabbro"));


    public static final RegistryObject<Block> POLISHED_GABBRO = registerBlock("polished_gabbro",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_GABBRO_SLAB = registerBlock("polished_gabbro_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_GABBRO_STAIRS = registerBlock("polished_gabbro_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_GABBRO.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_GABBRO_WALL = registerBlock("polished_gabbro_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> GABBRO_BRICKS = registerBlock("gabbro_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_BRICKS_SLAB = registerBlock("gabbro_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_BRICKS_STAIRS = registerBlock("gabbro_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.GABBRO_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_BRICKS_WALL = registerBlock("gabbro_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> GABBRO_MOSAIC = registerBlock("gabbro_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_MOSAIC_SLAB = registerBlock("gabbro_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_MOSAIC_STAIRS = registerBlock("gabbro_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.GABBRO_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GABBRO_MOSAIC_WALL = registerBlock("gabbro_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_GABBRO = registerBlock("chiseled_gabbro",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //mudstone
    public static final RegistryObject<Block> MUDSTONE = registerBlock("mudstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_SLAB = registerBlock("mudstone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_STAIRS = registerBlock("mudstone_stairs",
            ()-> new StairBlock(() -> MMBlocks.MUDSTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_WALL = registerBlock("mudstone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> MUDSTONE_FOSSIL_BLOCK = registerBlock("mudstone_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "mudstone"));


    public static final RegistryObject<Block> POLISHED_MUDSTONE = registerBlock("polished_mudstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_MUDSTONE_SLAB = registerBlock("polished_mudstone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_MUDSTONE_STAIRS = registerBlock("polished_mudstone_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_MUDSTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_MUDSTONE_WALL = registerBlock("polished_mudstone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> MUDSTONE_BRICKS = registerBlock("mudstone_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_BRICKS_SLAB = registerBlock("mudstone_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_BRICKS_STAIRS = registerBlock("mudstone_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.MUDSTONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_BRICKS_WALL = registerBlock("mudstone_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> MUDSTONE_MOSAIC = registerBlock("mudstone_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_MOSAIC_SLAB = registerBlock("mudstone_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_MOSAIC_STAIRS = registerBlock("mudstone_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.MUDSTONE_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> MUDSTONE_MOSAIC_WALL = registerBlock("mudstone_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_MUDSTONE = registerBlock("chiseled_mudstone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //limestone
    public static final RegistryObject<Block> LIMESTONE = registerBlock("limestone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_SLAB = registerBlock("limestone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            ()-> new StairBlock(() -> MMBlocks.LIMESTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_WALL = registerBlock("limestone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> LIMESTONE_FOSSIL_BLOCK = registerBlock("limestone_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "limestone"));


    public static final RegistryObject<Block> POLISHED_LIMESTONE = registerBlock("polished_limestone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_LIMESTONE_SLAB = registerBlock("polished_limestone_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_LIMESTONE_STAIRS = registerBlock("polished_limestone_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_LIMESTONE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_LIMESTONE_WALL = registerBlock("polished_limestone_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_BRICKS_SLAB = registerBlock("limestone_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_BRICKS_STAIRS = registerBlock("limestone_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.LIMESTONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_BRICKS_WALL = registerBlock("limestone_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> LIMESTONE_MOSAIC = registerBlock("limestone_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_MOSAIC_SLAB = registerBlock("limestone_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_MOSAIC_STAIRS = registerBlock("limestone_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.LIMESTONE_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> LIMESTONE_MOSAIC_WALL = registerBlock("limestone_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_LIMESTONE = registerBlock("chiseled_limestone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //chalk
    public static final RegistryObject<Block> CHALK = registerBlock("chalk",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_SLAB = registerBlock("chalk_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_STAIRS = registerBlock("chalk_stairs",
            ()-> new StairBlock(() -> MMBlocks.CHALK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_WALL = registerBlock("chalk_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHALK_FOSSIL_BLOCK = registerBlock("chalk_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "chalk"));


    public static final RegistryObject<Block> POLISHED_CHALK = registerBlock("polished_chalk",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_CHALK_SLAB = registerBlock("polished_chalk_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_CHALK_STAIRS = registerBlock("polished_chalk_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_CHALK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_CHALK_WALL = registerBlock("polished_chalk_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHALK_BRICKS = registerBlock("chalk_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_BRICKS_SLAB = registerBlock("chalk_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_BRICKS_STAIRS = registerBlock("chalk_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.CHALK_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_BRICKS_WALL = registerBlock("chalk_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHALK_MOSAIC = registerBlock("chalk_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_MOSAIC_SLAB = registerBlock("chalk_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_MOSAIC_STAIRS = registerBlock("chalk_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.CHALK_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHALK_MOSAIC_WALL = registerBlock("chalk_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_CHALK = registerBlock("chiseled_chalk",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //breccia
    public static final RegistryObject<Block> BRECCIA = registerBlock("breccia",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_SLAB = registerBlock("breccia_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_STAIRS = registerBlock("breccia_stairs",
            ()-> new StairBlock(() -> MMBlocks.BRECCIA.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_WALL = registerBlock("breccia_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> BRECCIA_FOSSIL_BLOCK = registerBlock("breccia_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "breccia"));


    public static final RegistryObject<Block> POLISHED_BRECCIA = registerBlock("polished_breccia",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_BRECCIA_SLAB = registerBlock("polished_breccia_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_BRECCIA_STAIRS = registerBlock("polished_breccia_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_BRECCIA.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_BRECCIA_WALL = registerBlock("polished_breccia_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> BRECCIA_BRICKS = registerBlock("breccia_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_BRICKS_SLAB = registerBlock("breccia_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_BRICKS_STAIRS = registerBlock("breccia_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.BRECCIA_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_BRICKS_WALL = registerBlock("breccia_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> BRECCIA_MOSAIC = registerBlock("breccia_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_MOSAIC_SLAB = registerBlock("breccia_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_MOSAIC_STAIRS = registerBlock("breccia_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.BRECCIA_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BRECCIA_MOSAIC_WALL = registerBlock("breccia_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_BRECCIA = registerBlock("chiseled_breccia",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //diatomite
    public static final RegistryObject<Block> DIATOMITE = registerBlock("diatomite",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_SLAB = registerBlock("diatomite_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_STAIRS = registerBlock("diatomite_stairs",
            ()-> new StairBlock(() -> MMBlocks.DIATOMITE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_WALL = registerBlock("diatomite_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> DIATOMITE_FOSSIL_BLOCK = registerBlock("diatomite_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE), "diatomite"));


    public static final RegistryObject<Block> POLISHED_DIATOMITE = registerBlock("polished_diatomite",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_DIATOMITE_SLAB = registerBlock("polished_diatomite_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_DIATOMITE_STAIRS = registerBlock("polished_diatomite_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_DIATOMITE.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> POLISHED_DIATOMITE_WALL = registerBlock("polished_diatomite_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> DIATOMITE_BRICKS = registerBlock("diatomite_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_BRICKS_SLAB = registerBlock("diatomite_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_BRICKS_STAIRS = registerBlock("diatomite_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.DIATOMITE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_BRICKS_WALL = registerBlock("diatomite_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> DIATOMITE_MOSAIC = registerBlock("diatomite_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_MOSAIC_SLAB = registerBlock("diatomite_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_MOSAIC_STAIRS = registerBlock("diatomite_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.DIATOMITE_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> DIATOMITE_MOSAIC_WALL = registerBlock("diatomite_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> CHISELED_DIATOMITE = registerBlock("chiseled_diatomite",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));





    //permafrost
    public static final RegistryObject<Block> PERMAFROST = registerBlock("permafrost",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_SLAB = registerBlock("permafrost_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_STAIRS = registerBlock("permafrost_stairs",
            ()-> new StairBlock(() -> MMBlocks.PERMAFROST.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_WALL = registerBlock("permafrost_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));


    public static final RegistryObject<Block> PERMAFROST_FOSSIL_BLOCK = registerBlock("permafrost_fossil_block",
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS), "permafrost"));


    public static final RegistryObject<Block> POLISHED_PERMAFROST = registerBlock("polished_permafrost",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> POLISHED_PERMAFROST_SLAB = registerBlock("polished_permafrost_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> POLISHED_PERMAFROST_STAIRS = registerBlock("polished_permafrost_stairs",
            ()-> new StairBlock(() -> MMBlocks.POLISHED_PERMAFROST.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> POLISHED_PERMAFROST_WALL = registerBlock("polished_permafrost_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));


    public static final RegistryObject<Block> PERMAFROST_BRICKS = registerBlock("permafrost_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_BRICKS_SLAB = registerBlock("permafrost_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_BRICKS_STAIRS = registerBlock("permafrost_bricks_stairs",
            ()-> new StairBlock(() -> MMBlocks.PERMAFROST_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_BRICKS_WALL = registerBlock("permafrost_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));


    public static final RegistryObject<Block> PERMAFROST_MOSAIC = registerBlock("permafrost_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_MOSAIC_SLAB = registerBlock("permafrost_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_MOSAIC_STAIRS = registerBlock("permafrost_mosaic_stairs",
            ()-> new StairBlock(() -> MMBlocks.PERMAFROST_MOSAIC.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> PERMAFROST_MOSAIC_WALL = registerBlock("permafrost_mosaic_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));


    public static final RegistryObject<Block> CHISELED_PERMAFROST = registerBlock("chiseled_permafrost",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));

    public static final RegistryObject<Block> CHRONOTITE = registerBlock("chronotite_block",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.COPPER)));

    public static final RegistryObject<Block> CHRONO_PEDESTAL = registerBlock("chrono_pedestal",
            ()-> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.COPPER)));

    public static final RegistryObject<Block> CHRONO_ALTAR = registerBlock("chrono_altar",
            ()-> new AltarBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).sound(SoundType.COPPER).lightLevel((p_187437_) -> {
                return 7;
            })));

    public static final RegistryObject<Block> PALEO_TABLE = registerBlock("paleo_table",
            ()-> new PaleontologyTableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED));


    private static <T extends Block> Supplier<T> registerBlockWithItem(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        MMItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block){
        return MMItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    public static void registerPaintings(IEventBus eventBus){
        PAINTINGS.register(eventBus);
    }
}
