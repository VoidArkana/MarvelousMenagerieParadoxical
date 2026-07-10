package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class StoicPanicGoal extends PanicGoal {

    final PathfinderMob mob;

    public StoicPanicGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        this.mob = pMob;
    }

    @Override
    public boolean canUse() {
        if (mob.isBaby())
            return super.canUse();
        return this.mob.isFreezing() || this.mob.isOnFire();
    }
}
