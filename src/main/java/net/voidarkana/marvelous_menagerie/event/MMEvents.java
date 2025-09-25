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
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Beholder;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;
import net.voidarkana.marvelous_menagerie.common.entity.animal.*;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;
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
        event.put(MMEntities.ANOMALOCARIS.get(), Anomalocaris.createAttributes().build());
        event.put(MMEntities.PIKAIA.get(), Pikaia.createAttributes().build());
        event.put(MMEntities.ROACH.get(), Apthoroblattina.createAttributes().build());
        event.put(MMEntities.BEHOLDER.get(), Beholder.createAttributes().build());
        event.put(MMEntities.DAWN_HORSE.get(), DawnHorse.createAttributes().build());
        event.put(MMEntities.ZOMBIE_DAWN_HORSE.get(), DawnHorse.createAttributes().build());
        event.put(MMEntities.SKELETON_DAWN_HORSE.get(), DawnHorse.createAttributes().build());
        event.put(MMEntities.HALLUCIGENIA.get(), Hallucigenia.createAttributes().build());
        event.put(MMEntities.TRILOBITE.get(), Trilobite.createAttributes().build());
        event.put(MMEntities.LEPTICTIDIUM.get(), Leptictidium.createAttributes().build());
        event.put(MMEntities.THYLACINE.get(), Thylacine.createAttributes().build());

//        event.put(MMEntities.DODO.get(), DodoEntity.createAttributes().build());
//        event.put(MMEntities.ELEPHANT_BIRD.get(), ElephantBirdEntity.createAttributes().build());
//        event.put(MMEntities.STELLER_SEA_COW.get(), StellerEntity.createAttributes().build());
//        event.put(MMEntities.OPHTHALMO.get(), OphthalmoEntity.createAttributes().build());
//        event.put(MMEntities.JOSEPHO.get(), JosephoEntity.createAttributes().build());

        event.put(MMEntities.FRACTURE.get(), LivingEntity.createLivingAttributes().build());
        event.put(MMEntities.RIFT.get(), RiftEntity.createAttributes().build());
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

        event.addListener(MarvelousMenagerie.PROXY.getPaleonomiconIndexManager());
    }
}
