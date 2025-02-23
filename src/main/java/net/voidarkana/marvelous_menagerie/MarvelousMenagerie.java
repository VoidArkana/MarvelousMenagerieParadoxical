package net.voidarkana.marvelous_menagerie;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.client.screen.MMMenuTypes;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.villager.MMVillagerProfessions;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModTrunkPlacerTypes;
import net.voidarkana.marvelous_menagerie.data.datagen.MMLootModifiers;
import net.voidarkana.marvelous_menagerie.event.MMEvents;
import net.voidarkana.marvelous_menagerie.event.ServerEvents;
import net.voidarkana.marvelous_menagerie.util.ClientProxy;
import net.voidarkana.marvelous_menagerie.util.CommonProxy;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import net.voidarkana.marvelous_menagerie.util.network.MMNetworkHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MarvelousMenagerie.MODID)
public class MarvelousMenagerie
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "marvelous_menagerie";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogManager.getLogger();

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

        MMLootModifiers.LOOT_MODIFIERS.register(modEventBus);

        MMEntities.register(modEventBus);
        MMVillagerProfessions.register(modEventBus);
        MMParticles.register(modEventBus);

        MMBlocks.register(modEventBus);
        MMBlockEntities.register(modEventBus);
        MMItems.register(modEventBus);

        MMMenuTypes.register(modEventBus);
        MMCreativeTab.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        PROXY.init();

        eventBus.register(new MMEvents());
        eventBus.register(new ServerEvents());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC,
                "marvelous_menagerie.toml");
        
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        MMNetworkHandler.init();
        MMMessages.register();
        event.enqueueWork(()->{

//            BrewingRecipeRegistry.addRecipe(new ModPotionRecipes(Potions.AWKWARD,
//                    ModItems.HALLUCIGENIC_SLIME.get(), ModPotions.HALLUCIGENIA_EXTRACT.get()));
//
//            MarvelousEntityPlacement.entityPlacement();

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MMBlocks.SIGILLARIA_SAPLING.getId(), MMBlocks.POTTED_SIGILLARIA_SAPLING);
            ComposterBlock.COMPOSTABLES.put(MMBlocks.SIGILLARIA_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(MMBlocks.SIGILLARIA_LEAVES.get().asItem(), 0.4F);

            ComposterBlock.COMPOSTABLES.put(MMBlocks.COOKSONIA.get().asItem(), 0.4F);

            ComposterBlock.COMPOSTABLES.put(MMBlocks.PROTOTAXITES.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(MMBlocks.PROTOTAXITES_BLOCK.get().asItem(), 0.8F);
        });
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
