package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Predicate;

//TODO: hunger
public class Inostrancevia extends TamableMarvelousAnimal implements NeutralMob, IAnimatedAttacker {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.BYTE);

    public final AnimationState attackAnimationState1 = new AnimationState();
    public final AnimationState attackAnimationState2 = new AnimationState();
    public final AnimationState attackAnimationState3 = new AnimationState();
    public int attackAnimationTimeout;
    public final AnimationState roarAnimationState = new AnimationState();
    public int roarAnimationTimeout;
    private int roarCounter;
    public final AnimationState shakeAnimationState = new AnimationState();
    public final AnimationState yawnAnimationState = new AnimationState();
    public int idleExtraAnimationTimeout = this.random.nextInt(160) + 160;

    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.PIG || entitytype == MMEntities.LYSTRO.get();
    };

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new MarvelousSitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new StoicPanicGoal(this, 1.4D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new PredicateTemptGoal(this, 1.1D, false, (itemstack)-> {
            if (itemstack.getFoodProperties(this) == null || itemstack.is(MMItems.LYSTRO_CHOP.get()))
                return false;
            if (itemstack.getFoodProperties(this) != null)
                return itemstack.getFoodProperties(this).isMeat();
            return false;
        }));
        this.goalSelector.addGoal(3, new TameableFollowOwnerGoal(this, 1.75, 5.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 1000, 600));

        this.targetSelector.addGoal(1, new MarvelousOwnerHurtByTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        });
        this.targetSelector.addGoal(2, new MarvelousOwnerHurtTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        });

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        });

        this.targetSelector.addGoal(5, new MarvelousNonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        });

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.5, true, 11, 9));

        this.goalSelector.addGoal(9, new Inostrancevia.RandomRoarGoal(this));

    }

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    public Inostrancevia(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(IS_ATTACKING, false);
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isRoaring();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("CollarColor", (byte) this.getCollarColor().getId());
        this.addPersistentAngerSaveData(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(pCompound.getInt("CollarColor")));
        }

        this.readPersistentAngerSaveData(this.level(), pCompound);
    }

    @Override
    public int getStandDuration() {
        return 20;
    }

    @Override
    public int getSitDuration() {
        return 20;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isEffectiveAi() && this.roarCounter > 0 && ++this.roarCounter > 30) {
            this.roarCounter = 0;
            this.setRoaring(false);
        }
    }

    public void setRoaring(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isRoaring() {
        return this.getFlag(32);
    }

    protected void setFlag(int pFlagId, boolean pValue) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (pValue) {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 | pFlagId));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte) (b0 & ~pFlagId));
        }
    }

    public void roarIfPossible() {
        if (this.isEffectiveAi() && !this.isBaby()) {
            this.roarCounter = 1;
            this.setRoaring(true);
            this.playSound(MMSounds.INOSTRANCEVIA_ROAR.get(), 0.6f, this.getVoicePitch());
        }
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.idleExtraAnimationTimeout <= 0) {
            this.idleExtraAnimationTimeout = this.random.nextInt(160) + 160;
            if (this.getRandom().nextBoolean())
                this.shakeAnimationState.start(this.tickCount);
            else
                this.yawnAnimationState.start(this.tickCount);
        } else {
            --this.idleExtraAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;

            switch (this.getRandom().nextInt(0, 3)){
                case 1:
                    attackAnimationState2.start(this.tickCount);
                    break;
                case 2:
                    attackAnimationState3.start(this.tickCount);
                    break;
                default:
                    attackAnimationState1.start(this.tickCount);
            }
        } else {
            --this.attackAnimationTimeout;
        }

        if (this.isRoaring() && this.roarAnimationTimeout <= 0){
            this.roarAnimationTimeout = 60;
            this.roarAnimationState.start(this.tickCount);
        }else if (0 < this.roarAnimationTimeout ){
            --this.roarAnimationTimeout;
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

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
    }

    public void setCollarColor(DyeColor pCollarColor) {
        this.entityData.set(DATA_COLLAR_COLOR, pCollarColor.getId());
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isAngryAt(player) && !this.isSitting() && !this.isInPoseTransition() && !this.isVehicle();
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return item.isEdible() && pStack.getFoodProperties(this).isMeat() && !pStack.is(MMItems.LYSTRO_CHOP.get());
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if (super.doHurtTarget(pEntity)) {
            if (pEntity instanceof LivingEntity && this.getRandom().nextInt(0, 3) == 0) {
                int i = this.getRandom().nextInt(7, 15);
                ((LivingEntity) pEntity).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0), this);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isEvil() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && (s.toLowerCase().contains("evil") || s.toLowerCase().contains("evil gorgonops")
                || s.toLowerCase().contains("evil gorgonopsid") || s.toLowerCase().contains("fideobowl"));
    }


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (hand == InteractionHand.MAIN_HAND && itemstack.is(MMItems.LYSTRO_CHOP.get()) && !this.isTame() && !this.level().isClientSide) {

            if (!player.getAbilities().instabuild) {
                if (this.getRandom().nextInt(5)==0)
                    itemstack.shrink(itemstack.getCount());
                else
                    itemstack.shrink(1);
            }

            this.playSound(SoundEvents.GENERIC_EAT);

            if (this.random.nextInt(10) == 0) {
                this.tame(player);
                this.getLookControl().setLookAt(player);
                this.roarIfPossible();
                this.getNavigation().stop();
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }

            System.out.println(this.isTame());

            return InteractionResult.SUCCESS;

        } else if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide && this.isTame() && this.isOwnedBy(player)
                && !this.isInPoseTransition()) {

            Item item = itemstack.getItem();

            if (item instanceof DyeItem) {
                DyeItem dyeitem = (DyeItem)item;
                if (this.isOwnedBy(player)) {
                    DyeColor dyecolor = dyeitem.getDyeColor();
                    if (dyecolor != this.getCollarColor()) {
                        this.setCollarColor(dyecolor);
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }

                        return InteractionResult.SUCCESS;
                    }

                    return super.mobInteract(player, hand);
                }
            }else if (this.isFood(itemstack)){
                if (this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                    this.gameEvent(GameEvent.EAT, this);
                }else {
                    super.mobInteract(player, hand);
                }
            } else {
                this.setCommand((this.getCommand() + 1) % 3);
                if (this.getCommand() == 3) {
                    this.setCommand(0);
                }

                int var10001 = this.getCommand();
                player.displayClientMessage(Component.translatable("entity.marvelous_menagerie.tameable.command_" + var10001, new Object[]{this.getName()}), true);
                boolean sit = this.getCommand() == 2;

                if (sit && !this.isSitting() && this.onGround()) {
                    this.sitDown();
                } else if (this.isSitting() && this.onGround()) {
                    this.standUp();
                }

            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }


    public boolean wantsToAttack(LivingEntity pTarget, LivingEntity pOwner) {
        if (!(pTarget instanceof Creeper) && !(pTarget instanceof Ghast) && pTarget.isInWater()) {
            if (pTarget instanceof Player && pOwner instanceof Player && !((Player)pOwner).canHarmPlayer((Player)pTarget)) {
                return false;
            } else if (pTarget instanceof AbstractHorse && ((AbstractHorse)pTarget).isTamed()) {
                return false;
            } else if (pTarget instanceof OwnableEntity animal) {
                return animal.getOwner() != this.getOwner();
            }else if (pTarget instanceof Wolf) {
                Wolf wolf = (Wolf)pTarget;
                return !wolf.isTame() || wolf.getOwner() != pOwner;
            }else {
                if (pTarget instanceof TamableAnimal) {
                    ((TamableAnimal) pTarget).isTame();
                }
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Inostrancevia inostrancevia = MMEntities.INOSTRANCEVIA.get().create(pLevel);
        if (inostrancevia != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                inostrancevia.setOwnerUUID(uuid);
                inostrancevia.setTame(true);
            }
        }

        return inostrancevia;
    }

    protected SoundEvent getAmbientSound() {
        return MMSounds.INOSTRANCEVIA_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.INOSTRANCEVIA_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.INOSTRANCEVIA_DEATH.get();
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
        return MMSounds.INOSTRANCEVIA_BITE.get();
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setSprinting(d0 >= 1.15D);
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }

    @Override
    public int getMaxYRot() {
        return 25;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(MMSounds.LARGE_STEPS.get(), 0.25F, 1.1F);
    }

    public class RandomRoarGoal extends Goal {
        private final Inostrancevia mob;
        private int nextRoar;

        public RandomRoarGoal(Inostrancevia mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            this.mob.roarIfPossible();
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            if (this.mob.isSitting() || this.mob.isAggressive() || this.mob.isBaby() || (this.mob.isTame() && !this.mob.isWandering()))
                return false;

            --this.nextRoar;

            if (this.mob.isEvil())
                this.nextRoar -= 4;

            if (this.nextRoar > 0 && this.mob.getRandom().nextInt(this.nextRoar) == 0 ) {
                this.resetStandInterval();
                return !this.mob.isImmobile() && this.mob.onGround();
            } else {
                return false;
            }
        }

        private void resetStandInterval() {
            this.nextRoar = 2000;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
