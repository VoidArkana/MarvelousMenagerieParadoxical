package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.message.FossilRecipeS2C;
import net.voidarkana.marvelous_menagerie.util.network.indexes.*;

public class MMMessages {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MarvelousMenagerie.MODID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        CHANNEL.registerMessage(id(), TESyncPacket.class,
                TESyncPacket::encode,
                TESyncPacket::decode,
                TESyncPacket::consume);

        CHANNEL.registerMessage(id(), FossilRecipeS2C.class,
                FossilRecipeS2C::encode,
                FossilRecipeS2C::decode,
                FossilRecipeS2C::onPacketReceived);

        CHANNEL.registerMessage(id(), ChangeFossilMessage.class,
                ChangeFossilMessage::write,
                ChangeFossilMessage::read,
                ChangeFossilMessage::handle);

        CHANNEL.registerMessage(id(), AbominationIndexSyncPacket.class,
                AbominationIndexSyncPacket::write,
                AbominationIndexSyncPacket::read,
                AbominationIndexSyncPacket::handle);

        CHANNEL.registerMessage(id(), CarboniferousIndexSyncPacket.class,
                CarboniferousIndexSyncPacket::write,
                CarboniferousIndexSyncPacket::read,
                CarboniferousIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), CretaceousIndexSyncPacket.class,
                CretaceousIndexSyncPacket::write,
                CretaceousIndexSyncPacket::read,
                CretaceousIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), EarlyPaleoIndexSyncPacket.class,
                EarlyPaleoIndexSyncPacket::write,
                EarlyPaleoIndexSyncPacket::read,
                EarlyPaleoIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), JurassicIndexSyncPacket.class,
                JurassicIndexSyncPacket::write,
                JurassicIndexSyncPacket::read,
                JurassicIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), NeogeneIndexSyncPacket.class,
                NeogeneIndexSyncPacket::write,
                NeogeneIndexSyncPacket::read,
                NeogeneIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), PaleogeneIndexSyncPacket.class,
                PaleogeneIndexSyncPacket::write,
                PaleogeneIndexSyncPacket::read,
                PaleogeneIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), PermianIndexSyncPacket.class,
                PermianIndexSyncPacket::write,
                PermianIndexSyncPacket::read,
                PermianIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), QuaternaryIndexSyncPacket.class,
                QuaternaryIndexSyncPacket::write,
                QuaternaryIndexSyncPacket::read,
                QuaternaryIndexSyncPacket::handle);
        CHANNEL.registerMessage(id(), TriassicIndexSyncPacket.class,
                TriassicIndexSyncPacket::write,
                TriassicIndexSyncPacket::read,
                TriassicIndexSyncPacket::handle);
    }

    public static <MSG> void sendToServer(MSG message) {
        CHANNEL.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), message);
    }


    public static <MSG> void sendMSGToAll(MSG message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendNonLocal(message, player);
        }
    }

    public static <MSG> void sendNonLocal(MSG msg, ServerPlayer player) {
        CHANNEL.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}

