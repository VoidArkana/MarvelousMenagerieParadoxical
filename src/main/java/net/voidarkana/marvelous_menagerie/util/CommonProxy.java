package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.PaleonomiconIndexManager;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonProxy {

    private final PaleonomiconIndexManager paleonomiconIndexManager = new PaleonomiconIndexManager();

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

    public PaleonomiconIndexManager getPaleonomiconIndexManager(){
        return paleonomiconIndexManager;
    }

    public void blockRenderingEntity(UUID id) {
    }

    public void releaseRenderingEntity(UUID id) {
    }

    public boolean isFirstPersonPlayer(Entity entity) {
        return false;
    }

}
