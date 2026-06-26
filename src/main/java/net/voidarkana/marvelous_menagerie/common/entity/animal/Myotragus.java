package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.movement.MarvelousLandMoveControl;
import net.voidarkana.marvelous_menagerie.common.entity.ai.brains.MMMemoryModuleTypes;
import net.voidarkana.marvelous_menagerie.common.entity.ai.brains.MyotragusAI;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;

import javax.annotation.Nullable;
import java.util.function.IntFunction;

public class Myotragus extends Goat implements ISittingAnimal {

    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standUpAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(Myotragus.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Myotragus.class, EntityDataSerializers.INT);

    protected static final ImmutableList<MemoryModuleType<?>> MYOTRAGUS_MEMORY_TYPES =
            ImmutableList.of(
                    MemoryModuleType.LOOK_TARGET,
                    MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
                    MemoryModuleType.WALK_TARGET,
                    MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
                    MemoryModuleType.PATH,
                    MemoryModuleType.ATE_RECENTLY,
                    MemoryModuleType.BREED_TARGET,
                    MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS,
                    MemoryModuleType.LONG_JUMP_MID_JUMP,
                    MemoryModuleType.TEMPTING_PLAYER,
                    MemoryModuleType.NEAREST_VISIBLE_ADULT,
                    MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
                    MemoryModuleType.IS_TEMPTED,
                    MemoryModuleType.RAM_COOLDOWN_TICKS,
                    MemoryModuleType.RAM_TARGET,
                    MemoryModuleType.IS_PANICKING,
                    MMMemoryModuleTypes.SITTING.get());

    public Myotragus(EntityType<? extends Goat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new MarvelousLandMoveControl(this, 90);
    }
    public static final EntityDataAccessor<Integer> SITTING_TICKS = SynchedEntityData.defineId(Myotragus.class, EntityDataSerializers.INT);

    public int getSittingTicks(){
        return this.entityData.get(SITTING_TICKS);
    }

    public void setSittingTicks(int ticks){
        this.entityData.set(SITTING_TICKS, ticks);
    }

    public int getSittingTickBase(){
        return 5;
    }

    public float getSittingMultiplier(){
        return (float) this.getSittingTicks() /this.getSittingTickBase();
    }

    public boolean isMylo() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && (s.toLowerCase().contains("mylo")
                || s.toLowerCase().contains("jiggly")
                || s.toLowerCase().contains("cyclops")
                || s.toLowerCase().contains("mylo the jiggly cyclops")
                || s.toLowerCase().contains("mylotragus"));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LAST_POSE_CHANGE_TICK, 0L);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(SITTING_TICKS, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        long i = pCompound.getLong("LastPoseTick");
        this.setVariant(pCompound.getInt("Variant"));
        if (i < 0L) {
            this.setPose(Pose.SITTING);
        }
        this.resetLastPoseChangeTick(i);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public String getVariantName(){
        return MyotragusVariant.byId(this.getVariant()).getSerializedName();
    };


    protected Brain.Provider<Myotragus> MyotragusBrainProvider() {
        return Brain.provider(MYOTRAGUS_MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> makeBrain(Dynamic<?> pDynamic) {
        return MyotragusAI.makeBrain(this.MyotragusBrainProvider().makeBrain(pDynamic));
    }
    @Override
    public ItemStack createHorn() {
        return super.createHorn();
    }

    protected SoundEvent getAmbientSound() {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_AMBIENT : SoundEvents.GOAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HURT : SoundEvents.GOAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_DEATH : SoundEvents.GOAT_DEATH;
    }

    @Nullable
    public Myotragus getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Myotragus otherParent = (Myotragus) pOtherParent;
        Myotragus goat = MMEntities.MYOTRAGUS.get().create(pLevel);
        if (goat != null) {
            goat.setVariant(this.getRandom().nextBoolean() ? this.getVariant() : otherParent.getVariant());
            boolean flag1;
            label22: {
                label21: {
                    MyotragusAI.initMemories(goat, pLevel.getRandom());
                    AgeableMob ageablemob = pLevel.getRandom().nextBoolean() ? this : pOtherParent;
                    if (ageablemob instanceof Myotragus) {
                        Myotragus goat1 = (Myotragus)ageablemob;
                        if (goat1.isScreamingGoat()) {
                            break label21;
                        }
                    }

                    if (!(pLevel.getRandom().nextDouble() < 0.02D)) {
                        flag1 = false;
                        break label22;
                    }
                }

                flag1 = true;
            }

            boolean flag = flag1;
            goat.setScreamingGoat(flag);
        }

        return goat;
    }

    @Override
    public void tick() {
        if (this.isSitting()){
            if (this.getSittingTicks()<this.getSittingTickBase()){
                int prevTicks = this.getSittingTicks();
                this.setSittingTicks(prevTicks+1);
            }
        }else {
            if (this.getSittingTicks()>0){
                int prevTicks = this.getSittingTicks();
                this.setSittingTicks(prevTicks-1);
            }
        }
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();

        if (this.refuseToMove()) {
            this.clampHeadRotationToBody(this, 30.0F);
        }

        if (this.hasToStandUpInstantly()) {
            this.standUpInstantly();
        }

        if (this.isAggressive() && this.isSitting()){
            this.standUp();
        }
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        if (this.canSit()&& pPlayer.zza > 0.0F && this.isSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    protected void onLeashDistance(float pDistance) {
        if (pDistance > 6.0F && this.isSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {
        SpawnGroupData data = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.resetLastPoseChangeTickToFullStand(pLevel.getLevel().getGameTime());
        MyotragusAI.initMemories(this, pLevel.getRandom());
        this.setVariant(Util.getRandom(Myotragus.MyotragusVariant.values(), this.getRandom()).id());
        return data;
    }

    protected void customServerAiStep() {
        this.level().getProfiler().push("myotragusBrain");
        this.getBrain().tick((ServerLevel)this.level(), this);
        this.level().getProfiler().pop();
        this.level().getProfiler().push("myotragusActivityUpdate");
        MyotragusAI.updateActivity(this);
        this.level().getProfiler().pop();

        if (this.getAge() != 0) {
            this.setInLoveTime(0);
        }
    }

    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        this.standUpInstantly();
        super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    public void setupAnimationStates() {
        if (this.canSit()){
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
    }

    private void clampHeadRotationToBody(Entity pEntity, float p_265541_) {
        float f = pEntity.getYHeadRot();
        float f1 = Mth.wrapDegrees(this.yBodyRot - f);
        float f2 = Mth.clamp(Mth.wrapDegrees(this.yBodyRot - f), -p_265541_, p_265541_);
        float f3 = f + f1 - f2;
        pEntity.setYHeadRot(f3);
    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public boolean refuseToMove() {
        return this.isSitting() || this.isInPoseTransition();
    }

    @Override
    public boolean isSitting() {
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    @Override
    public boolean isVisuallySitting() {
        return this.getPoseTime() < 0L != this.isSitting();
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        return i < (long)(this.isSitting() ? this.getSitDuration() : this.getStandDuration());
    }

    public boolean isVisuallySittingDown() {
        return this.isSitting() && this.getPoseTime() < this.getSitDuration() && this.getPoseTime() >= 0L;
    }

    public void sitDown() {
        if (!this.isSitting()) {
            this.getBrain().setMemory(MMMemoryModuleTypes.SITTING.get(), true);
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
        if (this.getBrain().hasMemoryValue(MMMemoryModuleTypes.SITTING.get()))
            this.getBrain().eraseMemory(MMMemoryModuleTypes.SITTING.get());
        this.resetLastPoseChangeTickToFullStand(this.level().getGameTime());
    }

    @Override
    public boolean hasToStandUpInstantly() {
        return this.isSitting() && this.isEyeInFluidType(Fluids.WATER.getFluidType());
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

    @Override
    public int getSitDuration() {
        return 20;
    }

    @Override
    public int getStandDuration() {
        return 20;
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(MMTags.Blocks.MARVELOUS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos) && CommonConfig.NATURAL_SPAWNS.get();
    }

    @Override
    public boolean isImmobile() {
        return this.isInPoseTransition() || super.isImmobile();
    }

    public boolean isPanicking() {
        return this.getBrain().checkMemory(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_PRESENT);
    }


    public enum MyotragusVariant implements StringRepresentable {
        UMBER(0, "umber"),
        GOLDEN(1, "golden"),
        LILAC(2, "lilac");

        private static final IntFunction<MyotragusVariant> BY_ID = ByIdMap.sparse(MyotragusVariant::id, values(), UMBER);
        public static final Codec<MyotragusVariant> CODEC = StringRepresentable.fromEnum(MyotragusVariant::values);
        final int id;
        private final String name;

        MyotragusVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static MyotragusVariant byId(int pId) {
            return BY_ID.apply(pId);
        }
    }
}
