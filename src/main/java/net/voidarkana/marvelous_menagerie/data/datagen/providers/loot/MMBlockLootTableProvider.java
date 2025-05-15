package net.voidarkana.marvelous_menagerie.data.datagen.providers.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.CharniaBlock;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.Set;
import java.util.stream.IntStream;

public class MMBlockLootTableProvider extends BlockLootSubProvider {

    public MMBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(MMBlocks.CHUD_BLOCK.get());

        this.dropSelf(MMBlocks.OPAL_BLOCK.get());

        this.dropSelf(MMBlocks.PALEO_TABLE.get());
        this.dropSelf(MMBlocks.CHRONO_ALTAR.get());
        this.dropSelf(MMBlocks.CHRONO_PEDESTAL.get());
        this.dropSelf(MMBlocks.CHRONOTITE.get());

        this.dropSelf(MMBlocks.SHALE.get());
        this.dropSelf(MMBlocks.SHALE_WALL.get());
        this.dropSelf(MMBlocks.SHALE_STAIRS.get());
        this.dropSelf(MMBlocks.SHALE_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_SHALE.get());
        this.dropSelf(MMBlocks.POLISHED_SHALE_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_SHALE_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_SHALE_SLAB.get());
        this.dropSelf(MMBlocks.SHALE_BRICKS.get());
        this.dropSelf(MMBlocks.SHALE_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.SHALE_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.SHALE_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.SHALE_MOSAIC.get());
        this.dropSelf(MMBlocks.SHALE_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.SHALE_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.SHALE_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_SHALE.get());
        this.dropOther(MMBlocks.SHALE_FOSSIL_BLOCK.get(), MMBlocks.SHALE.get());

        this.dropSelf(MMBlocks.SILTSTONE.get());
        this.dropSelf(MMBlocks.SILTSTONE_WALL.get());
        this.dropSelf(MMBlocks.SILTSTONE_STAIRS.get());
        this.dropSelf(MMBlocks.SILTSTONE_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_SILTSTONE.get());
        this.dropSelf(MMBlocks.POLISHED_SILTSTONE_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_SILTSTONE_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_SILTSTONE_SLAB.get());
        this.dropSelf(MMBlocks.SILTSTONE_BRICKS.get());
        this.dropSelf(MMBlocks.SILTSTONE_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.SILTSTONE_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.SILTSTONE_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.SILTSTONE_MOSAIC.get());
        this.dropSelf(MMBlocks.SILTSTONE_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.SILTSTONE_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.SILTSTONE_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_SILTSTONE.get());
        this.dropOther(MMBlocks.SILTSTONE_FOSSIL_BLOCK.get(), MMBlocks.SILTSTONE.get());

        this.dropSelf(MMBlocks.GABBRO.get());
        this.dropSelf(MMBlocks.GABBRO_WALL.get());
        this.dropSelf(MMBlocks.GABBRO_STAIRS.get());
        this.dropSelf(MMBlocks.GABBRO_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_GABBRO.get());
        this.dropSelf(MMBlocks.POLISHED_GABBRO_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_GABBRO_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_GABBRO_SLAB.get());
        this.dropSelf(MMBlocks.GABBRO_BRICKS.get());
        this.dropSelf(MMBlocks.GABBRO_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.GABBRO_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.GABBRO_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.GABBRO_MOSAIC.get());
        this.dropSelf(MMBlocks.GABBRO_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.GABBRO_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.GABBRO_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_GABBRO.get());
        this.dropOther(MMBlocks.GABBRO_FOSSIL_BLOCK.get(), MMBlocks.GABBRO.get());

        this.dropSelf(MMBlocks.MUDSTONE.get());
        this.dropSelf(MMBlocks.MUDSTONE_WALL.get());
        this.dropSelf(MMBlocks.MUDSTONE_STAIRS.get());
        this.dropSelf(MMBlocks.MUDSTONE_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_MUDSTONE.get());
        this.dropSelf(MMBlocks.POLISHED_MUDSTONE_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_MUDSTONE_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_MUDSTONE_SLAB.get());
        this.dropSelf(MMBlocks.MUDSTONE_BRICKS.get());
        this.dropSelf(MMBlocks.MUDSTONE_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.MUDSTONE_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.MUDSTONE_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.MUDSTONE_MOSAIC.get());
        this.dropSelf(MMBlocks.MUDSTONE_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.MUDSTONE_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.MUDSTONE_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_MUDSTONE.get());
        this.dropOther(MMBlocks.MUDSTONE_FOSSIL_BLOCK.get(), MMBlocks.MUDSTONE.get());

        this.dropSelf(MMBlocks.LIMESTONE.get());
        this.dropSelf(MMBlocks.LIMESTONE_WALL.get());
        this.dropSelf(MMBlocks.LIMESTONE_STAIRS.get());
        this.dropSelf(MMBlocks.LIMESTONE_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_LIMESTONE.get());
        this.dropSelf(MMBlocks.POLISHED_LIMESTONE_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_LIMESTONE_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_LIMESTONE_SLAB.get());
        this.dropSelf(MMBlocks.LIMESTONE_BRICKS.get());
        this.dropSelf(MMBlocks.LIMESTONE_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.LIMESTONE_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.LIMESTONE_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.LIMESTONE_MOSAIC.get());
        this.dropSelf(MMBlocks.LIMESTONE_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.LIMESTONE_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.LIMESTONE_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_LIMESTONE.get());
        this.dropOther(MMBlocks.LIMESTONE_FOSSIL_BLOCK.get(), MMBlocks.LIMESTONE.get());

        this.dropSelf(MMBlocks.CHALK.get());
        this.dropSelf(MMBlocks.CHALK_WALL.get());
        this.dropSelf(MMBlocks.CHALK_STAIRS.get());
        this.dropSelf(MMBlocks.CHALK_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_CHALK.get());
        this.dropSelf(MMBlocks.POLISHED_CHALK_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_CHALK_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_CHALK_SLAB.get());
        this.dropSelf(MMBlocks.CHALK_BRICKS.get());
        this.dropSelf(MMBlocks.CHALK_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.CHALK_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.CHALK_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.CHALK_MOSAIC.get());
        this.dropSelf(MMBlocks.CHALK_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.CHALK_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.CHALK_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_CHALK.get());
        this.dropOther(MMBlocks.CHALK_FOSSIL_BLOCK.get(), MMBlocks.CHALK.get());

        this.dropSelf(MMBlocks.BRECCIA.get());
        this.dropSelf(MMBlocks.BRECCIA_WALL.get());
        this.dropSelf(MMBlocks.BRECCIA_STAIRS.get());
        this.dropSelf(MMBlocks.BRECCIA_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_BRECCIA.get());
        this.dropSelf(MMBlocks.POLISHED_BRECCIA_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_BRECCIA_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_BRECCIA_SLAB.get());
        this.dropSelf(MMBlocks.BRECCIA_BRICKS.get());
        this.dropSelf(MMBlocks.BRECCIA_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.BRECCIA_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.BRECCIA_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.BRECCIA_MOSAIC.get());
        this.dropSelf(MMBlocks.BRECCIA_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.BRECCIA_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.BRECCIA_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_BRECCIA.get());
        this.dropOther(MMBlocks.BRECCIA_FOSSIL_BLOCK.get(), MMBlocks.BRECCIA.get());

        this.dropSelf(MMBlocks.DIATOMITE.get());
        this.dropSelf(MMBlocks.DIATOMITE_WALL.get());
        this.dropSelf(MMBlocks.DIATOMITE_STAIRS.get());
        this.dropSelf(MMBlocks.DIATOMITE_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_DIATOMITE.get());
        this.dropSelf(MMBlocks.POLISHED_DIATOMITE_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_DIATOMITE_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_DIATOMITE_SLAB.get());
        this.dropSelf(MMBlocks.DIATOMITE_BRICKS.get());
        this.dropSelf(MMBlocks.DIATOMITE_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.DIATOMITE_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.DIATOMITE_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.DIATOMITE_MOSAIC.get());
        this.dropSelf(MMBlocks.DIATOMITE_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.DIATOMITE_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.DIATOMITE_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_DIATOMITE.get());
        this.dropOther(MMBlocks.DIATOMITE_FOSSIL_BLOCK.get(), MMBlocks.DIATOMITE.get());

        this.dropSelf(MMBlocks.PERMAFROST.get());
        this.dropSelf(MMBlocks.PERMAFROST_WALL.get());
        this.dropSelf(MMBlocks.PERMAFROST_STAIRS.get());
        this.dropSelf(MMBlocks.PERMAFROST_SLAB.get());
        this.dropSelf(MMBlocks.POLISHED_PERMAFROST.get());
        this.dropSelf(MMBlocks.POLISHED_PERMAFROST_WALL.get());
        this.dropSelf(MMBlocks.POLISHED_PERMAFROST_STAIRS.get());
        this.dropSelf(MMBlocks.POLISHED_PERMAFROST_SLAB.get());
        this.dropSelf(MMBlocks.PERMAFROST_BRICKS.get());
        this.dropSelf(MMBlocks.PERMAFROST_BRICKS_WALL.get());
        this.dropSelf(MMBlocks.PERMAFROST_BRICKS_SLAB.get());
        this.dropSelf(MMBlocks.PERMAFROST_BRICKS_STAIRS.get());
        this.dropSelf(MMBlocks.PERMAFROST_MOSAIC.get());
        this.dropSelf(MMBlocks.PERMAFROST_MOSAIC_WALL.get());
        this.dropSelf(MMBlocks.PERMAFROST_MOSAIC_STAIRS.get());
        this.dropSelf(MMBlocks.PERMAFROST_MOSAIC_SLAB.get());
        this.dropSelf(MMBlocks.CHISELED_PERMAFROST.get());
        this.dropOther(MMBlocks.PERMAFROST_FOSSIL_BLOCK.get(), MMBlocks.PERMAFROST.get());

        this.dropSelf(MMBlocks.SIGILLARIA_PLANKS.get());
        this.dropSelf(MMBlocks.SIGILLARIA_STAIRS.get());
        this.dropSelf(MMBlocks.SIGILLARIA_FENCE.get());
        this.dropSelf(MMBlocks.SIGILLARIA_FENCE_GATE.get());
        this.dropSelf(MMBlocks.SIGILLARIA_BUTTON.get());
        this.dropSelf(MMBlocks.SIGILLARIA_PRESSURE_PLATE.get());
        this.dropSelf(MMBlocks.SIGILLARIA_TRAPDOOR.get());

        this.add(MMBlocks.SIGILLARIA_SLAB.get(),
                block -> createSlabItemTable(MMBlocks.SIGILLARIA_SLAB.get()));
        this.add(MMBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(MMBlocks.SIGILLARIA_MOSAIC_SLAB.get()));
        this.add(MMBlocks.SIGILLARIA_DOOR.get(),
                block -> createDoorTable(MMBlocks.SIGILLARIA_DOOR.get()));

        this.add(MMBlocks.SIGILLARIA_SIGN.get(),
                block -> createSingleItemTable(MMItems.SIGILLARIA_SIGN.get()));
        this.add(MMBlocks.SIGILLARIA_WALL_SIGN.get(),
                block -> createSingleItemTable(MMItems.SIGILLARIA_SIGN.get()));
        this.add(MMBlocks.SIGILLARIA_HANGING_SIGN.get(),
                block -> createSingleItemTable(MMItems.SIGILLARIA_HANGING_SIGN.get()));
        this.add(MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(MMItems.SIGILLARIA_HANGING_SIGN.get()));

        this.dropSelf(MMBlocks.SIGILLARIA_MOSAIC.get());
        this.dropSelf(MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get());

        this.dropSelf(MMBlocks.SIGILLARIA_STEM.get());
        this.dropSelf(MMBlocks.STRIPPED_SIGILLARIA_STEM.get());
        this.dropSelf(MMBlocks.SIGILLARIA_WOOD.get());
        this.dropSelf(MMBlocks.STRIPPED_SIGILLARIA_WOOD.get());

        this.dropSelf(MMBlocks.SIGILLARIA_SAPLING.get());
        this.add(MMBlocks.POTTED_SIGILLARIA_SAPLING.get(),
                createPotFlowerItemTable(MMBlocks.SIGILLARIA_SAPLING.get()));

        this.add(MMBlocks.SIGILLARIA_LEAVES.get(),
                block -> createLeavesDrops(MMBlocks.SIGILLARIA_LEAVES.get(), MMBlocks.SIGILLARIA_SAPLING.get(), 0.15f));

        this.dropSelf(MMBlocks.COOKSONIA.get());

        this.dropSelf(MMBlocks.PROTOTAXITES.get());
        this.add(MMBlocks.POTTED_PROTOTAXITES.get(),
                createPotFlowerItemTable(MMBlocks.PROTOTAXITES.get()));

        this.dropSelf(MMBlocks.PROTOTAXITES_BLOCK.get());

        this.dropSelf(MMBlocks.DICKINSONIA.get());

        this.dropSelf(MMBlocks.PROTOTAXITES_PLANKS.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_STAIRS.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_FENCE.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_FENCE_GATE.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_BUTTON.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_TRAPDOOR.get());

        this.add(MMBlocks.PROTOTAXITES_SLAB.get(),
                block -> createSlabItemTable(MMBlocks.PROTOTAXITES_SLAB.get()));
        this.add(MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get()));
        this.add(MMBlocks.PROTOTAXITES_DOOR.get(),
                block -> createDoorTable(MMBlocks.PROTOTAXITES_DOOR.get()));

        this.add(MMBlocks.PROTOTAXITES_SIGN.get(),
                block -> createSingleItemTable(MMItems.PROTOTAXITES_SIGN.get()));
        this.add(MMBlocks.PROTOTAXITES_WALL_SIGN.get(),
                block -> createSingleItemTable(MMItems.PROTOTAXITES_SIGN.get()));
        this.add(MMBlocks.PROTOTAXITES_HANGING_SIGN.get(),
                block -> createSingleItemTable(MMItems.PROTOTAXITES_HANGING_SIGN.get()));
        this.add(MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(MMItems.PROTOTAXITES_HANGING_SIGN.get()));

        this.dropSelf(MMBlocks.PROTOTAXITES_MOSAIC.get());
        this.dropSelf(MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get());

        this.dropSelf(MMBlocks.WIWAXIA.get());

        this.add(MMBlocks.CHARNIA.get(),
                block -> createCharniaDrops(MMBlocks.CHARNIA.get()));
        
    }

    public LootTable.Builder createCharniaDrops(Block charniaBlock) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(charniaBlock, LootItem.lootTableItem(charniaBlock).apply(IntStream.rangeClosed(1, 4).boxed().toList(), (integer) -> {
            return SetItemCountFunction.setCount(ConstantValue.exactly((float) integer.intValue())).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(charniaBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CharniaBlock.PICKLES, integer)));
        }))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
