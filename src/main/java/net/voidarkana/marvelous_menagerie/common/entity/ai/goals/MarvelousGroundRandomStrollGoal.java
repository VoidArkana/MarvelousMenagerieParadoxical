package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;

public class MarvelousGroundRandomStrollGoal extends WaterAvoidingRandomStrollGoal {
    final PathfinderMob mob;
    public MarvelousGroundRandomStrollGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        this.mob = pMob;
    }

    @Override
    public boolean canUse() {
        if (mob instanceof ISittingAnimal animal){
            return super.canUse() && !animal.isSitting();
        }
        return super.canUse();
    }
}
