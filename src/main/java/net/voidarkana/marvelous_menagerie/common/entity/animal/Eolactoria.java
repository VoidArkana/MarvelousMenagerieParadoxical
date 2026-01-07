package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

public class Eolactoria extends AbstractBasicFish {

    public Eolactoria(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private static final EntityDataAccessor<Boolean> FLOP_SIDE = SynchedEntityData.defineId(Eolactoria.class, EntityDataSerializers.BOOLEAN);

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6F);
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(Items.DRIED_KELP);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLOP_SIDE, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FlopSide", this.flopSide());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFlopSide(pCompound.getBoolean("FlopSide"));
    }

    public boolean flopSide() {
        return this.entityData.get(FLOP_SIDE);
    }

    public void setFlopSide(boolean flopSide) {
        this.entityData.set(FLOP_SIDE, flopSide);
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Eolactoria baby = MMEntities.EOLACTORIA.get().create(pLevel);
        baby.setFromBucket(true);
        return baby;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.EOLACTORIA_BUCKET.get());
    }

    @Override
    public boolean canFlop() {
        return false;
    }
}
