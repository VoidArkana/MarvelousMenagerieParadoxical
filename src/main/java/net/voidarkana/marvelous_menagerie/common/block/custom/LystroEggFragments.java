package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

public class LystroEggFragments extends Block {
    public LystroEggFragments(Properties pProperties) {
        super(pProperties);
    }

    public VoxelShape getShape(BlockState p_273399_, BlockGetter p_273568_, BlockPos p_273314_, CollisionContext p_273274_) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState groundstate = pLevel.getBlockState(pPos.below());
        if (groundstate.is(BlockTags.DIRT)){

            if (groundstate.is(Blocks.COARSE_DIRT)) {
                BlockState blockstate;
                blockstate = Blocks.DIRT.defaultBlockState();
                pLevel.setBlock(pPos.below(), blockstate, 3);
                pLevel.gameEvent(GameEvent.BLOCK_PLACE, pPos.below(), GameEvent.Context.of(blockstate));

            }else if (groundstate.getBlock() instanceof BonemealableBlock) {
                BonemealableBlock bonemealableblock = (BonemealableBlock)groundstate.getBlock();
                if (bonemealableblock.isValidBonemealTarget(pLevel, pPos, groundstate, pLevel.isClientSide)) {
                    if (pLevel instanceof ServerLevel) {
                        if (bonemealableblock.isBonemealSuccess(pLevel, pLevel.random, pPos, groundstate)) {
                            bonemealableblock.performBonemeal(pLevel, pLevel.random, pPos, groundstate);
                        }
                    }
                }
            }
            pLevel.playSound(null, pPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);

            pLevel.removeBlock(pPos, false);
            pLevel.gameEvent(GameEvent.BLOCK_DESTROY, pPos, GameEvent.Context.of(pState));
        }
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }

    protected boolean mayPlaceOn(BlockState p_154539_, BlockGetter p_154540_, BlockPos p_154541_) {
        return p_154539_.isFaceSturdy(p_154540_, p_154541_, Direction.UP);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
