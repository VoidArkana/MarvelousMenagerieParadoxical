package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;

public class ModHangingSignBlockEntity extends SignBlockEntity {

    public ModHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MMBlockEntities.MOD_HANGING_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return MMBlockEntities.MOD_HANGING_SIGN.get();
    }
}
