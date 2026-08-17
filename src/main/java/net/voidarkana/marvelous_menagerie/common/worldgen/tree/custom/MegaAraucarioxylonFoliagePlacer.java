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
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;

import java.util.Random;

public class MegaAraucarioxylonFoliagePlacer extends FoliagePlacer {
    public static final Codec<MegaAraucarioxylonFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68698_) -> {
        return foliagePlacerParts(p_68698_).and(IntProvider.codec(0, 24).fieldOf("height").forGetter((p_161500_) -> {
            return p_161500_.height;
        })).apply(p_68698_, MegaAraucarioxylonFoliagePlacer::new);
    });
    private final IntProvider height;

    public MegaAraucarioxylonFoliagePlacer(IntProvider pRadius, IntProvider pOffset, IntProvider pHeight) {
        super(pRadius, pOffset);
        this.height = pHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.MEGA_ARAUCARIOXYLON_FOLIAGE_PLACER.get();
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

            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, new BlockPos(blockpos.getX(), j, blockpos.getZ()), i1, 0, pAttachment.doubleTrunk());
            i = l;
        }
    }

    @Override
    public int foliageRadius(RandomSource pRandom, int pRadius) {
        return super.foliageRadius(pRandom, pRadius);
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height.sample(pRandom);
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
