package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.OtozamitesBush;

public class OtozamitesFeature extends Feature<NoneFeatureConfiguration> {

    public OtozamitesFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos) {
        int i = pPos.getY();
        return i >= pLevel.getMinBuildHeight() + 1 && i + 2 + 1 < pLevel.getMaxBuildHeight();
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        if (!this.isValidPosition(worldgenlevel, blockpos)) {
            return false;
        } else {
            this.placeBush(worldgenlevel, blockpos);
            return true;
        }
    }

    protected void placeBush(LevelAccessor pLevel, BlockPos pPos) {

        BlockState blockstate = MMBlocks.OTOZAMITES_BUSH.get().defaultBlockState();
        this.setBlock(pLevel, pPos, blockstate.setValue(OtozamitesBush.AGE, 1));

        this.setBlock(pLevel, pPos.above(), blockstate.setValue(OtozamitesBush.AGE, 2));

        Direction direction = Direction.WEST;
        for (int x = -1; x <= 1; x++){
            for (int z = -1; z <= 1; z++){
                if (!(x==0 && z==0)){
                    if ((x==0 || z==0)) {
                        Direction direction2 = Direction.fromDelta(x, 0, z);
                        this.setBlock(pLevel, pPos.offset(x, 1, z), blockstate.setValue(OtozamitesBush.AGE, 3).setValue(OtozamitesBush.FACING, direction2));
                    }else {
                        if (x==1 && z == 1){
                        direction = direction.getCounterClockWise();
                        direction = direction.getCounterClockWise();
                        }
                        this.setBlock(pLevel, pPos.offset(x, 1, z), blockstate.setValue(OtozamitesBush.AGE, 4).setValue(OtozamitesBush.FACING, direction));

                        direction = direction.getCounterClockWise();

                        if (z>-1){
                            direction = direction.getCounterClockWise();

                        }

                    }
                }
            }
        }
    }
}
