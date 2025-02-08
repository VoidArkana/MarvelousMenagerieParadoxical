package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                MMBlocks.SIGILLARIA_PLANKS.get(),
                MMBlocks.SIGILLARIA_STAIRS.get(),
                MMBlocks.SIGILLARIA_SLAB.get(),
                MMBlocks.SIGILLARIA_BUTTON.get(),
                MMBlocks.SIGILLARIA_PRESSURE_PLATE.get(),
                MMBlocks.SIGILLARIA_FENCE.get(),
                MMBlocks.SIGILLARIA_FENCE_GATE.get(),
                MMBlocks.SIGILLARIA_MOSAIC.get(),
                MMBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get(),
                MMBlocks.SIGILLARIA_DOOR.get(),
                MMBlocks.SIGILLARIA_TRAPDOOR.get(),
                MMBlocks.SIGILLARIA_STEM.get(),
                MMBlocks.STRIPPED_SIGILLARIA_STEM.get(),
                MMBlocks.SIGILLARIA_WOOD.get(),
                MMBlocks.STRIPPED_SIGILLARIA_WOOD.get(),
                MMBlocks.SIGILLARIA_SIGN.get(),
                MMBlocks.SIGILLARIA_WALL_SIGN.get(),
                MMBlocks.SIGILLARIA_HANGING_SIGN.get(),
                MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),

                MMBlocks.PROTOTAXITES.get(),
                MMBlocks.PROTOTAXITES_BLOCK.get(),
                MMBlocks.PROTOTAXITES_PLANKS.get(),
                MMBlocks.PROTOTAXITES_STAIRS.get(),
                MMBlocks.PROTOTAXITES_SLAB.get(),
                MMBlocks.PROTOTAXITES_BUTTON.get(),
                MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get(),
                MMBlocks.PROTOTAXITES_FENCE.get(),
                MMBlocks.PROTOTAXITES_FENCE_GATE.get(),
                MMBlocks.PROTOTAXITES_MOSAIC.get(),
                MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get(),
                MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get(),
                MMBlocks.PROTOTAXITES_DOOR.get(),
                MMBlocks.PROTOTAXITES_TRAPDOOR.get(),
                MMBlocks.PROTOTAXITES_SIGN.get(),
                MMBlocks.PROTOTAXITES_WALL_SIGN.get(),
                MMBlocks.PROTOTAXITES_HANGING_SIGN.get(),
                MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.PLANKS).add(
                MMBlocks.SIGILLARIA_PLANKS.get(),
                MMBlocks.SIGILLARIA_MOSAIC.get(),
                MMBlocks.PROTOTAXITES_PLANKS.get(),
                MMBlocks.PROTOTAXITES_MOSAIC.get()
        );

        this.tag(MMTags.Blocks.SIGILLARIA_LOG_BLOCK).add(
                MMBlocks.SIGILLARIA_STEM.get(),
                MMBlocks.STRIPPED_SIGILLARIA_STEM.get(),
                MMBlocks.SIGILLARIA_WOOD.get(),
                MMBlocks.STRIPPED_SIGILLARIA_WOOD.get()
        );

        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(MMTags.Blocks.SIGILLARIA_LOG_BLOCK);

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                MMBlocks.SIGILLARIA_LEAVES.get()
        );

        this.tag(BlockTags.LEAVES).add(
                MMBlocks.SIGILLARIA_LEAVES.get()
        );
        this.tag(BlockTags.WOODEN_FENCES).add(
                MMBlocks.SIGILLARIA_FENCE.get(),
                MMBlocks.PROTOTAXITES_FENCE.get()
        );
        this.tag(BlockTags.FENCE_GATES).add(
                MMBlocks.SIGILLARIA_FENCE_GATE.get(),
                MMBlocks.PROTOTAXITES_FENCE_GATE.get()
        );

        this.tag(BlockTags.WOODEN_DOORS).add(
                MMBlocks.SIGILLARIA_DOOR.get(),
                MMBlocks.PROTOTAXITES_DOOR.get()
        );

        this.tag(BlockTags.WOODEN_TRAPDOORS).add(
                MMBlocks.SIGILLARIA_TRAPDOOR.get(),
                MMBlocks.PROTOTAXITES_TRAPDOOR.get()
        );

        this.tag(BlockTags.WOODEN_STAIRS).add(
                MMBlocks.SIGILLARIA_STAIRS.get(),
                MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get(),
                MMBlocks.PROTOTAXITES_STAIRS.get(),
                MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get()
        );
        this.tag(BlockTags.WOODEN_SLABS).add(
                MMBlocks.SIGILLARIA_SLAB.get(),
                MMBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                MMBlocks.PROTOTAXITES_SLAB.get(),
                MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(
                MMBlocks.SIGILLARIA_BUTTON.get(),
                MMBlocks.PROTOTAXITES_BUTTON.get()
        );
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                MMBlocks.SIGILLARIA_PRESSURE_PLATE.get(),
                MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get()
        );

        this.tag(BlockTags.SIGNS).add(
                MMBlocks.SIGILLARIA_SIGN.get(),
                MMBlocks.PROTOTAXITES_SIGN.get()
        );

        this.tag(BlockTags.WALL_SIGNS).add(
                MMBlocks.SIGILLARIA_WALL_SIGN.get(),
                MMBlocks.PROTOTAXITES_WALL_SIGN.get()
        );

        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
                MMBlocks.SIGILLARIA_HANGING_SIGN.get(),
                MMBlocks.PROTOTAXITES_HANGING_SIGN.get()
        );

        this.tag(BlockTags.WALL_HANGING_SIGNS).add(
                MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                MMBlocks.COOKSONIA.get()
        );

        this.tag(MMTags.Blocks.DYE_DEPOT_MAROON_WOOL).addOptional(new ResourceLocation("dye_depot:maroon_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_ROSE_WOOL).addOptional(new ResourceLocation("dye_depot:rose_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_CORAL_WOOL).addOptional(new ResourceLocation("dye_depot:coral_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_INDIGO_WOOL).addOptional(new ResourceLocation("dye_depot:indigo_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_NAVY_WOOL).addOptional(new ResourceLocation("dye_depot:navy_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_SLATE_WOOL).addOptional(new ResourceLocation("dye_depot:slate_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_OLIVE_WOOL).addOptional(new ResourceLocation("dye_depot:olive_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_AMBER_WOOL).addOptional(new ResourceLocation("dye_depot:amber_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_BEIGE_WOOL).addOptional(new ResourceLocation("dye_depot:beige_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_TEAL_WOOL).addOptional(new ResourceLocation("dye_depot:teal_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_MINT_WOOL).addOptional(new ResourceLocation("dye_depot:mint_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_AQUA_WOOL).addOptional(new ResourceLocation("dye_depot:aqua_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_VERDANT_WOOL).addOptional(new ResourceLocation("dye_depot:verdant_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_FOREST_WOOL).addOptional(new ResourceLocation("dye_depot:forest_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_GINGER_WOOL).addOptional(new ResourceLocation("dye_depot:ginger_carpet"));
        this.tag(MMTags.Blocks.DYE_DEPOT_TAN_WOOL).addOptional(new ResourceLocation("dye_depot:tan_carpet"));

        this.tag(MMTags.Blocks.DYE_DEPOT_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_MAROON_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_ROSE_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_CORAL_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_INDIGO_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_NAVY_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_SLATE_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_OLIVE_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_AMBER_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_BEIGE_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_TEAL_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_MINT_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_AQUA_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_VERDANT_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_FOREST_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_GINGER_WOOL)
                .addTag(MMTags.Blocks.DYE_DEPOT_TAN_WOOL);

        this.tag(MMTags.Blocks.PIKAIA_REVIVING_TARGET).add(

                        Blocks.DEAD_BRAIN_CORAL,
                        Blocks.DEAD_BRAIN_CORAL_BLOCK,
                        Blocks.DEAD_BRAIN_CORAL_FAN,
                        Blocks.DEAD_BRAIN_CORAL_WALL_FAN,

                        Blocks.DEAD_BUBBLE_CORAL,
                        Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                        Blocks.DEAD_BUBBLE_CORAL_FAN,
                        Blocks.DEAD_BUBBLE_CORAL_WALL_FAN,

                        Blocks.DEAD_FIRE_CORAL,
                        Blocks.DEAD_FIRE_CORAL_BLOCK,
                        Blocks.DEAD_FIRE_CORAL_FAN,
                        Blocks.DEAD_FIRE_CORAL_WALL_FAN,

                        Blocks.DEAD_HORN_CORAL,
                        Blocks.DEAD_HORN_CORAL_BLOCK,
                        Blocks.DEAD_HORN_CORAL_FAN,
                        Blocks.DEAD_HORN_CORAL_WALL_FAN,

                        Blocks.DEAD_TUBE_CORAL,
                        Blocks.DEAD_TUBE_CORAL_BLOCK,
                        Blocks.DEAD_TUBE_CORAL_FAN,
                        Blocks.DEAD_TUBE_CORAL_WALL_FAN)

                //Unusual Prehistori
                .addOptional(new ResourceLocation("unusual_prehistory:dead_clathrodictyon_coral"))
                .addOptional(new ResourceLocation("unusual_prehistory:dead_clathrodictyon_coral_block_fan"))
                .addOptional(new ResourceLocation("unusual_prehistory:dead_clathrodictyon_coral_fan"))
                .addOptional(new ResourceLocation("unusual_prehistory:dead_clathrodictyon_coral_wall_fan"))
                
                //upgrade aquatic
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_acan_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_acan_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_acan_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_acan_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_finger_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_finger_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_finger_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_finger_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_star_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_star_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_star_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_star_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_moss_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_moss_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_moss_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_moss_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_petal_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_petal_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_petal_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_petal_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_branch_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_branch_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_branch_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_branch_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_rock_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_rock_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_rock_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_rock_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_pillow_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_pillow_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_pillow_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_pillow_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_silk_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_silk_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_silk_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_silk_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:dead_chrome_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_chrome_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_chrome_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:dead_chrome_coral_wall_fan"))

                .addOptional(new ResourceLocation("upgrade_aquatic:elder_prismarine_coral"))
                .addOptional(new ResourceLocation("upgrade_aquatic:elder_prismarine_coral_block_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:elder_prismarine_coral_fan"))
                .addOptional(new ResourceLocation("upgrade_aquatic:elder_prismarine_coral_wall_fan"))

                //rainbow reef
                .addOptional(new ResourceLocation("rainbowreef:dead_chimney_coral"))
                .addOptional(new ResourceLocation("rainbowreef:dead_chimney_coral_block"))

                .addOptional(new ResourceLocation("rainbowreef:dead_hand_coral"))
                .addOptional(new ResourceLocation("rainbowreef:dead_hand_coral_block"))

                .addOptional(new ResourceLocation("rainbowreef:dead_barrel_coral"))
                .addOptional(new ResourceLocation("rainbowreef:dead_barrel_coral_block"))

                .addOptional(new ResourceLocation("rainbowreef:dead_shelf_coral"))
                .addOptional(new ResourceLocation("rainbowreef:dead_shelf_coral_block"))

                .addOptional(new ResourceLocation("rainbowreef:dead_tower_coral"))
                .addOptional(new ResourceLocation("rainbowreef:dead_tower_coral_block"));

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
