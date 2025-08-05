
package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.NeogeneEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class NeogeneIndexSyncPacket extends SyncJsonResourcePacket<NeogeneEntryManager.EntityCodec> {

    public NeogeneIndexSyncPacket(BiMap<ResourceLocation, NeogeneEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public NeogeneIndexSyncPacket() {}

    @Override
    protected NeogeneEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return NeogeneEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, NeogeneEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static NeogeneIndexSyncPacket read(FriendlyByteBuf buf) {
        NeogeneIndexSyncPacket message = new NeogeneIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(NeogeneIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(NeogeneIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getNeogeneEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
