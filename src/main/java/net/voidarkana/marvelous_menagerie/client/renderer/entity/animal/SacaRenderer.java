package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;

public class SacaRenderer extends MobRenderer<Sacabambaspis, SacaModel<Sacabambaspis>> {

    public SacaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SacaModel<>(pContext.bakeLayer(MMModelLayers.SACA_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Sacabambaspis pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/sacabambaspis/saca"+pEntity.getVariantName(pEntity.getVariant())+".png");
    }

    @Override
    protected void setupRotations(Sacabambaspis pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
