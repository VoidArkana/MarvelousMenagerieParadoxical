package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.TamableMarvelousAnimal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class MarvelousNonTameRandomTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final TamableMarvelousAnimal tamableMob;

    public MarvelousNonTameRandomTargetGoal(TamableMarvelousAnimal pTamableMob, Class<T> pTargetType, boolean pMustSee, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(pTamableMob, pTargetType, 10, pMustSee, false, pTargetPredicate);
        this.tamableMob = pTamableMob;
    }

    public boolean canUse() {
        return !this.tamableMob.isTame() && super.canUse();
    }

    public boolean canContinueToUse() {
        return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
    }
}
