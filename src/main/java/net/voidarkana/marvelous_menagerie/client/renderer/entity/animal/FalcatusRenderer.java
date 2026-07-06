package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.FalcatusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Falcatus;

public class FalcatusRenderer extends MobRenderer<Falcatus, FalcatusModel<Falcatus>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/falcatus/falcatus.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/falcatus/falcatus_baby.png");

    public FalcatusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FalcatusModel<>(pContext.bakeLayer(MMModelLayers.FALCATUS_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Falcatus pEntity) {
        return pEntity.isBaby() ? TEXTURE_BABY : TEXTURE;
    }

    @Override
    protected void setupRotations(Falcatus pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float partialTick = pAgeInTicks - pEntityLiving.tickCount;
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pEntityLiving.getInWaterMultiplier(partialTick)*(1-pEntityLiving.getOnGroundMultiplier(partialTick)), 0, pEntityLiving.currentRoll*360/4)));
    }
}
