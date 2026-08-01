package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.serialization.Codec;
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
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.StrollOrSprintAvoidingWaterGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Atrociraptor extends MarvelousAnimal implements NeutralMob, IAnimatedAttacker {

    private int jumpCounter;
    private int shakeCounter;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CHICKEN, Items.RABBIT, MMItems.RAW_DAWN_HORSE.get());

    public Atrociraptor(EntityType<? extends MarvelousAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public final AnimationState jumpingAnimationState = new AnimationState();
    public int jumpAnimationStateTimeout;
    public final AnimationState shakingAnimationState = new AnimationState();
    public int shakingAnimationTimeout;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout;

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Atrociraptor.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Atrociraptor.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Atrociraptor.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Atrociraptor.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        if (p_289448_ instanceof Atrociraptor)
            return false;
        return p_289448_ instanceof Rabbit || p_289448_ instanceof Leptictidium || p_289448_ instanceof DawnHorse;
    };

    public final Predicate<LivingEntity> STARVED_PREY_SELECTOR = (entity) -> {
        return (entity.getBbHeight() * Math.pow(entity.getBbWidth(), 2)) < (this.getBbHeight() * Math.pow(this.getBbWidth(), 2));
    };

    @Override
    public int getMaxYRot() {
        return 45;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(MMTags.Items.ATROCIRAPTOR_FOOD);
    }

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, Ingredient.of(MMTags.Items.ATROCIRAPTOR_FOOD), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new StrollOrSprintAvoidingWaterGoal(this, 1.0D, 1.35));
        this.goalSelector.addGoal(9, new RandomJumpOrShakeGoal(this));

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.25D, true, 7, 8));

        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Rabbit.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Leptictidium.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, DawnHorse.class, false, PREY_SELECTOR));

        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class,
                10, true, false, this::isAngryAt));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte) 0);
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    //variants
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public String getVariantName() {
        if (this.isWraptor())
            return "wraptor";
        return RaptorVariant.byId(this.getVariant()).getSerializedName();
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        this.addPersistentAngerSaveData(compound);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.readPersistentAngerSaveData(this.level(), compound);
    }

    //determines a number when spawning
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        this.setVariant(Util.getRandom(RaptorVariant.values(), this.getRandom()).id());

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean isWraptor() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && (s.toLowerCase().contains("species") || s.toLowerCase().contains("wraptor"));
    }

    public void aiStep() {
        super.aiStep();
        //makes it fall slowly when it's falling
        Vec3 vec3 = this.getDeltaMovement();

        if (!this.onGround() && vec3.y < (-0.1D) && !this.isInWater()) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.8D, 1.0D));
        }
    }

    @Override
    public void tick() {

        if (this.jumpCounter == 0 && this.isJumping()){
            this.setJumping(false);
        }

        if (this.isEffectiveAi() && this.jumpCounter > 0 && ++this.jumpCounter > 10) {
            this.jumpCounter = 0;
            this.setJumping(false);
        }

        if (this.isEffectiveAi() && this.shakeCounter > 0 && ++this.shakeCounter > 59) {
            this.shakeCounter = 0;
            this.setShaking(false);
        }
        super.tick();
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isShaking() || this.isJumping();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Atrociraptor baby = MMEntities.ATROCIRAPTOR.get().create(pLevel);
        Atrociraptor otherParent = (Atrociraptor) pOtherParent;
        if (baby != null)
            baby.setVariant(this.random.nextBoolean() ? Util.getRandom(RaptorVariant.values(), this.getRandom()).id() : this.random.nextBoolean() ? this.getVariant() : otherParent.getVariant());
        return baby;
    }

    @Override
    public boolean canAttack(LivingEntity pLivingentity, TargetingConditions pCondition) {
        if (pLivingentity instanceof Atrociraptor)
            return false;
        return super.canAttack(pLivingentity, pCondition);
    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        if (pTarget instanceof Atrociraptor)
            return false;
        return super.canAttack(pTarget);
    }

    public void setJumping(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    public boolean isJumping() {
        return this.getFlag(32);
    }

    public void setShaking(boolean pStanding) {
        this.setFlag(16, pStanding);
    }

    public boolean isShaking() {
        return this.getFlag(16);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }


    protected void setFlag(int pFlagId, boolean pValue) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (pValue) {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 | pFlagId));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 & ~pFlagId));
        }
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

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.isJumping() && this.jumpAnimationStateTimeout <= 0) {
            this.jumpAnimationStateTimeout = 20;
            this.jumpingAnimationState.start(this.tickCount);
        } else {
            --this.jumpAnimationStateTimeout;
        }

        if (this.isShaking() && this.shakingAnimationTimeout <= 0) {
            this.shakingAnimationTimeout = 60;
            this.shakingAnimationState.start(this.tickCount);
        } else {
            --this.shakingAnimationTimeout;
        }

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;

            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
    }

    public void jumpIfPossible() {
        if (this.isEffectiveAi() && !this.isShaking()) {
            this.jumpCounter = 1;
            this.setJumping(true);
        }
    }

    public void shakeIfPossible() {
        if (this.isEffectiveAi() && !this.isJumping()) {
            this.shakeCounter = 1;
            this.setShaking(true);
        }
    }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, pTime);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean pFromBucket) {
        this.entityData.set(IS_ATTACKING, pFromBucket);
    }

    @Override
    public int attackAnimationTimeout() {
        return this.attackAnimationTimeout;
    }

    @Override
    public void setAttackAnimationTimeout(int attackAnimationTimeout) {
        this.attackAnimationTimeout = attackAnimationTimeout;
    }

    @Override
    public @Nullable SoundEvent getAttackSound() {
        return null;
    }

    public class RandomJumpOrShakeGoal extends Goal {
        private final Atrociraptor mob;
        private int nextStand;

        public RandomJumpOrShakeGoal(Atrociraptor mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            if (this.mob.getRandom().nextBoolean())
                this.mob.jumpIfPossible();
            else
                this.mob.shakeIfPossible();
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            --this.nextStand;
            if (this.nextStand > 0 && this.mob.getRandom().nextInt(this.nextStand) == 0) {
                this.resetStandInterval();
                return this.mob.onGround() && this.mob.getNavigation().isDone();
            } else {
                return false;
            }
        }

        private void resetStandInterval() {
            this.nextStand = 600;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public enum RaptorVariant implements StringRepresentable {
        BLUE(0, "blue"),
        DESERT(1, "desert"),
        RED(2, "red"),
        YELLOW(3, "yellow");

        private static final IntFunction<RaptorVariant> BY_ID = ByIdMap.sparse(RaptorVariant::id, values(), BLUE);
        public static final Codec<RaptorVariant> CODEC = StringRepresentable.fromEnum(RaptorVariant::values);
        final int id;
        private final String name;

        RaptorVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static RaptorVariant byId(int pId) {
            return BY_ID.apply(pId);
        }
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return MMSounds.ATROCIRAPTOR_AMBIENT.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.ATROCIRAPTOR_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return MMSounds.ATROCIRAPTOR_DEATH.get();
    }
}
