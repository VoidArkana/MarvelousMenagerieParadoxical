package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.BabyOphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;

public class OphthalmoPatternLayer extends RenderLayer<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> {

    private final BabyOphthalmoModel<Ophthalmosaurus> babyModel;
    private final OphthalmoModel<Ophthalmosaurus> model;

    public OphthalmoPatternLayer(RenderLayerParent<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
        this.babyModel = new BabyOphthalmoModel<>(pContext.bakeLayer(MMModelLayers.BABY_OPHTHALMO_PATTERN_LAYER));
        this.model = new OphthalmoModel<>(pContext.bakeLayer(MMModelLayers.OPHTHALMO_PATTERN_LAYER));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Ophthalmosaurus entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (!entity.isInvisible() && entity.getPattern()!=0){

            Object object = entity.isBaby() ? babyModel : model;

            String color = entity.getColorName(entity.getBaseColor());
            String pattern = entity.getPatternName(entity.getPattern());

            ResourceLocation texture = new ResourceLocation(MarvelousMenagerie.MOD_ID,
                    "textures/entity/animal/ophthalmo/"
                            +(entity.isBaby() ? "baby_" : "" )
                            +"ophthalmo_"+pattern+"_"+color+".png");

            MarvelousModel<Ophthalmosaurus> entitymodel = (MarvelousModel<Ophthalmosaurus>)object;

            coloredCutoutModelCopyLayerRender(this.getParentModel(), entitymodel, texture, pPoseStack, pBuffer, pPackedLight,
                    entity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);
        }
    }
}
