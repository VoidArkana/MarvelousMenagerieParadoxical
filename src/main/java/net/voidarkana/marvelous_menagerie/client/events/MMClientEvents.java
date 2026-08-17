package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.armor.*;
import net.voidarkana.marvelous_menagerie.client.model.block.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anurognathus.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.atrociraptor.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.kelenken.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.lystro.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.myotragus.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.tiktaalik.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia.*;
import net.voidarkana.marvelous_menagerie.client.model.entity.misc.FractureModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.misc.RiftModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.misc.WhispererModel;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.client.particles.custom.*;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import org.jetbrains.annotations.Nullable;

import java.awt.event.KeyEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MMClientEvents {

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
        event.registerLayerDefinition(MMModelLayers.BABY_HALLU_LAYER, BabyHallucigeniaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.HALLU_EYES_LAYER, HallucigeniaModel::createEyesLayer);

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

        event.registerLayerDefinition(MMModelLayers.ELEBIRD_LAYER, ElephantBirdModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ELEBIRD_LAYER, BabyElephantBirdModel::createBodyLayer);

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


        event.registerLayerDefinition(MMModelLayers.MYOTRAGUS_LAYER, MyotragusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_MYOTRAGUS_LAYER, BabyMyotragusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.TIKTAALIK_LAYER, TiktaalikModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_TIKTAALIK_LAYER, BabyTiktaalikModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.LYSTRO_LAYER, LystroModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_LYSTRO_LAYER, BabyLystroModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.INOSTRANCEVIA_LAYER, InostranceviaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.INOSTRANCEVIA_PATTERN_LAYER, InostranceviaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_INOSTRANCEVIA_LAYER, BabyInostranceviaModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_INOSTRANCEVIA_PATTERN_LAYER, BabyInostranceviaModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ANUROGNATHUS_LAYER, AnurognathusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ANUROGNATHUS_LAYER, BabyAnurognathusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.ANUROGNATHUS_PATTERN_LAYER, AnurognathusModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ANUROGNATHUS_PATTERN_LAYER, BabyAnurognathusModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.KELENKEN_LAYER, TerrorBirdModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_KELENKEN_LAYER, BabyTerrorBirdModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.ATROCIRAPTOR_LAYER, AtrociraptorModel::createBodyLayer);
        event.registerLayerDefinition(MMModelLayers.BABY_ATROCIRAPTOR_LAYER, BabyAtrociraptorModel::createBodyLayer);

        event.registerLayerDefinition(MMModelLayers.WHISPERER_LAYER, WhispererModel::createBodyLayer);


        event.registerLayerDefinition(MMModelLayers.GOGGLES_LAYER, AnomalousGogglesModel::createArmorLayer);

        event.registerLayerDefinition(MMModelLayers.SIGILLARIA_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.SIGILLARIA_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(MMModelLayers.PROTOTAXITES_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.PROTOTAXITES_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(MMModelLayers.CALAMITES_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.CALAMITES_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        event.registerLayerDefinition(MMModelLayers.ARAUCARIOXYLON_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(MMModelLayers.ARAUCARIOXYLON_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MMParticles.TIME_SHARD.get(), TimeShardParticle.Provider::new);
        event.registerSpriteSet(MMParticles.RIFT.get(), RiftParticle.Provider::new);
        event.registerSpriteSet(MMParticles.ITEM_MORPH.get(), ItemMorphParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        event.getBlockColors().register((state, level, pos, tintIndex) ->
                FoliageColor.getEvergreenColor(), MMBlocks.ARAUCARIOXYLON_LEAVES.get());

        event.getBlockColors().register((state, level, pos, tintIndex) -> {
            return level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor();
        }, MMBlocks.FERN_SPROUTS.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

        event.getItemColors().register((stack, tintIndex) -> {
                    BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                    return FastColor.ABGR32.opaque(event.getBlockColors().getColor(blockstate, null, null, tintIndex));},
                MMBlocks.ARAUCARIOXYLON_LEAVES.get(),
                MMBlocks.FERN_SPROUTS.get());
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
