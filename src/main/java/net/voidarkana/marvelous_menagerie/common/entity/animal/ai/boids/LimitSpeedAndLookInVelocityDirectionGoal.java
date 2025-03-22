package net.voidarkana.marvelous_menagerie.common.entity.animal.ai.boids;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;

public class LimitSpeedAndLookInVelocityDirectionGoal extends Goal {
    private final Arandaspis mob;
    private final float speed;
    //private final float maxSpeed;

    public LimitSpeedAndLookInVelocityDirectionGoal(Arandaspis mob, float speed) {
        this.mob = mob;
        this.speed = speed;
        //this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWaterOrBubble() && (mob.isFollower() || mob.hasFollowers());
    }

    @Override
    public void tick() {
        //var speed = velocity.length();
        var velocity = mob.getDeltaMovement().normalize().scale(0.2).scale(speed);

//        if (speed < minSpeed)
//            velocity = velocity.normalize().scale(minSpeed/2);
//        if (speed > maxSpeed)
//            velocity = velocity.normalize().scale(maxSpeed/2);

        mob.setDeltaMovement(velocity);
        mob.lookAt(EntityAnchorArgument.Anchor.EYES, mob.position().add(velocity.x, velocity.y+0.25, velocity.z)); // Scale by 3 just to be sure it is roughly the right direction
    }

    @Override
    public void stop() {
        mob.lookAt(EntityAnchorArgument.Anchor.EYES, mob.position());
    }
}