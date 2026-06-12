package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;

public class InostranceviaEyesLayer<T extends Inostrancevia, M extends MarvelousModel<T>> extends RenderLayer<T, M> {

    public InostranceviaEyesLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    public RenderType renderType(ResourceLocation location) {
        return RenderType.eyes(location);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.renderType(new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/inostrancevia/"+
                (pLivingEntity.isBaby() ? "baby_" : "")+(pLivingEntity.isEvil() ? "evil_" : "")+"inostrancevia_eyes.png")));
        this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
