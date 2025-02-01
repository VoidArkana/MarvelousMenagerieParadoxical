package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMItemTagGenerator extends ItemTagsProvider {
    public MMItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MarvelousMenagerie.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(MMTags.Items.NATURAL_FOSSILS).add(
                MMItems.SHALE_FOSSIL.get(),
                MMItems.SILTSTONE_FOSSIL.get(),
                MMItems.GABBRO_FOSSIL.get(),
                MMItems.MUDSTONE_FOSSIL.get(),
                MMItems.LIMESTONE_FOSSIL.get(),
                MMItems.CHALK_FOSSIL.get(),
                MMItems.BRECCIA_FOSSIL.get(),
                MMItems.DIATOMITE_FOSSIL.get(),
                MMItems.PERMAFROST_FOSSIL.get());

//        this.tag(Tags.Items.EGGS).add(
//                ModBlocks.DODO_EGGS.get().asItem(),
//                ModBlocks.ELE_EGG.get().asItem(),
//                ModItems.CRACKED_ELEPHANT_EGG.get(),
//                ModBlocks.SACA_EGGS.get().asItem(),
//                ModBlocks.TRILO_EGGS.get().asItem(),
//                ModBlocks.CARIS_EGGS.get().asItem(),
//                ModBlocks.PIKAIA_EGGS.get().asItem(),
//                ModBlocks.ARANDASPIS_EGGS.get().asItem(),
//                ModBlocks.HALLU_EGGS.get().asItem());
//
//        this.tag(Tags.Items.ARMORS_HELMETS).add(
//                ModItems.EGG_SHELLMET.get(),
//                ModItems.ANOMALOUS_GOGGLES.get());
//
//        this.tag(ItemTags.TRIMMABLE_ARMOR).add(
//                ModItems.EGG_SHELLMET.get());

//        this.copy(MMTags.Blocks.SIGILLARIA_LOG_BLOCK, MMTags.Items.SIGILLARIA_LOG_ITEM);
//
//        this.copy(MMTags.Blocks.DYE_DEPOT_AMBER_WOOL, MMTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_AQUA_WOOL, MMTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_BEIGE_WOOL, MMTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_CORAL_WOOL, MMTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_FOREST_WOOL, MMTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_GINGER_WOOL, MMTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_INDIGO_WOOL, MMTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_MAROON_WOOL, MMTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_MINT_WOOL, MMTags.Items.DYE_DEPOT_MINT_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_NAVY_WOOL, MMTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_OLIVE_WOOL, MMTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_ROSE_WOOL, MMTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_SLATE_WOOL, MMTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_TAN_WOOL, MMTags.Items.DYE_DEPOT_TAN_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_TEAL_WOOL, MMTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM);
//        this.copy(MMTags.Blocks.DYE_DEPOT_VERDANT_WOOL, MMTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);
//
//        this.tag(MMTags.Items.DYE_DEPOT_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_MINT_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_TAN_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM)
//                .addTag(MMTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);
//
//        this.tag(ItemTags.LOGS_THAT_BURN)
//                .addTag(MMTags.Items.SIGILLARIA_LOG_ITEM);

//        this.tag(ItemTags.PLANKS).add(
//                ModBlocks.PROTOTAXITES_PLANKS.get().asItem(),
//                ModBlocks.PROTOTAXITES_MOSAIC.get().asItem(),
//                ModBlocks.ZULOAGAE_PLANKS.get().asItem(),
//                ModBlocks.ZULOAGAE_MOSAIC.get().asItem(),
//                ModBlocks.SIGILLARIA_PLANKS.get().asItem(),
//                ModBlocks.SIGILLARIA_MOSAIC.get().asItem()
//        );
//
//        this.tag(ItemTags.LEAVES).add(
//                ModBlocks.SIGILLARIA_LEAVES.get().asItem()
//        );
//        this.tag(ItemTags.WOODEN_FENCES).add(
//                ModBlocks.ZULOAGAE_FENCE.get().asItem(),
//                ModBlocks.PROTOTAXITES_FENCE.get().asItem(),
//                ModBlocks.SIGILLARIA_FENCE.get().asItem()
//        );
//        this.tag(ItemTags.FENCE_GATES).add(
//                ModBlocks.ZULOAGAE_FENCE_GATE.get().asItem(),
//                ModBlocks.PROTOTAXITES_FENCE_GATE.get().asItem(),
//                ModBlocks.SIGILLARIA_FENCE_GATE.get().asItem()
//        );
//
//        this.tag(ItemTags.WOODEN_DOORS).add(
//                ModBlocks.ZULOAGAE_DOOR.get().asItem(),
//                ModBlocks.PROTOTAXITES_DOOR.get().asItem(),
//                ModBlocks.SIGILLARIA_DOOR.get().asItem()
//        );
//
//        this.tag(ItemTags.WOODEN_TRAPDOORS).add(
//                ModBlocks.ZULOAGAE_TRAPDOOR.get().asItem(),
//                ModBlocks.PROTOTAXITES_TRAPDOOR.get().asItem(),
//                ModBlocks.SIGILLARIA_TRAPDOOR.get().asItem()
//        );
//
//        this.tag(ItemTags.WOODEN_STAIRS).add(
//                ModBlocks.PROTOTAXITES_MOSAIC_STAIRS.get().asItem(),
//                ModBlocks.PROTOTAXITES_STAIRS.get().asItem(),
//                ModBlocks.ZULOAGAE_STAIRS.get().asItem(),
//                ModBlocks.ZULOAGAE_MOSAIC_STAIRS.get().asItem(),
//                ModBlocks.SIGILLARIA_STAIRS.get().asItem(),
//                ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get().asItem()
//        );
//        this.tag(ItemTags.WOODEN_SLABS).add(
//                ModBlocks.PROTOTAXITES_SLAB.get().asItem(),
//                ModBlocks.PROTOTAXITES_MOSAIC_SLAB.get().asItem(),
//                ModBlocks.ZULOAGAE_SLAB.get().asItem(),
//                ModBlocks.ZULOAGAE_MOSAIC_SLAB.get().asItem(),
//                ModBlocks.SIGILLARIA_SLAB.get().asItem(),
//                ModBlocks.SIGILLARIA_MOSAIC_SLAB.get().asItem()
//        );
//        this.tag(ItemTags.WOODEN_BUTTONS).add(
//                ModBlocks.PROTOTAXITES_BUTTON.get().asItem(),
//                ModBlocks.ZULOAGAE_BUTTON.get().asItem(),
//                ModBlocks.SIGILLARIA_BUTTON.get().asItem()
//        );
//        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
//                ModBlocks.PROTOTAXITES_PRESSURE_PLATE.get().asItem(),
//                ModBlocks.ZULOAGAE_PRESSURE_PLATE.get().asItem(),
//                ModBlocks.SIGILLARIA_PRESSURE_PLATE.get().asItem()
//        );
//
//        this.tag(ItemTags.SIGNS).add(
//                ModItems.PROTOTAXITES_SIGN.get(),
//                ModItems.ZULOAGAE_SIGN.get(),
//                ModItems.SIGILLARIA_SIGN.get()
//        );
//
//        this.tag(ItemTags.HANGING_SIGNS).add(
//                ModItems.PROTOTAXITES_HANGING_SIGN.get(),
//                ModItems.ZULOAGAE_HANGING_SIGN.get(),
//                ModItems.SIGILLARIA_HANGING_SIGN.get()
//        );
//
//        this.tag(ItemTags.FISHES).add(
//                ModItems.SACABAMBASPIS.get(),
//                ModItems.TRILO_BITE.get()
//        );
//
//        this.tag(ItemTags.PIGLIN_LOVED).add(
//                ModItems.GOLDEN_SACA.get()
//        );
//
//        this.tag(ItemTags.AXOLOTL_TEMPT_ITEMS).add(
//                ModItems.SACA_BUCKET.get(),
//                ModItems.PIKAIA_BUCKET.get(),
//                ModItems.ARANDASPIS_BUCKET.get()
//        );

        this.tag(MMTags.Items.FINTASTIC_BAD_FEED).addOptional(new ResourceLocation("fintastic:bad_feed"));
    }
}
