package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

public class MMBlockStateProvider extends BlockStateProvider {

    public MMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MarvelousMenagerie.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(MMBlocks.SHALE);
        stairsBlock(((StairBlock) MMBlocks.SHALE_STAIRS.get()), blockTexture(MMBlocks.SHALE.get()));
        slabBlock(((SlabBlock) MMBlocks.SHALE_SLAB.get()), blockTexture(MMBlocks.SHALE.get()), blockTexture(MMBlocks.SHALE.get()));
        wallBlock((WallBlock) MMBlocks.SHALE_WALL.get(), blockTexture(MMBlocks.SHALE.get()));
        blockWithItem(MMBlocks.SHALE_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.SILTSTONE_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.GABBRO_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.MUDSTONE_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.LIMESTONE_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.CHALK_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.BRECCIA_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.DIATOMITE_FOSSIL_BLOCK);
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
        blockWithItem(MMBlocks.PERMAFROST_FOSSIL_BLOCK);
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

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject
                .get()));
    }
}
