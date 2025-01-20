package net.voidarkana.marvelous_menagerie.event;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.PikaiaCoralManager;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
//        event.put(ModEntities.DODO.get(), DodoEntity.createAttributes().build());
//        event.put(ModEntities.THYLACINE.get(), ThylacineEntity.createAttributes().build());
//        event.put(ModEntities.ELEPHANT_BIRD.get(), ElephantBirdEntity.createAttributes().build());
//        event.put(ModEntities.STELLER_SEA_COW.get(), StellerEntity.createAttributes().build());
//        event.put(ModEntities.BABY_STELLER_SEA_COW.get(), BabyStellerEntity.createAttributes().build());
//        event.put(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(ModEntities.COOKSONIA_ENTITY.get(), PlantEntity.createAttributes().build());
//
//        event.put(ModEntities.TRILOBITE.get(), TrilobiteEntity.createAttributes().build());
//        event.put(ModEntities.SACABAMBASPIS.get(), SacabambaspisEntity.createAttributes().build());
//        event.put(ModEntities.PROTOTAXITES_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(ModEntities.DICKINSONIA_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(ModEntities.ANOMALOCARIS.get(), AnomalocarisEntity.createAttributes().build());
//        event.put(ModEntities.BABY_ANOMALOCARIS.get(), BabyAnomalocarisEntity.createAttributes().build());
//
//        event.put(ModEntities.OPHTHALMO.get(), OphthalmoEntity.createAttributes().build());
//        event.put(ModEntities.BABY_OPHTHALMO.get(), BabyOphthalmoEntity.createAttributes().build());
//
//        event.put(ModEntities.JOSEPHO.get(), JosephoEntity.createAttributes().build());
//
//        event.put(ModEntities.PIKAIA.get(), PikaiaEntity.createAttributes().build());
//
//        event.put(ModEntities.WIWAXIA_ENTITY.get(), PlantEntity.createAttributes().build());
//
//        event.put(ModEntities.ARANDASPIS.get(), ArandaspisEntity.createAttributes().build());
//        event.put(ModEntities.HALLUCIGENIA.get(), HallucigeniaEntity.createAttributes().build());
//
//        event.put(ModEntities.CHARNIA_ENTITY.get(), PlantEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void setTargetEvent(LivingChangeTargetEvent event){

//        if (event.getNewTarget() != null){
//            if (event.getNewTarget().hasEffect(ModEffects.PACIFIED.get())) {
//                event.setCanceled(true);
//                return;
//            }
//        }
    }

    @SubscribeEvent
    public void register(AddReloadListenerEvent event) {
        event.addListener(new PikaiaCoralManager());
        event.addListener(new RitualManager());
    }

}
