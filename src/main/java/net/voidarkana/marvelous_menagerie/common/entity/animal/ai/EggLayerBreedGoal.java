package net.voidarkana.marvelous_menagerie.common.entity.animal.ai;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.level.GameRules;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.MarvelousAnimal;

public class EggLayerBreedGoal extends BreedGoal {
    private final MarvelousAnimal animal;

    public EggLayerBreedGoal(MarvelousAnimal pAnimal, double pSpeedModifier) {
        super(pAnimal, pSpeedModifier);
        this.animal = pAnimal;
    }

    public boolean canUse() {
        if (this.animal instanceof IEggLayer eggLayer)
            return super.canUse() && !eggLayer.isPregnant();

        return false;
    }

    protected void breed() {
        ServerPlayer serverplayer = this.animal.getLoveCause();
        if (serverplayer == null && this.partner.getLoveCause() != null) {
            serverplayer = this.partner.getLoveCause();
        }

        if (serverplayer != null) {
            serverplayer.awardStat(Stats.ANIMALS_BRED);
            CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, (AgeableMob)null);
        }

        if (this.animal instanceof IEggLayer eggLayer)
            eggLayer.setPregnant(true);

        this.animal.setAge(6000);
        this.partner.setAge(6000);
        this.animal.resetLove();
        this.partner.resetLove();
        RandomSource randomsource = this.animal.getRandom();
        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomsource.nextInt(7) + 1));
        }
    }
}
