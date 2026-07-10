package net.voidarkana.marvelous_menagerie.util.network.message;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;

import java.util.function.Supplier;

public class MountSoundMessage {

    public int soundFlag;
    public int ownerID;
    public float volume;

    public MountSoundMessage(int pFlag, int pOwnerID, float volume) {
        this.soundFlag = pFlag;
        this.ownerID = pOwnerID;
        this.volume = volume;
    }

    public static MountSoundMessage read(FriendlyByteBuf buf) {
        return new MountSoundMessage(buf.readInt(), buf.readInt(), buf.readFloat());
    }

    public static void write(MountSoundMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.soundFlag);
        buf.writeInt(message.ownerID);
        buf.writeFloat(message.volume);
    }

    public static void handle(MountSoundMessage message, Supplier<NetworkEvent.Context> context) {

        Level level;
        ServerPlayer sender = context.get().getSender();
        if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            level = MarvelousMenagerie.PROXY.getWorld();
        else {
            if (sender == null) return;
            level = sender.level();
        }

        if (level != null){
            Entity pOwner = level.getEntity(message.ownerID);

            if (pOwner instanceof LivingEntity owner){
                SoundEvent event = null;

                if (message.soundFlag == 1)
                    event = SoundEvents.PARROT_FLY;
                if (message.soundFlag == 2)
                    event = MMSounds.KELENKEN_BITE.get();

                if (event != null)
                    owner.playSound(event, message.volume, owner.getVoicePitch());
            }

        }
    }
    
}
