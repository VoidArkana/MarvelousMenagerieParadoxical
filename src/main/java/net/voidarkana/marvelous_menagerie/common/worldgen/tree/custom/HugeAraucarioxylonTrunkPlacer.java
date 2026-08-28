package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.voidarkana.marvelous_menagerie.common.block.custom.GiantLogCorner;
import net.voidarkana.marvelous_menagerie.common.block.custom.GiantLogSide;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModTrunkPlacerTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class HugeAraucarioxylonTrunkPlacer extends TrunkPlacer {

    public static final Codec<HugeAraucarioxylonTrunkPlacer> CODEC = RecordCodecBuilder.create((p_68698_) -> {
        return trunkPlacerParts(p_68698_).and(
                PlacedFeature.CODEC.fieldOf("moss_feature").forGetter((p_204867_) -> {
                    return p_204867_.moss_feature;
                })
        ).apply(p_68698_, HugeAraucarioxylonTrunkPlacer::new);
    });
    public final Holder<PlacedFeature> moss_feature;

    public HugeAraucarioxylonTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB, Holder<PlacedFeature> pMoss_feature) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
        this.moss_feature = pMoss_feature;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.HUGE_ARAUCARIOXYLON_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        return this.placeTrunk(pLevel, pBlockSetter, pRandom, pFreeTreeHeight, pPos, pConfig);
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos,
            BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, @Nullable FeaturePlaceContext<HugeTreeConfiguration> pContext) {

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        if (pConfig instanceof HugeTreeConfiguration hugeTreeConfiguration){

            int sideSize1 = pRandom.nextInt(5, Math.max(7, (int)(pFreeTreeHeight*0.4f)));
            int sideSize2 = pRandom.nextInt(5, Math.max(7, (int)(pFreeTreeHeight*0.4f)));
            int sideSize3 = pRandom.nextInt(5, Math.max(7, (int)(pFreeTreeHeight*0.4f)));
            int sideSize4 = pRandom.nextInt(5, Math.max(7, (int)(pFreeTreeHeight*0.4f)));
            for(int size = 0; size < pFreeTreeHeight; ++size) {

                if (size < pFreeTreeHeight-3){

                    if (size == 0){
                        for (int i = -3; i < 4; i++){
                            for (int j = -3; j < 4; j++){
                                int absI = Math.abs(i);
                                int absJ = Math.abs(j);
                                if (((absI == 3 && absJ==0) || (absI == 0 && absJ==3)) ||
                                        (absI == 2 && absJ == 1) || (absI == 1 && absJ == 2)||
                                        (absI == 0 && absJ == 2) || (absI == 2 && absJ == 0)){
                                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                            hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                            pPos, i, size, j);

                                    if (pContext != null && pRandom.nextBoolean())
                                        this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(i+(i>0 ? 1 : -1), size, j+(j>0 ? 1 : -1)));

                                }
                            }
                        }
                    }

                    if (size < 3){
                        for (int i = -2; i < 3; i++){
                            for (int j = -2; j < 3; j++){
                                int absI = Math.abs(i);
                                int absJ = Math.abs(j);
                                if ((absI == 2 && absJ == 1) || (absI == 1 && absJ == 2)||
                                                (absI == 0 && absJ == 2) || (absI == 2 && absJ == 0)){
                                    if (size == 1 || pRandom.nextBoolean()){
                                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                                hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                                pPos, i, size, j);

                                        if (pContext != null && pRandom.nextBoolean())
                                            this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(i+(i>0 ? 1 : -1), size, j+(j>0 ? 1 : -1)));

                                    }
                                }
                            }
                        }
                    }

                    if (size <= sideSize1){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                pPos, 0, size, 2);

                        if (pContext != null && pRandom.nextInt(3)==0)
                            this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(0, size, 3));

                    }
                    if (size <= sideSize2){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                pPos, 2, size, 0);

                        if (pContext != null && pRandom.nextInt(3)==0)
                            this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(3, size, 0));
                    }

                    if (size <= sideSize3){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                pPos, 0, size, -2);


                        if (pContext != null && pRandom.nextInt(3)==0)
                            this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(0, size, -3));
                    }
                    if (size <= sideSize4){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                                hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos),
                                pPos, -2, size, 0);


                        if (pContext != null && pRandom.nextInt(3)==0)
                            this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, pPos.offset(-3, size, 0));
                    }

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig.trunkProvider.getState(pRandom, pPos), pPos, 0, size, 0);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.sideTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogSide.TYPE, GiantLogSide.SideType.RIGHT),
                            pPos, 1, size, 0);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.sideTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogSide.TYPE, GiantLogSide.SideType.BOTTOM),
                            pPos, 0, size, 1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.sideTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogSide.TYPE, GiantLogSide.SideType.LEFT),
                            pPos, -1, size, 0);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.sideTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogSide.TYPE, GiantLogSide.SideType.TOP),
                            pPos, 0, size, -1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.BOTTOM_RIGHT),
                            pPos, 1, size, 1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.TOP_LEFT),
                            pPos, -1, size, -1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.TOP_RIGHT),
                            pPos, 1, size, -1);

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos,
                            hugeTreeConfiguration.cornerTrunkProvider.getState(pRandom, pPos).trySetValue(GiantLogCorner.TYPE, GiantLogCorner.CornerType.BOTTOM_LEFT),
                            pPos, -1, size, 1);

                    if (pContext != null && size < pFreeTreeHeight/2){
                        for (int x = -2; x < 3; x++){
                            for (int z = -2; z < 3; z++){
                                if (!(x == 0 && z == 0)){
                                    this.moss_feature.value().place(pContext.level(), pContext.chunkGenerator(), pRandom, blockpos$mutableblockpos.offset(x, size, z));
                                }
                            }
                        }
                    }

                }else{
                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, hugeTreeConfiguration.normalTrunkProvider.getState(pRandom, pPos), pPos, 0, size, 0);
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight+3), 0, false));
    }

    private void placeLogIfFreeWithOffsetAndBlockstate(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState>
            pBlockSetter, RandomSource pRandom, BlockPos.MutableBlockPos pPos, HugeTreeConfiguration pConfig,
                                                       BlockPos pOffsetPos, int pOffsetX, int pOffsetY, int pOffsetZ,
                                                       Function<BlockState, BlockState> pPropertySetter) {
        pPos.setWithOffset(pOffsetPos, pOffsetX, pOffsetY, pOffsetZ);
        this.placeLog(pLevel, pBlockSetter, pRandom, pPos, pConfig, pPropertySetter);
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                          RandomSource pRandom, BlockPos.MutableBlockPos pPos, BlockState state,
                                          BlockPos pOffsetPos, int pOffsetX, int pOffsetY, int pOffsetZ) {

        pPos.setWithOffset(pOffsetPos, pOffsetX, pOffsetY, pOffsetZ);

        if (this.isFree(pLevel, pPos)) {
            this.placeLog(pLevel, pBlockSetter, pRandom, pPos, state, Function.identity());
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
