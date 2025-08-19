package net.voidarkana.marvelous_menagerie.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
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

                int lgbt = compoundtag.getInt("LGBTVariant");
                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                int i = compoundtag.getInt("Model");
                String model = "translatable.marvelous_menagerie.trilo_model." + Trilobite.getModelName(i);

                MutableComponent modelComponent = Component.translatable("translatable.marvelous_menagerie.model");
                modelComponent.append(":").append(CommonComponents.SPACE).append(Component.translatable(model)).withStyle(achatformatting);

                pTooltipComponents.add(modelComponent);

                if (lgbt==0){
                    int j = compoundtag.getInt("BaseColor");
                    int k = compoundtag.getInt("SecondColor");
                    int l = compoundtag.getInt("HighlightColor");
                    boolean hasHighlight = compoundtag.getBoolean("HasHighlight");

                    String base = "translatable.marvelous_menagerie.trilo_color." + Trilobite.getColorName(j);
                    String second = "translatable.marvelous_menagerie.trilo_color." + Trilobite.getColorName(k);
                    String highlight = "translatable.marvelous_menagerie.trilo_color." + Trilobite.getColorName(l);
                    MutableComponent baseComponent = Component.translatable("translatable.marvelous_menagerie.base_color");
                    baseComponent.append(":").append(CommonComponents.SPACE).append(Component.translatable(base)).withStyle(achatformatting);

                    MutableComponent secondComponent = Component.translatable("translatable.marvelous_menagerie.secondary_color");
                    secondComponent.append(":").append(CommonComponents.SPACE).append(Component.translatable(second)).withStyle(achatformatting);

                    pTooltipComponents.add(baseComponent);
                    pTooltipComponents.add(secondComponent);

                    if (hasHighlight){
                        MutableComponent highlightComponent = Component.translatable("translatable.marvelous_menagerie.highlight_color");
                        highlightComponent.append(":").append(CommonComponents.SPACE).append(Component.translatable(highlight)).withStyle(achatformatting);


                        pTooltipComponents.add(highlightComponent);
                    }
                }else {
                    String lgbtVariant = "translatable.marvelous_menagerie.lgbt." + Trilobite.getLGBTVariantName(lgbt);

                    MutableComponent lgbtComponent = Component.translatable("translatable.marvelous_menagerie.pride_flag");
                    lgbtComponent.append(":").append(CommonComponents.SPACE).append(Component.translatable(lgbtVariant)).withStyle(achatformatting);

                    pTooltipComponents.add(lgbtComponent);
                }
            }
        }


    }
}
