package net.voidarkana.marvelous_menagerie.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;


@OnlyIn(Dist.CLIENT)
public class RitualRecipeCategory implements IRecipeCategory<RitualManager.RitualProcessData> {
    public static final ResourceLocation UID = new ResourceLocation(MarvelousMenagerie.MODID, "summoning_ritual");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/jei/summoning_ritual.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slotDrawable;
    private final IDrawable goober;
    private final IDrawable sparkles;
    private final IDrawable resultArrow1;
    private final IDrawable resultArrow2;
    private final IDrawable resultArrow3;
    private final IDrawable resultArrow4;

    public RitualRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(150, 65);
        this.goober = guiHelper.createDrawable(TEXTURE, 0, 0, 24, 28);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MMBlocks.CHRONO_ALTAR.get()));
        this.slotDrawable = guiHelper.getSlotDrawable();
        this.sparkles = guiHelper.createDrawable(TEXTURE, 32, 0, 48, 48);
        this.resultArrow1 = guiHelper.createDrawable(TEXTURE, 0, 32, 19, 12);
        this.resultArrow2 = guiHelper.createDrawable(TEXTURE, 0, 48, 10, 18);
        this.resultArrow3 = guiHelper.createDrawable(TEXTURE, 16, 48, 10, 18);
        this.resultArrow4 = guiHelper.createDrawable(TEXTURE, 32, 48, 19, 12);
    }

    @Override
    public RecipeType<RitualManager.RitualProcessData> getRecipeType() {
        return new RecipeType<>(UID, RitualManager.RitualProcessData.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.marvelous_menagerie.summoning_rituals");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RitualManager.RitualProcessData recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,   4,   6+10).addIngredients(recipe.input1());
        builder.addSlot(RecipeIngredientRole.INPUT,  34,  26+10).addIngredients(recipe.input2());
        builder.addSlot(RecipeIngredientRole.INPUT, 100,  26+10).addIngredients(recipe.input3());
        builder.addSlot(RecipeIngredientRole.INPUT, 130,   6+10).addIngredients(recipe.input4());
        builder.addSlot(RecipeIngredientRole.INPUT, 65,   50).addItemStack(new ItemStack(MMItems.CHRONO_WATCH.get()))
                .addTooltipCallback((view, tooltip) -> {
                    tooltip.add(Component.translatable("jei.marvelous_menagerie.chrono_watch_tooltip").setStyle(Style.EMPTY.withColor(0x00c2f9)));
                });
    }

    @Override
    public void draw(RitualManager.RitualProcessData recipe, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY) {
        slotDrawable.draw(graphics,   3,  5+10);
        slotDrawable.draw(graphics,  33, 25+10);
        slotDrawable.draw(graphics,  99, 25+10 );
        slotDrawable.draw(graphics, 129,  5+10);

        goober.draw(graphics,   0, 4+10);
        goober.draw(graphics,  30,24+10);
        goober.draw(graphics,  93+3,24+10);
        goober.draw(graphics, 123+3, 4+10);

        resultArrow1.draw(graphics, 26, 2);
        resultArrow2.draw(graphics, 39, 14);
        resultArrow3.draw(graphics, 100, 14);
        resultArrow4.draw(graphics, 105, 2);

        sparkles.draw(graphics, 50, -3);

        Entity model = null;
        EntityType type = recipe.output();
        if (type != null) {
            model = type.create(Minecraft.getInstance().level);
        }

        if (model !=null) {
            if (model instanceof LivingEntity entity){

                int scale = entity.getDimensions(Pose.STANDING).height < 0.75 && entity.getDimensions(Pose.STANDING).width < 0.75 ? 35 :
                        entity.getDimensions(Pose.STANDING).height < 1 ? 25 :
                        (int) Math.max((Math.max(1/entity.getDimensions(Pose.STANDING).height, 1)
                        * Math.max(1/entity.getDimensions(Pose.STANDING).width, 1)),1) * 10;
                InventoryScreen.renderEntityInInventoryFollowsAngle(graphics, 73, 30, scale, (float) Math.toRadians(-75), (float) Math.toRadians(-45), entity);
            }
        }
    }

}
