package net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.BeholderModel;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Beholder;

public class BeholderRenderer extends MobRenderer<Beholder, BeholderModel<Beholder>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/abomination/beholder.png");

    public BeholderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BeholderModel<>(pContext.bakeLayer(MMModelLayers.BEHOLDER_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Beholder pEntity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(Beholder pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float partialTick = pAgeInTicks - pEntityLiving.tickCount;
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pEntityLiving.getInWaterMultiplier(partialTick)*(1-pEntityLiving.getOnGroundMultiplier(partialTick)), pEntityLiving.currentRoll*360/4, 0)));
    }
}
