package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractAmphibianCreature;
import net.voidarkana.marvelous_menagerie.common.entity.base.IChaserAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class ChaseGoal extends Goal {
    private final PathfinderMob entity;
    private final Predicate<Entity> validChasePartner;
    private int executionCooldown = 50;
    private final int cooldown;
    private final float speedModifier;

    public ChaseGoal(PathfinderMob pMob, int pCooldown, float speedModifier) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.speedModifier = speedModifier;
        this.entity = pMob;
        this.cooldown = pCooldown;
        this.validChasePartner = (partner -> partner instanceof IChaserAnimal otherAnimal
                && partner.getId() != this.entity.getId() && this.entity.getClass() == partner.getClass()
                && otherAnimal.getChasePartner() == null && otherAnimal.getChaseCooldown() <= 0);
    }

    @Override
    public boolean canUse() {
        if (this.entity instanceof IChaserAnimal chaser){
            if(!((entity.isInWaterOrBubble() && entity instanceof WaterAnimal) || (!entity.isInWaterOrBubble() && entity instanceof MarvelousAnimal && !(entity instanceof AbstractAmphibianCreature)))
                    || chaser.getChaseTime() > chaser.getMaxChaseTime() || chaser.getChaseCooldown() > 0){
                return false;
            }
            if(chaser.getChasePartner() != null && chaser.getChasePartner().isAlive()){
                return true;
            }
            if(executionCooldown > 0){
                executionCooldown--;
            }else{
                executionCooldown = 50 + entity.getRandom().nextInt(50);
                if(chaser.getChasePartner() == null || !chaser.getChasePartner().isAlive()){
                    List<? extends PathfinderMob> list = this.entity.level().getEntitiesOfClass(PathfinderMob.class,
                            this.entity.getBoundingBox().inflate(10, 8, 10), validChasePartner);
                    if(!list.isEmpty()){
                        for (PathfinderMob closestEntity : list){
                            if(closestEntity != null){
                                if (closestEntity.getClass() == this.entity.getClass()) {
                                    IChaserAnimal chasePartner = (IChaserAnimal) closestEntity;
                                    chaser.setChasePartner(closestEntity);
                                    chasePartner.setChasePartner(this.entity);;
                                    chaser.setIsChaseDriver(true);
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.entity instanceof IChaserAnimal chaser) {
            return chaser.getChasePartner() != null && chaser.getChasePartner().isAlive() && chaser.getChaseTime() < chaser.getMaxChaseTime();
        }
        return false;
    }

    @Override
    public void start() {
        if (this.entity instanceof IChaserAnimal chaser){
            if (chaser.getChasePartner() instanceof IChaserAnimal partner){
                chaser.setIsChaseDriver(!partner.isChaseDriver());
                chaser.setChaseTime(0);
                chaser.setMaxChaseTime(600);
            }
        }
    }

    @Override
    public void stop() {

        if (this.entity instanceof IChaserAnimal chaser){
            chaser.setChaseTime(0);
            chaser.setChaseCooldown(cooldown+this.entity.getRandom().nextInt(cooldown/2));
            chaser.setChasePartner(null);
        }
        executionCooldown = 50 + this.entity.getRandom().nextInt(50);
    }

    @Override
    public void tick() {
        if (this.entity instanceof IChaserAnimal chaser){
            int prevChaseTime = chaser.getChaseTime();
            chaser.setChaseTime(prevChaseTime+1);
            if(chaser.getChasePartner() == null || !chaser.isChaseDriver()){
                return;
            }
            float chaserSpeed = 1.2F + this.entity.getRandom().nextFloat() * 0.45F * speedModifier;
            float chasedSpeed = 0.2F + chaserSpeed * 0.7F;
            PathfinderMob flee = chaser.isChaseDriver() ? chaser.getChasePartner() : this.entity;
            PathfinderMob driver = chaser.isChaseDriver() ? this.entity : chaser.getChasePartner();
            driver.getNavigation().moveTo(flee.getX(), flee.getY(0.5F), flee.getZ(), chaserSpeed);
            Vec3 from = flee.position().add(this.entity.getRandom().nextFloat() - 0.5F, this.entity.getRandom().nextFloat() - 0.5F, this.entity.getRandom().nextFloat() - 0.5F).subtract(driver.position()).normalize().scale(2F + this.entity.getRandom().nextFloat() * 2F);
            Vec3 to = flee.position().add(from);
            flee.getNavigation().moveTo(to.x, to.y, to.z, chasedSpeed);
            if(this.entity.getRandom().nextInt(50) == 0){
                boolean prevChaseDriver = chaser.isChaseDriver();
                chaser.setIsChaseDriver(!prevChaseDriver);
                if (chaser.getChasePartner() instanceof IChaserAnimal partner){
                    prevChaseDriver = partner.isChaseDriver();
                    partner.setIsChaseDriver(!prevChaseDriver);
                }
            }
        }

    }

}
