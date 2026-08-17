package net.voidarkana.marvelous_menagerie.common.worldgen;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviderType;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.worldgen.features.*;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.*;
import net.voidarkana.marvelous_menagerie.common.worldgen.util.CalamitesFeatureConfiguration;
import net.voidarkana.marvelous_menagerie.common.worldgen.util.HugePrototaxitesFeatureConfiguration;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<Feature<HugePrototaxitesFeatureConfiguration>> PROTOTAXITES_FEATURE =
            register_feature("prototaxites_feature", () -> new HugePrototaxitesFeature(HugePrototaxitesFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<CalamitesFeatureConfiguration>> CALAMITES_FEATURE =
            register_feature("calamites_feature", () -> new CalamitesFeature(CalamitesFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OTOZAMITES_FEATURE =
            register_feature("otozamites_feature", () -> new OtozamitesFeature(NoneFeatureConfiguration.CODEC));

    public static final ResourceKey<ConfiguredFeature<?, ?>> MESOZOIL_PATCH = registerKey("mesozoil_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MESOZOIL_DECORATION = registerKey("mesozoil_decoration");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MESOZOIL_VEGETATION_BONEMEAL = registerKey("mesozoil_vegetation_bonemeal");
    //plants
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_KEY = registerKey("sigillaria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_HUGE = registerKey("sigillaria_huge");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PROTOTAXITES_KEY = registerKey("prototaxites");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CALAMITES_KEY = registerKey("calamites");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OTOZAMITES_KEY = registerKey("otozamites");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIOXYLON_KEY =  registerKey("araucarioxlyon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIOXYLON_MEGA = registerKey("araucarioxlyon_mega");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARAUCARIOXYLON_HUGE = registerKey("araucarioxlyon_huge");

    //fossils
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHALE_KEY = registerKey("shale");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHALE_FOSSIL_KEY = registerKey("shale_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILTSTONE_KEY = registerKey("siltstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILTSTONE_FOSSIL_KEY = registerKey("siltstone_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GABBRO_KEY = registerKey("gabbro");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GABBRO_FOSSIL_KEY = registerKey("gabbro_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MUDSTONE_KEY = registerKey("mudstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUDSTONE_FOSSIL_KEY = registerKey("mudstone_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_KEY = registerKey("limestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_FOSSIL_KEY = registerKey("limestone_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CHALK_KEY = registerKey("chalk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHALK_FOSSIL_KEY = registerKey("chalk_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BRECCIA_KEY = registerKey("breccia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRECCIA_FOSSIL_KEY = registerKey("breccia_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DIATOMITE_KEY = registerKey("diatomite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIATOMITE_FOSSIL_KEY = registerKey("diatomite_fossil");

    //public static final ResourceKey<ConfiguredFeature<?, ?>> PERMAFROST_KEY = registerKey("permafrost");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EXTRA_PERMAFROST_KEY = registerKey("extra_permafrost");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERMAFROST_FOSSIL_KEY = registerKey("permafrost_fossil");


    public static final RegistryObject<Feature<HugeTreeConfiguration>> HUGE_TREE =
            register_feature("huge_tree", () -> new HugeTreeFeature(HugeTreeConfiguration.CODEC));

    public static final RegistryObject<Feature<VegetationPatchConfiguration>> VEGETATION_CIRCLES_FEATURE =
            register_feature("vegetation_circles_feature", () -> new VegetationCirclesFeatures(VegetationPatchConfiguration.CODEC));



    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest dirtReplaceables = new TagMatchTest(BlockTags.DIRT);
        RuleTest iceReplaceables = new TagMatchTest(BlockTags.ICE);

        RuleTest shaleReplaceables = new BlockMatchTest(MMBlocks.SHALE.get());
        RuleTest siltstoneReplaceables = new BlockMatchTest(MMBlocks.SILTSTONE.get());
        RuleTest gabbroReplaceables = new BlockMatchTest(MMBlocks.GABBRO.get());
        RuleTest mudstoneReplaceables = new BlockMatchTest(MMBlocks.MUDSTONE.get());
        RuleTest limestoneReplaceables = new BlockMatchTest(MMBlocks.LIMESTONE.get());
        RuleTest chalkReplaceables = new BlockMatchTest(MMBlocks.CHALK.get());
        RuleTest brecciaReplaceables = new BlockMatchTest(MMBlocks.BRECCIA.get());
        RuleTest diatomiteReplaceables = new BlockMatchTest(MMBlocks.DIATOMITE.get());
        RuleTest permafrostReplaceables = new BlockMatchTest(MMBlocks.PERMAFROST.get());

        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SIGILLARIA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MMBlocks.SIGILLARIA_STEM.get()),
                new SigillariaTrunkPlacer(7, 1, 1),
                BlockStateProvider.simple(MMBlocks.SIGILLARIA_LEAVES.get()),
                new SigillariaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, SIGILLARIA_HUGE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MMBlocks.SIGILLARIA_STEM.get()),
                new HugeSigillariaTrunkPlacer(20, 1, 1),
                BlockStateProvider.simple(MMBlocks.SIGILLARIA_LEAVES.get()),
                new HugeSigillariaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 5),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, PROTOTAXITES_KEY, ModConfiguredFeatures.PROTOTAXITES_FEATURE.get(), new HugePrototaxitesFeatureConfiguration(
                BlockStateProvider.simple(MMBlocks.PROTOTAXITES_BLOCK.get()), 3));

        register(context, CALAMITES_KEY, ModConfiguredFeatures.CALAMITES_FEATURE.get(), new CalamitesFeatureConfiguration(
                BlockStateProvider.simple(MMBlocks.CALAMITES_LOG.get()), BlockStateProvider.simple(MMBlocks.CALAMITES_BRANCH.get()),12, 15));

        register(context, OTOZAMITES_KEY, ModConfiguredFeatures.OTOZAMITES_FEATURE.get(), new NoneFeatureConfiguration());

        //soil

        WeightedStateProvider mesozoilDecoWSP = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(MMBlocks.FERN_SPROUTS.get().defaultBlockState(), 3)
                .add(Blocks.FERN.defaultBlockState(), 2)
                .add(Blocks.LARGE_FERN.defaultBlockState(), 1));

        register(context, MESOZOIL_DECORATION, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(mesozoilDecoWSP));

        WeightedStateProvider mesozoilWSP = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(MMBlocks.MESOZOIL.get().defaultBlockState(), 8)
                .add(MMBlocks.SPARCE_MOSSY_MESOZOIL.get().defaultBlockState(), 6)
                .add(MMBlocks.MOSSY_MESOZOIL.get().defaultBlockState(), 4)
                .add(MMBlocks.MESOZOIC_PODZOL.get().defaultBlockState(), 2));

        register(context, MESOZOIL_PATCH, VEGETATION_CIRCLES_FEATURE.get(),
                new VegetationPatchConfiguration(BlockTags.DIRT,
                        mesozoilWSP,
                        PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MESOZOIL_DECORATION)),
                        CaveSurface.FLOOR,
                        ConstantInt.of(2),
                        0.5f,
                        3,
                        0.15f,
                        UniformInt.of(2, 5),
                        0.15F));

        register(context, MESOZOIL_VEGETATION_BONEMEAL, Feature.NETHER_FOREST_VEGETATION,
                new NetherForestVegetationConfig(mesozoilWSP, 6, 2));

        register(context, ARAUCARIOXYLON_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LOG.get()),
                new StraightTrunkPlacer(8, 1, 4),
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LEAVES.get()),
                new AraucarioxylonFoliagePlacer(UniformInt.of(1, 3), UniformInt.of(0, 1),
                        UniformInt.of(3, 6)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, ARAUCARIOXYLON_MEGA, HUGE_TREE.get(), new HugeTreeConfiguration.HugeTreeConfigurationBuilder(
                BlockStateProvider.simple(MMBlocks.STRIPPED_ARAUCARIOXYLON_LOG.get()),
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LOG.get()),
                BlockStateProvider.simple(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_CORNER.get()),
                BlockStateProvider.simple(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_SIDE.get()),
                new CornerGiantTrunkPlacer(13, 2, 12),
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LEAVES.get()),
                new MegaAraucarioxylonFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0),
                        UniformInt.of(8, 12)),
                new TwoLayersFeatureSize(1, 1, 2),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MESOZOIL_PATCH)),
                UniformInt.of(1, 5))
                .build());

        register(context, ARAUCARIOXYLON_HUGE, HUGE_TREE.get(), new HugeTreeConfiguration.HugeTreeConfigurationBuilder(
                BlockStateProvider.simple(MMBlocks.STRIPPED_ARAUCARIOXYLON_LOG.get()),
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LOG.get()),
                BlockStateProvider.simple(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_CORNER.get()),
                BlockStateProvider.simple(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_SIDE.get()),
                new HugeAraucarioxylonTrunkPlacer(20, 1, 12),
                BlockStateProvider.simple(MMBlocks.ARAUCARIOXYLON_LEAVES.get()),
                new HugeAraucarioxylonFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0),
                        UniformInt.of(15, 20)),
                new TwoLayersFeatureSize(2, 0, 2),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MESOZOIL_PATCH)),
                UniformInt.of(2, 7))
                .ignoreVines().build());
        
        //fossils
        register(context, SHALE_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables,
                MMBlocks.SHALE.get().defaultBlockState(), 64));

        register(context, SHALE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(shaleReplaceables,
                MMBlocks.SHALE_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, SILTSTONE_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables,
                MMBlocks.SILTSTONE.get().defaultBlockState(), 64));

        register(context, SILTSTONE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(siltstoneReplaceables,
                MMBlocks.SILTSTONE_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, GABBRO_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables,
                MMBlocks.GABBRO.get().defaultBlockState(), 64));

        register(context, GABBRO_FOSSIL_KEY, Feature.ORE, new OreConfiguration(gabbroReplaceables,
                MMBlocks.GABBRO_FOSSIL_BLOCK.get().defaultBlockState(), 15));


        register(context, MUDSTONE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.MUDSTONE.get().defaultBlockState(), 64));

        register(context, MUDSTONE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(mudstoneReplaceables,
                MMBlocks.MUDSTONE_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, LIMESTONE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.LIMESTONE.get().defaultBlockState(), 64));

        register(context, LIMESTONE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(limestoneReplaceables,
                MMBlocks.LIMESTONE_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, CHALK_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.CHALK.get().defaultBlockState(), 64));

        register(context, CHALK_FOSSIL_KEY, Feature.ORE, new OreConfiguration(chalkReplaceables,
                MMBlocks.CHALK_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, BRECCIA_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.BRECCIA.get().defaultBlockState(), 64));

        register(context, BRECCIA_FOSSIL_KEY, Feature.ORE, new OreConfiguration(brecciaReplaceables,
                MMBlocks.BRECCIA_FOSSIL_BLOCK.get().defaultBlockState(), 15));

        register(context, DIATOMITE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.DIATOMITE.get().defaultBlockState(), 64));

        register(context, DIATOMITE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(diatomiteReplaceables,
                MMBlocks.DIATOMITE_FOSSIL_BLOCK.get().defaultBlockState(), 15));


        List<OreConfiguration.TargetBlockState> extraPermafrost = List.of(OreConfiguration.target(stoneReplaceables,
                        MMBlocks.PERMAFROST.get().defaultBlockState()),
                OreConfiguration.target(dirtReplaceables, MMBlocks.PERMAFROST.get().defaultBlockState()),
                OreConfiguration.target(iceReplaceables, MMBlocks.PERMAFROST.get().defaultBlockState()));


//        register(context, PERMAFROST_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
//                MMBlocks.PERMAFROST.get().defaultBlockState(), 64));

        register(context, EXTRA_PERMAFROST_KEY, Feature.ORE, new OreConfiguration(extraPermafrost, 64));

        register(context, PERMAFROST_FOSSIL_KEY, Feature.ORE, new OreConfiguration(permafrostReplaceables,
                MMBlocks.PERMAFROST_FOSSIL_BLOCK.get().defaultBlockState(), 15));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static <T extends FeatureConfiguration> RegistryObject<Feature<T>> register_feature(String name, Supplier<Feature<T>> featureSupplier) {
        return MOD_FEATURES.register(name, featureSupplier);
    }

    public static void register(IEventBus eventBus){
        MOD_FEATURES.register(eventBus);
    }

}
