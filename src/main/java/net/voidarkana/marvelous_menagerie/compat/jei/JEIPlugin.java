package net.voidarkana.marvelous_menagerie.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.data.manager.RitualManager;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation ID = new ResourceLocation(MarvelousMenagerie.MOD_ID, "jei_plugin");
    public static final RecipeType<RitualManager.RitualProcessData> SUMMONING_RITUALS = RecipeType.create(MarvelousMenagerie.MOD_ID, "summoning_rituals",
            RitualManager.RitualProcessData.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(MMBlocks.CHRONO_ALTAR.get()), SUMMONING_RITUALS);
        registry.addRecipeCatalyst(new ItemStack(MMBlocks.CHRONO_PEDESTAL.get()), SUMMONING_RITUALS);
        registry.addRecipeCatalyst(new ItemStack(MMItems.CHRONO_WATCH.get()), SUMMONING_RITUALS);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RitualRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<RitualManager.RitualProcessData> ritualRecipes = RitualManager.DATA.stream().map(data ->
                new RitualManager.RitualProcessData(data.input1(), data.input2(), data.input3(), data.input4(), data.output())).toList();

        registration.addRecipes(new RecipeType<>(RitualRecipeCategory.UID, RitualManager.RitualProcessData.class), ritualRecipes);
    }
}
