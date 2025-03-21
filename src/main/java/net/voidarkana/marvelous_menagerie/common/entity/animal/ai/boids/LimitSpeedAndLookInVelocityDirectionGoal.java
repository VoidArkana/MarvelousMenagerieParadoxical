package net.voidarkana.marvelous_menagerie.common.entity.animal.ai.boids;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class LimitSpeedAndLookInVelocityDirectionGoal extends Goal {
    private final Mob mob;
    private final float minSpeed;
    private final float maxSpeed;

    public LimitSpeedAndLookInVelocityDirectionGoal(Mob mob, float minSpeed, float maxSpeed) {
        this.mob = mob;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean canUse() {
        return this.mob.isInWater();
    }

    @Override
    public void tick() {
        var velocity = mob.getDeltaMovement();
        var speed = velocity.length();

        if (speed < minSpeed)
            velocity = velocity.normalize().scale(minSpeed/2);
        if (speed > maxSpeed)
            velocity = velocity.normalize().scale(maxSpeed/2);

        mob.setDeltaMovement(velocity);
        mob.lookAt(EntityAnchorArgument.Anchor.EYES, mob.position().add(velocity.x, velocity.y+0.25, velocity.z)); // Scale by 3 just to be sure it is roughly the right direction
    }
}