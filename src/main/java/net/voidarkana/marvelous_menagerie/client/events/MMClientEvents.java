package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.enchantment.MMEnchantmentsClass;
import net.voidarkana.marvelous_menagerie.common.item.custom.AnomalousGogglesItem;
import net.voidarkana.marvelous_menagerie.util.ClientProxy;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class MMClientEvents {

    private static final ResourceLocation WOBBLE = new ResourceLocation("shaders/post/wobble.json");
    private static final ResourceLocation DECONVERGE = new ResourceLocation("shaders/post/deconverge.json");

    @SubscribeEvent
    public void postRenderStage(RenderLevelStageEvent event) {
        Entity player = Minecraft.getInstance().getCameraEntity();

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {

            GameRenderer renderer = Minecraft.getInstance().gameRenderer;

            if (player instanceof LivingEntity afflicted && afflicted.hasEffect(MMEffects.HALLUCINATING.get())) {
                if (CommonConfig.HALLU_FLASH.get()){
                    if (renderer.currentEffect() == null || !WOBBLE.toString().equals(renderer.currentEffect().getName())) {
                        attemptLoadShader(WOBBLE);
                    }
                }else{
                    if (renderer.currentEffect() == null || !DECONVERGE.toString().equals(renderer.currentEffect().getName())) {
                        attemptLoadShader(DECONVERGE);
                    }
                }


            } else if (renderer.currentEffect() != null && (DECONVERGE.toString().equals(renderer.currentEffect().getName()) ||
                    WOBBLE.toString().equals(renderer.currentEffect().getName())) ) {
                renderer.checkEntityPostEffect(null);
            }


        }
    }

    private static void attemptLoadShader(ResourceLocation resourceLocation) {
        GameRenderer renderer = Minecraft.getInstance().gameRenderer;
        if (ClientProxy.shaderLoadAttemptCooldown <= 0) {
            renderer.loadEffect(resourceLocation);
            if (renderer.currentEffect() == null) {
                ClientProxy.shaderLoadAttemptCooldown = 12000;
                System.out.println("Could not load the shader");
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            if (ClientProxy.shaderLoadAttemptCooldown > 0) {
                ClientProxy.shaderLoadAttemptCooldown--;
            }

        }
    }

    @SubscribeEvent
    public static void getFogDensity(ViewportEvent.RenderFog event){
        Camera camera = event.getCamera();
        Level level = Minecraft.getInstance().level;
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);

        if (camera.getPosition().y() >= blockPos.getY() + fluidState.getHeight(level, blockPos))
            return;

        Fluid fluid = fluidState.getType();
        Entity player = camera.getEntity();

        if (player.isSpectator())
            return;

        ItemStack helmet = AnomalousGogglesItem.getWornItem(player);
        if (!helmet.isEmpty() && helmet.getItem() instanceof AnomalousGogglesItem){
            if ((fluid == Fluids.LAVA || fluid == Fluids.FLOWING_LAVA) && helmet.getEnchantmentLevel(MMEnchantmentsClass.INFERNAL_VISION.get())>0){
                event.scaleFarPlaneDistance(10f);
                event.setCanceled(true);
                return;
            }else if ((fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER)){
                event.scaleFarPlaneDistance(10f);
                event.setCanceled(true);
                return;
            }
        }
    }

}
