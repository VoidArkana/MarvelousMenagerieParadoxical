package net.voidarkana.marvelous_menagerie.common.worldgen.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.stream.Stream;

public class CalamitesFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<CalamitesFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((CalamitesFeatureConfiguration) -> CalamitesFeatureConfiguration.stemProvider),
                    BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter((CalamitesFeatureConfiguration) -> CalamitesFeatureConfiguration.branchProvider),
                    Codec.intRange(0, 16).fieldOf("minHeight").orElse(8).forGetter((CalamitesFeatureConfiguration) -> CalamitesFeatureConfiguration.minHeight),
                    Codec.intRange(0, 16).fieldOf("maxHeight").orElse(16).forGetter((CalamitesFeatureConfiguration) -> CalamitesFeatureConfiguration.maxHeight))
            .apply(instance, CalamitesFeatureConfiguration::new));

    public final BlockStateProvider stemProvider;
    public final BlockStateProvider branchProvider;
    public int minHeight;
    public int maxHeight;

    public CalamitesFeatureConfiguration(BlockStateProvider stemProvider, BlockStateProvider branchProvider, int minHeight, int maxHeight) {
        this.stemProvider = stemProvider;
        this.branchProvider = branchProvider;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> getFeatures() {
        return FeatureConfiguration.super.getFeatures();
    }

}
