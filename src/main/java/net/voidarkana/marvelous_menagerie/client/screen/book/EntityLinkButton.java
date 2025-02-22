package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.marvelous_menagerie.client.renderer.item.MMItemstackRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.data.codec.AbominationEntryManager;

import javax.annotation.Nullable;
import java.util.List;

public class EntityLinkButton extends Button {

    @Expose
    private boolean sepia;

    @Expose(serialize = false, deserialize = false)
    private ItemStack actualItem = ItemStack.EMPTY;

    @Expose
    private String item;
    @Expose
    private String name;

    private static final RenderType SEPIA_ITEM_RENDER_TYPE = MMRenderTypes.getBookWidget(TextureAtlas.LOCATION_BLOCKS, true);

    //private final AbominationEntryManager.AbominationEntryCodec data;
    private final PaleonomiconScreen bookGUI;

    public EntityLinkButton(PaleonomiconScreen bookGUI, String icon, String name, int k, int l, Button.OnPress o) {
        super(k + 1 - 12, l + 1, 18, 18, CommonComponents.EMPTY, o, DEFAULT_NARRATION);
        this.item = icon;
        this.name = name;
        this.bookGUI = bookGUI;
    }


    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        actualItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)));

        int lvt_5_1_ = 0;
        int lvt_6_1_ = 30;
        float f = (float) 0.85;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(getX(), getY(), 0);
        guiGraphics.pose().translate(1, 10, 50);
        guiGraphics.pose().scale(f, f, 1);
        //this.drawBtn(false, guiGraphics, 0, 0, lvt_5_1_, lvt_6_1_, 24, 24);

        //guiGraphics.enableScissor(this.getX() + Math.round(f * 4), this.getY() + Math.round(f * 4), this.getX() + Math.round(f * 20), this.getY() + Math.round(f * 20));

        float scale = 16.0F * f;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(12, 12, 0);
        guiGraphics.pose().scale(1.5f, 1.5f, 0);
            renderItem(actualItem, guiGraphics.pose(), bufferSource, true, scale);
        guiGraphics.pose().popPose();

        //guiGraphics.disableScissor();

        if (this.isHovered) {
            bookGUI.setEntityTooltip(Component.translatable(name).getString());
            lvt_5_1_ = 48;
        } else {
            lvt_5_1_ = 24;
        }
        //this.drawBtn(!this.isHovered, guiGraphics, 0, 0, lvt_5_1_, lvt_6_1_, 24, 24);
        guiGraphics.pose().popPose();
    }

    public void drawBtn(boolean color, GuiGraphics guiGraphics, int p_238474_2_, int p_238474_3_, int p_238474_4_, int p_238474_5_, int p_238474_6_, int p_238474_7_) {
        if (color) {
            int widgetColor = bookGUI.getWidgetColor();
            int r = (widgetColor & 0xFF0000) >> 16;
            int g = (widgetColor & 0xFF00) >> 8;
            int b = (widgetColor & 0xFF);
            BookBlit.blitWithColor(guiGraphics, bookGUI.getBookWidgetTexture(),  p_238474_2_, p_238474_3_, 0, (float) p_238474_4_, (float) p_238474_5_, p_238474_6_, p_238474_7_, 256, 256, r, g, b, 255);
        } else {
            guiGraphics.blit(bookGUI.getBookWidgetTexture(), p_238474_2_, p_238474_3_, 0, (float) p_238474_4_, (float) p_238474_5_, p_238474_6_, p_238474_7_, 256, 256);
        }
    }

    public static void renderItem(ItemStack itemStack, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, boolean sepia, float scale){
        if(itemStack == null){
            return;
        }
        BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(itemStack, Minecraft.getInstance().level, null, 0);
        poseStack.pushPose();
        try {
            poseStack.scale(scale, scale, scale);
            PaleonomiconScreen.fixLighting();
            if (!sepia) {
                poseStack.mulPose(Axis.YP.rotationDegrees(180F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
            } else {
                poseStack.mulPose(Axis.ZN.rotationDegrees(180F));
                poseStack.scale(-1F, 1F, 1F);
                MMItemstackRenderer.sepiaFlag = true;
            }
            if (sepia && !bakedmodel.isCustomRenderer()) {
                renderSepiaItem(poseStack, bakedmodel, itemStack, bufferSource);
            } else {
                Minecraft.getInstance().getItemRenderer().render(itemStack, ItemDisplayContext.GUI, false, poseStack, bufferSource, 240, OverlayTexture.NO_OVERLAY, bakedmodel);
            }
            if (sepia) {
                MMItemstackRenderer.sepiaFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        poseStack.popPose();
    }

    public static void renderSepiaItem(PoseStack poseStack, BakedModel bakedmodel, ItemStack itemStack, MultiBufferSource.BufferSource bufferSource){
        poseStack.pushPose();
        bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(poseStack, bakedmodel, ItemDisplayContext.GUI, false);
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        for (net.minecraft.client.renderer.RenderType rt : bakedmodel.getRenderTypes(itemStack, false)) {
            renderModel(poseStack.last(), bufferSource.getBuffer(SEPIA_ITEM_RENDER_TYPE), 1.0F, null, bakedmodel, 1.0F, 1.0F, 1.0F, 240, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, rt);
        }
        poseStack.popPose();
    }
    private static void renderModel(PoseStack.Pose p_111068_, VertexConsumer p_111069_, float alpha, @Nullable BlockState p_111070_, BakedModel p_111071_, float p_111072_, float p_111073_, float p_111074_, int p_111075_, int p_111076_, ModelData modelData, net.minecraft.client.renderer.RenderType renderType) {
        RandomSource randomsource = RandomSource.create();
        long i = 42L;

        for (Direction direction : Direction.values()) {
            randomsource.setSeed(42L);
            renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, alpha, p_111071_.getQuads(p_111070_, direction, randomsource, modelData, renderType), p_111075_, p_111076_);
        }

        randomsource.setSeed(42L);
        renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, alpha, p_111071_.getQuads(p_111070_, (Direction) null, randomsource, modelData, renderType), p_111075_, p_111076_);
    }

    private static void renderQuadList(PoseStack.Pose p_111059_, VertexConsumer p_111060_, float p_111061_, float p_111062_, float p_111063_, float alpha, List<BakedQuad> p_111064_, int p_111065_, int p_111066_) {
        for (BakedQuad bakedquad : p_111064_) {
            float f;
            float f1;
            float f2;
            f = Mth.clamp(p_111061_, 0.0F, 1.0F);
            f1 = Mth.clamp(p_111062_, 0.0F, 1.0F);
            f2 = Mth.clamp(p_111063_, 0.0F, 1.0F);
            p_111060_.putBulkData(p_111059_, bakedquad, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, f, f1, f2, alpha, new int[]{p_111065_, p_111065_, p_111065_, p_111065_}, p_111066_, false);
        }

    }

}
