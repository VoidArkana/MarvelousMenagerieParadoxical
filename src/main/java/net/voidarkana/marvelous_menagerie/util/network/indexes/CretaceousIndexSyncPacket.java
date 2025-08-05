package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.CretaceousEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class CretaceousIndexSyncPacket extends SyncJsonResourcePacket<CretaceousEntryManager.EntityCodec> {

    public CretaceousIndexSyncPacket(BiMap<ResourceLocation, CretaceousEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public CretaceousIndexSyncPacket() {}

    @Override
    protected CretaceousEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return CretaceousEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, CretaceousEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static CretaceousIndexSyncPacket read(FriendlyByteBuf buf) {
        CretaceousIndexSyncPacket message = new CretaceousIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(CretaceousIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(CretaceousIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getCretaceousEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
