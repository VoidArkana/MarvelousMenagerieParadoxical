package net.voidarkana.marvelous_menagerie.common.blockentity.custom.util;


import net.minecraft.world.item.ItemStack;

public interface IRitualItemProvider {
    ItemStack provide();
    void take();
}
