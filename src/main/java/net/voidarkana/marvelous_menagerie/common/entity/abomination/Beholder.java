package net.voidarkana.marvelous_menagerie.common.entity.abomination;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IAnimatedAttacker;

public class Beholder extends Monster implements IAnimatedAttacker {

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);

    public int attackAnimationTimeout;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleTwitchState = new AnimationState();
    int idleTwitchTimeout;
    public final AnimationState idleSignalState = new AnimationState();
    int idleSignalTimeout;
    public final AnimationState idleOverlay = new AnimationState();
    public final AnimationState idleLookAround = new AnimationState();
    int idleLookTimeout;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();

    int attackTime = 11;
    int attackAnimLength = 20;

    public Beholder(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.25D, true, 11, 9));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 24)
                .add(Attributes.ARMOR, 7.5F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
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

    public void tick (){
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();
    }
    private void setupAnimationStates() {

        if (!this.isAttacking()){
            if (this.idleLookTimeout <= 0) {
                this.idleLookTimeout = this.random.nextInt(40) + 80;
                this.idleLookAround.start(this.tickCount);
            } else {
                --this.idleLookTimeout;
            }

            if (this.idleTwitchTimeout <= 0) {
                this.idleTwitchTimeout = this.random.nextInt(60) + 80;
                this.idleTwitchState.start(this.tickCount);
            } else {
                --this.idleTwitchTimeout;
            }

            if (this.idleSignalTimeout <= 0) {
                this.idleSignalTimeout = this.random.nextInt(30) + 60;
                this.idleSignalState.start(this.tickCount);
            } else {
                --this.idleSignalTimeout;
            }
        }else{
            if (this.idleLookAround.isStarted()) this.idleLookAround.stop();
            if (this.idleTwitchState.isStarted()) this.idleTwitchState.stop();
        }

        this.idleAnimationState.animateWhen(!this.isInWaterOrBubble() && this.isAlive(), this.tickCount);
        this.idleOverlay.animateWhen(!this.isInWaterOrBubble() && this.isAlive(), this.tickCount);

        this.swimAnimationState.animateWhen(this.isInWaterOrBubble() && this.isAlive(), this.tickCount);

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.05F, this.getVoicePitch());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.ARTHROPOD_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.ARTHROPOD_DEATH.get();
    }
}
