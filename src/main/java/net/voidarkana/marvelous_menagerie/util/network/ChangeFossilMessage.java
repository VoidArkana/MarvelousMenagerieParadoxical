package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;

import java.util.function.Supplier;

public class ChangeFossilMessage {

    public int success;
    public BlockPos pos;

    public ChangeFossilMessage(int pSuccess, BlockPos pPos) {
        this.success = pSuccess;
        this.pos = pPos;
    }

    public static ChangeFossilMessage read(FriendlyByteBuf buf) {
        return new ChangeFossilMessage(buf.readInt(), buf.readBlockPos());
    }

    public static void write(ChangeFossilMessage message, FriendlyByteBuf buf) {
        buf.writeInt(message.success);
        buf.writeBlockPos(message.pos);
    }

    public static void handle(ChangeFossilMessage message, Supplier<NetworkEvent.Context> context) {
        // server level
        //Player player = context.get().getSender();

        Level level;
        ServerPlayer sender = context.get().getSender();
        if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            level = MarvelousMenagerie.PROXY.getWorld();
        else {
            if (sender == null) return;
            level = sender.level();
        }
            //Level level = player.level();

        if (level != null){
            if (level.hasChunkAt(message.pos) && level.getBlockState(message.pos).getBlock() instanceof FossilBlock fossil) {

                //fossil.updateState(this.packed, this.recipient, this.sender, this.description, player);

                BlockState state = level.getBlockState(message.pos);
                fossil.destroyOriginalWithSuccessLevel(level ,message.success, message.pos);
                //also sends new block to clients. maybe not needed since blockstate changes
                level.sendBlockUpdated(message.pos, state, state, 3);

            }
        }
    }
    
}
