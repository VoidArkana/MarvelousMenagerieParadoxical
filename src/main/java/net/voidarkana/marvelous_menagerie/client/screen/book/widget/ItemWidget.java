package net.voidarkana.marvelous_menagerie.client.screen.book.widget;

import com.google.gson.annotations.Expose;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;
import net.voidarkana.marvelous_menagerie.client.renderer.item.MMItemstackRenderer;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.client.screen.book.PaleonomiconScreen;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWidget extends BookWidget {

    @Expose
    private String item;
    @Expose
    private String nbt;
    @Expose
    private boolean sepia;

    @Expose(serialize = false, deserialize = false)
    private ItemStack actualItem = ItemStack.EMPTY;

    private static final RenderType SEPIA_ITEM_RENDER_TYPE = MMRenderTypes.getBookWidget(TextureAtlas.LOCATION_BLOCKS, true);

    public ItemWidget(int displayPage, String item, String nbt, boolean sepia, int x, int y, float scale) {
        super(displayPage, Type.ITEM, x, y, scale);
        this.item = item;
        this.nbt = nbt;
        this.sepia = sepia;
    }

    public void render(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float partialTicks, boolean onFlippingPage, int mouseX, int mouseY) {
        if (actualItem == null && item != null) {
            actualItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)));
            if (nbt != null && !nbt.isEmpty()) {
                CompoundTag tag = null;
                try {
                    tag = TagParser.parseTag(nbt);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
                actualItem.setTag(tag);
            }
        }
        float scale = 16.0F * getScale();

        poseStack.pushPose();
        poseStack.translate(getX(), getY(), 0);
        poseStack.translate(0, 0, 50);
        renderItem(actualItem, poseStack, bufferSource, sepia, scale);
        poseStack.popPose();

    }

    public static void renderItem(ItemStack itemStack, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, boolean sepia, float scale){
        if(itemStack == null){
            return;
        }
        BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(itemStack, Minecraft.getInstance().level, null, 1);
        poseStack.pushPose();
        try {
            poseStack.scale(scale, scale, scale);
            PaleonomiconScreen.fixLighting();
            if (!sepia) {
                poseStack.mulPose(Axis.YP.rotationDegrees(180F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
            } else {
                poseStack.mulPose(Axis.ZN.rotationDegrees(180F));
                poseStack.scale(-1F, 1F, 1F);
                MMItemstackRenderer.sepiaFlag = true;
            }
            if (sepia && !bakedmodel.isCustomRenderer()) {
                renderSepiaItem(poseStack, bakedmodel, itemStack, bufferSource);
            } else {
                Minecraft.getInstance().getItemRenderer().render(itemStack, ItemDisplayContext.GUI, false, poseStack, bufferSource, 240, OverlayTexture.NO_OVERLAY, bakedmodel);
            }
            if (sepia) {
                MMItemstackRenderer.sepiaFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        poseStack.popPose();
    }

    public static void renderSepiaItem(PoseStack poseStack, BakedModel bakedmodel, ItemStack itemStack, MultiBufferSource.BufferSource bufferSource){
        poseStack.pushPose();
        bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(poseStack, bakedmodel, ItemDisplayContext.GUI, false);
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        for (net.minecraft.client.renderer.RenderType rt : bakedmodel.getRenderTypes(itemStack, false)) {
            renderModel(poseStack.last(), bufferSource.getBuffer(SEPIA_ITEM_RENDER_TYPE), 1.0F, null, bakedmodel, 1.0F, 1.0F, 1.0F, 240, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, rt);
        }
        poseStack.popPose();
    }

    private static void renderModel(PoseStack.Pose pose, VertexConsumer vertexConsumer, float alpha, @Nullable BlockState pState, BakedModel bakedModel, float r, float g, float b, int packedLight, int packedOverlay, ModelData modelData, net.minecraft.client.renderer.RenderType renderType) {
        RandomSource randomsource = RandomSource.create();
        long i = 42L;

        randomsource.setSeed(i);
        renderQuadList(pose, vertexConsumer, r, g, b, alpha, bakedModel.getQuads(null, Direction.UP, randomsource, modelData, renderType), packedLight, packedOverlay);
        renderQuadList(pose, vertexConsumer, r, g, b, alpha, bakedModel.getQuads(null, Direction.NORTH, randomsource, modelData, renderType), packedLight, packedOverlay);
        renderQuadList(pose, vertexConsumer, r, g, b, alpha, bakedModel.getQuads(null, Direction.EAST, randomsource, modelData, renderType), packedLight, packedOverlay);

        renderQuadList(pose, vertexConsumer, r, g, b, alpha, bakedModel.getQuads(null, (Direction) null, randomsource, modelData, renderType), packedLight, packedOverlay);
    }

    private static void renderQuadList(PoseStack.Pose pose, VertexConsumer vertexConsumer, float r, float g, float b, float alpha, List<BakedQuad> bakedQuads, int packedLight, int packedOverlay) {
        for (BakedQuad bakedquad : bakedQuads) {
            float f;
            float f1;
            float f2;
            f = Mth.clamp(r, 0.0F, 1.0F);
            f1 = Mth.clamp(g, 0.0F, 1.0F);
            f2 = Mth.clamp(b, 0.0F, 1.0F);
            vertexConsumer.putBulkData(pose, bakedquad, f, f1, f2, alpha, packedLight, packedOverlay, false);
        }
    }
}
