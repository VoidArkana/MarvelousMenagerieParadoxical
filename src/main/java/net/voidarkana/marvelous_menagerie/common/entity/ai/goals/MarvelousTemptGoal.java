package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

public class MarvelousTemptGoal extends TemptGoal {
    final PathfinderMob animal;

    public MarvelousTemptGoal(PathfinderMob pMob, double pSpeedModifier, Ingredient pItems, boolean pCanScare) {
        super(pMob, pSpeedModifier, pItems, pCanScare);
        this.animal = pMob;
    }

    @Override
    public void start() {
        if (this.animal instanceof ISittingAnimal mob){
            if (mob.isSitting())
                mob.standUp();
        }
        super.start();
    }
}
