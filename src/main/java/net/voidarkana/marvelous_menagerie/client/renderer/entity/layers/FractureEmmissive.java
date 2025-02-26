package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;

@OnlyIn(Dist.CLIENT)
public class FractureEmmissive <T extends Fracture, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation textureLocation;
    private final M model;
    private final FractureEmmissive.AlphaFunction<T> alphaFunction;

    public FractureEmmissive(RenderLayerParent<T, M> pRenderer, M pModel, ResourceLocation pTextureLocation, FractureEmmissive.AlphaFunction<T> pAlphaFunction) {
        super(pRenderer);
        this.model = pModel;
        this.textureLocation = pTextureLocation;
        this.alphaFunction = pAlphaFunction;
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
        this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucentEmissive(this.textureLocation));
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, this.alphaFunction.apply(pLivingEntity, pPartialTicks, pAgeInTicks));
    }

    @OnlyIn(Dist.CLIENT)
    public interface AlphaFunction<T extends Fracture> {
        float apply(T pLivingEntity, float pPartialTick, float pAgeInTicks);
    }

}