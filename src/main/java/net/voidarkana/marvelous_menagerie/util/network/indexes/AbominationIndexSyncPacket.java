package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.AbominationEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class AbominationIndexSyncPacket extends SyncJsonResourcePacket<AbominationEntryManager.EntityCodec> {

    public AbominationIndexSyncPacket(BiMap<ResourceLocation, AbominationEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public AbominationIndexSyncPacket() {}

    @Override
    protected AbominationEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return AbominationEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, AbominationEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static AbominationIndexSyncPacket read(FriendlyByteBuf buf) {
        AbominationIndexSyncPacket message = new AbominationIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(AbominationIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(AbominationIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getAbominationEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
