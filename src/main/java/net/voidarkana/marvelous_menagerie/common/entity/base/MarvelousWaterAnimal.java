package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.Cancelable;
import net.voidarkana.marvelous_menagerie.common.entity.ai.MarvelousSwimmingMoveControl;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class MarvelousWaterAnimal extends WaterAnimal implements ISittingAnimal {

    public float currentRoll = 0.0F;
    public float prevRoll;

    private static final EntityDataAccessor<Boolean> IS_INVENTORY = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.LONG);

    public static final int BABY_START_AGE = -24000;
    private static final int FORCED_AGE_PARTICLE_TICKS = 40;
    protected int age;
    protected int forcedAge;
    protected int forcedAgeTimer;
    int prevTicksOutOfWater;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState standUpAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();

    @Override
    public boolean isImmobile() {
        if (this.canSit())
            return this.isInPoseTransition() || super.isImmobile();
        return super.isImmobile();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        this.setFromInventory(false);

        if (pSpawnData == null) {
            pSpawnData = new AgeableFishGroupData(true);
        }

        AgeableFishGroupData ageablemob$ageablemobgroupdata = (AgeableFishGroupData)pSpawnData;
        if (ageablemob$ageablemobgroupdata.isShouldSpawnBaby() && ageablemob$ageablemobgroupdata.getGroupSize() > 0 && pLevel.getRandom().nextFloat() <= ageablemob$ageablemobgroupdata.getBabySpawnChance()) {
            this.setAge(-24000);
        }
        if (this.canSit()){
            this.resetLastPoseChangeTickToFullStand(pLevel.getLevel().getGameTime());
        }

        ageablemob$ageablemobgroupdata.increaseGroupSizeByOne();
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Nullable
    public abstract MarvelousWaterAnimal getBreedOffspring(ServerLevel pLevel, MarvelousWaterAnimal pOtherParent);

    public int getAge() {
        if (this.level().isClientSide) {
            return this.entityData.get(DATA_BABY_ID) ? -1 : 1;
        } else {
            return this.age;
        }
    }

    public void ageUp(int pAmount, boolean pForced, ItemStack stack) {
        int i = this.getAge();
        i += pAmount * 20;
        if (i > 0) {
            i = 0;
        }

        int j = i - i;
        this.setAge(i);
        if (pForced) {
            this.forcedAge += j;
            if (this.forcedAgeTimer == 0) {
                this.forcedAgeTimer = 40;
            }
        }

        if (this.getAge() == 0) {
            this.setAge(this.forcedAge);
        }

    }

//    public void ageUp(int pAmount) {
//        this.ageUp(pAmount, false);
//    }

    public void setAge(int pAge) {
        int i = this.getAge();
        this.age = pAge;
        if (i < 0 && pAge >= 0 || i >= 0 && pAge < 0) {
            this.entityData.set(DATA_BABY_ID, pAge < 0);
            this.ageBoundaryReached();
        }

    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BABY_ID.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

    protected void ageBoundaryReached() {
        if (!this.isBaby() && this.isPassenger()) {
            Entity entity = this.getVehicle();
            if (entity instanceof Boat) {
                Boat boat = (Boat)entity;
                if (!boat.hasEnoughSpaceFor(this)) {
                    this.stopRiding();
                }
            }
        }

    }

    @Override
    public boolean isBaby() {
        return this.getAge() < 0;
    }

    public void setBaby(boolean pBaby) {
        this.setAge(pBaby ? -24000 : 0);
    }

    public static int getSpeedUpSecondsWhenFeeding(int pTicksUntilAdult) {
        return (int)((float)(pTicksUntilAdult / 20) * 0.1F);
    }

    public static class AgeableFishGroupData implements SpawnGroupData {
        private int groupSize;
        private final boolean shouldSpawnBaby;
        private final float babySpawnChance;

        private AgeableFishGroupData(boolean pShouldSpawnBaby, float pBabySpawnChance) {
            this.shouldSpawnBaby = pShouldSpawnBaby;
            this.babySpawnChance = pBabySpawnChance;
        }

        public AgeableFishGroupData(boolean pShouldSpawnBaby) {
            this(pShouldSpawnBaby, 0.05F);
        }

        public AgeableFishGroupData(float pBabySpawnChance) {
            this(true, pBabySpawnChance);
        }

        public int getGroupSize() {
            return this.groupSize;
        }

        public void increaseGroupSizeByOne() {
            ++this.groupSize;
        }

        public boolean isShouldSpawnBaby() {
            return this.shouldSpawnBaby;
        }

        public float getBabySpawnChance() {
            return this.babySpawnChance;
        }
    }

    @Override
    public void tick() {

        if (this.isInWaterOrBubble()){
            if (this.getInWaterTicks()<this.getInWaterTickBase()){
                int prevTicks = this.getInWaterTicks();
                this.setInWaterTicks(prevTicks+1);
            }
        }else {
            if (this.getInWaterTicks()>0){
                int prevTicks = this.getInWaterTicks();
                this.setInWaterTicks(prevTicks-1);
            }
        }

        if (this.onGround()){
            if (this.getonGroundTicks()<this.getonGroundTickBase()){
                int prevTicks = this.getonGroundTicks();
                this.setonGroundTicks(prevTicks+1);
            }
        }else {
            if (this.getonGroundTicks()>0){
                int prevTicks = this.getonGroundTicks();
                this.setonGroundTicks(prevTicks-1);
            }
        }

        if (this.isAggressive()){
            if (this.getAggroTicks()<this.getAggroTickBase()){
                int prevTicks = this.getAggroTicks();
                this.setAggroTicks(prevTicks+1);
            }
        }else {
            if (this.getAggroTicks()>0){
                int prevTicks = this.getAggroTicks();
                this.setAggroTicks(prevTicks-1);
            }
        }

        if (this.isSitting()){
            if (this.getSittingTicks()<this.getSittingTickBase()){
                int prevTicks = this.getSittingTicks();
                this.setSittingTicks(prevTicks+1);
            }
        }else {
            if (this.getSittingTicks()>0){
                int prevTicks = this.getSittingTicks();
                this.setSittingTicks(prevTicks-1);
            }
        }

        if (this.isSprinting()){
            if (this.getSprintingTicks()<this.getSprintingTickBase()){
                int prevTicks = this.getSprintingTicks();
                this.setSprintingTicks(prevTicks+1);
            }
        }else {
            if (this.getSprintingTicks()>0){
                int prevTicks = this.getSprintingTicks();
                this.setSprintingTicks(prevTicks-1);
            }
        }
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        super.tick();
        if (this.canSit()){
            if (this.refuseToMove()) {
                this.clampHeadRotationToBody(this, 30.0F);
            }

            if (this.isSitting() && !this.isInWaterOrBubble()) {
                this.standUpInstantly();
            }
        }
    }

    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);

        if (this.canSit()){
            if (this.isVisuallySitting()) {
                this.standUpAnimationState.stop();
                if (this.isVisuallySittingDown()) {
                    this.sitAnimationState.startIfStopped(this.tickCount);
                    this.sitPoseAnimationState.stop();
                } else {
                    this.sitAnimationState.stop();
                    this.sitPoseAnimationState.startIfStopped(this.tickCount);
                }
            } else {
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.stop();
                this.standUpAnimationState.animateWhen(this.isInPoseTransition() && this.getPoseTime() >= 0L, this.tickCount);
            }
        }
    }

    private void clampHeadRotationToBody(Entity pEntity, float p_265541_) {
        float f = pEntity.getYHeadRot();
        float f1 = Mth.wrapDegrees(this.yBodyRot - f);
        float f2 = Mth.clamp(Mth.wrapDegrees(this.yBodyRot - f), -p_265541_, p_265541_);
        float f3 = f + f1 - f2;
        pEntity.setYHeadRot(f3);
    }

    protected void onLeashDistance(float pDistance) {
        if (pDistance > 6.0F && this.isSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    public boolean standsWhenHurt(){
        return true;
    }

    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        if (this.standsWhenHurt())
            this.standUpInstantly();
        super.actuallyHurt(pDamageSource, pDamageAmount);
    }

    //animal

    protected static final int PARENT_AGE_AFTER_BREEDING = 6000;
    private int inLove;
    @Nullable
    private UUID loveCause;

    protected void customServerAiStep() {
        if (this.getAge() != 0) {
            this.inLove = 0;
        }

        super.customServerAiStep();
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            this.inLove = 0;
            return super.hurt(pSource, pAmount);
        }
    }

    public int getExperienceReward() {
        return 1 + this.level().random.nextInt(3);
    }

    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        if (!pPlayer.getAbilities().instabuild) {
            pStack.shrink(1);
        }

    }

    public boolean canFallInLove() {
        return this.inLove <= 0;
    }

    public void setInLove(@Nullable Player pPlayer) {
        this.inLove = 600;
        if (pPlayer != null) {
            this.loveCause = pPlayer.getUUID();
        }

        this.level().broadcastEntityEvent(this, (byte)18);
    }

    public void setInLoveTime(int pInLove) {
        this.inLove = pInLove;
    }

    public int getInLoveTime() {
        return this.inLove;
    }

    @Nullable
    public ServerPlayer getLoveCause() {
        if (this.loveCause == null) {
            return null;
        } else {
            Player player = this.level().getPlayerByUUID(this.loveCause);
            return player instanceof ServerPlayer ? (ServerPlayer)player : null;
        }
    }

    public boolean isInLove() {
        return this.inLove > 0;
    }

    public void resetLove() {
        this.inLove = 0;
    }

    public boolean canMate(MarvelousWaterAnimal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        } else if (pOtherAnimal.getClass() != this.getClass()) {
            return false;
        } else {
            return this.isInLove() && pOtherAnimal.isInLove();
        }
    }

    public void spawnChildFromBreeding(ServerLevel pLevel, MarvelousWaterAnimal pMate) {
        MarvelousWaterAnimal ageablemob = this.getBreedOffspring(pLevel, pMate);
        MarvelousWaterAnimal ageableMob2 = null;
        MarvelousWaterAnimal ageableMob3 = null;
        MarvelousWaterAnimal ageableMob4 = null;
        MarvelousWaterAnimal ageableMob5 = null;

        int lowerQuality = Math.min(this.getFeedQuality(), pMate.getFeedQuality());

        final BabyFishSpawnEvent event = new BabyFishSpawnEvent(this, pMate, ageablemob);
        ageablemob = event.getChild();

        if ((lowerQuality > 0 && this.random.nextBoolean()) || lowerQuality > 2){
            ageableMob2 = this.getBreedOffspring(pLevel, pMate);
            final BabyFishSpawnEvent event2 = new BabyFishSpawnEvent(this, pMate, ageableMob2);
            ageableMob2 = event2.getChild();

            if ((lowerQuality > 1 && this.random.nextInt(4)==0) || (lowerQuality > 2 && this.random.nextBoolean())){
                ageableMob3 = this.getBreedOffspring(pLevel, pMate);
                final BabyFishSpawnEvent event3 = new BabyFishSpawnEvent(this, pMate, ageableMob3);
                ageableMob3 = event3.getChild();

                if (lowerQuality > 2 && this.random.nextBoolean()){

                    ageableMob4 = this.getBreedOffspring(pLevel, pMate);
                    final BabyFishSpawnEvent event4 = new BabyFishSpawnEvent(this, pMate, ageableMob4);
                    ageableMob4 = event4.getChild();

                    if (this.random.nextBoolean()){
                        ageableMob5 = this.getBreedOffspring(pLevel, pMate);
                        final BabyFishSpawnEvent event5 = new BabyFishSpawnEvent(this, pMate, ageableMob5);
                        ageableMob5 = event5.getChild();
                    }
                }
            }
        }

        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (cancelled) {
            //Reset the "inLove" state for the animals
            this.setAge(6000);
            pMate.setAge(6000);
            this.resetLove();
            pMate.resetLove();
            return;
        }
        if (ageablemob != null) {

            ageablemob.setBaby(true);
            ageablemob.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageablemob);
            pLevel.addFreshEntityWithPassengers(ageablemob);

            if (ageableMob2 != null){

                ageableMob2.setBaby(true);
                ageableMob2.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob2);
                pLevel.addFreshEntityWithPassengers(ageableMob2);

                if (ageableMob3 != null){

                    ageableMob3.setBaby(true);
                    ageableMob3.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                    this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob3);
                    pLevel.addFreshEntityWithPassengers(ageableMob3);

                    if (ageableMob4 != null){

                        ageableMob4.setBaby(true);
                        ageableMob4.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                        this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob4);
                        pLevel.addFreshEntityWithPassengers(ageableMob4);

                        if (ageableMob5 != null){

                            ageableMob5.setBaby(true);
                            ageableMob5.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
                            this.finalizeSpawnChildFromBreeding(pLevel, pMate, ageableMob5);
                            pLevel.addFreshEntityWithPassengers(ageableMob5);
                        }
                    }
                }
            }
        }
    }

    @Cancelable
    public class BabyFishSpawnEvent extends net.minecraftforge.eventbus.api.Event
    {
        private final Mob parentA;
        private final Mob parentB;
        private final Player causedByPlayer;
        private MarvelousWaterAnimal child;

        public BabyFishSpawnEvent(Mob parentA, Mob parentB, @org.jetbrains.annotations.Nullable MarvelousWaterAnimal proposedChild)
        {
            //causedByPlayer calculated here to simplify the patch.
            Player causedByPlayer = null;
            if (parentA instanceof MarvelousWaterAnimal) {
                causedByPlayer = ((MarvelousWaterAnimal)parentA).getLoveCause();
            }

            if (causedByPlayer == null && parentB instanceof MarvelousWaterAnimal)
            {
                causedByPlayer = ((MarvelousWaterAnimal)parentB).getLoveCause();
            }

            this.parentA = parentA;
            this.parentB = parentB;
            this.causedByPlayer = causedByPlayer;
            this.child = proposedChild;
        }

        public Mob getParentA()
        {
            return parentA;
        }

        public Mob getParentB()
        {
            return parentB;
        }

        @org.jetbrains.annotations.Nullable
        public Player getCausedByPlayer()
        {
            return causedByPlayer;
        }

        @org.jetbrains.annotations.Nullable
        public MarvelousWaterAnimal getChild()
        {
            return child;
        }

        public void setChild(MarvelousWaterAnimal proposedChild)
        {
            child = proposedChild;
        }
    }

    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, MarvelousWaterAnimal pAnimal, @Nullable MarvelousWaterAnimal pBaby) {
        Optional.ofNullable(this.getLoveCause()).or(() -> {
            return Optional.ofNullable(pAnimal.getLoveCause());
        }).ifPresent((p_277486_) -> {
            p_277486_.awardStat(Stats.ANIMALS_BRED);
            //CriteriaTriggers.BRED_ANIMALS.trigger(p_277486_, this, pAnimal, pBaby);

            LootContext $$4 = EntityPredicate.createContext(p_277486_, this);
            LootContext $$5 = EntityPredicate.createContext(p_277486_, pAnimal);
            LootContext $$6 = pBaby != null ? EntityPredicate.createContext(p_277486_, pBaby) : null;
            CriteriaTriggers.BRED_ANIMALS.trigger(p_277486_, (p_18653_) -> p_18653_.matches($$4, $$5, $$6));
        });
        this.setAge(6000);
        this.setFromInventory(false);
        pAnimal.setAge(6000);
        this.resetLove();
        pAnimal.resetLove();
        pLevel.broadcastEntityEvent(this, (byte)18);
        if (pLevel.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            pLevel.addFreshEntity(new ExperienceOrb(pLevel, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }

    }

    public void handleEntityEvent(byte pId) {
        if (pId == 18) {
            for(int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }






    private static final EntityDataAccessor<Integer> FEED_TYPE = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CAN_GROW_UP = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.BOOLEAN);

    protected MarvelousWaterAnimal(EntityType<? extends MarvelousWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        if (this.hasNormalControls()){
            this.moveControl = new MarvelousSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
            this.lookControl = new SmoothSwimmingLookControl(this, 10);
        }

    }

    protected BodyRotationControl createBodyControl() {
        if (this.canSit())
            return new MarvelousWaterAnimal.SittingAnimalBodyRotationControl(this);
        else
            return super.createBodyControl();
    }

    class SittingAnimalBodyRotationControl extends BodyRotationControl {
        public SittingAnimalBodyRotationControl(MarvelousWaterAnimal pCamel) {
            super(pCamel);
        }

        public void clientTick() {
            if (!MarvelousWaterAnimal.this.refuseToMove()) {
                super.clientTick();
            }

        }
    }

    public boolean canSit(){
        return false;
    }

    public boolean refuseToMove() {
        return this.isSitting() || this.isInPoseTransition();
    }

    public boolean isSitting() {
        if (!this.canSit()){
            return false;
        }
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public boolean isVisuallySitting() {
        return this.getPoseTime() < 0L != this.isSitting();
    }

    public int getSitDuration(){
        return 0;
    }

    public int getStandDuration(){
        return 0;
    }

    public boolean isInPoseTransition() {
        long i = this.getPoseTime();
        return i < (long)(this.isSitting() ? this.getSitDuration() : this.getStandDuration());
    }

    public boolean isVisuallySittingDown() {
        return this.isSitting() && this.getPoseTime() < this.getSitDuration() && this.getPoseTime() >= 0L;
    }

    public void sitDown() {
        if (this.canSit() && !this.isSitting()){
            this.setPose(Pose.SITTING);
            this.resetLastPoseChangeTick(-this.level().getGameTime());
            this.refreshDimensions();
        }
    }

    public void standUp() {
        if (this.isSitting()){
            this.setPose(Pose.STANDING);
            this.resetLastPoseChangeTick(this.level().getGameTime());
            if (this.isVehicle()){
                for(int i = this.getPassengers().size() - 1; i >= 0; --i) {
                    if (!(this.getPassengers().get(i) instanceof Player))
                        this.getPassengers().get(i).stopRiding();
                }
            }
            this.refreshDimensions();
        }
    }

    public void standUpInstantly() {
        this.setPose(Pose.STANDING);
        this.resetLastPoseChangeTickToFullStand(this.level().getGameTime());
    }

    @Override
    public boolean hasToStandUpInstantly() {
        return !this.isInWater();
    }

    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);
        if (this.canSit()&& pPlayer.zza > 0.0F && this.isSitting() && !this.isInPoseTransition()) {
            this.standUp();
        }
    }

    public void resetLastPoseChangeTick(long pLastPoseChangeTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, pLastPoseChangeTick);
    }

    public void resetLastPoseChangeTickToFullStand(long pLastPoseChangedTick) {
        this.resetLastPoseChangeTick(Math.max(0L, pLastPoseChangedTick - this.getStandDuration() - 1L));
    }

    public long getPoseTime() {
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public boolean hasNormalControls(){
        return true;
    }

    public static final EntityDataAccessor<Integer> ON_GROUND_TICKS = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> IN_WATER_TICKS = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SITTING_TICKS = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SPRINTING_TICKS = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> AGGRO_TICKS = SynchedEntityData.defineId(MarvelousWaterAnimal.class, EntityDataSerializers.INT);


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FEED_TYPE, 0);
        this.entityData.define(CAN_GROW_UP, true);
        this.entityData.define(IS_INVENTORY, true);
        this.entityData.define(DATA_BABY_ID, false);
        this.entityData.define(LAST_POSE_CHANGE_TICK, 0L);
        this.entityData.define(AGGRO_TICKS, 0);
        this.entityData.define(IN_WATER_TICKS, 0);
        this.entityData.define(ON_GROUND_TICKS, 0);
        this.entityData.define(SPRINTING_TICKS, 0);
        this.entityData.define(SITTING_TICKS, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Age", this.getAge());
        pCompound.putInt("ForcedAge", this.forcedAge);
        pCompound.putBoolean("CanGrowUp", this.getCanGrowUp());
        pCompound.putBoolean("IsFromInventory", this.isFromInventory());

        pCompound.putInt("FeedQuality", this.getFeedQuality());

        pCompound.putInt("InLove", this.inLove);
        if (this.loveCause != null) {
            pCompound.putUUID("LoveCause", this.loveCause);
        }

        if (this.canSit()){
            pCompound.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));
        }
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAge(pCompound.getInt("Age"));
        this.forcedAge = pCompound.getInt("ForcedAge");
        this.setCanGrowUp(pCompound.getBoolean("CanGrowUp"));
        this.setFromInventory(pCompound.getBoolean("IsFromInventory"));

        this.setFeedQuality(pCompound.getInt("FeedQuality"));

        this.inLove = pCompound.getInt("InLove");
        this.loveCause = pCompound.hasUUID("LoveCause") ? pCompound.getUUID("LoveCause") : null;

        if (this.canSit()){
            long i = pCompound.getLong("LastPoseTick");
            if (i < 0L) {
                this.setPose(Pose.SITTING);
            }

            this.resetLastPoseChangeTick(i);
        }
    }

    public int getInWaterTicks(){
        return this.entityData.get(IN_WATER_TICKS);
    }

    public void setInWaterTicks(int ticks){
        this.entityData.set(IN_WATER_TICKS, ticks);
    }

    public int getInWaterTickBase(){
        return 5;
    }

    public float getInWaterMultiplier(){
        return (float) this.getInWaterTicks() /this.getInWaterTickBase();
    }

    public int getonGroundTicks(){
        return this.entityData.get(ON_GROUND_TICKS);
    }

    public void setonGroundTicks(int ticks){
        this.entityData.set(ON_GROUND_TICKS, ticks);
    }

    public int getonGroundTickBase(){
        return 5;
    }

    public float getOnGroundMultiplier(){
        return (float) this.getonGroundTicks() /this.getonGroundTickBase();
    }

    public int getSprintingTicks(){
        return this.entityData.get(SPRINTING_TICKS);
    }

    public void setSprintingTicks(int ticks){
        this.entityData.set(SPRINTING_TICKS, ticks);
    }

    public int getSprintingTickBase(){
        return 5;
    }

    public float getSprintingMultiplier(){
        return (float) this.getSprintingTicks() /this.getSprintingTickBase();
    }

    public int getAggroTicks(){
        return this.entityData.get(AGGRO_TICKS);
    }

    public void setAggroTicks(int ticks){
        this.entityData.set(AGGRO_TICKS, ticks);
    }

    public int getAggroTickBase(){
        return 5;
    }

    public float getAggroMultiplier(){
        return (float) this.getAggroTicks() /this.getAggroTickBase();
    }

    public int getSittingTicks(){
        return this.entityData.get(SITTING_TICKS);
    }

    public void setSittingTicks(int ticks){
        this.entityData.set(SITTING_TICKS, ticks);
    }

    public int getSittingTickBase(){
        return 5;
    }

    public float getSittingMultiplier(){
        return (float) this.getSittingTicks() /this.getSittingTickBase();
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public int getFeedQuality() {
        return this.entityData.get(FEED_TYPE);
    }

    public void setFeedQuality(int variant) {
        this.entityData.set(FEED_TYPE, variant);
    }

    public Boolean getCanGrowUp() {
        return this.entityData.get(CAN_GROW_UP);
    }

    public void setCanGrowUp(boolean variant) {
        this.entityData.set(CAN_GROW_UP, variant);
    }

    public Boolean isFromInventory() {
        return this.entityData.get(IS_INVENTORY);
    }

    public void setFromInventory(boolean variant) {
        this.entityData.set(IS_INVENTORY, variant);
    }

    @Override
    public void aiStep() {

        if (this.isFromInventory())
            this.setFromInventory(false);

        if (!this.isInWater() && this.onGround() && this.verticalCollision && this.canFlop()) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();

        //ageable mob
        if (this.level().isClientSide) {
            if (this.forcedAgeTimer > 0) {
                if (this.forcedAgeTimer % 4 == 0) {
                    this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                }

                --this.forcedAgeTimer;
            }
        } else if (this.isAlive()) {
            int i = this.getAge();
            if (i < 0) {
                ++i;
                this.setAge(i);
            } else if (i > 0) {
                --i;
                this.setAge(i);
            }
        }

        //animal
        if (this.getAge() != 0) {
            this.inLove = 0;
        }

        if (this.inLove > 0) {
            --this.inLove;
            if (this.inLove % 10 == 0) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        }

        //fish

        this.prevRoll =  this.currentRoll;
        float targetRoll = Math.max(-0.45F, Math.min(0.45F, (this.getYRot() - this.yRotO) * 0.1F));
        targetRoll = -targetRoll;
        this.currentRoll += (targetRoll - this.currentRoll) * 0.05F;

        if (this.isAlive() && !this.getCanGrowUp()) {
            if (this.getAge() >- 500){
                int i = this.getAge();
                this.setAge(i-6000);
            }
        }
    }

    public boolean canSwim(){
        return true;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater() && this.canSwim()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, this.floatsUp() ? -0.005D : -0.010D, 0.0D));
            }

            this.calculateEntityAnimation(true);
        } else {
            super.travel(pTravelVector);
        }
    }

    @Nullable
    public abstract SoundEvent getFlopSound();

    public boolean canFlop(){
        return true;
    }

    public boolean floatsUp(){
        return true;
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f = Math.min(pPartialTick * 4.0F, 1.0F);
        this.walkAnimation.update(f, 0.4F);
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        int i = this.getAge();

        if (this.isBaby() && itemstack.is(MMTags.Items.FINTASTIC_BAD_FEED) && this.getCanGrowUp()){
            this.setCanGrowUp(false);

            this.setAge(-12000);

            for(int j = 0; j < 7; ++j) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }

            return InteractionResult.SUCCESS;
        }

        if (isFood(itemstack) || itemstack.is(MMTags.Items.FINTASTIC_ALL_FEEDS)){

            if (itemstack.is(MMTags.Items.FINTASTIC_FEED)){
                this.setFeedQuality(0);
            }else if (itemstack.is(MMTags.Items.FINTASTIC_QUALITY_FEED)){
                this.setFeedQuality(1);
            }else if (itemstack.is(MMTags.Items.FINTASTIC_GREAT_FEED)){
                this.setFeedQuality(2);
            }else if (itemstack.is(MMTags.Items.FINTASTIC_PREMIUM_FEED)){
                this.setFeedQuality(3);
            }else if ((!itemstack.is(MMTags.Items.FINTASTIC_ALL_FEEDS) || itemstack.is(MMTags.Items.FINTASTIC_BAD_FEED)) && this.getFeedQuality() != 0){
                this.setFeedQuality(0);
            }

            if (this.isBaby() && this.getCanGrowUp()){
                this.ageUp(getSpeedUpSecondsWhenFeedingFish(-i, this.getFeedQuality()), true, itemstack);
                this.usePlayerItem(pPlayer, pHand, itemstack);
                return InteractionResult.SUCCESS;
            }else if (this.isBaby()){
                if (itemstack.is(MMTags.Items.FINTASTIC_PREMIUM_FEED)){
                    this.setCanGrowUp(true);

                    for(int j = 0; j < 7; ++j) {
                        double d0 = this.random.nextGaussian() * 0.02D;
                        double d1 = this.random.nextGaussian() * 0.02D;
                        double d2 = this.random.nextGaussian() * 0.02D;
                        this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
                    }

                }else {
                    return InteractionResult.PASS;
                }
            }

            if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
                this.usePlayerItem(pPlayer, pHand, itemstack);
                this.setInLove(pPlayer);
                return InteractionResult.SUCCESS;
            }

            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }
        }

        return super.interactAt(pPlayer, pVec, pHand);
    }

    public static int getSpeedUpSecondsWhenFeedingFish(int pTicksUntilAdult, int multiplier) {
        return (int)((float)(pTicksUntilAdult / 20) * 0.1F * (multiplier+1));
    }

    public boolean isFood(ItemStack pStack) {
        return fintasticFoodIngredients().test(pStack) || foodIngredients().test(pStack);
    }

    public Ingredient foodIngredients(){
        return this.fintasticFoodIngredients();
    }

    public Ingredient fintasticFoodIngredients(){
        return Ingredient.of(MMTags.Items.FINTASTIC_ALL_FEEDS);
    }

    public static boolean checkSurfaceWaterCreatureRules(EntityType<? extends MarvelousWaterAnimal> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER) && CommonConfig.NATURAL_SPAWNS.get();
    }

    public void calculateEntityAnimation(boolean pIncludeHeight) {
        float f = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        this.updateWalkAnimation(f);
    }

}
