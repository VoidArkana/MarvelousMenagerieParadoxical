package net.voidarkana.marvelous_menagerie.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.function.Supplier;

public class MMBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MarvelousMenagerie.MODID);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.DEEPSLATE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));


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
            ()-> new FossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.MUD_BRICKS)));


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


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return MMItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
