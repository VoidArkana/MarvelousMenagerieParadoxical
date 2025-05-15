package net.voidarkana.marvelous_menagerie.common.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.voidarkana.marvelous_menagerie.common.effect.MMEffects;

public class MMFoods {

    public static final FoodProperties BOILED_ELEPHANT_EGG = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(0.6F).build();
    public static final FoodProperties JUMBO_OMELETTE = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationMod(1.5F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F).alwaysEat().build();

    public static final FoodProperties STELLER_ICE_CREAM = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.2F)
            .effect(new MobEffectInstance(MMEffects.SEA_COW_SERENITY.get(), 200, 0, true, true, true,
                    new MobEffectInstance(MobEffects.WATER_BREATHING, 200,0, false, false, false,
                            new MobEffectInstance(MobEffects.WATER_BREATHING, 200,0, false, false), MMEffects.SEA_COW_SERENITY.get().createFactorData()), MMEffects.SEA_COW_SERENITY.get().createFactorData()),
                    1.0F).alwaysEat().build();

    public static final FoodProperties TRILO_BITE = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F).build();

    public static final FoodProperties RAW_SACA = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F).build();

    public static final FoodProperties COOKED_SACA = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationMod(0.6F).build();

    public static final FoodProperties GOLDEN_SACA = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationMod(10F).build();

    public static final FoodProperties MAGIC_ROLL = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.1F)
            .effect(new MobEffectInstance(MMEffects.HALLUCINATING.get(), 20*30, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*30, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 20*30, 1), 1.0F).build();


    public static final FoodProperties RAW_DAWN_HORSE = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.3F).meat().build();

    public static final FoodProperties COOKED_DAWN_HORSE = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationMod(0.6F).meat().build();

}
