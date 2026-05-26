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
    public static final EntityDataAccessor<Integer> ON_GROUND_TICKS = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> IN_WATER_TICKS = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SPRINTING_TICKS = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> AGGRO_TICKS = SynchedEntityData.defineId(Abomination.class, EntityDataSerializers.INT);

    protected Abomination(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_INVENTORY, true);
        this.entityData.define(AGGRO_TICKS, 0);
        this.entityData.define(IN_WATER_TICKS, 0);
        this.entityData.define(ON_GROUND_TICKS, 0);
        this.entityData.define(SPRINTING_TICKS, 0);
    }

    public int getInWaterTicks(){
        return this.entityData.get(IN_WATER_TICKS);
    }

    public void setInWaterTicks(int ticks){
        this.entityData.set(IN_WATER_TICKS, ticks);
    }

    public int getInWaterTickBase(){
        return 5;
    }

    public float getInWaterMultiplier(){
        return (float) this.getInWaterTicks() /this.getInWaterTickBase();
    }

    public int getonGroundTicks(){
        return this.entityData.get(ON_GROUND_TICKS);
    }

    public void setonGroundTicks(int ticks){
        this.entityData.set(ON_GROUND_TICKS, ticks);
    }

    public int getonGroundTickBase(){
        return 5;
    }

    public float getonGroundMultiplier(){
        return (float) this.getonGroundTicks() /this.getonGroundTickBase();
    }

    public int getSprintingTicks(){
        return this.entityData.get(SPRINTING_TICKS);
    }

    public void setSprintingTicks(int ticks){
        this.entityData.set(SPRINTING_TICKS, ticks);
    }

    public int getSprintingTickBase(){
        return 5;
    }

    public float getSprintingMultiplier(){
        return (float) this.getSprintingTicks() /this.getSprintingTickBase();
    }

    public int getAggroTicks(){
        return this.entityData.get(AGGRO_TICKS);
    }

    public void setAggroTicks(int ticks){
        this.entityData.set(AGGRO_TICKS, ticks);
    }

    public int getAggroTickBase(){
        return 5;
    }

    public float getAggroMultiplier(){
        return (float) this.getAggroTicks() /this.getAggroTickBase();
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
        if (this.isInWaterOrBubble()){
            if (this.getInWaterTicks()<this.getInWaterTickBase()){
                int prevTicks = this.getInWaterTicks();
                this.setInWaterTicks(prevTicks+1);
            }
        }else {
            if (this.getInWaterTicks()>0){
                int prevTicks = this.getInWaterTicks();
                this.setInWaterTicks(prevTicks-1);
            }
        }

        if (this.onGround()){
            if (this.getonGroundTicks()<this.getonGroundTickBase()){
                int prevTicks = this.getonGroundTicks();
                this.setonGroundTicks(prevTicks+1);
            }
        }else {
            if (this.getonGroundTicks()>0){
                int prevTicks = this.getonGroundTicks();
                this.setonGroundTicks(prevTicks-1);
            }
        }

        if (this.isAggressive()){
            if (this.getAggroTicks()<this.getAggroTickBase()){
                int prevTicks = this.getAggroTicks();
                this.setAggroTicks(prevTicks+1);
            }
        }else {
            if (this.getAggroTicks()>0){
                int prevTicks = this.getAggroTicks();
                this.setAggroTicks(prevTicks-1);
            }
        }

        if (this.isSprinting()){
            if (this.getSprintingTicks()<this.getSprintingTickBase()){
                int prevTicks = this.getSprintingTicks();
                this.setSprintingTicks(prevTicks+1);
            }
        }else {
            if (this.getSprintingTicks()>0){
                int prevTicks = this.getSprintingTicks();
                this.setSprintingTicks(prevTicks-1);
            }
        }
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
