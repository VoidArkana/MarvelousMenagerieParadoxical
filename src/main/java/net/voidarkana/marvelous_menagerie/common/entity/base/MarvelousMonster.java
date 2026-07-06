package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public abstract class MarvelousMonster extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    int prevTicksInWater;

    private float groundProgress = 5.0F;
    private float prevGroundProgress = 5.0F;
    private float inWaterProgress = 0;
    private float prevInWaterProgress = 0;
    private float sprintingProgress = 0;
    private float prevSprintingProgress = 0;
    private float aggroProgress = 0;
    private float prevAggroProgress = 0;

    private static final EntityDataAccessor<Boolean> IS_INVENTORY = SynchedEntityData.defineId(MarvelousMonster.class, EntityDataSerializers.BOOLEAN);
    protected MarvelousMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_INVENTORY, true);
    }

    public float getOnGroundMultiplier(float partialTick) {
        return (prevGroundProgress + (groundProgress - prevGroundProgress) * partialTick) * 0.2F;
    }

    public float getInWaterMultiplier(float partialTick) {
        return (prevInWaterProgress + (inWaterProgress - prevInWaterProgress) * partialTick) * 0.2F;
    }

    public float getSprintingMultiplier(float partialTick) {
        return (prevSprintingProgress + (sprintingProgress - prevSprintingProgress) * partialTick) * 0.2F;
    }

    public float getAggroMultiplier(float partialTick) {
        return (prevAggroProgress + (aggroProgress - prevAggroProgress) * partialTick) * 0.2F;
    }

    public int getInWaterTickBase(){
        return 5;
    }

    public int getonGroundTickBase(){
        return 5;
    }

    public int getSprintingTickBase(){
        return 5;
    }

    public int getAggroTickBase(){
        return 5;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsFromInventory", this.isFromInventory());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromInventory(pCompound.getBoolean("IsFromInventory"));
    }

    public Boolean isFromInventory() {
        return this.entityData.get(IS_INVENTORY);
    }

    public void setFromInventory(boolean variant) {
        this.entityData.set(IS_INVENTORY, variant);
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


        prevGroundProgress = groundProgress;
        if (onGround() && groundProgress < this.getonGroundTickBase()) {
            groundProgress++;
        }
        if (!onGround() && groundProgress > 0F) {
            groundProgress--;
        }

        prevInWaterProgress = inWaterProgress;
        if (isInWaterOrBubble() && inWaterProgress < this.getInWaterTickBase()) {
            inWaterProgress++;
        }
        if (!isInWaterOrBubble() && inWaterProgress > 0F) {
            inWaterProgress--;
        }

        prevAggroProgress = aggroProgress;
        if (isAggressive() && aggroProgress < this.getAggroTickBase()) {
            aggroProgress++;
        }
        if (!isAggressive() && aggroProgress > 0F) {
            aggroProgress--;
        }

        prevSprintingProgress = sprintingProgress;
        if (isSprinting() && sprintingProgress < this.getSprintingTickBase()) {
            sprintingProgress++;
        }
        if (!isSprinting() && sprintingProgress > 0F) {
            sprintingProgress--;
        }
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setFromInventory(false);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }
}
