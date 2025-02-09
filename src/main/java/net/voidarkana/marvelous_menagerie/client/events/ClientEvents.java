package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.block.AltarSkullModel;
import net.voidarkana.marvelous_menagerie.client.model.block.CharniaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.FractureModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.ChudModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.client.particles.custom.RiftParticle;
import net.voidarkana.marvelous_menagerie.client.particles.custom.TimeShardParticle;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MMModelLayers.SKULL_LAYER, AltarSkullModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.CHARNIA_LAYER, CharniaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FRACTURE_LAYER, FractureModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FRACTURE_EMMISSIVE, FractureModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.CHUD_LAYER, ChudModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SACA_LAYER, SacaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ARANDASPIS_LAYER, ArandaspisModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FALCATUS_LAYER, FalcatusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.EOLACTORIA_LAYER, EolactoriaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SLOVENICUS_LAYER, SlovenicusModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MMParticles.TIME_SHARD.get(), TimeShardParticle.Provider::new);
        event.registerSpriteSet(MMParticles.RIFT.get(), RiftParticle.Provider::new);
    }
}
