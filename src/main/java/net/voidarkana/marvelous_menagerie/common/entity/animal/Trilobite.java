package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.FishBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BottomDwellerWaterCreature;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;


public class Trilobite extends BottomDwellerWaterCreature implements Bucketable {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState spinIdleAnimationState = new AnimationState();
    private int idleSpinTimeout = 0;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT_MODEL = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT_BASE_COLOR = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT_SECOND_COLOR = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HIGHLIGHT_COLOR = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_HIGHLIGHT = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> LGBT_VARIANT = SynchedEntityData.defineId(Trilobite.class, EntityDataSerializers.INT);

    public Trilobite(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
        switchNavigator(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5){
            @Override
            protected boolean shouldPanic() {
                return super.shouldPanic() && Trilobite.this.isInWaterOrBubble();
            }
        });
        this.goalSelector.addGoal(1, new MoveToWaterGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D, 80){
            @Nullable
            @Override
            protected Vec3 getPosition() {
                return DefaultRandomPos.getPos(this.mob, 10, 1);
            }

            @Override
            public boolean canUse() {
                return Trilobite.this.isInWaterOrBubble() && super.canUse();
            }
        });
        this.goalSelector.addGoal(2, new FishBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 2D, this.foodIngredients(), false));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(VARIANT_MODEL, 0);
        this.entityData.define(VARIANT_BASE_COLOR, 0);
        this.entityData.define(VARIANT_SECOND_COLOR, 0);
        this.entityData.define(HIGHLIGHT_COLOR, 0);
        this.entityData.define(LGBT_VARIANT, 0);
        this.entityData.define(HAS_HIGHLIGHT, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putInt("Model", this.getVariantModel());
        pCompound.putInt("BaseColor", this.getVariantBaseColor());
        pCompound.putInt("SecondColor", this.getVariantSecondColor());
        pCompound.putInt("HighlightColor", this.getHighlightColor());
        pCompound.putBoolean("HasHighlight", this.getHasHighlight());
        pCompound.putInt("LGBTVariant", this.getLGBTVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setVariantModel(pCompound.getInt("Model"));
        this.setVariantBaseColor(pCompound.getInt("BaseColor"));
        this.setVariantSecondColor(pCompound.getInt("SecondColor"));
        this.setHighlightColor(pCompound.getInt("HighlightColor"));
        this.setHasHighlight(pCompound.getBoolean("HasHighlight"));
        this.setLGBTVariant(pCompound.getInt("LGBTVariant"));
    }

    //variant model
    public void setLGBTVariant(int variant) {
        this.entityData.set(LGBT_VARIANT, variant);}

    public int getLGBTVariant() {
        return this.entityData.get(LGBT_VARIANT);}

    //variant model
    public void setVariantModel(int variant) {
        this.entityData.set(VARIANT_MODEL, variant);}

    public int getVariantModel() {
        return this.entityData.get(VARIANT_MODEL);}

    //variant model
    public void setVariantBaseColor(int variant) {
        this.entityData.set(VARIANT_BASE_COLOR, variant);}

    public int getVariantBaseColor() {
        return this.entityData.get(VARIANT_BASE_COLOR);}

    //variant model
    public void setVariantSecondColor(int variant) {
        this.entityData.set(VARIANT_SECOND_COLOR, variant);}

    public int getVariantSecondColor() {
        return this.entityData.get(VARIANT_SECOND_COLOR);}

    //pattern color
    public void setHighlightColor(int variant) {
        this.entityData.set(HIGHLIGHT_COLOR, variant);}

    public int getHighlightColor() {
        return this.entityData.get(HIGHLIGHT_COLOR);}

    public void setHasHighlight(boolean variant) {
        this.entityData.set(HAS_HIGHLIGHT, variant);}

    public boolean getHasHighlight() {
        return this.entityData.get(HAS_HIGHLIGHT);}


    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.getVariantModel() == 6) {
            return super.getDimensions(pPose).scale(1.5F, 1.75F);
        } else if (this.getVariantModel() == 5){
            return super.getDimensions(pPose).scale(0.5F, 1F);
        }else {
            return super.getDimensions(pPose);
        }
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();

        if (this.isInWaterOrBubble() && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (!this.isInWaterOrBubble() && !this.isLandNavigator) {
            switchNavigator(true);
        }
    }

    private void setupAnimationStates() {

        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if (this.idleSpinTimeout <= 0 && this.getNavigation().isDone()) {
            this.idleSpinTimeout = this.random.nextInt(40) + 80;
            this.spinIdleAnimationState.start(this.tickCount);
        } else {
            --this.idleSpinTimeout;
        }
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 >= 1.33D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }

    }

    @Override
    public @Nullable SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
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
        compoundnbt.putBoolean("CanGrow", this.getCanGrowUp());
        compoundnbt.putInt("Model", this.getVariantModel());
        compoundnbt.putInt("BaseColor", this.getVariantBaseColor());
        compoundnbt.putInt("SecondColor", this.getVariantSecondColor());
        compoundnbt.putInt("HighlightColor", this.getHighlightColor());
        compoundnbt.putBoolean("HasHighlight", this.getHasHighlight());
        compoundnbt.putInt("LGBTVariant", this.getLGBTVariant());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.TRILO_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (itemstack.is(MMItems.MAGIC_ROLL.get()) && this.getLGBTVariant() == 0){

            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            ParticleOptions particleoptions = ParticleTypes.NOTE;

            if (this.random.nextInt(10) == 0) {
                this.setLGBTVariant(this.random.nextInt(1, 14));
            } else {
                particleoptions = ParticleTypes.SMOKE;
            }

            for(int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }

            this.playSound(SoundEvents.CAT_EAT, this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F);

            return InteractionResult.SUCCESS;
        }

        return super.interactAt(pPlayer, pVec, pHand);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("Model", 3)) {

            System.out.println(dataTag.getInt("Age"));

            this.setVariantModel(dataTag.getInt("Model"));
            this.setVariantBaseColor(dataTag.getInt("BaseColor"));
            this.setVariantSecondColor(dataTag.getInt("SecondColor"));
            this.setLGBTVariant(dataTag.getInt("LGBTVariant"));
            this.setFromBucket(true);
            this.setCanGrowUp(dataTag.getBoolean("CanGrow"));
            this.setAge(dataTag.getInt("Age"));
            this.setHighlightColor(dataTag.getInt("HighlightColor"));
            this.setHasHighlight(dataTag.getBoolean("HasHighlight"));

        }else{
            int variantModelChange = this.random.nextInt(0, 7);
            int variantColorBaseChange = this.random.nextInt(0, 25);
            int variantColorSecondChange = this.random.nextInt(0, 25);
            int variantColorHighlight = this.random.nextInt(0, 25);
            boolean hasHighlight = this.random.nextInt(3) == 0;

            this.setVariantModel(variantModelChange);
            this.setVariantBaseColor(variantColorBaseChange);
            this.setVariantSecondColor(variantColorSecondChange);
            this.setHighlightColor(variantColorHighlight);
            this.setHasHighlight(hasHighlight);

            int LGBTVariant = this.random.nextInt(1, 14);
            if (this.random.nextInt(50) == 0){
                this.setLGBTVariant(LGBTVariant);
            }else{
                this.setLGBTVariant(0);
            }
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public @Nullable BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Trilobite otherParent = (Trilobite) pOtherParent;
        Trilobite baby = MMEntities.TRILOBITE.get().create(pLevel);

        int mainColor;
        int secondColor;
        boolean hasHighlightColor;
        int highlightColor;

        if (baby != null){
            int lowerQuality = Math.min(this.getFeedQuality(), otherParent.getFeedQuality());

            switch (lowerQuality){
                case 1:
                    if (this.random.nextBoolean()){
                        mainColor = this.random.nextInt(25);
                        secondColor = this.random.nextInt(25);
                        hasHighlightColor = this.random.nextInt(3)==0;
                        highlightColor = this.random.nextInt(25);
                    }else {
                        mainColor = this.random.nextBoolean() ? this.getVariantBaseColor() : otherParent.getVariantBaseColor();
                        secondColor = this.random.nextBoolean() ? this.getVariantSecondColor(): otherParent.getVariantSecondColor();
                        hasHighlightColor = this.random.nextBoolean() ? this.getHasHighlight() : otherParent.getHasHighlight();
                        highlightColor = this.random.nextBoolean() ? this.getHighlightColor() : otherParent.getHighlightColor();
                    }
                    break;

                case 2:

                    mainColor = this.random.nextBoolean() ? this.getVariantBaseColor() : otherParent.getVariantBaseColor();
                    secondColor = this.random.nextBoolean() ? this.getVariantSecondColor(): otherParent.getVariantSecondColor();
                    hasHighlightColor = this.random.nextBoolean() ? this.getHasHighlight() : otherParent.getHasHighlight();
                    highlightColor = this.random.nextBoolean() ? this.getHighlightColor() : otherParent.getHighlightColor();

                    break;

                case 3:
                    boolean parent = this.random.nextBoolean();

                    mainColor = parent ? this.getVariantBaseColor() : otherParent.getVariantBaseColor();
                    secondColor = parent ? this.getVariantSecondColor(): otherParent.getVariantSecondColor();
                    hasHighlightColor = parent ? this.getHasHighlight() : otherParent.getHasHighlight();
                    highlightColor = parent ? this.getHighlightColor() : otherParent.getHighlightColor();

                    break;

                default:

                    mainColor = this.random.nextInt(25);
                    secondColor = this.random.nextInt(25);
                    hasHighlightColor = this.random.nextInt(3)==0;
                    highlightColor = this.random.nextInt(25);

                    break;
            }

            int LGBTVariant = this.random.nextInt(1, 14);
            if (this.random.nextInt(60) == 0){
                baby.setLGBTVariant(LGBTVariant);
            }else{
                baby.setLGBTVariant(0);
            }

            baby.setVariantModel(this.getVariantModel());
            baby.setVariantBaseColor(mainColor);
            baby.setVariantSecondColor(secondColor);
            baby.setHasHighlight(hasHighlightColor);
            baby.setHighlightColor(highlightColor);

            baby.setFromBucket(true);
        }

        return baby;
    }

    @Override
    public boolean canMate(BreedableWaterAnimal pOtherAnimal) {
        Trilobite mate = (Trilobite)pOtherAnimal;
        return this.getVariantModel() == mate.getVariantModel() && super.canMate(mate);
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.hasCustomName() && !this.fromBucket();
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.ARTHROPOD_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.ARTHROPOD_DEATH.get();
    }

    public boolean isLGBTrilo(){
        return this.getLGBTVariant()>0;
    }

    public static String getLGBTVariantName(int i){
        return switch(i){
            case 1 -> "ace";
            case 2 -> "agender";
            case 3 -> "aro";
            case 4 -> "aroace";
            case 5 -> "bi";
            case 6 -> "enby";
            case 7 -> "gay";
            case 8 -> "genderfluid";
            case 9 -> "genderqueer";
            case 10 -> "lesbian";
            case 11 -> "lgbt";
            case 12 -> "pan";
            case 13 -> "trans";
            default ->  "";
        };
    }

    static class MoveToWaterGoal extends MoveToBlockGoal {

        private final Trilobite turtle;

        MoveToWaterGoal(Trilobite pTurtle, double pSpeedModifier) {
            super(pTurtle, pSpeedModifier, 24);
            this.turtle = pTurtle;
            this.verticalSearchStart = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.turtle.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.turtle.level(), this.blockPos);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.turtle.isInWater() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        /**
         * Return {@code true} to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    @Override
    public int getMaxAirSupply() {
        return 4800;
    }

    public static String getModelName(int i){
        return switch (i){
            case 1 -> "trident";
            case 2 -> "moon";
            case 3 -> "spiky";
            case 4 -> "feisty";
            case 5 -> "itty";
            case 6 -> "fat";
            default -> "curly";
        };
    }

    public static String getSize(int i){
        return switch (i){
            case 0,1,2,3,4 -> "mid";
            case 5 -> "itty";
            default -> "fat";
        };
    }

    public static String getColorName(int i){
        return switch (i){
            case 1 ->  "black";
            case 2 ->  "blue";
            case 3 ->  "carbon";
            case 4 ->  "clay";
            case 5 ->  "cocoa";
            case 6 ->  "dirt";
            case 7 ->  "fig";
            case 8 ->  "gravel";
            case 9 ->  "green";
            case 10 -> "lime";
            case 11 -> "marsh";
            case 12 -> "minsk";
            case 13 -> "mossy";
            case 14 -> "mud";
            case 15 -> "olive";
            case 16 -> "orange";
            case 17 -> "pink";
            case 18 -> "purple";
            case 19 -> "red";
            case 20 -> "rosy";
            case 21 -> "sand";
            case 22 -> "teal";
            case 23 -> "white";
            case 24 -> "yellow";
            default -> "brown";
        };
    }
}
