package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import org.jetbrains.annotations.Nullable;

public class Apthoroblattina extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleRotRightState = new AnimationState();
    public final AnimationState idleRotLeftState = new AnimationState();
    public final AnimationState idleRotBothState = new AnimationState();
    public final AnimationState idleVibrateState = new AnimationState();
    public final AnimationState fallFlyState = new AnimationState();
    public final AnimationState johnAnimationState = new AnimationState();

    private int idleRotTimeout = 0;
    private int idleVibrateTimeout = 0;

    private boolean isJohn;
    @javax.annotation.Nullable
    private BlockPos jukebox;

    public Apthoroblattina(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new RoachMoveControl();
        this.lookControl = new RoachLookControl();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5F));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.HONEYCOMB), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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

    private void setupAnimationStates() {

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

        this.johnAnimationState.animateWhen(this.isJohn(), this.tickCount);

        this.fallFlyState.animateWhen(!this.onGround() && !this.isBaby(), this.tickCount);

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
    public void tick() {
        super.tick();
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
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isFallFlying() {
        return super.isFallFlying();
    }

    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.isJohn = false;
            this.jukebox = null;
        }
        super.aiStep();
        if (isGlidingDown()) {
            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }
    }
    
    public boolean isGlidingDown(){
        Vec3 vec3 = this.getDeltaMovement();
        return !this.onGround() && vec3.y < 0.0D && !this.isBaby();
    }

    public void setRecordPlayingNearby(BlockPos pPos, boolean pIsPartying) {
        this.jukebox = pPos;
        this.isJohn = pIsPartying;
    }

    public boolean isJohn() {
        return this.isJohn;
    }


    class RoachMoveControl extends MoveControl {
        public RoachMoveControl() {
            super(Apthoroblattina.this);
        }

        public void tick() {
            if (Apthoroblattina.this.canMove()) {
                super.tick();
            }

        }
    }

    public class RoachLookControl extends LookControl {
        public RoachLookControl() {
            super(Apthoroblattina.this);
        }

        public void tick() {
            if (!Apthoroblattina.this.isJohn()) {
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
}
