package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.TriassicEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class TriassicIndexSyncPacket extends SyncJsonResourcePacket<TriassicEntryManager.EntityCodec> {

    public TriassicIndexSyncPacket(BiMap<ResourceLocation, TriassicEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public TriassicIndexSyncPacket() {}

    @Override
    protected TriassicEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return TriassicEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, TriassicEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static TriassicIndexSyncPacket read(FriendlyByteBuf buf) {
        TriassicIndexSyncPacket message = new TriassicIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(TriassicIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(TriassicIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getTriassicEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
