package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteFatModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteIttyModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteMidModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.TrilobiteHighlightLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.TrilobiteSecondLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

public class TrilobiteRenderer extends MobRenderer<Trilobite, MarvelousModel<Trilobite>> {

    private final TrilobiteIttyModel<Trilobite> triloIttyModel;
    private final TrilobiteMidModel<Trilobite> triloMidModel;
    private final TrilobiteFatModel<Trilobite> triloFatModel;

    public TrilobiteRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TrilobiteMidModel<>(pContext.bakeLayer(MMModelLayers.TRILO_MID_LAYER)), 0.15f);

        this.triloIttyModel = new TrilobiteIttyModel<>(pContext.bakeLayer(MMModelLayers.TRILO_ITTY_LAYER));
        this.triloMidModel = new TrilobiteMidModel<>(pContext.bakeLayer(MMModelLayers.TRILO_MID_LAYER));
        this.triloFatModel = new TrilobiteFatModel<>(pContext.bakeLayer(MMModelLayers.TRILO_FAT_LAYER));

        this.addLayer(new TrilobiteSecondLayer(this, pContext));
        this.addLayer(new TrilobiteHighlightLayer(this, pContext));
    }

    @Override
    public void render(Trilobite pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        switch (pEntity.getVariantModel()){
            case 0,1,2,3,4 :
                this.model = triloMidModel;
                break;
            case 5 :
                this.model = triloIttyModel;
                break;
            default :
                this.model = triloFatModel;
                break;
        };

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Trilobite entity) {
//        if (entity.isLGBTrilo()){
//            return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/trilobite/lgbtrilos/"+entity.getModelName(entity.getVariantModel())+
//                    "/trilobite_"+entity.getModelName(entity.getVariantModel())+"_"+entity.getTriloName()+".png");
//        }
//        else{
            return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/trilobite/"
                    +entity.getModelName(entity.getVariantModel())+ "/base/"
                    +entity.getModelName(entity.getVariantModel())+"_base_"
                    +entity.getColorName(entity.getVariantBaseColor())+".png");
        //}
    }

}
