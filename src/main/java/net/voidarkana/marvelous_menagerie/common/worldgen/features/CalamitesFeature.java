package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.CalamitesBranchBlock;
import net.voidarkana.marvelous_menagerie.common.worldgen.util.CalamitesFeatureConfiguration;

public class CalamitesFeature extends Feature<CalamitesFeatureConfiguration> {

    public CalamitesFeature(Codec<CalamitesFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    protected int getCalamitesHeight(RandomSource pRandom, CalamitesFeatureConfiguration pConfig) {
        return pRandom.nextInt(pConfig.minHeight, pConfig.maxHeight+1);
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, CalamitesFeatureConfiguration pConfig) {
        int i = pPos.getY();
        if (i >= pLevel.getMinBuildHeight() + 1 && i + pMaxHeight + 1 < pLevel.getMaxBuildHeight()) {
                return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<CalamitesFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        CalamitesFeatureConfiguration calamitesFeatureConfiguration = pContext.config();
        int i = this.getCalamitesHeight(randomsource, calamitesFeatureConfiguration);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, i, blockpos$mutableblockpos, calamitesFeatureConfiguration)) {
            return false;
        } else {
            this.placeBranches(worldgenlevel, randomsource, blockpos, calamitesFeatureConfiguration, i, blockpos$mutableblockpos, pContext);
            this.placeStem(worldgenlevel, randomsource, blockpos, calamitesFeatureConfiguration, i, blockpos$mutableblockpos, pContext);
            return true;
        }
    }

    protected void placeBranches(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos,
                             CalamitesFeatureConfiguration pConfig, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos,
                             FeaturePlaceContext<CalamitesFeatureConfiguration> pContext) {

        int midSectionStart = 4;
        int midSectionEnd = pMaxHeight - 3;
        int midSectionLength = pMaxHeight - 6;
        int midSectionPortionSmall = (int) Math.floor((double) midSectionLength /3);
        int midSectionPortionBigStart = 3 + midSectionPortionSmall;
        int midSectionPortionBigEnd = 3 - midSectionPortionSmall;

        for(int currentHeight = 1; currentHeight <= pMaxHeight; ++currentHeight) {
            if (!(currentHeight <= 2 || currentHeight == pMaxHeight)){

                int radiusDiagonal;
                int radius;

                if (currentHeight==3 || currentHeight == pMaxHeight-2){
                    radiusDiagonal = 1;
                }else if (currentHeight == pMaxHeight-1){
                    radiusDiagonal = 0;
                }else{
                    if (currentHeight >= midSectionPortionBigStart && currentHeight <= midSectionPortionBigEnd){
                        radiusDiagonal = 3;
                    }else {
                        radiusDiagonal = 2;
                    }
                }

                radius = radiusDiagonal+1;

                for (int x = -radius; x <= radius; x++){
                    for (int z = -radius; z <= radius; z++){
                        if ((x == 0 && z != 0) || (z == 0 && x != 0)){
                            pMutablePos.setWithOffset(pPos, x, currentHeight-1, z);
                            BlockState blockstate = pConfig.branchProvider.getState(pRandom, pPos);
                            if (x==0){
                                this.setBlock(pLevel, pMutablePos, blockstate);
                            }
                            if (z==0){
                                this.setBlock(pLevel, pMutablePos, blockstate.setValue(CalamitesBranchBlock.ROTATION_8, 2));
                            }
                        }
                    }
                }

                for (int x = -radiusDiagonal; x <= radiusDiagonal; x++){
                    for (int z = -radiusDiagonal; z <= radiusDiagonal; z++){
                        if ((Math.abs(x) == Math.abs(z) && z != 0)){
                            pMutablePos.setWithOffset(pPos, x, currentHeight-1, z);
                            BlockState blockstate = pConfig.branchProvider.getState(pRandom, pPos);
                            if ((x<0 && z<0) || (x>0 && z>0)){
                                this.setBlock(pLevel, pMutablePos, blockstate.setValue(CalamitesBranchBlock.ROTATION_8, 7));
                            }
                            if ((x<0 && z>0) || (x>0 && z<0)){
                                this.setBlock(pLevel, pMutablePos, blockstate.setValue(CalamitesBranchBlock.ROTATION_8, 1));
                            }

                        }
                    }
                }

            }

        }
    }

    protected void placeStem(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos,
                             CalamitesFeatureConfiguration pConfig, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos,
                             FeaturePlaceContext<CalamitesFeatureConfiguration> pContext) {

        for(int currentHeight = 1; currentHeight <= pMaxHeight; ++currentHeight) {

            pMutablePos.setWithOffset(pPos, 0, currentHeight-1, 0);
            BlockState blockstate = pConfig.stemProvider.getState(pRandom, pPos);
            this.setBlock(pLevel, pMutablePos, blockstate);

        }

    }

}
