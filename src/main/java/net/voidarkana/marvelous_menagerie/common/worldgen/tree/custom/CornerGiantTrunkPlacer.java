package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.voidarkana.marvelous_menagerie.common.block.custom.GiantLogCorner;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CornerGiantTrunkPlacer extends TrunkPlacer {
    public static final Codec<CornerGiantTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70189_) -> {
        return trunkPlacerParts(p_70189_).apply(p_70189_, CornerGiantTrunkPlacer::new);
    });

    public CornerGiantTrunkPlacer(int p_70165_, int p_70166_, int p_70167_) {
        super(p_70165_, p_70166_, p_70167_);
    }

    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.MEGA_ARAUCARIOXYLON_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        BlockPos blockpos = pPos.below();
//        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos, pConfig);
//        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.east(), pConfig);
//        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south(), pConfig);
//        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().east(), pConfig);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        if (pConfig instanceof HugeTreeConfiguration configuration)
            for(int i = 0; i < pFreeTreeHeight; ++i) {

                this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom,
                        blockpos$mutableblockpos, configuration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.TOP_LEFT),
                        pPos, 0, i, 0);

                if (i < pFreeTreeHeight - 1) {

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom,
                            blockpos$mutableblockpos, configuration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.TOP_RIGHT),
                            pPos, 1, i, 0);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom,
                            blockpos$mutableblockpos, configuration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.BOTTOM_RIGHT),
                            pPos, 1, i, 1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom,
                            blockpos$mutableblockpos, configuration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.BOTTOM_LEFT),
                            pPos, 0, i, 1);

            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight), 0, true));
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, BlockPos.MutableBlockPos pPos, BlockState pState, BlockPos pOffsetPos, int pOffsetX, int pOffsetY, int pOffsetZ) {
        pPos.setWithOffset(pOffsetPos, pOffsetX, pOffsetY, pOffsetZ);

        if (this.isFree(pLevel, pPos)) {
            this.placeLog(pLevel, pBlockSetter, pRandom, pPos, pState, Function.identity());
        }
    }

    protected boolean placeLog(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, BlockPos pPos, BlockState state, Function<BlockState, BlockState> pPropertySetter) {
        if (this.validTreePos(pLevel, pPos)) {
            pBlockSetter.accept(pPos, pPropertySetter.apply(state));
            return true;
        } else {
            return false;
        }
    }
}
