package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MMItemTagGenerator extends ItemTagsProvider {
    public MMItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(Tags.Items.EGGS).add(
                MMBlocks.BOREALOPELTA_EGG.get().asItem()
        );

        this.tag(MMTags.Items.ANIMAL_FOSSILS).add(
                MMItems.SACA_FOSSIL.get(),
                MMItems.ARANDASPIS_FOSSIL.get(),
                MMItems.TRILO_FOSSIL.get(),
                MMItems.CARIS_FOSSIL.get(),
                MMItems.PIKAIA_FOSSIL.get(),
                MMItems.HALLU_FOSSIL.get(),
                MMItems.FALCA_FOSSIL.get(),
                MMItems.GORGON_FOSSIL.get(),
                MMItems.LYSTRO_FOSSIL.get(),
                MMItems.OPHTHALMO_FOSSIL.get(),
                MMItems.THERI_FOSSIL.get(),
                MMItems.TLATO_FOSSIL.get(),
                MMItems.PELTA_FOSSIL.get(),
                MMItems.DAEODON_FOSSIL.get(),
                MMItems.DAWN_FOSSIL.get(),
                MMItems.LEPTI_FOSSIL.get(),
                MMItems.EOLACTORIA_FOSSIL.get(),
                MMItems.KELENKEN_FOSSIL.get(),
                MMItems.JOSEPHO_FOSSIL.get(),
                MMItems.SLOV_FOSSIL.get(),
                MMItems.DODO_FOSSIL.get(),
                MMItems.THYLACINE_FOSSIL.get(),
                MMItems.STELLER_FOSSIL.get(),
                MMItems.ELEBIRD_FOSSIL.get(),
                MMItems.DOEDICURUS_FOSSIL.get(),
                MMItems.SNIFFER_FOSSIL.get(),
                MMItems.FLUBBER_FOSSIL.get(),
                MMItems.DIPLOCAULUS_FOSSIL.get()
        );

        this.tag(MMTags.Items.PALEONOMICON_INGREDIENTS).add(
                MMItems.SACA_FOSSIL.get(),
                MMItems.ARANDASPIS_FOSSIL.get(),
                MMItems.TRILO_FOSSIL.get(),
                MMItems.CARIS_FOSSIL.get(),
                MMItems.PIKAIA_FOSSIL.get(),
                MMItems.HALLU_FOSSIL.get(),
                MMItems.FALCA_FOSSIL.get(),
                MMItems.GORGON_FOSSIL.get(),
                MMItems.LYSTRO_FOSSIL.get(),
                MMItems.OPHTHALMO_FOSSIL.get(),
                MMItems.THERI_FOSSIL.get(),
                MMItems.TLATO_FOSSIL.get(),
                MMItems.PELTA_FOSSIL.get(),
                MMItems.DAEODON_FOSSIL.get(),
                MMItems.DAWN_FOSSIL.get(),
                MMItems.LEPTI_FOSSIL.get(),
                MMItems.EOLACTORIA_FOSSIL.get(),
                MMItems.KELENKEN_FOSSIL.get(),
                MMItems.JOSEPHO_FOSSIL.get(),
                MMItems.SLOV_FOSSIL.get(),
                MMItems.DODO_FOSSIL.get(),
                MMItems.THYLACINE_FOSSIL.get(),
                MMItems.STELLER_FOSSIL.get(),
                MMItems.ELEBIRD_FOSSIL.get(),
                MMItems.DOEDICURUS_FOSSIL.get(),
                MMItems.SNIFFER_FOSSIL.get(),
                MMItems.FLUBBER_FOSSIL.get(),
                MMItems.DIPLOCAULUS_FOSSIL.get(),
                MMItems.CHRONOTITE.get(),
                MMItems.MOLTEN_SPINE.get(),
                MMItems.BEHOLDER_MANDIBLE.get(),
                MMItems.CHUD_FLESH.get()
        );

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

        this.tag(Tags.Items.EGGS).add(
                MMItems.ELEPHANT_BIRD_EGG.get(),
                MMBlocks.BOREALOPELTA_EGG.get().asItem()
                );

        this.tag(Tags.Items.ARMORS_HELMETS).add(
                MMItems.EGG_SHELLMET.get(),
                MMItems.ANOMALOUS_GOGGLES.get());

        this.copy(MMTags.Blocks.SIGILLARIA_LOG_BLOCK, MMTags.Items.SIGILLARIA_LOG_ITEM);

        this.copy(MMTags.Blocks.CALAMITES_LOG_BLOCK, MMTags.Items.CALAMITES_LOG_ITEM);
        this.copy(MMTags.Blocks.CALAMITES_BUNDLE_BLOCK, MMTags.Items.CALAMITES_BUNDLE_ITEM);

        this.copy(MMTags.Blocks.DYE_DEPOT_AMBER_WOOL, MMTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_AQUA_WOOL, MMTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_BEIGE_WOOL, MMTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_CORAL_WOOL, MMTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_FOREST_WOOL, MMTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_GINGER_WOOL, MMTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_INDIGO_WOOL, MMTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_MAROON_WOOL, MMTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_MINT_WOOL, MMTags.Items.DYE_DEPOT_MINT_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_NAVY_WOOL, MMTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_OLIVE_WOOL, MMTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_ROSE_WOOL, MMTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_SLATE_WOOL, MMTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_TAN_WOOL, MMTags.Items.DYE_DEPOT_TAN_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_TEAL_WOOL, MMTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM);
        this.copy(MMTags.Blocks.DYE_DEPOT_VERDANT_WOOL, MMTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);

        this.tag(MMTags.Items.DYE_DEPOT_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_MINT_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_TAN_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM)
                .addTag(MMTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(MMTags.Items.SIGILLARIA_LOG_ITEM)
                .addTag(MMTags.Items.CALAMITES_LOG_ITEM)
                .addTag(MMTags.Items.CALAMITES_BUNDLE_ITEM);

        this.tag(ItemTags.PLANKS).add(
                MMBlocks.PROTOTAXITES_PLANKS.get().asItem(),
                MMBlocks.PROTOTAXITES_MOSAIC.get().asItem(),
                MMBlocks.SIGILLARIA_PLANKS.get().asItem(),
                MMBlocks.SIGILLARIA_MOSAIC.get().asItem(),
                MMBlocks.CALAMITES_PLANKS.get().asItem(),
                MMBlocks.CALAMITES_MOSAIC.get().asItem()
        );

        this.tag(ItemTags.LEAVES).add(
                MMBlocks.SIGILLARIA_LEAVES.get().asItem(),
                MMBlocks.CALAMITES_BRANCH.get().asItem()
        );

        this.tag(ItemTags.WOODEN_FENCES).add(
                MMBlocks.PROTOTAXITES_FENCE.get().asItem(),
                MMBlocks.SIGILLARIA_FENCE.get().asItem(),
                MMBlocks.CALAMITES_FENCE.get().asItem()
        );
        this.tag(ItemTags.FENCE_GATES).add(
                MMBlocks.PROTOTAXITES_FENCE_GATE.get().asItem(),
                MMBlocks.SIGILLARIA_FENCE_GATE.get().asItem(),
                MMBlocks.CALAMITES_FENCE_GATE.get().asItem()
        );

        this.tag(ItemTags.WOODEN_DOORS).add(
                MMBlocks.PROTOTAXITES_DOOR.get().asItem(),
                MMBlocks.SIGILLARIA_DOOR.get().asItem(),
                MMBlocks.CALAMITES_DOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_TRAPDOORS).add(
                MMBlocks.PROTOTAXITES_TRAPDOOR.get().asItem(),
                MMBlocks.SIGILLARIA_TRAPDOOR.get().asItem(),
                MMBlocks.CALAMITES_TRAPDOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_STAIRS).add(
                MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get().asItem(),
                MMBlocks.PROTOTAXITES_STAIRS.get().asItem(),
                MMBlocks.SIGILLARIA_STAIRS.get().asItem(),
                MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get().asItem(),
                MMBlocks.CALAMITES_STAIRS.get().asItem(),
                MMBlocks.CALAMITES_MOSAIC_STAIRS.get().asItem()
        );
        this.tag(ItemTags.WOODEN_SLABS).add(
                MMBlocks.PROTOTAXITES_SLAB.get().asItem(),
                MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get().asItem(),
                MMBlocks.SIGILLARIA_SLAB.get().asItem(),
                MMBlocks.SIGILLARIA_MOSAIC_SLAB.get().asItem(),
                MMBlocks.CALAMITES_SLAB.get().asItem(),
                MMBlocks.CALAMITES_MOSAIC_SLAB.get().asItem()
        );
        this.tag(ItemTags.WOODEN_BUTTONS).add(
                MMBlocks.PROTOTAXITES_BUTTON.get().asItem(),
                MMBlocks.SIGILLARIA_BUTTON.get().asItem(),
                MMBlocks.CALAMITES_BUTTON.get().asItem()
        );
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
                MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get().asItem(),
                MMBlocks.SIGILLARIA_PRESSURE_PLATE.get().asItem(),
                MMBlocks.CALAMITES_PRESSURE_PLATE.get().asItem()
        );

        this.tag(ItemTags.SIGNS).add(
                MMItems.PROTOTAXITES_SIGN.get(),
                MMItems.SIGILLARIA_SIGN.get(),
                MMItems.CALAMITES_SIGN.get()
        );

        this.tag(ItemTags.HANGING_SIGNS).add(
                MMItems.PROTOTAXITES_HANGING_SIGN.get(),
                MMItems.SIGILLARIA_HANGING_SIGN.get(),
                MMItems.CALAMITES_HANGING_SIGN.get()
        );

        this.tag(ItemTags.FISHES).add(
                MMItems.SACABAMBASPIS.get(),
                MMItems.TRILO_BITE.get()
        );

        this.tag(ItemTags.PIGLIN_LOVED).add(
                MMItems.GOLDEN_SACA.get()
        );

        this.tag(ItemTags.AXOLOTL_TEMPT_ITEMS).add(
                MMItems.SACA_BUCKET.get(),
                MMItems.PIKAIA_BUCKET.get(),
                MMItems.ARANDASPIS_BUCKET.get()
        );

        this.tag(MMTags.Items.FINTASTIC_BAD_FEED).addOptional(new ResourceLocation("fintastic:bad_feed"));
        this.tag(MMTags.Items.FINTASTIC_FEED).addOptional(new ResourceLocation("fintastic:regular_feed"));
        this.tag(MMTags.Items.FINTASTIC_QUALITY_FEED).addOptional(new ResourceLocation("fintastic:quality_feed"));
        this.tag(MMTags.Items.FINTASTIC_GREAT_FEED).addOptional(new ResourceLocation("fintastic:great_feed"));
        this.tag(MMTags.Items.FINTASTIC_PREMIUM_FEED).addOptional(new ResourceLocation("fintastic:premium_feed"));

        this.tag(MMTags.Items.DOMESTICATION_INNOVATION_ROTTEN_APPLE).addOptional(new ResourceLocation("domesticationinnovation:rotten_apple"));

        this.tag(MMTags.Items.FINTASTIC_ALL_FEEDS)
                .addOptional(new ResourceLocation("fintastic:regular_feed"))
                .addOptional(new ResourceLocation("fintastic:quality_feed"))
                .addOptional(new ResourceLocation("fintastic:great_feed"))
                .addOptional(new ResourceLocation("fintastic:premium_feed"));

        this.tag(MMTags.Items.CORAL_ITEMS).add(
                Blocks.TUBE_CORAL.asItem(),
                Blocks.BRAIN_CORAL.asItem(),
                Blocks.BUBBLE_CORAL.asItem(),
                Blocks.FIRE_CORAL.asItem(),
                Blocks.HORN_CORAL.asItem(),
                Blocks.TUBE_CORAL_FAN.asItem(),
                Blocks.BRAIN_CORAL_FAN.asItem(),
                Blocks.BUBBLE_CORAL_FAN.asItem(),
                Blocks.FIRE_CORAL_FAN.asItem(),
                Blocks.HORN_CORAL_FAN.asItem());

        this.tag(MMTags.Items.CORAL_BLOCK_ITEMS).add(
                Blocks.TUBE_CORAL_BLOCK.asItem(),
                Blocks.BRAIN_CORAL_BLOCK.asItem(),
                Blocks.BUBBLE_CORAL_BLOCK.asItem(),
                Blocks.FIRE_CORAL_BLOCK.asItem(),
                Blocks.HORN_CORAL_BLOCK.asItem());

        this.tag(MMTags.Items.OPHTHALMO_FOOD).add(
                MMItems.SACABAMBASPIS.get(),
                MMItems.TRILO_BITE.get());

        this.copy(MMTags.Blocks.ANIMAL_BLOCKS, MMTags.Items.ANIMAL_BLOCKS_ITEM);

        this.copy(MMTags.Blocks.FLUBBER_DIG, MMTags.Items.FLUBBER_DIG_ITEM);
    }
}
