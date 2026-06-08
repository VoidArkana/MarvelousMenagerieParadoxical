package net.voidarkana.marvelous_menagerie.common.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;

public class MarvelousSwimmingMoveControl extends SmoothSwimmingMoveControl {
    final Mob entity;
    public MarvelousSwimmingMoveControl(Mob pMob, int pMaxTurnX, int pMaxTurnY, float pInWaterSpeedModifier, float pOutsideWaterSpeedModifier, boolean pApplyGravity) {
        super(pMob, pMaxTurnX, pMaxTurnY, pInWaterSpeedModifier, pOutsideWaterSpeedModifier, pApplyGravity);
        this.entity = pMob;
    }

    @Override
    public void tick() {
        if (this.entity instanceof PathfinderMob mob && this.entity instanceof ISittingAnimal pAnimal){
            if (!pAnimal.refuseToMove()) {
                super.tick();
            }
//            else if (pAnimal.refuseToMove() && this.operation == MoveControl.Operation.MOVE_TO && !mob.isLeashed() && pAnimal.isSitting() && !pAnimal.isInPoseTransition()) {
//                pAnimal.standUp();
//            }
        }else {
            super.tick();
        }
    }
}
