package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.util.function.BiConsumer;

public class BookBlit {
    private static int r = 255;
    private static int g = 255;
    private static int b = 255;
    private static int a = 255;

//    public static void fill(PoseStack p_238467_0_, int p_238467_1_, int p_238467_2_, int p_238467_3_, int p_238467_4_, int p_238467_5_) {
//        innerFill(p_238467_0_.last().pose(), p_238467_1_, p_238467_2_, p_238467_3_, p_238467_4_, p_238467_5_);
//    }
//
//    private static void innerFill(Matrix4f p_238460_0_, int p_238460_1_, int p_238460_2_, int p_238460_3_, int p_238460_4_, int p_238460_5_) {
//        int lvt_6_2_;
//        if (p_238460_1_ < p_238460_3_) {
//            lvt_6_2_ = p_238460_1_;
//            p_238460_1_ = p_238460_3_;
//            p_238460_3_ = lvt_6_2_;
//        }
//
//        if (p_238460_2_ < p_238460_4_) {
//            lvt_6_2_ = p_238460_2_;
//            p_238460_2_ = p_238460_4_;
//            p_238460_4_ = lvt_6_2_;
//        }
//
//        float lvt_6_3_ = (float) (p_238460_5_ >> 24 & 255) / 255.0F;
//        float lvt_7_1_ = (float) (p_238460_5_ >> 16 & 255) / 255.0F;
//        float lvt_8_1_ = (float) (p_238460_5_ >> 8 & 255) / 255.0F;
//        float lvt_9_1_ = (float) (p_238460_5_ & 255) / 255.0F;
//        BufferBuilder lvt_10_1_ = Tesselator.getInstance().getBuilder();
//        RenderSystem.enableBlend();
//        RenderSystem.disableTexture();
//        RenderSystem.defaultBlendFunc();
//        lvt_10_1_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
//        lvt_10_1_.vertex(p_238460_0_, (float) p_238460_1_, (float) p_238460_4_, 0.0F).color(lvt_7_1_, lvt_8_1_, lvt_9_1_, lvt_6_3_).endVertex();
//        lvt_10_1_.vertex(p_238460_0_, (float) p_238460_3_, (float) p_238460_4_, 0.0F).color(lvt_7_1_, lvt_8_1_, lvt_9_1_, lvt_6_3_).endVertex();
//        lvt_10_1_.vertex(p_238460_0_, (float) p_238460_3_, (float) p_238460_2_, 0.0F).color(lvt_7_1_, lvt_8_1_, lvt_9_1_, lvt_6_3_).endVertex();
//        lvt_10_1_.vertex(p_238460_0_, (float) p_238460_1_, (float) p_238460_2_, 0.0F).color(lvt_7_1_, lvt_8_1_, lvt_9_1_, lvt_6_3_).endVertex();
//        BufferUploader.drawWithShader(lvt_10_1_.end());
//        RenderSystem.enableTexture();
//        RenderSystem.disableBlend();
//    }

    protected static void fillGradient(Matrix4f p_238462_0_, BufferBuilder p_238462_1_, int p_238462_2_, int p_238462_3_, int p_238462_4_, int p_238462_5_, int p_238462_6_, int p_238462_7_, int p_238462_8_) {
        float lvt_9_1_ = (float) (p_238462_7_ >> 24 & 255) / 255.0F;
        float lvt_10_1_ = (float) (p_238462_7_ >> 16 & 255) / 255.0F;
        float lvt_11_1_ = (float) (p_238462_7_ >> 8 & 255) / 255.0F;
        float lvt_12_1_ = (float) (p_238462_7_ & 255) / 255.0F;
        float lvt_13_1_ = (float) (p_238462_8_ >> 24 & 255) / 255.0F;
        float lvt_14_1_ = (float) (p_238462_8_ >> 16 & 255) / 255.0F;
        float lvt_15_1_ = (float) (p_238462_8_ >> 8 & 255) / 255.0F;
        float lvt_16_1_ = (float) (p_238462_8_ & 255) / 255.0F;
        p_238462_1_.vertex(p_238462_0_, (float) p_238462_4_, (float) p_238462_3_, (float) p_238462_6_).color(lvt_10_1_, lvt_11_1_, lvt_12_1_, lvt_9_1_).endVertex();
        p_238462_1_.vertex(p_238462_0_, (float) p_238462_2_, (float) p_238462_3_, (float) p_238462_6_).color(lvt_10_1_, lvt_11_1_, lvt_12_1_, lvt_9_1_).endVertex();
        p_238462_1_.vertex(p_238462_0_, (float) p_238462_2_, (float) p_238462_5_, (float) p_238462_6_).color(lvt_14_1_, lvt_15_1_, lvt_16_1_, lvt_13_1_).endVertex();
        p_238462_1_.vertex(p_238462_0_, (float) p_238462_4_, (float) p_238462_5_, (float) p_238462_6_).color(lvt_14_1_, lvt_15_1_, lvt_16_1_, lvt_13_1_).endVertex();
    }

    public static void blit(PoseStack p_238464_0_, int p_238464_1_, int p_238464_2_, int p_238464_3_, float p_238464_4_, float p_238464_5_, int p_238464_6_, int p_238464_7_, int p_238464_8_, int p_238464_9_) {
        innerBlit(p_238464_0_, p_238464_1_, p_238464_1_ + p_238464_6_, p_238464_2_, p_238464_2_ + p_238464_7_, p_238464_3_, p_238464_6_, p_238464_7_, p_238464_4_, p_238464_5_, p_238464_9_, p_238464_8_);
    }

    public static void blit(PoseStack p_238466_0_, int p_238466_1_, int p_238466_2_, int p_238466_3_, int p_238466_4_, float p_238466_5_, float p_238466_6_, int p_238466_7_, int p_238466_8_, int p_238466_9_, int p_238466_10_) {
        innerBlit(p_238466_0_, p_238466_1_, p_238466_1_ + p_238466_3_, p_238466_2_, p_238466_2_ + p_238466_4_, 0, p_238466_7_, p_238466_8_, p_238466_5_, p_238466_6_, p_238466_9_, p_238466_10_);
    }

    public static void blit(PoseStack p_238463_0_, int p_238463_1_, int p_238463_2_, float p_238463_3_, float p_238463_4_, int p_238463_5_, int p_238463_6_, int p_238463_7_, int p_238463_8_) {
        blit(p_238463_0_, p_238463_1_, p_238463_2_, p_238463_5_, p_238463_6_, p_238463_3_, p_238463_4_, p_238463_5_, p_238463_6_, p_238463_7_, p_238463_8_);
    }

    private static void innerBlit(PoseStack p_238469_0_, int p_238469_1_, int p_238469_2_, int p_238469_3_, int p_238469_4_, int p_238469_5_, int p_238469_6_, int p_238469_7_, float p_238469_8_, float p_238469_9_, int p_238469_10_, int p_238469_11_) {
        innerBlit(p_238469_0_.last().pose(), p_238469_1_, p_238469_2_, p_238469_3_, p_238469_4_, p_238469_5_, (p_238469_8_ + 0.0F) / (float) p_238469_10_, (p_238469_8_ + (float) p_238469_6_) / (float) p_238469_10_, (p_238469_9_ + 0.0F) / (float) p_238469_11_, (p_238469_9_ + (float) p_238469_7_) / (float) p_238469_11_);
    }

    private static void innerBlit(Matrix4f p_238461_0_, int p_238461_1_, int p_238461_2_, int p_238461_3_, int p_238461_4_, int p_238461_5_, float p_238461_6_, float p_238461_7_, float p_238461_8_, float p_238461_9_) {
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(p_238461_0_, (float) p_238461_1_, (float) p_238461_4_, (float) p_238461_5_).color(r, g, b, a).uv(p_238461_6_, p_238461_9_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float) p_238461_2_, (float) p_238461_4_, (float) p_238461_5_).color(r, g, b, a).uv(p_238461_7_, p_238461_9_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float) p_238461_2_, (float) p_238461_3_, (float) p_238461_5_).color(r, g, b, a).uv(p_238461_7_, p_238461_8_).endVertex();
        bufferbuilder.vertex(p_238461_0_, (float) p_238461_1_, (float) p_238461_3_, (float) p_238461_5_).color(r, g, b, a).uv(p_238461_6_, p_238461_8_).endVertex();
        //bufferbuilder.end();
        //RenderSystem.enableAlphaTest();
        BufferUploader.drawWithShader(bufferbuilder.end());
    }

    public static void setRGB(int r, int g, int b, int a){
        BookBlit.r = r;
        BookBlit.g = g;
        BookBlit.b = b;
        BookBlit.a = a;
    }

    public void blitOutlineBlack(int p_238459_1_, int p_238459_2_, BiConsumer<Integer, Integer> p_238459_3_) {
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        p_238459_3_.accept(p_238459_1_ + 1, p_238459_2_);
        p_238459_3_.accept(p_238459_1_ - 1, p_238459_2_);
        p_238459_3_.accept(p_238459_1_, p_238459_2_ + 1);
        p_238459_3_.accept(p_238459_1_, p_238459_2_ - 1);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        p_238459_3_.accept(p_238459_1_, p_238459_2_);
    }

    public static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation p_283377_, int p_281970_, int p_282111_, int p_283134_, int p_282778_, int p_281478_, int p_281821_, int r, int g, int b, int a) {
        blitWithColor(guiGraphics, p_283377_, p_281970_, p_282111_, 0, (float)p_283134_, (float)p_282778_, p_281478_, p_281821_, 256, 256, r, g, b, a);
    }

    public static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation p_283573_, int p_283574_, int p_283670_, int p_283545_, float p_283029_, float p_283061_, int p_282845_, int p_282558_, int p_282832_, int p_281851_, int r, int g, int b, int a) {
        blitWithColor(guiGraphics, p_283573_, p_283574_, p_283574_ + p_282845_, p_283670_, p_283670_ + p_282558_, p_283545_, p_282845_, p_282558_, p_283029_, p_283061_, p_282832_, p_281851_, r, g, b, a);
    }

    public static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation p_282034_, int p_283671_, int p_282377_, int p_282058_, int p_281939_, float p_282285_, float p_283199_, int p_282186_, int p_282322_, int p_282481_, int p_281887_, int r, int g, int b, int a) {
        blitWithColor(guiGraphics, p_282034_, p_283671_, p_283671_ + p_282058_, p_282377_, p_282377_ + p_281939_, 0, p_282186_, p_282322_, p_282285_, p_283199_, p_282481_, p_281887_, r, g, b, a);
    }

    public static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation p_283272_, int p_283605_, int p_281879_, float p_282809_, float p_282942_, int p_281922_, int p_282385_, int p_282596_, int p_281699_, int r, int g, int b, int a) {
        blitWithColor(guiGraphics, p_283272_, p_283605_, p_281879_, p_281922_, p_282385_, p_282809_, p_282942_, p_281922_, p_282385_, p_282596_, p_281699_, r, g, b, a);
    }

    private static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation p_282639_, int p_282732_, int p_283541_, int p_281760_, int p_283298_, int p_283429_, int p_282193_, int p_281980_, float p_282660_, float p_281522_, int p_282315_, int p_281436_, int r, int g, int b, int a) {
        blitWithColor(guiGraphics, p_282639_, p_282732_, p_283541_, p_281760_, p_283298_, p_283429_, (p_282660_ + 0.0F) / (float)p_282315_, (p_282660_ + (float)p_282193_) / (float)p_282315_, (p_281522_ + 0.0F) / (float)p_281436_, (p_281522_ + (float)p_281980_) / (float)p_281436_, r, g, b, a);
    }

    private static void blitWithColor(GuiGraphics guiGraphics, ResourceLocation texture, int startX, int endX, int startY, int endY, int zLevel, float u0, float u1, float v0, float v1, int r, int g, int b, int a) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.enableBlend();
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix4f, (float)startX, (float)startY, (float)zLevel).color(r, g, b, a).uv(u0, v0).endVertex();
        bufferbuilder.vertex(matrix4f, (float)startX, (float)endY, (float)zLevel).color(r, g, b, a).uv(u0, v1).endVertex();
        bufferbuilder.vertex(matrix4f, (float)endX, (float)endY, (float)zLevel).color(r, g, b, a).uv(u1, v1).endVertex();
        bufferbuilder.vertex(matrix4f, (float)endX, (float)startY, (float)zLevel).color(r, g, b, a).uv(u1, v0).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
    }
}
