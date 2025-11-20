package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.FishFollowParentGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.EnumSet;

public class StellerSeaCow extends AbstractBasicFish {

    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();

    public StellerSeaCow(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
        this.moveControl = new StellerSwimmingController(this, 85, 10, 0.02F, 0.01F, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.ARMOR, 10.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.MOVEMENT_SPEED, (double)0.8F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, foodIngredients(), false));
        this.goalSelector.addGoal(3, new FishFollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(3, new SeaCowSwimWithPlayerGoal(this, 2D));
        this.goalSelector.addGoal(4, new RandomShallowSwimmingGoal(this, 1, 10));
        this.goalSelector.addGoal(5, new SeaCowBreachGoal(this)); //rework into tick?
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F, 0.02F, true));
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isBaby() ) {
            return super.getDimensions(pPose).scale(0.5F, 0.75F);
        }else {
            return super.getDimensions(pPose);
        }
    }

    public Ingredient foodIngredients(){
        return Ingredient.of(Items.KELP, Items.DRIED_KELP, Items.DRIED_KELP_BLOCK);
    }

    @Override
    public boolean canBeBucketed() {
        return this.isBaby();
    }

    @Override
    public boolean canFlop() {
        return this.isBaby();
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnDataIn, @org.jetbrains.annotations.Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag == null)
            this.setAge(-24000);

        this.setXRot(0.0F);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public @org.jetbrains.annotations.Nullable BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        return MMEntities.STELLER_SEA_COW.get().create(pLevel);
    }

    public void tick() {
        super.tick();
        //if it gets too deep it goes up
        if ( this.isInWater() && this.getFluidHeight(FluidTags.WATER) > 3 || this.isInFluidType((fluidType, height) -> this.canSwimInFluidType(fluidType) && height > 3)){
            this.findAirPosition();
            this.moveRelative(0.02F, new Vec3(this.xxa, this.yya, this.zza));
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            pPlayer.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, pPlayer, MMItems.STELLER_MILK.get().getDefaultInstance());
            pPlayer.setItemInHand(pHand, itemstack1);

            if (!this.fromBucket()){
                this.setFromBucket(true);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.STELLER_BUCKET.get());
    }

    public class StellerSwimmingController extends MoveControl {
        private static final float FULL_SPEED_TURN_THRESHOLD = 10.0F;
        private static final float STOP_TURN_THRESHOLD = 60.0F;
        private final int maxTurnX;
        private final int maxTurnY;
        private final float inWaterSpeedModifier;
        private final float outsideWaterSpeedModifier;
        private final boolean applyGravity;
        private final StellerSeaCow StellerSeaCow;

        public StellerSwimmingController(StellerSeaCow StellerSeaCowIn, int pMaxTurnX, int pMaxTurnY, float pInWaterSpeedModifier, float pOutsideWaterSpeedModifier, boolean pApplyGravity) {
            super(StellerSeaCowIn);
            this.StellerSeaCow = StellerSeaCowIn;
            this.maxTurnX = pMaxTurnX;
            this.maxTurnY = pMaxTurnY;
            this.inWaterSpeedModifier = pInWaterSpeedModifier;
            this.outsideWaterSpeedModifier = pOutsideWaterSpeedModifier;
            this.applyGravity = pApplyGravity;
        }

        public void tick() {

            if (this.applyGravity && this.StellerSeaCow.isInWater() && this.StellerSeaCow.getFluidHeight(FluidTags.WATER)>0.1F) {
                this.StellerSeaCow.setDeltaMovement(this.StellerSeaCow.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            } else if (this.StellerSeaCow.isInWater() && this.StellerSeaCow.getNavigation().isDone()) {
                this.StellerSeaCow.setDeltaMovement(this.StellerSeaCow.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            } else if (this.StellerSeaCow.isInWater() && this.StellerSeaCow.getFluidHeight(FluidTags.WATER)>3F){
                this.StellerSeaCow.setDeltaMovement(this.StellerSeaCow.getDeltaMovement().add(0.0D, 0.5D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.StellerSeaCow.getNavigation().isDone()) {
                double d0 = this.wantedX - this.StellerSeaCow.getX();
                double d1 = this.wantedY - this.StellerSeaCow.getY();
                double d2 = this.wantedZ - this.StellerSeaCow.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.StellerSeaCow.setZza(0.0F);
                } else {
                    float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.StellerSeaCow.setYRot(this.rotlerp(this.StellerSeaCow.getYRot(), f, (float)this.maxTurnY));

                    this.StellerSeaCow.yBodyRot = this.StellerSeaCow.getYRot();
                    this.StellerSeaCow.yHeadRot = this.StellerSeaCow.getYRot();


                    float f1 = (float)(this.speedModifier * this.StellerSeaCow.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.StellerSeaCow.isInWater()) {
                        this.StellerSeaCow.setSpeed(f1 * this.inWaterSpeedModifier);
                        double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                        if (Math.abs(d1) > (double)1.0E-5F || Math.abs(d4) > (double)1.0E-5F) {
                            float f3 = -((float)(Mth.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
                            f3 = Mth.clamp(Mth.wrapDegrees(f3), (float)(-this.maxTurnX), (float)this.maxTurnX);
                            this.StellerSeaCow.setXRot(this.rotlerp(this.StellerSeaCow.getXRot(), f3, 5.0F));
                        }

                        float f6 = Mth.cos(this.StellerSeaCow.getXRot() * ((float)Math.PI / 180F));
                        float f4 = Mth.sin(this.StellerSeaCow.getXRot() * ((float)Math.PI / 180F));

                        this.StellerSeaCow.zza = f6 * f1;
                        this.StellerSeaCow.yya = -f4 * f1;

                    } else {
                        float f5 = Math.abs(Mth.wrapDegrees(this.StellerSeaCow.getYRot() - f));
                        float f2 = getTurningSpeedFactor(f5);
                        this.StellerSeaCow.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
                    }
                }
            } else {
                this.StellerSeaCow.setSpeed(0.0F);
                this.StellerSeaCow.setXxa(0.0F);
                this.StellerSeaCow.setYya(0.0F);
                this.StellerSeaCow.setZza(0.0F);
            }


        }

        private static float getTurningSpeedFactor(float p_249853_) {
            return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
        }
    }

    static class SeaCowSwimWithPlayerGoal extends Goal {
        private final StellerSeaCow dolphin;
        private final double speedModifier;
        @javax.annotation.Nullable
        private Player player;

        SeaCowSwimWithPlayerGoal(StellerSeaCow pDolphin, double pSpeedModifier) {
            this.dolphin = pDolphin;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.player = this.dolphin.level().getNearestPlayer(StellerSeaCow.SWIM_WITH_PLAYER_TARGETING, this.dolphin);
            if (this.player == null) {
                return false;
            } else {
                return this.player.isSwimming() && this.dolphin.getTarget() != this.player && this.player.getFluidHeight(FluidTags.WATER) < 5;
            }
        }

        public boolean canContinueToUse() {
            return this.player != null && this.player.isSwimming() && this.dolphin.distanceToSqr(this.player) < 256.0D && this.player.getFluidHeight(FluidTags.WATER) < 5;
        }

        public void start() {
            this.player.addEffect(new MobEffectInstance(MMEffects.SEA_COW_SERENITY.get(), 50), this.dolphin);
        }

        public void stop() {
            this.player = null;
            this.dolphin.getNavigation().stop();
        }

        public void tick() {
            this.dolphin.getLookControl().setLookAt(this.player, (float)(this.dolphin.getMaxHeadYRot() + 20), (float)this.dolphin.getMaxHeadXRot());
            if (this.dolphin.distanceToSqr(this.player) < 8.25D) {
                this.dolphin.getNavigation().stop();
            } else {
                this.dolphin.getNavigation().moveTo(this.player, this.speedModifier);
            }

            if (this.player.isSwimming() && this.player.level().random.nextInt(6) == 0) {
                this.player.addEffect(new MobEffectInstance(MMEffects.SEA_COW_SERENITY.get(), 50), this.dolphin);
            }
        }
    }

    public class RandomShallowSwimmingGoal extends RandomStrollGoal {
        public RandomShallowSwimmingGoal(PathfinderMob mob, double speedMod, int interval) {
            super(mob, speedMod, interval);}

        @javax.annotation.Nullable
        protected Vec3 getPosition() {
            return BehaviorUtils.getRandomSwimmablePos(this.mob, 7, 1);
        }
    }

    public class SeaCowBreachGoal extends Goal {
        private final StellerSeaCow mob;

        public SeaCowBreachGoal(StellerSeaCow pMob) {
            this.mob = pMob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return this.mob.isInWater() && this.mob.getRandom().nextInt( 5000) == 0 && !this.mob.isBaby();
        }

        public boolean canContinueToUse() {
            return this.mob.isInWater() && !(this.mob.getLastHurtByMob() != null || this.mob.isFreezing() || this.mob.isOnFire());
        }

        public boolean isInterruptable() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void start() {
            this.mob.findAirPosition();
        }

        public void tick() {
            this.mob.findAirPosition();
            this.mob.moveRelative(0.02F, new Vec3((double)this.mob.xxa, (double)this.mob.yya, (double)this.mob.zza));
            this.mob.move(MoverType.SELF, this.mob.getDeltaMovement());
            if (this.mob.getFluidHeight(FluidTags.WATER) <= 0.5){
                int i;
                for (i=0; i<60; i++){
                    this.mob.getNavigation().stop();
                    this.mob.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
                }
                if (i==60){
                    this.mob.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
                    this.stop();
                }
            }
        }
    }

    private void findAirPosition() {
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.getX() - 1.0D), this.getBlockY(), Mth.floor(this.getZ() - 1.0D),
                Mth.floor(this.getX() + 1.0D), Mth.floor(this.getY() + 8.0D), Mth.floor(this.getZ() + 1.0D));
        BlockPos blockpos = null;

        for(BlockPos blockpos1 : iterable) {
            if (this.givesAir(this.level(), blockpos1)) {
                blockpos = blockpos1;
                break;
            }
        }

        if (blockpos == null) {
            blockpos = BlockPos.containing(this.getX(), this.getY() + 8.0D, this.getZ());
        }
        this.getNavigation().moveTo((double)blockpos.getX(), (double)(blockpos.getY() + 1), (double)blockpos.getZ(), 0.8D);
    }

    private boolean givesAir(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (pLevel.getFluidState(pPos).isEmpty() && blockstate.isPathfindable(pLevel, pPos, PathComputationType.LAND));
    }

    protected SoundEvent getSwimSound() {
        return MMSounds.CREATURE_SWIM.get();
    }

    public boolean canBeCollidedWith() {
        return !this.isEyeInFluid(FluidTags.WATER) && !this.onGround() && !this.isBaby();
    }
    
    protected SoundEvent getAmbientSound() {
        if (this.isEyeInFluid(FluidTags.WATER)){
            return MMSounds.STELLER_IDLE.get();
        }else {
            return MMSounds.DOLPHIN_BLOWHOLE.get();
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.isEyeInFluid(FluidTags.WATER)){
            return MMSounds.STELLER_HURT.get();
        }else {
            return MMSounds.STELLER_LAND_HURT.get();
        }
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.STELLER_DEATH.get();
    }

}
