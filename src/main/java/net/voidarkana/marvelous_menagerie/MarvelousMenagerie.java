package net.voidarkana.marvelous_menagerie;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.client.screen.MMMenuTypes;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.event.ModEvents;
import net.voidarkana.marvelous_menagerie.util.ClientProxy;
import net.voidarkana.marvelous_menagerie.util.CommonProxy;
import net.voidarkana.marvelous_menagerie.util.network.MMNetworkHandler;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MarvelousMenagerie.MODID)
public class MarvelousMenagerie
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "marvelous_menagerie";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static CommonProxy PROXY = (CommonProxy) DistExecutor.runForDist(() -> {
        return ClientProxy::new;
    }, () -> {
        return CommonProxy::new;
    });

    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public MarvelousMenagerie()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setupClient);

        MMEntities.register(modEventBus);

        MMBlocks.register(modEventBus);
        MMBlockEntities.register(modEventBus);
        MMItems.register(modEventBus);

        MMMenuTypes.register(modEventBus);
        MMCreativeTab.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        PROXY.init();

        eventBus.register(new ModEvents());

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        MMNetworkHandler.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
