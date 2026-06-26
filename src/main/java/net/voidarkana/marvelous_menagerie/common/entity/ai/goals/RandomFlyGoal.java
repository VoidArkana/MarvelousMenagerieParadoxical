package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractFlyingAnimal;

import java.util.EnumSet;
import java.util.Random;

public class RandomFlyGoal extends Goal {

    private AbstractFlyingAnimal entity;
    private double x;
    private double y;
    private double z;
    private float speedModifier;

    private int hangCheckIn = 0;

    public RandomFlyGoal(AbstractFlyingAnimal entity) {
        this(entity, 1F);
    }

    public RandomFlyGoal(AbstractFlyingAnimal entity, float pSpeedModifier) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.entity = entity;
        this.speedModifier = pSpeedModifier;
    }

    @Override
    public boolean canUse() {
        if (entity.isVehicle() || (entity.getTarget() != null && entity.getTarget().isAlive()) || entity.isPassenger()) {
            return false;
        }
        if (entity.groundedFor > 0) {
            return false;
        }
        if (!entity.isFlying() && entity.getRandom().nextInt(70) != 0) {
            return false;
        }
        Vec3 target = this.getPosition();
        if (target == null) {
            return false;
        } else {
            this.x = target.x;
            this.y = target.y;
            this.z = target.z;
            return true;
        }
    }

    protected Vec3 getPosition() {
        return findFlightPos();
    }

    public void start() {
        this.entity.setFlying(true);
        hangCheckIn = 0;
        entity.getNavigation().moveTo(this.x, this.y, this.z, speedModifier);
    }

    public void tick() {
        if (entity.isFlying() && entity.onGround() && entity.timeFlying > 40) {
            entity.setFlying(false);
        }
    }

    public boolean canContinueToUse() {
        return entity.isFlying() && !entity.getNavigation().isDone() && entity.groundedFor <= 0;
    }

    public void stop() {
    }

    protected Vec3 findFlightPos() {
        int range = 13;

        Vec3 heightAdjusted = entity.position().add(entity.getRandom().nextInt(range * 2) - range, 0, entity.getRandom().nextInt(range * 2) - range);
        if (entity.level().canSeeSky(BlockPos.containing(heightAdjusted))) {
            Vec3 ground = groundPosition(heightAdjusted);
            heightAdjusted = new Vec3(heightAdjusted.x, ground.y + 4 + entity.getRandom().nextInt(3), heightAdjusted.z);
        } else {
            Vec3 ground = groundPosition(heightAdjusted);
            BlockPos ceiling = BlockPos.containing(ground).above(2);
            while (ceiling.getY() < entity.level().getMaxBuildHeight() && !entity.level().getBlockState(ceiling).isSolid()) {
                ceiling = ceiling.above();
            }
            float randCeilVal = 0.3F + entity.getRandom().nextFloat() * 0.5F;
            heightAdjusted = new Vec3(heightAdjusted.x, ground.y + (ceiling.getY() - ground.y) * randCeilVal, heightAdjusted.z);
        }

        BlockHitResult result = entity.level().clip(new ClipContext(entity.getEyePosition(), heightAdjusted, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        if (result.getType() == HitResult.Type.MISS) {
            return heightAdjusted;
        } else {
            return result.getLocation();
        }
    }

    private boolean isOverWaterOrVoid() {
        BlockPos position = entity.blockPosition();
        while (position.getY() > entity.level().getMinBuildHeight() && entity.level().isEmptyBlock(position)) {
            position = position.below();
        }
        return !entity.level().getFluidState(position).isEmpty() || entity.level().getBlockState(position).is(Blocks.VINE) || position.getY() <= entity.level().getMinBuildHeight();
    }

    public Vec3 groundPosition(Vec3 airPosition) {
        BlockPos.MutableBlockPos ground = new BlockPos.MutableBlockPos();
        ground.set(airPosition.x, airPosition.y, airPosition.z);
        boolean flag = false;
        while (ground.getY() < entity.level().getMaxBuildHeight() && !entity.level().getBlockState(ground).isSolid() && entity.level().getFluidState(ground).isEmpty()){
            ground.move(0, 1, 0);
            flag = true;
        }
        ground.move(0, -1, 0);
        while (ground.getY() > entity.level().getMinBuildHeight() && !entity.level().getBlockState(ground).isSolid() && entity.level().getFluidState(ground).isEmpty()) {
            ground.move(0, -1, 0);
        }
        return Vec3.atCenterOf(flag ? ground.above() : ground.below());
    }
}
