package net.voidarkana.marvelous_menagerie.common.entity.ai.brains;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Myotragus;

import java.util.function.Predicate;

public class MyotragusAI {
    private static final UniformInt ADULT_FOLLOW_RANGE = UniformInt.of(5, 16);
    private static final float SPEED_MULTIPLIER_WHEN_MAKING_LOVE = 1.0F;
    private static final float SPEED_MULTIPLIER_WHEN_IDLING = 0.75F;
    private static final float SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT = 1.1F;
    private static final float SPEED_MULTIPLIER_WHEN_TEMPTED = 1.15F;
    private static final float SPEED_MULTIPLIER_WHEN_PANICKING = 1.75F;
    private static final float SPEED_MULTIPLIER_WHEN_PREPARING_TO_RAM = 1.15F;
    private static final UniformInt TIME_BETWEEN_LONG_JUMPS = UniformInt.of(800, 1600);
    public static final int MAX_LONG_JUMP_HEIGHT = 5;
    public static final int MAX_LONG_JUMP_WIDTH = 5;
    public static final float MAX_JUMP_VELOCITY = 1.5F;
    private static final UniformInt TIME_BETWEEN_RAMS = UniformInt.of(1000, 6000);
    private static final UniformInt TIME_BETWEEN_RAMS_SCREAMER = UniformInt.of(200, 600);
    private static final TargetingConditions RAM_TARGET_CONDITIONS = TargetingConditions.forCombat().selector((p_289449_) -> {
        return !p_289449_.getType().equals(MMEntities.MYOTRAGUS) && p_289449_.level().getWorldBorder().isWithinBounds(p_289449_.getBoundingBox());
    });
    private static final float SPEED_MULTIPLIER_WHEN_RAMMING = 2.0F;
    public static final int RAM_PREPARE_TIME = 20;
    public static final int RAM_MIN_DISTANCE = 4;
    public static final int RAM_MAX_DISTANCE = 7;
    public static final float ADULT_RAM_KNOCKBACK_FORCE = 2.5F;
    public static final float BABY_RAM_KNOCKBACK_FORCE = 1.0F;

    public static void initMemories(Myotragus pGoat, RandomSource pRandom) {
        pGoat.getBrain().setMemory(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, TIME_BETWEEN_LONG_JUMPS.sample(pRandom));
        pGoat.getBrain().setMemory(MemoryModuleType.RAM_COOLDOWN_TICKS, TIME_BETWEEN_RAMS.sample(pRandom));
    }

    public static Brain<?> makeBrain(Brain<Myotragus> pBrain) {
        initCoreActivity(pBrain);
        initIdleActivity(pBrain);
        initLongJumpActivity(pBrain);
        initRamActivity(pBrain);
        pBrain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        pBrain.setDefaultActivity(Activity.IDLE);
        pBrain.useDefaultActivity();
        return pBrain;
    }

    private static void initCoreActivity(Brain<Myotragus> pBrain) {
        pBrain.addActivity(Activity.CORE, 0,
                ImmutableList.of(new Swim(0.8F),
                        new MyotragusAI.MyotragusPanic(SPEED_MULTIPLIER_WHEN_PANICKING),
                        new LookAtTargetSink(45, 90),
                        new MoveToTargetSink(),
                        new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                        new CountDownCooldownTicks(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS),
                        new CountDownCooldownTicks(MemoryModuleType.RAM_COOLDOWN_TICKS)));
    }

    private static void initIdleActivity(Brain<Myotragus> pBrain) {
        pBrain.addActivityWithConditions(Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
                        Pair.of(1, new AnimalMakeLove(MMEntities.MYOTRAGUS.get(), SPEED_MULTIPLIER_WHEN_MAKING_LOVE)),
                        Pair.of(2, new FollowTemptation((entity) -> SPEED_MULTIPLIER_WHEN_TEMPTED)),
                        Pair.of(3, BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove),
                                BabyFollowAdult.create(ADULT_FOLLOW_RANGE, SPEED_MULTIPLIER_WHEN_FOLLOWING_ADULT))),
                        Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0F, 0.0F, 0.0F)),
                        Pair.of(5, new RunOne<>(
                                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                        ImmutableList.of(
                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), StrollTowardsShelterOrSky.stroll(SPEED_MULTIPLIER_WHEN_IDLING)), 2),
                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), RandomStroll.stroll(SPEED_MULTIPLIER_WHEN_IDLING)), 2),
                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), SetWalkTargetFromLookTarget.create(SPEED_MULTIPLIER_WHEN_IDLING, 3)), 2),
                                                Pair.of(new MyotragusAI.RandomSitting(20), 3),
                                                Pair.of(new DoNothing(30, 60), 1))
                                )
                        )
                ),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.RAM_TARGET, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryStatus.VALUE_ABSENT))

        );
    }

    private static void initLongJumpActivity(Brain<Myotragus> pBrain) {
        pBrain.addActivityWithConditions(Activity.LONG_JUMP,
                ImmutableList.of(
                        Pair.of(0, new LongJumpMidJump(TIME_BETWEEN_LONG_JUMPS, SoundEvents.GOAT_STEP)),
                        Pair.of(1, new LongJumpToRandomPos<>(TIME_BETWEEN_LONG_JUMPS, MAX_LONG_JUMP_HEIGHT, MAX_LONG_JUMP_WIDTH, MAX_JUMP_VELOCITY,
                                (p_149476_) -> p_149476_.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_LONG_JUMP : SoundEvents.GOAT_LONG_JUMP))),

                ImmutableSet.of(
                        Pair.of(MMMemoryModuleTypes.SITTING.get(), MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT)));
    }

    private static void initRamActivity(Brain<Myotragus> pBrain) {
        pBrain.addActivityWithConditions(Activity.RAM,
                ImmutableList.of(Pair.of(0, new RamTarget(
                            (goat) -> goat.isScreamingGoat() ? TIME_BETWEEN_RAMS_SCREAMER : TIME_BETWEEN_RAMS, RAM_TARGET_CONDITIONS, SPEED_MULTIPLIER_WHEN_RAMMING,
                            (goat) -> goat.isBaby() ? BABY_RAM_KNOCKBACK_FORCE : ADULT_RAM_KNOCKBACK_FORCE,
                            (goat) -> goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_RAM_IMPACT : SoundEvents.GOAT_RAM_IMPACT,
                            (goat) -> goat.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HORN_BREAK : SoundEvents.GOAT_HORN_BREAK)
                        ),
                        Pair.of(1, new PrepareRamNearestTarget<>(
                                (p_218770_) -> p_218770_.isScreamingGoat() ? TIME_BETWEEN_RAMS_SCREAMER.getMinValue() : TIME_BETWEEN_RAMS.getMinValue(),
                                RAM_MIN_DISTANCE, RAM_MAX_DISTANCE, SPEED_MULTIPLIER_WHEN_PREPARING_TO_RAM, RAM_TARGET_CONDITIONS, RAM_PREPARE_TIME,
                                (p_218768_) -> p_218768_.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_PREPARE_RAM : SoundEvents.GOAT_PREPARE_RAM))),

                ImmutableSet.of(
                        Pair.of(MMMemoryModuleTypes.SITTING.get(), MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.TEMPTING_PLAYER, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.RAM_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT)));
    }

//    private static void initRegulateActivity(Brain<Myotragus> pBrain) {
//        pBrain.addActivityWithConditions(Activity.IDLE,
//                ImmutableList.of(
//                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
//
//                        Pair.of(2, ),
//                        Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0F, 0.0F, 0.0F)),
//                        Pair.of(5, new RunOne<>(
//                                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
//                                        ImmutableList.of(
//                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), StrollTowardsShelterOrSky.stroll(SPEED_MULTIPLIER_WHEN_IDLING)), 2),
//                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), RandomStroll.stroll(SPEED_MULTIPLIER_WHEN_IDLING)), 2),
//                                                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(Myotragus::refuseToMove), SetWalkTargetFromLookTarget.create(SPEED_MULTIPLIER_WHEN_IDLING, 3)), 2),
//                                                Pair.of(new MyotragusAI.RandomSitting(20), 3),
//                                                Pair.of(new DoNothing(30, 60), 1))
//                                )
//                        )
//                ),
//                ImmutableSet.of(
//                        Pair.of(MemoryModuleType.RAM_TARGET, MemoryStatus.VALUE_ABSENT),
//                        Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryStatus.VALUE_ABSENT))
//
//        );
//    }
//
//    private static void initRegulateActivit(Brain<Myotragus> pBrain) {
//        pBrain.addActivityWithConditions(Activity.LAY_SPAWN,
//                ImmutableList.of(Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
//                        Pair.of(2, TryFindLandNearWater.create(8, 1.0F)),
//                        Pair.of(3, TryLaySpawnOnWaterNearLand.create(Blocks.FROGSPAWN)),
//                        Pair.of(4, new RunOne<>(ImmutableList.of(
//                                Pair.of(RandomStroll.stroll(1.0F), 2),
//                                Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 1),
//                                Pair.of(new Croak(), 2))))),
//                ImmutableSet.of(Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryStatus.VALUE_ABSENT),
//                        Pair.of(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_PRESENT)));
//    }

    public static void updateActivity(Myotragus pBrain) {
        pBrain.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.RAM, Activity.LONG_JUMP, Activity.IDLE));
    }

    public static class MyotragusPanic extends AnimalPanic {
        public MyotragusPanic(float p_249921_) {
            super(p_249921_);
        }

        protected void start(ServerLevel p_249530_, PathfinderMob p_248643_, long p_251124_) {
            if (p_248643_ instanceof Myotragus goat) {
                goat.standUpInstantly();
            }

            super.start(p_249530_, p_248643_, p_251124_);
        }
    }

    public static class RandomSitting extends Behavior<Myotragus> {
        private final int minimalPoseTicks;

        public RandomSitting(int pMinimalPoseSeconds) {
            super(ImmutableMap.of());
            this.minimalPoseTicks = pMinimalPoseSeconds * 20;
        }

        protected boolean checkExtraStartConditions(ServerLevel pLevel, Myotragus pOwner) {
            return !pOwner.isEyeInFluidType(Fluids.WATER.getFluidType()) && pOwner.getPoseTime() >= (long)this.minimalPoseTicks && !pOwner.isLeashed()
                    && pOwner.onGround() && !pOwner.hasControllingPassenger();
        }

        protected void start(ServerLevel pLevel, Myotragus pEntity, long pGameTime) {
            if (pEntity.isSitting()) {
                pEntity.standUp();
                if (pEntity.getBrain().hasMemoryValue(MMMemoryModuleTypes.SITTING.get()))
                    pEntity.getBrain().eraseMemory(MMMemoryModuleTypes.SITTING.get());
            } else if (!pEntity.isPanicking()) {
                pEntity.sitDown();
                pEntity.getBrain().setMemory(MMMemoryModuleTypes.SITTING.get(), false);
            }
        }
    }

    public static class RandomStandUp extends Behavior<Myotragus> {
        private final int minimalPoseTicks;

        public RandomStandUp(int pMinimalPoseSeconds) {
            super(ImmutableMap.of());
            this.minimalPoseTicks = pMinimalPoseSeconds * 20;
        }

        protected boolean checkExtraStartConditions(ServerLevel pLevel, Myotragus pOwner) {
            return !pOwner.isEyeInFluidType(Fluids.WATER.getFluidType()) && pOwner.getPoseTime() >= (long)this.minimalPoseTicks && !pOwner.isLeashed()
                    && pOwner.onGround() && !pOwner.hasControllingPassenger() && pOwner.isSitting();
        }

        protected void start(ServerLevel pLevel, Myotragus pEntity, long pGameTime) {
            pEntity.standUp();
            if (pEntity.getBrain().hasMemoryValue(MMMemoryModuleTypes.SITTING.get()))
                pEntity.getBrain().eraseMemory(MMMemoryModuleTypes.SITTING.get());
        }
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(Items.WHEAT);
    }
}
