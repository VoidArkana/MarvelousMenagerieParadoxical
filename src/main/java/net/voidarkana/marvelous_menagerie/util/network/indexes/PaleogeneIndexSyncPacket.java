package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.PaleogeneEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class PaleogeneIndexSyncPacket extends SyncJsonResourcePacket<PaleogeneEntryManager.EntityCodec> {

    public PaleogeneIndexSyncPacket(BiMap<ResourceLocation, PaleogeneEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public PaleogeneIndexSyncPacket() {}

    @Override
    protected PaleogeneEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return PaleogeneEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, PaleogeneEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static PaleogeneIndexSyncPacket read(FriendlyByteBuf buf) {
        PaleogeneIndexSyncPacket message = new PaleogeneIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(PaleogeneIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(PaleogeneIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getPaleogeneEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
