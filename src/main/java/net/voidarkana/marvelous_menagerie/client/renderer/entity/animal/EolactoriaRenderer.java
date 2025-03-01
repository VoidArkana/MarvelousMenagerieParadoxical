package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.EolactoriaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Eolactoria;

public class EolactoriaRenderer extends MobRenderer<Eolactoria, EolactoriaModel<Eolactoria>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/animal/eolactoria.png");

    public EolactoriaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EolactoriaModel<>(pContext.bakeLayer(MMModelLayers.EOLACTORIA_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Eolactoria pEntity) {
        return TEXTURE;
    }

//    @Override
//    public void render(Eolactoria pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        if(pEntity.isBaby()){
//            pPoseStack.scale(0.6f, 0.6f, 0.6f);
//        }
//        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
//    }

    @Override
    protected void setupRotations(Eolactoria pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
