package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedEater;

import java.util.EnumSet;
import java.util.function.Predicate;

public class MarvelousEatBlockGoal extends Goal {
    public static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.GRASS);
    public static final int EAT_ANIMATION_TICKS = 40;
    /** The entity owner of this AITask */
    public final Mob mob;
    /** The world the grass eater entity is eating from */
    public final Level level;
    /** Number of ticks since the entity started to eat grass */
    public int eatAnimationTick;

    public final int tickDuration;
    public final int tickDelay;
    public final int totalDuration;
    public final int chance;

    public MarvelousEatBlockGoal(Mob pMob) {
        this(pMob, 20, 20, 1000);
    }

    public MarvelousEatBlockGoal(Mob pMob, int pTickDuration, int pTickDelay, int chance) {
        this.mob = pMob;
        this.level = pMob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        this.tickDuration = pTickDuration;
        this.tickDelay = pTickDelay;
        this.totalDuration = this.tickDuration+this.tickDelay;
        this.chance = chance;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.mob instanceof IAnimatedEater eater){
            if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : chance) != 0) {
                return false;
            } else {
                if (eater.isEating()){
                    return false;
                }
                BlockPos blockpos = this.mob.blockPosition();
                BlockState state = this.level.getBlockState(blockpos);
                BlockState groundState = this.level.getBlockState(blockpos.below());
                if (this.canDestroyBlock(state)) {
                    return true;
                } else {
                    return this.isEdibleGroundBlock(groundState);
                }
            }
        }else {
            return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.eatAnimationTick = 0;
        this.level.broadcastEntityEvent(this.mob, (byte)10);
        this.mob.getNavigation().stop();
        if (this.mob instanceof IAnimatedEater eater){
            eater.setEatingTicks(this.totalDuration);
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.eatAnimationTick = this.totalDuration;

        if (this.mob instanceof IAnimatedEater eater){
            eater.setEatingTicks(0);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.eatAnimationTick < this.totalDuration && this.mob instanceof IAnimatedEater;
    }

    /**
     * Number of ticks since the entity started to eat grass
     */
    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.eatAnimationTick = Math.min(this.totalDuration, this.eatAnimationTick + 1);
        if (this.eatAnimationTick == this.adjustedTickDelay(this.tickDelay)) {
            BlockPos blockpos = this.mob.blockPosition();
            if (this.canDestroyBlock(this.level.getBlockState(blockpos))) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mob)) {
                    this.level.destroyBlock(blockpos, false);
                }

                this.mob.ate();
            } else {
                BlockPos blockpos1 = blockpos.below();
                BlockState blockState = this.level.getBlockState(blockpos1);
                if (isEdibleGroundBlock(blockState)) {
                    if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mob)) {
                        this.level.levelEvent(2001, blockpos1, Block.getId(blockState));
                        Block block = this.getLeftoverBlock(blockState);
                        this.level.setBlock(blockpos1, block.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }
        }
    }

    public boolean canDestroyBlock(BlockState state){
        return IS_TALL_GRASS.test(state);
    }

    public boolean isEdibleGroundBlock(BlockState state){
        return state.is(Blocks.GRASS_BLOCK);
    }

    public Block getLeftoverBlock(BlockState state){
        return Blocks.DIRT;
    }

}
