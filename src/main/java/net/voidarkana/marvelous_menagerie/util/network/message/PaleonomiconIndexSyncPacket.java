package net.voidarkana.marvelous_menagerie.util.network.message;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.PaleonomiconIndexManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class PaleonomiconIndexSyncPacket extends SyncJsonResourcePacket<PaleonomiconIndexManager.EntityCodec> {

    public PaleonomiconIndexSyncPacket(BiMap<ResourceLocation, PaleonomiconIndexManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public PaleonomiconIndexSyncPacket() {}

    @Override
    protected PaleonomiconIndexManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return PaleonomiconIndexManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, PaleonomiconIndexManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static PaleonomiconIndexSyncPacket read(FriendlyByteBuf buf) {
        PaleonomiconIndexSyncPacket message = new PaleonomiconIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(PaleonomiconIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(PaleonomiconIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getPaleonomiconIndexManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
