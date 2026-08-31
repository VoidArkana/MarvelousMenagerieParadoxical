package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.common.entity.base.MMEntityAccess;

public class MMSummonGlowLayer extends RenderLayer {

    public MMSummonGlowLayer(RenderLayerParent pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource pBuffer, int pPackedLight,
                       Entity entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (entity instanceof MMEntityAccess access && access.getSummonedProgress(pPartialTick)>0 && !entity.isInvisible()){
            VertexConsumer ivertexbuilder = pBuffer.getBuffer(MMRenderTypes.GLOWING);
            poseStack.pushPose();
            float alpha = access.getSummonedProgress(pPartialTick);
            this.getParentModel().renderToBuffer(poseStack, ivertexbuilder, pPackedLight, OverlayTexture.NO_OVERLAY,
                    1, 1,1, alpha);
            poseStack.popPose();
        }
    }
}
