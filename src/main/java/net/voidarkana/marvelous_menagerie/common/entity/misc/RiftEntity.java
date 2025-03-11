package net.voidarkana.marvelous_menagerie.common.entity.misc;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fluids.FluidType;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class RiftEntity extends Monster {

    private static final Predicate<LivingEntity> PLAYER = (entity) -> {
        return entity instanceof Player;
    };

    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(PLAYER);

    public RiftEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0);
    }

    @Override
    public void tick() {
        super.tick();

        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(2.0D), (p_149015_) -> {
            return RiftEntity.targetingConditions.test(this, p_149015_);
        });

        if (this.getRandom().nextInt(4) == 0) {
            int xSpeed = this.getRandom().nextInt(-2, 3);
            int ySpeed = this.getRandom().nextInt(-2, 3);
            int zSpeed = this.getRandom().nextInt(-2, 3);

            this.level().addAlwaysVisibleParticle(MMParticles.TIME_SHARD.get(),
                    this.blockPosition().getX() + xSpeed + 0.75,
                    this.blockPosition().getY() + ySpeed + 0.75,
                    this.blockPosition().getZ() + zSpeed + 0.25,
                    -xSpeed, -ySpeed, -zSpeed
            );
        }

        if (!list.isEmpty()) {
            createFracture();
        }
    }

    private void createFracture() {
        Level $$1 = this.level();
        if ($$1 instanceof ServerLevel serverlevel) {
            Fracture fracture = new Fracture(serverlevel, this.blockPosition().getX()+0.5,
                    this.blockPosition().getY()-0.25,
                    this.blockPosition().getZ()+0.5);
            fracture.setIsNatural(true);
            this.playSound(SoundEvents.GLASS_BREAK, 1f, 0.75F);
            serverlevel.addFreshEntity(fracture);
            this.discard();
        }

    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        if (pReason == MobSpawnType.NATURAL){
            this.setPos(this.getX(), this.getY()+1f, this.getZ());
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean isPushable() {
        return false;
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
