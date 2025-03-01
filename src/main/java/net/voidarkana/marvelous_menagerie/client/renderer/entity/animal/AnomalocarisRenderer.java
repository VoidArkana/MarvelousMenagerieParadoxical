package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.AnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;

public class AnomalocarisRenderer extends MobRenderer<Anomalocaris, AnomalocarisModel<Anomalocaris>> {

    private static final ResourceLocation NORMAL = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/anomalocaris/anomalocaris.png");
    private static final ResourceLocation SWAMPY = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/anomalocaris/anomalocaris_swampy.png");

    public AnomalocarisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AnomalocarisModel<>(pContext.bakeLayer(MMModelLayers.ANOMALOCARIS_LAYER)), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Anomalocaris pEntity) {
        return pEntity.getVariant() == 0 ? NORMAL : SWAMPY;
    }

//    @Override
//    public void render(Anomalocaris pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        if(pEntity.isBaby()){
//            pPoseStack.scale(0.6f, 0.6f, 0.6f);
//        }
//        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
//    }

    @Override
    protected void setupRotations(Anomalocaris pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
