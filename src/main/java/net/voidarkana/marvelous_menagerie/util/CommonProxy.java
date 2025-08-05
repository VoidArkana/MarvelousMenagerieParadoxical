package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.entityentrymanager.*;
import net.voidarkana.marvelous_menagerie.util.network.indexes.PaleogeneIndexSyncPacket;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    private final AbominationEntryManager abominationEntryManager = new AbominationEntryManager();

    private final EarlyPaleoEntryManager earlyPaleoEntryManager = new EarlyPaleoEntryManager();
    private final CarboniferousEntryManager carboniferousEntryManager = new CarboniferousEntryManager();
    private final PermianEntryManager permianEntryManager = new PermianEntryManager();

    private final TriassicEntryManager triassicEntryManager = new TriassicEntryManager();
    private final JurassicEntryManager jurassicEntryManager = new JurassicEntryManager();
    private final CretaceousEntryManager cretaceousEntryManager = new CretaceousEntryManager();

    private final PaleogeneEntryManager paleogeneEntryManager = new PaleogeneEntryManager();
    private final NeogeneEntryManager neogeneEntryManager = new NeogeneEntryManager();
    private final QuaternaryEntryManager quaternaryEntryManager = new QuaternaryEntryManager();

    public CommonProxy() {
    }

    public void init() {
    }

    public void clientInit() {
    }

    public Level getWorld() {
        return ServerLifecycleHooks.getCurrentServer().overworld();
    }

    public void openToolkitGUI(Player player, BlockPos clicked) {
    }

    public void openBookGUI() {
    }

    public void openBookGUI(String link) {
    }

    public Object getArmorRenderProperties() {
        return null;
    }

    public AbominationEntryManager getAbominationEntryManager(){
        return abominationEntryManager;
    }

    public EarlyPaleoEntryManager getEarlyPaleoEntryManager(){
        return earlyPaleoEntryManager;
    }
    public CarboniferousEntryManager getCarboniferousEntryManager(){
        return carboniferousEntryManager;
    }
    public PermianEntryManager getPermianEntryManager(){
        return permianEntryManager;
    }

    public TriassicEntryManager getTriassicEntryManager(){
        return triassicEntryManager;
    }
    public JurassicEntryManager getJurassicEntryManager(){
        return jurassicEntryManager;
    }
    public CretaceousEntryManager getCretaceousEntryManager(){
        return cretaceousEntryManager;
    }

    public PaleogeneEntryManager getPaleogeneEntryManager(){
        return paleogeneEntryManager;
    }
    public NeogeneEntryManager getNeogeneEntryManager(){
        return neogeneEntryManager;
    }
    public QuaternaryEntryManager getQuaternaryEntryManager(){
        return quaternaryEntryManager;
    }
}
