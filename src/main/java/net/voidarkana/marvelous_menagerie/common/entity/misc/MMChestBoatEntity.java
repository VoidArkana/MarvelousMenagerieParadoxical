package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

public class MMChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public MMChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MMChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(MMEntities.MM_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        switch (getMMVariant()) {
            case SIGILLARIA -> {
                return MMItems.SIGILLARIA_CHEST_BOAT.get();
            }
            case PROTOTAXITES -> {
                return MMItems.PROTOTAXITES_CHEST_BOAT.get();
            }
            case CALAMITES -> {
                return MMItems.CALAMITES_CHEST_BOAT.get();
            }
        }
        return super.getDropItem();
    }

    public void setVariant(MMBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, MMBoatEntity.Type.SIGILLARIA.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getMMVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Type", 8)) {
            this.setVariant(MMBoatEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public MMBoatEntity.Type getMMVariant() {
        return MMBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
