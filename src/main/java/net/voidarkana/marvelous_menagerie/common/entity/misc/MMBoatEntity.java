package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

import java.util.function.IntFunction;

public class MMBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(MMBoatEntity.class, EntityDataSerializers.INT);

    public MMBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MMBoatEntity(Level level, double pX, double pY, double pZ) {
        this(MMEntities.MM_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return switch (getMMVariant()) {
            case SIGILLARIA -> MMItems.SIGILLARIA_BOAT.get();
            case PROTOTAXITES -> MMItems.PROTOTAXITES_BOAT.get();
            case CALAMITES -> MMItems.CALAMITES_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getMMVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.SIGILLARIA.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getMMVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        SIGILLARIA(MMBlocks.SIGILLARIA_PLANKS.get(), "sigillaria"),
        PROTOTAXITES(MMBlocks.PROTOTAXITES_PLANKS.get(), "prototaxites"),
        CALAMITES(MMBlocks.CALAMITES_PLANKS.get(), "calamites");

        private final String name;
        private final Block planks;
        public static final EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static Type byName(String pName) {
            return CODEC.byName(pName, SIGILLARIA);
        }
    }
}
