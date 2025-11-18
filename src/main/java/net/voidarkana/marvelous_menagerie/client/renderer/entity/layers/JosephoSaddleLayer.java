package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.JosephoModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Josephoartigasia;

@OnlyIn(Dist.CLIENT)
public class JosephoSaddleLayer extends RenderLayer<Josephoartigasia, JosephoModel<Josephoartigasia>> {

    public JosephoSaddleLayer(RenderLayerParent<Josephoartigasia, JosephoModel<Josephoartigasia>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Josephoartigasia entityLivingBaseIn, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (entityLivingBaseIn.isSaddled()) {

            ResourceLocation texture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/josepho/josepho_saddle.png");

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    entityLivingBaseIn, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);

        }
    }
}
