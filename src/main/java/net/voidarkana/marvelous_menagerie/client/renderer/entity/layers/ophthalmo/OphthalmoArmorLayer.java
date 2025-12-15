package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;

public class OphthalmoArmorLayer extends RenderLayer<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> {
//    private final OphthalmoModel<Ophthalmosaurus> model;

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor.png");
    private static final ResourceLocation TEXTURE_CRACKED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor_cracked.png");
    private static final ResourceLocation TEXTURE_VERY_CRACKED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor_very_cracked.png");

    public OphthalmoArmorLayer(RenderLayerParent<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
//        this.model = new OphthalmoModel<>(pContext.bakeLayer(MMModelLayers.OPHTHALMO_ARMOR_LAYER));
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Ophthalmosaurus pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (pLivingEntity.getHasArmor() && !pLivingEntity.isBaby()) {

//            this.getParentModel().copyPropertiesTo(this.model);
//            this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
//            this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
//
//            ResourceLocation texture;
//            if (pLivingEntity.isArmorHalfwayDamaged()){
//                texture = TEXTURE_CRACKED;
//            }else if (pLivingEntity.isArmorQuarterDamaged()){
//                texture = TEXTURE_VERY_CRACKED;
//            }else {
//                texture = TEXTURE;
//            }
//
//            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(texture));
//            this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1.0F);

            ResourceLocation texture;
            if (pLivingEntity.isArmorHalfwayDamaged()){
                texture = TEXTURE_CRACKED;
            }else if (pLivingEntity.isArmorQuarterDamaged()){
                texture = TEXTURE_VERY_CRACKED;
            }else {
                texture = TEXTURE;
            }

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);

        }
    }
}
