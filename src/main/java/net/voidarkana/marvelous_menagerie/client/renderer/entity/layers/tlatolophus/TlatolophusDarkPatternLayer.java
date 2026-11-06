package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tlatolophus;

@OnlyIn(Dist.CLIENT)
public class TlatolophusDarkPatternLayer<T extends Tlatolophus, M extends MarvelousModel<T>> extends RenderLayer<T, M> {

    public TlatolophusDarkPatternLayer(RenderLayerParent<T, M> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible() && pLivingEntity.getBaseVariant() > 0 && !pLivingEntity.isBaby()) {

            ResourceLocation patternTexture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/tlatolophus/tlatolophus_"
                    +pLivingEntity.getBaseVariantName()+".png");

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), patternTexture, pPoseStack, pBuffer, pPackedLight,
                    pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTicks, 1, 1, 1);
        }
    }
}
