package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;

import java.util.Random;

public class HugeAraucarioxylonFoliagePlacer extends FoliagePlacer {

    public static final Codec<HugeAraucarioxylonFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68664_) -> {
        return foliagePlacerParts(p_68664_).and(IntProvider.codec(0, 24).fieldOf("crown_height").forGetter((p_161484_) -> {
            return p_161484_.crownHeight;
        })).apply(p_68664_, HugeAraucarioxylonFoliagePlacer::new);
    });
    private final IntProvider crownHeight;

    public HugeAraucarioxylonFoliagePlacer(IntProvider pRadius, IntProvider pOffset, IntProvider height) {
        super(pRadius, pOffset);
        this.crownHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.HUGE_ARAUCARIOXYLON_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        BlockPos blockpos = pAttachment.pos();
        int i = 0;

        for(int j = blockpos.getY() - pFoliageHeight + pOffset; j <= blockpos.getY() + pOffset; ++j) {
            int k = blockpos.getY() - j;
            int l = pFoliageRadius + pAttachment.radiusOffset() + Mth.floor((float)k / (float)pFoliageHeight * 3.5F);
            int i1;
            if (k > 0 && l == i && (j & 1) == 0) {
                i1 = l + 1;
            } else {
                i1 = l;
            }

            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, new BlockPos(blockpos.getX(), j, blockpos.getZ()), i1, 0, false);
            i = l;
        }
    }

    @Override
    public int foliageRadius(RandomSource pRandom, int pRadius) {
        return super.foliageRadius(pRandom, pRadius) + pRandom.nextInt(1)+1;
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.crownHeight.sample(pRandom);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        if (pLocalX + pLocalZ >= 7) {
            return true;
        } else {
            return pLocalX * pLocalX + pLocalZ * pLocalZ > pRange * pRange;
        }
    }
}
