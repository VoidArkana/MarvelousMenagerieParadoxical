package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.ai.MarvelousLandMoveControl;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;

public abstract class MarvelousAnimal extends Animal implements ISittingAnimal{

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standUpAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    int prevTicksInWater;

    private static final EntityDataAccessor<Boolean> IS_INVENTORY = SynchedEntityData.defineId(MarvelousAnimal.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(MarvelousAnimal.class, EntityDataSerializers.LONG);

    protected MarvelousAnimal(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new MarvelousLandMoveControl(this, this.getMaxYRot());
        this.setMaxUpStep(1);
    }

    public int getMaxYRot(){
        return 25;
    }

    protected BodyRotationControl createBodyControl() {
        if (this.canSit())
            return new MarvelousAnimal.SittingAnimalBodyRotationControl(this);
        else
            return super.createBodyControl();
    }

    class SittingAnimalBodyRotationControl extends BodyRotationControl {
        public SittingAnimalBodyRotationControl(MarvelousAnimal pCamel) {
            super(pCamel);
        }

        public void clientTick() {
            if (!MarvelousAnimal.this.refuseToMove()) {
                super.clientTick();
            }

        }
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_INVENTORY, true);
        this.entityData.define(LAST_POSE_CHANGE_TICK, 0L);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsFromInventory", this.isFromInventory());

        pCompound.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromInventory(pCompound.getBoolean("IsFromInventory"));

        long i = pCompound.getLong("LastPoseTick");
        if (i < 0L) {
            this.setPose(Pose.SITTING);
        }

        this.resetLastPoseChangeTick(i);
    }

    public boolean canSit(){
        return false;
    }

    public boolean refuseToMove() {
        return this.isInPoseTransition();
    }

    public boolean isSitting() {
        if (!this.canSit()){
            return false;
        }
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public boolean isVisuallySitting() {
        if (!this.canSit()){
            return false;
        }
        return this.getPoseTime() < 0L != this.isSitting();
    }

    public int getSitDuration(){
        return 0;
    }

    public int getStandDuration(){
        return 0;
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        return i < (long)(this.isSitting() ? this.getSitDuration() : this.getStandDuration());
    }

    public boolean isVisuallySittingDown() {
        return this.isSitting() && this.getPoseTime() < this.getSitDuration() && this.getPoseTime() >= 0L;
    }

    public void sitDown() {
        if (this.canSit()) {
            this.setPose(Pose.SITTING);
            this.resetLastPoseChangeTick(-this.level().getGameTime());
            this.refreshDimensions();
        }
    }

    public void standUp() {
        if (this.isSitting()) {
            this.setPose(Pose.STANDING);
            this.resetLastPoseChangeTick(this.level().getGameTime());
            if (this.isVehicle()){
                for(int i = this.getPassengers().size() - 1; i >= 0; --i) {
                    if (!(this.getPassengers().get(i) instanceof Player))
                        this.getPassengers().get(i).stopRiding();
                }
            }
            this.refreshDimensions();
        }
    }

    public void standUpInstantly() {
        this.setPose(Pose.STANDING);
        this.resetLastPoseChangeTickToFullStand(this.level().getGameTime());
    }

    public void resetLastPoseChangeTick(long pLastPoseChangeTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, pLastPoseChangeTick);
    }

    public void resetLastPoseChangeTickToFullStand(long pLastPoseChangedTick) {
        this.resetLastPoseChangeTick(Math.max(0L, pLastPoseChangedTick - this.getStandDuration() - 1L));
    }

    public long getPoseTime() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        if (pPlayer.zza > 0.0F && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    public Boolean isFromInventory() {
        return this.entityData.get(IS_INVENTORY);
    }

    public void setFromInventory(boolean variant) {
        this.entityData.set(IS_INVENTORY, variant);
    }

    @Override
    public void aiStep() {

        if (this.isFromInventory())
            this.setFromInventory(false);

        super.aiStep();
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();

        if (this.canSit()){
            if (this.refuseToMove()) {
                this.clampHeadRotationToBody(this, 30.0F);
            }

            if (this.hasToStandUpInstantly()) {
                this.standUpInstantly();
            }

            if (this.isAggressive() && this.isSitting()){
                if (this instanceof TamableMarvelousAnimal tamable){
                    if (!tamable.isTame())
                        this.standUp();
                }else
                    this.standUp();
            }
        }
    }

    public boolean hasToStandUpInstantly(){
        return this.isSitting() && this.isEyeInFluidType(Fluids.WATER.getFluidType());
    }


    private void clampHeadRotationToBody(Entity pEntity, float p_265541_) {
        float f = pEntity.getYHeadRot();
        float f1 = Mth.wrapDegrees(this.yBodyRot - f);
        float f2 = Mth.clamp(Mth.wrapDegrees(this.yBodyRot - f), -p_265541_, p_265541_);
        float f3 = f + f1 - f2;
        pEntity.setYHeadRot(f3);
    }

    protected void onLeashDistance(float pDistance) {
        if (pDistance > 6.0F && this.isSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        this.standUpInstantly();
        super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setFromInventory(false);
        if (this.canSit()){
            this.resetLastPoseChangeTickToFullStand(pLevel.getLevel().getGameTime());
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if (this.isVisuallySitting()) {
            this.standUpAnimationState.stop();
            if (this.isVisuallySittingDown()) {
                this.sitAnimationState.startIfStopped(this.tickCount);
                this.sitPoseAnimationState.stop();
            } else {
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sitAnimationState.stop();
            this.sitPoseAnimationState.stop();
            this.standUpAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
        }
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(MMTags.Blocks.MARVELOUS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos) && CommonConfig.NATURAL_SPAWNS.get();
    }

    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @Nullable AgeableMob pBaby) {
        this.setFromInventory(false);
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
    }

    @Override
    public boolean isImmobile() {
        if (this.canSit())
            return this.isInPoseTransition() || super.isImmobile();
        return super.isImmobile();
    }
}
