package net.voidarkana.marvelous_menagerie.common.entity.animal.base;

public interface IAnimatedAttacker {

    boolean isAttacking();

    void setAttacking(boolean attacking);


    int attackAnimationTimeout();

    void setAttackAnimationTimeout(int attackAnimationTimeout);

}