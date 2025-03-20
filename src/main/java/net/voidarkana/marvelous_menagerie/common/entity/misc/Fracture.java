package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Fracture extends LivingEntity {

    private static final Predicate<LivingEntity> PLAYER = (entity) -> {
        return entity instanceof Player;
    };

    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(PLAYER);

    private static final EntityDataAccessor<Boolean> IS_NATURAL = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> OPENING_TIME = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> OPENING_TIME_LAG = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CLOSING_TIME = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SUMMONING_TIME = SynchedEntityData.defineId(Fracture.class, EntityDataSerializers.INT);

    EntityType<?> entityType;

    public float rot;
    public float oRot;
    public float tRot;

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
        this.entityData.define(SUMMONING_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("IsNatural", this.isNatural());
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

    public boolean isNatural() {
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

    public int getSummoningTime() {
        return this.entityData.get(SUMMONING_TIME);
    }

    public void setSummoningTime(int time) {
        this.entityData.set(SUMMONING_TIME, time);
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

    @Override
    public boolean isInvulnerable() {
        return super.isInvulnerable() && this.getOpeningTime()<20;
    }

    public boolean isValid(){
        return this.getClosingTime()<=0 && this.getOpeningTime()>=50 && this.getSummoningTime()<=0;
    }

    @Override
    public void tick() {

        if (this.getRandom().nextInt(100) == 0) {
            this.level().playLocalSound((double)this.blockPosition().getX() + (double)0.5F,
                    (double)this.blockPosition().getY() + (double)0.5F,
                    (double)this.blockPosition().getZ() + (double)0.5F,
                    SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 0.5F,
                    this.getRandom().nextFloat() * 0.4F + 0.8F, false);
        }

        if (this.isUnderWater()){

            double d0 = this.blockPosition().getX();
            double d1 = this.blockPosition().getY()+1;
            double d2 = this.blockPosition().getZ();

            if (this.getRandom().nextInt(16) == 0){
                this.level().addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                        d0 + 0.5D, d1, d2 + 0.5D,
                        0.0D, 0.04D, 0.0D);

                this.level().addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                        d0 + this.getRandom().nextFloat(),
                        d1 + this.getRandom().nextFloat(),
                        d2 + this.getRandom().nextFloat(),
                        0.0D, 0.04D, 0.0D);

            }
        }

        if (getOpeningTime()==0 && !this.isNatural()){
            this.level().addAlwaysVisibleParticle(MMParticles.RIFT.get(),
                    this.blockPosition().getX() + 0.5,
                    this.blockPosition().getY() + 1.1,
                    this.blockPosition().getZ() + 0.5,
                    0,0,0
            );
        }

        if (getOpeningTime()==0 && this.isNatural()){
            this.setOpeningTime(20);
            this.setOpeningTimeLag(18);
        }

        if (this.getOpeningTime()==20){
            this.playSound(SoundEvents.GLASS_BREAK, 1f, 0.75F);
        }

        if (getOpeningTime() == 20 || getSummoningTime() == 40){
            for (int i = 0; i<20; ++i){
                int xSpeed = this.getRandom().nextInt(-2, 3);
                int ySpeed = this.getRandom().nextInt(-2, 3);
                int zSpeed = this.getRandom().nextInt(-2, 3);

                this.level().addAlwaysVisibleParticle(MMParticles.TIME_SHARD.get(),
                        this.blockPosition().getX() + xSpeed + 0.5,
                        this.blockPosition().getY() + ySpeed + 1.25,
                        this.blockPosition().getZ() + zSpeed + 0.5,
                        -xSpeed, -ySpeed, -zSpeed
                );
            }
        }

        if (getOpeningTime()>15) {
            if (this.getRandom().nextInt(4) == 0) {
                int xSpeed = this.getRandom().nextInt(-2, 3);
                int ySpeed = this.getRandom().nextInt(-2, 3);
                int zSpeed = this.getRandom().nextInt(-2, 3);

                this.level().addAlwaysVisibleParticle(MMParticles.TIME_SHARD.get(),
                        this.blockPosition().getX() + xSpeed + 0.5,
                        this.blockPosition().getY() + ySpeed + 1.25,
                        this.blockPosition().getZ() + zSpeed + 0.5,
                        -xSpeed, -ySpeed, -zSpeed
                );
            }

            if (this.getRandom().nextInt(16) == 0) {

                int xSpeed = this.getRandom().nextInt(-2, 3);
                int ySpeed = this.getRandom().nextInt(-2, 3);
                int zSpeed = this.getRandom().nextInt(-2, 3);

                this.level().addAlwaysVisibleParticle(ParticleTypes.END_ROD,
                        this.blockPosition().getX() + xSpeed/1.75 + 0.5,
                        this.blockPosition().getY() + ySpeed/1.75 + 1.25,
                        this.blockPosition().getZ() + zSpeed/1.75 + 0.5,
                        0,0,0
                );
            }
        }

        super.tick();

        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2.0D), (p_149015_) -> {
            return RiftEntity.targetingConditions.test(this, p_149015_);
        });

        if (this.getSummoningTime()>0){
            int prevSummoningTime = this.getSummoningTime();


            if (getSummoningTime()==40 && !this.level().isClientSide){

                this.spawnCreature();

                if (this.isNatural()){
                    for (int i = this.getRandom().nextInt(1, 6); i>0; i--){

                        BuiltInRegistries.ENTITY_TYPE.getTag(MMTags.EntityTypes.TIME_ABERRATIONS).flatMap((entityTypeNamed) -> {
                            return entityTypeNamed.getRandomElement(this.level().random);
                        }).ifPresent((entityTypeHolder) -> {
                            this.entityType = entityTypeHolder.get();
                        });

                        this.spawnCreature();
                    }

                    this.setClosingTime(3);
                }

            }
            this.setSummoningTime(prevSummoningTime-1);
        }

        if (this.getOpeningTime()>2 && this.getOpeningTimeLag() < 50){
            int prevOpeningTimeLag = this.getOpeningTimeLag();
            this.setOpeningTimeLag(prevOpeningTimeLag+1);
        }

        if (this.getOpeningTime()<50){
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

                    this.level().addAlwaysVisibleParticle(MMParticles.TIME_SHARD.get(),
                            this.blockPosition().getX() + xSpeed + 0.5,
                            this.blockPosition().getY() + ySpeed + 1.25,
                            this.blockPosition().getZ() + zSpeed + 0.5,
                            -xSpeed, -ySpeed, -zSpeed
                    );
                }
            }
            this.setClosingTime(nextClosingTime);
        }

        this.oRot = this.rot;
        this.tRot += 0.02F;
        while(this.rot >= (float)Math.PI) {
            this.rot -= ((float)Math.PI * 2F);
        }

        while(this.rot < -(float)Math.PI) {
            this.rot += ((float)Math.PI * 2F);
        }

        while(this.tRot >= (float)Math.PI) {
            this.tRot -= ((float)Math.PI * 2F);
        }

        while(this.tRot < -(float)Math.PI) {
            this.tRot += ((float)Math.PI * 2F);
        }

        float f2;
        for(f2 = this.tRot - this.rot; f2 >= (float)Math.PI; f2 -= ((float)Math.PI * 2F)) {
        }

        while(f2 < -(float)Math.PI) {
            f2 += ((float)Math.PI * 2F);
        }

        this.rot += f2 * 0.4F;

        if(!list.isEmpty() && this.isValid() && this.isNatural()){
            BuiltInRegistries.ENTITY_TYPE.getTag(MMTags.EntityTypes.TIME_ABERRATIONS).flatMap((entityTypeNamed) -> {
                return entityTypeNamed.getRandomElement(this.level().random);
            }).ifPresent((entityTypeHolder) -> {
                this.summonCreature(entityTypeHolder.get());
            });
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
        } else if (!this.level().isClientSide && !this.isRemoved() && this.getClosingTime()==0) {
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, pSource.getEntity());
            boolean flag = pSource.getEntity() instanceof Player && ((Player)pSource.getEntity()).getAbilities().instabuild;
            if (flag) {
                this.closeFracture();
            }
            return false;
        } else {
            return false;
        }
    }

    public void summonCreature(EntityType<?> ritualEntity){
        this.setSummoningTime(80);
        this.entityType = ritualEntity;
    }

    public void spawnCreature(){
        if (entityType==null){
            entityType = MMEntities.CHUD.get();
        }

        entityType.spawn((ServerLevel) this.level(),
                new BlockPos(this.blockPosition().getX() + (this.isNatural() ? this.getRandom().nextInt(3) : 0),
                        this.blockPosition().getY()+1,
                        this.blockPosition().getZ() + (this.isNatural() ? this.getRandom().nextInt(3) : 0)),
                MobSpawnType.SPAWN_EGG);

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
