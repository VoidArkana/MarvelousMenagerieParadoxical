package net.voidarkana.marvelous_menagerie.client.screen.fossil;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.voidarkana.marvelous_menagerie.client.screen.MMMenuTypes;
import org.jetbrains.annotations.Nullable;

public class FossilMinigameMenu extends AbstractContainerMenu {

    //private final ContainerLevelAccess access;


    public FossilMinigameMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public FossilMinigameMenu(int id, Inventory inventory, final ContainerLevelAccess access) {
        super(MMMenuTypes.FOSSIL_MENU.get(), id);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
