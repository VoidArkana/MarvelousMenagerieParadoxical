package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class HugeTreeConfiguration extends TreeConfiguration {
    public static final Codec<HugeTreeConfiguration> CODEC = RecordCodecBuilder.create((p_225468_) -> {
        return p_225468_.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_161248_) -> {
            return p_161248_.trunkProvider;
        }), BlockStateProvider.CODEC.fieldOf("normal_trunk_provider").forGetter((p_161248_) -> {
            return p_161248_.normalTrunkProvider;
        }), BlockStateProvider.CODEC.fieldOf("corner_trunk_provider").forGetter((p_161244_) -> {
            return p_161244_.cornerTrunkProvider;
        }), BlockStateProvider.CODEC.fieldOf("side_trunk_provider").forGetter((p_161244_) -> {
            return p_161244_.sideTrunkProvider;
        }), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((p_161246_) -> {
            return p_161246_.trunkPlacer;
        }), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((p_161244_) -> {
            return p_161244_.foliageProvider;
        }), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((p_191357_) -> {
            return p_191357_.foliagePlacer;
        }), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter((p_225478_) -> {
            return p_225478_.rootPlacer;
        }), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((p_225476_) -> {
            return p_225476_.dirtProvider;
        }), FeatureSize.CODEC.fieldOf("minimum_size").forGetter((p_225474_) -> {
            return p_225474_.minimumSize;
        }), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_225472_) -> {
            return p_225472_.decorators;
        }), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((p_161232_) -> {
            return p_161232_.ignoreVines;
        }), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((p_225470_) -> {
            return p_225470_.forceDirt;
        }), PlacedFeature.CODEC.fieldOf("ground_feature").forGetter((p_204867_) -> {
            return p_204867_.ground_feature;
        }), IntProvider.CODEC.fieldOf("xz_radius").forGetter((p_161308_) -> {
            return p_161308_.xzRadius;
        })).apply(p_225468_, HugeTreeConfiguration::new);
    });

    public final BlockStateProvider normalTrunkProvider;
    public final BlockStateProvider cornerTrunkProvider;
    public final BlockStateProvider sideTrunkProvider;
    public final Holder<PlacedFeature> ground_feature;
    public final IntProvider xzRadius;

    protected HugeTreeConfiguration(BlockStateProvider logCenter,
                                    BlockStateProvider logNormal,
                                    BlockStateProvider logCorner,
                                    BlockStateProvider logSide,
                                    TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider,
                                    FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer,
                                    BlockStateProvider dirtProvider, FeatureSize minSize,
                                    List<TreeDecorator> decorators, boolean pIgnoreVines, boolean pForceDirt,
                                    Holder<PlacedFeature> featureHolder, IntProvider pXZRadius) {
        super(logCenter, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, dirtProvider, minSize, decorators, pIgnoreVines, pForceDirt);
        this.normalTrunkProvider = logNormal;
        this.cornerTrunkProvider = logCorner;
        this.sideTrunkProvider = logSide;
        this.ground_feature = featureHolder;
        this.xzRadius = pXZRadius;
    }

    public static class HugeTreeConfigurationBuilder {
        public final BlockStateProvider trunkProvider;
        public final BlockStateProvider normalTrunkProvider;
        public final BlockStateProvider cornerTrunkProvider;
        public final BlockStateProvider sideTrunkProvider;
        private final TrunkPlacer trunkPlacer;
        public final BlockStateProvider foliageProvider;
        private final FoliagePlacer foliagePlacer;
        private final Optional<RootPlacer> rootPlacer;
        private BlockStateProvider dirtProvider;
        private final FeatureSize minimumSize;
        private List<TreeDecorator> decorators = ImmutableList.of();
        private boolean ignoreVines;
        private boolean forceDirt;
        public Holder<PlacedFeature> groundFeature;
        public IntProvider xzRadius;

        public HugeTreeConfigurationBuilder(BlockStateProvider pTrunkProvider,
                                            BlockStateProvider pNormalTrunkProvider,
                                            BlockStateProvider pCornerTrunkProvider,
                                            BlockStateProvider pSideTrunkProvider,
                                            TrunkPlacer pTrunkPlacer, BlockStateProvider pFoliageProvider,
                                            FoliagePlacer pFoliagePlacer, Optional<RootPlacer> pRootPlacer,
                                            FeatureSize pMinimumSize,
                                            Holder<PlacedFeature> featureHolder,
                                            IntProvider pRadius) {
            this.trunkProvider = pTrunkProvider;
            this.normalTrunkProvider = pNormalTrunkProvider;
            this.cornerTrunkProvider = pCornerTrunkProvider;
            this.sideTrunkProvider = pSideTrunkProvider;
            this.trunkPlacer = pTrunkPlacer;
            this.foliageProvider = pFoliageProvider;
            this.dirtProvider = BlockStateProvider.simple(Blocks.DIRT);
            this.foliagePlacer = pFoliagePlacer;
            this.rootPlacer = pRootPlacer;
            this.minimumSize = pMinimumSize;
            this.groundFeature = featureHolder;
            this.xzRadius = pRadius;
        }

        public HugeTreeConfigurationBuilder(BlockStateProvider pTrunkProvider,
                                            BlockStateProvider pNormalTrunkProvider,
                                            BlockStateProvider pCornerTrunkProvider,
                                            BlockStateProvider pSideTrunkProvider,
                                            TrunkPlacer pTrunkPlacer,
                                            BlockStateProvider pFoliageProvider,
                                            FoliagePlacer pFoliagePlacer,
                                            FeatureSize pMinimumSize,
                                            Holder<PlacedFeature> featureHolder,
                                            IntProvider pRadius) {
            this(pTrunkProvider, pNormalTrunkProvider, pCornerTrunkProvider, pSideTrunkProvider, pTrunkPlacer, pFoliageProvider, pFoliagePlacer, Optional.empty(), pMinimumSize, featureHolder, pRadius);
        }

        public HugeTreeConfigurationBuilder dirt(BlockStateProvider pDirtProvider) {
            this.dirtProvider = pDirtProvider;
            return this;
        }

        public HugeTreeConfigurationBuilder groundFeature(Holder<PlacedFeature> pFeature) {
            this.groundFeature = pFeature;
            return this;
        }

        public HugeTreeConfigurationBuilder decorators(List<TreeDecorator> pDecorators) {
            this.decorators = pDecorators;
            return this;
        }

        public HugeTreeConfigurationBuilder ignoreVines() {
            this.ignoreVines = true;
            return this;
        }

        public HugeTreeConfigurationBuilder forceDirt() {
            this.forceDirt = true;
            return this;
        }

        public HugeTreeConfiguration build() {
            return new HugeTreeConfiguration(this.trunkProvider,this.normalTrunkProvider, this.cornerTrunkProvider, this.sideTrunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt, this.groundFeature, this.xzRadius);
        }
    }
}
