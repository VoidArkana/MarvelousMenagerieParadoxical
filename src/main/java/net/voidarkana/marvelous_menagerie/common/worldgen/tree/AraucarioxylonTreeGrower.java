package net.voidarkana.marvelous_menagerie.common.worldgen.tree;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.AbstractHugeOrMegaTreeGrower;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.AbstractHugeTreeGrower;
import org.jetbrains.annotations.Nullable;

public class AraucarioxylonTreeGrower extends AbstractHugeOrMegaTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return ModConfiguredFeatures.ARAUCARIOXYLON_KEY;
    }

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom) {
        return ModConfiguredFeatures.ARAUCARIOXYLON_MEGA;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredHugeFeature(RandomSource pRandom) {
        return ModConfiguredFeatures.ARAUCARIOXYLON_HUGE;
    }
}
