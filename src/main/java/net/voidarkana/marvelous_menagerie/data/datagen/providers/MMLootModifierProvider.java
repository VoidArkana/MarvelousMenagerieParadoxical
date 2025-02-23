package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

import java.util.List;

public class MMLootModifierProvider extends GlobalLootModifierProvider {

    public MMLootModifierProvider(PackOutput output) {
        super(output, MarvelousMenagerie.MODID);
    }

    @Override
    protected void start() {
        add("seeds_from_sniffing", new AddItemsModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/sniffer_digging")).build()
        }, List.of(MMBlocks.SIGILLARIA_SAPLING.get().asItem(), MMBlocks.PROTOTAXITES.get().asItem(), MMBlocks.COOKSONIA.get().asItem())));
    }
}
