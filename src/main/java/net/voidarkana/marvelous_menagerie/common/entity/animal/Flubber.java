package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.EggLayerBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.LayEggGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

public class Flubber extends MarvelousAnimal implements IEggLayer, Bucketable {

    private static final EntityDataAccessor<Boolean> IS_PREGNANT = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_BE_IN_LAND = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BOOLEAN);

    public boolean isLandNavigator;
    int layEggCounter;
    public float currentRoll = 0.0F;

    public Flubber(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_OPEN, -1.0F);

        this.switchNavigator(true);
        this.setMaxUpStep(1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
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
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Flubber.FlubberPanicGoal(this, 1.2D));
        this.goalSelector.addGoal(0, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(0, new LayEggGoal(this, 1.0D, BlockTags.SAND, MMBlocks.FLUBBER_EGG, 1D));

        this.goalSelector.addGoal(1, new FlubberGoToWaterGoal(this, 1));
        this.goalSelector.addGoal(1, new FlubberExitWaterGoal(this, 1.5));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2D, Ingredient.of(MMTags.Items.FLUBBER_DIG_ITEM), false));

        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.0F));

        this.goalSelector.addGoal(3, new FlubberStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FlubberSwimGoal(this, 1.5, 7));

        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(MMTags.Items.FLUBBER_DIG_ITEM);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PREGNANT, false);
        this.entityData.define(IS_LAYING_EGG, false);
        this.entityData.define(WANTS_TO_BE_IN_LAND, true);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsPregnant", this.isPregnant());
        compound.putBoolean("IsLayingEgg", this.isLayingEgg());
        compound.putBoolean("WantsToBeInLand", this.wantsToBeInLand());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setPregnant(compound.getBoolean("IsPregnant"));
        this.setLayingEgg(compound.getBoolean("IsLayingEgg"));
        this.setWantsToBeInLand(compound.getBoolean("WantsToBeInLand"));
    }

    public boolean wantsToBeInLand() {
        return this.entityData.get(WANTS_TO_BE_IN_LAND);
    }

    public void setWantsToBeInLand(boolean wantsToBeInLand) {
        this.entityData.set(WANTS_TO_BE_IN_LAND, wantsToBeInLand);
    }

    @Override
    public boolean isPregnant() {
        return this.entityData.get(IS_PREGNANT);
    }

    @Override
    public void setPregnant(boolean pregnant) {
        this.entityData.set(IS_PREGNANT, pregnant);
    }

    @Override
    public int getLayEggCounter() {
        return this.layEggCounter;
    }

    @Override
    public void setLayEggCounter(int layEggCounter) {
        this.layEggCounter = layEggCounter;
    }

    @Override
    public boolean isLayingEgg() {
        return this.entityData.get(IS_LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean pIsLayingEgg) {
        this.layEggCounter = pIsLayingEgg ? 1 : 0;
        this.entityData.set(IS_LAYING_EGG, pIsLayingEgg);
    }

    @Override
    public void tick() {
        super.tick();

        final boolean inWater = this.isInWaterOrBubble();

        if (inWater && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (!inWater && !this.isLandNavigator) {
            switchNavigator(true);
        }

        if ((this.getRandom().nextInt(500) == 0 && this.isInWaterOrBubble() && !this.wantsToBeInLand()) || this.isPregnant()){
            this.setWantsToBeInLand(true);
        }

        if (this.getRandom().nextInt(500) == 0 && !this.isInWaterOrBubble() && this.wantsToBeInLand()){
            this.setWantsToBeInLand(false);
        }
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (this.level().getBlockState(blockpos.below()).is(BlockTags.SAND)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
            }
        }

        float prevRoll =  this.currentRoll;
        float targetRoll = Math.max(-0.45F, Math.min(0.45F, (this.getYRot() - this.yRotO) * 0.1F));
        targetRoll = -targetRoll;
        this.currentRoll = prevRoll + (targetRoll - prevRoll) * 0.05F;
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

            if (this.isInWaterOrBubble() && this.wantsToBeInLand() && this.horizontalCollision
                    && !this.isEyeInFluidType(net.minecraftforge.common.ForgeMod.WATER_TYPE.get())){

                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.05D, 0.0D));
            }

        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return MMEntities.FLUBBER.get().create(pLevel);
    }

    @Override
    public boolean fromBucket() {
        return true;
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {

    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (this.isBaby())
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.BABY_FLUBBER_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Age", 3)) {
            if (dataTag.contains("Age")) {
                this.setAge(dataTag.getInt("Age"));}
        }

        if (reason == MobSpawnType.BUCKET && dataTag == null) {
                this.setAge(-24000);
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static class FlubberStrollGoal extends RandomStrollGoal{

        private final Flubber flubber;

        public FlubberStrollGoal(Flubber pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.flubber = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.flubber.isLandNavigator;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.flubber.isLandNavigator;
        }
    }

    public class FlubberSwimGoal extends RandomSwimmingGoal {
        private final Flubber flubber;

        public FlubberSwimGoal(Flubber creature, double speed, int chance) {
            super(creature, speed, chance);
            this.flubber = creature;
        }

        public boolean canUse() {
            return super.canUse() && !flubber.isLandNavigator;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !flubber.isLandNavigator;
        }
    }

    static class FlubberGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final Flubber turtle;

        FlubberGoToWaterGoal(Flubber pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 24);
            this.turtle = pTurtle;
            this.verticalSearchStart = -1;
        }

        public boolean canContinueToUse() {
            return !this.turtle.isInWater() && !this.turtle.wantsToBeInLand() && this.tryTicks <= 1200
                    && this.isValidTarget(this.turtle.level(), this.blockPos);
        }

        public boolean canUse() {
            return !this.turtle.isInWater() && !this.turtle.isPregnant() && !this.turtle.wantsToBeInLand() ? super.canUse() : false;
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    static class FlubberExitWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final Flubber turtle;

        FlubberExitWaterGoal(Flubber pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 24);
            this.turtle = pTurtle;
        }

        public boolean canContinueToUse() {
            return (this.turtle.isInWater() || this.turtle.wantsToBeInLand()) && this.tryTicks <= 1200
                    && this.isValidTarget(this.turtle.level(), this.blockPos);
        }

        public boolean canUse() {
            return (this.turtle.isInWater() || this.turtle.isPregnant() || this.turtle.wantsToBeInLand()) && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos.above()).isAir() && pLevel.getBlockState(pPos).isSolid();
        }
    }

    static class FlubberPanicGoal extends PanicGoal {
        FlubberPanicGoal(Flubber pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier);
        }

        public boolean canUse() {
            if (!this.shouldPanic()) {
                return false;
            } else {
                BlockPos blockpos = this.lookForWater(this.mob.level(), this.mob, 7);
                if (blockpos != null) {
                    this.posX = (double)blockpos.getX();
                    this.posY = (double)blockpos.getY();
                    this.posZ = (double)blockpos.getZ();
                    return true;
                } else {
                    return this.findRandomPosition();
                }
            }
        }
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new AmphibiousPathNavigation(this, pLevel);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return MMSounds.DOLPHIN_BLOWHOLE.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.STELLER_LAND_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return MMSounds.STELLER_DEATH.get();
    }
}
