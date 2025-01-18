package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMBlockTagGenerator extends BlockTagsProvider {


    public MMBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MarvelousMenagerie.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.WALLS).add(

                MMBlocks.SHALE_WALL.get(),
                MMBlocks.POLISHED_SHALE_WALL.get(),
                MMBlocks.SHALE_MOSAIC_WALL.get(),
                MMBlocks.SHALE_BRICKS_WALL.get(),

                MMBlocks.SILTSTONE_WALL.get(),
                MMBlocks.POLISHED_SILTSTONE_WALL.get(),
                MMBlocks.SILTSTONE_MOSAIC_WALL.get(),
                MMBlocks.SILTSTONE_BRICKS_WALL.get(),
                
                MMBlocks.GABBRO_WALL.get(),
                MMBlocks.POLISHED_GABBRO_WALL.get(),
                MMBlocks.GABBRO_MOSAIC_WALL.get(),
                MMBlocks.GABBRO_BRICKS_WALL.get(),
                
                MMBlocks.MUDSTONE_WALL.get(),
                MMBlocks.POLISHED_MUDSTONE_WALL.get(),
                MMBlocks.MUDSTONE_MOSAIC_WALL.get(),
                MMBlocks.MUDSTONE_BRICKS_WALL.get(),

                MMBlocks.LIMESTONE_WALL.get(),
                MMBlocks.POLISHED_LIMESTONE_WALL.get(),
                MMBlocks.LIMESTONE_MOSAIC_WALL.get(),
                MMBlocks.LIMESTONE_BRICKS_WALL.get(),

                MMBlocks.CHALK_WALL.get(),
                MMBlocks.POLISHED_CHALK_WALL.get(),
                MMBlocks.CHALK_MOSAIC_WALL.get(),
                MMBlocks.CHALK_BRICKS_WALL.get(),

                MMBlocks.BRECCIA_WALL.get(),
                MMBlocks.POLISHED_BRECCIA_WALL.get(),
                MMBlocks.BRECCIA_MOSAIC_WALL.get(),
                MMBlocks.BRECCIA_BRICKS_WALL.get(),

                MMBlocks.DIATOMITE_WALL.get(),
                MMBlocks.POLISHED_DIATOMITE_WALL.get(),
                MMBlocks.DIATOMITE_MOSAIC_WALL.get(),
                MMBlocks.DIATOMITE_BRICKS_WALL.get(),

                MMBlocks.PERMAFROST_WALL.get(),
                MMBlocks.POLISHED_PERMAFROST_WALL.get(),
                MMBlocks.PERMAFROST_MOSAIC_WALL.get(),
                MMBlocks.PERMAFROST_BRICKS_WALL.get()
        );

        this.tag(MMTags.Blocks.FOSSIL_BLOCKS).add(
                MMBlocks.SHALE_FOSSIL_BLOCK.get(),
                MMBlocks.GABBRO_FOSSIL_BLOCK.get(),
                MMBlocks.SILTSTONE_FOSSIL_BLOCK.get(),
                MMBlocks.MUDSTONE_FOSSIL_BLOCK.get(),
                MMBlocks.LIMESTONE_FOSSIL_BLOCK.get(),
                MMBlocks.CHALK_FOSSIL_BLOCK.get(),
                MMBlocks.DIATOMITE_FOSSIL_BLOCK.get(),
                MMBlocks.BRECCIA_FOSSIL_BLOCK.get(),
                MMBlocks.PERMAFROST_FOSSIL_BLOCK.get()
        );
    }
}
