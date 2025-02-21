package net.voidarkana.marvelous_menagerie.client.renderer.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.client.screen.book.widget.ItemWidget;

public class MMItemstackRenderer extends BlockEntityWithoutLevelRenderer {

    public static boolean sepiaFlag = false;

    public MMItemstackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ClientLevel level = Minecraft.getInstance().level;
        float partialTick = Minecraft.getInstance().getPartialTick();
        boolean heldIn3d = transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
        boolean left = transformType == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || transformType == ItemDisplayContext.FIRST_PERSON_LEFT_HAND;

    }

    private void renderStaticItemSprite(ItemStack spriteItem, ItemDisplayContext transformType, int combinedLightIn, int combinedOverlayIn, PoseStack poseStack, MultiBufferSource bufferIn, ClientLevel level) {
        if(sepiaFlag){
            BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(spriteItem, Minecraft.getInstance().level, null, 0);
            ItemWidget.renderSepiaItem(poseStack, bakedmodel, spriteItem, Minecraft.getInstance().renderBuffers().bufferSource());
        }else{
            Minecraft.getInstance().getItemRenderer().renderStatic(spriteItem, transformType, transformType == ItemDisplayContext.GROUND ? combinedLightIn : 240, combinedOverlayIn, poseStack, bufferIn, level, 0);
        }
    }

    private void renderStaticItemSpriteWithLighting(ItemStack spriteItem, ItemDisplayContext transformType, int combinedLightIn, int combinedOverlayIn, PoseStack poseStack, MultiBufferSource bufferIn, ClientLevel level) {
        if(sepiaFlag){
            BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(spriteItem, Minecraft.getInstance().level, null, 0);
            ItemWidget.renderSepiaItem(poseStack, bakedmodel, spriteItem, Minecraft.getInstance().renderBuffers().bufferSource());
        }else{
            Minecraft.getInstance().getItemRenderer().renderStatic(spriteItem, transformType, transformType != ItemDisplayContext.GUI ? combinedLightIn : 240, combinedOverlayIn, poseStack, bufferIn, level, 0);
        }
    }

    private static VertexConsumer getVertexConsumerFoil(MultiBufferSource bufferIn, RenderType _default, ResourceLocation resourceLocation, boolean foil){
        return sepiaFlag ? bufferIn.getBuffer(MMRenderTypes.getBookWidget(resourceLocation, true)) : ItemRenderer.getFoilBuffer(bufferIn, _default, false, foil);
    }
    private static VertexConsumer getVertexConsumer(MultiBufferSource bufferIn, RenderType _default, ResourceLocation resourceLocation){
        return sepiaFlag ? bufferIn.getBuffer(MMRenderTypes.getBookWidget(resourceLocation, true)) : bufferIn.getBuffer(_default);
    }
}
