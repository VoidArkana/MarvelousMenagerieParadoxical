package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.JurassicEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class JurassicIndexSyncPacket extends SyncJsonResourcePacket<JurassicEntryManager.EntityCodec> {

    public JurassicIndexSyncPacket(BiMap<ResourceLocation, JurassicEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public JurassicIndexSyncPacket() {}

    @Override
    protected JurassicEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return JurassicEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, JurassicEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static JurassicIndexSyncPacket read(FriendlyByteBuf buf) {
        JurassicIndexSyncPacket message = new JurassicIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(JurassicIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(JurassicIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getJurassicEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
