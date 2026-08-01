package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class StrollOrSprintAvoidingWaterGoal extends WaterAvoidingRandomStrollGoal {
    protected final float probability;
    protected final double sprintSpeed;

    public StrollOrSprintAvoidingWaterGoal(PathfinderMob pMob, double pWalkSpeedModifier, double pSprintSpeedModifier, float pChance) {
        super(pMob, pWalkSpeedModifier);
        this.probability = pChance;
        this.sprintSpeed = pSprintSpeedModifier;
    }

    public StrollOrSprintAvoidingWaterGoal(PathfinderMob pMob, double pWalkSpeedModifier, double pSprintSpeedModifier) {
        this(pMob, pWalkSpeedModifier, pSprintSpeedModifier, 0.5f);
    }

    @Override
    public void start() {
        if (this.mob.getRandom().nextFloat() >= this.probability)
            this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.sprintSpeed);
        else
            this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }
}
