package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tiktaalik;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;

public class RandomlySitUpOrDownGoal extends Goal {
    private final PathfinderMob mob;
    private int nextMove;
    final int sittingUpInterval;
    final int sittingDownInterval;

    public RandomlySitUpOrDownGoal(PathfinderMob mob, int interval) {
        this(mob, interval, interval);
    }

    public RandomlySitUpOrDownGoal(PathfinderMob mob, int gettingUpInterval, int sittingDownInterval) {
        this.mob = mob;
        this.sittingUpInterval = gettingUpInterval;
        this.sittingDownInterval = sittingDownInterval;
        this.resetInterval();
    }

    public void start() {
        if (this.mob instanceof ISittingAnimal animal){
            if (animal.isSitting())
                animal.standUp();
            else
                animal.sitDown();
        }
    }

    public boolean canContinueToUse() {
        return false;
    }

    public boolean canUse() {
        if (this.mob instanceof ISittingAnimal animal){

            if (animal.isInPoseTransition() || !animal.canSit()){
                return false;
            }

            if (this.mob instanceof IEggLayer eggLayer){
                if (eggLayer.isPregnant()){
                    return animal.isSitting();
                }
            }

            if (this.mob instanceof TamableMarvelousAnimal tamable){
                if (tamable.isTame() && tamable.getCommand() != 0){
                    return false;
                }
            }

            this.nextMove--;


            if (this.nextMove > 0){
                if (this.mob.getRandom().nextInt(0, this.nextMove) == 0 ) {
                    this.resetInterval();
                    return !animal.hasToStandUpInstantly();
                }else {
                    return false;
                }
            }else {
                if (animal.hasToStandUpInstantly()){
                    return false;
                }else {
                    this.resetInterval();
                    return true;
                }
            }
        }
        return false;
    }

    private void resetInterval() {
        if (this.mob instanceof ISittingAnimal animal){
            if (animal.canSit()){
                if (this.mob.level().isNight()){
                    this.nextMove = animal.isSitting() ? sittingUpInterval*2 : sittingDownInterval/2;
                }else {
                    this.nextMove = animal.isSitting() ? sittingUpInterval : sittingDownInterval;
                }
            }
            else{
                this.nextMove = 0;
            }
        }else {
            this.nextMove = 0;
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }
}

