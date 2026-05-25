package net.voidarkana.marvelous_menagerie.common.entity.base;

public interface ISittingAnimal {

    boolean canSit();

    boolean refuseToMove();

    boolean isSitting();

    boolean isVisuallySitting();

    default int getSitDuration(){
        return 0;
    }

    default int getStandDuration(){
        return 0;
    }

    boolean isInPoseTransition();

    boolean isVisuallySittingDown();

    void sitDown();

    void standUp();

    void standUpInstantly();

    boolean hasToStandUpInstantly();

    void resetLastPoseChangeTick(long pLastPoseChangeTick);

    void resetLastPoseChangeTickToFullStand(long pLastPoseChangedTick);

    long getPoseTime();
}
