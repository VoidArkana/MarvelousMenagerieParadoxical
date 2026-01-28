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
import net.voidarkana.marvelous_menagerie.client.model.block.HerpetogasterModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.FractureModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.RiftModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.BeholderModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.ChudModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.MoltenModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris.AnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris.BabyAnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.ArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.BabyArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BabyBorealopeltaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BorealoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.DawnHorseModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.SkeletonDawnHorseModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.ZombieDawnHorseModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.BabyDiplocaulusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.DiplocaulusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo.BabyDodoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo.DodoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.BabyEleBirdModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.EleBirdModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.BabyFlubberModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.FlubberModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.BabyJosephoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.JosephoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.BabyOphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia.BabyPikaiaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia.PikaiaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.BabySacaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.SacaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow.BabyStellerModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow.StellerModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.BabyThylacineModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteFatModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteIttyModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteMidModel;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.client.particles.custom.ItemMorphParticle;
import net.voidarkana.marvelous_menagerie.client.particles.custom.RiftParticle;
import net.voidarkana.marvelous_menagerie.client.particles.custom.TimeShardParticle;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MMModelLayers.SKULL_LAYER, AltarSkullModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.CHARNIA_LAYER, CharniaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.HERPETOGASTER_LAYER, HerpetogasterModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.FRACTURE_LAYER, FractureModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.FRACTURE_EMMISSIVE, FractureModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.RIFT_LAYER, RiftModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.CHUD_LAYER, ChudModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.MOLTEN_LAYER, MoltenModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BEHOLDER_LAYER, BeholderModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.SACA_LAYER, SacaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_SACA_LAYER, BabySacaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ARANDASPIS_LAYER, ArandaspisModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ARANDASPIS_LAYER, BabyArandaspisModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.FALCATUS_LAYER, FalcatusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.EOLACTORIA_LAYER, EolactoriaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.SLOVENICUS_LAYER, SlovenicusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ANOMALOCARIS_LAYER, AnomalocarisModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ANOMALOCARIS_LAYER, BabyAnomalocarisModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.PIKAIA_LAYER, PikaiaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_PIKAIA_LAYER, BabyPikaiaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ROACH_LAYER, RoachModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.DAWNHORSE_LAYER, DawnHorseModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.SKELETON_DAWNHORSE_LAYER, SkeletonDawnHorseModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ZOMBIE_DAWNHORSE_LAYER, ZombieDawnHorseModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.HALLU_LAYER, HallucigeniaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.TRILO_FAT_LAYER, TrilobiteFatModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_FAT_LAYER_SECOND, TrilobiteFatModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_FAT_LAYER_HIGHLIGHT, TrilobiteFatModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_ITTY_LAYER, TrilobiteIttyModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_ITTY_LAYER_SECOND, TrilobiteIttyModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_ITTY_LAYER_HIGHLIGHT, TrilobiteIttyModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_MID_LAYER, TrilobiteMidModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_MID_LAYER_SECOND, TrilobiteMidModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.TRILO_MID_LAYER_HIGHLIGHT, TrilobiteMidModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.LEPTI_LAYER, LeptiModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.THYLACINE_LAYER, ThylacineModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_THYLACINE_LAYER, BabyThylacineModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.DODO_LAYER, DodoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_DODO_LAYER, BabyDodoModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ELEBIRD_LAYER, EleBirdModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ELEBIRD_LAYER, BabyEleBirdModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.DOEDICURUS_LAYER, DoedicurusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.JOSEPHO_LAYER, JosephoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_JOSEPHO_LAYER, BabyJosephoModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.STELLER_LAYER, StellerModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_STELLER_LAYER, BabyStellerModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.OPHTHALMO_LAYER, OphthalmoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.OPHTHALMO_PATTERN_LAYER, OphthalmoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.OPHTHALMO_SADDLE_LAYER, OphthalmoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.OPHTHALMO_ARMOR_LAYER, OphthalmoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_OPHTHALMO_LAYER, BabyOphthalmoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_OPHTHALMO_PATTERN_LAYER, BabyOphthalmoModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.BOREALOPELTA_LAYER, BorealoModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_BOREALOPELTA_LAYER, BabyBorealopeltaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.DIPLOCAULUS_LAYER, DiplocaulusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_DIPLOCAULUS_LAYER, BabyDiplocaulusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.FLUBBER_LAYER, FlubberModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_FLUBBER_LAYER, BabyFlubberModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.GOGGLES_LAYER, AnomalousGogglesModel::createArmorLayer);

        event.registerLayerDefinition(MMModelLayers.SIGILLARIA_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.SIGILLARIA_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(MMModelLayers.PROTOTAXITES_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.PROTOTAXITES_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(MMModelLayers.CALAMITES_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.CALAMITES_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
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
