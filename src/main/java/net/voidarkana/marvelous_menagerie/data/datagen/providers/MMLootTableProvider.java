package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.voidarkana.marvelous_menagerie.data.datagen.providers.loot.MMBlockLootTableProvider;

import java.util.List;
import java.util.Set;

public class MMLootTableProvider {
    public static LootTableProvider create(PackOutput output){
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(MMBlockLootTableProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
