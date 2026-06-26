package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.Predicate;

//TODO: hunger
public class Inostrancevia extends TamableMarvelousAnimal implements NeutralMob, IAnimatedAttacker {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EATING_TIME = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Inostrancevia.class, EntityDataSerializers.INT);

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
    public final AnimationState eatingAnimationState = new AnimationState();
    public int eatingAnimationTimeout;
    private LookForFoodGoal forFoodGoal;

    public final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.PIG || entitytype == MMEntities.LYSTRO.get();
    };

    public final Predicate<LivingEntity> STARVED_PREY_SELECTOR = (p_289448_) -> {
        return true;
    };

    @Override
    protected void registerGoals() {
        this.forFoodGoal = new LookForFoodGoal(this, (itemEntity)-> {
            if (itemEntity.isInWaterOrBubble())
                return false;
            ItemStack item = itemEntity.getItem();
            if (item.getFoodProperties(this) == null)
                return false;
            else
               return item.getFoodProperties(this).isMeat();
        });
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new MarvelousSitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new StoicPanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new MarvelousBreedGoal(this, 1));
        this.goalSelector.addGoal(2, this.forFoodGoal);
        this.goalSelector.addGoal(3, new PredicateTemptGoal(this, 1.1D, false, (itemstack)-> {
            if (itemstack.getFoodProperties(this) == null || itemstack.is(MMItems.LYSTRO_CHOP.get()))
                return false;
            if (itemstack.getFoodProperties(this) != null)
                return itemstack.getFoodProperties(this).isMeat();
            return false;
        }));
        this.goalSelector.addGoal(3, new TameableFollowOwnerGoal(this, 1.75, 5.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D){
            @Override
            public boolean canUse() {
                return super.canUse() && !Inostrancevia.this.isTame();
            }
        });
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new RandomlySitUpOrDownGoal(this, 1000, 600));

        this.targetSelector.addGoal(1, new MarvelousOwnerHurtByTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        });
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }
        }));

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

            @Override
            public boolean canUse() {
                return Inostrancevia.this.isHungry() && super.canUse();
            }
        });


        this.targetSelector.addGoal(5, new MarvelousNonTameRandomTargetGoal<>(this, LivingEntity.class, false, STARVED_PREY_SELECTOR){
            @Override
            public void start() {
                super.start();
                Inostrancevia.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return Inostrancevia.this.isStarving() && super.canUse();
            }
        });

        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, false));

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.5, true, 11, 9));

        this.goalSelector.addGoal(9, new Inostrancevia.RandomRoarGoal(this));

    }

    public boolean canMate(Animal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        }else if (!(pOtherAnimal instanceof Inostrancevia)) {
            return false;
        } else {
            Inostrancevia wolf = (Inostrancevia)pOtherAnimal;
            if (!wolf.isTame()) {
                return !this.isTame() && this.isInLove() && wolf.isInLove();
            } else if (wolf.isOrderedToSit()) {
                return false;
            } else {
                return this.isInLove() && wolf.isInLove();
            }
        }
    }

    private void triggerFoodSearch() {
        if (this.forFoodGoal != null) {
            this.forFoodGoal.trigger();
        } else {
            this.navigation.stop();
            Predicate<ItemEntity> predicate = (itemEntity)-> {
                ItemStack item = itemEntity.getItem();
                if (item.getFoodProperties(this) == null)
                    return false;
                else
                    return item.getFoodProperties(this).isMeat();
            };
            List<? extends ItemEntity> list = this.level().getEntitiesOfClass(ItemEntity.class, this.getBoundingBox().inflate((double)32.0F, (double)8.0F, (double)32.0F), predicate);
            if (!list.isEmpty()) {
                this.navigation.moveTo((Entity)list.get(0), 1.1);
            }
        }
    }

    @Override
    public boolean canPickUpLoot() {
        return this.isHungry();
    }

    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack item = itemEntity.getItem();
        if (item.getFoodProperties(this)!=null){
            if (item.getFoodProperties(this).isMeat()) {
                ItemStack itemstack = itemEntity.getItem();
                ItemStack itemstack1 = this.equipItemIfPossible(itemstack.copy());
                if (!itemstack1.isEmpty()) {
                    this.onItemPickup(itemEntity);
                    this.take(itemEntity, 1);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        itemEntity.discard();
                    }
                }
            }
        }
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
        this.entityData.define(HUNGER, 10*20*60);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(EATING_TIME, 0);
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isRoaring() || this.isEating();
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("CollarColor", (byte) this.getCollarColor().getId());
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putInt("Hunger", this.getHunger());
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(pCompound.getInt("CollarColor")));
        }

        this.readPersistentAngerSaveData(this.level(), pCompound);
        this.setHunger(pCompound.getInt("Hunger"));
        this.setVariant(pCompound.getInt("Variant"));
    }

    public String getBaseColor(){
        if (this.isEvil())
            return "evil";

        int colorID = this.getVariant() / 10;
        InostranceviaColor color = InostranceviaColor.byId(colorID);
        return color.getSerializedName();
    }

    @Nullable
    public String getPattern(){
        int patternID = this.getVariant() % 10;
        if (patternID == 0)
            return null;
        InostranceviaPattern pattern = InostranceviaPattern.byId(patternID);
        return pattern.getSerializedName();
    }


    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getEatingTime() {
        return this.entityData.get(EATING_TIME);
    }

    public void setEatingTime(int pTime) {
        this.entityData.set(EATING_TIME, pTime);
    }

    public boolean isEating(){
        return this.getEatingTime()>0;
    }

    public void setEating(boolean eating){
        this.setEatingTime(eating ? 50 : 0);
    }

    public int getHunger() {
        return this.entityData.get(HUNGER);
    }

    public void setHunger(int pTime) {
        this.entityData.set(HUNGER, pTime);
    }

    public boolean isHungry(){
        if (this.isEvil())
            return true;
        return this.getHunger()<5*60*20;
    }

    public boolean isStarving(){
        if (this.isTame())
            return false;
        if (this.isEvil())
            return true;
        return this.getHunger()<2.5*60*20;
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

        if (this.getHunger()>0) {
            int prevFood = this.getHunger();
            this.setHunger(prevFood - 1);
        }

        if (this.isStarving() || this.isEvil()) {
            this.triggerFoodSearch();
        }

        super.tick();

        if (this.isEffectiveAi() && this.roarCounter > 0 && ++this.roarCounter > 30) {
            this.roarCounter = 0;
            this.setRoaring(false);
        }
    }

    public Vec3 getHeadPos(boolean random, float randomValue) {
        final float angle = (0.0174532925F * this.yBodyRot);
        final float headX = 1.25F * getScale() * Mth.sin(Mth.PI + angle);
        final float headZ = 1.25F * getScale() * Mth.cos(angle);

        if (random) {
            return new Vec3(this.getRandomX(randomValue) + headX, this.getRandomY(), this.getRandomZ(randomValue) + headZ);
        } else return new Vec3(this.getX() + headX, this.getBlockY(), this.getZ() + headZ);
    }
    
    public BlockPos getHeadBlockPos() {
        final float angle = (0.0174532925F * this.yBodyRot);
        final double headX = 1.25F * getScale() * Mth.sin(Mth.PI + angle);
        final double headZ = 1.25F * getScale() * Mth.cos(angle);

        return new BlockPos((int)(this.getX() + headX), this.getBlockY(), (int)(this.getZ() + headZ));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int baseColor = Util.getRandom(InostranceviaColor.values(), this.getRandom()).id();
        int pattern = Util.getRandom(InostranceviaPattern.values(), this.getRandom()).id();

        this.setVariant((baseColor*10)+pattern);

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public boolean isAggressive() {
        return super.isAggressive() || (this.isHungry() && !this.isTame());
    }

    @Override
    public void ate() {
        super.ate();

        this.setEating(true);

        if (this.getHealth() < this.getMaxHealth()) {
            this.heal(2);
            this.gameEvent(GameEvent.EAT, this);
        }

        if (this.isBaby()) {
            this.ageUp(60);
        }

        int prevHunger = this.getHunger();

        this.setHunger(prevHunger+20*60*2);
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

        if (!this.isEating() && !this.isRoaring()){
            if (this.idleExtraAnimationTimeout <= 0) {
                this.idleExtraAnimationTimeout = this.random.nextInt(160) + 160;
                if (this.getRandom().nextBoolean())
                    this.shakeAnimationState.start(this.tickCount);
                else
                    this.yawnAnimationState.start(this.tickCount);
            } else {
                --this.idleExtraAnimationTimeout;
            }
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

        if (this.isEating() && this.eatingAnimationTimeout <= 0){
            this.eatingAnimationTimeout = 50;
            this.eatingAnimationState.start(this.tickCount);
        }else if (0 < this.eatingAnimationTimeout ){
            --this.eatingAnimationTimeout;
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
                || s.toLowerCase().contains("evil gorgonopsid") || s.toLowerCase().contains("fideobowl")
                || s.toLowerCase().contains("princess") || s.toLowerCase().contains("cupcake"));
    }


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(MMItems.LYSTRO_CHOP.get()) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } if (hand == InteractionHand.MAIN_HAND && itemstack.is(MMItems.LYSTRO_CHOP.get()) && !this.isTame()) {

            this.playSound(SoundEvents.GENERIC_EAT, this.getSoundVolume(), this.getVoicePitch());
            this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, itemstack), this.getX(), this.getY(), this.getZ(), (double) 0.0F, (double) 0.0F, (double) 0.0F);

            if (!player.getAbilities().instabuild) {
                if (this.getRandom().nextInt(5)==0)
                    itemstack.shrink(itemstack.getCount());
                else
                    itemstack.shrink(1);
            }

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

        } else if (hand == InteractionHand.MAIN_HAND && this.isTame() && this.isOwnedBy(player)
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
            }else if (this.isFood(itemstack) || itemstack.is(MMItems.LYSTRO_CHOP.get())){

                this.playSound(SoundEvents.GENERIC_EAT);
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, itemstack), this.getX(), this.getY(), this.getZ(), (double) 0.0F, (double) 0.0F, (double) 0.0F);

                if (this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float) itemstack.getFoodProperties(this).getNutrition());
                    this.gameEvent(GameEvent.EAT, this);

                    return InteractionResult.SUCCESS;
                }else {
                    this.gameEvent(GameEvent.EAT, this);

                    return super.mobInteract(player, hand);
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

        ItemStack stack = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (!stack.isEmpty() && !this.isEating()){
            this.ate();
        }

        if (this.isEating()) {
            int prevTime = this.getEatingTime();
            this.setEatingTime(prevTime - 1);

            if (prevTime == 25 || prevTime == 20 || prevTime == 10){
                this.playSound(SoundEvents.GENERIC_EAT, this.getSoundVolume(), this.getVoicePitch());
            }

            if (this.random.nextFloat() <= 0.4f && this.getMainHandItem() != ItemStack.EMPTY) {
                this.level().addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, this.getMainHandItem()),
                        this.getHeadPos(true, 0.4f).x,
                        this.getHeadBlockPos().getY() + 0.5f,
                        this.getHeadPos(true, 0.4f).z,
                        0.0, 0.0, 0.0
                );
            }
        }

        if ((this.isEating() && this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) || (!this.isEating() && !this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty())){
            this.setEating(false);
            stack.setCount(0);
            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }


    public boolean wantsToAttack(LivingEntity pTarget, LivingEntity pOwner) {
        if (!(pTarget instanceof Creeper) && !(pTarget instanceof Ghast)) {
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
                if (pTarget instanceof TamableAnimal animal) {
                    return !animal.isTame();
                }
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Inostrancevia baby = MMEntities.INOSTRANCEVIA.get().create(pLevel);
        Inostrancevia otherParent = (Inostrancevia) pOtherParent;
        if (baby != null) {
            if (this.isTame()){
                UUID uuid = this.getOwnerUUID();
                if (uuid != null) {
                    baby.setOwnerUUID(uuid);
                    baby.setTame(true);
                }
            }
            int baseColor = this.getRandom().nextBoolean() ? Util.getRandom(InostranceviaColor.values(), this.getRandom()).id() : this.getRandom().nextBoolean() ? otherParent.getVariant()/10 : this.getVariant()/10;
            int pattern = this.getRandom().nextBoolean() ? Util.getRandom(InostranceviaPattern.values(), this.getRandom()).id() :  this.getRandom().nextBoolean() ? otherParent.getVariant()%10 : this.getVariant()%10;
            baby.setVariant((baseColor*10)+pattern);
        }

        return baby;
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

    public enum InostranceviaPattern implements StringRepresentable {
        PLAIN(0, ""),
        STRIPES(1, "stripes"),
        PANTHER(2, "panther"),
        RACER(3, "racer");

        private static final IntFunction<InostranceviaPattern> BY_ID = ByIdMap.sparse(InostranceviaPattern::id, values(), PLAIN);
        public static final Codec<InostranceviaPattern> CODEC = StringRepresentable.fromEnum(InostranceviaPattern::values);
        final int id;
        private final String name;

        InostranceviaPattern(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static InostranceviaPattern byId(int pId) {
            return BY_ID.apply(pId);
        }
    }

    public enum InostranceviaColor implements StringRepresentable {
        OLIVE(0, "olive"),
        SANDY(1, "sandy"),
        TERRACOTTA(2, "terracotta"),
        CLAY(3, "clay");

        private static final IntFunction<InostranceviaColor> BY_ID = ByIdMap.sparse(InostranceviaColor::id, values(), OLIVE);
        public static final Codec<InostranceviaColor> CODEC = StringRepresentable.fromEnum(InostranceviaColor::values);
        final int id;
        private final String name;

        InostranceviaColor(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static InostranceviaColor byId(int pId) {
            return BY_ID.apply(pId);
        }
    }
}
