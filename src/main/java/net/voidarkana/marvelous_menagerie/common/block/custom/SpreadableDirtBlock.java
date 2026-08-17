package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadableDirtBlock extends Block implements BonemealableBlock {
    final TagKey<Block> spreadable;

    public SpreadableDirtBlock(BlockBehaviour.Properties p_54995_, TagKey<Block> pSpreadable) {
        super(p_54995_);
        this.spreadable = pSpreadable;
    }

    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        if (!pLevel.getBlockState(pPos.above()).propagatesSkylightDown(pLevel, pPos)) {
            return false;
        } else {
            for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
                if (pLevel.getBlockState(blockpos).is(spreadable)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean isBonemealSuccess(Level p_221816_, RandomSource p_221817_, BlockPos p_221818_, BlockState p_221819_) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-1, -1, -1), pPos.offset(1, 1, 1))) {
            if (pLevel.getBlockState(blockpos).is(spreadable)) {
                pLevel.setBlock(pPos, pLevel.getBlockState(blockpos), 3);
            }
        }
    }
}
