package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;

import java.util.Random;

public class AraucarioxylonFoliagePlacer extends FoliagePlacer {
    public static final Codec<AraucarioxylonFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68698_) -> {
        return foliagePlacerParts(p_68698_).and(IntProvider.codec(0, 24).fieldOf("height").forGetter((p_161500_) -> {
            return p_161500_.height;
        })).apply(p_68698_, AraucarioxylonFoliagePlacer::new);
    });
    private final IntProvider height;

    public AraucarioxylonFoliagePlacer(IntProvider pRadius, IntProvider pOffset, IntProvider pHeight) {
        super(pRadius, pOffset);
        this.height = pHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.ARAUCARIOXYLON_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {

        int i = 0;

        for(int j = pOffset; j >= pOffset - pFoliageHeight; --j) {
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos(), i, j, pAttachment.doubleTrunk());
            if (i >= 1 && j == pOffset - pFoliageHeight + 1) {
                --i;
            } else if (i < pFoliageRadius + pAttachment.radiusOffset()) {
                ++i;
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height.sample(pRandom);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return pLocalX == pRange && pLocalZ == pRange && pRange > 0;
    }
}
