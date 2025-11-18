package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.AnomalocarisModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;

public class AnomalocarisRenderer extends MobRenderer<Anomalocaris, AnomalocarisModel<Anomalocaris>> {

    public AnomalocarisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AnomalocarisModel<>(pContext.bakeLayer(MMModelLayers.ANOMALOCARIS_LAYER)), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Anomalocaris pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/anomalocaris/anomalocaris"+pEntity.getVariantName(pEntity.getVariant())+".png");
    }

    @Override
    protected void setupRotations(Anomalocaris pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
