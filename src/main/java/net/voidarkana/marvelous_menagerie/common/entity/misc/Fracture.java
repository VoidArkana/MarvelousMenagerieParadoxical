package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;

import java.util.Collections;

public class Fracture extends LivingEntity {

    private static final EntityDataAccessor<Boolean> IS_NATURAL = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.BOOLEAN);

    public Fracture(EntityType<Fracture> fractureEntityType, Level level) {
        super(fractureEntityType, level);
        this.blocksBuilding = true;
        this.setNoGravity(true);
    }

    public Fracture(Level level, double pX, double pY, double pZ) {
        this(MMEntities.FRACTURE.get(), level);
        this.setPos(new Vec3(pX, pY, pZ));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_NATURAL, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsNatural", this.getIsNatural());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setIsNatural(pCompound.getBoolean("IsNatural"));
    }

    public boolean getIsNatural() {
        return this.entityData.get(IS_NATURAL);
    }

    public void setIsNatural(boolean pFromBucket) {
        this.entityData.set(IS_NATURAL, pFromBucket);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.singleton(ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {}

    public boolean canCollideWith(Entity pEntity) {
        return canFractureCollideWith(this, pEntity);
    }

    public static boolean canFractureCollideWith(Entity pVehicle, Entity pEntity) {
        return pEntity.canBeCollidedWith() || pEntity.isPushable();}

    public boolean isPushable() {
        return false;
    }

//    @Override
//    public boolean isInvulnerable() {
//        return true;
//    }


    @Override
    public void tick() {

        if (this.getRandom().nextInt(4) == 0) {
            this.level().addParticle(ParticleTypes.ENCHANT, this.blockPosition().getX() + 0.5D,
                    this.blockPosition().getY() + 2.5D, this.blockPosition().getZ() + 0.5D,
                    this.getRandom().nextInt(-2, 3),
                    this.getRandom().nextInt(-4, 3),
                    this.getRandom().nextInt(-2, 3));
        }

        super.tick();

//        if (this.level().isClientSide){
//        }
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

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (!this.level().isClientSide && !this.isRemoved()) {
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, pSource.getEntity());
            boolean flag = pSource.getEntity() instanceof Player && ((Player)pSource.getEntity()).getAbilities().instabuild;
            if (flag) {
                this.discard();
            }
            return true;
        } else {
            return true;
        }
    }


    public void summonCreature(EntityType<?> entitytype){
        if (entitytype==null){
            entitytype = MMEntities.CHUD.get();
        }
        entitytype.spawn((ServerLevel) this.level(), this.blockPosition(), MobSpawnType.NATURAL);
        this.level().gameEvent(null, GameEvent.ENTITY_PLACE, this.blockPosition());
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance pEffectInstance) {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }
}
