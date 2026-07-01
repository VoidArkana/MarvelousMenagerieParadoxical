package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.FlyAndAttachGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.FlyingAttachableAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.IntFunction;

public class Anurognathus extends FlyingAttachableAnimal implements Bucketable {

    public final AnimationState idleLookState1 = new AnimationState();
    public final AnimationState idleLookState2 = new AnimationState();
    public final AnimationState idleAttachedState = new AnimationState();
    private int idleStateTimeout = this.random.nextInt(180) + 60;

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Anurognathus.class, EntityDataSerializers.INT);

    public Anurognathus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return Ingredient.of(Items.SPIDER_EYE, Items.HONEY_BOTTLE).test(pStack);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.MAX_HEALTH, 3)
                .add(Attributes.FOLLOW_RANGE, 52);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.25f));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25f, Ingredient.of(Items.SPIDER_EYE, Items.HONEY_BOTTLE), false));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 15, false) {

            @Override
            public boolean canUse() {
                return !Anurognathus.this.isFlying() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !Anurognathus.this.isFlying() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(3, new FlyAndAttachGoal(this, 1.5F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
    }

    public String getBaseColor(){
        int colorID = this.getVariant() / 10;
        AnurognathusBase color = AnurognathusBase.byId(colorID);
        return color.getSerializedName();
    }

    public static String getBaseColor(int variant){
        int colorID = variant / 10;
        AnurognathusBase color = AnurognathusBase.byId(colorID);
        return color.getSerializedName();
    }

    @Nullable
    public String getPattern(){
        int patternID = this.getVariant() % 10;
        if (patternID == 0)
            return null;
        AnurognathusPattern pattern = AnurognathusPattern.byId(patternID);
        return pattern.getSerializedName();
    }

    public static String getPattern(int variant){
        int patternID = variant % 10;
        if (patternID == 0)
            return null;
        AnurognathusPattern pattern = AnurognathusPattern.byId(patternID);
        return pattern.getSerializedName();
    }
    
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        float width = this.isAttached() ? 0.6F : 1F;
        float height = this.isAttached() ? 2.1F : 1F;
        return super.getDimensions(pPose).scale(width, height);
    }

    @Override
    public void tick() {
        if (this.isInWaterOrBubble() && !this.isFlying()) {
            this.setFlying(true);
        }
        super.tick();
    }

    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.idleStateTimeout <= 0 && !this.isFlying()) {
            this.idleStateTimeout = this.random.nextInt(180) + 60;
            if (this.onGround()){
                if (this.getRandom().nextBoolean())
                    this.idleLookState1.start(this.tickCount);
                else
                    this.idleLookState2.start(this.tickCount);
            }else if (this.isAttached()){
                this.idleAttachedState.start(this.tickCount);
            }
        } else {
            --this.idleStateTimeout;
        }
    }

    @Override
    public SoundEvent getFlapSound() {
        return SoundEvents.PARROT_FLY;
    }

    @Override
    public int getFlapRate() {
        return 10;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Anurognathus baby = MMEntities.ANUROGNATHUS.get().create(pLevel);
        Anurognathus otherParent = (Anurognathus) pOtherParent;
        if (baby != null) {
            int baseColor = this.getRandom().nextBoolean() ? Util.getRandom(AnurognathusBase.values(), this.getRandom()).id() : this.getRandom().nextBoolean() ? otherParent.getVariant()/10 : this.getVariant()/10;
            int pattern = this.getRandom().nextBoolean() ? Util.getRandom(AnurognathusPattern.values(), this.getRandom()).id() :  this.getRandom().nextBoolean() ? otherParent.getVariant()%10 : this.getVariant()%10;
            baby.setVariant((baseColor*10)+pattern);
        }

        return baby;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (super.hurt(pSource, pAmount)){
            this.addDeltaMovement(new Vec3(0, 0.15, 0));
            this.setFlying(true);
            this.switchNavigator(false);
            return true;
        }
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int baseColor = Util.getRandom(AnurognathusBase.values(), this.getRandom()).id();
        int pattern = Util.getRandom(AnurognathusPattern.values(), this.getRandom()).id();

        this.setVariant((baseColor*10)+pattern);

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return MMSounds.ANUROGNATHUS_IDLE.get();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return MMSounds.ANUROGNATHUS_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return MMSounds.ANUROGNATHUS_DEATH.get();
    }

    public int getAmbientSoundInterval() {
        return this.getRandom().nextInt(300, 500);
    }

    @Override
    public boolean fromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {}

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("Age", this.getAge());
        compoundnbt.putInt("Variant", this.getVariant());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Variant")){
            this.setVariant(pTag.getInt("Variant"));
        }
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.ANURO_BUCKET.get());
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        return pickupAnurognathus(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    static <T extends LivingEntity & Bucketable> Optional<InteractionResult> pickupAnurognathus(Player pPlayer, InteractionHand pHand, T pEntity) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.getItem() == Items.FLOWER_POT && pEntity.isAlive()) {
            pEntity.playSound(pEntity.getPickupSound(), 1.0F, 1.0F);
            ItemStack itemstack1 = pEntity.getBucketItemStack();
            pEntity.saveToBucketTag(itemstack1);
            ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemstack1, false);
            pPlayer.setItemInHand(pHand, itemstack2);
            Level level = pEntity.level();
            if (!level.isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)pPlayer, itemstack1);
            }

            pEntity.discard();
            return Optional.of(InteractionResult.sidedSuccess(level.isClientSide));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public SoundEvent getPickupSound() {
        return MMSounds.ANURO_BUCKET_CATCH.get();
    }

    public enum AnurognathusPattern implements StringRepresentable {
        PLAIN(0, ""),
        STRIPES(1, "stripes"),
        EYES(2, "eyes"),
        DOTTED(3, "dotted"),
        STARS(4, "stars");

        private static final IntFunction<AnurognathusPattern> BY_ID = ByIdMap.sparse(AnurognathusPattern::id, values(), PLAIN);
        public static final Codec<AnurognathusPattern> CODEC = StringRepresentable.fromEnum(AnurognathusPattern::values);
        final int id;
        private final String name;

        AnurognathusPattern(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static AnurognathusPattern byId(int pId) {
            return BY_ID.apply(pId);
        }
    }

    public enum AnurognathusBase implements StringRepresentable {
        BROWN(0, "brown"),
        BLACK(1, "black"),
        GOLDEN(2, "golden"),
        ORANGE(3, "orange"),
        WHITE(4, "white");

        private static final IntFunction<AnurognathusBase> BY_ID = ByIdMap.sparse(AnurognathusBase::id, values(), BROWN);
        public static final Codec<AnurognathusBase> CODEC = StringRepresentable.fromEnum(AnurognathusBase::values);
        final int id;
        private final String name;

        AnurognathusBase(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static AnurognathusBase byId(int pId) {
            return BY_ID.apply(pId);
        }
    }
}
