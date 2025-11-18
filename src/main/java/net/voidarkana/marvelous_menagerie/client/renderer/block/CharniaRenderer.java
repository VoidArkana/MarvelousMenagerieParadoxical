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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.events.ForgeClientEvents;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.block.CharniaModel;
import net.voidarkana.marvelous_menagerie.common.block.custom.plant.CharniaBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.CharniaBlockEntity;

@OnlyIn(Dist.CLIENT)
public class CharniaRenderer implements BlockEntityRenderer<CharniaBlockEntity> {

    public static final Material CHARNIA_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS,
            new ResourceLocation(MarvelousMenagerie.MOD_ID, "entity/charnia"));

    private final CharniaModel charniaModel;

    public CharniaRenderer(BlockEntityRendererProvider.Context pContext) {
        this.charniaModel = new CharniaModel(pContext.bakeLayer(MMModelLayers.CHARNIA_LAYER));
    }

    @Override
    public void render(CharniaBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
            pPoseStack.translate(0, 1.5, 0);
            pPoseStack.mulPose(Axis.XN.rotationDegrees(180));
            pPoseStack.translate(0.5, 0, -0.5);
            int amount = pBlockEntity.getBlockState().getOptionalValue(CharniaBlock.PICKLES).get();
            int rotation = pBlockEntity.getBlockState().getOptionalValue(CharniaBlock.ROTATION_8).get();
            this.charniaModel.setupAnim( ForgeClientEvents.getClientTicks() + pPartialTick, amount, rotation);
            VertexConsumer vertexconsumer = CHARNIA_LOCATION.buffer(pBuffer, RenderType::entityTranslucentCull);
            this.charniaModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
    }
}
