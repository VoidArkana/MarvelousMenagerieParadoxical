package net.voidarkana.marvelous_menagerie.util.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.util.advancements.MMCriterion;

import java.util.function.Supplier;

public class MountAttackMessage {

    public int victimID;
    public int ownerID;

    public MountAttackMessage(int pVictimID, int pOwnerID) {
        this.victimID = pVictimID;
        this.ownerID = pOwnerID;
    }

    public static MountAttackMessage read(FriendlyByteBuf buf) {
        return new MountAttackMessage(buf.readInt(), buf.readInt());
    }

    public static void write(MountAttackMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.victimID);
        buf.writeInt(message.ownerID);
    }

    public static void handle(MountAttackMessage message, Supplier<NetworkEvent.Context> context) {

        Level level;
        ServerPlayer sender = context.get().getSender();
        if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            level = MarvelousMenagerie.PROXY.getWorld();
        else {
            if (sender == null) return;
            level = sender.level();
        }

        if (level != null){
            Entity entity = level.getEntity(message.victimID);
            Entity pOwner = level.getEntity(message.ownerID);

            if (entity instanceof LivingEntity livingentity && pOwner instanceof LivingEntity owner && owner.hasControllingPassenger()){

//                if (owner instanceof IAnimatedAttacker attacker){
//
//                    if (attacker.getAttackSound() != null)
//                        level.playSound((Player)null, owner, attacker.getAttackSound(), SoundSource.NEUTRAL, 1.0F, 1.0F);
//                }

                if (livingentity.isAlive()){
                    int i = owner.hasEffect(MobEffects.MOVEMENT_SPEED) ? owner.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() + 1 : 0;
                    int j = owner.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? owner.getEffect(MobEffects.MOVEMENT_SLOWDOWN).getAmplifier() + 1 : 0;
                    float f = 0.25F * (float)(i - j);
                    float f1 = Mth.clamp(owner.getSpeed() * 1.65F, 0.2F, 3.0F) + f;
                    float f2 = livingentity.isDamageSourceBlocked(owner.damageSources().mobAttack(owner)) ? 0.5F : 1.0F;
                    livingentity.knockback((double)(f2 * f1), owner.getLookAngle().x(), owner.getLookAngle().z());
                    livingentity.hurt(level.damageSources().mobAttack(owner.getControllingPassenger()), (float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE));
                }
            }

        }
    }
    
}
