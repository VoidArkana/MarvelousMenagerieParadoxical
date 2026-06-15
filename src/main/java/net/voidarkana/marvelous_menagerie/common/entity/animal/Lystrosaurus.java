package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedEater;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMBoatEntity;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Lystrosaurus extends MarvelousAnimal implements IEggLayer, IAnimatedEater {

    public final AnimationState layEggAnimationState = new AnimationState();
    private int layEggAnimTimeout = 70;
    public final AnimationState sniffAnimationState = new AnimationState();
    private int sniffAnimTimeout = this.getRandom().nextInt(160) + 160;
    public final AnimationState headTurnAnimationState = new AnimationState();
    private int headTurnAnimTimeout = this.getRandom().nextInt(160) + 160;
    public final AnimationState eatAnimationState = new AnimationState();
    public final AnimationState digAnimationState = new AnimationState();
    public int eatAnimationTimeout;

    int layEggCounter;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);

    private static final EntityDataAccessor<Integer> PREGNANT_TIME = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_AUTOLAY_EGGS = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> EATING_TIME = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_DIGGING = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Lystrosaurus.class, EntityDataSerializers.STRING);

    public Lystrosaurus(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0F));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5));

        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 6.0F, 1.25D, 1.25D,
                e -> (e instanceof Inostrancevia)){
            @Override
            public void start() {
                super.start();
                if (Lystrosaurus.this.isSitting())
                    Lystrosaurus.this.standUp();
            }
        });

        this.goalSelector.addGoal(2, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LystroLayEggGoal(this, 1.0D, MMBlocks.LYSTRO_EGG, 0.5D));

        this.goalSelector.addGoal(3, new PredicateTemptGoal(this, 1.25D, false, (itemstack)-> itemstack.isEdible() && !itemstack.is(Items.POISONOUS_POTATO)));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.BEETROOT), false));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LystroHerdGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.goalSelector.addGoal(6, new LystroEatBlockGoal());
        this.goalSelector.addGoal(6, new RandomlySitUpOrDownGoal(this, 20*60*5));

        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isBaby()) {
            return pPose == Pose.SITTING ? super.getDimensions(pPose).scale(1F, 1F)
                    : super.getDimensions(pPose).scale(1.15F, 1.15F);
        }else {
            return pPose == Pose.SITTING ? super.getDimensions(pPose).scale(1.0F, 0.85F) : super.getDimensions(pPose);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PREGNANT_TIME, 0);
        this.entityData.define(CAN_AUTOLAY_EGGS, true);
        this.entityData.define(IS_LAYING_EGG, false);
        this.entityData.define(EATING_TIME, 0);
        this.entityData.define(IS_DIGGING, false);
        this.entityData.define(VARIANT, "red");
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsPregnant", this.isPregnant());
        compound.putBoolean("IsLayingEgg", this.isLayingEgg());
        compound.putBoolean("CanAutoBreed", this.canAutoLayEggs());
        compound.putString("Variant", LystroVariant.byId(this.getVariant()).getSerializedName());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setPregnant(compound.getBoolean("IsPregnant"));
        this.setLayingEgg(compound.getBoolean("IsLayingEgg"));
        this.setCanAutoLayEggs(compound.getBoolean("CanAutoBreed"));
        this.setVariant(LystroVariant.byName(compound.getString("Variant")).id());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.125F);
    }

    public int getVariant() {
        return LystroVariant.byName(this.getVariantName()).id();
    }

    public void setVariant(int variant) {
        this.setVariantName(LystroVariant.byId(variant).getSerializedName());
    }

    public String getVariantName() {
        return this.entityData.get(VARIANT);
    }

    public void setVariantName(String variantName) {
        this.entityData.set(VARIANT, variantName);
    }

    public boolean canAutoLayEggs() {
        return this.entityData.get(CAN_AUTOLAY_EGGS);
    }

    public void setCanAutoLayEggs(boolean pIsLayingEgg) {
        this.entityData.set(CAN_AUTOLAY_EGGS, pIsLayingEgg);
    }


    public void setupAnimationStates() {
        super.setupAnimationStates();

        if(headTurnAnimTimeout <= 0 && this.getNavigation().isDone() && !this.isLayingEgg() && !this.isEating()) {
            headTurnAnimTimeout = this.getRandom().nextInt(160) * this.getRandom().nextInt();
            headTurnAnimationState.start(this.tickCount);
        } else {
            --this.headTurnAnimTimeout;
        }

        if(sniffAnimTimeout <= 0 && !this.isSprinting() && !this.isInWaterOrBubble() && !this.isLayingEgg() && !this.isEating()) {
            sniffAnimTimeout = this.getRandom().nextInt(160) * this.getRandom().nextInt();
            sniffAnimationState.start(this.tickCount);
        } else {
            --this.sniffAnimTimeout;
        }

        if(layEggAnimTimeout <= 0 && this.isLayingEgg()) {
            layEggAnimTimeout = 70;
            this.layEggAnimationState.start(this.tickCount);
        } else if (layEggAnimTimeout > 0){
            --this.layEggAnimTimeout;
        }

        if (this.isEating() && this.eatAnimationTimeout <= 0){
            this.eatAnimationTimeout = 49;
            if (this.isDigging())
                this.digAnimationState.start(this.tickCount);
            else
                this.eatAnimationState.start(this.tickCount);
        }else if (eatAnimationTimeout > 0){
            --this.eatAnimationTimeout;
        }
    }

    //SITTING STUFF
    @Override
    public boolean canBeCollidedWith() {
        return !(this.isInPoseTransition()) && this.isSitting();
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isSitting() && !(this.isInPoseTransition()) && !this.isVehicle();
    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public int getSitDuration() {
        return 20;
    }

    @Override
    public int getStandDuration() {
        return 30;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.LYSTRO_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return MMSounds.LYSTRO_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSounds.LYSTRO_IDLE.get();
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.isEdible() && !stack.is(Items.POISONOUS_POTATO);
    }

    @Override
    protected float getSoundVolume() {
        return 1.25f;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        Lystrosaurus baby = MMEntities.LYSTRO.get().create(level);
        Lystrosaurus otherParent = (Lystrosaurus) mob;
        if (baby != null){
            baby.setVariant(this.getRandom().nextBoolean() ? this.getVariant() : otherParent.getVariant());
        }
        return baby;
    }

    public int getPregnantTime() {
        return this.entityData.get(PREGNANT_TIME);
    }

    public void setPregnantTime(int pregnant) {
        this.entityData.set(PREGNANT_TIME, pregnant);
    }

    public boolean isPickyAboutNest(){
        return this.getPregnantTime()>20*30;
    }

    @Override
    public boolean isPregnant() {
        return this.getPregnantTime()>0;
    }

    @Override
    public void setPregnant(boolean pregnant) {
        this.setPregnantTime(pregnant ? 20*60 : 0);
    }

    @Override
    public int getLayEggCounter() {
        return this.layEggCounter;
    }

    @Override
    public void setLayEggCounter(int layEggCounter) {
        this.layEggCounter = layEggCounter;
    }

    @Override
    public boolean isLayingEgg() {
        return this.entityData.get(IS_LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean pIsLayingEgg) {
        this.layEggCounter = pIsLayingEgg ? 1 : 0;
        this.entityData.set(IS_LAYING_EGG, pIsLayingEgg);
    }

    @Override
    public void onEggLaid() {
        this.setLayingEgg(false);
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
            this.playSound(SoundEvents.PIG_STEP, 0.25F, 1.0F);
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

    @Override
    public void tick() {
        super.tick();


        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends Animal> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }
    }

    public void aiStep() {
        if (!this.isPregnant() && this.isLayingEgg()){
            this.setLayingEgg(false);
        }

        if (!this.isEating() && this.isDigging()){
            this.setDigging(false);
        }

        super.aiStep();

        if (this.isEating()) {
            int prevTime = this.getEatingTicks();
            this.setEatingTicks(prevTime - 1);

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

        if ((this.isEating() && this.getMainHandItem().isEmpty()) || (!this.isEating() && !this.getMainHandItem().isEmpty())){
            this.setEatingTicks(0);
            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }

        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (this.level().getBlockState(blockpos.below()).is(MMTags.Blocks.DINOSAUR_NEST)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
            }
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

    public int getMaxYRot(){
        return 25;
    }

    @Override
    public int getEatingTicks() {
        return this.entityData.get(EATING_TIME);
    }

    @Override
    public void setEatingTicks(int eating) {
        this.entityData.set(EATING_TIME, eating);
    }

    @Override
    public boolean isEating() {
        return this.getEatingTicks()>0;
    }

    @Override
    public int eatAnimationTimeout() {
        return this.eatAnimationTimeout;
    }

    @Override
    public void setEatAnimationTimeout(int eatAnimationTimeout) {
        this.eatAnimationTimeout = eatAnimationTimeout;
    }

    @Override
    public @org.jetbrains.annotations.Nullable SoundEvent getEatSound() {
        return SoundEvents.LLAMA_EAT;
    }

    public void setDigging(boolean eating) {
        this.entityData.set(IS_DIGGING, eating);
    }

    public boolean isDigging() {
        return this.entityData.get(IS_DIGGING);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {
        if (pReason == MobSpawnType.NATURAL){
            this.setCanAutoLayEggs(false);
        }
        this.setVariant(Util.getRandom(LystroVariant.values(), this.getRandom()).id());

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }


    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (itemStack.is(Items.POISONOUS_POTATO) && this.canAutoLayEggs()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanAutoLayEggs(false);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        if ((itemStack.is(Items.GOLDEN_APPLE) || itemStack.is(Items.GLISTERING_MELON_SLICE)) && !this.canAutoLayEggs()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanAutoLayEggs(true);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void ate() {
        super.ate();
        if (this.isDigging()){
            this.spawnAtLocation(new ItemStack(Items.HANGING_ROOTS));
        }

        if (!this.level().isClientSide){
            if (this.isBaby()) {
                this.ageUp(60);
            }else if (this.canAutoLayEggs() && this.canFallInLove() && this.getRandom().nextBoolean()){
                this.setInLove(null);
            }
        }
    }

    class LystroLayEggGoal extends LayEggGoal{
        final Lystrosaurus lystro;

        public LystroLayEggGoal(Lystrosaurus pEggLayer, double pSpeedModifier, Supplier<Block> pEgg, double acceptedDistance) {
            super(pEggLayer, pSpeedModifier, MMTags.Blocks.DINOSAUR_NEST, pEgg, acceptedDistance);
            this.lystro = pEggLayer;
        }

        @Override
        public boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            if (Lystrosaurus.this.isPickyAboutNest())
                return pLevel.isEmptyBlock(pPos.above()) && pLevel.getBlockState(pPos).is(Blocks.COARSE_DIRT);
            else
                return super.isValidTarget(pLevel, pPos);
        }
    }

    class LystroEatBlockGoal extends MarvelousEatBlockGoal {
        public LystroEatBlockGoal() {
            super(Lystrosaurus.this, 30, 20, 250);
        }

        @Override
        public void start() {
            super.start();

            BlockPos blockpos = this.mob.blockPosition().below();
            BlockState state = this.mob.level().getBlockState(blockpos);
            Lystrosaurus.this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(state.getBlock()));
            if (this.level.getBlockState(blockpos).is(Blocks.DIRT) || this.level.getBlockState(blockpos).is(Blocks.PODZOL) || this.level.getBlockState(blockpos).is(Blocks.ROOTED_DIRT)) {
                Lystrosaurus.this.setDigging(true);
            }
        }

        public boolean isEdibleGroundBlock(BlockState state){
            return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.DIRT) || state.is(Blocks.ROOTED_DIRT);
        }

        @Override
        public Block getLeftoverBlock(BlockState state) {
            return state.is(Blocks.GRASS_BLOCK) ? super.getLeftoverBlock(state) : Blocks.COARSE_DIRT;
        }
    }

    @Nullable
    protected Lystrosaurus leader;
    private int schoolSize = 1;


    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public Lystrosaurus startFollowing(Lystrosaurus pLeader) {
        this.leader = pLeader;
        pLeader.addFollower();
        return pLeader;
    }

    public void stopFollowing() {
        this.leader.removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 120.0D && this.distanceToSqr(this.leader) > 20.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.15D);
        }
    }

    public void addFollowers(Stream<? extends Lystrosaurus> pFollowers) {
        pFollowers.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((fish) -> {
            fish.startFollowing(this);
        });
    }
    
    public enum LystroVariant implements StringRepresentable {
        RED(0, "red"),
        GREEN(1, "green"),
        BROWN(2, "brown"),
        BLUE(3, "blue"),
        SANDY(4, "sandy");

        private static final IntFunction<LystroVariant> BY_ID = ByIdMap.sparse(LystroVariant::id, values(), RED);
        public static final Codec<LystroVariant> CODEC = StringRepresentable.fromEnum(LystroVariant::values);
        public static final EnumCodec<LystroVariant> NAME_CODEC = StringRepresentable.fromEnum(LystroVariant::values);
        final int id;
        private final String name;

        LystroVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static LystroVariant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static LystroVariant byName(String pName) {
            return NAME_CODEC.byName(pName, RED);
        }
    }

    class LystroHerdGoal extends Goal {
        private static final int INTERVAL_TICKS = 200;
        private final Lystrosaurus mob;
        private int timeToRecalcPath;
        private int nextStartTick;

        public LystroHerdGoal(Lystrosaurus pFish) {
            this.mob = pFish;
            this.nextStartTick = this.nextStartTick(pFish);
        }

        protected int nextStartTick(Lystrosaurus pTaskOwner) {
            return reducedTickDelay(200 + pTaskOwner.getRandom().nextInt(200) % 20);
        }

        public boolean canUse() {
            if (this.mob.hasFollowers()) {
                return false;
            } else if (this.mob.isFollower()) {
                return true;
            } else if (this.nextStartTick > 0) {
                --this.nextStartTick;
                return false;
            } else {
                this.nextStartTick = this.nextStartTick(this.mob);
                Predicate<Lystrosaurus> predicate = (fish) -> {
                    return fish.canBeFollowed() || !fish.isFollower();
                };
                List<? extends Lystrosaurus> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
                Lystrosaurus abstractLystrosaurus = DataFixUtils.orElse(list.stream().filter(Lystrosaurus::canBeFollowed).findAny(), this.mob);
                abstractLystrosaurus.addFollowers(list.stream().filter((fish) -> {
                    return !fish.isFollower();
                }));
                return this.mob.isFollower() && this.mob.inRangeOfLeader() && !this.mob.isSitting();
            }
        }

        public boolean canContinueToUse() {
            return this.mob.isFollower() && this.mob.inRangeOfLeader();
        }

        public void start() {
            this.timeToRecalcPath = 0;
        }

        public void stop() {
            this.mob.stopFollowing();
        }

        public void tick() {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.mob.pathToLeader();
            }
        }
    }
}
