package net.voidarkana.marvelous_menagerie.common.entity.abomination;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.Abomination;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Molten extends Abomination implements IAnimatedAttacker {

    public final AnimationState biteAttackAnimationState = new AnimationState();
    public final AnimationState slamAttackAnimationState = new AnimationState();
    public final AnimationState swirlAttackAnimationState1 = new AnimationState();
    public final AnimationState swirlAttackAnimationState2 = new AnimationState();
    public final AnimationState breatheFireAnimationState = new AnimationState();
    public final AnimationState shootFireballAnimationState = new AnimationState();
    public int attackAnimationTimeout;
    public int shootFireballAnimationTimeout;
    public int breatheFireAnimationTimeout;

    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Molten.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SHOOTING_FIREBALL = SynchedEntityData.defineId(Molten.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_BREATHING_FIRE = SynchedEntityData.defineId(Molten.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> WANTS_TO_SHOOT_FIREBALL = SynchedEntityData.defineId(Molten.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> WANTS_TO_BREATHE_FIRE = SynchedEntityData.defineId(Molten.class, EntityDataSerializers.BOOLEAN);

    public Molten(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(1, new MoltenMeeleeAttackGoal(this, 1.25D,
                true, 12, 18));
        this.goalSelector.addGoal(1, new MoltenShootFireballGoal(this));
        this.goalSelector.addGoal(1, new MoltenFireBreathingGoal(this));

        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1, 32.0F));

        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75F)
                .add(Attributes.ATTACK_DAMAGE, 7.0)
                .add(Attributes.FOLLOW_RANGE, 48)
                .add(Attributes.ARMOR, 10);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(IS_SHOOTING_FIREBALL, false);
        this.entityData.define(IS_BREATHING_FIRE, false);
        this.entityData.define(WANTS_TO_SHOOT_FIREBALL, false);
        this.entityData.define(WANTS_TO_BREATHE_FIRE, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsAttacking", this.isAttacking());
        pCompound.putBoolean("IsShootingFireball", this.isShootingFireball());
        pCompound.putBoolean("IsBreathingFire", this.isBreathingFire());
        pCompound.putBoolean("WantsToShootFireball", this.wantsToShootFireball());
        pCompound.putBoolean("WantsToBreatheFire", this.wantsToBreatheFire());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAttacking(pCompound.getBoolean("IsAttacking"));
        this.setBreathingFire(pCompound.getBoolean("IsBreathingFire"));
        this.setShootingFireball(pCompound.getBoolean("IsShootingFireball"));
        this.setWantsToShootFireball(pCompound.getBoolean("WantsToGrab"));
        this.setWantsToBreatheFire(pCompound.getBoolean("IsGrabbing"));
    }

    public int fireballFollowRange(){
        return 48;
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public boolean isShootingFireball() {
        return this.entityData.get(IS_SHOOTING_FIREBALL);
    }

    public void setShootingFireball(boolean attacking) {
        this.entityData.set(IS_SHOOTING_FIREBALL, attacking);
    }

    public boolean isBreathingFire() {
        return this.entityData.get(IS_BREATHING_FIRE);
    }

    public void setBreathingFire(boolean attacking) {
        this.entityData.set(IS_BREATHING_FIRE, attacking);
    }

    public boolean wantsToShootFireball() {
        return this.entityData.get(WANTS_TO_SHOOT_FIREBALL);
    }

    public void setWantsToShootFireball(boolean attacking) {
        this.entityData.set(WANTS_TO_SHOOT_FIREBALL, attacking);
    }

    public boolean wantsToBreatheFire() {
        return this.entityData.get(WANTS_TO_BREATHE_FIRE);
    }

    public void setWantsToBreatheFire(boolean attacking) {
        this.entityData.set(WANTS_TO_BREATHE_FIRE, attacking);
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
    public void tick() {
        super.tick();

        if (!this.isEyeInFluid(FluidTags.WATER) && !this.wantsToShootFireball() && !this.wantsToBreatheFire() && this.random.nextInt(50)==0 && this.getTarget() != null){
            if (this.getRandom().nextBoolean()){
                this.setWantsToBreatheFire(true);
            }else{
                this.setWantsToShootFireball(true);
            }
        }else if ((this.wantsToBreatheFire() || this.wantsToBreatheFire()) && (this.getTarget() == null || this.isEyeInFluid(FluidTags.WATER))){
            if (this.wantsToBreatheFire())
                this.setWantsToBreatheFire(false);
            if (this.wantsToShootFireball())
                this.setWantsToShootFireball(false);
        }

        if (this.level().isClientSide){

            Vec3 vec3 = this.getViewVector(0.0F);
            float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
            float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
            float f2 = 1.2F - this.random.nextFloat() * 0.7F;

            if (this.isEyeInFluid(FluidTags.WATER)){
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.BUBBLE,
                            this.getX() + vec3.x * (double)f2 + (double)f,
                            this.getY() + vec3.y + 1.75,
                            this.getZ() + vec3.z * (double)f2 + (double)f1,
                            0.0D, 0.0D, 0.0D);

                    this.level().addParticle(ParticleTypes.BUBBLE,
                            this.getX() + vec3.x * (double)f2 - (double)f,
                            this.getY() + vec3.y + 1.75,
                            this.getZ() + vec3.z * (double)f2 - (double)f1,
                            0.0D, 0.0D, 0.0D);
                }
            }else {
                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.SMOKE,
                            this.getX() + vec3.x * (double)f2 + (double)f,
                            this.getY() + vec3.y + 1.75,
                            this.getZ() + vec3.z * (double)f2 + (double)f1,
                            0.0D, 0.0D, 0.0D);

                    this.level().addParticle(ParticleTypes.SMOKE,
                            this.getX() + vec3.x * (double)f2 - (double)f,
                            this.getY() + vec3.y + 1.75,
                            this.getZ() + vec3.z * (double)f2 - (double)f1,
                            0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    public void setupAnimationStates(){

        super.setupAnimationStates();

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            switch (this.getRandom().nextInt(4)){
                case 1:
                    slamAttackAnimationState.start(this.tickCount);
                    break;
                case 2:
                    swirlAttackAnimationState1.start(this.tickCount);
                    break;
                case 3:
                    swirlAttackAnimationState2.start(this.tickCount);
                    break;
                default:
                    biteAttackAnimationState.start(this.tickCount);
            }
        } else {
            --this.attackAnimationTimeout;
        }

        if(this.isBreathingFire() && breatheFireAnimationTimeout <= 0) {
            breatheFireAnimationTimeout = 60;
            breatheFireAnimationState.start(this.tickCount);
        } else {
            --this.breatheFireAnimationTimeout;
        }

        if(this.isShootingFireball() && shootFireballAnimationTimeout <= 0) {
            shootFireballAnimationTimeout = 24;
            shootFireballAnimationState.start(this.tickCount);
        } else {
            --this.shootFireballAnimationTimeout;
        }
    }

    static class MoltenShootFireballGoal extends Goal {
        private final Molten molten;
        public int chargeTime;

        public MoltenShootFireballGoal(Molten pMolten) {
            this.molten = pMolten;
        }

        public boolean canUse() {
            return this.molten.getTarget() != null && this.molten.wantsToShootFireball();
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            this.molten.setShootingFireball(false);
            this.molten.setWantsToShootFireball(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.molten.getTarget();
            if (livingentity != null) {


                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.molten) < 4096.0D && this.molten.hasLineOfSight(livingentity)) {

                    this.molten.lookAt(livingentity, 10, 10);

                    this.molten.setShootingFireball(true);
                    Level level = this.molten.level();
                    ++this.chargeTime;

                    if (this.chargeTime == 10) {
                        double d1 = 4.0D;
                        Vec3 vec3 = this.molten.getViewVector(1.0F);
                        double d2 = livingentity.getX() - (this.molten.getX() + vec3.x);
                        double d3 = livingentity.getY(0.5D) - (0.5D + this.molten.getY(0.5D));
                        double d4 = livingentity.getZ() - (this.molten.getZ() + vec3.z);

                        if (!this.molten.isSilent()) {
                            this.molten.level().levelEvent((Player)null, 1018, this.molten.blockPosition(), 0);
                        }

                        LargeFireball smallFireball = new LargeFireball(level, this.molten, d2, d3, d4, 1);
                        smallFireball.setPos(this.molten.getX() + vec3.x * 4.0D, this.molten.getY(0.5D) + 0.5D, smallFireball.getZ() + vec3.z * 4.0D);
                        level.addFreshEntity(smallFireball);
                        this.chargeTime = -40;
                        this.stop();
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }
            }
        }
    }

    @Override
    public float getStepHeight() {
        return 1.5F;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.MOLTEN_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return MMSounds.MOLTEN_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSounds.MOLTEN_IDLE.get();
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


    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(MMSounds.MOLTEN_STEPS.get(), 0.1F, this.getVoicePitch() + this.random.nextFloat() * 0.4F);
    }

    static class MoltenMeeleeAttackGoal extends AnimatedAttackGoal{
        private final Molten molten;
        public MoltenMeeleeAttackGoal(Molten pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, int pAttackDelay, int pTicksUntilNextAttack) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen, pAttackDelay, pTicksUntilNextAttack);
            this.molten = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !molten.wantsToBreatheFire() && !molten.wantsToShootFireball();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !molten.wantsToBreatheFire() && !molten.wantsToShootFireball();
        }
    }

    static class MoltenFireBreathingGoal extends Goal {
        private final Molten molten;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public MoltenFireBreathingGoal(Molten pBlaze) {
            this.molten = pBlaze;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.molten.getTarget();

            return livingentity != null && livingentity.isAlive()
                    && this.molten.canAttack(livingentity)
                    && this.molten.wantsToBreatheFire();
        }

        public void start() {
            this.attackTime = 10;
            this.attackStep = 0;
        }

        public void stop() {
            this.lastSeen = 0;
            this.molten.setWantsToBreatheFire(false);
            this.molten.setBreathingFire(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {

            if (this.attackTime == 10 && !this.molten.isBreathingFire()){
                this.molten.setBreathingFire(true);
            }

            --this.attackTime;

            LivingEntity livingentity = this.molten.getTarget();

            if (livingentity != null) {

                boolean flag = this.molten.getSensing().hasLineOfSight(livingentity);

                if (flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.molten.distanceToSqr(livingentity);

                if (d0 < 15 * 15 && flag) {

                    double d1 = livingentity.getX() - this.molten.getX();
                    double d2 = livingentity.getY(0.5D) - this.molten.getY(0.5D);
                    double d3 = livingentity.getZ() - this.molten.getZ();

//                    System.out.println(this.attackTime);

                    if (this.attackTime <= 0) {
                        ++this.attackStep;

                        if (this.attackStep <= 5) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.stop();
                        }

                        if (this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                            if (!this.molten.isSilent()) {
                                this.molten.level().levelEvent((Player)null, 1018, this.molten.blockPosition(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
                                SmallFireball smallfireball = new SmallFireball(this.molten.level(), this.molten, this.molten.getRandom().triangle(d1, 2.297D * d4), d2, this.molten.getRandom().triangle(d3, 2.297D * d4));
                                smallfireball.setPos(smallfireball.getX(), this.molten.getY(0.5D) + 0.5D, smallfireball.getZ());
                                this.molten.level().addFreshEntity(smallfireball);
                            }
                        }
                    }

                    this.molten.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                    this.molten.lookAt(livingentity, 10, 10);

                } else if (this.lastSeen < 5) {
                    this.molten.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.5D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.molten.fireballFollowRange();
        }
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getDirectEntity() instanceof Snowball) {
            return super.hurt(pSource, 3);
        }

        return super.hurt(pSource, pAmount);
    }

}
