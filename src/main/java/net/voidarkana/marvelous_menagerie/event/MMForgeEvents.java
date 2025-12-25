package net.voidarkana.marvelous_menagerie.event;

import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MMForgeEvents {

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        MarvelousMenagerie.PROXY.getPaleonomiconIndexManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getRitualManager().onDatapackSync(event.getPlayer());
    }
}
