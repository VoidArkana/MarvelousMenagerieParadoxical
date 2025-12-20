package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.network.message.*;

public class MMMessages {

    private static final String PROTOCOL_VERSION = "1";

    private static final ResourceLocation PACKET_NETWORK_NAME = new ResourceLocation(MarvelousMenagerie.MOD_ID, "main_channel");
    public static final SimpleChannel CHANNEL =  NetworkRegistry.ChannelBuilder
            .named(PACKET_NETWORK_NAME)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

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

        CHANNEL.registerMessage(id(), PaleonomiconIndexSyncPacket.class,
                PaleonomiconIndexSyncPacket::write,
                PaleonomiconIndexSyncPacket::read,
                PaleonomiconIndexSyncPacket::handle);

        CHANNEL.registerMessage(id(), OpenElephantBirdScreenPacket.class,
                OpenElephantBirdScreenPacket::write,
                OpenElephantBirdScreenPacket::read,
                OpenElephantBirdScreenPacket.Handler::handle);
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

