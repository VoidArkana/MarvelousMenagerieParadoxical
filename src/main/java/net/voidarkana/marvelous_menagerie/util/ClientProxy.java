package net.voidarkana.marvelous_menagerie.util;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.events.ClientEvents;
import net.voidarkana.marvelous_menagerie.client.events.MMClientEvents;
import net.voidarkana.marvelous_menagerie.client.renderer.block.AltarRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.CharniaRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PaleoTableRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.block.PedestalRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination.MoltenRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.misc.FractureRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.misc.MMBoatRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.misc.RiftRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination.BeholderRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination.ChudRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.renderer.item.CustomArmorRenderProperties;
import net.voidarkana.marvelous_menagerie.client.screen.book.PaleonomiconScreen;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, value = {Dist.CLIENT})
public class ClientProxy extends CommonProxy{

    public static List<UUID> blockedEntityRenders = new ArrayList<>();
    public static int shaderLoadAttemptCooldown = 0;

    @Override
    public void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public void clientInit() {
        Sheets.addWoodType(MMWoodTypes.SIGILLARIA);
        Sheets.addWoodType(MMWoodTypes.PROTOTAXITES);
        Sheets.addWoodType(MMWoodTypes.CALAMITES);

        MinecraftForge.EVENT_BUS.register(new MMClientEvents());

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MarvelousMenagerie.CALLBACKS.forEach(Runnable::run);
        MarvelousMenagerie.CALLBACKS.clear();

        bus.addListener(this::registerShaders);

        EntityRenderers.register(MMEntities.CHUD.get(), ChudRenderer::new);
        EntityRenderers.register(MMEntities.SACABAMBASPIS.get(), SacaRenderer::new);
        EntityRenderers.register(MMEntities.ARANDASPIS.get(), ArandaspisRenderer::new);
        EntityRenderers.register(MMEntities.FALCATUS.get(), FalcatusRenderer::new);
        EntityRenderers.register(MMEntities.EOLACTORIA.get(), EolactoriaRenderer::new);
        EntityRenderers.register(MMEntities.SLOVENICUS.get(), SlovenicusRenderer::new);
        EntityRenderers.register(MMEntities.ANOMALOCARIS.get(), AnomalocarisRenderer::new);
        EntityRenderers.register(MMEntities.PIKAIA.get(), PikaiaRenderer::new);
        EntityRenderers.register(MMEntities.ROACH.get(), RoachRenderer::new);
        EntityRenderers.register(MMEntities.BEHOLDER.get(), BeholderRenderer::new);
        EntityRenderers.register(MMEntities.DAWN_HORSE.get(), DawnHorseRenderer::new);
        EntityRenderers.register(MMEntities.SKELETON_DAWN_HORSE.get(), SkeletonDawnHorseRenderer::new);
        EntityRenderers.register(MMEntities.ZOMBIE_DAWN_HORSE.get(), ZombieDawnHorseRenderer::new);
        EntityRenderers.register(MMEntities.HALLUCIGENIA.get(), HallucigeniaRenderer::new);
        EntityRenderers.register(MMEntities.TRILOBITE.get(), TrilobiteRenderer::new);
        EntityRenderers.register(MMEntities.LEPTICTIDIUM.get(), LeptiRenderer::new);
        EntityRenderers.register(MMEntities.THYLACINE.get(), ThylacineRenderer::new);
        EntityRenderers.register(MMEntities.DODO.get(), DodoRenderer::new);
        EntityRenderers.register(MMEntities.ELEPHANT_BIRD.get(), EleBirdRenderer::new);
        EntityRenderers.register(MMEntities.DOEDICURUS.get(), DoedicurusRenderer::new);
        EntityRenderers.register(MMEntities.JOSEPHO.get(), JosephoRenderer::new);
        EntityRenderers.register(MMEntities.STELLER_SEA_COW.get(), StellerRenderer::new);
        EntityRenderers.register(MMEntities.OPHTHALMO.get(), OphthalmoRenderer::new);
        EntityRenderers.register(MMEntities.BOREALOPELTA.get(), BorealopeltaRenderer::new);
        EntityRenderers.register(MMEntities.MOLTEN.get(), MoltenRenderer::new);
        EntityRenderers.register(MMEntities.DIPLOCAULUS.get(), DiplocaulusRenderer::new);;
        EntityRenderers.register(MMEntities.FLUBBER.get(), FlubberRenderer::new);

        BlockEntityRenderers.register(MMBlockEntities.PEDESTAL_ENTITY.get(), PedestalRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.ALTAR_ENTITY.get(), AltarRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.CHARNIA_BLOCK_ENTITY.get(), CharniaRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.PALEO_TABLE_ENTITY.get(), PaleoTableRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(MMBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);

        EntityRenderers.register(MMEntities.FRACTURE.get(), FractureRenderer::new);
        EntityRenderers.register(MMEntities.RIFT.get(), RiftRenderer::new);

        EntityRenderers.register(MMEntities.MM_BOAT.get(), p_174010_ -> new MMBoatRenderer(p_174010_, false));
        EntityRenderers.register(MMEntities.MM_CHEST_BOAT.get(), p_174010_ -> new MMBoatRenderer(p_174010_, true));
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

    private void registerShaders(RegisterShadersEvent event) {
        try {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), new ResourceLocation(MarvelousMenagerie.MOD_ID, "glowing"), DefaultVertexFormat.POSITION_COLOR), ClientEvents::setRenderTypeGlowingShader);
        event.registerShader(new ShaderInstance(event.getResourceProvider(), new ResourceLocation(MarvelousMenagerie.MOD_ID, "sepia"), DefaultVertexFormat.NEW_ENTITY), ClientEvents::setRenderTypeSepiaShader);
            MarvelousMenagerie.LOGGER.info("registered internal shaders");
        } catch (IOException exception) {
            MarvelousMenagerie.LOGGER.error("could not register internal shaders");
            exception.printStackTrace();
        }
    }

    public void blockRenderingEntity(UUID id) {
        blockedEntityRenders.add(id);
    }

    public void releaseRenderingEntity(UUID id) {
        blockedEntityRenders.remove(id);
    }

    public boolean isFirstPersonPlayer(Entity entity) {
        return entity.equals(Minecraft.getInstance().cameraEntity) && Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }
}
