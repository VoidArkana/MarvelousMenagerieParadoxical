package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.FishBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Anomalocaris extends AbstractBasicFish implements IAnimatedAttacker, NeutralMob {

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Anomalocaris.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> SHAKING_TIME = SynchedEntityData.defineId(Anomalocaris.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Anomalocaris.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Anomalocaris.class, EntityDataSerializers.INT);

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public int attackAnimationTimeout;

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState shakeAnimationState = new AnimationState();

    public Anomalocaris(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.25D, true, 7, 13));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, this.foodIngredients(), false));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, this.fintasticFoodIngredients(), false));
        this.goalSelector.addGoal(2, new FishBreedGoal(this, 1.5D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(ItemTags.FISHES);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.FOLLOW_RANGE, 12)
                .add(Attributes.ARMOR, 5F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHAKING_TIME, 0);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("ShakingTime", this.getShakingTime());
        pCompound.putInt("Variant", this.getVariant());
        pCompound.putBoolean("IsAttacking", this.isAttacking());
        this.addPersistentAngerSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setShakingTime(pCompound.getInt("ShakingTime"));
        this.setVariant(pCompound.getInt("Variant"));
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
        this.readPersistentAngerSaveData(this.level(), pCompound);
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this),
                (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
        }

        return flag;
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean pFromBucket) {
        this.entityData.set(IS_ATTACKING, pFromBucket);
    }

    @Override
    public int attackAnimationTimeout() {
        return this.attackAnimationTimeout;
    }

    @Override
    public void setAttackAnimationTimeout(int attackAnimationTimeout) {
        this.attackAnimationTimeout = attackAnimationTimeout;
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int anim) {
        this.entityData.set(VARIANT, anim);
    }

    public int getShakingTime() {
        return this.entityData.get(SHAKING_TIME);
    }

    public void setShakingTime(int variant) {
        this.entityData.set(SHAKING_TIME, variant);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Variant", this.getVariant());
        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putBoolean("CanGrowUp", this.getCanGrowUp());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        super.loadFromBucketTag(pTag);
        if (pTag.contains("Variant"))
            this.setVariant(pTag.getInt("Variant"));
    }

    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(MMItems.CARIS_LENS.get(), 1);
            this.spawnAtLocation(MMItems.CARIS_LENS.get(), 1);
        }

    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.CARIS_BUCKET.get());
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason==MobSpawnType.TRIGGERED){
            this.setFromBucket(true);
        }

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Variant", 3)) {
            if (dataTag.contains("Age")) {
                this.setAge(dataTag.getInt("Age"));}

            this.setCanGrowUp(dataTag.getBoolean("CanGrowUp"));
            this.setVariant(dataTag.getInt("Variant"));
            this.setFromBucket(true);
        }else {
            int chance = this.getRandom().nextInt(100);
            int variant;
            if (chance<50){
                variant = 0;
            }else if (chance < 75){
                variant = 1;
            }else if (chance < 85){
                variant = 2;
            }else if (chance < 95){
                variant = 3;
            }else {
                variant = 4;
            }
            this.setVariant(variant);
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public String getVariantName(int variantNumber) {
        return  switch(variantNumber){
            case 1 -> "_swampy";
            case 2 -> "_blue";
            case 3 -> "_yellow";
            case 4 -> "_half";
            default -> "";
        };
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Anomalocaris baby = MMEntities.ANOMALOCARIS.get().create(pLevel);
        Anomalocaris otherCaris = (Anomalocaris) pOtherParent;

        if (this.getRandom().nextInt(4)==0){
            int chance = this.getRandom().nextInt(100);
            int variant;
            if (chance<50){
                variant = 0;
            }else if (chance < 75){
                variant = 1;
            }else if (chance < 85){
                variant = 2;
            }else if (chance < 95){
                variant = 3;
            }else {
                variant = 4;
            }
            baby.setVariant(variant);
        }else {
            if (this.random.nextBoolean()) {
                baby.setVariant(this.getVariant());
            } else {
                baby.setVariant(otherCaris.getVariant());
            }
        }

        baby.setFromBucket(true);

        return baby;
    }

    public void tick (){

        super.tick();

        if (this.getShakingTime()>0){
            int prevShakingTime = this.getShakingTime();
            if (this.getShakingTime()==12){
                this.playSound(SoundEvents.SNIFFER_EGG_PLOP, 1.0F, ((this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F));
            }
            if (this.getShakingTime()==10){
                this.spawnAtLocation(MMItems.CARIS_SCUTE.get());
            }

            this.setShakingTime(prevShakingTime-1);
        }
    }


    public void setupAnimationStates() {
        super.setupAnimationStates();

        this.shakeAnimationState.animateWhen(this.getShakingTime()>0, this.tickCount);

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);

        if ((itemstack.is(MMItems.TRILO_BITE.get())) && this.isInWater() && this.getShakingTime()==0 ) {
            this.usePlayerItem(player, hand, itemstack);
            this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setShakingTime(30);
            return InteractionResult.SUCCESS;
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        if (!pPlayer.getAbilities().instabuild) {
            pStack.shrink(1);
        }

    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.ARTHROPOD_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.ARTHROPOD_DEATH.get();
    }

    @Override
    public boolean canAttack(LivingEntity entity) {
        return super.canAttack(entity) && !this.isBaby();
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }
}
