package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.inostrancevia;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia.*;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;

@OnlyIn(Dist.CLIENT)
public class InostranceviaPatternLayer<T extends Inostrancevia, M extends MarvelousModel<T>> extends RenderLayer<T, M> {

    private final BabyInostranceviaModel<Inostrancevia> babyModel;
    private final InostranceviaModel<Inostrancevia> model;

    public InostranceviaPatternLayer(RenderLayerParent<T, M> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
        this.babyModel = new BabyInostranceviaModel<>(pContext.bakeLayer(MMModelLayers.BABY_INOSTRANCEVIA_PATTERN_LAYER));
        this.model = new InostranceviaModel<>(pContext.bakeLayer(MMModelLayers.INOSTRANCEVIA_PATTERN_LAYER));
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible() && pLivingEntity.getPattern() != null) {

            ResourceLocation patternTexture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/inostrancevia/"+pLivingEntity.getBaseColor()+"/"
                    + (pLivingEntity.isBaby() ? "baby_" : "")  + "inostrancevia_"+pLivingEntity.getBaseColor()+"_"+pLivingEntity.getPattern()+".png");

            Object object = pLivingEntity.isBaby() ? babyModel : model;

            M entitymodel = (M)object;

            coloredCutoutModelCopyLayerRender(this.getParentModel(), entitymodel, patternTexture, pPoseStack, pBuffer, pPackedLight,
                    pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTicks, 1, 1, 1);
        }
    }
}
