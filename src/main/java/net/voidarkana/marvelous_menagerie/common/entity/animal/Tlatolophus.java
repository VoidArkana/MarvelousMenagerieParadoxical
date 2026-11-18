package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.*;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Tlatolophus extends TamableMarvelousAnimal implements Saddleable, IEggLayer, IAnimatedEater {

    public final AnimationState standState = new AnimationState();
    private int standTimeout = 0;
    private int standCounter;

    public final AnimationState callState = new AnimationState();
    private int callTimeout = 0;
    private int callCounter;

    public final AnimationState surveyState = new AnimationState();
    private int surveyTimeout = 0;
    private int surveyCounter;

    public final AnimationState shakeState = new AnimationState();
    private int shakeTimeout = 0;
    private int shakeCounter;

    private boolean isContending;
    protected Tlatolophus contender;
    private int contendingCooldown = 0;
    private boolean victorious;

    int layEggCounter;

    private static final EntityDataAccessor<Integer> PREGNANT_TIME = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> EATING_TIME = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> BASE_VARIANT = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SECOND_VARIANT = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> CAST = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(Tlatolophus.class, EntityDataSerializers.BYTE);

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.FERN);

    public Tlatolophus(EntityType<? extends TamableMarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setMaxUpStep(1.5F);
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation)this.getNavigation();
        groundpathnavigation.setCanWalkOverFences(true);
    }

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0F));

        this.targetSelector.addGoal(1, new HerdPanicGoal(this, 1.25));

        this.goalSelector.addGoal(1, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LayEggGoal(this, 1.0D, MMTags.Blocks.DINOSAUR_NEST, MMBlocks.BOREALOPELTA_EGG, 1d));

        this.goalSelector.addGoal(2, new MarvelousTemptGoal(this, 1.15D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 1600, 2000));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(4, new TlatoHerdGoal(this));
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isBaby()) {
            return super.getDimensions(pPose).scale(0.75F, 0.5F);
        }else {
            return super.getDimensions(pPose);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false);
        this.entityData.define(BASE_VARIANT, 0);
        this.entityData.define(SECOND_VARIANT, 0);
        this.entityData.define(CAST, TlatolophusCast.FOLLOWER.getSerializedName());
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(EATING_TIME, 0);
        this.entityData.define(PREGNANT_TIME, 0);
        this.entityData.define(IS_LAYING_EGG, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Saddle", this.isSaddled());
        compound.putInt("BaseVariant", this.getBaseVariant());
        compound.putInt("SecondVariant", this.getSecondVariant());
        compound.putString("Cast", this.getCastName());

        compound.putBoolean("IsPregnant", this.isPregnant());
        compound.putBoolean("IsLayingEgg", this.isLayingEgg());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSaddled(compound.getBoolean("Saddle"));
        this.setBaseVariant(compound.getInt("BaseVariant"));
        this.setSecondVariant(compound.getInt("SecondVariant"));
        this.setCastName(compound.getString("Cast"));

        this.setPregnant(compound.getBoolean("IsPregnant"));
        this.setLayingEgg(compound.getBoolean("IsLayingEgg"));
    }

    public int getBaseVariant() {return this.entityData.get(BASE_VARIANT);}
    public String getBaseVariantName() {return DarkTlatolophusVariant.byId(this.getBaseVariant()).getSerializedName();}
    public void setBaseVariant(int variant) {this.entityData.set(BASE_VARIANT, variant);}

    public int  getSecondVariant() {return this.entityData.get(SECOND_VARIANT);}
    public String getSecondVariantName() {return LightTlatolophusVariant.byId(this.getSecondVariant()).getSerializedName();}
    public void setSecondVariant(int variant) {this.entityData.set(SECOND_VARIANT, variant);}

    @Override public boolean isSaddled() {return this.entityData.get(SADDLED);}
    public void setSaddled(boolean saddled) {this.entityData.set(SADDLED, saddled);}

    public String getCastName() {return this.entityData.get(CAST);}
    public void setCastName(String cast) {this.entityData.set(CAST, cast);}
    public TlatolophusCast getCast() {
        if (this.isBaby())
            return TlatolophusCast.FOLLOWER;
        return TlatolophusCast.byName(this.getCastName());
    }
    public void setCast(TlatolophusCast cast) {this.setCastName(cast.getSerializedName());}


    public int getPregnantTime() {
        return this.entityData.get(PREGNANT_TIME);
    }

    public void setPregnantTime(int pregnant) {
        this.entityData.set(PREGNANT_TIME, pregnant);
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

    public void setShaking(boolean calling) {
        this.setFlag(8, calling);
    }

    public void setCalling(boolean calling) {
        this.setFlag(16, calling);
    }

    public void setStanding(boolean pStanding) {
        this.setFlag(32, pStanding);
    }

    public void setSurveying(boolean calling) {
        this.setFlag(64, calling);
    }

    protected boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_ID_FLAGS) & pFlagId) != 0;
    }

    public boolean isShaking() {
        return this.getFlag(8);
    }

    public boolean isCalling() {
        return this.getFlag(16);
    }

    public boolean isStanding() {
        return this.getFlag(32);
    }

    public boolean isSurveying() {
        return this.getFlag(64);
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
    public void onEggLaid() {
        this.setLayingEgg(false);
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setSprinting(d0 >= 1.2D);
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }

    protected void dropEquipment() {
        super.dropEquipment();

        if (this.isSaddled()) {
            this.spawnAtLocation(new ItemStack(Items.SADDLE));
        }
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        Tlatolophus baby = MMEntities.TLATOLOPHUS.get().create(level);
        Tlatolophus otherParent = (Tlatolophus) mob;
        if (baby != null){
            baby.setBaseVariant(this.getRandom().nextBoolean() ? Util.getRandom(DarkTlatolophusVariant.values(), this.getRandom()).id() :
                    this.getRandom().nextBoolean() ? this.getBaseVariant() : otherParent.getBaseVariant());

            baby.setSecondVariant(this.getRandom().nextBoolean() ? Util.getRandom(LightTlatolophusVariant.values(), this.getRandom()).id() :
                    this.getRandom().nextBoolean() ? this.getSecondVariant() : otherParent.getSecondVariant());
        }
        return baby;
    }

    @Override
    public int getEatingTicks() {
        return 0;
    }

    @Override
    public void setEatingTicks(int eating) {

    }

    @Override
    public boolean isEating() {
        return false;
    }

    @Override
    public int eatAnimationTimeout() {
        return 0;
    }

    @Override
    public void setEatAnimationTimeout(int eatAnimationTimeout) {

    }

    @Override
    public @Nullable SoundEvent getEatSound() {
        return null;
    }

    @Override
    public boolean isSaddleable() {
        return this.isTame();
    }

    @Override
    public void equipSaddle(@Nullable SoundSource pSource) {

    }

    @Override
    public boolean canSit() {
        return true;
    }

    @Override
    public int getSitDuration() {
        return 40;
    }

    @Override
    public int getStandDuration() {
        return 20;
    }

    @javax.annotation.Nullable
    protected Tlatolophus leader;
    private int schoolSize = 1;

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    public boolean isFollower() {
        return !this.isBaby() && this.leader != null && this.leader.isAlive()
                && this.leader.getCast().getHierarchy()> this.getCast().getHierarchy();
    }

    public Tlatolophus startFollowing(Tlatolophus pLeader) {
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
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize()
                && this.getCast().getHierarchy()>0 && !this.isBaby();
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 120.0D && this.distanceToSqr(this.leader) > 5.0D;
    }


    public boolean wantsToGetCloserToLeader() {
        return this.inRangeOfLeader() && this.distanceToSqr(this.leader) > 30.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.15D);
        }
    }

    public void addFollowers(Stream<? extends Tlatolophus> pFollowers) {
        pFollowers.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((tlatolophus) -> {
            return tlatolophus != this && this.getCast().getHierarchy() > tlatolophus.getCast().getHierarchy();
        }).forEach((tlatolophus) -> {
            tlatolophus.startFollowing(this);
        });
    }

    //TODO: check determining system
    @Override
    public void tick() {
        super.tick();

        if (this.isEffectiveAi()){
            if (this.standCounter > 0 && ++this.standCounter > 80) {
                this.standCounter = 0;
                this.setStanding(false);
                if (this.isContending){
                    this.isContending = false;

                    if (this.victorious){

                        this.broadcastIfPossible();

                        if (this.isFollower()){
                            this.setCast(TlatolophusCast.byHierarchy(this.getCast().hierarchy+1));
                            this.contender.setCast(TlatolophusCast.byHierarchy(this.contender.getCast().hierarchy-1));
                        }

                        this.addParticlesAroundSelf(ParticleTypes.FLAME);

                        this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);

                    }else {
                        this.addParticlesAroundSelf(ParticleTypes.SMOKE);

                        this.shakeIfPossible();

                        this.playSound(MMSounds.ELE_GRUMBLE.get(), this.getSoundVolume(), this.getVoicePitch());
                    }
                }
            }

            if (this.callCounter > 0 && ++this.callCounter > 60) {
                this.callCounter = 0;
                this.setCalling(false);
            }

            if (this.surveyCounter > 0 && ++this.surveyCounter > 80) {
                this.surveyCounter = 0;
                this.setSurveying(false);
            }

            if (this.shakeCounter > 0 && ++this.shakeCounter > 60) {
                this.shakeCounter = 0;
                this.setShaking(false);
            }
        }

        if (this.getRandom().nextInt(500)==0 && (!this.isFollower()
                || this.getRandom().nextInt(1500)==0) && !this.level().isClientSide()
                && !this.isImmobile()) {

            Predicate<Tlatolophus> predicate = (leader) -> leader.getCast().getHierarchy()>this.getCast().getHierarchy();

            List<? extends Tlatolophus> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(64.0D, 16, 64.0D), predicate);

            if (this.getCast() == TlatolophusCast.FOLLOWER){
                if (list.size() < 6){
                    this.setCast(TlatolophusCast.LEADER);
                    this.broadcastIfPossible();
                }else {
                    this.contender = list.get(0);
                    list.get(0).contender = this;
                }
            }else if (this.getCast() == TlatolophusCast.LEADER){
                if (list.size() == 0){
                    this.setCast(TlatolophusCast.ALPHA);
                    this.broadcastIfPossible();
                }else {
                    this.contender = list.get(0);
                    list.get(0).contender = this;
                }
            }
        }

        if(contendingCooldown > 0){
            contendingCooldown--;
        }
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.isStanding() && this.standTimeout <= 0){
            this.standTimeout = 80;
            this.standState.start(this.tickCount);
        }else if (0 < this.standTimeout ){
            --this.standTimeout;
        }

        if (this.isCalling() && this.callTimeout <= 0){
            this.callTimeout = 60;
            this.callState.start(this.tickCount);
        }else if (0 < this.callTimeout ){
            --this.callTimeout;
        }

        if (this.isSurveying() && this.surveyTimeout <= 0){
            this.surveyTimeout = 80;
            this.surveyState.start(this.tickCount);
        }else if (0 < this.surveyTimeout ){
            --this.surveyTimeout;
        }

        if (this.isShaking() && this.shakeTimeout <= 0){
            this.shakeTimeout = 80;
            this.shakeState.start(this.tickCount);
        }else if (0 < this.shakeTimeout ){
            --this.shakeTimeout;
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || this.isStanding();
    }

    public void broadcastIfPossible() {
        if (!this.isBaby()){
            this.surveyIfPossible();
            this.callIfPossible();
        }
    }

    public void contendIfPossible() {
        if (!this.isBaby()){
            this.standIfPossible();
            this.callIfPossible();
            if (this.isEffectiveAi()){
                this.isContending = true;
                this.victorious = this.getRandom().nextBoolean();
                this.contender.victorious = !this.victorious;
            }
        }
    }

    public void shakeIfPossible() {
        if (this.isEffectiveAi()) {
            this.shakeCounter = 1;
            this.setShaking(true);
        }
    }

    public void surveyIfPossible() {
        if (this.isEffectiveAi()) {
            this.surveyCounter = 1;
            this.setSurveying(true);
        }
    }

    public void standIfPossible() {
        if (this.isEffectiveAi()) {
            this.standCounter = 1;
            this.setStanding(true);
        }
    }

    public void callIfPossible() {
        if (this.isEffectiveAi()) {
            this.callCounter = 1;
            this.setCalling(true);
            this.playSound(SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(this.getRandom().nextInt(8)).get(), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setBaseVariant(Util.getRandom(DarkTlatolophusVariant.values(), this.getRandom()).id());
        this.setSecondVariant(Util.getRandom(LightTlatolophusVariant.values(), this.getRandom()).id());
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (!this.isFollower() || this.getCast() == TlatolophusCast.ALPHA)
            return super.getWalkTargetValue(pos, level);
        return pos.distSqr(this.leader.blockPosition()) > 32 ? 0 : 10 ;
    }

    public enum DarkTlatolophusVariant implements StringRepresentable {
        NONE(0, ""),
        PANTALOONS(1, "pantaloons"),
        TUXEDO(2, "tuxedo"),
        SHAWL(3, "shawl"),
        DARK_STRIPES(4, "stripes_dark");

        private static final IntFunction<DarkTlatolophusVariant> BY_ID = ByIdMap.sparse(DarkTlatolophusVariant::id, values(), NONE);
        public static final Codec<DarkTlatolophusVariant> CODEC = StringRepresentable.fromEnum(DarkTlatolophusVariant::values);
        public static final EnumCodec<DarkTlatolophusVariant> NAME_CODEC = StringRepresentable.fromEnum(DarkTlatolophusVariant::values);
        final int id;
        private final String name;

        DarkTlatolophusVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static DarkTlatolophusVariant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static DarkTlatolophusVariant byName(String pName) {
            return NAME_CODEC.byName(pName, NONE);
        }
    }

    public enum LightTlatolophusVariant implements StringRepresentable {
        NONE(0, ""),
        SPOTS(1, "spots"),
        DOTS(2, "dots"),
        SPLOTCHY(3, "splotchy"),
        WHITE_STRIPES(4, "stripes_white");

        private static final IntFunction<LightTlatolophusVariant> BY_ID = ByIdMap.sparse(LightTlatolophusVariant::id, values(), NONE);
        public static final Codec<LightTlatolophusVariant> CODEC = StringRepresentable.fromEnum(LightTlatolophusVariant::values);
        public static final EnumCodec<LightTlatolophusVariant> NAME_CODEC = StringRepresentable.fromEnum(LightTlatolophusVariant::values);
        final int id;
        private final String name;

        LightTlatolophusVariant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int id() {
            return this.id;
        }

        public static LightTlatolophusVariant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static LightTlatolophusVariant byName(String pName) {
            return NAME_CODEC.byName(pName, NONE);
        }
    }

    public enum TlatolophusCast implements StringRepresentable {
        FOLLOWER("follower", 0),
        LEADER("leader", 1),
        ALPHA("alpha", 2);

        private static final IntFunction<TlatolophusCast> BY_ID = ByIdMap.sparse(TlatolophusCast::getHierarchy, values(), FOLLOWER);
        public static final Codec<TlatolophusCast> CODEC = StringRepresentable.fromEnum(TlatolophusCast::values);
        public static final EnumCodec<TlatolophusCast> NAME_CODEC = StringRepresentable.fromEnum(TlatolophusCast::values);
        private final String name;
        private final int hierarchy;


        TlatolophusCast(String pName, int hierarchy) {
            this.name = pName;
            this.hierarchy = hierarchy;
        }

        public String getSerializedName() {
            return this.name;
        }

        public int getHierarchy() {
            return hierarchy;
        }

        public static TlatolophusCast byHierarchy(int pId) {
            return BY_ID.apply(pId);
        }

        public static TlatolophusCast byName(String pName) {
            return NAME_CODEC.byName(pName, FOLLOWER);
        }
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(MMSounds.LARGE_STEPS.get(), 0.15F, this.getVoicePitch());
    }

    @Override
    public boolean canBeCollidedWith() {
        return super.canBeCollidedWith() || this.isImmobile();
    }

    class TlatoHerdGoal extends Goal {
        private static final int INTERVAL_TICKS = 200;
        private final Tlatolophus mob;
        private int timeToRecalcPath;
        private int nextStartTick;

        public TlatoHerdGoal(Tlatolophus pFish) {
            this.mob = pFish;
            this.nextStartTick = this.nextStartTick(pFish);
        }

        protected int nextStartTick(Tlatolophus pTaskOwner) {
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
                Predicate<Tlatolophus> predicate = (fish) -> {
                    return fish.canBeFollowed() || fish.getCast().getHierarchy()>0;
                };
                List<? extends Tlatolophus> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
                Tlatolophus leader = DataFixUtils.orElse(list.stream().filter(Tlatolophus::canBeFollowed).findAny(), this.mob);
                leader.addFollowers(list.stream().filter((follower) -> {
                    return !follower.isFollower() && follower.getCast().getHierarchy() < leader.getCast().getHierarchy();
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
            if (--this.timeToRecalcPath <= 0 && this.mob.wantsToGetCloserToLeader()) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.mob.pathToLeader();
            }
        }
    }

    class ContendGoal extends Goal {
        private final Tlatolophus entity;
        private int executionCooldown = 50;
        private final int cooldown;
        private final float speedModifier;
        private int timeToRecalcPath;

        public ContendGoal(Tlatolophus pMob, int pCooldown, float speedModifier) {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
            this.speedModifier = speedModifier;
            this.entity = pMob;
            this.cooldown = pCooldown;
        }

        @Override
        public boolean canUse() {
            if (entity.isInWaterOrBubble() || entity.contendingCooldown > 0) {
                return false;
            }
            if (entity.contender != null && entity.contender.isAlive()) {
                return true;
            }
            if (executionCooldown > 0) {
                executionCooldown--;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return entity.contender != null && entity.contender.isAlive();
        }

        @Override
        public void start() {
            this.timeToRecalcPath = 0;
        }

        @Override
        public void stop() {
            this.entity.contendingCooldown = cooldown + this.entity.getRandom().nextInt(cooldown / 2);
            this.entity.contender = null;
            this.entity.navigation.stop();
            executionCooldown = 50 + this.entity.getRandom().nextInt(50);
        }

        public void tick() {
            if (--this.timeToRecalcPath <= 0 && this.entity.distanceTo(this.entity.contender) > 2) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.entity.getNavigation().moveTo(this.entity.contender, this.speedModifier);
            }else if (this.entity.distanceTo(this.entity.contender) <= 2){
                this.entity.contendIfPossible();
                this.entity.contender.contendIfPossible();
            }
        }
    }

    @Override
    public void setLastHurtByMob(@Nullable LivingEntity pLivingEntity) {
        super.setLastHurtByMob(pLivingEntity);
        if (this.isFollower())
            this.leader.setLastHurtByMob(pLivingEntity);
    }

    public class HerdPanicGoal extends Goal {
        public static final int WATER_CHECK_DISTANCE_VERTICAL = 1;
        protected final Tlatolophus mob;
        protected final double speedModifier;
        protected double posX;
        protected double posY;
        protected double posZ;
        protected boolean isRunning;

        public HerdPanicGoal(Tlatolophus pMob, double pSpeedModifier) {
            this.mob = pMob;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (!this.shouldPanic()) {
                return false;
            } else {
                if (this.mob.isOnFire()) {
                    BlockPos blockpos = this.lookForWater(this.mob.level(), this.mob, 5);
                    if (blockpos != null) {
                        this.posX = (double)blockpos.getX();
                        this.posY = (double)blockpos.getY();
                        this.posZ = (double)blockpos.getZ();
                        return true;
                    }
                }

                return this.findRandomPosition();
            }
        }

        protected boolean shouldPanic() {
            return this.mob.getLastHurtByMob() != null || this.mob.leader.getLastHurtByMob() != null || this.mob.isFreezing() || this.mob.isOnFire();
        }

        protected boolean findRandomPosition() {
            Vec3 vec3 = DefaultRandomPos.getPos(this.mob, 5, 4);
            if (vec3 == null) {
                return false;
            } else {
                this.posX = vec3.x;
                this.posY = vec3.y;
                this.posZ = vec3.z;
                return true;
            }
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
            this.isRunning = true;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.isRunning = false;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.mob.getNavigation().isDone();
        }

        @javax.annotation.Nullable
        protected BlockPos lookForWater(BlockGetter pLevel, Entity pEntity, int pRange) {
            BlockPos blockpos = pEntity.blockPosition();
            return !pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty() ? null : BlockPos.findClosestMatch(pEntity.blockPosition(), pRange, 1, (p_196649_) -> {
                return pLevel.getFluidState(p_196649_).is(FluidTags.WATER);
            }).orElse((BlockPos)null);
        }
    }
}
