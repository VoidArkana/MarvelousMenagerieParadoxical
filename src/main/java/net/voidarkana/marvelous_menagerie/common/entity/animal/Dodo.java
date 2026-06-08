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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
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
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.RandomlySitUpOrDownGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import org.jetbrains.annotations.Nullable;

public class Dodo extends MarvelousAnimal {

    private int eggLayTime;
    private int initialEggTime;
    private int peckCounter;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE, Items.MELON, Items.PUMPKIN);

    public Dodo(EntityType<? extends MarvelousAnimal> entityType, Level level) {
        super(entityType, level);
        eggLayTime = random.nextInt(5000) + (5000);
        initialEggTime = eggLayTime;
    }

    private static final int PECK_ANIMATION_TICKS = 36;
    
    public final AnimationState lookAnimationState = new AnimationState();
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState shakingAnimationState = new AnimationState();

    public int lookAnimationTimeout = this.random.nextInt(320) + 160;
    public int shakingAnimationTimeout;
    public int peckingAnimationTimeout;

    @Override
    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

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
        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 20*60*5));
        this.goalSelector.addGoal(5, new Dodo.DodoLookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new Dodo.DodoRandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new Dodo.RandomPeckGoal(this));
    }

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PECKING_TIME = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CAN_PECK = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Dodo.class, EntityDataSerializers.BYTE);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(PECKING_TIME, 0);
        this.entityData.define(CAN_PECK, true);

        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    //variants
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public String getVariantName(){
        if (this.isSpawn())
            return "spawn";
        if (this.isNugget())
            return "nugget";
        return this.getVariant() == 0 ? "grey" : "brown";
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("canPeck", this.getCanPeck());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
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

    public boolean isSpawn() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("spawn");
    }

    public boolean isNika() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("nika");
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
        }

        if (isAlive() && !isBaby() && --eggLayTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(Items.FEATHER);

            this.eggLayTime = random.nextInt(6000) + (6000);
            initialEggTime = eggLayTime;
        }

    }

    @Override
    public void tick() {
        super.tick();

        if (this.isEffectiveAi() && this.peckCounter > 0 && ++this.peckCounter > 30) {
            this.peckCounter = 0;
            this.setPecking(false);
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

    //SIT STUFF
    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isSitting() || !(this.isInPoseTransition()) || !this.isVehicle() && super.canBeLeashed(player);
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
        return 15;
    }

    //PECKING STUFF
    public void setPecking(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isPecking() {
        return this.getFlag(32);
    }

    protected void setFlag(int pFlagId, boolean pValue) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (pValue) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 | pFlagId));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 & ~pFlagId));
        }

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

            if (this.isReachedTarget()) {

                if (!this.dodo.isPecking()){
                    this.dodo.setPecking(true);
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
                }

                this.eatAnimationTick--;
            }
        }

        public void stop() {
            this.nextStartTick = 10;
            this.eatAnimationTick = 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return (blockstate.is(Blocks.MELON) || blockstate.is(Blocks.PUMPKIN)) && pLevel.isEmptyBlock(pPos.above());
        }

    }

    public class DodoRandomLookAroundGoal extends RandomLookAroundGoal {
        public DodoRandomLookAroundGoal(Mob pMob) {
            super(pMob);
        }

        public boolean canUse() {
            if (Dodo.this.isPecking()){
                return false;
            }
            return super.canUse();
        }

        public boolean canContinueToUse() {
            if (Dodo.this.isPecking()){
                return false;
            }
            return super.canUse();
        }
    }


    public class DodoLookAtPlayerGoal extends LookAtPlayerGoal {
        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            super(pMob, pLookAtType, pLookDistance);
        }

        public boolean canUse() {
            if (Dodo.this.isPecking()){
                return false;
            }
            return super.canUse();
        }

        public boolean canContinueToUse() {
            if (Dodo.this.isPecking()){
                return false;
            }
            return super.canUse();
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

        if (this.lookAnimationTimeout <= 0) {
            this.lookAnimationTimeout = this.random.nextInt(320) + 160;
            this.lookAnimationState.start(this.tickCount);
        } else {
            --this.lookAnimationTimeout;
        }

        if (this.isPecking() && this.peckingAnimationTimeout <= 0) {
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
    }

    public void peckIfPossible() {
        if (this.isEffectiveAi()) {
            this.peckCounter = 1;
            this.setPecking(true);
        }
    }

    public class RandomPeckGoal extends Goal {
        private final Dodo mob;
        private int nextStand;

        public RandomPeckGoal(Dodo mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            this.mob.peckIfPossible();
            this.playStandSound();
        }

        private void playStandSound() {
            this.mob.playSound(this.mob.getAmbientSound(), 1, this.mob.getVoicePitch());
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            --this.nextStand;
            if (this.nextStand > 0 && this.mob.getRandom().nextInt(this.nextStand) == 0 ) {
                this.resetStandInterval();
                return !this.mob.isSitting() && this.mob.onGround() && this.mob.getNavigation().isDone();
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
}
