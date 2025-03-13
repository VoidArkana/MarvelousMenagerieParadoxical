package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class MMEntityTagGenerator extends EntityTypeTagsProvider {

    public MMEntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, MarvelousMenagerie.MODID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(MMTags.EntityTypes.THYLA_ALERT_TARGET)
                .add(EntityType.HOGLIN).add(EntityType.WITHER)
                .add(EntityType.ENDER_DRAGON).add(EntityType.PUFFERFISH);

        this.tag(MMTags.EntityTypes.TIME_ABERRATIONS)
                .add(MMEntities.CHUD.get()).add(EntityType.COD).add(EntityType.SLIME);
    }
}
