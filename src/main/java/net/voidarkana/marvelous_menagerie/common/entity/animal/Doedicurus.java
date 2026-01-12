package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

import javax.annotation.Nullable;

public class Doedicurus extends MarvelousAnimal implements IAnimatedAttacker {

    public final AnimationState attackAnimationState1 = new AnimationState();
    public final AnimationState attackAnimationState2 = new AnimationState();
    public final AnimationState idleShakeState = new AnimationState();
    public int attackAnimationTimeout;
    private int idleShakeTimeout = this.getRandom().nextInt(160) + 160;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(ItemTags.LEAVES);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Doedicurus.class, EntityDataSerializers.BOOLEAN);

    public Doedicurus(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.35D, true, 27, 43));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0F));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsAttacking", this.isAttacking());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setAttacking(compound.getBoolean("IsAttacking"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0F)
                .add(Attributes.ARMOR, 6)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 14.0F);
    }

    public void tick (){
        super.tick();

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 70;

            if (this.getRandom().nextBoolean())
                attackAnimationState2.start(this.tickCount);
            else
                attackAnimationState1.start(this.tickCount);

        } else {
            --this.attackAnimationTimeout;
        }

        if(idleShakeTimeout <= 0) {
            idleShakeTimeout = this.getRandom().nextInt(160) + 160;
            idleShakeState.start(this.tickCount);
        } else {
            --this.idleShakeTimeout;
        }
    }

    @Override
    public float getStepHeight() {
        return 1.1F;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.DOEDICURUS_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return MMSounds.DOEDICURUS_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSounds.DOEDICURUS_AMBIENT.get();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return MMEntities.DOEDICURUS.get().create(level);
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

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
            this.playSound(MMSounds.LARGE_STEPS.get(), 0.25F, 1.0F);
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 >= 1.25D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
    }
}
