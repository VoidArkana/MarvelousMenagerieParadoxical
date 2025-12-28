package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<Entity> {

    protected EntityMixin(Class<Entity> baseClass) {
        super(baseClass);
    }

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
}
