package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

public interface IAnimatedEater {

    int getEatingTicks();

    void setEatingTicks(int eating);

    boolean isEating();

    int eatAnimationTimeout();

    void setEatAnimationTimeout(int eatAnimationTimeout);

    @Nullable
    SoundEvent getEatSound();
}