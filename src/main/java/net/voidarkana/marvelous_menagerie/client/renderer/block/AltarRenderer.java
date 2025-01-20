package net.voidarkana.marvelous_menagerie.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.block.AltarSkullModel;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.AltarBlockEntity;

@OnlyIn(Dist.CLIENT)
public class AltarRenderer implements BlockEntityRenderer<AltarBlockEntity> {

    public static final Material SKULL_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS,
            new ResourceLocation(MarvelousMenagerie.MODID, "entity/chrono_altar_skull"));
    private final AltarSkullModel skullModel;

    public AltarRenderer(BlockEntityRendererProvider.Context pContext) {
        this.skullModel = new AltarSkullModel(pContext.bakeLayer(MMModelLayers.SKULL_LAYER));
    }

    @Override
    public void render(AltarBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0F, 0F, 0F);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(-180));
            pPoseStack.pushPose();
                pPoseStack.translate(-0.5F, -1.1F, 0.5F);
                float f = (float)pBlockEntity.time + pPartialTick;
                pPoseStack.translate(0.0F, -1.1F + Mth.sin(f * 0.1F) * 0.01F, 0.0F);

                float f1;
                for(f1 = pBlockEntity.rot - pBlockEntity.oRot; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) {
                }

                while(f1 < -(float)Math.PI) {
                    f1 += ((float)Math.PI * 2F);
                }

                float f2 = pBlockEntity.oRot + f1 * pPartialTick;
                pPoseStack.mulPose(Axis.YP.rotation(f2));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
                VertexConsumer vertexconsumer = SKULL_LOCATION.buffer(pBuffer, RenderType::entityCutoutNoCull);
                this.skullModel.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            pPoseStack.popPose();
        pPoseStack.popPose();
    }
}
