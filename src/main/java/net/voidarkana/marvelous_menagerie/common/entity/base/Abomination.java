package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;

public abstract class Abomination extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    int prevTicksInWater;
    private static final EntityDataAccessor<Boolean> IS_INVENTORY = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> IN_WATER_TICKS = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.INT);

    protected Abomination(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IN_WATER_TICKS, 0);
        this.entityData.define(IS_INVENTORY, true);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsFromInventory", this.isFromInventory());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromInventory(pCompound.getBoolean("IsFromInventory"));
    }

    public int getInWaterTicks() {
        return this.entityData.get(IN_WATER_TICKS);
    }

    public void setInWaterTicks(int variant) {
        this.entityData.set(IN_WATER_TICKS, variant);
    }

    public Boolean isFromInventory() {
        return this.entityData.get(IS_INVENTORY);
    }

    public void setFromInventory(boolean variant) {
        this.entityData.set(IS_INVENTORY, variant);
    }

    @Override
    public void aiStep() {

        if (!this.level().isClientSide){

            if ((!this.isInWater() || this.onGround()) && this.getInWaterTicks() > 0){

                this.prevTicksInWater = this.getInWaterTicks();
                this.setInWaterTicks(this.prevTicksInWater -1);

            }else if (this.isInWater() && !this.onGround() && this.getInWaterTicks() < 5){

                this.prevTicksInWater = this.getInWaterTicks();
                this.setInWaterTicks(this.prevTicksInWater +1);

            }
        }
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
