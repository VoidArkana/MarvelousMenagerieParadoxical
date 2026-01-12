package net.voidarkana.marvelous_menagerie.util.network.message;

import com.google.common.collect.BiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.manager.RitualManager;
import net.voidarkana.marvelous_menagerie.util.network.SyncJsonResourcePacket;

import java.util.function.Supplier;

public class SyncSummoningRituals extends SyncJsonResourcePacket<RitualManager.RitualProcessData> {

    public SyncSummoningRituals(BiMap<ResourceLocation, RitualManager.RitualProcessData> registryMap) {
        super(registryMap);
    }

    public SyncSummoningRituals() {}

    @Override
    protected RitualManager.RitualProcessData readJsonObject(FriendlyByteBuf buf) {
        return RitualManager.RitualProcessData.fromNetwork(buf);
    }

    @Override
    protected void writeJsonObject(FriendlyByteBuf buf, RitualManager.RitualProcessData toWrite) {
        toWrite.toNetwork(buf);
    }

    public static SyncSummoningRituals read(FriendlyByteBuf buf) {
        SyncSummoningRituals message = new SyncSummoningRituals();
        message.readMap(buf);
        return message;
    }

    public static void write(SyncSummoningRituals message, FriendlyByteBuf buf) {
        message.writeMap(buf);
    }

    public static void handle(SyncSummoningRituals message, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                MarvelousMenagerie.PROXY.getRitualManager().synchronizeRegistryForClient(message.registryMap);
            }
        });
    }
}
