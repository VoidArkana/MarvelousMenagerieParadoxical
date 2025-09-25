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
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.config.ModConfig;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class Apthoroblattina extends Animal {

    protected static final EntityDataAccessor<Boolean> IS_JOHN = SynchedEntityData.defineId(Apthoroblattina.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleRotRightState = new AnimationState();
    public final AnimationState idleRotLeftState = new AnimationState();
    public final AnimationState idleRotBothState = new AnimationState();
    public final AnimationState idleVibrateState = new AnimationState();
    public final AnimationState fallFlyState = new AnimationState();
    public final AnimationState johnAnimationState = new AnimationState();

    private int idleRotTimeout = 0;
    private int idleVibrateTimeout = 0;
    int prevTicksOffGround;

    @javax.annotation.Nullable
    private BlockPos jukebox;

    private static final EntityDataAccessor<Integer> TICKS_OFF_GROUND = SynchedEntityData.defineId(Apthoroblattina.class, EntityDataSerializers.INT);

    public Apthoroblattina(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.lookControl = new Apthoroblattina.RoachLookControl(this);
        this.moveControl = new Apthoroblattina.RoachMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5F));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new DanceParty(this));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.HONEYCOMB), false)
        {
            @Override
            public void start() {
                if (Apthoroblattina.this.isJohn()){
                    Apthoroblattina.this.setIsJohn(false);
                }
                super.start();
            }
        });
        this.goalSelector.addGoal(4, new RoachWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                if (Apthoroblattina.this.isJohn()){
                    return false;
                }
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                if (Apthoroblattina.this.isJohn()){
                    return false;
                }
                return super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                if (Apthoroblattina.this.isJohn()){
                    return false;
                }
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                if (Apthoroblattina.this.isJohn()){
                    return false;
                }
                return super.canContinueToUse();
            }
        });
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_JOHN, false);
        this.entityData.define(TICKS_OFF_GROUND, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setIsJohn(pCompound.getBoolean("IsJohn"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsJohn", this.isJohn());
    }

    public int getTicksOffGround() {
        return this.entityData.get(TICKS_OFF_GROUND);
    }

    public void setTicksOffGround(int variant) {
        this.entityData.set(TICKS_OFF_GROUND, variant);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.HONEYCOMB);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return MMEntities.ROACH.get().create(p_146743_);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isJohn()) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            pTravelVector = Vec3.ZERO;
            super.travel(pTravelVector);
        } else {
            super.travel(pTravelVector);
        }
    }

    private void setupAnimationStates() {

        if (!this.isJohn()){
            this.idleAnimationState.animateWhen(this.isAlive() && ((this.onGround() && !this.isBaby()) || this.isBaby()),
                    this.tickCount);

            if (!this.walkAnimation.isMoving()){
                if (this.idleRotTimeout <= 0) {

                    this.idleRotTimeout = this.random.nextInt(30) + 130;
                    int rot = this.getRandom().nextInt(3);

                    if (rot == 0)
                        this.idleRotRightState.start(this.tickCount);
                    else if (rot == 1)
                        this.idleRotLeftState.start(this.tickCount);
                    else
                        this.idleRotBothState.start(this.tickCount);

                } else {
                    --this.idleRotTimeout;
                }
            }

            if (this.idleVibrateTimeout <= 0) {
                this.idleVibrateTimeout = this.random.nextInt(40) + 80;

                this.idleVibrateState.start(this.tickCount);

            } else {
                --this.idleVibrateTimeout;
            }

            this.fallFlyState.animateWhen(!this.onGround() && !this.isBaby(), this.tickCount);
        }

        this.johnAnimationState.animateWhen(this.isAlive() && this.isJohn(), this.tickCount);


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
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isJohn()){
            this.setIsJohn(false);
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void tick() {
        super.tick();

        if ((this.isInWater() || this.isInLove()) && this.isJohn()){
            this.setIsJohn(false);
        }

        if (this.isJohn()){
            this.getNavigation().stop();
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
        }

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.ARTHROPOD_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.ARTHROPOD_DEATH.get();
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.05F, this.getVoicePitch());
    }

    @Override
    public boolean isFallFlying() {
        return super.isFallFlying();
    }

    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.setIsJohn(false);
            this.jukebox = null;
        }
        super.aiStep();
        if (isGlidingDown()) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));

            if (!this.level().isClientSide()){
                if (this.getTicksOffGround() < 5){
                    this.prevTicksOffGround = this.getTicksOffGround();
                    this.setTicksOffGround(this.prevTicksOffGround+1);
                }
            }
        }else{
            if (!this.level().isClientSide()){
                if (this.getTicksOffGround() > 0){
                    this.prevTicksOffGround = this.getTicksOffGround();
                    this.setTicksOffGround(this.prevTicksOffGround-1);
                }
            }
        }
    }
    
    public boolean isGlidingDown(){
        Vec3 vec3 = this.getDeltaMovement();
        return !this.onGround() && vec3.y < 0.0D && !this.isBaby();
    }

    public void setRecordPlayingNearby(BlockPos pPos, boolean pIsPartying) {
        this.jukebox = pPos;
        this.setIsJohn(pIsPartying);
    }

    public boolean isJohn() {
        return this.entityData.get(IS_JOHN);
    }

    public void setIsJohn(boolean isJohn) {
        this.entityData.set(IS_JOHN, isJohn);
    }


    class RoachMoveControl extends MoveControl {
        Apthoroblattina mob;
        public RoachMoveControl(Apthoroblattina pMob) {
            super(pMob);
            this.mob = pMob;
        }

        public void tick() {
            if (!mob.isJohn()) super.tick();
        }
    }

    public class RoachLookControl extends LookControl {
        Apthoroblattina mob;

        public RoachLookControl(Apthoroblattina pMob) {
            super(pMob);
            this.mob = pMob;
        }

        public void tick() {
            if (!mob.isJohn()) {
                super.tick();
            }
        }
    }

    boolean canMove() {
        return !this.isJohn();
    }

    protected boolean isImmobile() {
        return this.isDeadOrDying() || this.isJohn();
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 >= 1.33D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public class DanceParty extends Goal {
        private final Apthoroblattina mob;

        public DanceParty(Apthoroblattina pMob) {
            this.mob = pMob;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public boolean canUse() {
            if (this.mob.isInWaterOrBubble()) {
                return false;
            } else if (!this.mob.onGround()) {
                return false;
            } else {
                return this.mob.isJohn();
            }
        }

        public void start() {
            this.mob.getNavigation().stop();
        }

        @Override
        public void stop() {
            this.mob.setIsJohn(false);
        }
    }

    public class RoachWanderGoal extends WaterAvoidingRandomStrollGoal {

        private Apthoroblattina mob;

        public RoachWanderGoal(Apthoroblattina pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.mob = pMob;
        }

        @Override
        public boolean canUse() {
            if (mob.isJohn()){
                return false;
            }
            return super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            if (this.mob.isJohn()){
                this.mob.getNavigation().stop();
                return false;
            }
            return super.canContinueToUse();
        }
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return pLevel.getBlockState(pPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos) && CommonConfig.NATURAL_SPAWNS.get();
    }

}
