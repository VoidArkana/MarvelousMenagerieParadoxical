package net.voidarkana.marvelous_menagerie.common.entity.ai;

import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

public class MarvelousAvoidWaterWanderGoal extends WaterAvoidingRandomStrollGoal {
    final MarvelousAnimal animal;

    public MarvelousAvoidWaterWanderGoal(MarvelousAnimal pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        this.animal = pMob;
    }

    @Override
    public boolean canUse() {
        if (this.animal.canSit() && (this.animal.isSitting() || this.animal.isInPoseTransition())){
            return false;
        }
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.animal.canSit() && (this.animal.isSitting() || this.animal.isInPoseTransition())){
            return false;
        }
        return super.canContinueToUse();
    }
}
