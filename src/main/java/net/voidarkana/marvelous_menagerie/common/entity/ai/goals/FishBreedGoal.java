package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousWaterAnimal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FishBreedGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    protected final MarvelousWaterAnimal animal;
    private final Class<? extends MarvelousWaterAnimal> partnerClass;
    protected final Level level;
    @Nullable
    protected MarvelousWaterAnimal partner;
    private int loveTime;
    private final double speedModifier;
    private final double inflation;

    public FishBreedGoal(MarvelousWaterAnimal pAnimal, double pSpeedModifier) {
        this(pAnimal, pSpeedModifier, pAnimal.getClass(), 0.1);
    }

    public FishBreedGoal(MarvelousWaterAnimal pAnimal, double pSpeedModifier, double inflation) {
        this(pAnimal, pSpeedModifier, pAnimal.getClass(), inflation);
    }

    public FishBreedGoal(MarvelousWaterAnimal pAnimal, double pSpeedModifier, Class<? extends MarvelousWaterAnimal> pPartnerClass, double inflation) {
        this.animal = pAnimal;
        this.level = pAnimal.level();
        this.partnerClass = pPartnerClass;
        this.speedModifier = pSpeedModifier;
        this.inflation = inflation;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.animal.isInLove()) {
            return false;
        } else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
    }

    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    public void tick() {
        this.animal.getLookControl().setLookAt(this.partner, 10.0F, (float)this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        if (this.loveTime >= this.adjustedTickDelay(60) && this.animal.getBoundingBox().inflate(inflation).intersects(this.partner.getBoundingBox().inflate(inflation))) {
            this.breed();
        }

    }

    @Nullable
    private MarvelousWaterAnimal getFreePartner() {
        List<? extends MarvelousWaterAnimal> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        MarvelousWaterAnimal animal = null;

        for(MarvelousWaterAnimal animal1 : list) {
            if (this.animal.canMate(animal1) && this.animal.distanceToSqr(animal1) < d0) {
                animal = animal1;
                d0 = this.animal.distanceToSqr(animal1);
            }
        }

        return animal;
    }

    protected void breed() {
        this.animal.spawnChildFromBreeding((ServerLevel)this.level, this.partner);
    }
}