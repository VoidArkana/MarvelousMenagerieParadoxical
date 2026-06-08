package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.CustomRideGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.MarvelousSitWhenOrderedToGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.RandomlySitUpOrDownGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.TameableFollowOwnerGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;
import org.jetbrains.annotations.Nullable;

public class Josephoartigasia extends TamableMarvelousAnimal implements Saddleable {

    public Josephoartigasia (EntityType<? extends TamableMarvelousAnimal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0F);
        this.reassessTameGoals();
    }

    int inWaterTicks;

    static final TargetingConditions ADULT_TO_RIDE =
            TargetingConditions.forNonCombat().range(15.0D)
                    .ignoreLineOfSight();

    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(Josephoartigasia.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState earsWiggleAnimationState = new AnimationState();
    public final AnimationState rightEarWiggleAnimationState = new AnimationState();
    public final AnimationState leftEarWiggleAnimationState = new AnimationState();
    public final AnimationState headShakeAnimationState = new AnimationState();

    public int earWiggleAnimationTimeout = this.random.nextInt(40) + 80;
    public int headShakeAnimationTimeout = this.random.nextInt(160) + 80;

    
    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        for(Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player) {
                return (Player)passenger;
            }
        }
        return null;
    }

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new MarvelousSitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new DismountGoal(this));
        this.goalSelector.addGoal(1, new MountAdultGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(1, new CustomRideGoal(this, 1));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2, Ingredient.of(ItemTags.LEAVES), false));
        this.goalSelector.addGoal(3, new TameableFollowOwnerGoal(this, 1.2, 5.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !Josephoartigasia.this.isVehicle();
            }
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && (!Josephoartigasia.this.isVehicle() || !Josephoartigasia.this.isPassenger());
            }
        });
        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 6000, 3000));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Saddle", this.isSaddled());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSaddled(compound.getBoolean("Saddle"));
    }

    @Override
    public boolean isSaddleable() {
        return this.isTame();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        this.setSaddled(true);
        this.playSound(SoundEvents.HORSE_SADDLE);
    }

    public boolean isSaddled() {
        return this.entityData.get(SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(SADDLED, saddled);
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    public void travel(Vec3 pos) {
        if (this.isAlive()) {
            LivingEntity livingentity = this.getControllingPassenger();
            if (this.isVehicle() && livingentity instanceof Player) {

                float f = livingentity.xxa * 0.5F;
                float f1 = livingentity.zza;
                if (f1 <= 0.0F) {
                    f1 *= 0.25F;
                }
                double moveY = pos.y;

                if (this.isControlledByLocalInstance() && this.isInWater()){
                    moveY = Minecraft.getInstance().options.keyJump.isDown() ? 5F : 0F;
                }

                this.setSpeed(0.1F);

                Vec3 vec = new Vec3(f, moveY, f1);

                super.travel(vec);

            } else {
                super.travel(pos);
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return !(this.isInPoseTransition()) && this.isSitting();
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isSitting() && !(this.isInPoseTransition()) && !this.isVehicle();
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.LEAVES);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.isFood(itemstack)){
            return super.mobInteract(player, hand);
        } else if (hand == InteractionHand.MAIN_HAND && itemstack.is(Items.APPLE) && !this.isTame() && !this.isBaby()) {

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.playSound(SoundEvents.HORSE_EAT);

            if (this.random.nextInt(5) == 0) {
                this.tame(player);
                this.getNavigation().stop();
                this.level().broadcastEntityEvent(this, (byte)7);
            }else{
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;

        } else if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide && this.isTame() && this.isOwnedBy(player)
                && !this.isInPoseTransition()) {

            if (itemstack.is(Items.APPLE) && this.getHealth() < this.getMaxHealth()) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                this.gameEvent(GameEvent.EAT, this);
            } else if (itemstack.getItem() == Items.SADDLE && !this.isSaddled()) {
                this.usePlayerItem(player, hand, itemstack);
                this.equipSaddle(SoundSource.NEUTRAL);
            } else if (itemstack.getItem() == Items.SHEARS && this.isSaddled()) {
                this.setSaddled(false);
                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(Items.SADDLE);
            } else if (!player.isShiftKeyDown() && !this.isBaby() && this.isSaddled() && !this.isSitting() && !this.isInPoseTransition()) {
                player.startRiding(this);
            } else {
                this.setCommand((this.getCommand() + 1) % 3);
                if (this.getCommand() == 3) {
                    this.setCommand(0);
                }

                int var10001 = this.getCommand();
                player.displayClientMessage(Component.translatable("entity.marvelous_menagerie.tameable.command_" + var10001, new Object[]{this.getName()}), true);
                boolean sit = this.getCommand() == 2;

                if (sit && !this.isSitting() && this.onGround()){
                    this.sitDown();
                } else if (this.isSitting() && this.onGround()){
                    this.standUp();
                }

            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (pPose == Pose.SITTING) {
            return super.getDimensions(pPose).scale(1.0F, 0.575F);
        } else {
            return super.getDimensions(pPose);
        }
    }

    @Override
    public double getPassengersRidingOffset() {
        if (this.isSitting()) {
            return this.getDimensions(Pose.SITTING).height;
        }
        if (this.isInWater() && this.inWaterTicks<5){
            this.inWaterTicks++;
        }else if (!this.isInWater() && this.inWaterTicks>0){
            this.inWaterTicks--;
        }
        return Mth.lerp(this.inWaterTicks/5f, super.getPassengersRidingOffset(), super.getPassengersRidingOffset()*0.95D);
    }

    public void setupAnimationStates() {

        super.setupAnimationStates();

        if (this.earWiggleAnimationTimeout <= 0) {
            this.earWiggleAnimationTimeout = this.random.nextInt(40) + 80;

            switch (this.random.nextInt(3)){
                case 1:
                    this.leftEarWiggleAnimationState.start(this.tickCount);
                    break;
                case 2:
                    this.rightEarWiggleAnimationState.start(this.tickCount);
                    break;
                default:
                    this.earsWiggleAnimationState.start(this.tickCount);
            }
        } else {
            --this.earWiggleAnimationTimeout;
        }

        if (this.headShakeAnimationTimeout <= 0) {
            this.headShakeAnimationTimeout = this.random.nextInt(160) + 80;
            this.headShakeAnimationState.start(this.tickCount);
        } else {
            --this.headShakeAnimationTimeout;
        }

    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public int getSitDuration() {
        return 40;
    }

    @Override
    public int getStandDuration() {
        return 60;
    }

    @Override
    public void standUpInstantly() {
        if (this.isVehicle()) {
            this.ejectPassengers();
        }
        super.standUpInstantly();
        this.setCommand(0);
    } 

    @Override
    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {

        super.tickRidden(pPlayer, pTravelVector);

        this.setRot(pPlayer.getYRot(),pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!level().isClientSide && level().getGameTime() % 20 == 0) {
            for (LivingEntity living : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0D, 10.0D, 20.0D))) {
                living.addEffect(new MobEffectInstance(MMEffects.PACIFIED.get(), 100, 0, false, false));
            }
        }
    }

    protected SoundEvent getAmbientSound() {
        return MMSounds.JOSEPHO_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.JOSEPHO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.JOSEPHO_DEATH.get();
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Josephoartigasia baby = MMEntities.JOSEPHO.get().create(pLevel);

        return baby;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(MMSounds.LARGE_STEPS.get());
    }

    public static class MountAdultGoal extends Goal {

        private final Josephoartigasia childAnimal;
        private final double moveSpeed;
        @javax.annotation.Nullable
        private Josephoartigasia josepho;

        public MountAdultGoal(Josephoartigasia child, double speed) {
            this.childAnimal = child;
            this.moveSpeed = speed;
        }

        @Override
        public boolean canUse() {

            if(!this.childAnimal.isPassenger() && this.childAnimal.isBaby()) {
                this.josepho = this.childAnimal.level().getNearestEntity(Josephoartigasia.class, Josephoartigasia.ADULT_TO_RIDE, this.childAnimal, this.childAnimal.getX(), this.childAnimal.getY(), this.childAnimal.getZ(), this.childAnimal.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
                if (josepho != null && !josepho.isBaby() && !josepho.isVehicle() && josepho.isSitting()) {
                    this.childAnimal.getNavigation().moveTo(this.childAnimal.getNavigation().createPath(josepho, 0), this.moveSpeed);
                    return true;
                }
            }
            return false;
        }


        @Override
        public void tick() {
            Josephoartigasia adult = this.childAnimal.level().getNearestEntity(Josephoartigasia.class, Josephoartigasia.ADULT_TO_RIDE, this.childAnimal, this.childAnimal.getX(), this.childAnimal.getY(), this.childAnimal.getZ(), this.childAnimal.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));

            if (adult!= null){
                if (!adult.equals(this.childAnimal) && !adult.isBaby() && !adult.isVehicle()) {
                    this.childAnimal.startRiding(adult);
                    this.childAnimal.sitDown();
                }
            }
        }

        public boolean canContinueToUse() {
            return !this.childAnimal.isPassenger();
        }

    }

    public int getMaxHeadYRot() {
        return 30;
    }

    static class DismountGoal extends Goal {

        private final Josephoartigasia childAnimal;

        public DismountGoal(Josephoartigasia child) {
            this.childAnimal = child;
        }

        @Override
        public boolean canUse() {
            Entity entity = this.childAnimal.getVehicle();
            if (entity instanceof Josephoartigasia josepho){
                if ((this.childAnimal.getRandom().nextInt(250) == 0 || (this.childAnimal.isPassenger() && !this.childAnimal.isBaby())) || !josepho.isSitting()) {
                    return true;
                }
            }

            return false;

        }

        @Override
        public void tick() {
            if (this.childAnimal.isPassenger()) {
                this.childAnimal.stopRiding();
                this.childAnimal.standUpInstantly();
            }
        }
    }
}
