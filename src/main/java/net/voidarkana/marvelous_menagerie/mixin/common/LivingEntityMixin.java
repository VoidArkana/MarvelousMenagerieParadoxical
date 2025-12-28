package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(
            method = {"canAttack(Lnet/minecraft/world/entity/LivingEntity;)Z"},
            at = @At(value = "HEAD"),
            cancellable = true)
    protected void canAttackMixin(LivingEntity pTarget, CallbackInfoReturnable<Boolean> cir) {
        var self = (LivingEntity) (Object) (this);
        if (pTarget.hasEffect(MMEffects.PACIFIED.get()) || self.hasEffect(MMEffects.PACIFIED.get())) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = {"canAttack(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/ai/targeting/TargetingConditions;)Z"},
            at = @At(value = "HEAD"),
            cancellable = true)
    protected void canAttackMixin(LivingEntity pLivingentity, TargetingConditions pCondition, CallbackInfoReturnable<Boolean> cir) {
        var self = (LivingEntity) (Object) (this);
        if (pLivingentity.hasEffect(MMEffects.PACIFIED.get()) || self.hasEffect(MMEffects.PACIFIED.get())) {
            cir.setReturnValue(false);
        }
    }
}
