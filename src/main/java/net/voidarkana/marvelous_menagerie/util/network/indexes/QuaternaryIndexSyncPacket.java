
package net.voidarkana.marvelous_menagerie.util.network.indexes;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.QuaternaryEntryManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class QuaternaryIndexSyncPacket extends SyncJsonResourcePacket<QuaternaryEntryManager.EntityCodec> {

    public QuaternaryIndexSyncPacket(BiMap<ResourceLocation, QuaternaryEntryManager.EntityCodec> registryMap) {
        super(registryMap);
    }

    public QuaternaryIndexSyncPacket() {}

    @Override
    protected QuaternaryEntryManager.EntityCodec readJsonObject(FriendlyByteBuf buf) {
        return QuaternaryEntryManager.EntityCodec.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, QuaternaryEntryManager.EntityCodec toWrite) {
        toWrite.toNetwork(buf);
    }

    public static QuaternaryIndexSyncPacket read(FriendlyByteBuf buf) {
        QuaternaryIndexSyncPacket message = new QuaternaryIndexSyncPacket();
        message.readMap(buf);
        return message;
    }

    public static void write(QuaternaryIndexSyncPacket message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(QuaternaryIndexSyncPacket message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getQuaternaryEntryManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
