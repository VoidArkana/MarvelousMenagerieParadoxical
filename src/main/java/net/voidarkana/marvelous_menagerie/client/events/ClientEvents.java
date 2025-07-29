package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.armor.AnomalousGogglesModel;
import net.voidarkana.marvelous_menagerie.client.model.block.AltarSkullModel;
import net.voidarkana.marvelous_menagerie.client.model.block.CharniaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.FractureModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.RiftModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.BeholderModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.ChudModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.client.particles.custom.ItemMorphParticle;
import net.voidarkana.marvelous_menagerie.client.particles.custom.RiftParticle;
import net.voidarkana.marvelous_menagerie.client.particles.custom.TimeShardParticle;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MMModelLayers.SKULL_LAYER, AltarSkullModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.CHARNIA_LAYER, CharniaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FRACTURE_LAYER, FractureModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FRACTURE_EMMISSIVE, FractureModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.RIFT_LAYER, RiftModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.CHUD_LAYER, ChudModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SACA_LAYER, SacaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ARANDASPIS_LAYER, ArandaspisModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FALCATUS_LAYER, FalcatusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.EOLACTORIA_LAYER, EolactoriaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SLOVENICUS_LAYER, SlovenicusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ANOMALOCARIS_LAYER, AnomalocarisModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.PIKAIA_LAYER, PikaiaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ROACH_LAYER, RoachModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BEHOLDER_LAYER, BeholderModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.DAWNHORSE_LAYER, DawnHorseModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SKELETON_DAWNHORSE_LAYER, SkeletonDawnHorseModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ZOMBIE_DAWNHORSE_LAYER, ZombieDawnHorseModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.HALLU_LAYER, HallucigeniaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.GOGGLES_LAYER, AnomalousGogglesModel::createArmorLayer);

        event.registerLayerDefinition(MMModelLayers.BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MMParticles.TIME_SHARD.get(), TimeShardParticle.Provider::new);
        event.registerSpriteSet(MMParticles.RIFT.get(), RiftParticle.Provider::new);
        event.registerSpriteSet(MMParticles.ITEM_MORPH.get(), ItemMorphParticle.Provider::new);
    }

    public static ShaderInstance GLOWING_SHADER, SEPIA_SHADER;

    public static ShaderInstance getGlowingShader() {
        return GLOWING_SHADER;
    }

    @Nullable
    public static ShaderInstance getSepiaShader() {
        return SEPIA_SHADER;
    }

    public static void setRenderTypeSepiaShader(ShaderInstance instance) {
        SEPIA_SHADER = instance;
    }

    public static void setRenderTypeGlowingShader(ShaderInstance instance) {
        GLOWING_SHADER = instance;
    }

}
