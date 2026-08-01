package net.voidarkana.marvelous_menagerie.client.screen.book.widget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.renderer.rendertypes.MMRenderTypes;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.data.manager.RitualManager;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class EntityProfileWidget extends BookWidget {

    @Expose
    @SerializedName("entity_id")
    private String entityID;
    @Expose
    private boolean sepia;

    @Expose(serialize = false, deserialize = false)
    private ResourceLocation actualTexture;

    @Expose
    private int hearts;
    @Expose
    private int shields;
    @Expose
    private int attack;
    @Expose
    private boolean diet;
    @Expose
    private boolean tame;

    private static final int GRID_TEXTURE_SIZE = 128;

    private static final ResourceLocation FRAMES = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/summoning_ritual_frames.png");
    private static final ResourceLocation HEART = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/heart.png");
    private static final ResourceLocation SHIELD = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/armor.png");
    private static final ResourceLocation DIET = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/hunger.png");
    private static final ResourceLocation ATTACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/attack.png");
    private static final ResourceLocation TAME = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/tame.png");

    public EntityProfileWidget(int displayPage, String recipeId, boolean sepia, int x, int y, float scale) {
        super(displayPage, Type.ENTITY_PROFILE, x, y, scale);
        this.entityID = recipeId;
        this.sepia = sepia;
    }

    public void render(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float partialTicks, boolean onFlippingPage, int mouseX, int mouseY) {

        if (entityID != null) {

            if(this.actualTexture == null){
                String id = this.entityID.split(":")[1];

                this.actualTexture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/book/"+ id+"_book.png");
            }

            VertexConsumer vertexconsumer1 = bufferSource.getBuffer(MMRenderTypes.getBookWidget(this.actualTexture, sepia));
            float scale = getScale();
            int u0 = 0;
            int u1 = 128;
            int v0 = 0;
            int v1 = 128;
            float scaledU0_1 = u0 / (float)128;
            float scaledU1_1 = u1 / (float)128;
            float scaledV0_1 = v0 / (float)128;
            float scaledV1_1 = v1 / (float)128;
            float texWidth1 = (u1 - u0) / 2F;
            float texHeight1 = (v1 - v0) / 2F;
            float alpha = 1.0F;
            Lighting.setupFor3DItems();

            poseStack.pushPose();
            poseStack.translate(65, 75, -15);
            poseStack.pushPose();
            poseStack.scale(0.7f, 0.7f, scale);
            PoseStack.Pose posestack$pose1 = poseStack.last();
            Matrix4f matrix4f1 = posestack$pose1.pose();
            Matrix3f matrix3f1 = posestack$pose1.normal();
            vertexconsumer1.vertex(matrix4f1, texWidth1, -texHeight1, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(scaledU1_1, scaledV0_1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f1, 0.0F, 1.0F, 0.0F).endVertex();
            vertexconsumer1.vertex(matrix4f1, -texWidth1, -texHeight1, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(scaledU0_1, scaledV0_1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f1, 0.0F, 1.0F, 0.0F).endVertex();
            vertexconsumer1.vertex(matrix4f1, -texWidth1, texHeight1, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(scaledU0_1, scaledV1_1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f1, 0.0F, 1.0F, 0.0F).endVertex();
            vertexconsumer1.vertex(matrix4f1, texWidth1, texHeight1, 0.0F).color(1.0F, 1.0F, 1.0F, alpha).uv(scaledU1_1, scaledV1_1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f1, 0.0F, 1.0F, 0.0F).endVertex();
            poseStack.popPose();

            for (RitualManager.RitualProcessData data : RitualManager.DATA) {
                if (BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(entityID)) == data.output()){
                    Ingredient[] inputs = new Ingredient[4];
                    inputs[0] = data.input1();
                    inputs[1] = data.input2();
                    inputs[2] = data.input3();
                    inputs[3] = data.input4();

                    float itemScale = 16.0F;

                    VertexConsumer vertexconsumer = bufferSource.getBuffer(MMRenderTypes.getBookWidget(FRAMES, sepia));
                    poseStack.pushPose();
                    poseStack.translate(getX(), getY(), 0);
                    poseStack.scale(0.85f, 0.85f, 1);
                    poseStack.pushPose();
                    PoseStack.Pose posestack$pose = poseStack.last();
                    Matrix4f matrix4f = posestack$pose.pose();
                    Matrix3f matrix3f = posestack$pose.normal();
                    float scaledU1;
                    float scaledV1;
                    float texWidth;
                    float texHeight;

                    texWidth = 118 / 2F;
                    texHeight = 118 / 2F;
                    scaledU1 = 118 / (float)GRID_TEXTURE_SIZE;
                    scaledV1 = 118 / (float)GRID_TEXTURE_SIZE;

                    vertexconsumer.vertex(matrix4f, -texWidth, -texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0, 0).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
                    vertexconsumer.vertex(matrix4f, texWidth, -texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(scaledU1, 0).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(0, scaledV1).endVertex();
                    vertexconsumer.vertex(matrix4f, texWidth, texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(scaledU1, scaledV1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(0, 0).endVertex();
                    vertexconsumer.vertex(matrix4f, -texWidth, texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0, scaledV1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(scaledU1, 0).endVertex();
                    poseStack.popPose();

                    NonNullList<ItemStack> displayedStacks = NonNullList.create();

                    int renderY = 0;
                    int renderX = 0;
                    poseStack.pushPose();
                    poseStack.scale(0.9f, 0.9f, 1);
                    poseStack.translate(0, -0.1, 0);
                    for (int i = 0; i < 4; i++) {

                        ItemStack stack = getItemStack(inputs[i]);
                        if(i % 2 == 0 ){
                            renderX = 0;
                        }else{
                            renderX=1;
                        }

                        if(i > 1){
                            renderY=1;
                        }

                        if (!stack.isEmpty()) {

                            if (stack.is(Items.CHEST))
                                stack = new ItemStack(MMBlocks.CHEST_BOOK.get());
                            if (stack.is(MMBlocks.PALEO_TABLE.get().asItem()))
                                stack = new ItemStack(MMBlocks.PALEO_TABLE_BOOK.get());
                            if (stack.is(MMBlocks.CHRONO_ALTAR.get().asItem()))
                                stack = new ItemStack(MMBlocks.CHRONO_ALTAR_BOOK.get());
                            if (stack.is(MMBlocks.CHRONO_PEDESTAL.get().asItem()))
                                stack = new ItemStack(MMBlocks.CHRONO_PEDESTAL_BOOK.get());

                            poseStack.pushPose();
                            poseStack.translate(-54 + renderX * 107, -54 + renderY * 107, 0);
                            ItemWidget.renderItem(stack, poseStack, bufferSource, sepia, itemScale);
                            poseStack.popPose();
                        }
                        displayedStacks.add(i, stack);
                    }
                    poseStack.popPose();

                    break;
                }
            }

            if (hearts > 0){
                renderIcon(HEART, poseStack, bufferSource, 0, hearts);
            }
            if (attack > 0){
                renderIcon(ATTACK, poseStack, bufferSource, 1, attack);
            }
            if (shields > 0){
                renderIcon(SHIELD, poseStack, bufferSource, 2, shields);
            }
            if (tame){
                renderIcon(TAME, poseStack, bufferSource, 3, 0);
            }
            if (diet){
                renderIcon(DIET, poseStack, bufferSource, 4, 0);
            }

            poseStack.popPose();

        }
        poseStack.popPose();
    }

    public int getHearts(){
        return hearts;
    }

    public int getShields(){
        return shields;
    }

    public int getAttack(){
        return attack;
    }

    public boolean getTame(){
        return tame;
    }

    public boolean getDiet(){
        return diet;
    }

    private void renderIcon(ResourceLocation location, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, int lines, int amount) {
        VertexConsumer vertexconsumer = bufferSource.getBuffer(MMRenderTypes.getBookWidget(location, sepia));
        poseStack.pushPose();
        poseStack.translate(-48+(45 * (lines % 3)), 77+(16 * (int)((lines)/3)), 0);
        poseStack.scale(getScale(), getScale(), 20);
        poseStack.pushPose();
        poseStack.scale(1.5f, 1.5f, 1);
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        float scaledU1;
        float scaledV1;
        float texWidth;
        float texHeight;

        texWidth = 8;
        texHeight = 8;
        scaledU1 = 1;
        scaledV1 = 1;

        vertexconsumer.vertex(matrix4f, -texWidth, -texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0, 0).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
        vertexconsumer.vertex(matrix4f, texWidth, -texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(scaledU1, 0).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(0, scaledV1).endVertex();
        vertexconsumer.vertex(matrix4f, texWidth, texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(scaledU1, scaledV1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(0, 0).endVertex();
        vertexconsumer.vertex(matrix4f, -texWidth, texHeight, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).uv(0, scaledV1).overlayCoords(NO_OVERLAY).uv2(240).normal(matrix3f, 0.0F, 1.0F, 0.0F).uv(scaledU1, 0).endVertex();

        if (lines < 3 && amount > 0){
            poseStack.pushPose();
            poseStack.translate(5f, -6, 0);
            poseStack.scale(0.65f, 0.65f, 1F);
            poseStack.translate(-3 / 2F, 0, 0);

            Component title = Component.literal("x"+amount);
            Font font = Minecraft.getInstance().font;

            font.drawInBatch8xOutline(title.getVisualOrderText(), 0.0F, 0.0F, 0XFFE7BF, 0XAA977F, poseStack.last().pose(), bufferSource, 15728880);

            poseStack.popPose();
        }

        poseStack.popPose();
        poseStack.popPose();

    }

    public String getItemName(int index) {
        if (entityID != null) {

            for (RitualManager.RitualProcessData data : RitualManager.DATA) {
                if (BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(entityID)) == data.output()){
                    Ingredient[] inputs = new Ingredient[4];
                    inputs[0] = data.input1();
                    inputs[1] = data.input2();
                    inputs[2] = data.input3();
                    inputs[3] = data.input4();

                    ItemStack stack = getItemStack(inputs[index]);

                    return stack.getDisplayName().getString();
                }
            }
        }
        return "";
    }

    public static ItemStack getItemStack(Ingredient inputs) {
        Ingredient ing = inputs;
        ItemStack stack = ItemStack.EMPTY;
        float playerTicks = Minecraft.getInstance().player.tickCount;

        if (!ing.isEmpty()) {
            if (ing.getItems().length > 1) {
                int currentIndex = (int) ((playerTicks / 20F) % ing.getItems().length);
                stack = ing.getItems()[currentIndex];
            } else {
                stack = ing.getItems()[0];
            }
        }
        return stack;
    }

}
