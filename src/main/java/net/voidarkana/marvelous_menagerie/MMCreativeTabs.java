package net.voidarkana.marvelous_menagerie;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

public class MMCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_MOBS =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab_mobs", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(MMItems.CARIS_BUCKET.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab_mobs"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(MMItems.BEHOLDER_MANDIBLE.get());
                        output.accept(MMItems.CHUD_FLESH.get());
                        output.accept(MMItems.MOLTEN_SPINE.get());

                        output.accept(MMItems.CARIS_BUCKET.get());
                        output.accept(MMItems.CARIS_LENS.get());
                        output.accept(MMItems.CARIS_SCUTE.get());
                        output.accept(MMItems.ANOMALOUS_GOGGLES.get());

                        output.accept(MMItems.ARANDASPIS_BUCKET.get());

                        output.accept(MMBlocks.BOREALOPELTA_EGG.get());

                        output.accept(MMItems.RAW_DAWN_HORSE.get());
                        output.accept(MMItems.COOKED_DAWN_HORSE.get());

                        output.accept(MMItems.DIPLOCAULUS_BUCKET.get());

                        output.accept(MMItems.ELEPHANT_BIRD_EGG.get());
                        output.accept(MMItems.BOILED_ELEPHANT_EGG.get());
                        output.accept(MMItems.JUMBO_OMELETTE.get());
                        output.accept(MMItems.EGG_SHELL_FRAGMENT.get());
                        output.accept(MMItems.EGG_SHELLMET.get());

                        output.accept(MMItems.EOLACTORIA_BUCKET.get());

                        output.accept(MMItems.FALCA_BUCKET.get());

                        output.accept(MMBlocks.FLUBBER_EGG.get());
                        output.accept(MMItems.BABY_FLUBBER_BUCKET.get());

                        output.accept(MMItems.HALLUCIGENIA_BUCKET.get());
                        output.accept(MMItems.HALLUCIGENIC_SLIME.get());
                        output.accept(MMItems.MAGIC_ROLL.get());

                        output.accept(MMItems.BABY_OPHTHALMO_BUCKET.get());
                        output.accept(MMItems.OPHTHALMO_ARMOR.get());

                        output.accept(MMItems.PIKAIA_BUCKET.get());

                        output.accept(MMItems.SACA_BUCKET.get());
                        output.accept(MMItems.SACABAMBASPIS.get());
                        output.accept(MMItems.COOKED_SACA.get());
                        output.accept(MMItems.GOLDEN_SACA.get());

                        output.accept(MMItems.SLOVENICUS_BUCKET.get());

                        output.accept(MMItems.STELLER_BUCKET.get());
                        output.accept(MMItems.STELLER_MILK.get());
                        output.accept(MMItems.STELLER_ICE_CREAM.get());

                        output.accept(MMItems.TRILO_BUCKET.get());
                        output.accept(MMItems.TRILO_BITE.get());

                        output.accept(MMItems.CARIS_FOSSIL.get());
                        output.accept(MMItems.ROACH_FOSSIL.get());
                        output.accept(MMItems.ARANDASPIS_FOSSIL.get());
                        output.accept(MMItems.PELTA_FOSSIL.get());
                        output.accept(MMItems.DAEODON_FOSSIL.get());
                        output.accept(MMItems.DAWN_FOSSIL.get());
                        output.accept(MMItems.DIPLOCAULUS_FOSSIL.get());
                        output.accept(MMItems.DODO_FOSSIL.get());
                        output.accept(MMItems.DOEDICURUS_FOSSIL.get());
                        output.accept(MMItems.ELEBIRD_FOSSIL.get());
                        output.accept(MMItems.FLUBBER_FOSSIL.get());
                        output.accept(MMItems.EOLACTORIA_FOSSIL.get());
                        output.accept(MMItems.FALCA_FOSSIL.get());
                        output.accept(MMItems.HALLU_FOSSIL.get());
                        output.accept(MMItems.GORGON_FOSSIL.get());
                        output.accept(MMItems.JOSEPHO_FOSSIL.get());
                        output.accept(MMItems.KELENKEN_FOSSIL.get());
                        output.accept(MMItems.LEPTI_FOSSIL.get());
                        output.accept(MMItems.LYSTRO_FOSSIL.get());
                        output.accept(MMItems.OPHTHALMO_FOSSIL.get());
                        output.accept(MMItems.PIKAIA_FOSSIL.get());
                        output.accept(MMItems.SACA_FOSSIL.get());
                        output.accept(MMItems.SLOV_FOSSIL.get());
                        output.accept(MMItems.SNIFFER_FOSSIL.get());
                        output.accept(MMItems.STELLER_FOSSIL.get());
                        output.accept(MMItems.THERI_FOSSIL.get());
                        output.accept(MMItems.THYLACINE_FOSSIL.get());
                        output.accept(MMItems.TLATO_FOSSIL.get());
                        output.accept(MMItems.TRILO_FOSSIL.get());


                        output.accept(MMItems.CARIS_SPAWN_EGG.get());
                        output.accept(MMItems.ROACH_SPAWN_EGG.get());
                        output.accept(MMItems.ARANDASPIS_SPAWN_EGG.get());
                        output.accept(MMItems.BOREALO_SPAWN_EGG.get());
                        output.accept(MMItems.DAWN_HORSE_SPAWN_EGG.get());
                        output.accept(MMItems.SKELETON_DAWN_HORSE_SPAWN_EGG.get());
                        output.accept(MMItems.ZOMBIE_DAWN_HORSE_SPAWN_EGG.get());
                        output.accept(MMItems.DIPLOCAULUS_SPAWN_EGG.get());
                        output.accept(MMItems.DODO_SPAWN_EGG.get());
                        output.accept(MMItems.DOEDICURUS_SPAWN_EGG.get());
                        output.accept(MMItems.ELE_SPAWN_EGG.get());
                        output.accept(MMItems.EOLACTORIA_SPAWN_EGG.get());
                        output.accept(MMItems.FALCA_SPAWN_EGG.get());
                        output.accept(MMItems.FLUBBER_SPAWN_EGG.get());
                        output.accept(MMItems.HALLUCIGENIA_SPAWN_EGG.get());
                        output.accept(MMItems.JOSEPHO_SPAWN_EGG.get());
                        output.accept(MMItems.LEPTICTIDIUM_SPAWN_EGG.get());
                        output.accept(MMItems.OPHTHALMO_SPAWN_EGG.get());
                        output.accept(MMItems.PIKAIA_SPAWN_EGG.get());
                        output.accept(MMItems.SACA_SPAWN_EGG.get());
                        output.accept(MMItems.SLOVENICUS_SPAWN_EGG.get());
                        output.accept(MMItems.STELLER_SPAWN_EGG.get());
                        output.accept(MMItems.THYLA_SPAWN_EGG.get());
                        output.accept(MMItems.TRILOBITE_SPAWN_EGG.get());

                        output.accept(MMItems.BEHOLDER_SPAWN_EGG.get());
                        output.accept(MMItems.CHUD_SPAWN_EGG.get());
                        output.accept(MMItems.MOLTEN_SPAWN_EGG.get());
                    })
                    .build());


    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_PLANTS =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab_plants", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(MMBlocks.SIGILLARIA_SAPLING.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab_plants"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(MMBlocks.CHARNIA.get());
                        output.accept(MMBlocks.COOKSONIA.get());
                        output.accept(MMBlocks.DICKINSONIA.get());
                        output.accept(MMBlocks.HERPETOGASTER.get());
                        output.accept(MMBlocks.WIWAXIA.get());

                        output.accept(MMBlocks.CALAMITES_LOG.get());
                        output.accept(MMBlocks.STRIPPED_CALAMITES_LOG.get());
                        output.accept(MMBlocks.CALAMITES_BARK.get());
                        output.accept(MMBlocks.STRIPPED_CALAMITES_BARK.get());

                        output.accept(MMBlocks.CALAMITES_PLANKS.get());

                        output.accept(MMBlocks.CALAMITES_BUNDLE.get());
                        output.accept(MMBlocks.STRIPPED_CALAMITES_BUNDLE.get());
                        output.accept(MMBlocks.CALAMITES_BUNDLED_BARK.get());
                        output.accept(MMBlocks.STRIPPED_CALAMITES_BUNDLED_BARK.get());

                        output.accept(MMBlocks.CALAMITES_STAIRS.get());
                        output.accept(MMBlocks.CALAMITES_SLAB.get());
                        output.accept(MMBlocks.CALAMITES_FENCE.get());
                        output.accept(MMBlocks.CALAMITES_FENCE_GATE.get());
                        output.accept(MMBlocks.CALAMITES_DOOR.get());
                        output.accept(MMBlocks.CALAMITES_TRAPDOOR.get());
                        output.accept(MMBlocks.CALAMITES_PRESSURE_PLATE.get());
                        output.accept(MMBlocks.CALAMITES_BUTTON.get());

                        output.accept(MMBlocks.CALAMITES_MOSAIC.get());
                        output.accept(MMBlocks.CALAMITES_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.CALAMITES_MOSAIC_SLAB.get());
                        output.accept(MMItems.CALAMITES_SIGN.get());
                        output.accept(MMItems.CALAMITES_HANGING_SIGN.get());
                        output.accept(MMBlocks.CALAMITES_BRANCH.get());
                        output.accept(MMBlocks.CALAMITES_SAPLING.get());

                        output.accept(MMItems.CALAMITES_BOAT.get());
                        output.accept(MMItems.CALAMITES_CHEST_BOAT.get());


                        output.accept(MMBlocks.PROTOTAXITES_PLANKS.get());

                        output.accept(MMBlocks.PROTOTAXITES_BLOCK.get());

                        output.accept(MMBlocks.PROTOTAXITES_STAIRS.get());
                        output.accept(MMBlocks.PROTOTAXITES_SLAB.get());
                        output.accept(MMBlocks.PROTOTAXITES_FENCE.get());
                        output.accept(MMBlocks.PROTOTAXITES_FENCE_GATE.get());
                        output.accept(MMBlocks.PROTOTAXITES_DOOR.get());
                        output.accept(MMBlocks.PROTOTAXITES_TRAPDOOR.get());
                        output.accept(MMBlocks.PROTOTAXITES_PRESSURE_PLATE.get());
                        output.accept(MMBlocks.PROTOTAXITES_BUTTON.get());

                        output.accept(MMBlocks.PROTOTAXITES_MOSAIC.get());
                        output.accept(MMBlocks.PROTOTAXITES_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.PROTOTAXITES_MOSAIC_SLAB.get());
                        output.accept(MMItems.PROTOTAXITES_SIGN.get());
                        output.accept(MMItems.PROTOTAXITES_HANGING_SIGN.get());

                        output.accept(MMBlocks.PROTOTAXITES.get());

                        output.accept(MMItems.PROTOTAXITES_BOAT.get());
                        output.accept(MMItems.PROTOTAXITES_CHEST_BOAT.get());



                        output.accept(MMBlocks.SIGILLARIA_PLANKS.get());

                        output.accept(MMBlocks.SIGILLARIA_STEM.get());
                        output.accept(MMBlocks.STRIPPED_SIGILLARIA_STEM.get());
                        output.accept(MMBlocks.SIGILLARIA_WOOD.get());
                        output.accept(MMBlocks.STRIPPED_SIGILLARIA_WOOD.get());

                        output.accept(MMBlocks.SIGILLARIA_STAIRS.get());
                        output.accept(MMBlocks.SIGILLARIA_SLAB.get());
                        output.accept(MMBlocks.SIGILLARIA_FENCE.get());
                        output.accept(MMBlocks.SIGILLARIA_FENCE_GATE.get());
                        output.accept(MMBlocks.SIGILLARIA_DOOR.get());
                        output.accept(MMBlocks.SIGILLARIA_TRAPDOOR.get());
                        output.accept(MMBlocks.SIGILLARIA_PRESSURE_PLATE.get());
                        output.accept(MMBlocks.SIGILLARIA_BUTTON.get());

                        output.accept(MMBlocks.SIGILLARIA_MOSAIC.get());
                        output.accept(MMBlocks.SIGILLARIA_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.SIGILLARIA_MOSAIC_SLAB.get());
                        output.accept(MMItems.SIGILLARIA_SIGN.get());
                        output.accept(MMItems.SIGILLARIA_HANGING_SIGN.get());
                        output.accept(MMBlocks.SIGILLARIA_LEAVES.get());
                        output.accept(MMBlocks.SIGILLARIA_SAPLING.get());

                        output.accept(MMItems.SIGILLARIA_BOAT.get());
                        output.accept(MMItems.SIGILLARIA_CHEST_BOAT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_MISC =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab_misc", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(MMItems.PALEONOMICON.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab_misc"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(MMItems.PALEONOMICON.get());

                        output.accept(MMItems.PALEO_TOOLKIT.get());

                        output.accept(MMBlocks.PALEO_TABLE.get());

                        output.accept(MMItems.CHRONOTITE.get());
                        output.accept(MMBlocks.CHRONOTITE.get());
                        output.accept(MMBlocks.CHRONO_ALTAR.get());
                        output.accept(MMBlocks.CHRONO_PEDESTAL.get());

                        output.accept(MMItems.OPAL.get());
                        output.accept(MMBlocks.OPAL_BLOCK.get());

                        output.accept(MMItems.CHRONO_WATCH.get());

                        output.accept(MMItems.SHALE_FOSSIL.get());
                        output.accept(MMItems.SILTSTONE_FOSSIL.get());
                        output.accept(MMItems.GABBRO_FOSSIL.get());
                        output.accept(MMItems.MUDSTONE_FOSSIL.get());
                        output.accept(MMItems.LIMESTONE_FOSSIL.get());
                        output.accept(MMItems.CHALK_FOSSIL.get());
                        output.accept(MMItems.BRECCIA_FOSSIL.get());
                        output.accept(MMItems.DIATOMITE_FOSSIL.get());
                        output.accept(MMItems.PERMAFROST_FOSSIL.get());

                        output.accept(MMBlocks.SHALE.get());
                        output.accept(MMBlocks.SHALE_SLAB.get());
                        output.accept(MMBlocks.SHALE_STAIRS.get());
                        output.accept(MMBlocks.SHALE_WALL.get());
                        output.accept(MMBlocks.POLISHED_SHALE.get());
                        output.accept(MMBlocks.POLISHED_SHALE_SLAB.get());
                        output.accept(MMBlocks.POLISHED_SHALE_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_SHALE_WALL.get());
                        output.accept(MMBlocks.SHALE_BRICKS.get());
                        output.accept(MMBlocks.SHALE_BRICKS_SLAB.get());
                        output.accept(MMBlocks.SHALE_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.SHALE_BRICKS_WALL.get());
                        output.accept(MMBlocks.SHALE_MOSAIC.get());
                        output.accept(MMBlocks.SHALE_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.SHALE_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.SHALE_MOSAIC_WALL.get());
                        output.accept(MMBlocks.SHALE_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_SHALE.get());

                        output.accept(MMBlocks.SILTSTONE.get());
                        output.accept(MMBlocks.SILTSTONE_SLAB.get());
                        output.accept(MMBlocks.SILTSTONE_STAIRS.get());
                        output.accept(MMBlocks.SILTSTONE_WALL.get());
                        output.accept(MMBlocks.POLISHED_SILTSTONE.get());
                        output.accept(MMBlocks.POLISHED_SILTSTONE_SLAB.get());
                        output.accept(MMBlocks.POLISHED_SILTSTONE_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_SILTSTONE_WALL.get());
                        output.accept(MMBlocks.SILTSTONE_BRICKS.get());
                        output.accept(MMBlocks.SILTSTONE_BRICKS_SLAB.get());
                        output.accept(MMBlocks.SILTSTONE_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.SILTSTONE_BRICKS_WALL.get());
                        output.accept(MMBlocks.SILTSTONE_MOSAIC.get());
                        output.accept(MMBlocks.SILTSTONE_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.SILTSTONE_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.SILTSTONE_MOSAIC_WALL.get());
                        output.accept(MMBlocks.SILTSTONE_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_SILTSTONE.get());

                        output.accept(MMBlocks.GABBRO.get());
                        output.accept(MMBlocks.GABBRO_SLAB.get());
                        output.accept(MMBlocks.GABBRO_STAIRS.get());
                        output.accept(MMBlocks.GABBRO_WALL.get());
                        output.accept(MMBlocks.POLISHED_GABBRO.get());
                        output.accept(MMBlocks.POLISHED_GABBRO_SLAB.get());
                        output.accept(MMBlocks.POLISHED_GABBRO_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_GABBRO_WALL.get());
                        output.accept(MMBlocks.GABBRO_BRICKS.get());
                        output.accept(MMBlocks.GABBRO_BRICKS_SLAB.get());
                        output.accept(MMBlocks.GABBRO_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.GABBRO_BRICKS_WALL.get());
                        output.accept(MMBlocks.GABBRO_MOSAIC.get());
                        output.accept(MMBlocks.GABBRO_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.GABBRO_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.GABBRO_MOSAIC_WALL.get());
                        output.accept(MMBlocks.GABBRO_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_GABBRO.get());

                        output.accept(MMBlocks.MUDSTONE.get());
                        output.accept(MMBlocks.MUDSTONE_SLAB.get());
                        output.accept(MMBlocks.MUDSTONE_STAIRS.get());
                        output.accept(MMBlocks.MUDSTONE_WALL.get());
                        output.accept(MMBlocks.POLISHED_MUDSTONE.get());
                        output.accept(MMBlocks.POLISHED_MUDSTONE_SLAB.get());
                        output.accept(MMBlocks.POLISHED_MUDSTONE_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_MUDSTONE_WALL.get());
                        output.accept(MMBlocks.MUDSTONE_BRICKS.get());
                        output.accept(MMBlocks.MUDSTONE_BRICKS_SLAB.get());
                        output.accept(MMBlocks.MUDSTONE_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.MUDSTONE_BRICKS_WALL.get());
                        output.accept(MMBlocks.MUDSTONE_MOSAIC.get());
                        output.accept(MMBlocks.MUDSTONE_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.MUDSTONE_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.MUDSTONE_MOSAIC_WALL.get());
                        output.accept(MMBlocks.MUDSTONE_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_MUDSTONE.get());

                        output.accept(MMBlocks.LIMESTONE.get());
                        output.accept(MMBlocks.LIMESTONE_SLAB.get());
                        output.accept(MMBlocks.LIMESTONE_STAIRS.get());
                        output.accept(MMBlocks.LIMESTONE_WALL.get());
                        output.accept(MMBlocks.POLISHED_LIMESTONE.get());
                        output.accept(MMBlocks.POLISHED_LIMESTONE_SLAB.get());
                        output.accept(MMBlocks.POLISHED_LIMESTONE_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_LIMESTONE_WALL.get());
                        output.accept(MMBlocks.LIMESTONE_BRICKS.get());
                        output.accept(MMBlocks.LIMESTONE_BRICKS_SLAB.get());
                        output.accept(MMBlocks.LIMESTONE_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.LIMESTONE_BRICKS_WALL.get());
                        output.accept(MMBlocks.LIMESTONE_MOSAIC.get());
                        output.accept(MMBlocks.LIMESTONE_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.LIMESTONE_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.LIMESTONE_MOSAIC_WALL.get());
                        output.accept(MMBlocks.LIMESTONE_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_LIMESTONE.get());

                        output.accept(MMBlocks.CHALK.get());
                        output.accept(MMBlocks.CHALK_SLAB.get());
                        output.accept(MMBlocks.CHALK_STAIRS.get());
                        output.accept(MMBlocks.CHALK_WALL.get());
                        output.accept(MMBlocks.POLISHED_CHALK.get());
                        output.accept(MMBlocks.POLISHED_CHALK_SLAB.get());
                        output.accept(MMBlocks.POLISHED_CHALK_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_CHALK_WALL.get());
                        output.accept(MMBlocks.CHALK_BRICKS.get());
                        output.accept(MMBlocks.CHALK_BRICKS_SLAB.get());
                        output.accept(MMBlocks.CHALK_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.CHALK_BRICKS_WALL.get());
                        output.accept(MMBlocks.CHALK_MOSAIC.get());
                        output.accept(MMBlocks.CHALK_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.CHALK_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.CHALK_MOSAIC_WALL.get());
                        output.accept(MMBlocks.CHALK_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_CHALK.get());

                        output.accept(MMBlocks.BRECCIA.get());
                        output.accept(MMBlocks.BRECCIA_SLAB.get());
                        output.accept(MMBlocks.BRECCIA_STAIRS.get());
                        output.accept(MMBlocks.BRECCIA_WALL.get());
                        output.accept(MMBlocks.POLISHED_BRECCIA.get());
                        output.accept(MMBlocks.POLISHED_BRECCIA_SLAB.get());
                        output.accept(MMBlocks.POLISHED_BRECCIA_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_BRECCIA_WALL.get());
                        output.accept(MMBlocks.BRECCIA_BRICKS.get());
                        output.accept(MMBlocks.BRECCIA_BRICKS_SLAB.get());
                        output.accept(MMBlocks.BRECCIA_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.BRECCIA_BRICKS_WALL.get());
                        output.accept(MMBlocks.BRECCIA_MOSAIC.get());
                        output.accept(MMBlocks.BRECCIA_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.BRECCIA_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.BRECCIA_MOSAIC_WALL.get());
                        output.accept(MMBlocks.BRECCIA_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_BRECCIA.get());

                        output.accept(MMBlocks.DIATOMITE.get());
                        output.accept(MMBlocks.DIATOMITE_SLAB.get());
                        output.accept(MMBlocks.DIATOMITE_STAIRS.get());
                        output.accept(MMBlocks.DIATOMITE_WALL.get());
                        output.accept(MMBlocks.POLISHED_DIATOMITE.get());
                        output.accept(MMBlocks.POLISHED_DIATOMITE_SLAB.get());
                        output.accept(MMBlocks.POLISHED_DIATOMITE_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_DIATOMITE_WALL.get());
                        output.accept(MMBlocks.DIATOMITE_BRICKS.get());
                        output.accept(MMBlocks.DIATOMITE_BRICKS_SLAB.get());
                        output.accept(MMBlocks.DIATOMITE_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.DIATOMITE_BRICKS_WALL.get());
                        output.accept(MMBlocks.DIATOMITE_MOSAIC.get());
                        output.accept(MMBlocks.DIATOMITE_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.DIATOMITE_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.DIATOMITE_MOSAIC_WALL.get());
                        output.accept(MMBlocks.DIATOMITE_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_DIATOMITE.get());

                        output.accept(MMBlocks.PERMAFROST.get());
                        output.accept(MMBlocks.PERMAFROST_SLAB.get());
                        output.accept(MMBlocks.PERMAFROST_STAIRS.get());
                        output.accept(MMBlocks.PERMAFROST_WALL.get());
                        output.accept(MMBlocks.POLISHED_PERMAFROST.get());
                        output.accept(MMBlocks.POLISHED_PERMAFROST_SLAB.get());
                        output.accept(MMBlocks.POLISHED_PERMAFROST_STAIRS.get());
                        output.accept(MMBlocks.POLISHED_PERMAFROST_WALL.get());
                        output.accept(MMBlocks.PERMAFROST_BRICKS.get());
                        output.accept(MMBlocks.PERMAFROST_BRICKS_SLAB.get());
                        output.accept(MMBlocks.PERMAFROST_BRICKS_STAIRS.get());
                        output.accept(MMBlocks.PERMAFROST_BRICKS_WALL.get());
                        output.accept(MMBlocks.PERMAFROST_MOSAIC.get());
                        output.accept(MMBlocks.PERMAFROST_MOSAIC_SLAB.get());
                        output.accept(MMBlocks.PERMAFROST_MOSAIC_STAIRS.get());
                        output.accept(MMBlocks.PERMAFROST_MOSAIC_WALL.get());
                        output.accept(MMBlocks.PERMAFROST_FOSSIL_BLOCK.get());
                        output.accept(MMBlocks.CHISELED_PERMAFROST.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
