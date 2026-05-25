package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.crafting.Ingredient;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

public class MarvelousTemptGoal extends TemptGoal {
    final MarvelousAnimal animal;

    public MarvelousTemptGoal(MarvelousAnimal pMob, double pSpeedModifier, Ingredient pItems, boolean pCanScare) {
        super(pMob, pSpeedModifier, pItems, pCanScare);
        this.animal = pMob;
    }

    @Override
    public void start() {
        super.start();
        if (this.animal.canSit()){
            if (this.animal.isSitting())
                this.animal.standUp();
        }
    }
}
