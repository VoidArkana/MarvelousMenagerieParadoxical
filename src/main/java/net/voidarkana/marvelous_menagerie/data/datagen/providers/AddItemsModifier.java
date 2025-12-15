package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraftforge.common.loot.LootModifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AddItemsModifier extends LootModifier {

    public static final Supplier<Codec<AddItemsModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ExtraCodecs.nonEmptyList(ForgeRegistries.ITEMS.getCodec().listOf())
            .fieldOf("item").forGetter(m -> m.items))
            .and(Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance))
            .apply(inst, AddItemsModifier::new)));

    private final List<Item> items;
    private final float chance;

    public AddItemsModifier(LootItemCondition[] conditionsIn, List<Item> items, float chance) {
        super(conditionsIn);
        this.items = items;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();

        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        if (context.getRandom().nextFloat() < chance){
            generatedLoot.clear();
            items.forEach(item -> generatedLoot.add(item.getDefaultInstance()));
        }

        newLoot.add(Util.getRandom(generatedLoot, context.getRandom()));
        return newLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
