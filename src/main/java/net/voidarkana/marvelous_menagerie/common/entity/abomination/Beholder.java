package net.voidarkana.marvelous_menagerie.common.entity.abomination;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.util.Mathf;

import javax.annotation.Nullable;

public class Beholder extends Monster implements IAnimatedAttacker {

    public Boolean hasJawsOpened;
    public Boolean hasJawsClosed;
    public Boolean hasReleased;

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_GRAB = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GRABBING = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GRABBING_TICKS = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.INT);

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

    public final AnimationState openJawsStartState = new AnimationState();
    int openJawsStartTimeout;
    public final AnimationState keepJawsOpenState = new AnimationState();
    public final AnimationState closeJawsState = new AnimationState();
    int closeJawsStartTimeout;
    public final AnimationState keepGrabbingState = new AnimationState();
    public final AnimationState releaseJawsState = new AnimationState();
    int releaseJawsStartTimeout;

    int attackTime = 11;
    int attackAnimLength = 20;

    public Beholder(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.hasJawsOpened = false;
        this.hasJawsClosed = false;
        this.hasReleased = true;
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
        this.goalSelector.addGoal(1, new BeholderAttackGoal(this, 1.25D, true, 11, 9));
        this.goalSelector.addGoal(1, new BeholderGrabGoal(this, 1.25D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1, 32.0F));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
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
        this.entityData.define(WANTS_TO_GRAB, false);
        this.entityData.define(IS_GRABBING, false);
        this.entityData.define(GRABBING_TICKS, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
        pCompound.putBoolean("WantsToGrab", this.wantsToGrab());
        pCompound.putBoolean("IsGrabbing", this.isGrabbing());
        pCompound.putInt("GrabbingTicks", this.getGrabbingTicks());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
        this.setWantsToGrab(pCompound.getBoolean("WantsToGrab"));
        this.setIsGrabbing(pCompound.getBoolean("IsGrabbing"));
        this.setGrabbingTicks(pCompound.getInt("GrabbingTicks"));
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean pFromBucket) {
        this.entityData.set(IS_ATTACKING, pFromBucket);
    }

    public boolean wantsToGrab() {
        return this.entityData.get(WANTS_TO_GRAB);
    }

    public void setWantsToGrab(boolean pFromBucket) {
        this.entityData.set(WANTS_TO_GRAB, pFromBucket);
    }

    public boolean isGrabbing() {
        return this.entityData.get(IS_GRABBING);
    }

    public void setIsGrabbing(boolean pFromBucket) {
        this.entityData.set(IS_GRABBING, pFromBucket);
    }

    public int getGrabbingTicks() {
        return this.entityData.get(GRABBING_TICKS);
    }

    public void setGrabbingTicks(int pFromBucket) {
        this.entityData.set(GRABBING_TICKS, pFromBucket);
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

        if (this.getGrabbingTicks()>0){
            int prevGrabbingTicks = this.getGrabbingTicks();
            this.setGrabbingTicks(prevGrabbingTicks-1);
        }

        super.tick();


        if (this.isGrabbing()){
            Entity entity = this.getFirstPassenger();

            if (entity != null) {
                if (entity instanceof Player player){
                    if (!player.isCreative()){
                        entity.setShiftKeyDown(false);
                    }
                }else {
                    entity.setShiftKeyDown(false);
                }
            }
        }

        if (!this.level().isClientSide()) {

            if (this.getTarget() != null && !this.wantsToGrab() && this.getRandom().nextInt(150) == 0){
                if (!this.getTarget().isPassenger())
                    this.setWantsToGrab(true);
            }

            if (this.canGrabOrGrabbing() && !this.isAggressive() && this.getPassengers().isEmpty()){
                this.setWantsToGrab(false);
                this.setIsGrabbing(false);
                this.setGrabbingTicks(0);
            }

            if ((!this.canContinueGrabbing()) && !this.getPassengers().isEmpty()){
                this.setWantsToGrab(false);
                this.setIsGrabbing(false);
                this.setGrabbingTicks(0);
                this.ejectPassengers();
            }
        }
    }

//    @Override
//    public void baseTick() {
//        super.baseTick();
//
//    }

    public void positionRider(Entity passenger, Entity.MoveFunction moveFunc) {
        if (!this.getPassengers().isEmpty()) {
            this.yBodyRot = Mth.wrapDegrees(this.getYRot() - 180F);
        }
        if (this.hasPassenger(passenger)) {
            final float radius = 0.8F;
            final float angle = (Mathf.STARTING_ANGLE * this.yBodyRot);
            final double extraX = radius * Mth.sin(Mth.PI + angle);
            final double extraZ = radius * Mth.cos(angle);
            passenger.setPos(this.getX() + extraX, this.getY() + 0.1F, this.getZ() + extraZ);
        }
    }

    public boolean canGrabOrGrabbing(){
        return this.wantsToGrab() || this.isGrabbing();
    }

    public boolean canContinueGrabbing(){
        return this.wantsToGrab() && this.isVehicle() && this.isGrabbing() && this.getGrabbingTicks()>0;
    }

    private void setupAnimationStates() {

        if (this.wantsToGrab()){

            if (this.hasReleased) this.hasReleased = false;

            if (this.releaseJawsState.isStarted()) this.releaseJawsState.stop();

            if (!this.hasJawsOpened){
                this.openJawsStartTimeout = 10;
                this.hasJawsOpened = true;
                this.openJawsStartState.start(this.tickCount);
            }

            if (this.openJawsStartTimeout > 0){
                this.openJawsStartTimeout--;
                if (this.openJawsStartTimeout == 0) this.openJawsStartState.stop();
            }

            if (!this.hasJawsClosed && this.hasJawsOpened && this.closeJawsStartTimeout == 0){
                this.closeJawsStartTimeout = 12;
                this.closeJawsState.start(this.tickCount);
            }

            if (this.closeJawsStartTimeout>0){
                this.closeJawsStartTimeout--;
                if (closeJawsStartTimeout == 0){
                    this.closeJawsState.stop();
                    this.hasJawsClosed = true;
                }
            }else {
                if (this.closeJawsStartTimeout != 0) this.closeJawsStartTimeout = 0;
            }

        }else {
            this.openJawsStartState.stop();
            this.closeJawsState.stop();
            if (!this.hasReleased && this.hasJawsClosed){
                this.hasReleased = true;
                this.releaseJawsState.start(this.tickCount);
                this.releaseJawsStartTimeout = 15;
            }

            if (this.releaseJawsStartTimeout>0){
                this.releaseJawsStartTimeout--;
                if (this.releaseJawsStartTimeout == 0)
                    this.releaseJawsState.stop();
            }

            if (this.hasJawsOpened) this.hasJawsOpened = false;
            if (this.hasJawsClosed) this.hasJawsClosed = false;
        }


        this.keepGrabbingState.animateWhen(this.wantsToGrab() && this.closeJawsStartTimeout == 0 && this.isGrabbing() &&
                this.hasJawsClosed && !this.getPassengers().isEmpty(), this.tickCount);

        this.keepJawsOpenState.animateWhen(this.wantsToGrab() && this.hasJawsOpened && !this.hasJawsClosed, this.tickCount);

        if (!this.isAttacking() && !this.wantsToGrab()){
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

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        return super.canAttack(pTarget) && this.getPassengers().isEmpty();
    }

    public class BeholderAttackGoal extends AnimatedAttackGoal{

        private final Beholder beholder;

        public BeholderAttackGoal(Beholder pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, int pAttackDelay, int pTicksUntilNextAttack) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen, pAttackDelay, pTicksUntilNextAttack);
            this.beholder = pMob;
        }

        public boolean canUse() {
            return super.canUse() && beholder.getPassengers().isEmpty() && !beholder.wantsToGrab();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && beholder.getPassengers().isEmpty() && !beholder.wantsToGrab();
        }
    }

    public class BeholderGrabGoal extends MeleeAttackGoal{

        private final Beholder beholder;

        public BeholderGrabGoal(Beholder pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
            this.beholder = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && beholder.wantsToGrab() && beholder.getPassengers().isEmpty();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && beholder.wantsToGrab() && beholder.getPassengers().isEmpty();
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            double d0 = this.getAttackReachSqr(pEnemy);

            if (pDistToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
                pEnemy.startRiding(this.beholder, true);
                beholder.lookControl.setLookAt(0, 0, 0);
                beholder.setIsGrabbing(true);
                beholder.setGrabbingTicks(60 + (beholder.getRandom().nextInt(1, 5) * 20));
            }

            super.checkAndPerformAttack(pEnemy, pDistToEnemySqr);
        }
    }

    @Override
    public boolean dismountsUnderwater() {
        return false;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        return null;
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }
}
