package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;

public class RandomlySitUpOrDownGoal extends Goal {
    private final PathfinderMob mob;
    private int nextMove;
    final int standingUpInterval;
    final int sittingDownInterval;

    public RandomlySitUpOrDownGoal(PathfinderMob mob, int interval) {
        this(mob, interval, interval);
    }

    public RandomlySitUpOrDownGoal(PathfinderMob mob, int gettingUpInterval, int sittingDownInterval) {
        this.mob = mob;
        this.standingUpInterval = gettingUpInterval;
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

            if (animal.isSitting() && mob.isVehicle())
                return true;

            if (this.mob.getTarget() != null)
                return animal.isSitting();

            if (animal.isInPoseTransition() || !animal.canSit() || mob.isVehicle()){
                return false;
            }

            if (animal.hasToStandUpInstantly()){
                return false;
            }

            if (this.mob instanceof IEggLayer eggLayer && eggLayer.isPregnant()){
                return false;
            }

            if (this.mob instanceof TamableMarvelousAnimal tamable){
                if (tamable.isTame() && !tamable.isWandering()){
                    return false;
                }
            }

            if (this.nextMove > 0){
                this.nextMove--;
                if (this.mob.getRandom().nextInt(0, this.nextMove) == 0 ) {
                    this.resetInterval();
                     return true;
                }else {
                    return false;
                }
            }else {
                this.resetInterval();
                return true;
            }
        }
        return false;
    }

    private void resetInterval() {
        if (this.mob instanceof ISittingAnimal animal){
            if (this.mob.level().isNight()){
                this.nextMove = animal.isSitting() ? standingUpInterval *2 : sittingDownInterval/2;
            }else {
                this.nextMove = animal.isSitting() ? standingUpInterval : sittingDownInterval;
            }
        }else {
            this.nextMove = 0;
        }
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }
}

