package net.voidarkana.marvelous_menagerie.common.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.worldgen.features.HugePrototaxitesFeature;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeSigillariaFoliagePlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeSigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.SigillariaFoliagePlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.SigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.util.HugePrototaxitesFeatureConfiguration;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MarvelousMenagerie.MODID);

    public static final RegistryObject<Feature<HugePrototaxitesFeatureConfiguration>> PROTOTAXITES_FEATURE =
            register_feature("prototaxites_feature", () -> new HugePrototaxitesFeature(HugePrototaxitesFeatureConfiguration.CODEC));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_KEY = registerKey("sigillaria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_HUGE = registerKey("sigillaria_huge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PROTOTAXITES_KEY = registerKey("prototaxites");

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

    public static final ResourceKey<ConfiguredFeature<?, ?>> PERMAFROST_KEY = registerKey("permafrost");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EXTRA_PERMAFROST_KEY = registerKey("extra_permafrost");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIRT_PERMAFROST_KEY = registerKey("dirt_permafrost");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PERMAFROST_FOSSIL_KEY = registerKey("permafrost_fossil");



    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest dirtReplaceables = new TagMatchTest(BlockTags.DIRT);

        RuleTest shaleReplaceables = new BlockMatchTest(MMBlocks.SHALE.get());
        RuleTest siltstoneReplaceables = new BlockMatchTest(MMBlocks.SILTSTONE.get());
        RuleTest gabbroReplaceables = new BlockMatchTest(MMBlocks.GABBRO.get());
        RuleTest mudstoneReplaceables = new BlockMatchTest(MMBlocks.MUDSTONE.get());
        RuleTest limestoneReplaceables = new BlockMatchTest(MMBlocks.LIMESTONE.get());
        RuleTest chalkReplaceables = new BlockMatchTest(MMBlocks.CHALK.get());
        RuleTest brecciaReplaceables = new BlockMatchTest(MMBlocks.BRECCIA.get());
        RuleTest diatomiteReplaceables = new BlockMatchTest(MMBlocks.DIATOMITE.get());
        RuleTest permafrostReplaceables = new BlockMatchTest(MMBlocks.PERMAFROST.get());

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


        register(context, PERMAFROST_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.PERMAFROST.get().defaultBlockState(), 64));

        register(context, EXTRA_PERMAFROST_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables,
                MMBlocks.PERMAFROST.get().defaultBlockState(), 64));


        register(context, DIRT_PERMAFROST_KEY, Feature.ORE, new OreConfiguration(dirtReplaceables,
                MMBlocks.PERMAFROST.get().defaultBlockState(), 64));

        register(context, PERMAFROST_FOSSIL_KEY, Feature.ORE, new OreConfiguration(permafrostReplaceables,
                MMBlocks.PERMAFROST_FOSSIL_BLOCK.get().defaultBlockState(), 15));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MarvelousMenagerie.MODID, name));
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
