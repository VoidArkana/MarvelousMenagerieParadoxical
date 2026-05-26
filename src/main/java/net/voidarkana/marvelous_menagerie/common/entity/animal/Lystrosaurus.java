package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

public class Lystrosaurus extends MarvelousAnimal implements IEggLayer {

    public final AnimationState layEggAnimationState = new AnimationState();
    private int layEggAnimTimeout = 70;
    public final AnimationState sniffAnimationState = new AnimationState();
    private int sniffAnimTimeout = this.getRandom().nextInt(160) + 160;
    public final AnimationState headTurnAnimationState = new AnimationState();
    private int headTurnAnimTimeout = this.getRandom().nextInt(160) + 160;

    int layEggCounter;

    private static final EntityDataAccessor<Boolean> IS_PREGNANT = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_AUTOLAY_EGGS = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);

    public Lystrosaurus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.FERN, Items.LARGE_FERN);

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0F));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(1, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LayEggGoal(this, 1.0D, MMTags.Blocks.DINOSAUR_NEST, MMBlocks.BOREALOPELTA_EGG, 1d));

        this.goalSelector.addGoal(2, new MarvelousTemptGoal(this, 1.25D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
            @Override
            public boolean canUse() {
                return !Lystrosaurus.this.isInPoseTransition() && super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 20*60*5));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean refuseToMove() {
        return super.refuseToMove() || this.isLayingEgg();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.ARMOR, 2)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.25)
                .add(Attributes.MOVEMENT_SPEED, 0.125F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PREGNANT, false);
        this.entityData.define(IS_LAYING_EGG, false);
        this.entityData.define(CAN_AUTOLAY_EGGS, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsPregnant", this.isPregnant());
        compound.putBoolean("IsLayingEgg", this.isLayingEgg());
        compound.putBoolean("CanAutoLayEggs", this.canAutoLayEggs());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setPregnant(compound.getBoolean("IsPregnant"));
        this.setLayingEgg(compound.getBoolean("IsLayingEgg"));
        this.setCanAutoLayEggs(compound.getBoolean("CanAutoLayEggs"));
    }


    public boolean canAutoLayEggs() {
        return this.entityData.get(CAN_AUTOLAY_EGGS);
    }

    public void setCanAutoLayEggs(boolean pIsLayingEgg) {
        this.entityData.set(CAN_AUTOLAY_EGGS, pIsLayingEgg);
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if(headTurnAnimTimeout <= 0 && !this.getNavigation().isInProgress() && !this.isLayingEgg()) {
            headTurnAnimTimeout = this.getRandom().nextInt(160) * this.getRandom().nextInt();
            headTurnAnimationState.start(this.tickCount);
        } else {
            --this.headTurnAnimTimeout;
        }

        if(sniffAnimTimeout <= 0 && !this.isSprinting() && !this.isInWaterOrBubble() && !this.isLayingEgg()) {
            sniffAnimTimeout = this.getRandom().nextInt(160) * this.getRandom().nextInt();
            sniffAnimationState.start(this.tickCount);
        } else {
            --this.sniffAnimTimeout;
        }

        if(layEggAnimTimeout <= 0 && this.isLayingEgg()) {
            layEggAnimTimeout = 70;
            this.layEggAnimationState.start(this.tickCount);
        } else if (layEggAnimTimeout > 0){
            --this.layEggAnimTimeout;
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (pReason == MobSpawnType.BREEDING || pReason == MobSpawnType.MOB_SUMMONED){
            this.setCanAutoLayEggs(true);
        }
        if (pReason == MobSpawnType.NATURAL){
            this.setCanAutoLayEggs(false);
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public boolean canBeCollidedWith() {
        return (!(this.isInPoseTransition()) && this.isSitting()) || this.isLayingEgg();
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isSitting() && !(this.isInPoseTransition()) && !this.isVehicle();
    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public int getSitDuration() {
        return 20;
    }

    @Override
    public int getStandDuration() {
        return 30;
    }

    @javax.annotation.Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.BOREALOPELTA_DEATH.get();
    }

    @javax.annotation.Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return MMSounds.BOREALOPELTA_HURT.get();
    }

    @javax.annotation.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSounds.BOREALOPELTA_AMBIENT.get();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return FOOD_ITEMS.test(stack);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return MMEntities.LYSTROSAURUS.get().create(pLevel);
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
    public void onEggLaid() {
        this.setLayingEgg(false);
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.PIG_STEP, 0.25F, 1.0F);
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setSprinting(d0 >= 1.25D);
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (this.level().getBlockState(blockpos.below()).is(MMTags.Blocks.DINOSAUR_NEST)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
            }
        }

        if ((this.layEggCounter == 0 || !this.isPregnant()) && this.isLayingEgg()){
            this.setLayingEgg(false);
        }

    }

}
