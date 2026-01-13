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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.ArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.BabyArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BabyBorealopeltaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BorealoModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;

public class ArandaspisRenderer extends MobRenderer<Arandaspis, MarvelousModel<Arandaspis>> {

    private final ArandaspisModel<Arandaspis> adultModel;
    private final BabyArandaspisModel<Arandaspis> babyModel;

    public ArandaspisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ArandaspisModel<>(pContext.bakeLayer(MMModelLayers.ARANDASPIS_LAYER)), 0.25f);
        adultModel = new ArandaspisModel<>(pContext.bakeLayer(MMModelLayers.ARANDASPIS_LAYER));
        babyModel = new BabyArandaspisModel<>(pContext.bakeLayer(MMModelLayers.BABY_ARANDASPIS_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Arandaspis pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/arandaspis/" + (pEntity.isBaby() ? "baby_" : "") +"arandaspis_"
                +Arandaspis.getVariantName(pEntity.getVariant())+".png");
    }

    @Override
    public void render(Arandaspis pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(Arandaspis pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
