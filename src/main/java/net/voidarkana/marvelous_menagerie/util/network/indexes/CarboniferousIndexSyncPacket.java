package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.AbominationEntryManager;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.CarboniferousEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class CarboniferousIndexSyncPacket extends SyncJsonResourcePacket<CarboniferousEntryManager.EntityCodec> {

    public CarboniferousIndexSyncPacket(BiMap<ResourceLocation, CarboniferousEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public CarboniferousIndexSyncPacket() {}

    @Override
    protected CarboniferousEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return CarboniferousEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, CarboniferousEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static CarboniferousIndexSyncPacket read(FriendlyByteBuf buf) {
        CarboniferousIndexSyncPacket message = new CarboniferousIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(CarboniferousIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(CarboniferousIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getCarboniferousEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
