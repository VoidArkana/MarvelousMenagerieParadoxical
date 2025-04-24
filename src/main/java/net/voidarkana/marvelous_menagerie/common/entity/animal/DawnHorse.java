package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import org.jetbrains.annotations.Nullable;


public class DawnHorse extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleTailState = new AnimationState();
    public final AnimationState idleEarsState = new AnimationState();
    public final AnimationState neighState = new AnimationState();

    public DawnHorse(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleTailTimeout = 0;
    private int idleEarsTimeout = 0;
    private int neighTimeout = 0;

    protected boolean canGallop = true;
    protected int gallopSoundCounter;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5F));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.GOLDEN_CARROT), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.GOLDEN_CARROT);
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if (this.idleEarsTimeout <= 0) {
            this.idleEarsTimeout = this.random.nextInt(40) + 80;
            this.idleEarsState.start(this.tickCount);
        } else {
            --this.idleEarsTimeout;
        }

        if (this.idleTailTimeout <= 0) {
            this.idleTailTimeout = this.random.nextInt(40) + 80;
            this.idleTailState.start(this.tickCount);
        } else {
            --this.idleTailTimeout;
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return MMEntities.DAWN_HORSE.get().create(p_146743_);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HORSE_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.HORSE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HORSE_AMBIENT;
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        if (pFallDistance > 1.0F) {
            this.playSound(SoundEvents.HORSE_LAND, 0.4F, 1.0F);
        }

        int i = this.calculateFallDamage(pFallDistance, pMultiplier);
        if (i <= 0) {
            return false;
        } else {
            this.hurt(pSource, (float)i);
            if (this.isVehicle()) {
                for(Entity entity : this.getIndirectPassengers()) {
                    entity.hurt(pSource, (float)i);
                }
            }

            this.playBlockFallSound();
            return true;
        }
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby() ? 3.2f : 2.2F;
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
        if (!pBlock.liquid()) {
            BlockState blockstate = this.level().getBlockState(pPos.above());
            SoundType soundtype = pBlock.getSoundType(level(), pPos, this);
            if (blockstate.is(Blocks.SNOW)) {
                soundtype = blockstate.getSoundType(level(), pPos, this);
            }

            if (this.isVehicle() && this.canGallop) {
                ++this.gallopSoundCounter;
                if (this.gallopSoundCounter > 5 && this.gallopSoundCounter % 3 == 0) {
                    this.playGallopSound(soundtype);
                } else if (this.gallopSoundCounter <= 5) {
                    this.playSound(SoundEvents.HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
                }
            } else if (this.isWoodSoundType(soundtype)) {
                this.playSound(SoundEvents.HORSE_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
            } else {
                this.playSound(SoundEvents.HORSE_STEP, soundtype.getVolume() * 0.15F, soundtype.getPitch());
            }
        }
    }

    private boolean isWoodSoundType(SoundType pSoundType) {
        return pSoundType == SoundType.WOOD || pSoundType == SoundType.NETHER_WOOD || pSoundType == SoundType.STEM || pSoundType == SoundType.CHERRY_WOOD || pSoundType == SoundType.BAMBOO_WOOD;
    }

    protected void playGallopSound(SoundType pSoundType) {
        this.playSound(SoundEvents.HORSE_GALLOP, pSoundType.getVolume() * 0.15F, pSoundType.getPitch());
    }
}
