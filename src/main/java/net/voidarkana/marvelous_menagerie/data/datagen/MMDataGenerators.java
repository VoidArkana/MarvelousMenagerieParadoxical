package net.voidarkana.marvelous_menagerie.data.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.datagen.providers.*;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new MMRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), MMLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new MMBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new MMItemModelProvider(packOutput, existingFileHelper));

        MMBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new MMBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(),new MMItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(true, new MMEntityTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(true, new MMBiomeTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new MMPOITagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new  MMLootModifierProvider(packOutput));
    }
}
