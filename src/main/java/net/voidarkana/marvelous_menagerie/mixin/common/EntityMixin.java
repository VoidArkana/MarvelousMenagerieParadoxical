package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.entity.base.MMEntityAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements MMEntityAccess {

    @Shadow
    @Final
    protected SynchedEntityData entityData;

    @Shadow public abstract boolean isInvisible();

    @Shadow public abstract void setInvisible(boolean pInvisible);

    private static final EntityDataAccessor<Float> SUMMON_TIME = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.FLOAT);
    float prevSummonTime;

    @Inject(
            method = {"isInvulnerableTo"},
            at = @At(value = "HEAD"),
            cancellable = true)
    public void isInvulnerableTo(DamageSource pSource, CallbackInfoReturnable<Boolean> cir) {
        if (pSource.getEntity() instanceof LivingEntity entity){
            if (entity.hasEffect(MMEffects.PACIFIED.get()))
                cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("TAIL"),
            remap = true,
            method = "Lnet/minecraft/world/entity/Entity;<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V")
    private void citadel_registerData(CallbackInfo ci) {
        entityData.define(SUMMON_TIME, 0F);
    }

    @Override
    public float getSummonedProgress(float partialTick) {
        return (getPrevSummonTime() + (getPrevSummonTime() - getPrevSummonTime()) * partialTick) * 0.05F;
    }

    public float getSummonedTime() {
        return entityData.hasItem(SUMMON_TIME) ? entityData.get(SUMMON_TIME) : 0.0F;
    }

    public float getPrevSummonTime() {
        return this.prevSummonTime;
    }


    @Override
    public void setSummonedTime(float pProgress) {
        if (entityData.hasItem(SUMMON_TIME)){
            this.entityData.set(SUMMON_TIME, pProgress);
        }
    }


    @Inject(
            method = {"tick"},
            at = @At(value = "HEAD"))
    protected void tickMixin(CallbackInfo ci) {

        this.prevSummonTime = this.getSummonedTime();

        if (this.getSummonedTime() > 0F) {
            this.setSummonedTime(prevSummonTime-1);
        }
    }
}
