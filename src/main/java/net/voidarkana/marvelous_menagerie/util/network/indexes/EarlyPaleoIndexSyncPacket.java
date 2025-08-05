package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.EarlyPaleoEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class EarlyPaleoIndexSyncPacket extends SyncJsonResourcePacket<EarlyPaleoEntryManager.EntityCodec> {

    public EarlyPaleoIndexSyncPacket(BiMap<ResourceLocation, EarlyPaleoEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public EarlyPaleoIndexSyncPacket() {}

    @Override
    protected EarlyPaleoEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return EarlyPaleoEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, EarlyPaleoEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static EarlyPaleoIndexSyncPacket read(FriendlyByteBuf buf) {
        EarlyPaleoIndexSyncPacket message = new EarlyPaleoIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(EarlyPaleoIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(EarlyPaleoIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getEarlyPaleoEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
