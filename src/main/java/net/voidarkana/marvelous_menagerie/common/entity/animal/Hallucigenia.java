package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.FishBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.BottomDwellerWaterCreature;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class Hallucigenia extends BottomDwellerWaterCreature implements Bucketable {

    public final AnimationState stingAnimationState = new AnimationState();
    int stingTimeout;

    public final AnimationState stretchState = new AnimationState();
    private int stretchTimeout;
    private int stretchCounter;
    public final AnimationState admireState = new AnimationState();
    private int admireTimeout;
    private int admireCounter;


    private static final Predicate<LivingEntity> SCARY_MOB = (p_289442_) -> {
        if (p_289442_ instanceof Player && ((Player)p_289442_).isCreative()) {
            return false;
        } else {
            return p_289442_.getType() != MMEntities.FLUBBER.get() && p_289442_.getType() != EntityType.AXOLOTL && p_289442_.getMobType() != MobType.WATER;
        }
    };

    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Hallucigenia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FLOP_SIDE = SynchedEntityData.defineId(Hallucigenia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STINGING = SynchedEntityData.defineId(Hallucigenia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Hallucigenia.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Hallucigenia.class, EntityDataSerializers.BYTE);

    public Hallucigenia(EntityType<? extends MarvelousWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.switchNavigator(false);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D, 80){
            @Nullable
            @Override
            protected Vec3 getPosition() {
                return DefaultRandomPos.getPos(this.mob, 10, 1);
            }
        });
        this.goalSelector.addGoal(2, new FishBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, this.fintasticFoodIngredients(), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, this.foodIngredients(), false));

        this.goalSelector.addGoal(9, new Hallucigenia.RandomIdleGoal(this));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(FLOP_SIDE, false);
        this.entityData.define(STINGING, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putBoolean("FlopSide", this.flopSide());
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setFlopSide(pCompound.getBoolean("FlopSide"));
        this.setVariant(pCompound.getInt("Variant"));
    }

    public void setAdmiring(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    public void setStretching(boolean pStanding) {
        this.setFlag(16, pStanding);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isAdmiring() {
        return this.getFlag(32);
    }

    public boolean isStretching() {
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
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    public boolean flopSide() {
        return this.entityData.get(FLOP_SIDE);
    }

    public void setFlopSide(boolean flopSide){
        this.entityData.set(FLOP_SIDE, flopSide);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int var){
        this.entityData.set(VARIANT, var);
    }

    public boolean isStinging() {
        return this.entityData.get(STINGING);
    }

    public void setStinging(boolean stinging){
        this.entityData.set(STINGING, stinging);
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(Items.BONE_MEAL);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isAlive()) {
            for(Mob mob : this.level().getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(0.3D), (p_149013_) -> {
                return targetingConditions.test(this, p_149013_);
            })) {
                if (mob.isAlive() && this.canAttack(mob)) {
                    this.touch(mob);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isStinging())
            this.setStinging(false);

        if (this.isEffectiveAi()){
            if (this.admireCounter > 0 && ++this.admireCounter > 110) {
                this.admireCounter = 0;
                this.setAdmiring(false);
            }
            if (this.stretchCounter > 0 && ++this.stretchCounter > 120) {
                this.stretchCounter = 0;
                this.setStretching(false);
            }
        }
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();
        if (this.isStinging() && stingTimeout == 0){
            this.stingAnimationState.start(this.tickCount);
            stingTimeout = 10;
        }else if (stingTimeout>0){
            stingTimeout--;
        }

        if (this.isAdmiring() && this.admireTimeout <= 0){
            this.admireTimeout = 110;
            this.admireState.start(this.tickCount);
        }else if (0 < this.admireTimeout ){
            --this.admireTimeout;
        }

        if (this.isStretching() && this.stretchTimeout <= 0){
            this.stretchTimeout = 120;
            this.stretchState.start(this.tickCount);
        }else if (0 < this.stretchTimeout ){
            --this.stretchTimeout;
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isAdmiring() || this.isStretching();
    }

    public void admireIfPossible() {
        if (this.isEffectiveAi()) {
            this.admireCounter = 1;
            this.setAdmiring(true);
        }
    }

    public void stretchIfPossible() {
        if (this.isEffectiveAi()) {
            this.stretchCounter = 1;
            this.setStretching(true);
        }
    }

    @Override
    public @Nullable SoundEvent getFlopSound() {
        return null;
    }

    private void touch(Mob pMob) {

        if (pMob.hurt(this.damageSources().mobAttack(this), (float)(1))) {

            if (this.getRandom().nextInt(100)>75){
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.0F);

                this.spawnAtLocation(MMItems.HALLUCIGENIC_SLIME.get());
            }

            pMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 3), this);
            pMob.addEffect(new MobEffectInstance(MMEffects.HALLUCINATING.get(), 120, 0), this);
            pMob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
            this.setStinging(true);
        }
    }

    public void playerTouch(Player pEntity) {

        if (pEntity instanceof ServerPlayer && pEntity.hurt(this.damageSources().mobAttack(this), (float)(1))) {
            if (!this.isSilent()) {
                ((ServerPlayer)pEntity).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }

            if (this.getRandom().nextInt(100)>75){
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.0F);

                this.spawnAtLocation(MMItems.HALLUCIGENIC_SLIME.get());
            }

            pEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 3), this);
            pEntity.addEffect(new MobEffectInstance(MMEffects.HALLUCINATING.get(), 120, 0), this);
            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.005F, 1.25F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putInt("Variant", this.getVariant());
        compoundnbt.putBoolean("CanGrowUp", this.getCanGrowUp());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Age"))
            this.setAge(pTag.getInt("Age"));
        if (pTag.contains("Variant"))
            this.setVariant(pTag.getInt("Variant"));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.HALLUCIGENIA_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        this.setFlopSide(this.random.nextBoolean());
        this.setVariant(this.random.nextInt(0, 2));

        if (reason == MobSpawnType.TRIGGERED){
            this.setFromBucket(true);
        }

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Age", 3)) {
            this.setFromBucket(true);
            if (dataTag.contains("Age")) {
                this.setAge(dataTag.getInt("Age"));}
            if (dataTag.contains("Variant"))
                this.setVariant(dataTag.getInt("Variant"));
            if (dataTag.contains("CanGrowUp"))
                this.setCanGrowUp(dataTag.getBoolean("CanGrowUp"));
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public @Nullable MarvelousWaterAnimal getBreedOffspring(ServerLevel pLevel, MarvelousWaterAnimal pOtherParent) {
        Hallucigenia baby = MMEntities.HALLUCIGENIA.get().create(pLevel);
        Hallucigenia otherParent = (Hallucigenia) pOtherParent;
        if (baby!=null)
            baby.setVariant(this.random.nextBoolean() ? this.getVariant() : otherParent.getVariant());
        return baby;
    }

    public String getVariantName(){
        if (this.getVariant() == 0){
            return "pink";
        }else {
            return "purple";
        }
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.hasCustomName() && !this.fromBucket();
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    public static class RandomIdleGoal extends Goal {
        private final Hallucigenia mob;
        private int nextIdle;

        public RandomIdleGoal(Hallucigenia mob) {
            this.mob = mob;
            this.resetStandInterval();
        }

        public void start() {
            if (this.mob.getRandom().nextBoolean())
                this.mob.stretchIfPossible();
            else
                this.mob.admireIfPossible();

            this.playStandSound();
        }

        private void playStandSound() {
            this.mob.playSound(SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, 0.75f, this.mob.getVoicePitch());
        }

        public boolean canContinueToUse() {
            return false;
        }

        public boolean canUse() {
            --this.nextIdle;
            if (this.nextIdle > 0 && this.mob.getRandom().nextInt(this.nextIdle) == 0 ) {
                this.resetStandInterval();
                return !this.mob.isImmobile() && this.mob.onGround();
            } else {
                return false;
            }
        }

        private void resetStandInterval() {
            this.nextIdle = 900;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}
