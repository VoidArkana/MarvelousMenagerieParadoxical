package net.voidarkana.marvelous_menagerie.event.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.base.MMEntityAccess;
import net.voidarkana.marvelous_menagerie.util.ClientProxy;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {

    @OnlyIn(Dist.CLIENT)
    static float clientTicks = 0;

    @OnlyIn(Dist.CLIENT)
    public static float getClientTicks() {
        return clientTicks;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRenderStages(final RenderLevelStageEvent event) {
        //if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) onRenderLast();
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRIPWIRE_BLOCKS)
            clientTicks += event.getPartialTick();
    }

    @SubscribeEvent
    public static void preRenderLiving(RenderLivingEvent.Pre event) {
        if (ClientProxy.blockedEntityRenders.contains(event.getEntity().getUUID())) {
            if (!MarvelousMenagerie.PROXY.isFirstPersonPlayer(event.getEntity())){
                MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
                event.setCanceled(true);
            }
            ClientProxy.blockedEntityRenders.remove(event.getEntity().getUUID());
        }
    }

    @SubscribeEvent
    public static void livingEntityRenderer(RenderLivingEvent<LivingEntity, EntityModel<LivingEntity>> event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof MMEntityAccess entityAccess){
            float partialTicks = event.getPartialTick();
            if (entityAccess.getSummonedProgress(partialTicks)>0){
                float scale = 1-(Math.max(0, entityAccess.getSummonedProgress(partialTicks)));
                event.getPoseStack().scale(scale, scale, scale);
            }
        }
    }
}
