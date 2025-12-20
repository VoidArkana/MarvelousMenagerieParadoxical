package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.DiplocaulusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Diplocaulus;

public class DiplocaulusRenderer extends MobRenderer<Diplocaulus, DiplocaulusModel<Diplocaulus>> {

    public DiplocaulusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DiplocaulusModel<>(pContext.bakeLayer(MMModelLayers.DIPLOCAULUS_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Diplocaulus pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/diplocaulus.png");
    }

    @Override
    protected void setupRotations(Diplocaulus pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
