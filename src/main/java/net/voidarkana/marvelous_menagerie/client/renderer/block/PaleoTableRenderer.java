package net.voidarkana.marvelous_menagerie.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PaleontologyTableBlockEntity;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PedestalBlockEntity;
import org.jetbrains.annotations.NotNull;

public class PaleoTableRenderer implements BlockEntityRenderer<PaleontologyTableBlockEntity> {

    public PaleoTableRenderer(BlockEntityRendererProvider.Context pContext) {
    }

    @Override
    public void render(PaleontologyTableBlockEntity tileEntityIn, float partialTicks, @NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer ir = mc.getItemRenderer();
        if (!tileEntityIn.stack.isEmpty()) {
            matrixStackIn.pushPose();
            matrixStackIn.translate(0.5, 0.87, 0.4);
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(90));
            ir.renderStatic(tileEntityIn.stack, ItemDisplayContext.GROUND, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, mc.level, 0);
            matrixStackIn.popPose();
        }
    }
}
