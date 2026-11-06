package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus.TlatolophusDarkPatternLayer;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.*;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedEater;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeTreeConfiguration;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Tlatolophus extends TamableMarvelousAnimal implements Saddleable, IEggLayer, IAnimatedEater {

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
        this.goalSelector.addGoal(0, new AnimatedAttackGoal(this, 1.35D, true, 27, 53));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0F));

        this.targetSelector.addGoal(1, new BabyPanicGoal(this, 1.25).setAlertOthers());

        this.goalSelector.addGoal(1, new EggLayerBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LayEggGoal(this, 1.0D, MMTags.Blocks.DINOSAUR_NEST, MMBlocks.BOREALOPELTA_EGG, 1d));

        this.goalSelector.addGoal(2, new MarvelousTemptGoal(this, 1.2D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));

        this.goalSelector.addGoal(5, new RandomlySitUpOrDownGoal(this, 1600, 2000));

        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(4, new TlatoHerdGoal(this));

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

    @Override
    public void onEggLaid() {
        this.setLayingEgg(false);
    }

    //TODO: fix
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
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
        if (this.getRandom().nextInt(100)==0 && (!this.isFollower() || this.getRandom().nextInt(500)==0)) {

            Predicate<Tlatolophus> predicate = (leader) -> leader.canBeFollowed() && leader.getCast().getHierarchy()>this.getCast().getHierarchy();

            List<? extends Tlatolophus> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);

            if (this.getCast() == TlatolophusCast.FOLLOWER){
                if (list.size() < 6)
                    this.setCast(TlatolophusCast.LEADER);
            }

            if (list.isEmpty()){
                if (this.getCast() == TlatolophusCast.LEADER)
                    this.setCast(TlatolophusCast.ALPHA);
            }
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
        if (!this.isFollower() || this.getCast().getHierarchy() == 2)
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

        public static TlatolophusCast byName(String pName) {
            return NAME_CODEC.byName(pName, FOLLOWER);
        }
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
}
