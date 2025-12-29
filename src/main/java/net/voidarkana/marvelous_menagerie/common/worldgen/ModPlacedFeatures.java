package net.voidarkana.marvelous_menagerie.common.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SHALE_VEIN_PLACED_KEY = registerKey("shale_placed");
    public static final ResourceKey<PlacedFeature> SHALE_FOSSIL_PLACED_KEY = registerKey("shale_fossil_placed");

    public static final ResourceKey<PlacedFeature> SILTSTONE_VEIN_PLACED_KEY = registerKey("siltstone_placed");
    public static final ResourceKey<PlacedFeature> SILTSTONE_FOSSIL_PLACED_KEY = registerKey("siltstone_fossil_placed");

    public static final ResourceKey<PlacedFeature> GABBRO_VEIN_PLACED_KEY = registerKey("gabbro_placed");
    public static final ResourceKey<PlacedFeature> GABBRO_FOSSIL_PLACED_KEY = registerKey("gabbro_fossil_placed");

    public static final ResourceKey<PlacedFeature> MUDSTONE_VEIN_PLACED_KEY = registerKey("mudstone_placed");
    public static final ResourceKey<PlacedFeature> MUDSTONE_FOSSIL_PLACED_KEY = registerKey("mudstone_fossil_placed");

    public static final ResourceKey<PlacedFeature> LIMESTONE_VEIN_PLACED_KEY = registerKey("limestone_placed");
    public static final ResourceKey<PlacedFeature> LIMESTONE_FOSSIL_PLACED_KEY = registerKey("limestone_fossil_placed");

    public static final ResourceKey<PlacedFeature> CHALK_VEIN_PLACED_KEY = registerKey("chalk_placed");
    public static final ResourceKey<PlacedFeature> CHALK_FOSSIL_PLACED_KEY = registerKey("chalk_fossil_placed");

    public static final ResourceKey<PlacedFeature> BRECCIA_VEIN_PLACED_KEY = registerKey("breccia_placed");
    public static final ResourceKey<PlacedFeature> BRECCIA_FOSSIL_PLACED_KEY = registerKey("breccia_fossil_placed");

    public static final ResourceKey<PlacedFeature> DIATOMITE_VEIN_PLACED_KEY = registerKey("diatomite_placed");
    public static final ResourceKey<PlacedFeature> DIATOMITE_FOSSIL_PLACED_KEY = registerKey("diatomite_fossil_placed");

//    public static final ResourceKey<PlacedFeature> PERMAFROST_VEIN_PLACED_KEY = registerKey("permafrost_placed");
    public static final ResourceKey<PlacedFeature> EXTRA_PERMAFROST_VEIN_PLACED_KEY = registerKey("extra_permafrost_placed");
    public static final ResourceKey<PlacedFeature> PERMAFROST_FOSSIL_PLACED_KEY = registerKey("permafrost_fossil_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SHALE_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SHALE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(-34))));

        register(context, SHALE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SHALE_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(-34))));



        register(context, SILTSTONE_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILTSTONE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-44),
                        VerticalAnchor.absolute(-25))));

        register(context, SILTSTONE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILTSTONE_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(-44),
                        VerticalAnchor.absolute(-25))));



        register(context, GABBRO_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GABBRO_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-25),
                        VerticalAnchor.absolute(5))));

        register(context, GABBRO_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GABBRO_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(-25),
                        VerticalAnchor.absolute(5))));



        register(context, MUDSTONE_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MUDSTONE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-5),
                        VerticalAnchor.absolute(25))));

        register(context, MUDSTONE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MUDSTONE_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(-5),
                        VerticalAnchor.absolute(25))));



        register(context, LIMESTONE_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIMESTONE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(20),
                        VerticalAnchor.absolute(45))));

        register(context, LIMESTONE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIMESTONE_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(20),
                        VerticalAnchor.absolute(45))));



        register(context, CHALK_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHALK_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(40),
                        VerticalAnchor.absolute(65))));

        register(context, CHALK_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHALK_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(40),
                        VerticalAnchor.absolute(65))));



        register(context, BRECCIA_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRECCIA_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(50),
                        VerticalAnchor.absolute(85))));

        register(context, BRECCIA_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BRECCIA_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(50),
                        VerticalAnchor.absolute(75))));



        register(context, DIATOMITE_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DIATOMITE_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(70),
                        VerticalAnchor.absolute(100))));

        register(context, DIATOMITE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DIATOMITE_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(70),
                        VerticalAnchor.absolute(100))));



//        register(context, PERMAFROST_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERMAFROST_KEY),
//                ModOrePlacement.rareOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(120),
//                        VerticalAnchor.absolute(320))));

        register(context, EXTRA_PERMAFROST_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EXTRA_PERMAFROST_KEY),
                ModOrePlacement.rareOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(80),
                        VerticalAnchor.absolute(150))));

        register(context, PERMAFROST_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERMAFROST_FOSSIL_KEY),
                ModOrePlacement.commonOrePlacement(75, HeightRangePlacement.uniform(VerticalAnchor.absolute(80),
                        VerticalAnchor.absolute(150))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
