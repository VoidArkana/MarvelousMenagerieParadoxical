package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;

public class CharniaBlockEntity extends BlockEntity {

    public CharniaBlockEntity(BlockPos pos, BlockState state) {
        super(MMBlockEntities.CHARNIA_BLOCK_ENTITY.get(), pos, state);
    }

}
