package net.voidarkana.marvelous_menagerie.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.FractureModel;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import org.jetbrains.annotations.Nullable;

public class FractureRenderer extends LivingEntityRenderer<Fracture, FractureModel<Fracture>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/fracture.png");

    public FractureRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FractureModel<>(pContext.bakeLayer(MMModelLayers.FRACTURE_LAYER)), 0);
    }

    @Override
    public ResourceLocation getTextureLocation(Fracture pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(Fracture pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        pPoseStack.pushPose();
//            pPoseStack.translate(0, -1.2, 0);
//        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(Fracture pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucent(TEXTURE);
    }

    @Override
    protected boolean shouldShowName(Fracture pEntity) {
        return false;
    }
}
