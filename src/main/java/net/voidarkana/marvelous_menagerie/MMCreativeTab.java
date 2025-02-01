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

public class MMCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarvelousMenagerie.MODID);
    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_TAB =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(MMItems.SHALE_FOSSIL.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(MMItems.PALEO_TOOLKIT.get());

                        output.accept(MMBlocks.PALEO_TABLE.get());

                        output.accept(MMItems.CHRONOTITE.get());
                        output.accept(MMBlocks.CHRONOTITE.get());
                        output.accept(MMBlocks.CHRONO_ALTAR.get());
                        output.accept(MMBlocks.CHRONO_PEDESTAL.get());

                        output.accept(MMItems.SHALE_FOSSIL.get());
                        output.accept(MMItems.SILTSTONE_FOSSIL.get());
                        output.accept(MMItems.GABBRO_FOSSIL.get());
                        output.accept(MMItems.MUDSTONE_FOSSIL.get());
                        output.accept(MMItems.LIMESTONE_FOSSIL.get());
                        output.accept(MMItems.CHALK_FOSSIL.get());
                        output.accept(MMItems.BRECCIA_FOSSIL.get());
                        output.accept(MMItems.DIATOMITE_FOSSIL.get());
                        output.accept(MMItems.PERMAFROST_FOSSIL.get());

                        output.accept(MMItems.ARANDASPIS_FOSSIL.get());
                        output.accept(MMItems.ARANDASPIS_BUCKET.get());

                        output.accept(MMItems.SACA_FOSSIL.get());
                        output.accept(MMItems.SACA_BUCKET.get());
                        output.accept(MMItems.SACABAMBASPIS.get());
                        output.accept(MMItems.COOKED_SACA.get());
                        output.accept(MMItems.GOLDEN_SACA.get());

                        output.accept(MMItems.SNIFFER_FOSSIL.get());


                        output.accept(MMItems.ARANDASPIS_SPAWN_EGG.get());
                        output.accept(MMItems.CHUD_SPAWN_EGG.get());
                        output.accept(MMItems.SACA_SPAWN_EGG.get());

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
