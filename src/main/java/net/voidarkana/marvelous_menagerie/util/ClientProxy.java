package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{
    public ClientProxy() {
    }

    @Override
    public void openToolkitGUI(Player player) {
        Minecraft.getInstance().setScreen(new FossilMinigameScreen(player));
    }
}
