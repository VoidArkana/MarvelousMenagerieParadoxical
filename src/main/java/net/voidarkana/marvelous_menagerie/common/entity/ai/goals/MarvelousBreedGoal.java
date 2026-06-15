package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MarvelousBreedGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    protected final MarvelousAnimal animal;
    private final Class<? extends MarvelousAnimal> partnerClass;
    protected final Level level;
    @Nullable
    protected MarvelousAnimal partner;
    private int loveTime;
    private final double speedModifier;
    private final double inflation;

    public MarvelousBreedGoal(MarvelousAnimal pAnimal, double pSpeedModifier) {
        this(pAnimal, pSpeedModifier, pAnimal.getClass(), 0.1);
    }

    public MarvelousBreedGoal(MarvelousAnimal pAnimal, double pSpeedModifier, double inflation) {
        this(pAnimal, pSpeedModifier, pAnimal.getClass(), inflation);
    }

    public MarvelousBreedGoal(MarvelousAnimal pAnimal, double pSpeedModifier, Class<? extends MarvelousAnimal> pPartnerClass, double inflation) {
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
    private MarvelousAnimal getFreePartner() {
        List<? extends MarvelousAnimal> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.animal, this.animal.getBoundingBox().inflate(8.0D));
        double d0 = Double.MAX_VALUE;
        MarvelousAnimal animal = null;

        for(MarvelousAnimal animal1 : list) {
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