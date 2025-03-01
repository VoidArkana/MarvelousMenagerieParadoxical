package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.SlovenicusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Slovenicus;

public class SlovenicusRenderer extends MobRenderer<Slovenicus, SlovenicusModel<Slovenicus>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/slovenicus.png");

    public SlovenicusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SlovenicusModel<>(pContext.bakeLayer(MMModelLayers.SLOVENICUS_LAYER)), 0.1f);
    }

    @Override
    public ResourceLocation getTextureLocation(Slovenicus pEntity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(Slovenicus pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
