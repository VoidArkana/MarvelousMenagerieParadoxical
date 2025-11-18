package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.EolactoriaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Eolactoria;

public class EolactoriaRenderer extends MobRenderer<Eolactoria, EolactoriaModel<Eolactoria>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/eolactoria.png");

    public EolactoriaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EolactoriaModel<>(pContext.bakeLayer(MMModelLayers.EOLACTORIA_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Eolactoria pEntity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(Eolactoria pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
