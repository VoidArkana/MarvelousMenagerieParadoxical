package net.voidarkana.marvelous_menagerie.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.voidarkana.marvelous_menagerie.common.block.custom.PaleontologyTableBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PaleontologyTableBlockEntity;

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
                matrixStackIn.mulPose(Axis.YP.rotationDegrees(180));

                matrixStackIn.pushPose();
                    matrixStackIn.translate(0, 0, 0);
                    if (tileEntityIn.getBlockState().getOptionalValue(PaleontologyTableBlock.FACING).get() == Direction.EAST){
                        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(270));
                        matrixStackIn.translate(-0.1, -0.15, 0);
                    }
                    if (tileEntityIn.getBlockState().getOptionalValue(PaleontologyTableBlock.FACING).get() == Direction.SOUTH){
                        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(180));
                        matrixStackIn.translate(0, -0.25, 0);
                    }
                    if (tileEntityIn.getBlockState().getOptionalValue(PaleontologyTableBlock.FACING).get() == Direction.WEST){
                        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90));
                        matrixStackIn.translate(0.1, -0.1, 0);
                    }

                    ir.renderStatic(tileEntityIn.stack, ItemDisplayContext.GROUND, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, mc.level, 0);
                matrixStackIn.popPose();
            matrixStackIn.popPose();
        }
    }
}
