package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractAmphibianCreature;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class Tiktaalik extends AbstractAmphibianCreature implements Bucketable, IAnimatedAttacker {

    int prevTicksOnGround;

    public AnimationState attackAnimationState = new AnimationState();
    int attackAnimationTimeout;
    public AnimationState fallAnimationState = new AnimationState();
    int fallAnimationTimeout;
    public AnimationState squashAnimationState = new AnimationState();
    int squashAnimationTimeout;


    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Tiktaalik.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> WANTS_TO_SWIM = SynchedEntityData.defineId(Tiktaalik.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Tiktaalik.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(Tiktaalik.class, EntityDataSerializers.INT);

    public Tiktaalik(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.jumpControl = new FishJumpControl(this);
        this.refreshDimensions();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AmphibianPanicGoal(this, 1.2D));
        this.goalSelector.addGoal(0, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(0, new LayEggGoal(this, 1.0D, BlockTags.SAND, MMBlocks.FLUBBER_EGG, 1D));

        this.goalSelector.addGoal(1, new AmphibianGoToWaterGoal(this, 1));
        this.goalSelector.addGoal(1, new AmphibianExitWaterGoal(this, 1.5));
        this.goalSelector.addGoal(1, new MarvelousTemptGoal(this, 1.2D, Ingredient.of(ItemTags.FISHES), false));

        this.goalSelector.addGoal(3, new AmphibianStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new BottomDwellerSwimGoal(this));
        this.goalSelector.addGoal(3, new BottomMoveGoal(this, 1, 80));

        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 900, 500));

        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !Tiktaalik.this.isSitting();
            }
        });

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && !Tiktaalik.this.isSitting();
            }
        });

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.25D, true, 6, 9));

        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)));
    }

    @Override
    public int getOutOfWaterChance() {
        return (int) (super.getOutOfWaterChance()*1.5);
    }

    @Override
    public int getIntoWaterChance() {
        return (int) (super.getIntoWaterChance()*0.75);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WANTS_TO_SWIM, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(SIZE, 0);
        this.entityData.define(IS_ATTACKING, false);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("Variant"));
    }

    //variants
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public String getVariantName(){
        return Tiktaalik.TiktaalikVariant.byId(this.getVariant()).getSerializedName();
    };

    public int getSize() {
        return this.entityData.get(SIZE);
    }

    public void setSize(int size) {
        this.entityData.set(SIZE, size);
    }

    public int getActualSize() {
        if (this.getSize()>=10){
            return getSize()-10;
        }else {
            return getSize();
        }
    }

    public String getSizeName(){
        return switch (this.getActualSize()){
            case 1 -> "medium";
            case 2 -> "big";
            default -> "small";
        };
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isBaby()){
            return super.getDimensions(pPose);
        }else {
            return switch (this.getActualSize()){
                case 1 -> super.getDimensions(pPose).scale(2.5f, 1.5f);
                case 2 -> super.getDimensions(pPose).scale(4.5f, 2.5f);
                default -> super.getDimensions(pPose);
            };
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    public boolean wantsToSwim() {
        return this.entityData.get(WANTS_TO_SWIM);
    }

    public void setWantsToSwim(boolean wantsToSwim) {
        if (wantsToSwim){
            this.lookControl = new SmoothSwimmingLookControl(this, 10);
        }else {
            this.lookControl = new LookControl(this);
        }
        this.entityData.set(WANTS_TO_SWIM, wantsToSwim);
    }

    @Override
    public int getMaxYRot() {
        return 45;
    }

    @Override
    public void calculateEntityAnimation(boolean pIncludeHeight) {
        super.calculateEntityAnimation(!this.isLandNavigator() && this.wantsToSwim());
    }

    public void tick() {
        super.tick();

        if (this.isInWaterOrBubble() && !this.isLandNavigator()){

            if (this.wantsToSwim()){
                if (this.onGround() || (!this.onGround() && (this.random.nextInt(800)==0)) ){
                    this.setWantsToSwim(false);
                }
            }

            if (this.onGround() && !this.wantsToSwim() && this.getRandom().nextInt(1200)==0){
                this.setWantsToSwim(true);
            }
        }

        if (!this.wantsToBeInLand() && this.isInWaterOrBubble()){
            this.setWantsToSwim(true);
        }

        if (!this.isBaby() && this.getRandom().nextInt(1500) == 0 && this.getSize()==0){
            if (this.getRandom().nextInt(3)>0)
                this.setSize(10);
            else {
                this.setSize(11);
            }
        }
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (stack.is(MMItems.GOLDEN_SACA.get()) && this.getActualSize() < 2){
            int size = this.getSize();
            if (size < 10){
                size = size+10;
            }
            this.setSize(size+1);

            this.playSound(SoundEvents.GENERIC_EAT, 0.75f, this.getVoicePitch());

            if (!pPlayer.isCreative())
                stack.shrink(1);

            return InteractionResult.SUCCESS;
        }
        return super.interactAt(pPlayer, pVec, pHand);
    }

    @Override
    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {

        if (!this.wantsToSwim()){
            this.setWantsToSwim(true);
        }

        super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    @Override
    public boolean hasToStandUpInstantly() {
        return false;
    }

    public void travel(Vec3 pTravelVector) {

        if (this.isEffectiveAi() && this.isInWater() && !this.wantsToSwim()) {
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        }

        if (this.isEffectiveAi() && this.isInWater() && this.wantsToSwim()) {
            if (this.getTarget() == null && this.random.nextInt(100)==0) {
                    this.setWantsToSwim(false);
            }
        }

        super.travel(pTravelVector);
    }

    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 15;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D && this.fallAnimationTimeout == 0 && !this.isInWaterOrBubble()) {
            this.fallAnimationState.start(this.tickCount);
            this.fallAnimationTimeout = 10;
        }

        if (this.fallAnimationTimeout > 0){
            this.fallAnimationTimeout--;

            if (this.onGround() && this.squashAnimationTimeout == 0 && !this.isInWaterOrBubble()){
                this.squashAnimationState.start(this.tickCount);
                this.squashAnimationTimeout = 15;
            }
        }

        if (this.squashAnimationTimeout > 0){
            this.squashAnimationTimeout--;
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Tiktaalik baby = MMEntities.TIKTAALIK.get().create(pLevel);
        Tiktaalik otherParent = (Tiktaalik) pOtherParent;
        if (baby != null){
            baby.setSize(0);
            baby.setVariant(this.getRandom().nextBoolean() ? this.getVariant() : otherParent.getVariant());
        }
        return baby;
    }

    @Override
    public boolean fromBucket() {
        return true;
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {}


    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putInt("Size", this.getSize());
        compoundnbt.putInt("Variant", this.getVariant());
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
        if (pTag.contains("Size")) {
            this.setSize(pTag.getInt("Size"));
        }
        if (pTag.contains("Variant")) {
            this.setVariant(pTag.getInt("Variant"));
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("Variant", 3)) {
            if (pDataTag.contains("Age"))
                this.setAge(pDataTag.getInt("Age"));
            this.setVariant(pDataTag.getInt("Variant"));
            this.setSize(pDataTag.getInt("Size"));
        }else if (pReason == MobSpawnType.TRIGGERED){
            int size = this.getRandom().nextBoolean() ? 0 : 1;
            if (this.getRandom().nextBoolean()){
                size = size+10;
            }
            this.setSize(size);
            this.setVariant(Util.getRandom(TiktaalikVariant.values(), this.getRandom()).id());
        }else {
            int size = this.getRandom().nextBoolean() ? 0 : 1;

            if (this.getRandom().nextBoolean()){
                size = size+10;
            }
            if (this.getRandom().nextInt(5)==0){
                size=12;
            }
            this.setSize(size);
            this.setVariant(Util.getRandom(TiktaalikVariant.values(), this.getRandom()).id());
        }
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (this.getActualSize()<2 || this.isBaby())
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.TIKTAALIK_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public int getStandDuration() {
        return 20;
    }

    @Override
    public int getSitDuration() {
        return 40;
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
        return 15;
    }

    @Override
    public void setAttackAnimationTimeout(int attackAnimationTimeout) {
        this.attackAnimationTimeout = attackAnimationTimeout;
    }

    @Override
    public @Nullable SoundEvent getAttackSound() {
        return SoundEvents.FOX_BITE;
    }

    static class FishJumpControl extends JumpControl {

        Tiktaalik mob;
        public FishJumpControl(Tiktaalik fish) {
            super(fish);
            mob = fish;
        }

        @Override
        public void jump() {
            if (!mob.isInWater()){
                super.jump();
            }
        }
    }

    public static boolean checkSurfaceWaterCreatureRules(EntityType<? extends MarvelousAnimal> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER) && CommonConfig.NATURAL_SPAWNS.get();
    }

    public static class BottomDwellerSwimGoal extends AmphibianSwimGoal {
        Tiktaalik pleco;

        public BottomDwellerSwimGoal(Tiktaalik mob) {
            super(mob, 1.0D, 10);
            this.pleco = mob;
        }

        @Override
        public boolean canUse() {
            return this.pleco.wantsToSwim() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.pleco.wantsToSwim() && super.canContinueToUse();
        }
    }

    public static class BottomMoveGoal extends RandomStrollGoal {
        Tiktaalik fish;
        public BottomMoveGoal(Tiktaalik pMob, double pSpeedModifier, int interval) {
            super(pMob, pSpeedModifier, interval);
            this.fish = pMob;
        }

        @Override
        public boolean canUse() {
            return !this.fish.wantsToSwim() && this.fish.onGround() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.fish.wantsToSwim() && super.canContinueToUse();
        }

        @Nullable
        @Override
        protected Vec3 getPosition() {
            return DefaultRandomPos.getPos(this.fish, 10, 1);
        }
    }

    public enum TiktaalikVariant implements StringRepresentable{
        BLUE(0, "blue"),
        GREEN(1, "green"),
        BROWN(2, "brown"),
        ROSE(3, "rose");

        private static final IntFunction<TiktaalikVariant> BY_ID = ByIdMap.sparse(TiktaalikVariant::id, values(), BLUE);
        public static final Codec<TiktaalikVariant> CODEC = StringRepresentable.fromEnum(TiktaalikVariant::values);
        final int id;
        private final String name;

        TiktaalikVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static TiktaalikVariant byId(int pId) {
            return BY_ID.apply(pId);
        }
    }

    @Override
    public float getVoicePitch() {
        return this.getActualSize() == 2 ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F : super.getVoicePitch();
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        if (this.isInWaterOrBubble()){
            this.playSound(SoundEvents.FISH_SWIM, 0.05F, this.getVoicePitch());
        }else {
            this.playSound(SoundEvents.FROG_STEP, 0.15F, this.getVoicePitch());
        }
    }
}
