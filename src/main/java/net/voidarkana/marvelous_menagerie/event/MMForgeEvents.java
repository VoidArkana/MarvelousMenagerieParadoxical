package net.voidarkana.marvelous_menagerie.event;

import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MMForgeEvents {

    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        MarvelousMenagerie.PROXY.getAbominationEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getEarlyPaleoEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getCarboniferousEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getPermianEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getTriassicEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getJurassicEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getCretaceousEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getNeogeneEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getPaleogeneEntryManager().onDatapackSync(event.getPlayer());
        MarvelousMenagerie.PROXY.getQuaternaryEntryManager().onDatapackSync(event.getPlayer());
    }
}
