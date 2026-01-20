package net.voidarkana.marvelous_menagerie.common.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;

import java.util.EnumSet;

public class MarvelousSitWhenOrderedToGoal extends Goal {
    private final TamableMarvelousAnimal mob;

    public MarvelousSitWhenOrderedToGoal(TamableMarvelousAnimal pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    public boolean canContinueToUse() {
        return this.mob.isOrderedToSit();
    }

    public boolean canUse() {
        if (!this.mob.isTame()) {
            return false;
        } else if (this.mob.isInWaterOrBubble()) {
            return false;
        } else if (!this.mob.onGround()) {
            return false;
        } else {
            LivingEntity livingentity = this.mob.getOwner();
            if (livingentity == null) {
                return true;
            } else {
                return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.isOrderedToSit();
            }
        }
    }

    public void start() {
        this.mob.getNavigation().stop();
        this.mob.setInSittingPose(true);
    }

    public void stop() {
        this.mob.setInSittingPose(false);
    }
}
