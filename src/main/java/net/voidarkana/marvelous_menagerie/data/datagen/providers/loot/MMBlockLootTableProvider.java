package net.voidarkana.marvelous_menagerie.data.datagen.providers.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.Set;

public class MMBlockLootTableProvider extends BlockLootSubProvider {

    public MMBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

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
        
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
