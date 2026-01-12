package net.voidarkana.marvelous_menagerie.event;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.manager.RitualManager;
import net.voidarkana.marvelous_menagerie.util.network.message.FossilRecipeS2C;
import net.voidarkana.marvelous_menagerie.data.manager.FossilCleaningManager;
import net.voidarkana.marvelous_menagerie.data.manager.codec.WeightedItemCodec;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.message.SyncSummoningRituals;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    @SubscribeEvent
    public static void onRegisterReloadListeners(ServerStartedEvent event) {
        try{
            FossilCleaningManager.populateRecipeMap(event.getServer().getLevel(Level.OVERWORLD));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void synchDataPack(OnDatapackSyncEvent event){


        ServerPlayer player = event.getPlayer();
        List<ServerPlayer> playerList = event.getPlayerList().getPlayers();
        Map<Item, List<WeightedItemCodec>> analyzerRecipes = FossilCleaningManager.getRecipes();
        BiMap<ResourceLocation, RitualManager.RitualProcessData> registryMap = HashBiMap.create();

        if(player != null){
            MarvelousMenagerie.PROXY.getPaleonomiconIndexManager().onDatapackSync(event.getPlayer());

            MMMessages.sendToPlayer(new FossilRecipeS2C(analyzerRecipes), player);
            MMMessages.sendToPlayer(new SyncSummoningRituals(registryMap), player);

            MarvelousMenagerie.PROXY.getRitualManager().onDatapackSync(event.getPlayer());
        }

        if(playerList != null && !playerList.isEmpty()){
            for(ServerPlayer player1 : playerList){
                ServerLevel serverLevel = (ServerLevel) player1.level();
                if(FossilCleaningManager.getRecipes().isEmpty()){
                    FossilCleaningManager.populateRecipeMap(serverLevel);
                }
                Map<Item, List<WeightedItemCodec>> analyzerRecipesReload = FossilCleaningManager.getRecipes();

                MMMessages.sendToPlayer(new FossilRecipeS2C(analyzerRecipesReload), player1);
            }
        }
    }


}
