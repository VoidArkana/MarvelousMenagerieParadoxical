package net.voidarkana.marvelous_menagerie.common.entity.abomination;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Chud extends Monster {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleNoseState = new AnimationState();
    public final AnimationState rotateAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int idleNoseTimeout = 0;
    private int rotateAnimationTimeout = 0;


    public Chud(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1D, 1.5D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);

        if (this.idleNoseTimeout <= 0) {
            this.idleNoseTimeout = this.random.nextInt(40) + 80;
            this.idleNoseState.start(this.tickCount);
        } else {
            --this.idleNoseTimeout;
        }

        if (this.rotateAnimationTimeout <= 0 && this.isSprinting()) {
            this.rotateAnimationTimeout = 20;
        }

        this.rotateAnimationState.animateWhen(this.rotateAnimationTimeout>0, this.tickCount);

        if (this.rotateAnimationTimeout > 0){
            --this.rotateAnimationTimeout;
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

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
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

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FROG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }
}
