package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.PermianEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class PermianIndexSyncPacket extends SyncJsonResourcePacket<PermianEntryManager.EntityCodec> {

    public PermianIndexSyncPacket(BiMap<ResourceLocation, PermianEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public PermianIndexSyncPacket() {}

    @Override
    protected PermianEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return PermianEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, PermianEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static PermianIndexSyncPacket read(FriendlyByteBuf buf) {
        PermianIndexSyncPacket message = new PermianIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(PermianIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(PermianIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getPermianEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
