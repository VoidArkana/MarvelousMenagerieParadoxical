package net.voidarkana.marvelous_menagerie.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;
import net.voidarkana.marvelous_menagerie.common.entity.animal.*;
import net.voidarkana.marvelous_menagerie.data.codec.BookEntityManager;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.*;
import net.voidarkana.marvelous_menagerie.data.codec.FossilCleaningManager;
import net.voidarkana.marvelous_menagerie.data.codec.PikaiaCoralManager;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){

        event.put(MMEntities.CHUD.get(), Chud.createAttributes().build());
        event.put(MMEntities.SACABAMBASPIS.get(), Sacabambaspis.createAttributes().build());
        event.put(MMEntities.ARANDASPIS.get(), Arandaspis.createAttributes().build());
        event.put(MMEntities.FALCATUS.get(), Falcatus.createAttributes().build());
        event.put(MMEntities.EOLACTORIA.get(), Eolactoria.createAttributes().build());
        event.put(MMEntities.SLOVENICUS.get(), Slovenicus.createAttributes().build());

//        event.put(MMEntities.DODO.get(), DodoEntity.createAttributes().build());
//        event.put(MMEntities.THYLACINE.get(), ThylacineEntity.createAttributes().build());
//        event.put(MMEntities.ELEPHANT_BIRD.get(), ElephantBirdEntity.createAttributes().build());
//        event.put(MMEntities.STELLER_SEA_COW.get(), StellerEntity.createAttributes().build());
//        event.put(MMEntities.BABY_STELLER_SEA_COW.get(), BabyStellerEntity.createAttributes().build());
//        event.put(MMEntities.SIGILLARIA_SAPLING_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(MMEntities.COOKSONIA_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(MMEntities.TRILOBITE.get(), TrilobiteEntity.createAttributes().build());
//        event.put(MMEntities.PROTOTAXITES_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(MMEntities.DICKINSONIA_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(MMEntities.ANOMALOCARIS.get(), AnomalocarisEntity.createAttributes().build());
//        event.put(MMEntities.BABY_ANOMALOCARIS.get(), BabyAnomalocarisEntity.createAttributes().build());
//        event.put(MMEntities.OPHTHALMO.get(), OphthalmoEntity.createAttributes().build());
//        event.put(MMEntities.BABY_OPHTHALMO.get(), BabyOphthalmoEntity.createAttributes().build());
//        event.put(MMEntities.JOSEPHO.get(), JosephoEntity.createAttributes().build());
//        event.put(MMEntities.PIKAIA.get(), PikaiaEntity.createAttributes().build());
//        event.put(MMEntities.WIWAXIA_ENTITY.get(), PlantEntity.createAttributes().build());
//        event.put(MMEntities.HALLUCIGENIA.get(), HallucigeniaEntity.createAttributes().build());
//        event.put(MMEntities.CHARNIA_ENTITY.get(), PlantEntity.createAttributes().build());

        event.put(MMEntities.FRACTURE.get(), LivingEntity.createLivingAttributes().build());
    }

    @SubscribeEvent
    public void setTargetEvent(LivingChangeTargetEvent event){

        if (event.getNewTarget() != null){
            if (event.getNewTarget().hasEffect(MMEffects.PACIFIED.get())) {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public void register(AddReloadListenerEvent event) {
        event.addListener(new PikaiaCoralManager());
        event.addListener(new RitualManager());
        event.addListener(new FossilCleaningManager());
        event.addListener(new BookEntityManager());

        event.addListener(new AbominationEntryManager());
        event.addListener(new EarlyPaleoEntryManager());
        event.addListener(new CarboniferousEntryManager());
        event.addListener(new PermianEntryManager());
        event.addListener(new TriassicEntryManager());
        event.addListener(new JurassicEntryManager());
        event.addListener(new CretaceousEntryManager());
        event.addListener(new PaleogeneEntryManager());
        event.addListener(new NeogeneEntryManager());
        event.addListener(new QuaternaryEntryManager());
    }

}
