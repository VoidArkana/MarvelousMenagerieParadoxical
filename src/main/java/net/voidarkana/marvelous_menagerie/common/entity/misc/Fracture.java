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
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;

import java.util.Collections;

public class Fracture extends LivingEntity {

    private static final EntityDataAccessor<Boolean> IS_NATURAL = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> OPENING_TIME = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> OPENING_TIME_LAG = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CLOSING_TIME = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);

    private int strikeTime = 0;
    private Vec3 lightningPos = null;
    private LightningBolt dummyBolt;

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
        this.entityData.define(OPENING_TIME, 0);
        this.entityData.define(OPENING_TIME_LAG, 0);
        this.entityData.define(CLOSING_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsNatural", this.getIsNatural());
        pCompound.putInt("OpeningTime", this.getOpeningTime());
        pCompound.putInt("OpeningTimeLag", this.getOpeningTimeLag());
        pCompound.putInt("ClosingTime", this.getClosingTime());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setIsNatural(pCompound.getBoolean("IsNatural"));
        this.setOpeningTime(pCompound.getInt("OpeningTime"));
        this.setOpeningTimeLag(pCompound.getInt("OpeningTimeLag"));
        this.setClosingTime(pCompound.getInt("ClosingTime"));
    }

    public boolean getIsNatural() {
        return this.entityData.get(IS_NATURAL);
    }

    public void setIsNatural(boolean pFromBucket) {
        this.entityData.set(IS_NATURAL, pFromBucket);
    }

    public int getOpeningTime() {
        return this.entityData.get(OPENING_TIME);
    }

    public void setOpeningTime(int time) {
        this.entityData.set(OPENING_TIME, time);
    }

    public int getOpeningTimeLag() {
        return this.entityData.get(OPENING_TIME_LAG);
    }

    public void setOpeningTimeLag(int time) {
        this.entityData.set(OPENING_TIME_LAG, time);
    }

    public int getClosingTime() {
        return this.entityData.get(CLOSING_TIME);
    }

    public void setClosingTime(int time) {
        this.entityData.set(CLOSING_TIME, time);
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
            int xSpeed = this.getRandom().nextInt(-2, 3);
            int ySpeed = this.getRandom().nextInt(-2, 3);
            int zSpeed = this.getRandom().nextInt(-2, 3);

            this.level().addParticle(MMParticles.TIME_SHARD.get(),
                    this.blockPosition().getX() + xSpeed + 0.5,
                    this.blockPosition().getY() + ySpeed + 1,
                    this.blockPosition().getZ() + zSpeed + 0.5,
                    -xSpeed, -ySpeed, -zSpeed
            );
        }

        if (this.getRandom().nextInt(16) == 0) {

            int xSpeed = this.getRandom().nextInt(-2, 3);
            int ySpeed = this.getRandom().nextInt(-2, 3);
            int zSpeed = this.getRandom().nextInt(-2, 3);

            this.level().addParticle(ParticleTypes.END_ROD,
                    this.blockPosition().getX() + xSpeed/1.75 + 0.5,
                    this.blockPosition().getY() + ySpeed/1.75 + 1,
                    this.blockPosition().getZ() + zSpeed/1.75 + 0.5,
                    0,0,0
            );
        }

        super.tick();

        if (this.getOpeningTime()>2 && this.getOpeningTimeLag() < 30){
            int prevOpeningTimeLag = this.getOpeningTimeLag();
            this.setOpeningTimeLag(prevOpeningTimeLag+1);
        }

        if (this.getOpeningTime()<30){
            int prevOpeningTime = this.getOpeningTime();
            this.setOpeningTime(prevOpeningTime+1);
        }

        if (this.getClosingTime()>0){
            int nextClosingTime = this.getClosingTime() -1;
            if (nextClosingTime == 0){
                this.discard();
            }else if (nextClosingTime == 3){
                for (int i = 0; i<20; ++i){
                    int xSpeed = this.getRandom().nextInt(-2, 3);
                    int ySpeed = this.getRandom().nextInt(-2, 3);
                    int zSpeed = this.getRandom().nextInt(-2, 3);

                    this.level().addParticle(MMParticles.TIME_SHARD.get(),
                            this.blockPosition().getX() + xSpeed + 0.5,
                            this.blockPosition().getY() + ySpeed + 1,
                            this.blockPosition().getZ() + zSpeed + 0.5,
                            -xSpeed, -ySpeed, -zSpeed
                    );
                }
            }
            this.setClosingTime(nextClosingTime);
        }

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

    public void closeFracture(){
        this.setClosingTime(20);
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
