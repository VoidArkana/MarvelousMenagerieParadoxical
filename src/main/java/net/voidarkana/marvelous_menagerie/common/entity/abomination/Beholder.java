package net.voidarkana.marvelous_menagerie.common.entity.abomination;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.util.Mathf;

import javax.annotation.Nullable;

public class Beholder extends Monster implements IAnimatedAttacker {

    public Boolean hasJawsOpened;
    public Boolean hasJawsClosed;
    public Boolean hasReleased;
    public boolean isLandNavigator;
    public float prevTilt;
    public float tilt;
    public float currentRoll = 0.0F;

    private static final EntityDataAccessor<Integer> IN_WATER_TICKS = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_GRAB = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GRABBING = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GRABBING_TICKS = SynchedEntityData.defineId(Beholder.class, EntityDataSerializers.INT);

    public int attackAnimationTimeout;
    int prevTicksInWater;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleTwitchState = new AnimationState();
    int idleTwitchTimeout;
    public final AnimationState idleSignalState = new AnimationState();
    int idleSignalTimeout;
    public final AnimationState idleOverlay = new AnimationState();
    public final AnimationState idleLookAround = new AnimationState();
    int idleLookTimeout;

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
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
        switchNavigator(true);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(1, new BeholderAttackGoal(this, 1.25D, true, 11, 9));
        this.goalSelector.addGoal(1, new BeholderGrabGoal(this, 1.25D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1, 32.0F));
        this.goalSelector.addGoal(7, new BeholderStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new BeholderSwimGoal(this, 1.0D, 7));
        this.goalSelector.addGoal(8, new BeholderLookAtPlayer(this));
        this.goalSelector.addGoal(8, new BeholderRandomLook(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
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
        this.entityData.define(IN_WATER_TICKS, 0);
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

    public int getInWaterTicks() {
        return this.entityData.get(IN_WATER_TICKS);
    }

    public void setInWaterTicks(int variant) {
        this.entityData.set(IN_WATER_TICKS, variant);
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

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MoveControl(this);
            PathNavigation prevNav = this.navigation;
            this.navigation = new GroundPathNavigation(this, level());
            this.lookControl = new LookControl(this);
            this.isLandNavigator = true;
        } else {
            this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.2F, 0.1F, true);
            PathNavigation prevNav = this.navigation;
            this.navigation = new AmphibiousPathNavigation(this, level());
            this.lookControl = new SmoothSwimmingLookControl(this, 10);
            this.isLandNavigator = false;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide){

            if ((!this.isInWater() || this.onGround()) && this.getInWaterTicks() > 0 && !this.isVehicle()){

                this.prevTicksInWater = this.getInWaterTicks();
                this.setInWaterTicks(this.prevTicksInWater -1);

            }else if (this.isInWater() && !this.onGround() && this.getInWaterTicks() < 5){

                this.prevTicksInWater = this.getInWaterTicks();
                this.setInWaterTicks(this.prevTicksInWater +1);

            }

        }

        prevTilt = tilt;
        if (this.isInWater() && !this.onGround() && !this.isLandNavigator) {
            final float v = Mth.degreesDifference(this.getYRot(), yRotO);
            if (Math.abs(v) > 1) {
                if (Math.abs(tilt) < 25) {
                    tilt -= Math.signum(v);
                }
            } else {
                if (Math.abs(tilt) > 0) {
                    final float tiltSign = Math.signum(tilt);
                    tilt -= tiltSign * 0.85F;
                    if (tilt * tiltSign < 0) {
                        tilt = 0;
                    }
                }
            }
        } else {
            tilt = 0;
        }

        float prevRoll =  this.currentRoll;
        float targetRoll = Math.max(-0.45F, Math.min(0.45F, (this.getYRot() - this.yRotO) * 0.1F));
        targetRoll = -targetRoll;
        this.currentRoll = prevRoll + (targetRoll - prevRoll) * 0.05F;

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

        final boolean ground = !this.isInWater();

        if (!ground && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (ground && !this.isLandNavigator) {
            switchNavigator(true);
        }


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

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }

            this.calculateEntityAnimation(true);
        } else {
            super.travel(pTravelVector);
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

    public class BeholderRandomLook extends RandomLookAroundGoal{

        private final Beholder beholder;

        public BeholderRandomLook(Beholder pMob) {
            super(pMob);
            this.beholder = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !beholder.isGrabbing();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !beholder.isGrabbing();
        }
    }

    public class BeholderLookAtPlayer extends LookAtPlayerGoal{

        private final Beholder beholder;

        public BeholderLookAtPlayer(Beholder pMob) {
            super(pMob, Player.class, 8.0F);
            this.beholder = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !beholder.isGrabbing();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !beholder.isGrabbing();
        }
    }

    public class BeholderStrollGoal extends RandomStrollGoal{

        private final Beholder beholder;

        public BeholderStrollGoal(Beholder pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.beholder = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.beholder.isLandNavigator;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.beholder.isLandNavigator;
        }
    }

    public class BeholderSwimGoal extends RandomStrollGoal {
        public BeholderSwimGoal(PathfinderMob creature, double speed, int chance) {
            super(creature, speed, chance, false);
        }

        public boolean canUse() {
            if (this.mob.isVehicle() || mob.getTarget() != null || !this.mob.isInWater() && this.mob instanceof Beholder && ((Beholder) this.mob).isLandNavigator) {
                return false;
            } else {
                if (!this.forceTrigger) {
                    if (this.mob.getRandom().nextInt(this.interval) != 0) {
                        return false;
                    }
                }
                Vec3 vector3d = this.getPosition();
                if (vector3d == null) {
                    return false;
                } else {
                    this.wantedX = vector3d.x;
                    this.wantedY = vector3d.y;
                    this.wantedZ = vector3d.z;
                    this.forceTrigger = false;
                    return true;
                }
            }
        }

        @Nullable
        protected Vec3 getPosition() {
            if(this.mob.hasRestriction() && this.mob.distanceToSqr(Vec3.atCenterOf(this.mob.getRestrictCenter())) > this.mob.getRestrictRadius() * this.mob.getRestrictRadius()){
                return DefaultRandomPos.getPosTowards(this.mob, 7, 3, Vec3.atBottomCenterOf(this.mob.getRestrictCenter()), 1);
            }
            if(this.mob.getRandom().nextFloat() < 0.3F){
                Vec3 vector3d = findSurfaceTarget(this.mob, 15, 7);
                if(vector3d != null){
                    return vector3d;
                }
            }
            Vec3 vector3d = DefaultRandomPos.getPos(this.mob, 7, 3);

            for(int i = 0; vector3d != null && !this.mob.level().getBlockState(this.fromVec3(vector3d)).isPathfindable(this.mob.level(), fromVec3(vector3d), PathComputationType.WATER) && i++ < 15; vector3d = DefaultRandomPos.getPos(this.mob, 10, 7)) {
            }

            return vector3d;
        }

        public static final BlockPos fromVec3(Vec3 vec3){
            return fromCoords(vec3.x, vec3.y, vec3.z);
        }

        public static final BlockPos fromCoords(double x, double y, double z){
            return new BlockPos((int) x, (int) y, (int) z);
        }

        private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
            BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
            return this.mob.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.mob.level().getBlockState(blockpos).blocksMotion();
        }

        private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
            return this.mob.level().getBlockState(pos.offset(dx * scale, 1, dz * scale)).isAir() && this.mob.level().getBlockState(pos.offset(dx * scale, 2, dz * scale)).isAir();
        }

        private Vec3 findSurfaceTarget(PathfinderMob creature, int i, int i1) {
            BlockPos upPos = creature.blockPosition();
            while(creature.level().getFluidState(upPos).is(FluidTags.WATER)){
                upPos = upPos.above();
            }
            if(isAirAbove(upPos.below(), 0, 0, 0) && canJumpTo(upPos.below(), 0, 0, 0)){
                return new Vec3(upPos.getX() + 0.5F, upPos.getY() - 1F, upPos.getZ() + 0.5F);
            }
            return null;
        }
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
