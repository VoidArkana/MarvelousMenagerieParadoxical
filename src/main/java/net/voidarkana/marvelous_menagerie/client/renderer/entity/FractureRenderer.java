package net.voidarkana.marvelous_menagerie.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.events.ForgeClientEvents;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.FractureModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.FractureEmmissive;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class FractureRenderer extends LivingEntityRenderer<Fracture, FractureModel<Fracture>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/fracture/fracture.png");

    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    public FractureRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FractureModel<>(pContext.bakeLayer(MMModelLayers.FRACTURE_LAYER)), 0);
        this.addLayer(new FractureEmmissive<>(this, new FractureModel<>(pContext.bakeLayer(MMModelLayers.FRACTURE_EMMISSIVE)),
                new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/fracture/fracture_emmissive.png")));
    }

    @Override
    public ResourceLocation getTextureLocation(Fracture pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(Fracture pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.5F, 0F);

        float scaleClosing;
        float scaleOpeningLag;
        float scaleOpening;

        if (pEntity.getClosingTime()>0){
            scaleClosing = 1 + ((float) (20 - pEntity.getClosingTime()) /30);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(((ForgeClientEvents.getClientTicks() / 300)*scaleClosing)*(20 - pEntity.getClosingTime())/5));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees((float) (Math.sin((ForgeClientEvents.getClientTicks() / 300)*scaleClosing)*(20 - pEntity.getClosingTime())/2)));
            pPoseStack.mulPose(Axis.XP.rotationDegrees((float) (Math.cos((ForgeClientEvents.getClientTicks() / 300)*scaleClosing)*(20 - pEntity.getClosingTime())/2)));
        } else {
            scaleClosing = 1;
        }

        scaleOpening = (float) ((Math.sin(Math.max(0 ,(double) pEntity.getOpeningTime()-20)/13)));
        scaleOpeningLag = (float) ((Math.sin(Math.max(0 ,(double) pEntity.getOpeningTimeLag()-20)/13)));

        pPoseStack.scale(scaleOpening * scaleClosing, scaleOpening * scaleClosing, scaleOpening * scaleClosing);

        pPoseStack.translate(0.0F, -0.5F, 0F);
            float f5 = 0.5F;
            float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
            RandomSource randomsource = RandomSource.create(432L);
            VertexConsumer vertexconsumer2 = pBuffer.getBuffer(RenderType.lightning());

            pPoseStack.pushPose();
            pPoseStack.translate(0.0F, 0.7F, 0F);
            pPoseStack.scale(0.05f, 0.05f, 0.05f);
            pPoseStack.scale(scaleOpeningLag, scaleOpeningLag, scaleOpeningLag);

            if (pEntity.getClosingTime()>0)
                pPoseStack.mulPose(Axis.YP.rotationDegrees(((ForgeClientEvents.getClientTicks() / 300)*scaleClosing)*(20 - pEntity.getClosingTime())/5));

            float rotation = ForgeClientEvents.getClientTicks() / 300;

            for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
                pPoseStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                pPoseStack.mulPose(Axis.XP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(randomsource.nextFloat() * 360.0F));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(randomsource.nextFloat() * 360.0F + rotation * 90.0F));
                float f3 = randomsource.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
                float f4 = randomsource.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
                Matrix4f matrix4f = pPoseStack.last().pose();
                int j = (int)(255.0F * (1.0F - f7));
                vertex01(vertexconsumer2, matrix4f, j);
                vertex2(vertexconsumer2, matrix4f, f3, f4);
                vertex3(vertexconsumer2, matrix4f, f3, f4);
                vertex01(vertexconsumer2, matrix4f, j);
                vertex3(vertexconsumer2, matrix4f, f3, f4);
                vertex4(vertexconsumer2, matrix4f, f3, f4);
                vertex01(vertexconsumer2, matrix4f, j);
                vertex4(vertexconsumer2, matrix4f, f3, f4);
                vertex2(vertexconsumer2, matrix4f, f3, f4);
            }
            pPoseStack.popPose();

            super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        pPoseStack.popPose();
    }

    private static void vertex01(VertexConsumer pConsumer, Matrix4f pMatrix, int pAlpha) {
        pConsumer.vertex(pMatrix, 0.0F, 0.0F, 0.0F).color(255, 255, 255, pAlpha).endVertex();
    }

    private static void vertex2(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253704_, float p_253701_) {
        pConsumer.vertex(pMatrix, -HALF_SQRT_3 * p_253701_, p_253704_, -0.5F * p_253701_).color(0, 150, 255, 0).endVertex();
    }

    private static void vertex3(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253729_, float p_254030_) {
        pConsumer.vertex(pMatrix, HALF_SQRT_3 * p_254030_, p_253729_, -0.5F * p_254030_).color(0, 100, 255, 0).endVertex();
    }

    private static void vertex4(VertexConsumer pConsumer, Matrix4f pMatrix, float p_253649_, float p_253694_) {
        pConsumer.vertex(pMatrix, 0.0F, p_253649_, 1.0F * p_253694_).color(0, 50, 255, 0).endVertex();
    }

    @Nullable
    @Override
    protected RenderType getRenderType(Fracture pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucent(TEXTURE);
    }

    @Override
    protected boolean shouldShowName(Fracture pEntity) {
        return false;
    }
}
