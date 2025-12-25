package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.LockCode;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;

import javax.annotation.Nullable;

public class HerpetogasterBlockEntity extends BlockEntity {

    @Nullable
    private Component name;

    public HerpetogasterBlockEntity(BlockPos pos, BlockState state) {
        super(MMBlockEntities.HERPETOGASTER_BLOCK_ENTITY.get(), pos, state);
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(pTag.getString("CustomName"));
        }

    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (this.name != null) {
            pTag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
    }

    protected Component getDefaultName() {
        return Component.translatable("block.marvelous_menagerie.herpetogaster");
    }

    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    public void setCustomName(Component pName) {
        this.name = pName;
    }

    public boolean isChristmas(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && (s.toLowerCase().contains("christmas")
                || s.toLowerCase().contains("boot")
                || s.toLowerCase().contains("stocking")
                || s.toLowerCase().contains("sock")
                || s.toLowerCase().contains("christmas boot")
                || s.toLowerCase().contains("christmas stocking")
                || s.toLowerCase().contains("christmas sock")
        );
    }
}
