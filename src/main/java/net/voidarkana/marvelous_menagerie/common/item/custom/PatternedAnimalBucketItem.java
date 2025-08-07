package net.voidarkana.marvelous_menagerie.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class PatternedAnimalBucketItem extends FishBucketItem {
    public PatternedAnimalBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item, boolean hasTooltip, Item.Properties builder) {
        super(entityType, fluid, item, hasTooltip, builder);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (getFishType() == MMEntities.TRILOBITE.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("Model", 3)) {
                int i = compoundtag.getInt("Model");
                int j = compoundtag.getInt("BaseColor");
                int k = compoundtag.getInt("SecondColor");
                int l = compoundtag.getInt("HighlightColor");
                boolean hasHighlight = compoundtag.getBoolean("HasHighlight");

                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                String model = "translatable.marvelous_menagerie.trilo_model." + Trilobite.getModelName(i);
                String base = "translatable.marvelous_menagerie.trilo_base_color." + Trilobite.getColorName(j);
                String second = "translatable.marvelous_menagerie.trilo_second_color." + Trilobite.getColorName(k);
                String highlight = "translatable.marvelous_menagerie.trilo_highlight_color." + Trilobite.getColorName(l);


                pTooltipComponents.add(Component.translatable(model).withStyle(achatformatting));

                MutableComponent mutablecomponent = Component.translatable(base);

                mutablecomponent.withStyle(achatformatting);
                pTooltipComponents.add(mutablecomponent);

                MutableComponent mutablecomponent2 = Component.translatable(second);

                mutablecomponent2.withStyle(achatformatting);
                pTooltipComponents.add(mutablecomponent2);


                if (hasHighlight){
                    MutableComponent mutablecomponent3 = Component.translatable(highlight);
                    mutablecomponent3.withStyle(achatformatting);
                    pTooltipComponents.add(mutablecomponent3);
                }
            }
        }

//        if (getFishType() == ModEntities.BABY_OPHTHALMO.get()) {
//            CompoundTag compoundtag = pStack.getTag();
//            if (compoundtag != null && compoundtag.contains("BaseColor", 3)) {
//                int i = compoundtag.getInt("BaseColor");
//                int j = compoundtag.getInt("Pattern");
//
//                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};
//
//                String model = "translatable.marvelous_menagerie.ophthalmo.base" + BabyOphthalmoEntity.getColorName(i);
//
//                pTooltipComponents.add(Component.translatable(model).withStyle(achatformatting));
//
//                if (j!=0){
//                    String base = "translatable.marvelous_menagerie.ophthalmo.pattern" + BabyOphthalmoEntity.getPatternName(j);
//                    MutableComponent mutablecomponent = Component.translatable(base);
//                    mutablecomponent.withStyle(achatformatting);
//                    pTooltipComponents.add(mutablecomponent);
//                }
//            }
//        }


    }
}
