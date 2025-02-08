package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

import static net.minecraft.data.models.model.TextureMapping.cubeBottomTop;

public class MMBlockStateProvider extends BlockStateProvider {

    public MMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MarvelousMenagerie.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //sigillaria woodset
        blockWithItem(MMBlocks.SIGILLARIA_PLANKS);
        stairsBlock(((StairBlock) MMBlocks.SIGILLARIA_STAIRS.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        slabBlock(((SlabBlock) MMBlocks.SIGILLARIA_SLAB.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        buttonBlock(((ButtonBlock) MMBlocks.SIGILLARIA_BUTTON.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) MMBlocks.SIGILLARIA_PRESSURE_PLATE.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        fenceBlock(((FenceBlock) MMBlocks.SIGILLARIA_FENCE.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) MMBlocks.SIGILLARIA_FENCE_GATE.get()), blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) MMBlocks.SIGILLARIA_DOOR.get()), modLoc("block/sigillaria_door_bottom"), modLoc("block/sigillaria_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) MMBlocks.SIGILLARIA_TRAPDOOR.get()), modLoc("block/sigillaria_trapdoor"), true, "cutout");

        blockWithItem(MMBlocks.SIGILLARIA_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.SIGILLARIA_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.SIGILLARIA_MOSAIC_SLAB.get()), blockTexture(MMBlocks.SIGILLARIA_MOSAIC.get()), blockTexture(MMBlocks.SIGILLARIA_MOSAIC.get()));

        signBlock(((StandingSignBlock) MMBlocks.SIGILLARIA_SIGN.get()), ((WallSignBlock) MMBlocks.SIGILLARIA_WALL_SIGN.get()),
                blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));
        hangingSignBlock(MMBlocks.SIGILLARIA_HANGING_SIGN.get(), MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                blockTexture(MMBlocks.SIGILLARIA_PLANKS.get()));

        logBlock(((RotatedPillarBlock) MMBlocks.SIGILLARIA_STEM.get()));
        axisBlock(((RotatedPillarBlock) MMBlocks.STRIPPED_SIGILLARIA_STEM.get()), blockTexture(MMBlocks.STRIPPED_SIGILLARIA_STEM.get()), new ResourceLocation(MarvelousMenagerie.MODID, "block/stripped_sigillaria_stem_top"));
        blockItem(MMBlocks.SIGILLARIA_STEM);
        blockItem(MMBlocks.STRIPPED_SIGILLARIA_STEM);

        axisBlock(((RotatedPillarBlock) MMBlocks.SIGILLARIA_WOOD.get()), blockTexture(MMBlocks.SIGILLARIA_STEM.get()), blockTexture(MMBlocks.SIGILLARIA_STEM.get()));
        axisBlock(((RotatedPillarBlock) MMBlocks.STRIPPED_SIGILLARIA_WOOD.get()), blockTexture(MMBlocks.STRIPPED_SIGILLARIA_STEM.get()), blockTexture(MMBlocks.STRIPPED_SIGILLARIA_STEM.get()));
        blockItem(MMBlocks.SIGILLARIA_WOOD);
        blockItem(MMBlocks.STRIPPED_SIGILLARIA_WOOD);

        leavesBlock(MMBlocks.SIGILLARIA_LEAVES);

        simpleBlockWithItem(MMBlocks.SIGILLARIA_SAPLING.get(), models().cross(blockTexture(MMBlocks.SIGILLARIA_SAPLING.get()).getPath(),
                blockTexture(MMBlocks.SIGILLARIA_SAPLING.get())).renderType("cutout"));
        simpleBlockWithItem(MMBlocks.POTTED_SIGILLARIA_SAPLING.get(), models().singleTexture("potted_sigillaria_sapling", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(MMBlocks.SIGILLARIA_SAPLING.get())).renderType("cutout"));

        simpleBlockWithItem(MMBlocks.COOKSONIA.get(), models().cross(blockTexture(MMBlocks.COOKSONIA.get()).getPath(),
                blockTexture(MMBlocks.COOKSONIA.get())).renderType("cutout"));

        //prototaxites woodset
        blockWithItem(MMBlocks.PROTOTAXITES_PLANKS);
        stairsBlock(((StairBlock) MMBlocks.PROTOTAXITES_STAIRS.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        slabBlock(((SlabBlock) MMBlocks.PROTOTAXITES_SLAB.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        buttonBlock(((ButtonBlock) MMBlocks.PROTOTAXITES_BUTTON.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        fenceBlock(((FenceBlock) MMBlocks.PROTOTAXITES_FENCE.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) MMBlocks.PROTOTAXITES_FENCE_GATE.get()), blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) MMBlocks.PROTOTAXITES_DOOR.get()), modLoc("block/prototaxites_door_bottom"), modLoc("block/prototaxites_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) MMBlocks.PROTOTAXITES_TRAPDOOR.get()), modLoc("block/prototaxites_trapdoor"), true, "cutout");

        blockWithItem(MMBlocks.PROTOTAXITES_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.PROTOTAXITES_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get()), blockTexture(MMBlocks.PROTOTAXITES_MOSAIC.get()), blockTexture(MMBlocks.PROTOTAXITES_MOSAIC.get()));

        signBlock(((StandingSignBlock) MMBlocks.PROTOTAXITES_SIGN.get()), ((WallSignBlock) MMBlocks.PROTOTAXITES_WALL_SIGN.get()),
                blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));
        hangingSignBlock(MMBlocks.PROTOTAXITES_HANGING_SIGN.get(), MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),
                blockTexture(MMBlocks.PROTOTAXITES_PLANKS.get()));


        //Chronotite
        axisBlock((RotatedPillarBlock) MMBlocks.CHRONOTITE.get(),
                new ResourceLocation(MarvelousMenagerie.MODID, "block/chronotite_block_side"),
                new ResourceLocation(MarvelousMenagerie.MODID, "block/chronotite_block_end"));

        blockWithItem(MMBlocks.SHALE);
        stairsBlock(((StairBlock) MMBlocks.SHALE_STAIRS.get()), blockTexture(MMBlocks.SHALE.get()));
        slabBlock(((SlabBlock) MMBlocks.SHALE_SLAB.get()), blockTexture(MMBlocks.SHALE.get()), blockTexture(MMBlocks.SHALE.get()));
        wallBlock((WallBlock) MMBlocks.SHALE_WALL.get(), blockTexture(MMBlocks.SHALE.get()));
        //blockWithItem(MMBlocks.SHALE_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_SHALE);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_SHALE_STAIRS.get()), blockTexture(MMBlocks.POLISHED_SHALE.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_SHALE_SLAB.get()), blockTexture(MMBlocks.POLISHED_SHALE.get()), blockTexture(MMBlocks.POLISHED_SHALE.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_SHALE_WALL.get(), blockTexture(MMBlocks.POLISHED_SHALE.get()));
        blockWithItem(MMBlocks.SHALE_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.SHALE_BRICKS_STAIRS.get()), blockTexture(MMBlocks.SHALE_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.SHALE_BRICKS_SLAB.get()), blockTexture(MMBlocks.SHALE_BRICKS.get()), blockTexture(MMBlocks.SHALE_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.SHALE_BRICKS_WALL.get(), blockTexture(MMBlocks.SHALE_BRICKS.get()));
        blockWithItem(MMBlocks.SHALE_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.SHALE_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.SHALE_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.SHALE_MOSAIC_SLAB.get()), blockTexture(MMBlocks.SHALE_MOSAIC.get()), blockTexture(MMBlocks.SHALE_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.SHALE_MOSAIC_WALL.get(), blockTexture(MMBlocks.SHALE_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_SHALE);

        blockWithItem(MMBlocks.SILTSTONE);
        stairsBlock(((StairBlock) MMBlocks.SILTSTONE_STAIRS.get()), blockTexture(MMBlocks.SILTSTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.SILTSTONE_SLAB.get()), blockTexture(MMBlocks.SILTSTONE.get()), blockTexture(MMBlocks.SILTSTONE.get()));
        wallBlock((WallBlock) MMBlocks.SILTSTONE_WALL.get(), blockTexture(MMBlocks.SILTSTONE.get()));
        //blockWithItem(MMBlocks.SILTSTONE_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_SILTSTONE);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_SILTSTONE_STAIRS.get()), blockTexture(MMBlocks.POLISHED_SILTSTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_SILTSTONE_SLAB.get()), blockTexture(MMBlocks.POLISHED_SILTSTONE.get()), blockTexture(MMBlocks.POLISHED_SILTSTONE.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_SILTSTONE_WALL.get(), blockTexture(MMBlocks.POLISHED_SILTSTONE.get()));
        blockWithItem(MMBlocks.SILTSTONE_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.SILTSTONE_BRICKS_STAIRS.get()), blockTexture(MMBlocks.SILTSTONE_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.SILTSTONE_BRICKS_SLAB.get()), blockTexture(MMBlocks.SILTSTONE_BRICKS.get()), blockTexture(MMBlocks.SILTSTONE_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.SILTSTONE_BRICKS_WALL.get(), blockTexture(MMBlocks.SILTSTONE_BRICKS.get()));
        blockWithItem(MMBlocks.SILTSTONE_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.SILTSTONE_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.SILTSTONE_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.SILTSTONE_MOSAIC_SLAB.get()), blockTexture(MMBlocks.SILTSTONE_MOSAIC.get()), blockTexture(MMBlocks.SILTSTONE_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.SILTSTONE_MOSAIC_WALL.get(), blockTexture(MMBlocks.SILTSTONE_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_SILTSTONE);

        blockWithItem(MMBlocks.GABBRO);
        stairsBlock(((StairBlock) MMBlocks.GABBRO_STAIRS.get()), blockTexture(MMBlocks.GABBRO.get()));
        slabBlock(((SlabBlock) MMBlocks.GABBRO_SLAB.get()), blockTexture(MMBlocks.GABBRO.get()), blockTexture(MMBlocks.GABBRO.get()));
        wallBlock((WallBlock) MMBlocks.GABBRO_WALL.get(), blockTexture(MMBlocks.GABBRO.get()));
        //blockWithItem(MMBlocks.GABBRO_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_GABBRO);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_GABBRO_STAIRS.get()), blockTexture(MMBlocks.POLISHED_GABBRO.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_GABBRO_SLAB.get()), blockTexture(MMBlocks.POLISHED_GABBRO.get()), blockTexture(MMBlocks.POLISHED_GABBRO.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_GABBRO_WALL.get(), blockTexture(MMBlocks.POLISHED_GABBRO.get()));
        blockWithItem(MMBlocks.GABBRO_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.GABBRO_BRICKS_STAIRS.get()), blockTexture(MMBlocks.GABBRO_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.GABBRO_BRICKS_SLAB.get()), blockTexture(MMBlocks.GABBRO_BRICKS.get()), blockTexture(MMBlocks.GABBRO_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.GABBRO_BRICKS_WALL.get(), blockTexture(MMBlocks.GABBRO_BRICKS.get()));
        blockWithItem(MMBlocks.GABBRO_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.GABBRO_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.GABBRO_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.GABBRO_MOSAIC_SLAB.get()), blockTexture(MMBlocks.GABBRO_MOSAIC.get()), blockTexture(MMBlocks.GABBRO_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.GABBRO_MOSAIC_WALL.get(), blockTexture(MMBlocks.GABBRO_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_GABBRO);

        blockWithItem(MMBlocks.MUDSTONE);
        stairsBlock(((StairBlock) MMBlocks.MUDSTONE_STAIRS.get()), blockTexture(MMBlocks.MUDSTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.MUDSTONE_SLAB.get()), blockTexture(MMBlocks.MUDSTONE.get()), blockTexture(MMBlocks.MUDSTONE.get()));
        wallBlock((WallBlock) MMBlocks.MUDSTONE_WALL.get(), blockTexture(MMBlocks.MUDSTONE.get()));
        //blockWithItem(MMBlocks.MUDSTONE_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_MUDSTONE);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_MUDSTONE_STAIRS.get()), blockTexture(MMBlocks.POLISHED_MUDSTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_MUDSTONE_SLAB.get()), blockTexture(MMBlocks.POLISHED_MUDSTONE.get()), blockTexture(MMBlocks.POLISHED_MUDSTONE.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_MUDSTONE_WALL.get(), blockTexture(MMBlocks.POLISHED_MUDSTONE.get()));
        blockWithItem(MMBlocks.MUDSTONE_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.MUDSTONE_BRICKS_STAIRS.get()), blockTexture(MMBlocks.MUDSTONE_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.MUDSTONE_BRICKS_SLAB.get()), blockTexture(MMBlocks.MUDSTONE_BRICKS.get()), blockTexture(MMBlocks.MUDSTONE_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.MUDSTONE_BRICKS_WALL.get(), blockTexture(MMBlocks.MUDSTONE_BRICKS.get()));
        blockWithItem(MMBlocks.MUDSTONE_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.MUDSTONE_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.MUDSTONE_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.MUDSTONE_MOSAIC_SLAB.get()), blockTexture(MMBlocks.MUDSTONE_MOSAIC.get()), blockTexture(MMBlocks.MUDSTONE_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.MUDSTONE_MOSAIC_WALL.get(), blockTexture(MMBlocks.MUDSTONE_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_MUDSTONE);

        blockWithItem(MMBlocks.LIMESTONE);
        stairsBlock(((StairBlock) MMBlocks.LIMESTONE_STAIRS.get()), blockTexture(MMBlocks.LIMESTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.LIMESTONE_SLAB.get()), blockTexture(MMBlocks.LIMESTONE.get()), blockTexture(MMBlocks.LIMESTONE.get()));
        wallBlock((WallBlock) MMBlocks.LIMESTONE_WALL.get(), blockTexture(MMBlocks.LIMESTONE.get()));
        //blockWithItem(MMBlocks.LIMESTONE_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_LIMESTONE);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_LIMESTONE_STAIRS.get()), blockTexture(MMBlocks.POLISHED_LIMESTONE.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_LIMESTONE_SLAB.get()), blockTexture(MMBlocks.POLISHED_LIMESTONE.get()), blockTexture(MMBlocks.POLISHED_LIMESTONE.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_LIMESTONE_WALL.get(), blockTexture(MMBlocks.POLISHED_LIMESTONE.get()));
        blockWithItem(MMBlocks.LIMESTONE_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.LIMESTONE_BRICKS_STAIRS.get()), blockTexture(MMBlocks.LIMESTONE_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.LIMESTONE_BRICKS_SLAB.get()), blockTexture(MMBlocks.LIMESTONE_BRICKS.get()), blockTexture(MMBlocks.LIMESTONE_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.LIMESTONE_BRICKS_WALL.get(), blockTexture(MMBlocks.LIMESTONE_BRICKS.get()));
        blockWithItem(MMBlocks.LIMESTONE_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.LIMESTONE_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.LIMESTONE_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.LIMESTONE_MOSAIC_SLAB.get()), blockTexture(MMBlocks.LIMESTONE_MOSAIC.get()), blockTexture(MMBlocks.LIMESTONE_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.LIMESTONE_MOSAIC_WALL.get(), blockTexture(MMBlocks.LIMESTONE_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_LIMESTONE);

        blockWithItem(MMBlocks.CHALK);
        stairsBlock(((StairBlock) MMBlocks.CHALK_STAIRS.get()), blockTexture(MMBlocks.CHALK.get()));
        slabBlock(((SlabBlock) MMBlocks.CHALK_SLAB.get()), blockTexture(MMBlocks.CHALK.get()), blockTexture(MMBlocks.CHALK.get()));
        wallBlock((WallBlock) MMBlocks.CHALK_WALL.get(), blockTexture(MMBlocks.CHALK.get()));
        //blockWithItem(MMBlocks.CHALK_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_CHALK);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_CHALK_STAIRS.get()), blockTexture(MMBlocks.POLISHED_CHALK.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_CHALK_SLAB.get()), blockTexture(MMBlocks.POLISHED_CHALK.get()), blockTexture(MMBlocks.POLISHED_CHALK.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_CHALK_WALL.get(), blockTexture(MMBlocks.POLISHED_CHALK.get()));
        blockWithItem(MMBlocks.CHALK_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.CHALK_BRICKS_STAIRS.get()), blockTexture(MMBlocks.CHALK_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.CHALK_BRICKS_SLAB.get()), blockTexture(MMBlocks.CHALK_BRICKS.get()), blockTexture(MMBlocks.CHALK_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.CHALK_BRICKS_WALL.get(), blockTexture(MMBlocks.CHALK_BRICKS.get()));
        blockWithItem(MMBlocks.CHALK_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.CHALK_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.CHALK_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.CHALK_MOSAIC_SLAB.get()), blockTexture(MMBlocks.CHALK_MOSAIC.get()), blockTexture(MMBlocks.CHALK_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.CHALK_MOSAIC_WALL.get(), blockTexture(MMBlocks.CHALK_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_CHALK);

        blockWithItem(MMBlocks.BRECCIA);
        stairsBlock(((StairBlock) MMBlocks.BRECCIA_STAIRS.get()), blockTexture(MMBlocks.BRECCIA.get()));
        slabBlock(((SlabBlock) MMBlocks.BRECCIA_SLAB.get()), blockTexture(MMBlocks.BRECCIA.get()), blockTexture(MMBlocks.BRECCIA.get()));
        wallBlock((WallBlock) MMBlocks.BRECCIA_WALL.get(), blockTexture(MMBlocks.BRECCIA.get()));
        //blockWithItem(MMBlocks.BRECCIA_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_BRECCIA);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_BRECCIA_STAIRS.get()), blockTexture(MMBlocks.POLISHED_BRECCIA.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_BRECCIA_SLAB.get()), blockTexture(MMBlocks.POLISHED_BRECCIA.get()), blockTexture(MMBlocks.POLISHED_BRECCIA.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_BRECCIA_WALL.get(), blockTexture(MMBlocks.POLISHED_BRECCIA.get()));
        blockWithItem(MMBlocks.BRECCIA_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.BRECCIA_BRICKS_STAIRS.get()), blockTexture(MMBlocks.BRECCIA_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.BRECCIA_BRICKS_SLAB.get()), blockTexture(MMBlocks.BRECCIA_BRICKS.get()), blockTexture(MMBlocks.BRECCIA_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.BRECCIA_BRICKS_WALL.get(), blockTexture(MMBlocks.BRECCIA_BRICKS.get()));
        blockWithItem(MMBlocks.BRECCIA_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.BRECCIA_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.BRECCIA_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.BRECCIA_MOSAIC_SLAB.get()), blockTexture(MMBlocks.BRECCIA_MOSAIC.get()), blockTexture(MMBlocks.BRECCIA_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.BRECCIA_MOSAIC_WALL.get(), blockTexture(MMBlocks.BRECCIA_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_BRECCIA);

        blockWithItem(MMBlocks.DIATOMITE);
        stairsBlock(((StairBlock) MMBlocks.DIATOMITE_STAIRS.get()), blockTexture(MMBlocks.DIATOMITE.get()));
        slabBlock(((SlabBlock) MMBlocks.DIATOMITE_SLAB.get()), blockTexture(MMBlocks.DIATOMITE.get()), blockTexture(MMBlocks.DIATOMITE.get()));
        wallBlock((WallBlock) MMBlocks.DIATOMITE_WALL.get(), blockTexture(MMBlocks.DIATOMITE.get()));
        //blockWithItem(MMBlocks.DIATOMITE_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_DIATOMITE);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_DIATOMITE_STAIRS.get()), blockTexture(MMBlocks.POLISHED_DIATOMITE.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_DIATOMITE_SLAB.get()), blockTexture(MMBlocks.POLISHED_DIATOMITE.get()), blockTexture(MMBlocks.POLISHED_DIATOMITE.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_DIATOMITE_WALL.get(), blockTexture(MMBlocks.POLISHED_DIATOMITE.get()));
        blockWithItem(MMBlocks.DIATOMITE_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.DIATOMITE_BRICKS_STAIRS.get()), blockTexture(MMBlocks.DIATOMITE_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.DIATOMITE_BRICKS_SLAB.get()), blockTexture(MMBlocks.DIATOMITE_BRICKS.get()), blockTexture(MMBlocks.DIATOMITE_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.DIATOMITE_BRICKS_WALL.get(), blockTexture(MMBlocks.DIATOMITE_BRICKS.get()));
        blockWithItem(MMBlocks.DIATOMITE_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.DIATOMITE_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.DIATOMITE_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.DIATOMITE_MOSAIC_SLAB.get()), blockTexture(MMBlocks.DIATOMITE_MOSAIC.get()), blockTexture(MMBlocks.DIATOMITE_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.DIATOMITE_MOSAIC_WALL.get(), blockTexture(MMBlocks.DIATOMITE_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_DIATOMITE);

        blockWithItem(MMBlocks.PERMAFROST);
        stairsBlock(((StairBlock) MMBlocks.PERMAFROST_STAIRS.get()), blockTexture(MMBlocks.PERMAFROST.get()));
        slabBlock(((SlabBlock) MMBlocks.PERMAFROST_SLAB.get()), blockTexture(MMBlocks.PERMAFROST.get()), blockTexture(MMBlocks.PERMAFROST.get()));
        wallBlock((WallBlock) MMBlocks.PERMAFROST_WALL.get(), blockTexture(MMBlocks.PERMAFROST.get()));
        //blockWithItem(MMBlocks.PERMAFROST_FOSSIL_BLOCK);
        blockWithItem(MMBlocks.POLISHED_PERMAFROST);
        stairsBlock(((StairBlock) MMBlocks.POLISHED_PERMAFROST_STAIRS.get()), blockTexture(MMBlocks.POLISHED_PERMAFROST.get()));
        slabBlock(((SlabBlock) MMBlocks.POLISHED_PERMAFROST_SLAB.get()), blockTexture(MMBlocks.POLISHED_PERMAFROST.get()), blockTexture(MMBlocks.POLISHED_PERMAFROST.get()));
        wallBlock((WallBlock) MMBlocks.POLISHED_PERMAFROST_WALL.get(), blockTexture(MMBlocks.POLISHED_PERMAFROST.get()));
        blockWithItem(MMBlocks.PERMAFROST_BRICKS);
        stairsBlock(((StairBlock) MMBlocks.PERMAFROST_BRICKS_STAIRS.get()), blockTexture(MMBlocks.PERMAFROST_BRICKS.get()));
        slabBlock(((SlabBlock) MMBlocks.PERMAFROST_BRICKS_SLAB.get()), blockTexture(MMBlocks.PERMAFROST_BRICKS.get()), blockTexture(MMBlocks.PERMAFROST_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.PERMAFROST_BRICKS_WALL.get(), blockTexture(MMBlocks.PERMAFROST_BRICKS.get()));
        blockWithItem(MMBlocks.PERMAFROST_MOSAIC);
        stairsBlock(((StairBlock) MMBlocks.PERMAFROST_MOSAIC_STAIRS.get()), blockTexture(MMBlocks.PERMAFROST_MOSAIC.get()));
        slabBlock(((SlabBlock) MMBlocks.PERMAFROST_MOSAIC_SLAB.get()), blockTexture(MMBlocks.PERMAFROST_MOSAIC.get()), blockTexture(MMBlocks.PERMAFROST_MOSAIC.get()));
        wallBlock((WallBlock) MMBlocks.PERMAFROST_MOSAIC_WALL.get(), blockTexture(MMBlocks.PERMAFROST_MOSAIC.get()));
        blockWithItem(MMBlocks.CHISELED_PERMAFROST);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(MarvelousMenagerie.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),
                cubeAll(blockRegistryObject.get()));
    }

    private void nonRotateablePillarBlock(RegistryObject<Block> blockRegistryObject, String top, String bottom, String side){
        ResourceLocation rSide = new ResourceLocation(MarvelousMenagerie.MODID, "block/" + side);
        ResourceLocation rTop = new ResourceLocation(MarvelousMenagerie.MODID, "block/" + top);
        ResourceLocation rBottom = new ResourceLocation(MarvelousMenagerie.MODID, "block/" + bottom);

        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeBottomTop(name(blockRegistryObject.get()), rSide, rBottom, rTop));
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
