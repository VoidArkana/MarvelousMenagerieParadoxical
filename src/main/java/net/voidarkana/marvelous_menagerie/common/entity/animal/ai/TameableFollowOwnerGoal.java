package net.voidarkana.marvelous_menagerie.common.entity.animal.ai;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.ICustomFollower;

//taken from the old UP
public class TameableFollowOwnerGoal extends FollowOwnerGoal {

    private ICustomFollower follower;

    public TameableFollowOwnerGoal(TamableAnimal tameable, double speed, float minDist, float maxDist, boolean teleportToLeaves) {
        super(tameable, speed, minDist, maxDist, teleportToLeaves);
        this.follower = (ICustomFollower)tameable;
    }

    public boolean canUse() {
        return super.canUse() && this.follower.shouldFollow();
    }
}
