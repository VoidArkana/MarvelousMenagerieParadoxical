package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class Dodo extends Animal {

    private static final Logger log = LoggerFactory.getLogger(Dodo.class);
    private int eggLayTime;
    private int initialEggTime;
    int prevTicksOffGround;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE, Items.MELON, Items.PUMPKIN);

    public Dodo(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        eggLayTime = random.nextInt(5000) + (5000);
        initialEggTime = eggLayTime;
    }

    private static final int PECK_ANIMATION_TICKS = 36;
    public boolean isFalling;
    
    public final AnimationState flappingAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState lookAnimationState = new AnimationState();
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState shakingAnimationState = new AnimationState();

    public int lookAnimationTimeout;
    public int shakingAnimationTimeout;
    public int peckingAnimationTimeout;


    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));;
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new Dodo.DestroyMelonAndPumpkinGoal(this));
        this.goalSelector.addGoal(5, new Dodo.DodoLookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new Dodo.DodoRandomLookAroundGoal(this));
        super.registerGoals();
    }

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PECKING_TIME = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TICKS_OFF_GROUND = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> PECKING = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_PECK = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(PECKING_TIME, 0);
        this.entityData.define(TICKS_OFF_GROUND, 0);
        this.entityData.define(PECKING, false);
        this.entityData.define(CAN_PECK, true);
    }

    //variants
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getTicksOffGround() {
        return this.entityData.get(TICKS_OFF_GROUND);
    }

    public void setTicksOffGround(int variant) {
        this.entityData.set(TICKS_OFF_GROUND, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("isPecking", this.getIsPecking());
        compound.putBoolean("canPeck", this.getCanPeck());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setIsPecking(compound.getBoolean("isPecking"));
        this.setCanPeck(compound.getBoolean("canPeck"));
    }

    //determines variant based on the number determined at spawn
    public void determineVariant(int variantChange){
        /*if(variantChange <= 33){
            this.setVariant(2);
        }else */
        if(variantChange <= 50){
            this.setVariant(1);
        }else{
            this.setVariant(0);
        }
    }

    //determines a number when spawning
    @Override
    @org.jetbrains.annotations.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn,
                                        DifficultyInstance difficultyIn,
                                        MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnDataIn,
                                        @org.jetbrains.annotations.Nullable CompoundTag dataTag) {
        int variantChange = this.random.nextInt(0, 100);
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.determineVariant(variantChange);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    //custom name
    public boolean isNugget() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("nugget");
    }

    //is pecking
    public boolean getIsPecking() {
        return this.entityData.get(PECKING);
    }

    public void setIsPecking(boolean pecking) {
        this.entityData.set(PECKING, pecking);
    }

    //can peck
    public boolean getCanPeck() {
        return (Boolean)this.entityData.get(CAN_PECK);
    }

    public void setCanPeck(boolean pecking) {
        this.entityData.set(CAN_PECK, pecking);
    }

    //eye height
    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? pSize.height * 0.85F : pSize.height * 0.92F;
    }

    public void aiStep() {
        super.aiStep();
        //makes it fall slowly when it's falling
        Vec3 vec3 = this.getDeltaMovement();

        if (!this.onGround() && vec3.y < (-0.1D) && !this.isInWater()) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));

            if (!this.level().isClientSide()){
                if (this.getTicksOffGround() < 5){
                    this.prevTicksOffGround = this.getTicksOffGround();
                    this.setTicksOffGround(this.prevTicksOffGround+1);
                }
            }

        } else {

            if (!this.level().isClientSide()){
                if (this.getTicksOffGround() > 0){
                    this.prevTicksOffGround = this.getTicksOffGround();
                    this.setTicksOffGround(this.prevTicksOffGround-1);
                }
            }

        }

        if (isAlive() && !isBaby() && --eggLayTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.FEATHER);

            this.eggLayTime = random.nextInt(6000) + (6000);
            initialEggTime = eggLayTime;
        }

    }

    protected SoundEvent getAmbientSound() {
        return MMSounds.DODO_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.DODO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.DODO_DEATH.get();
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Dodo baby = MMEntities.DODO.get().create(pLevel);
        Dodo otherParent = (Dodo) pOtherParent;
        baby.setVariant(this.random.nextBoolean() ? this.getVariant() : otherParent.getVariant());
        return baby;
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (itemStack.is(Items.GLISTERING_MELON_SLICE) && this.getCanPeck()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanPeck(false);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        if ((itemStack.is(Items.MELON_SLICE) || itemStack.is(Items.PUMPKIN) || itemStack.is(Items.MELON)) && !this.getCanPeck()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanPeck(true);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    static class DestroyMelonAndPumpkinGoal extends MoveToBlockGoal {
        private final Dodo dodo;
        private int eatAnimationTick;

        public void start() {
            this.eatAnimationTick = PECK_ANIMATION_TICKS;
            this.dodo.level().broadcastEntityEvent(this.dodo, (byte)10);
        }

        public DestroyMelonAndPumpkinGoal(Dodo pDodo) {
            super(pDodo, 1F, 16);
            this.dodo = pDodo;
        }

        public boolean canUse() {
            if (!this.dodo.getCanPeck()){
                return false;
            }
            return super.canUse();
        }

        public void tick() {
            super.tick();
            //this.dodo.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.dodo.getMaxHeadXRot());
            if (this.isReachedTarget()) {

                if (!this.dodo.getIsPecking()){
                    this.dodo.setIsPecking(true);
                }

                if (this.dodo.getNavigation().getPath() != null) {
                    this.dodo.getNavigation().stop();
                }

                if(this.eatAnimationTick == 12){
                    Level level = this.dodo.level();
                    BlockPos blockpos = this.blockPos;
                    BlockState blockstate = level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    if (block instanceof MelonBlock || block instanceof PumpkinBlock) {
                        level.destroyBlock(blockpos, true, this.dodo);
                    }

                    this.nextStartTick = 10;
                    this.dodo.setIsPecking(false);
                } else if (this.eatAnimationTick == 0) {
                    this.dodo.setIsPecking(false);
                }

                this.eatAnimationTick--;
            }
        }

        public void stop() {
            this.nextStartTick = 10;
            this.eatAnimationTick = 0;
            this.dodo.setIsPecking(false);
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return (blockstate.is(Blocks.MELON) || blockstate.is(Blocks.PUMPKIN)) && pLevel.isEmptyBlock(pPos.above());
        }

    }

    public class DodoRandomLookAroundGoal extends Goal {
        private final Mob mob;
        private double relX;
        private double relZ;
        private int lookTime;

        public DodoRandomLookAroundGoal(Mob mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return !Dodo.this.getIsPecking() && this.mob.getRandom().nextFloat() < 0.02F;
        }

        public boolean canContinueToUse() {
            return !Dodo.this.getIsPecking() && this.lookTime >= 0;
        }

        public void start() {
            double d0 = (Math.PI * 2D) * this.mob.getRandom().nextDouble();
            this.relX = Math.cos(d0);
            this.relZ = Math.sin(d0);
            this.lookTime = 20 + this.mob.getRandom().nextInt(20);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            --this.lookTime;
            this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
        }
    }


    public class DodoLookAtPlayerGoal extends Goal {
        public static final float DEFAULT_PROBABILITY = 0.02F;
        protected final Mob mob;
        @javax.annotation.Nullable
        protected Entity lookAt;
        protected final float lookDistance;
        private int lookTime;
        protected final float probability;
        private final boolean onlyHorizontal;
        protected final Class<? extends LivingEntity> lookAtType;
        protected final TargetingConditions lookAtContext;

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            this(pMob, pLookAtType, pLookDistance, 0.02F);
        }

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability) {
            this(pMob, pLookAtType, pLookDistance, pProbability, false);
        }

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability, boolean pOnlyHorizontal) {
            this.mob = pMob;
            this.lookAtType = pLookAtType;
            this.lookDistance = pLookDistance;
            this.probability = pProbability;
            this.onlyHorizontal = pOnlyHorizontal;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
            if (pLookAtType == Player.class) {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance).selector((p_25531_) -> {
                    return EntitySelector.notRiding(pMob).test(p_25531_);
                });
            } else {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance);
            }

        }

        public boolean canUse() {

            if (Dodo.this.getIsPecking()){
                return false;
            }
            if (this.mob.getRandom().nextFloat() >= this.probability) {
                return false;
            } else {
                if (this.mob.getTarget() != null) {
                    this.lookAt = this.mob.getTarget();
                }

                if (this.lookAtType == Player.class) {
                    this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                } else {
                    this.lookAt = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.lookAtType, this.mob.getBoundingBox().inflate((double)this.lookDistance, 3.0D, (double)this.lookDistance), (p_148124_) -> {
                        return true;
                    }), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                }

                return this.lookAt != null;
            }
        }

        public boolean canContinueToUse() {
            if (Dodo.this.getIsPecking()){
                return false;
            }
            if (!this.lookAt.isAlive()) {
                return false;
            } else if (this.mob.distanceToSqr(this.lookAt) > (double)(this.lookDistance * this.lookDistance)) {
                return false;
            } else {
                return this.lookTime > 0;
            }
        }

        public void start() {
            this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
        }

        public void stop() {
            this.lookAt = null;
        }

        public void tick() {
            if (this.lookAt.isAlive()) {
                double d0 = this.onlyHorizontal ? this.mob.getEyeY() : this.lookAt.getEyeY();
                this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
                --this.lookTime;
            }
        }
    }
    
    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 >= 1.25D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
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

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos) && CommonConfig.NATURAL_SPAWNS.get();
    }

    @Override
    public void tick() {
        //System.out.println(this.getTicksOffGround());


        super.tick();
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }

    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if (this.lookAnimationTimeout <= 0) {
            this.lookAnimationTimeout = this.random.nextInt(320) + 160;
            this.lookAnimationState.start(this.tickCount);
        } else {
            --this.lookAnimationTimeout;
        }

        if (this.getIsPecking() && this.peckingAnimationTimeout <= 0) {
            this.peckingAnimationTimeout = 30;
            this.peckingAnimationState.start(this.tickCount);
        } else {
            --this.peckingAnimationTimeout;
        }

        if ((eggLayTime <= 20 && eggLayTime > 0) || (initialEggTime/2 <= eggLayTime && initialEggTime/2 > eggLayTime-20)
                && this.shakingAnimationTimeout <= 0) {
            this.shakingAnimationTimeout = 20;
            this.shakingAnimationState.start(this.tickCount);
        } else {
            --this.shakingAnimationTimeout;
        }

        this.flappingAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }
}
