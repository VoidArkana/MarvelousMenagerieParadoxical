package net.voidarkana.marvelous_menagerie.client.renderer.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Rotation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.RiftModel;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class RiftRenderer extends LivingEntityRenderer<RiftEntity, RiftModel<RiftEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/fracture/rift.png");

    private final EntityRenderDispatcher renderer;

    public RiftRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RiftModel<>(pContext.bakeLayer(MMModelLayers.RIFT_LAYER)), 0);
        this.renderer = pContext.getEntityRenderDispatcher();
    }

    @Override
    public ResourceLocation getTextureLocation(RiftEntity pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(RiftEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        float f = (float)pEntity.tickCount + pPartialTicks;
        float f2 = Mth.sin(f * 0.1F) / 2.0F + 0.5F;
        f2 = f2 * f2 + f2;

        pPoseStack.pushPose();
            pPoseStack.translate(0, -0.1F + f2 * 0.05F, 0);
            pPoseStack.mulPose(Axis.XP.rotationDegrees((float) Math.cos(f*2)/6));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees((float) Math.sin(f*2)/6));
            pPoseStack.mulPose(Axis.YP.rotationDegrees((float) Math.sin(f/8)*2));
            super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
        pPoseStack.popPose();
    }

    @Override
    protected boolean shouldShowName(RiftEntity pEntity) {
        return false;
    }
}
