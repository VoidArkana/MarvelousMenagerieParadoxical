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
        tag(MMTags.Biomes.DODO_BIOMES).addTags(BiomeTags.IS_JUNGLE).add(Biomes.MUSHROOM_FIELDS);
        tag(MMTags.Biomes.ELEPHANT_BIRD_BIOMES).addTags(BiomeTags.IS_JUNGLE);
        tag(MMTags.Biomes.THYLACINE_BIOMES).addTags(BiomeTags.IS_SAVANNA).add(Biomes.PLAINS).add(Biomes.SUNFLOWER_PLAINS);
        tag(MMTags.Biomes.STELLER_BIOMES).add(Biomes.COLD_OCEAN).add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.FROZEN_OCEAN).add(Biomes.DEEP_FROZEN_OCEAN);

        tag(MMTags.Biomes.CARIS_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.TRILO_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.OPHTHALMO_BIOMES).add(Biomes.WARM_OCEAN).add(Biomes.LUKEWARM_OCEAN).add(Biomes.DEEP_LUKEWARM_OCEAN);
        tag(MMTags.Biomes.SACA_BIOMES).addTags(BiomeTags.IS_OCEAN);
        tag(MMTags.Biomes.JOSEPHO_BIOMES).addTags(BiomeTags.IS_JUNGLE).addTags(Tags.Biomes.IS_SWAMP);

        tag(MMTags.Biomes.PIKAIA_BIOMES).add(Biomes.WARM_OCEAN);

        tag(MMTags.Biomes.EOLACTORIA_BIOMES).add(Biomes.LUKEWARM_OCEAN);
        tag(MMTags.Biomes.SLOVENICUS_BIOMES).add(Biomes.WARM_OCEAN);
        tag(MMTags.Biomes.FALCATUS_BIOMES).add(Biomes.SWAMP, Biomes.MANGROVE_SWAMP);

        tag(MMTags.Biomes.ARANDASPIS_BIOMES).addTags(BiomeTags.IS_OCEAN);
        tag(MMTags.Biomes.HALLU_BIOMES).add(Biomes.WARM_OCEAN);

        tag(MMTags.Biomes.PERMAFROST_BIOMES).addTags(BiomeTags.SPAWNS_SNOW_FOXES)
                .addTags(BiomeTags.HAS_IGLOO).addTags(BiomeTags.HAS_VILLAGE_SNOWY).addTags(Tags.Biomes.IS_SNOWY);

        tag(MMTags.Biomes.ROACH_BIOMES).addTags(BiomeTags.IS_SAVANNA).add(Biomes.DRIPSTONE_CAVES);

        tag(MMTags.Biomes.DAWN_HORSE_BIOMES).addTags(BiomeTags.IS_TAIGA).addTags(BiomeTags.IS_FOREST);
    }
}
