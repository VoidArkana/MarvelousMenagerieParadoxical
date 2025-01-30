package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;

public class ArandaspisRenderer extends MobRenderer<Arandaspis, ArandaspisModel<Arandaspis>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/arandaspis.png");

    public ArandaspisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ArandaspisModel<>(pContext.bakeLayer(MMModelLayers.ARANDASPIS_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Arandaspis pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(Arandaspis pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.6f, 0.6f, 0.6f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }


    @Override
    protected void setupRotations(Arandaspis pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
