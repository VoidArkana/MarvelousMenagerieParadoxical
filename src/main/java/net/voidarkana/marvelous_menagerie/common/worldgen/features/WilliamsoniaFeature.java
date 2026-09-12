package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.BranchingTrunkBlock;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.WilliamsoniaLeaves;

public class WilliamsoniaFeature extends Feature<NoneFeatureConfiguration> {

    public WilliamsoniaFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    protected int getProtoHeight(RandomSource pRandom) {
        return pRandom.nextInt(2, 6);
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight) {
        int i = pPos.getY();
        return i >= pLevel.getMinBuildHeight() + 1 && i + pMaxHeight + 1 < pLevel.getMaxBuildHeight();
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        int i = this.getProtoHeight(randomsource);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (this.isValidPosition(worldgenlevel, blockpos, i)) {
            this.placeStem(worldgenlevel, randomsource, blockpos, i, blockpos$mutableblockpos, pContext);
            return true;
        }
        return false;
    }

    protected void placeStem(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, FeaturePlaceContext<NoneFeatureConfiguration> pContext) {

        for(int currentHeight = 0; currentHeight <= pMaxHeight; ++currentHeight) {

            BlockState trunkState = MMBlocks.WILLIAMSONIA_PLANT.get().defaultBlockState();
            BlockState leavesState = MMBlocks.WILLIAMSONIA_LEAVES.get().defaultBlockState();

            if (currentHeight < pMaxHeight && currentHeight>0 && pRandom.nextFloat() > 0.25) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
                if (direction.getAxis() == Direction.Axis.Y){
                    direction = Direction.NORTH;
                }

                if (pRandom.nextBoolean()){
                    pMutablePos.setWithOffset(pPos, direction.getStepX(), currentHeight, direction.getStepZ());
                    this.setBlock(pLevel, pMutablePos, trunkState.trySetValue(BranchingTrunkBlock.AXIS, direction.getAxis()));

                    pMutablePos.setWithOffset(pPos, direction.getStepX(), currentHeight+1, direction.getStepZ());
                    this.setBlock(pLevel, pMutablePos, leavesState);
                }else{
                    pMutablePos.setWithOffset(pPos, direction.getStepX(), currentHeight, direction.getStepZ());
                    this.setBlock(pLevel, pMutablePos, leavesState.trySetValue(WilliamsoniaLeaves.ATTACHMENT_STYLE, WilliamsoniaLeaves.WilliamsoniaAttachFace.WALL)
                            .trySetValue(WilliamsoniaLeaves.FACING, direction));
                }
            }

            pMutablePos.setWithOffset(pPos, 0, currentHeight, 0);
            this.setBlock(pLevel, pMutablePos, trunkState.trySetValue(BranchingTrunkBlock.DOWN, true)
                    .trySetValue(BranchingTrunkBlock.UP, true));

            if (currentHeight == pMaxHeight){
                pMutablePos.setWithOffset(pPos, 0, currentHeight+1, 0);
                this.setBlock(pLevel, pMutablePos, leavesState);
            }
        }
    }
}
