package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import java.util.concurrent.CompletableFuture;

public class MMBiomeTagGenerator extends BiomeTagsProvider {
    public MMBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MarvelousMenagerie.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.addTags();
    }

    protected void addTags() {
        tag(MMTags.Biomes.DODO_BIOMES).addTag(BiomeTags.IS_JUNGLE).add(Biomes.MUSHROOM_FIELDS);
        tag(MMTags.Biomes.ELEPHANT_BIRD_BIOMES).addTag(BiomeTags.IS_JUNGLE);
        tag(MMTags.Biomes.THYLACINE_BIOMES).addTag(BiomeTags.IS_SAVANNA).add(Biomes.PLAINS).add(Biomes.SUNFLOWER_PLAINS);
        tag(MMTags.Biomes.STELLER_BIOMES).add(Biomes.COLD_OCEAN).add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.FROZEN_OCEAN).add(Biomes.DEEP_FROZEN_OCEAN);

        tag(MMTags.Biomes.CARIS_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.TRILO_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.OPHTHALMO_BIOMES).add(Biomes.WARM_OCEAN).add(Biomes.LUKEWARM_OCEAN).add(Biomes.DEEP_LUKEWARM_OCEAN);
        tag(MMTags.Biomes.SACA_BIOMES).addTag(BiomeTags.IS_OCEAN);
        tag(MMTags.Biomes.JOSEPHO_BIOMES).addTag(BiomeTags.IS_JUNGLE).addTag(Tags.Biomes.IS_SWAMP);

        tag(MMTags.Biomes.PIKAIA_BIOMES).add(Biomes.WARM_OCEAN);

        tag(MMTags.Biomes.EOLACTORIA_BIOMES).add(Biomes.LUKEWARM_OCEAN);
        tag(MMTags.Biomes.SLOVENICUS_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.FALCATUS_BIOMES).add(Biomes.SWAMP, Biomes.MANGROVE_SWAMP);

        tag(MMTags.Biomes.ARANDASPIS_BIOMES).addTag(BiomeTags.IS_OCEAN);
        tag(MMTags.Biomes.HALLU_BIOMES).add(Biomes.WARM_OCEAN);

        tag(MMTags.Biomes.PERMAFROST_BIOMES).addTag(BiomeTags.SPAWNS_SNOW_FOXES)
                .addTag(BiomeTags.HAS_IGLOO).addTag(BiomeTags.HAS_VILLAGE_SNOWY).addTag(Tags.Biomes.IS_SNOWY);

        tag(MMTags.Biomes.ROACH_BIOMES).addTag(BiomeTags.IS_SAVANNA).add(Biomes.DRIPSTONE_CAVES);

        tag(MMTags.Biomes.DAWN_HORSE_BIOMES).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_FOREST);

        tag(MMTags.Biomes.LEPTI_BIOMES).addTag(BiomeTags.IS_FOREST);

        tag(MMTags.Biomes.DOEDICURUS_BIOMES).addTag(BiomeTags.HAS_VILLAGE_PLAINS).addTag(BiomeTags.IS_SAVANNA);
    }
}
