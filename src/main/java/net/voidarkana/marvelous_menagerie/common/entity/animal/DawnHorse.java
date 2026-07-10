package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;


public class DawnHorse extends MarvelousAnimal {

    public final AnimationState idleTailState = new AnimationState();
    public final AnimationState idleEarsState = new AnimationState();
    public final AnimationState neighState = new AnimationState();
    private int standCounter;

    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(DawnHorse.class, EntityDataSerializers.BYTE);

    public DawnHorse(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleTailTimeout = this.random.nextInt(40) + 80;
    private int idleEarsTimeout = this.random.nextInt(40) + 80;
    private int neighTimeout = 0;

    protected boolean canGallop = true;
    protected int gallopSoundCounter;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5F));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Kelenken.class, 6.0F, 1.0D, 1.5D){
            @Override
            public void tick() {
                super.tick();
                if (DawnHorse.this.getRandom().nextInt(10)==0)
                    DawnHorse.this.addParticlesAroundSelf(ParticleTypes.SPLASH);
            }
        });
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.GOLDEN_CARROT), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(9, new DawnHorse.RandomStandGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.GOLDEN_CARROT);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isEffectiveAi() && this.standCounter > 0 && ++this.standCounter > 30) {
            this.standCounter = 0;
            this.setStanding(false);
        }
    }

    public void setStanding(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isStanding() {
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

    public void setupAnimationStates() {
        super.setupAnimationStates();

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

        if (this.isStanding() && this.neighTimeout <= 0){
            this.neighTimeout = 30;
            this.neighState.start(this.tickCount);
        }else if (0 < this.neighTimeout ){
            --this.neighTimeout;
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isStanding();
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

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (itemstack.is(MMTags.Items.DOMESTICATION_INNOVATION_ROTTEN_APPLE) || itemstack.is(MMItems.CHUD_FLESH.get())){

            this.playSound(SoundEvents.HORSE_DEATH, 0.8F, this.getVoicePitch());
            this.playSound(SoundEvents.ZOMBIE_INFECT, 0.8F, this.getVoicePitch());

            CompoundTag horseExtras = new CompoundTag();

            this.addAdditionalSaveData(horseExtras);

            for(int i = 0; i < 6 + this.getRandom().nextInt(5); i++){
                this.level().addParticle(ParticleTypes.SNEEZE, this.getRandomX(1.0F), this.getRandomY(), this.getRandomZ(1.0F), 0F, 0F, 0F);
            }

            ZombieDawnHorse zombie = MMEntities.ZOMBIE_DAWN_HORSE.get().create(this.level());

            if(this.isLeashed()){
                zombie.setLeashedTo(this.getLeashHolder(), true);
            }

            zombie.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            zombie.setNoAi(this.isNoAi());
            zombie.setBaby(this.isBaby());
            if (this.hasCustomName()) {
                zombie.setCustomName(this.getCustomName());
                zombie.setCustomNameVisible(this.isCustomNameVisible());
            }
            zombie.readAdditionalSaveData(horseExtras);
            zombie.setPersistenceRequired();
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, zombie);
            pPlayer.level().addFreshEntity(zombie);
            this.discard();
            if(!pPlayer.isCreative()){
                itemstack.shrink(1);
            }
            return InteractionResult.CONSUME;
        }

        return super.interactAt(pPlayer, pVec, pHand);
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
        return this.isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 5F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
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

    public void standIfPossible() {
        if (this.isEffectiveAi()) {
            this.standCounter = 1;
            this.setStanding(true);
        }
    }

    public class RandomStandGoal extends Goal {
        private final DawnHorse mob;
        private int nextStand;

        public RandomStandGoal(DawnHorse mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            this.mob.standIfPossible();
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
                return !this.mob.isImmobile() && this.mob.onGround();
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
