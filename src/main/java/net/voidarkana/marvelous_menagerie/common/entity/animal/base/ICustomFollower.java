package net.voidarkana.marvelous_menagerie.common.entity.animal.base;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;

public interface ICustomFollower {
    boolean shouldFollow();

    default void followEntity(TamableAnimal tameable, LivingEntity owner, double followSpeed) {
        tameable.navigation.moveTo(owner, followSpeed);
    }
}
