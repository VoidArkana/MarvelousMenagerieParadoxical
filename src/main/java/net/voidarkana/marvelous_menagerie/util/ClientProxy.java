package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.MMMenuTypes;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{

    public ClientProxy() {
    }

    public void clientInit() {
        //MenuScreens.register(MMMenuTypes.FOSSIL_MINIGAME_CONTAINER.get(), FossilMinigameScreen::new);
    }

    @Override
    public void openToolkitGUI(Player player, BlockPos clicked) {
        Minecraft.getInstance().setScreen(new FossilMinigameScreen(player, clicked));
    }
}
