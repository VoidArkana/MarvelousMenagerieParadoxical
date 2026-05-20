package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

public interface IAnimatedAttacker {

    boolean isAttacking();

    void setAttacking(boolean attacking);

    int attackAnimationTimeout();

    void setAttackAnimationTimeout(int attackAnimationTimeout);

    @Nullable
    SoundEvent getAttackSound();
}