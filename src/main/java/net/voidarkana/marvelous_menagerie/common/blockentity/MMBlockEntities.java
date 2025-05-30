package net.voidarkana.marvelous_menagerie.common.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.*;

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

    public static final RegistryObject<BlockEntityType<PaleontologyTableBlockEntity>> PALEO_TABLE_ENTITY =
            BLOCK_ENTITIES.register("paleo_table_entity", () ->
                    BlockEntityType.Builder.of(PaleontologyTableBlockEntity::new,
                            MMBlocks.PALEO_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register( "mod_sign", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            MMBlocks.PROTOTAXITES_SIGN.get(),
                            MMBlocks.PROTOTAXITES_WALL_SIGN.get(),
                            MMBlocks.SIGILLARIA_SIGN.get(),
                            MMBlocks.SIGILLARIA_WALL_SIGN.get(),
                            MMBlocks.CALAMITES_SIGN.get(),
                            MMBlocks.CALAMITES_WALL_SIGN.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register( "mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            MMBlocks.PROTOTAXITES_HANGING_SIGN.get(),
                            MMBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),
                            MMBlocks.SIGILLARIA_HANGING_SIGN.get(),
                            MMBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                            MMBlocks.CALAMITES_HANGING_SIGN.get(),
                            MMBlocks.CALAMITES_WALL_HANGING_SIGN.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<CharniaBlockEntity>> CHARNIA_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("charnia", () ->
                    BlockEntityType.Builder.of(CharniaBlockEntity::new,
                            MMBlocks.CHARNIA.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
