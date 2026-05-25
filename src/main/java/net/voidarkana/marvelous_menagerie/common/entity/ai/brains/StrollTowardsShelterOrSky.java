package net.voidarkana.marvelous_menagerie.common.entity.ai.brains;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class StrollTowardsShelterOrSky {

    public static OneShot<PathfinderMob> stroll(float pSpeedModifier) {
        return stroll(pSpeedModifier, true);
    }

    public static OneShot<PathfinderMob> stroll(float pSpeedModifier, boolean pMayStrollFromWater) {
        return strollFlyOrSwim(pSpeedModifier, (mob) -> {
            return findNearestBlock(mob, 10, 7);
        }, pMayStrollFromWater ? (p_258615_) -> {
            return true;
        } : (p_289370_) -> {
            return !p_289370_.isInWaterOrBubble();
        });
    }

    public static BehaviorControl<PathfinderMob> stroll(float pSpeedModifier, int pMaxHorizontalDistance, int pMaxVerticalDistance) {
        return strollFlyOrSwim(pSpeedModifier,
                (mob) -> findNearestBlock(mob, pMaxHorizontalDistance, pMaxVerticalDistance),
                (mob) -> {
            if (mob instanceof ISittingAnimal animal){
                return !animal.refuseToMove();
            }
            return true;
        });
    }

    private static OneShot<PathfinderMob> strollFlyOrSwim(float pSpeedModifier, Function<PathfinderMob, Vec3> pTarget, Predicate<PathfinderMob> pCanStroll) {
        return BehaviorBuilder.create((mobInstance) -> {
            return mobInstance.group(mobInstance.absent(MemoryModuleType.WALK_TARGET)).apply(mobInstance, (targetMemoryAccessor) -> {
                return (level, mob, l) -> {
                    if (!pCanStroll.test(mob)) {
                        return false;
                    } else {
                        Optional<Vec3> optional = Optional.ofNullable(pTarget.apply(mob));
                        targetMemoryAccessor.setOrErase(optional.map((p_258622_) -> {
                            return new WalkTarget(p_258622_, pSpeedModifier, 0);
                        }));
                        return true;
                    }
                };
            });
        });
    }

    @Nullable
    public static Vec3 findNearestBlock(PathfinderMob pMob, int pMaxDistance, int pYRange) {
        int maxDistance = pMaxDistance;
        int yRange = pYRange;
        BlockPos mobBlockPos = pMob.blockPosition();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(int y = -pYRange; y <= yRange; y = y > 0 ? -y : 1 - y) {
            for(int radius = 0; radius < maxDistance; ++radius) {
                for(int xOffset = 0; xOffset <= radius; xOffset = xOffset > 0 ? -xOffset : 1 - xOffset) {
                    for(int zOffset = xOffset < radius && xOffset > -radius ? radius : 0; zOffset <= radius; zOffset = zOffset > 0 ? -zOffset : 1 - zOffset) {
                        mutableBlockPos.setWithOffset(mobBlockPos, xOffset, y - 1, zOffset);
                        if (pMob.isWithinRestriction(mutableBlockPos) && isValidTarget(mobBlockPos, pMob.level(), mutableBlockPos)) {
                            return Vec3.atBottomCenterOf(mutableBlockPos);
                        }
                    }
                }
            }
        }

        return null;
    }

    public static boolean isValidTarget(BlockPos originBlockpos, LevelReader level, BlockPos targetBlockpos){
        if (level.canSeeSky(originBlockpos))
            return !level.canSeeSky(targetBlockpos) && level.getBlockState(targetBlockpos.above()).getFluidState().is(Fluids.EMPTY);
        else
            return level.canSeeSky(targetBlockpos) && level.getBlockState(targetBlockpos.above()).getFluidState().is(Fluids.EMPTY);
    }
}
