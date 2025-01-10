package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.MMMenuTypes;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {
    public CommonProxy() {
    }

    public void init() {
    }

    public void clientInit() {
    }

    public void openToolkitGUI(Player player, BlockPos clicked) {
    }
}
