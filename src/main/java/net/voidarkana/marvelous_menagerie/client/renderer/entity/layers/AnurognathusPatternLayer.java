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
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anurognathus.BabyAnurognathusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anurognathus.AnurognathusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anurognathus;

@OnlyIn(Dist.CLIENT)
public class AnurognathusPatternLayer<T extends Anurognathus, M extends MarvelousModel<T>> extends RenderLayer<T, M> {

    private final BabyAnurognathusModel<Anurognathus> babyModel;
    private final AnurognathusModel<Anurognathus> model;

    public AnurognathusPatternLayer(RenderLayerParent<T, M> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
        this.babyModel = new BabyAnurognathusModel<>(pContext.bakeLayer(MMModelLayers.BABY_ANUROGNATHUS_PATTERN_LAYER));
        this.model = new AnurognathusModel<>(pContext.bakeLayer(MMModelLayers.ANUROGNATHUS_PATTERN_LAYER));
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible() && pLivingEntity.getPattern() != null) {

            ResourceLocation patternTexture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/anurognathus/"
                    + (pLivingEntity.isBaby() ? "baby_" : "")  + "anurognathus_"+pLivingEntity.getPattern()+".png");

            Object object = pLivingEntity.isBaby() ? babyModel : model;

            M entitymodel = (M)object;

            coloredCutoutModelCopyLayerRender(this.getParentModel(), entitymodel, patternTexture, pPoseStack, pBuffer, pPackedLight,
                    pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTicks, 1, 1, 1);
        }
    }
}
