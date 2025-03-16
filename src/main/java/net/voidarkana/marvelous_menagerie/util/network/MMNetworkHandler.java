package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.message.FossilRecipeS2C;

public class MMNetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MarvelousMenagerie.MODID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    //public static SimpleChannel INSTANCE;

    @SuppressWarnings("UnusedAssignment")
    public static void init() {
        int id = 0;
//        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MarvelousMenagerie.MODID, "network"),
//                () -> "1.0", (s) -> true, (s) -> true);

        CHANNEL.registerMessage(++id, TESyncPacket.class,
                TESyncPacket::encode,
                TESyncPacket::decode,
                TESyncPacket::consume);

        //CHANNEL.registerMessage(id++, OpenElephantBirdScreenPacket.class, OpenElephantBirdScreenPacket::encode, OpenElephantBirdScreenPacket::decode, OpenElephantBirdScreenPacket.Handler::handle);
    }

}
