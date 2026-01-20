package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

public class Sacabambaspis extends AbstractBasicFish {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Sacabambaspis.class, EntityDataSerializers.INT);

    public Sacabambaspis(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 1F);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(ItemTags.FISHES);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("Variant"));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        super.saveToBucketTag(bucket);

        CompoundTag compoundnbt = bucket.getOrCreateTag();

        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putInt("Variant", this.getVariant());
        compoundnbt.putBoolean("CanGrowUp", this.getCanGrowUp());
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        super.loadFromBucketTag(pTag);

        this.setVariant(pTag.getInt("Variant"));

        this.setCanGrowUp(pTag.getBoolean("CanGrow"));
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {


        if (reason==MobSpawnType.TRIGGERED || reason==MobSpawnType.BREEDING){
            this.setFromBucket(true);
        }

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Variant", 3)) {
            if (dataTag.contains("Age")) {
                this.setAge(dataTag.getInt("Age"));}
            this.setFromBucket(dataTag.getBoolean("CanGrowUp"));
            this.setVariant(dataTag.getInt("Variant"));
            this.setFromBucket(true);
        }else{

            int chance = this.getRandom().nextInt(100);
            int variant;
            if (chance>94){
                variant = 2;
            }else {
                variant = this.getRandom().nextInt(2);
            }

            this.setVariant(variant);
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public String getVariantName(int variantNumber) {
        return  switch(variantNumber){
            case 1 -> "_blue";
            case 2 -> "_golden";
            default -> "";
        };
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Sacabambaspis baby = MMEntities.SACABAMBASPIS.get().create(pLevel);
        Sacabambaspis otherSaca = (Sacabambaspis)pOtherParent;

        int chance = this.getRandom().nextInt(100);
        int variant;
        if (chance>94){
            variant = 2;
        }else {
            variant = this.getRandom().nextBoolean() ? this.getVariant() : otherSaca.getVariant();
        }

        baby.setVariant(variant);

        baby.setFromBucket(true);
        return baby;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.SACA_BUCKET.get());
    }
}
