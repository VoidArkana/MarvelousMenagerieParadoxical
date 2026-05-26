package net.voidarkana.marvelous_menagerie.common.entity.animal;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.EggLayerBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.LayEggGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.MarvelousTemptGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.RandomlySitUpOrDownGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractAmphibianCreature;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class Flubber extends AbstractAmphibianCreature implements IEggLayer, Bucketable {

    public static final ResourceLocation LOOT_COMMON = new ResourceLocation(MarvelousMenagerie.MOD_ID, "flubber/flubber_dig");

    private static final EntityDataAccessor<Boolean> IS_PREGNANT = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DIGGING_COOLDOWN = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DANCING_TICKS = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DIGGING_TICKS = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(Flubber.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState waterDigAnimationState = new AnimationState();
    public final AnimationState landDigAnimationState = new AnimationState();
    public final AnimationState landDanceAnimationState = new AnimationState();
    public final AnimationState waterDanceAnimationState1 = new AnimationState();
    public final AnimationState waterDanceAnimationState2 = new AnimationState();
    public final AnimationState bellyDrumAnimationState = new AnimationState();
    public int digAnimationTimeout;
    public int danceAnimationTimeout;
    public int bellyDrumAnimationTimeout = this.random.nextInt(320) + 160;
    public final AnimationState sniffState = new AnimationState();
    public final AnimationState snortState = new AnimationState();
    private int sniffTimeout = 0;
    private int snortTimeout = 0;
    private int snortCounter;
    private int sniffCounter;

    int layEggCounter;

    public Flubber(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AmphibianPanicGoal(this, 1.2D));
        this.goalSelector.addGoal(0, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(0, new LayEggGoal(this, 1.0D, BlockTags.SAND, MMBlocks.FLUBBER_EGG, 1D));

        this.goalSelector.addGoal(1, new FlubberGoToWaterGoal(this, 1));
        this.goalSelector.addGoal(1, new AmphibianExitWaterGoal(this, 1.5));
        this.goalSelector.addGoal(1, new MarvelousTemptGoal(this, 1.2D, Ingredient.of(MMTags.Items.FLUBBER_DIG_ITEM), false));

        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !Flubber.this.isSitting();
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && !Flubber.this.isSitting();
            }
        });
        this.goalSelector.addGoal(2, new DigUpSedimentGoal(this));
        this.goalSelector.addGoal(2, new MoveToSedimentGoal(this, 1.2f, 12, 3));

        this.goalSelector.addGoal(3, new AmphibianStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AmphibianSwimGoal(this, 1.5, 7));

        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 600, 1300));
        this.goalSelector.addGoal(5, new SniffAndSnortGoal(this));

        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !Flubber.this.isSitting();
            }
        });

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && !Flubber.this.isSitting();
            }
        });
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(MMTags.Items.FLUBBER_DIG_ITEM);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PREGNANT, false);
        this.entityData.define(DIGGING_COOLDOWN, 5*20*60);
        this.entityData.define(DANCING_TICKS, 0);
        this.entityData.define(DIGGING_TICKS, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(IS_LAYING_EGG, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsPregnant", this.isPregnant());
        compound.putBoolean("IsLayingEgg", this.isLayingEgg());
        compound.putInt("DiggingCooldown", this.getDiggingCooldown());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setPregnant(compound.getBoolean("IsPregnant"));
        this.setLayingEgg(compound.getBoolean("IsLayingEgg"));
        this.setDiggingCooldown(compound.getInt("DiggingCooldown"));
    }

    public int getDiggingCooldown() {
        return this.entityData.get(DIGGING_COOLDOWN);
    }

    public void setDiggingCooldown(int diggingCooldown) {
        this.entityData.set(DIGGING_COOLDOWN, diggingCooldown);
    }

    public int getDancingTicks() {
        return this.entityData.get(DANCING_TICKS);
    }

    public void setDancingTicks(int diggingCooldown) {
        this.entityData.set(DANCING_TICKS, diggingCooldown);
    }

    public int getDiggingTicks() {
        return this.entityData.get(DIGGING_TICKS);
    }

    public void setDiggingTicks(int diggingCooldown) {
        this.entityData.set(DIGGING_TICKS, diggingCooldown);
    }

    @Override
    public boolean isPregnant() {
        return this.entityData.get(IS_PREGNANT);
    }

    @Override
    public void setPregnant(boolean pregnant) {
        this.entityData.set(IS_PREGNANT, pregnant);
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
        if (this.isLandNavigator()){
            this.setDancingTicks(40);
            this.playSound(MMSounds.FLUBBER_CELEBRATE.get(), 0.8F, this.getVoicePitch());
        }
    }

    public void ate() {
        if (this.level() instanceof ServerLevel serverLevel) {
            boolean hasDugOutItem = false;

            while (!hasDugOutItem){
                LootParams.Builder builder = new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, this.getHeadPos(false, 0)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY);
                LootParams lootParams = builder.withParameter(LootContextParams.BLOCK_STATE,
                        serverLevel.getBlockState(this.getHeadBlockPos().below())).create(LootContextParamSets.BLOCK);

                LootTable lootTable = lootParams.getLevel().getServer().getLootData().getLootTable(LOOT_COMMON);
                ObjectArrayList<ItemStack> list = lootTable.getRandomItems(lootParams);
                if (!list.isEmpty()){
                    for (ItemStack stack : list) {
                        this.spawnAtHeadLocation(stack);
                        hasDugOutItem = true;
                    }
                }
            }

            this.setDiggingCooldown(this.random.nextInt(20*60*5) + 20*60*2);
        }
        super.ate();
    }

    public void spawnAtHeadLocation(ItemStack itemStack) {
        if (!itemStack.isEmpty() && !this.level().isClientSide) {
            ItemEntity itemEntity = new ItemEntity(this.level(), this.getHeadPos(false, 0).x, this.getHeadPos(false, 0).y+1, this.getHeadPos(false, 0).z, itemStack);
            itemEntity.setDefaultPickUpDelay();
            this.level().addFreshEntity(itemEntity);
        }
    }

    @Override
    public void tick() {

        if ((!this.wantsToBeInLand()) && this.isPregnant()){
            this.setWantsToBeInLand(true);
        }

        super.tick();

        if (this.isEffectiveAi() && this.sniffCounter > 0 && ++this.sniffCounter > 30) {
            this.sniffCounter = 0;
            this.setSniffing(false);
        }
        if (this.isEffectiveAi() && this.snortCounter > 0 && ++this.snortCounter > 10) {
            this.snortCounter = 0;
            this.setSnorting(false);
        }
    }

    public void setSniffing(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    public void setSnorting(boolean pStanding) {
        this.setFlag(16, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isSniffing() {
        return this.getFlag(32);
    }

    public boolean isSnorting() {
        return this.getFlag(16);
    }

    protected void setFlag(int pFlagId, boolean pValue) {
        byte b0 = this.entityData.get(DATA_ID_FLAGS);
        if (pValue) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 | pFlagId));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(b0 & ~pFlagId));
        }

    }

    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.getDiggingTicks()>0 && digAnimationTimeout<=0){
            this.digAnimationTimeout = 120;
            if (this.isLandNavigator())
                this.landDanceAnimationState.start(this.tickCount);
            else
                this.waterDigAnimationState.start(this.tickCount);
        }else {
            this.digAnimationTimeout--;
        }

        if (this.getDancingTicks()>0 && danceAnimationTimeout<=0){
            this.danceAnimationTimeout = this.getDancingTicks();
            if (this.isLandNavigator())
                this.landDanceAnimationState.start(this.tickCount);
            else {
                if (this.getRandom().nextBoolean())
                    this.waterDanceAnimationState1.start(this.tickCount);
                else
                    this.waterDanceAnimationState2.start(this.tickCount);
            }
        }else if (danceAnimationTimeout>0){
            this.danceAnimationTimeout--;
        }

        if (this.isSitting()){
            if (this.bellyDrumAnimationTimeout <= 0) {
                this.bellyDrumAnimationTimeout = this.random.nextInt(320) * this.random.nextInt(8);
                this.bellyDrumAnimationState.start(this.tickCount);
            } else if (0 < this.bellyDrumAnimationTimeout){
                this.bellyDrumAnimationTimeout--;
            }
        }

        if (this.isSniffing() && this.sniffTimeout <= 0){
            this.sniffTimeout = 30;
            this.sniffState.start(this.tickCount);
        }else if (0 < this.sniffTimeout ){
            --this.sniffTimeout;
        }

        if (this.isSnorting() && this.snortTimeout <= 0){
            this.snortTimeout = 10;
            this.snortState.start(this.tickCount);
        }else if (0 < this.snortTimeout ){
            --this.snortTimeout;
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.getDancingTicks() > 0 || this.isInPoseTransition();
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
        return 20;
    }

    public void aiStep() {

        if (this.getDiggingCooldown()>0 && !this.isBaby()){
            int prevDiggingCooldown = this.getDiggingCooldown();
            this.setDiggingCooldown(Math.max(0, prevDiggingCooldown - 1));
        }

        if (this.getDancingTicks()>0){
            int prevDancingTicks = this.getDancingTicks();
            this.setDancingTicks(Math.max(0, prevDancingTicks - 1));
        }

        if (this.getDiggingTicks() > 0) {

            int prevDiggingticks = this.getDiggingTicks();
            this.setDiggingTicks(Math.max(0, prevDiggingticks - 1));

            if (this.getDiggingCooldown() > 0) this.setDiggingTicks(0);

            if (this.random.nextFloat() <= 0.4f) {
                this.level().addParticle(
                        new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()),
                        this.getHeadPos(true, 0.4f).x,
                        this.getHeadBlockPos().getY() + 0.5f,
                        this.getHeadPos(true, 0.4f).z,
                        0.0, 0.0, 0.0
                );
                this.level().addParticle(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        this.getHeadPos(true, 0.8f).x,
                        this.getHeadBlockPos().getY() + 0.5f,
                        this.getHeadPos(true, 0.8f).z,
                        0.0, 0.0, 0.0
                );
            }

            if (this.getDiggingTicks()==4)
                if (this.isLandNavigator()){
                    this.setDancingTicks(40);
                    this.playSound(MMSounds.FLUBBER_CELEBRATE.get(), 0.8F, this.getVoicePitch());
                }
                else{
                    this.setDancingTicks(59);
                    this.playSound(MMSounds.FLUBBER_UNDERWATER_CELEBRATE.get(), 0.8F, this.getVoicePitch());
                }
        }

        super.aiStep();

        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
            if (this.level().getBlockState(blockpos.below()).is(BlockTags.SAND)) {
                this.level().levelEvent(2001, blockpos, Block.getId(this.level().getBlockState(blockpos.below())));
            }
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return MMEntities.FLUBBER.get().create(pLevel);
    }

    @Override
    public boolean fromBucket() {
        return true;
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {}

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (this.isBaby())
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
        else if (pPlayer.isCreative() && item.is(Items.GOLDEN_APPLE)){
            this.setDiggingCooldown(0);

            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }else {
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.BABY_FLUBBER_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag == null) {
            this.setAge(-24000);
        }else if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Age", 3)) {
            if (dataTag.contains("Age")) {
                this.setAge(dataTag.getInt("Age"));}
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    static class FlubberGoToWaterGoal extends AmphibianGoToWaterGoal {
        private final Flubber turtle;

        FlubberGoToWaterGoal(Flubber pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier);
            this.turtle = pTurtle;
        }

        public boolean canUse() {
            return !this.turtle.isPregnant() && super.canUse();
        }
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return this.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ? MMSounds.FLUBBER_UNDERWATER_IDLE.get() : MMSounds.FLUBBER_IDLE.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ? MMSounds.FLUBBER_UNDERWATER_HURT.get() : MMSounds.FLUBBER_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return this.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) ? MMSounds.FLUBBER_UNDERWATER_DEATH.get() : MMSounds.FLUBBER_DEATH.get();
    }

    public Vec3 getHeadPos(boolean random, float randomValue) {
        final float angle = (0.0174532925F * Flubber.this.yBodyRot);
        final float headX = 1.25F * getScale() * Mth.sin(Mth.PI + angle);
        final float headZ = 1.25F * getScale() * Mth.cos(angle);

        if (random) {
            return new Vec3(Flubber.this.getRandomX(randomValue) + headX, Flubber.this.getRandomY(), Flubber.this.getRandomZ(randomValue) + headZ);
        } else return new Vec3(Flubber.this.getX() + headX, Flubber.this.getBlockY(), Flubber.this.getZ() + headZ);
    }

    public BlockPos getHeadBlockPos() {
        final float angle = (0.0174532925F * Flubber.this.yBodyRot);
        final double headX = 1.25F * getScale() * Mth.sin(Mth.PI + angle);
        final double headZ = 1.25F * getScale() * Mth.cos(angle);

        return new BlockPos((int)(Flubber.this.getX() + headX), Flubber.this.getBlockY(), (int)(Flubber.this.getZ() + headZ));
    }

    class DigUpSedimentGoal extends Goal {
        private final Flubber flubber;
        private final Level level;
        private int eatAnimationTick;

        public DigUpSedimentGoal(Flubber flubber) {
            this.flubber = flubber;
            this.level = flubber.level();
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            BlockPos blockPos = flubber.getHeadBlockPos().below();
            return !this.flubber.isBaby() && this.level.getBlockState(blockPos).is(MMTags.Blocks.FLUBBER_DIG_TARGET)
                    && this.flubber.getDiggingCooldown() == 0 && this.flubber.isInWaterOrBubble();
        }

        @Override
        public void start() {
            this.eatAnimationTick = 100;
            this.flubber.setDiggingTicks(100);
            this.level.broadcastEntityEvent(this.flubber, (byte)10);
            this.flubber.getNavigation().stop();
        }

        @Override
        public void stop() {
            this.eatAnimationTick = 0;
        }

        @Override
        public boolean canContinueToUse() {
            return this.eatAnimationTick > 0 && this.flubber.getDiggingCooldown() == 0;
        }

        @Override
        public void tick() {
            BlockPos blockPos = flubber.getHeadBlockPos().below();

            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);

            if (this.eatAnimationTick==10)
                if (this.level.getBlockState(blockPos).is(MMTags.Blocks.FLUBBER_DIG_TARGET)) {
                    this.flubber.ate();
                }
        }
    }

    public class MoveToSedimentGoal extends MoveToBlockGoal {
        private final Flubber flubber;
        public MoveToSedimentGoal(Flubber pMob, double pSpeedModifier, int pSearchRange, int verticalRange) {
            super(pMob, pSpeedModifier, pSearchRange, verticalRange);
            flubber = pMob;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && flubber.getDiggingCooldown() == 0;
        }

        @Override
        protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
            BlockPos blockPos1 = flubber.getHeadBlockPos();
            return levelReader.getFluidState(blockPos1).is(Fluids.WATER) && levelReader.getBlockState(blockPos1.below()).is(MMTags.Blocks.FLUBBER_DIG_TARGET);
        }
    }

    public void snortIfPossible() {
        if (this.isEffectiveAi()) {
            this.snortCounter = 1;
            this.setSnorting(true);
        }
    }
    public void sniffIfPossible() {
        if (this.isEffectiveAi()) {
            this.sniffCounter = 1;
            this.setSniffing(true);
        }
    }

    public class SniffAndSnortGoal extends Goal {
        private final Flubber mob;
        private int nextIdle;

        public SniffAndSnortGoal(Flubber mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            if (this.mob.getRandom().nextInt(4)==0){
                this.mob.sniffIfPossible();
                this.playSound(SoundEvents.SNIFFER_SNIFFING);
            }
            else{
                this.mob.snortIfPossible();
                this.playSound(SoundEvents.SNIFFER_SCENTING);
            }
        }

        private void playSound(SoundEvent event) {
            this.mob.playSound(event, 1, this.mob.getVoicePitch());
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            --this.nextIdle;
            if (this.nextIdle > 0 && this.mob.getRandom().nextInt(this.nextIdle) == 0 ) {
                this.resetStandInterval();
                return this.mob.onGround() && this.mob.isLandNavigator();
            } else {
                return false;
            }
        }

        private void resetStandInterval() {
            this.nextIdle = 200;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
