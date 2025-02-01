package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.renderer.block.AltarRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PaleoTableRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PedestalRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination.ChudRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.animal.ArandaspisRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.animal.SacaRenderer;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{

    public ClientProxy() {
    }

    public void clientInit() {

        MarvelousMenagerie.CALLBACKS.forEach(Runnable::run);
        MarvelousMenagerie.CALLBACKS.clear();

        EntityRenderers.register(MMEntities.CHUD.get(), ChudRenderer::new);
        EntityRenderers.register(MMEntities.SACABAMBASPIS.get(), SacaRenderer::new);
        EntityRenderers.register(MMEntities.ARANDASPIS.get(), ArandaspisRenderer::new);

        BlockEntityRenderers.register(MMBlockEntities.PEDESTAL_ENTITY.get(), PedestalRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.ALTAR_ENTITY.get(), AltarRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.PALEO_TABLE_ENTITY.get(), PaleoTableRenderer::new);
        //MenuScreens.register(MMMenuTypes.FOSSIL_MINIGAME_CONTAINER.get(), FossilMinigameScreen::new);
    }

    @Override
    public Level getWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    public void openToolkitGUI(Player player, BlockPos clicked) {
        Minecraft.getInstance().setScreen(new FossilMinigameScreen(player, clicked));
    }
}
