package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;

public class Fracture extends Entity {

    public Fracture(EntityType<Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.blocksBuilding = true;
        this.setNoGravity(true);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    public boolean canCollideWith(Entity pEntity) {
        return canFractureCollideWith(this, pEntity);
    }

    public static boolean canFractureCollideWith(Entity pVehicle, Entity pEntity) {
        return pEntity.canBeCollidedWith() || pEntity.isPushable();
    }

    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    public void push(Entity pEntity) {
        if (pEntity instanceof Boat) {
            if (pEntity.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.push(pEntity);
            }
        } else if (pEntity.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.push(pEntity);
        }

    }
}
