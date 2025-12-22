package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.FlubberModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Flubber;

public class FlubberRenderer extends MobRenderer<Flubber, FlubberModel<Flubber>> {

    public FlubberRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FlubberModel<>(pContext.bakeLayer(MMModelLayers.FLUBBER_LAYER)), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Flubber pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/flubber.png");
    }

    @Override
    protected void setupRotations(Flubber pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pEntityLiving.getInWaterTicks()/5f, 0, pEntityLiving.currentRoll*360/4)));
    }
}
