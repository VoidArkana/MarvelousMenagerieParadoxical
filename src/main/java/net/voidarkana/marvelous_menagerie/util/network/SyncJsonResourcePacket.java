package net.voidarkana.marvelous_menagerie.util.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

//code that I got from Species that Ninni got from Alex
public abstract class SyncJsonResourcePacket<T> {

    protected BiMap<ResourceLocation, T> registryMap;

    public SyncJsonResourcePacket(BiMap<ResourceLocation, T> registryMap) {
        this.registryMap = registryMap;
    }

    public SyncJsonResourcePacket(){
        this.registryMap = ImmutableBiMap.of();
    }

    protected void readMap(FriendlyByteBuf buf) {
        ImmutableBiMap.Builder<ResourceLocation, T> builder = ImmutableBiMap.builder();
        int len = buf.readVarInt();
        for (int x = 0; x < len; x++){
            builder.put(buf.readResourceLocation(), readJsonObject(buf));
        }
        this.registryMap = builder.build();
    }

    protected void writeMap(FriendlyByteBuf buf) {
        buf.writeVarInt(this.registryMap.size());
        for(Map.Entry<ResourceLocation, T> entry : this.registryMap.entrySet()){
            buf.writeResourceLocation(entry.getKey());
            writeJsonObject(buf, entry.getValue());
        }
    }

    protected abstract T readJsonObject(FriendlyByteBuf buf);
    protected abstract void writeJsonObject(FriendlyByteBuf buf, T toWrite);

}
