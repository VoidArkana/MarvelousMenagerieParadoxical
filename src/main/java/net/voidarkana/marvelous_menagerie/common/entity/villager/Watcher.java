package net.voidarkana.marvelous_menagerie.common.entity.villager;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.function.Predicate;

public class Watcher extends Mob implements Npc {

    private static final Predicate<LivingEntity> SCARY_MOB = (p_289442_) -> p_289442_ instanceof Player;
    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);

    public Watcher(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0);
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
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        this.disappear();
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.disappear();
        return super.hurt(pSource, pAmount);
    }

    public void disappear(){
        this.spawnAnim();
        this.level().playSound(this, this.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.VOICE, this.getSoundVolume(), this.getVoicePitch());
        this.remove(RemovalReason.CHANGED_DIMENSION);
    }

    @Override
    public void tick() {
        super.tick();


        Player victim = null;

        for(Player watched : this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(75), (p_149013_) -> {
            return targetingConditions.test(this, p_149013_);
        })) {
            if (watched == null){
                this.disappear();
            }
            if (victim == null){
                victim = watched;
            }
            if (this.isLookingAtMe(watched)){
                this.disappear();
            }
        }

        if (victim != null){
            this.getLookControl().setLookAt(victim);
        }
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
    
    boolean isLookingAtMe(Player pPlayer) {
        ItemStack itemstack = pPlayer.getInventory().armor.get(3);
        if (itemstack.is(Items.CARVED_PUMPKIN) || pPlayer.isCreative() || pPlayer.isSpectator()) {
            return false;
        } else {
            Vec3 vec3 = pPlayer.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - pPlayer.getX(), this.getEyeY() - pPlayer.getEyeY(), this.getZ() - pPlayer.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 0.025D / d0 && pPlayer.hasLineOfSight(this);
        }
    }
}
