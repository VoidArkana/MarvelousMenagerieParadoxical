package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.List;
import java.util.function.Predicate;

public class LookForFoodGoal extends Goal {
    private final PathfinderMob base;
    private final Predicate<ItemEntity> predicate;
    private ItemEntity itemPos;
    public LookForFoodGoal(PathfinderMob base, Predicate<ItemEntity> predicate) {
        this.base = base;
        this.predicate = predicate;
    }

    @Override
    public boolean canUse() {
        if (base.isVehicle())
            return false;
        if(base.canPickUpLoot()) {
            ItemEntity pos = itemPos();
            if (pos != null) {
                itemPos = pos;
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        this.base.getNavigation().moveTo(itemPos, 1.1);
    }

    public ItemEntity itemPos() {
        List<? extends ItemEntity> list = this.base.level().getEntitiesOfClass(ItemEntity.class, this.base.getBoundingBox().inflate(16, 8.0, 16), this.predicate);
        if(!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void trigger() {
        if(canUse()) {
            this.base.getNavigation().stop();
            start();
        }
    }
}
