package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.base.FlyingAttachableAnimal;

import java.util.Random;

public class FlyAndAttachGoal extends RandomFlyGoal{
    private final FlyingAttachableAnimal entity;
    private boolean wantsToHang = false;
    private int hangCheckIn = 0;
    Direction direction;

    public FlyAndAttachGoal(FlyingAttachableAnimal entity, float pSpeedModifier) {
        super(entity, pSpeedModifier);
        this.entity = entity;
    }

    public FlyAndAttachGoal(FlyingAttachableAnimal entity) {
        this(entity, 1);
    }

    @Override
    public boolean canUse() {

        if (entity.isAttached()) {
            return false;
        }
        wantsToHang = entity.wantsToAttach();

        return super.canUse();
    }

    public boolean canContinueToUse() {
        if (entity.wantsToAttach()) {
            return !entity.getNavigation().isDone() && !entity.isAttached();
        } else {
            return super.canContinueToUse();
        }
    }

    @Override
    public void stop() {
        if (wantsToHang) {
            this.entity.getNavigation().stop();
        }
        wantsToHang = false;
    }

    @Override
    protected Vec3 getPosition() {
        if (wantsToHang) {
            Vec3 hangPos = findHangFromPos();
            if (hangPos != null) {
                return hangPos;
            }
        }
        return super.getPosition();
    }

    @Override
    public void start() {
        hangCheckIn = 0;
        super.start();
    }

    @Override
    public void tick() {

        direction = entity.getDirection();

        boolean flag = this.entity.tickCount % 20 == 0 ? (entity.horizontalCollision && entity.canAttachTo(entity.blockPosition().offset(direction.getNormal()),
                entity.level().getBlockState(entity.blockPosition().offset(direction.getNormal())), direction.getOpposite())) : false;

        if (wantsToHang || flag) {
            if (hangCheckIn-- < 0 || flag) {
                hangCheckIn = 5 + entity.getRandom().nextInt(5);
                if (!entity.isAttached() && entity.canAttachTo(entity.blockPosition().offset(direction.getNormal()),
                        entity.level().getBlockState(entity.blockPosition().offset(direction.getNormal())), direction.getOpposite())) {
                    entity.setAttachedDirection(direction);
                    entity.setFlying(false);
                }
            }
        }else
            super.tick();

    }

    public boolean hasLineOfToPos(BlockPos in) {
        HitResult raytraceresult = entity.level().clip(new ClipContext(entity.getEyePosition(1.0F), new Vec3(in.getX() + 0.5, in.getY() + 0.5, in.getZ() + 0.5), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        if (raytraceresult instanceof BlockHitResult) {
            BlockHitResult blockRayTraceResult = (BlockHitResult) raytraceresult;
            BlockPos pos = blockRayTraceResult.getBlockPos();
            return pos.equals(in) || entity.level().isEmptyBlock(pos);
        }
        return true;
    }

    public Vec3 findHangFromPos() {
        BlockPos blockpos = null;
        Random random = new Random();
        int range = 14;
        for (int i = 0; i < 15; i++) {
            BlockPos blockpos1 = this.entity.blockPosition().offset(
                    random.nextInt(range) - range / 2,
                    random.nextInt(range) - range / 4,
                    random.nextInt(range) - range / 2);
            if (!this.entity.level().isEmptyBlock(blockpos1) || !this.entity.level().isLoaded(blockpos1)) {
                continue;
            }
            int j = 0;
            while (this.entity.level().isEmptyBlock(blockpos1) && blockpos1.getY() < this.entity.level().getMaxBuildHeight() && j < 10) {
                blockpos1 = blockpos1.offset(this.entity.getDirection().getNormal());
                j++;
            }
            if (entity.canAttachTo(blockpos1, entity.level().getBlockState(blockpos1), this.entity.getDirection().getOpposite()) && hasLineOfToPos(blockpos1)) {
                blockpos = blockpos1;
            }
        }
        return blockpos == null ? null : Vec3.atCenterOf(blockpos);
    }

}
