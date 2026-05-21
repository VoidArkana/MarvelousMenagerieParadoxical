package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAmphibianCreature extends MarvelousAnimal{

    public float currentRoll = 0.0F;

    private static final EntityDataAccessor<Boolean> IS_LAND_NAVIGATOR = SynchedEntityData.defineId(AbstractAmphibianCreature.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_BE_IN_LAND = SynchedEntityData.defineId(AbstractAmphibianCreature.class, EntityDataSerializers.BOOLEAN);

    protected AbstractAmphibianCreature(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_OPEN, -1.0F);

        this.switchNavigator(true);
        this.setMaxUpStep(1.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_LAND_NAVIGATOR, true);
        this.entityData.define(WANTS_TO_BE_IN_LAND, true);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("WantsToBeInLand", this.wantsToBeInLand());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setWantsToBeInLand(compound.getBoolean("WantsToBeInLand"));
    }

    public boolean isLandNavigator() {
        return this.entityData.get(IS_LAND_NAVIGATOR);
    }

    public void setIsLandNavigator(boolean isLandNavigator) {
        this.entityData.set(IS_LAND_NAVIGATOR, isLandNavigator);
    }

    public boolean wantsToBeInLand() {
        return this.entityData.get(WANTS_TO_BE_IN_LAND);
    }

    public void setWantsToBeInLand(boolean wantsToBeInLand) {
        this.entityData.set(WANTS_TO_BE_IN_LAND, wantsToBeInLand);
    }

    @Override
    public int getMaxYRot() {
        return 10;
    }

    public void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MarvelousMoveControl(this);
            this.navigation = new GroundPathNavigation(this, level());
            this.lookControl = new LookControl(this);
            this.setIsLandNavigator(true);
        } else {
            this.moveControl = new SmoothSwimmingMoveControl(this, 65, this.getMaxYRot(), 0.2F, 0.1F, true);
            this.navigation = new AmphibiousPathNavigation(this, level());
            this.lookControl = new SmoothSwimmingLookControl(this, this.getMaxYRot());
            this.setIsLandNavigator(false);
        }
    }

    public int getIntoWaterChance(){
        return 1500;
    }

    public int getOutOfWaterChance(){
        return 1500;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide && this.wantsToBeInLand()){
            if (this.horizontalCollision && this.isInWaterOrBubble()){
                this.addDeltaMovement(new Vec3(0, 0.2D, 0));
            }
        }

        final boolean inWater = this.isInWaterOrBubble();

        if (inWater && this.isLandNavigator()) {
            switchNavigator(false);
        }
        if (!inWater && !this.isLandNavigator()) {
            switchNavigator(true);
        }

        if ((this.getRandom().nextInt(this.getOutOfWaterChance()) == 0 && this.isInWaterOrBubble() && !this.wantsToBeInLand())){
            this.setWantsToBeInLand(true);
            if (this.isSitting())
                this.standUp();
        }

        if (this.getRandom().nextInt(this.getIntoWaterChance()) == 0 && !this.isInWaterOrBubble() && this.wantsToBeInLand()){
            this.setWantsToBeInLand(false);
            if (this.isSitting())
                this.standUp();
        }

    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isImmobile() && !this.isLandNavigator() && this.isInWaterOrBubble()){
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005f, 0.0));
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

                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.1D, 0.05D));
            }

        } else {
            super.travel(pTravelVector);
        }
    }


    public static class AmphibianStrollGoal extends RandomStrollGoal {

        private final AbstractAmphibianCreature flubber;

        public AmphibianStrollGoal(AbstractAmphibianCreature pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.flubber = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.flubber.isLandNavigator() && !this.flubber.isSitting();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.flubber.isLandNavigator() && !this.flubber.isSitting();
        }
    }

    public static class AmphibianSwimGoal extends RandomSwimmingGoal {
        private final AbstractAmphibianCreature flubber;

        public AmphibianSwimGoal(AbstractAmphibianCreature creature, double speed, int chance) {
            super(creature, speed, chance);
            this.flubber = creature;
        }

        public boolean canUse() {
            return super.canUse() && !flubber.isLandNavigator();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !flubber.isLandNavigator();
        }
    }



    public static class AmphibianPanicGoal extends PanicGoal {
        public AmphibianPanicGoal(AbstractAmphibianCreature pTurtle, double pSpeedModifier) {
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

    public static class AmphibianExitWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final AbstractAmphibianCreature turtle;

        public AmphibianExitWaterGoal(AbstractAmphibianCreature pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 24);
            this.turtle = pTurtle;
        }

        public boolean canContinueToUse() {
            return (this.turtle.isInWater() && this.turtle.wantsToBeInLand()) && this.tryTicks <= 1200
                    && this.isValidTarget(this.turtle.level(), this.blockPos);
        }

        public boolean canUse() {
            return this.turtle.isInWater() && this.turtle.wantsToBeInLand() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos.above()).isAir() && pLevel.getBlockState(pPos).isSolid();
        }
    }

    public static class AmphibianGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final AbstractAmphibianCreature amphibian;

        public AmphibianGoToWaterGoal(AbstractAmphibianCreature pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 24);
            this.amphibian = pTurtle;
            this.verticalSearchStart = -1;
        }

        public boolean canContinueToUse() {
            return !this.amphibian.isInWater() && !this.amphibian.wantsToBeInLand() && this.tryTicks <= 1200
                    && this.isValidTarget(this.amphibian.level(), this.blockPos);
        }

        public boolean canUse() {
            return !this.amphibian.isInWater() && !this.amphibian.wantsToBeInLand() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new AmphibiousPathNavigation(this, pLevel);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 0.0F;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }
}
