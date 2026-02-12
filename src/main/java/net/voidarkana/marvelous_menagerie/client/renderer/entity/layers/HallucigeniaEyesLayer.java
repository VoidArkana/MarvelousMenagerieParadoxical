package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia.HallucigeniaModel;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;

public class HallucigeniaEyesLayer<T extends Hallucigenia> extends RenderLayer<T, MarvelousModel<T>> {
    private static final ResourceLocation POWER_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/hallucigenia/hallucigenia_eyes.png");
    private final HallucigeniaModel<T> model;

    public HallucigeniaEyesLayer(RenderLayerParent<T, MarvelousModel<T>> pRenderer, EntityModelSet pModelSet) {
        super(pRenderer);
        this.model = new HallucigeniaModel<>(pModelSet.bakeLayer(MMModelLayers.HALLU_EYES_LAYER));
    }

    protected float xOffset(float pTickCount) {
        return pTickCount * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return POWER_LOCATION;
    }

    protected EntityModel<T> model() {
        return this.model;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isBaby() && !pLivingEntity.isInvisible()){
            float f = (float)pLivingEntity.tickCount + pPartialTicks;
            EntityModel<T> entitymodel = this.model();
            entitymodel.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
            this.getParentModel().copyPropertiesTo(entitymodel);
            VertexConsumer vertexconsumer = pBuffer.getBuffer(MMRenderTypes.ScrollingTex(this.getTextureLocation(), this.xOffset(f) % 1.0F, f * 0.01F % 1.0F));
            entitymodel.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            entitymodel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1.0F);
        }
    }
}
