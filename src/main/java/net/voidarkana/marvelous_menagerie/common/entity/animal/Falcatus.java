package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

public class Falcatus extends AbstractBasicFish {

    public Falcatus(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 1.1F);
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(ItemTags.FISHES);
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Falcatus baby = MMEntities.FALCATUS.get().create(pLevel);
        baby.setFromBucket(true);
        return baby;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.FALCA_BUCKET.get());
    }
}
