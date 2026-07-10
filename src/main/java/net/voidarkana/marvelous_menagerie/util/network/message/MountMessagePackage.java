package net.voidarkana.marvelous_menagerie.util.network.message;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.client.events.MMEventBusClientEvents;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Kelenken;

import java.util.function.Supplier;

public class MountMessagePackage {

    public MountMessagePackage() {
    }

    public static MountMessagePackage read(FriendlyByteBuf buf) {
        return new MountMessagePackage();
    }

    public static void write(MountMessagePackage packet, FriendlyByteBuf buf) {
    }

    public static void handle(MountMessagePackage packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer localPlayer = minecraft.player;
            if (localPlayer != null && localPlayer.getVehicle() instanceof Kelenken) {
                localPlayer.displayClientMessage(Component.translatable("message.marvelous_menagerie.mount_attack", MMEventBusClientEvents.attackKey.getTranslatedKeyMessage()), true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
