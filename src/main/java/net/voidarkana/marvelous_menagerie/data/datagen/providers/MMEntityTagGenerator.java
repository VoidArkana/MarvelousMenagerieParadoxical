package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class MMEntityTagGenerator extends EntityTypeTagsProvider {

    public MMEntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(MMTags.EntityTypes.THYLA_ALERT_TARGET)
                .add(EntityType.HOGLIN).add(EntityType.WITHER)
                .add(EntityType.ENDER_DRAGON).add(EntityType.PUFFERFISH);

        this.tag(MMTags.EntityTypes.TIME_ABERRATIONS)
                .add(MMEntities.CHUD.get())
                .add(MMEntities.BEHOLDER.get());

        this.tag(MMTags.EntityTypes.HAS_ENTRY)
                .add(MMEntities.CHUD.get())
                .add(MMEntities.BEHOLDER.get())
                .add(MMEntities.ANOMALOCARIS.get())
                .add(MMEntities.ARANDASPIS.get())
                .add(MMEntities.SACABAMBASPIS.get())
                .add(MMEntities.PIKAIA.get())
                .add(EntityType.SNIFFER)
                .add(MMEntities.FALCATUS.get())
                .add(MMEntities.ROACH.get())
                .add(MMEntities.SLOVENICUS.get())
                .add(MMEntities.EOLACTORIA.get())
                .add(MMEntities.DAWN_HORSE.get())
                .add(MMEntities.HALLUCIGENIA.get())
                .add(MMEntities.TRILOBITE.get())
                .add(MMEntities.THYLACINE.get())
                .add(MMEntities.DODO.get())
                .add(MMEntities.ELEPHANT_BIRD.get())
                .add(MMEntities.DOEDICURUS.get())
                .add(MMEntities.JOSEPHO.get());
    }
}
