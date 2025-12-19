package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.FishBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.boids.BoidGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.boids.LimitSpeedAndLookInVelocityDirectionGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.boids.StayInWaterGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

//TODO: only join leader in boids if the school is of the right size
public class Arandaspis extends BreedableWaterAnimal implements Bucketable {


    public final AnimationState idleAnimationState = new AnimationState();

    @javax.annotation.Nullable
    public Arandaspis leader;
    public int schoolSize = 1;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Arandaspis.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Arandaspis.class, EntityDataSerializers.INT);

    public Arandaspis(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());

        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, this.foodIngredients(), false));
        this.goalSelector.addGoal(1, new FishBreedGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(1, new OrganizeBoidsGoal(this));

        this.goalSelector.addGoal(2, new BoidGoal(this, 0.2f, 0.4f, 8 / 20f, 1 / 20f));
        this.goalSelector.addGoal(2, new StayInWaterGoal(this));
        this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.65f));

        this.goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1, 10));
        //this.goalSelector.addGoal(6, new FishSwimGoal(this));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setVariant(pCompound.getInt("Variant"));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    protected void waterSwimSound() {}

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }


    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.ARANDASPIS_BUCKET.get());
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());

        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putBoolean("CanGrow", this.getCanGrowUp());
        compoundnbt.putInt("Variant", this.getVariant());

        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);

        this.setVariant(pTag.getInt("Variant"));
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }

        this.setCanGrowUp(pTag.getBoolean("CanGrow"));
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Arandaspis otherParent = (Arandaspis) pOtherParent;
        Arandaspis baby = MMEntities.ARANDASPIS.get().create(pLevel);
        baby.setFromBucket(true);
        baby.setVariant(this.getRandom().nextBoolean() ? this.getVariant() : otherParent.getVariant());
        return baby;
    }

    public static String getVariantName(int variantNumber) {
        return  switch(variantNumber){
            case 1 -> "blue";
            default -> "green";
        };
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    public SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return 10;
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public Arandaspis startFollowing(Arandaspis pLeader) {
        this.leader = pLeader;
        pLeader.addFollower();
        return pLeader;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public void tick() {
        super.tick();
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends Arandaspis> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);
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

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }
    }

    public void addFollowers(Stream<? extends Arandaspis> pFollowers) {
        pFollowers.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((p_27536_) -> {
            p_27536_.startFollowing(this);
        });
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);

        if (pSpawnData == null) {
            pSpawnData = new Arandaspis.SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((Arandaspis.SchoolSpawnGroupData)pSpawnData).leader);
        }

        if (pReason==MobSpawnType.TRIGGERED || pReason==MobSpawnType.BREEDING){
            this.setFromBucket(true);
        }

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("CanGrowUp", 3)) {
            if (pDataTag.contains("Age")) {
                this.setAge(pDataTag.getInt("Age"));}
            this.setVariant(pDataTag.getInt("Variant"));
            this.setFromBucket(pDataTag.getBoolean("CanGrowUp"));
            this.setFromBucket(true);
        }else {
            this.setVariant(this.getRandom().nextInt(2));
        }


        return pSpawnData;
    }

    public static class SchoolSpawnGroupData extends AgeableFishGroupData {
        public final Arandaspis leader;

        public SchoolSpawnGroupData(Arandaspis pLeader) {
            super(true);
            this.leader = pLeader;
        }
    }

    public class OrganizeBoidsGoal extends Goal {
        private static final int INTERVAL_TICKS = 200;
        private final Arandaspis mob;
        private int timeToRecalcPath;
        private int nextStartTick;

        public OrganizeBoidsGoal(Arandaspis pFish) {
            this.mob = pFish;
            this.nextStartTick = this.nextStartTick(pFish);
        }

        protected int nextStartTick(Arandaspis pTaskOwner) {
            return reducedTickDelay(200 + pTaskOwner.getRandom().nextInt(200) % 20);
        }

        public boolean canUse() {
            if (this.mob.hasFollowers()) {
                return false;
            } else if (this.mob.isFollower()) {
                return true;
            } else if (this.nextStartTick > 0) {
                --this.nextStartTick;
                return false;
            } else {
                this.nextStartTick = this.nextStartTick(this.mob);
                Predicate<Arandaspis> predicate = (p_25258_) -> {
                    return p_25258_.canBeFollowed() || !p_25258_.isFollower();
                };
                List<? extends Arandaspis> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
                Arandaspis abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(Arandaspis::canBeFollowed).findAny(), this.mob);
                abstractschoolingfish.addFollowers(list.stream().filter((p_25255_) -> {
                    return !p_25255_.isFollower();
                }));
                return this.mob.isFollower();
            }
        }

        public boolean canContinueToUse() {
            return this.mob.isFollower() && this.mob.inRangeOfLeader();
        }

        public void start() {
            this.timeToRecalcPath = 0;
        }

        public void stop() {
            this.mob.stopFollowing();
        }

//        public void tick() {
//            if (--this.timeToRecalcPath <= 0) {
//                this.timeToRecalcPath = this.adjustedTickDelay(10);
//                this.mob.pathToLeader();
//            }
//        }
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final Arandaspis fish;

        public FishSwimGoal(Arandaspis boidFish) {
            super(boidFish, 1.0, 10);
            this.fish = boidFish;
        }

        @Override
        public boolean canUse() {
            return !this.fish.isFollower() && !this.fish.hasFollowers() && super.canUse();
        }
    }
}
