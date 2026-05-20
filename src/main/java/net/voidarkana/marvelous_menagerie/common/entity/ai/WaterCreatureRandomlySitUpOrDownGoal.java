package net.voidarkana.marvelous_menagerie.common.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.voidarkana.marvelous_menagerie.common.entity.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;

public class WaterCreatureRandomlySitUpOrDownGoal extends Goal {
    private final BreedableWaterAnimal mob;
    private int nextMove;
    final int sittingUpInterval;
    final int sittingDownInterval;

    public WaterCreatureRandomlySitUpOrDownGoal(BreedableWaterAnimal mob, int interval) {
        this(mob, interval, interval);
    }

    public WaterCreatureRandomlySitUpOrDownGoal(BreedableWaterAnimal mob, int gettingUpInterval, int sittingDownInterval) {
        this.mob = mob;
        this.sittingUpInterval = gettingUpInterval;
        this.sittingDownInterval = sittingDownInterval;
        this.resetInterval();
    }

    public void start() {
        if (this.mob.isSitting())
            this.mob.standUp();
        else
            this.mob.sitDown();
    }

    public boolean canContinueToUse() {
        return false;
    }

    public boolean canUse() {
        if (!this.mob.canSit()){
            return false;
        }else if (this.mob.isInPoseTransition()){
            return false;
        }

        if (this.mob instanceof IEggLayer eggLayer){
            if (eggLayer.isPregnant()){
                return false;
            }
        }

//        if (this.mob instanceof TamableMarvelousAnimal tamable){
//            if (tamable.isTame() && tamable.getCommand() != 0){
//                return false;
//            }
//        }

        --this.nextMove;

        if (this.nextMove > 0){
            if (this.mob.level().isNight()){
                int chance;
                if (this.mob.isSitting()){
                    chance = this.mob.getRandom().nextInt(0, this.nextMove *2);
                }else{
                    chance = this.mob.getRandom().nextInt(0, this.nextMove /2);
                }

                if (chance == 0){
                    this.resetInterval();
                    if (this.mob.isSitting())
                        return true;
                    else
                        return this.mob.onGround() && !this.mob.isImmobile();
                }

            }else if (this.mob.getRandom().nextInt(0, this.nextMove) == 0 ) {
                this.resetInterval();
                if (this.mob.isSitting())
                    return this.mob.onGround();
                else
                    return this.mob.onGround() && !this.mob.isImmobile();
            }
        }
        return false;
    }

    private void resetInterval() {
        if (this.mob.canSit())
            this.nextMove = this.mob.isSitting() ? sittingUpInterval : sittingDownInterval;
        else
            this.nextMove = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }
}

