package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.serialization.Codec;
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
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.client.events.MMEventBusClientEvents;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.message.MountAttackMessage;
import net.voidarkana.marvelous_menagerie.util.network.message.MountMessagePackage;
import net.voidarkana.marvelous_menagerie.util.network.message.MountSoundMessage;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Kelenken extends TamableMarvelousAnimal implements Saddleable, IAnimatedAttacker, NeutralMob, PlayerRideableJumping {

    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EATING_TIME = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.BYTE);

    private static final EntityDataAccessor<Integer> MOUNT_ATTACKING_TICKS = SynchedEntityData.defineId(Kelenken.class, EntityDataSerializers.INT);
    private static int messageCooldown;

    public final AnimationState leftPreenAnimationState = new AnimationState();
    public final AnimationState rightPreenAnimationState = new AnimationState();

    public final AnimationState wingsShakeAnimationState = new AnimationState();
    public final AnimationState headShakeAnimationState = new AnimationState();

    public final AnimationState attackAnimationState1 = new AnimationState();
    public final AnimationState attackAnimationState2 = new AnimationState();
    public final AnimationState attackAnimationState3 = new AnimationState();
    public int attackAnimationTimeout;
    public final AnimationState roarAnimationState = new AnimationState();
    public int roarAnimationTimeout;
    private int roarCounter;
    private int preenCounter;
    public int preenAnimationTimeout = 60;
    public int headShakeAnimationTimeout = this.random.nextInt(160) + 80;
    public int wingsShakeAnimationTimeout = this.random.nextInt(160) + 80;

    public final AnimationState eatingAnimationState = new AnimationState();
    public int eatingAnimationTimeout;
    private LookForFoodGoal forFoodGoal;

    protected float playerJumpPendingScale;
    
    int inWaterTicks;
    
    public final Predicate<LivingEntity> PREY_SELECTOR = (p_289448_) -> {
        EntityType<?> entitytype = p_289448_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.PIG
                || entitytype == MMEntities.DOEDICURUS.get() || entitytype == MMEntities.DAWN_HORSE.get()
                || entitytype == MMEntities.ZOMBIE_DAWN_HORSE.get()
                || entitytype == MMEntities.SKELETON_DAWN_HORSE.get()
                || entitytype == MMEntities.LEPTICTIDIUM.get();
    };

    public final Predicate<LivingEntity> STARVED_PREY_SELECTOR = (entity) -> {
        return entity.getType() != this.getType();
    };
    
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;
    
    public Kelenken(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.JUMP_STRENGTH, 1.5f);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

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
        this.goalSelector.addGoal(1, new StoicPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new CustomRideGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1));
        this.goalSelector.addGoal(2, this.forFoodGoal);
        this.goalSelector.addGoal(3, new PredicateTemptGoal(this, 1.1D, false, (itemstack)-> {
            if (itemstack.getFoodProperties(this) == null || itemstack.is(MMItems.RAW_DAWN_HORSE.get()))
                return false;
            if (itemstack.getFoodProperties(this) != null)
                return itemstack.getFoodProperties(this).isMeat();
            return false;
        }));

        this.goalSelector.addGoal(3, new TameableFollowOwnerGoal(this, 1.15, 5.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !Kelenken.this.isVehicle();
            }
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && (!Kelenken.this.isVehicle());
            }
        });

        this.goalSelector.addGoal(7, new RandomlySitUpOrDownGoal(this, 1000, 600));

        this.targetSelector.addGoal(1, new MarvelousOwnerHurtByTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return super.canUse() && !Kelenken.this.isVehicle();
            }
        });
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return super.canUse() && !Kelenken.this.isVehicle();
            }
        }));

        this.targetSelector.addGoal(2, new MarvelousOwnerHurtTargetGoal(this){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return super.canUse() && !Kelenken.this.isVehicle();
            }
        });

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return super.canUse() && !Kelenken.this.isVehicle();
            }
        });

        this.targetSelector.addGoal(5, new MarvelousNonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return Kelenken.this.isHungry() && super.canUse() && !Kelenken.this.isVehicle();
            }
        });


        this.targetSelector.addGoal(5, new MarvelousNonTameRandomTargetGoal<>(this, LivingEntity.class, false, STARVED_PREY_SELECTOR){
            @Override
            public void start() {
                super.start();
                Kelenken.this.roarIfPossible();
            }

            @Override
            public boolean canUse() {
                return Kelenken.this.isStarving() && super.canUse() && !Kelenken.this.isVehicle();
            }
        });

        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, false));

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 2, true, 10, 10, true){
            @Override
            public boolean canUse() {
                return super.canUse() && Kelenken.this.getAttackingTicks()==0 && !Kelenken.this.isVehicle();
            }
        });

        this.goalSelector.addGoal(9, new Kelenken.RandomRoarGoal(this));
    }

    @Override
    public boolean canSit() {
        return super.canSit() && !this.isVehicle();
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
    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isRoaring() || this.isEating();
    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(IS_ATTACKING, false);
        this.entityData.define(HUNGER, 10*20*60);
        this.entityData.define(EATING_TIME, 0);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(MOUNT_ATTACKING_TICKS, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Saddle", this.isSaddled());
        this.addPersistentAngerSaveData(compound);
        compound.putInt("Hunger", this.getHunger());
        compound.putInt("Variant", this.getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSaddled(compound.getBoolean("Saddle"));

        this.readPersistentAngerSaveData(this.level(), compound);
        this.setHunger(compound.getInt("Hunger"));
        this.setVariant(compound.getInt("Variant"));
    }

    public String getVariantName(){
        return KelenkenVariant.byId(this.getVariant()).getSerializedName();
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
        return this.getHunger()<5*60*20;
    }

    public boolean isStarving(){
        if (this.isTame())
            return false;
        return this.getHunger()<2.5*60*20;
    }

    public boolean isActuallyAttacking(){
        return this.isAttacking() || this.getAttackingTicks()>0;
    }

    public int getAttackingTicks() {
        return this.entityData.get(MOUNT_ATTACKING_TICKS);
    }

    public void setAttackingTicks(int pFromBucket) {
        this.entityData.set(MOUNT_ATTACKING_TICKS, pFromBucket);
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
        return MMSounds.KELENKEN_BITE.get();
    }

    @Override
    public int getStandDuration() {
        return 30;
    }

    @Override
    public int getSitDuration() {
        return 30;
    }

    @Override
    public boolean isSaddleable() {
        return this.isTame() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {
        this.setSaddled(true);
        this.playSound(SoundEvents.HORSE_SADDLE);
    }

    public boolean isSaddled() {
        if (this.isBaby())
            return false;
        return this.entityData.get(SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(SADDLED, saddled);
    }
    
    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isAngryAt(player) && !this.isSitting() && !this.isInPoseTransition() && !this.isVehicle();
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        Item item = pStack.getItem();
        return item.isEdible() && pStack.getFoodProperties(this).isMeat() && !pStack.is(MMItems.RAW_DAWN_HORSE.get());
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

    public void setPreening(boolean pStanding) {
        this.setFlag(16, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isRoaring() {
        return this.getFlag(32);
    }

    public boolean isPreening() {
        return this.getFlag(16);
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
        if (this.isEffectiveAi()) {
            this.roarCounter = 1;
            this.setRoaring(true);
            this.playSound(MMSounds.KELENKEN_ROAR.get(), 0.6f, this.getVoicePitch());
        }
    }

    public void preenIfPossible() {
        if (this.isEffectiveAi()) {
            this.preenCounter = 1;
            this.setPreening(true);
        }
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

                if (this.isControlledByLocalInstance()){
                    moveY = this.isInWaterOrBubble() ? 0.05F : 0F;
                }

                Vec3 vec = new Vec3(f, moveY, f1);

                if (MMEventBusClientEvents.attackKey.isDown() && this.getAttackingTicks() == 0){
                    this.setAttackingTicks(20);

                    MMMessages.CHANNEL.sendToServer(new MountSoundMessage(2, this.getId(), 0.6f));
                }

                super.travel(vec);

            } else {
                super.travel(pos);
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return super.canBeCollidedWith();
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isBaby()){

            if (pPose == Pose.SITTING) {
                return super.getDimensions(pPose).scale(0.75F, 0.3F);
            } else {
                return super.getDimensions(pPose);
            }
        }
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
        return Mth.lerp(this.inWaterTicks/5f, super.getPassengersRidingOffset()*0.75f, super.getPassengersRidingOffset()*0.25D);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(MMItems.RAW_DAWN_HORSE.get()) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } if (hand == InteractionHand.MAIN_HAND && itemstack.is(MMItems.RAW_DAWN_HORSE.get()) && !this.isTame()) {

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

            if (this.isFood(itemstack) || itemstack.is(MMItems.LYSTRO_CHOP.get())){

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
            }if (itemstack.getItem() == Items.SADDLE && !this.isSaddled()) {
                this.usePlayerItem(player, hand, itemstack);
                this.equipSaddle(SoundSource.NEUTRAL);
            } else if (itemstack.getItem() == Items.SHEARS && this.isSaddled()) {
                this.setSaddled(false);
                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(Items.SADDLE);
            } else if (!player.isShiftKeyDown() && !this.isBaby() && this.isSaddled() && !this.isSitting() && !this.isInPoseTransition()) {
                messageCooldown = 260;
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
    public void tick() {

        if (this.getHunger()>0) {
            int prevFood = this.getHunger();
            this.setHunger(prevFood - 1);
        }

        if (this.isStarving()) {
            this.triggerFoodSearch();
        }

        if (this.getAttackingTicks()>0) {
            int prevTicks = this.getAttackingTicks();
            this.setAttackingTicks(prevTicks - 1);

            if (this.getAttackingTicks()==10)
                this.performMountAttack();

            if (this.getAttackingTicks()==0)
                this.setAttackAnimationTimeout(0);
        }

        super.tick();

        if (this.isEffectiveAi() && this.roarCounter > 0 && ++this.roarCounter > 30) {
            this.roarCounter = 0;
            this.setRoaring(false);
        }

        if (this.isEffectiveAi() && this.preenCounter > 0 && ++this.preenCounter > 60) {
            this.preenCounter = 0;
            this.setPreening(false);
        }

        if (messageCooldown > 0 && !this.level().isClientSide) messageCooldown--;
        if (messageCooldown < 10 && messageCooldown > 0 && this.isVehicle()) {
            if (this.getFirstPassenger() instanceof ServerPlayer serverPlayer) {
                MMMessages.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MountMessagePackage());
            }
        }
    }

    private final TargetingConditions MOUNT_TARGET_CONDITIONS = TargetingConditions.forCombat().selector((entity) -> {
        if (this.isVehicle()){
            if (this.getControllingPassenger() == entity){
                return false;
            }
            return entity.canBeSeenAsEnemy() && !entity.isSpectator() && entity.level().getWorldBorder().isWithinBounds(entity.getBoundingBox());
        }
        return false;
    });

    public void performMountAttack(){
        List<LivingEntity> list = this.level().getNearbyEntities(LivingEntity.class, MOUNT_TARGET_CONDITIONS, this,
                this.getBoundingBox().deflate(0.75).expandTowards(this.getLookAngle().scale(2)));

        this.swing(InteractionHand.MAIN_HAND);

        if (!list.isEmpty() && this.hasControllingPassenger()) {
            LivingEntity livingentity = list.get(0);

            MMMessages.CHANNEL.sendToServer(new MountAttackMessage(livingentity.getId(), this.getId()));
        }
    }
    
    public void aiStep() {
        super.aiStep();

        Vec3 vec3 = this.getDeltaMovement();

        if (!this.onGround() && vec3.y < (-0.1D) && !this.isInWater()) {
            this.setDeltaMovement(vec3.multiply(1.0D, this.isVehicle() ? 0.9D : 0.85D, 1.0D));
        }

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

    protected float getRiddenSpeed(Player pPlayer) {
        float f = pPlayer.isSprinting() && this.onGround() ? 1.15f : 0.45F;
        this.setSprinting(pPlayer.isSprinting() && this.onGround());
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * f;
    }

    public boolean canSprint() {
        return this.onGround();
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }
    
    @Override
    public int getMaxYRot() {
        return 30;
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

    public void setupAnimationStates() {

        super.setupAnimationStates();

        if (!this.isEating() && !this.isRoaring() && !this.isPreening()){
            if (this.headShakeAnimationTimeout <= 0) {
                this.headShakeAnimationTimeout = this.random.nextInt(160) + 160;
                this.headShakeAnimationState.start(this.tickCount);
            } else {
                --this.headShakeAnimationTimeout;
            }

            if (this.wingsShakeAnimationTimeout <= 0) {
                this.wingsShakeAnimationTimeout = this.random.nextInt(160) + 80;
                this.wingsShakeAnimationState.start(this.tickCount);
            } else {
                --this.wingsShakeAnimationTimeout;
            }
        }

        if(this.isActuallyAttacking() && attackAnimationTimeout <= 0) {
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

        if (this.isPreening() && this.preenAnimationTimeout <= 0){
            this.preenAnimationTimeout = 60;
            if (this.getRandom().nextBoolean())
                this.leftPreenAnimationState.start(this.tickCount);
            else
                this.rightPreenAnimationState.start(this.tickCount);
        }else if (0 < this.preenAnimationTimeout ){
            --this.preenAnimationTimeout;
        }

        if (this.isEating() && this.eatingAnimationTimeout <= 0){
            this.eatingAnimationTimeout = 50;
            this.eatingAnimationState.start(this.tickCount);
        }else if (0 < this.eatingAnimationTimeout ){
            --this.eatingAnimationTimeout;
        }

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

        Vec2 vec2 = new Vec2(pPlayer.getXRot() * 0.5F, pPlayer.getYRot());
        this.setRot(vec2.y, vec2.x);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();

        if (this.isControlledByLocalInstance()) {

            if (this.playerJumpPendingScale > 0.0F) {
                this.executeRidersJump(this.playerJumpPendingScale, pTravelVector);
                MMMessages.CHANNEL.sendToServer(new MountSoundMessage(1, this.getId(), 0.6f));
            }
            this.playerJumpPendingScale = 0.0F;
        }
    }

    protected Vec3 getRiddenInput(Player pPlayer, Vec3 pTravelVector) {
        if (!this.onGround() && this.playerJumpPendingScale == 0.0F) {
            return Vec3.ZERO;
        } else {
            float f = pPlayer.xxa * 0.5F;
            float f1 = pPlayer.zza;
            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }

            return new Vec3((double)f, 0.0D, (double)f1);
        }
    }

    public double getCustomJump() {
        return this.getAttributeValue(Attributes.JUMP_STRENGTH);
    }

    protected void executeRidersJump(float pPlayerJumpPendingScale, Vec3 pTravelVector) {
        double d0 = (this.onGround() ? 0.85 : 0.275) * (double)pPlayerJumpPendingScale * (double)this.getBlockJumpFactor();

        double d1 = d0 + (double)this.getJumpBoostPower();
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x, d1, vec3.z);
        this.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(this);
        if (pTravelVector.z > 0.0D) {
            float scale = this.onGround() ? 1 : 0.35f;
            float f = Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
            float f1 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
            this.setDeltaMovement(this.getDeltaMovement().add((double)(-0.4F * f * pPlayerJumpPendingScale * scale), 0.0D, (double)(0.4F * f1 * pPlayerJumpPendingScale * scale)));
        }
    }

    public void onPlayerJump(int pJumpPower) {
        if (this.isSaddled()) {
            if (pJumpPower < 0) {
                pJumpPower = 0;
            }

            if (pJumpPower >= 90) {
                this.playerJumpPendingScale = 1.0F;
            } else {
                this.playerJumpPendingScale = 0.4F + 0.4F * (float)pJumpPower / 90.0F;
            }

        }
    }

    public boolean canJump() {
        return this.isSaddled();
    }

    @Override
    public void handleStartJump(int pJumpPower) {

    }

    @Override
    public void handleStopJump() {

    }

    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        for(Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player) {
                return (Player)passenger;
            }
        }
        return null;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Kelenken baby = MMEntities.KELENKEN.get().create(pLevel);
        Kelenken otherParent = (Kelenken) pOtherParent;
        if (baby != null){
            int variant;
            if (this.getRandom().nextBoolean()){
                variant = this.getRandom().nextBoolean() ? this.getVariant() : otherParent.getVariant();
            }else{
                variant = Util.getRandom(Kelenken.KelenkenVariant.values(), this.getRandom()).id();
            }
            baby.setVariant(variant);
        }
        return baby;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int variant = Util.getRandom(KelenkenVariant.values(), this.getRandom()).id();

        this.setVariant(variant);

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public int getMaxHeadYRot() {
        return 30;
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

    public class RandomRoarGoal extends Goal {
        private final Kelenken mob;
        private int nextRoar;

        public RandomRoarGoal(Kelenken mob) {
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
            if (this.mob.isVehicle() || this.mob.isSitting() || this.mob.isAggressive() || this.mob.isBaby() || (this.mob.isTame() && !this.mob.isWandering()))
                return false;

            --this.nextRoar;

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

    public class PreenGoal extends Goal {
        private final Kelenken mob;
        private int nextRoar;

        public PreenGoal(Kelenken mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            this.mob.preenIfPossible();
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            if (this.mob.isVehicle() || this.mob.isSitting() || this.mob.isAggressive() || this.mob.isBaby() || (this.mob.isTame() && !this.mob.isWandering()))
                return false;

            --this.nextRoar;

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

    public enum KelenkenVariant implements StringRepresentable {
        BROWN(0, "brown", false),
        SERIEMA(1, "seriema", false),
        ROOSTER(2, "rooster", false),
        HAWK(3, "hawk", false);
        //TODO: Chocobo, Aether Moa, UP Kevin, Gallian, slooty

        private static final IntFunction<KelenkenVariant> BY_ID = ByIdMap.sparse(KelenkenVariant::id, values(), BROWN);
        public static final Codec<KelenkenVariant> CODEC = StringRepresentable.fromEnum(KelenkenVariant::values);
        final int id;
        private final String name;
        private final boolean isRare;

        KelenkenVariant(int pId, String pName, boolean isRare) {
            this.id = pId;
            this.name = pName;
            this.isRare = isRare;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static KelenkenVariant byId(int pId) {
            return BY_ID.apply(pId);
        }
    }


    protected SoundEvent getAmbientSound() {
        return MMSounds.KELENKEN_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.KELENKEN_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.KELENKEN_DEATH.get();
    }
}
