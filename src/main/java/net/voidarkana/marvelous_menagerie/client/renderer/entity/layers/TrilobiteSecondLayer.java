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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteFatModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteIttyModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteMidModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

@OnlyIn(Dist.CLIENT)
public class TrilobiteSecondLayer extends RenderLayer<Trilobite, MarvelousModel<Trilobite>> {

    private final TrilobiteIttyModel<Trilobite> triloIttyModel;
    private final TrilobiteMidModel<Trilobite> triloMidModel;
    private final TrilobiteFatModel<Trilobite> triloFatModel;

    public TrilobiteSecondLayer(RenderLayerParent<Trilobite, MarvelousModel<Trilobite>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
        this.triloIttyModel = new TrilobiteIttyModel<>(pContext.bakeLayer(MMModelLayers.TRILO_ITTY_LAYER_SECOND));
        this.triloMidModel = new TrilobiteMidModel<>(pContext.bakeLayer(MMModelLayers.TRILO_MID_LAYER_SECOND));
        this.triloFatModel = new TrilobiteFatModel<>(pContext.bakeLayer(MMModelLayers.TRILO_FAT_LAYER_SECOND));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Trilobite entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (!entity.isLGBTrilo() && !entity.isInvisible()){
            Object object;
            ResourceLocation texture;
            switch (entity.getVariantModel()){
                case 0,1,2,3,4 :
                    object = triloMidModel;
                    texture = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/trilobite/mid_second/mid_second_"+Trilobite.getColorName(entity.getVariantSecondColor())+".png");
                    break;
                case 5 :
                    object = triloIttyModel;
                    texture = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/trilobite/itty/second/itty_second_"+Trilobite.getColorName(entity.getVariantSecondColor())+".png");
                    break;
                default :
                    object = triloFatModel;
                    texture = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/trilobite/fat/second/fat_second_"+Trilobite.getColorName(entity.getVariantSecondColor())+".png");
                    break;
            };

            MarvelousModel<Trilobite> entitymodel = (MarvelousModel<Trilobite>)object;

            coloredCutoutModelCopyLayerRender(this.getParentModel(), entitymodel, texture, pPoseStack, pBuffer, pPackedLight,
                    entity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);
        }

//        this.getParentModel().copyPropertiesTo(this.model);
//        this.model.prepareMobModel(entity, pLimbSwing, pLimbSwingAmount, pPartialTick);
//        this.model.setupAnim(entity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

//        if (!entity.isLGBTrilo() && !entity.isInvisible()){
//
//            RenderType cameo = RenderType.entityCutoutNoCull(new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/trilobite/trilobite_"+entity.getModelName(entity.getVariantModel())
//                    +"_second/trilobite_"+entity.getModelName(entity.getVariantModel())+"_second_"+entity.getSecondColorName(entity.getVariantSecondColor())+".png"));
//            ResourceLocation trilobiteModel = new ResourceLocation(MarvelousMenagerie.MODID, "geo/trilobite/trilobite_"
//                    +entity.getModelName(entity.getVariantModel())+".geo.json");
//
//            this.getRenderer().reRender(this.getGeoModel().getBakedModel(trilobiteModel), poseStack, bufferSource, entity, renderType,
//                    bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//
//        }
    }
}
