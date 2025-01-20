package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

public class MMItemModelProvider extends ItemModelProvider {

    public MMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MarvelousMenagerie.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        evenSimplerBlockItem(MMBlocks.CHRONOTITE);

        simpleItem(MMItems.CHRONOTITE);

        simpleItem(MMItems.SNIFFER_FOSSIL);

        simpleItem(MMItems.SHALE_FOSSIL);
        simpleItem(MMItems.SILTSTONE_FOSSIL);
        simpleItem(MMItems.GABBRO_FOSSIL);
        simpleItem(MMItems.MUDSTONE_FOSSIL);
        simpleItem(MMItems.LIMESTONE_FOSSIL);
        simpleItem(MMItems.CHALK_FOSSIL);
        simpleItem(MMItems.BRECCIA_FOSSIL);
        simpleItem(MMItems.DIATOMITE_FOSSIL);
        simpleItem(MMItems.PERMAFROST_FOSSIL);

        evenSimplerBlockItem(MMBlocks.SHALE_SLAB);
        evenSimplerBlockItem(MMBlocks.SHALE_STAIRS);
        wallItem(MMBlocks.SHALE_WALL, MMBlocks.SHALE);
        evenSimplerBlockItem(MMBlocks.POLISHED_SHALE_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_SHALE_STAIRS);
        wallItem(MMBlocks.POLISHED_SHALE_WALL, MMBlocks.POLISHED_SHALE);
        evenSimplerBlockItem(MMBlocks.SHALE_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.SHALE_MOSAIC_STAIRS);
        wallItem(MMBlocks.SHALE_MOSAIC_WALL, MMBlocks.SHALE_MOSAIC);
        evenSimplerBlockItem(MMBlocks.SHALE_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.SHALE_BRICKS_STAIRS);
        wallItem(MMBlocks.SHALE_BRICKS_WALL, MMBlocks.SHALE_BRICKS);

        evenSimplerBlockItem(MMBlocks.SILTSTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.SILTSTONE_STAIRS);
        wallItem(MMBlocks.SILTSTONE_WALL, MMBlocks.SILTSTONE);
        evenSimplerBlockItem(MMBlocks.POLISHED_SILTSTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_SILTSTONE_STAIRS);
        wallItem(MMBlocks.POLISHED_SILTSTONE_WALL, MMBlocks.POLISHED_SILTSTONE);
        evenSimplerBlockItem(MMBlocks.SILTSTONE_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.SILTSTONE_MOSAIC_STAIRS);
        wallItem(MMBlocks.SILTSTONE_MOSAIC_WALL, MMBlocks.SILTSTONE_MOSAIC);
        evenSimplerBlockItem(MMBlocks.SILTSTONE_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.SILTSTONE_BRICKS_STAIRS);
        wallItem(MMBlocks.SILTSTONE_BRICKS_WALL, MMBlocks.SILTSTONE_BRICKS);

        evenSimplerBlockItem(MMBlocks.GABBRO_SLAB);
        evenSimplerBlockItem(MMBlocks.GABBRO_STAIRS);
        wallItem(MMBlocks.GABBRO_WALL, MMBlocks.GABBRO);
        evenSimplerBlockItem(MMBlocks.POLISHED_GABBRO_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_GABBRO_STAIRS);
        wallItem(MMBlocks.POLISHED_GABBRO_WALL, MMBlocks.POLISHED_GABBRO);
        evenSimplerBlockItem(MMBlocks.GABBRO_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.GABBRO_MOSAIC_STAIRS);
        wallItem(MMBlocks.GABBRO_MOSAIC_WALL, MMBlocks.GABBRO_MOSAIC);
        evenSimplerBlockItem(MMBlocks.GABBRO_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.GABBRO_BRICKS_STAIRS);
        wallItem(MMBlocks.GABBRO_BRICKS_WALL, MMBlocks.GABBRO_BRICKS);

        evenSimplerBlockItem(MMBlocks.MUDSTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.MUDSTONE_STAIRS);
        wallItem(MMBlocks.MUDSTONE_WALL, MMBlocks.MUDSTONE);
        evenSimplerBlockItem(MMBlocks.POLISHED_MUDSTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_MUDSTONE_STAIRS);
        wallItem(MMBlocks.POLISHED_MUDSTONE_WALL, MMBlocks.POLISHED_MUDSTONE);
        evenSimplerBlockItem(MMBlocks.MUDSTONE_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.MUDSTONE_MOSAIC_STAIRS);
        wallItem(MMBlocks.MUDSTONE_MOSAIC_WALL, MMBlocks.MUDSTONE_MOSAIC);
        evenSimplerBlockItem(MMBlocks.MUDSTONE_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.MUDSTONE_BRICKS_STAIRS);
        wallItem(MMBlocks.MUDSTONE_BRICKS_WALL, MMBlocks.MUDSTONE_BRICKS);

        evenSimplerBlockItem(MMBlocks.LIMESTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.LIMESTONE_STAIRS);
        wallItem(MMBlocks.LIMESTONE_WALL, MMBlocks.LIMESTONE);
        evenSimplerBlockItem(MMBlocks.POLISHED_LIMESTONE_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_LIMESTONE_STAIRS);
        wallItem(MMBlocks.POLISHED_LIMESTONE_WALL, MMBlocks.POLISHED_LIMESTONE);
        evenSimplerBlockItem(MMBlocks.LIMESTONE_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.LIMESTONE_MOSAIC_STAIRS);
        wallItem(MMBlocks.LIMESTONE_MOSAIC_WALL, MMBlocks.LIMESTONE_MOSAIC);
        evenSimplerBlockItem(MMBlocks.LIMESTONE_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.LIMESTONE_BRICKS_STAIRS);
        wallItem(MMBlocks.LIMESTONE_BRICKS_WALL, MMBlocks.LIMESTONE_BRICKS);

        evenSimplerBlockItem(MMBlocks.CHALK_SLAB);
        evenSimplerBlockItem(MMBlocks.CHALK_STAIRS);
        wallItem(MMBlocks.CHALK_WALL, MMBlocks.CHALK);
        evenSimplerBlockItem(MMBlocks.POLISHED_CHALK_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_CHALK_STAIRS);
        wallItem(MMBlocks.POLISHED_CHALK_WALL, MMBlocks.POLISHED_CHALK);
        evenSimplerBlockItem(MMBlocks.CHALK_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.CHALK_MOSAIC_STAIRS);
        wallItem(MMBlocks.CHALK_MOSAIC_WALL, MMBlocks.CHALK_MOSAIC);
        evenSimplerBlockItem(MMBlocks.CHALK_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.CHALK_BRICKS_STAIRS);
        wallItem(MMBlocks.CHALK_BRICKS_WALL, MMBlocks.CHALK_BRICKS);

        evenSimplerBlockItem(MMBlocks.BRECCIA_SLAB);
        evenSimplerBlockItem(MMBlocks.BRECCIA_STAIRS);
        wallItem(MMBlocks.BRECCIA_WALL, MMBlocks.BRECCIA);
        evenSimplerBlockItem(MMBlocks.POLISHED_BRECCIA_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_BRECCIA_STAIRS);
        wallItem(MMBlocks.POLISHED_BRECCIA_WALL, MMBlocks.POLISHED_BRECCIA);
        evenSimplerBlockItem(MMBlocks.BRECCIA_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.BRECCIA_MOSAIC_STAIRS);
        wallItem(MMBlocks.BRECCIA_MOSAIC_WALL, MMBlocks.BRECCIA_MOSAIC);
        evenSimplerBlockItem(MMBlocks.BRECCIA_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.BRECCIA_BRICKS_STAIRS);
        wallItem(MMBlocks.BRECCIA_BRICKS_WALL, MMBlocks.BRECCIA_BRICKS);

        evenSimplerBlockItem(MMBlocks.DIATOMITE_SLAB);
        evenSimplerBlockItem(MMBlocks.DIATOMITE_STAIRS);
        wallItem(MMBlocks.DIATOMITE_WALL, MMBlocks.DIATOMITE);
        evenSimplerBlockItem(MMBlocks.POLISHED_DIATOMITE_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_DIATOMITE_STAIRS);
        wallItem(MMBlocks.POLISHED_DIATOMITE_WALL, MMBlocks.POLISHED_DIATOMITE);
        evenSimplerBlockItem(MMBlocks.DIATOMITE_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.DIATOMITE_MOSAIC_STAIRS);
        wallItem(MMBlocks.DIATOMITE_MOSAIC_WALL, MMBlocks.DIATOMITE_MOSAIC);
        evenSimplerBlockItem(MMBlocks.DIATOMITE_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.DIATOMITE_BRICKS_STAIRS);
        wallItem(MMBlocks.DIATOMITE_BRICKS_WALL, MMBlocks.DIATOMITE_BRICKS);
        
        evenSimplerBlockItem(MMBlocks.PERMAFROST_SLAB);
        evenSimplerBlockItem(MMBlocks.PERMAFROST_STAIRS);
        wallItem(MMBlocks.PERMAFROST_WALL, MMBlocks.PERMAFROST);
        evenSimplerBlockItem(MMBlocks.POLISHED_PERMAFROST_SLAB);
        evenSimplerBlockItem(MMBlocks.POLISHED_PERMAFROST_STAIRS);
        wallItem(MMBlocks.POLISHED_PERMAFROST_WALL, MMBlocks.POLISHED_PERMAFROST);
        evenSimplerBlockItem(MMBlocks.PERMAFROST_MOSAIC_SLAB);
        evenSimplerBlockItem(MMBlocks.PERMAFROST_MOSAIC_STAIRS);
        wallItem(MMBlocks.PERMAFROST_MOSAIC_WALL, MMBlocks.PERMAFROST_MOSAIC);
        evenSimplerBlockItem(MMBlocks.PERMAFROST_BRICKS_SLAB);
        evenSimplerBlockItem(MMBlocks.PERMAFROST_BRICKS_STAIRS);
        wallItem(MMBlocks.PERMAFROST_BRICKS_WALL, MMBlocks.PERMAFROST_BRICKS);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MarvelousMenagerie.MODID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(MarvelousMenagerie.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(MarvelousMenagerie.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
}
