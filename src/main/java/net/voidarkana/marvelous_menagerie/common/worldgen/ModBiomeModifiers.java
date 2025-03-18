package net.voidarkana.marvelous_menagerie.common.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_SHALE_VEIN = registerKey("add_shale_vein");
    public static final ResourceKey<BiomeModifier> ADD_SHALE_FOSSIL = registerKey("add_shale_fossil");

    public static final ResourceKey<BiomeModifier> ADD_SILTSTONE_VEIN = registerKey("add_siltstone_vein");
    public static final ResourceKey<BiomeModifier> ADD_SILTSTONE_FOSSIL = registerKey("add_siltstone_fossil");

    public static final ResourceKey<BiomeModifier> ADD_GABBRO_VEIN = registerKey("add_gabbro_vein");
    public static final ResourceKey<BiomeModifier> ADD_GABBRO_FOSSIL = registerKey("add_gabbro_fossil");

    public static final ResourceKey<BiomeModifier> ADD_MUDSTONE_VEIN = registerKey("add_mudstone_vein");
    public static final ResourceKey<BiomeModifier> ADD_MUDSTONE_FOSSIL = registerKey("add_mudstone_fossil");

    public static final ResourceKey<BiomeModifier> ADD_LIMESTONE_VEIN = registerKey("add_limestone_vein");
    public static final ResourceKey<BiomeModifier> ADD_LIMESTONE_FOSSIL = registerKey("add_limestone_fossil");

    public static final ResourceKey<BiomeModifier> ADD_CHALK_VEIN = registerKey("add_chalk_vein");
    public static final ResourceKey<BiomeModifier> ADD_CHALK_FOSSIL = registerKey("add_chalk_fossil");

    public static final ResourceKey<BiomeModifier> ADD_BRECCIA_VEIN = registerKey("add_breccia_vein");
    public static final ResourceKey<BiomeModifier> ADD_BRECCIA_FOSSIL = registerKey("add_breccia_fossil");

    public static final ResourceKey<BiomeModifier> ADD_DIATOMITE_VEIN = registerKey("add_diatomite_vein");
    public static final ResourceKey<BiomeModifier> ADD_DIATOMITE_FOSSIL = registerKey("add_diatomite_fossil");

    public static final ResourceKey<BiomeModifier> ADD_PERMAFROST_VEIN = registerKey("add_permafrost_vein");
    public static final ResourceKey<BiomeModifier> EXTRA_ADD_PERMAFROST_VEIN = registerKey("extra_add_permafrost_vein");
    public static final ResourceKey<BiomeModifier> DIRT_ADD_PERMAFROST_VEIN = registerKey("dirt_add_permafrost_vein");
    public static final ResourceKey<BiomeModifier> ADD_PERMAFROST_FOSSIL = registerKey("add_permafrost_fossil");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SHALE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SHALE_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SHALE_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SHALE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_SILTSTONE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SILTSTONE_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_SILTSTONE_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SILTSTONE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_GABBRO_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GABBRO_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_GABBRO_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GABBRO_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_MUDSTONE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MUDSTONE_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_MUDSTONE_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MUDSTONE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_LIMESTONE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LIMESTONE_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_LIMESTONE_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LIMESTONE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_CHALK_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHALK_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_CHALK_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHALK_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_BRECCIA_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BRECCIA_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_BRECCIA_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BRECCIA_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_DIATOMITE_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DIATOMITE_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_DIATOMITE_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DIATOMITE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));



        context.register(ADD_PERMAFROST_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PERMAFROST_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(EXTRA_ADD_PERMAFROST_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EXTRA_PERMAFROST_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(DIRT_ADD_PERMAFROST_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DIRT_PERMAFROST_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PERMAFROST_FOSSIL, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PERMAFROST_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(MarvelousMenagerie.MODID, name));
    }
}
