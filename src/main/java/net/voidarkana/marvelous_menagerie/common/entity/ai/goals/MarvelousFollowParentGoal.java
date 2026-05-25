package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

public class MarvelousFollowParentGoal extends FollowParentGoal {
    final MarvelousAnimal animal;

    public MarvelousFollowParentGoal(MarvelousAnimal pAnimal, double pSpeedModifier) {
        super(pAnimal, pSpeedModifier);
        this.animal = pAnimal;
    }

    @Override
    public boolean canUse() {
        if (this.animal.canSit() && this.animal.isSitting()){
            return false;
        }
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.animal.canSit() && this.animal.isSitting()){
            return false;
        }
        return super.canContinueToUse();
    }
}
