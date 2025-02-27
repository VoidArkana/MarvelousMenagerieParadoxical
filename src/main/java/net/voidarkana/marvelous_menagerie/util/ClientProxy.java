package net.voidarkana.marvelous_menagerie.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.events.MMClientEvents;
import net.voidarkana.marvelous_menagerie.client.renderer.block.AltarRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.CharniaRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PaleoTableRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PedestalRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.FractureRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination.ChudRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.renderer.item.CustomArmorRenderProperties;
import net.voidarkana.marvelous_menagerie.client.screen.book.PaleonomiconScreen;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{

    public static int shaderLoadAttemptCooldown = 0;

    public ClientProxy() {
    }

    @Override
    public void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new MMClientEvents());

        MarvelousMenagerie.CALLBACKS.forEach(Runnable::run);
        MarvelousMenagerie.CALLBACKS.clear();

        EntityRenderers.register(MMEntities.CHUD.get(), ChudRenderer::new);
        EntityRenderers.register(MMEntities.SACABAMBASPIS.get(), SacaRenderer::new);
        EntityRenderers.register(MMEntities.ARANDASPIS.get(), ArandaspisRenderer::new);
        EntityRenderers.register(MMEntities.FALCATUS.get(), FalcatusRenderer::new);
        EntityRenderers.register(MMEntities.EOLACTORIA.get(), EolactoriaRenderer::new);
        EntityRenderers.register(MMEntities.SLOVENICUS.get(), SlovenicusRenderer::new);

        BlockEntityRenderers.register(MMBlockEntities.PEDESTAL_ENTITY.get(), PedestalRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.ALTAR_ENTITY.get(), AltarRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.CHARNIA_BLOCK_ENTITY.get(), CharniaRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.PALEO_TABLE_ENTITY.get(), PaleoTableRenderer::new);

        EntityRenderers.register(MMEntities.FRACTURE.get(), FractureRenderer::new);
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

    public void openBookGUI() {
        Minecraft.getInstance().setScreen(new PaleonomiconScreen());
    }


    public void openBookGUI(String link) {
        Minecraft.getInstance().setScreen(new PaleonomiconScreen(link));
    }

    @Override
    public Object getArmorRenderProperties() {
        return new CustomArmorRenderProperties();
    }
}
