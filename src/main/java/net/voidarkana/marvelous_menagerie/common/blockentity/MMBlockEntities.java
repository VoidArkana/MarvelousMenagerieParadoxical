package net.voidarkana.marvelous_menagerie.common.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.AltarBlockEntity;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PedestalBlockEntity;

public class MMBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MarvelousMenagerie.MODID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL_ENTITY =
            BLOCK_ENTITIES.register("pedestal_entity", () ->
                    BlockEntityType.Builder.of(PedestalBlockEntity::new,
                            MMBlocks.CHRONO_PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<AltarBlockEntity>> ALTAR_ENTITY =
            BLOCK_ENTITIES.register("altar_entity", () ->
                    BlockEntityType.Builder.of(AltarBlockEntity::new,
                            MMBlocks.CHRONO_ALTAR.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
