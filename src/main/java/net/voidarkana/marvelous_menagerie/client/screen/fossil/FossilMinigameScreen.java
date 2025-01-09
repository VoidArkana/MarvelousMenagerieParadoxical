package net.voidarkana.marvelous_menagerie.client.screen.fossil;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class FossilMinigameScreen extends Screen {

    public static final ResourceLocation GUI =
            new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/fossil/fossil_minigame.png");

    final int dirtSize = 176;
    int imageWidth;
    int imageHeight;
    int leftPos;
    int topPos;

    Player player;

    private boolean draggingChisel = false;
    private boolean draggingHammer = false;
    private boolean draggingPick = false;

    private float chiselPosXInit;
    private float chiselPosYInit;
    private float chiselPosX;
    private float chiselPosY;
    private float prevChiselPosX;
    private float prevChiselPosY;

    private float hammerPosXInit;
    private float hammerPosYInit;
    private float hammerPosX;
    private float hammerPosY;
    private float prevHammerPosX;
    private float prevHammerPosY;

    private float pickPosXInit;
    private float pickPosYInit;
    private float pickPosX;
    private float pickPosY;
    private float prevPickPosX;
    private float prevPickPosY;

    private int lastMouseX;
    private int lastMouseY;

    private int bone1TileX=0;
    private int bone1TileY=0;

    private int bone2TileX=0;
    private int bone2TileY=0;

    private int bone3TileX=0;
    private int bone3TileY=0;

    public Boolean isDud;

    public boolean[][] tileHasFossil = new boolean[7][7];
    public int[][] tileSoilLevel = new int[7][7];

    public FossilMinigameScreen(Player pPlayer) {
        super(Component.translatable("encyclopedia.title"));
        this.player = pPlayer;
        this.imageWidth = 241;
        this.imageHeight = dirtSize;
        this.plantFalseEvidence();
    }

    protected void plantFalseEvidence(){
        int tempxMed;
        int tempyMed;
        int tempxSmall;
        int tempySmall;
        int tempxBig;
        int tempyBig;
        int array;

        isDud = player.getRandom().nextInt(10)==0;

        //fossil medium
        array = player.getRandom().nextInt(3);
        if (array == 0) {
            tempxMed = player.getRandom().nextInt(3, 6);
            tempyMed = player.getRandom().nextInt(0, 2);

            tempxSmall = player.getRandom().nextInt(0, 3);
            tempySmall = player.getRandom().nextInt(0, 5);

            tempxBig = player.getRandom().nextInt(3, 5);
            tempyBig = player.getRandom().nextInt(3, 6);

        } else if (array == 1) {

            tempxMed = player.getRandom().nextInt(3, 6);
            tempyMed = player.getRandom().nextInt(3, 6);

            tempxSmall = player.getRandom().nextInt(3, 7);
            tempySmall = player.getRandom().nextInt(0, 2);

            tempxBig = 0;
            tempyBig = player.getRandom().nextInt(3, 6);
        }else {
            tempxMed = player.getRandom().nextInt(0, 2);
            tempyMed = player.getRandom().nextInt(0, 6);

            tempxSmall = player.getRandom().nextInt(3, 7);
            tempySmall = player.getRandom().nextInt(3, 6);

            tempxBig = player.getRandom().nextInt(3, 5);
            tempyBig = player.getRandom().nextInt(0, 2);
        }

        bone1TileX = tempxMed;
        bone1TileY = tempyMed;
        tileHasFossil[tempxMed][tempyMed] = true;
        tileHasFossil[tempxMed+1][tempyMed] = true;
        tileHasFossil[tempxMed][tempyMed+1] = true;
        tileHasFossil[tempxMed+1][tempyMed+1] = true;

        bone2TileX = tempxSmall;
        bone2TileY = tempySmall;
        tileHasFossil[tempxSmall][tempySmall] = true;
        tileHasFossil[tempxSmall][tempySmall+1] = true;

        bone3TileX = tempxBig;
        bone3TileY = tempyBig;

        tileHasFossil[tempxBig][tempyBig] = true;
        tileHasFossil[tempxBig+1][tempyBig] = true;
        tileHasFossil[tempxBig+2][tempyBig] = true;
        tileHasFossil[tempxBig][tempyBig+1] = true;
        tileHasFossil[tempxBig+1][tempyBig+1] = true;
        tileHasFossil[tempxBig+2][tempyBig+1] = true;

        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                if (tileHasFossil[x][y]){
                    if (this.player.getRandom().nextBoolean()){
                        tileSoilLevel[x][y] = this.player.getRandom().nextInt(2, 4);
                    }else {
                        if (this.player.getRandom().nextBoolean()){
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 4);
                        }else {
                            tileSoilLevel[x][y] = 0;
                        }
                    }
                }else {
                    if (this.player.getRandom().nextBoolean()){
                        if (this.player.getRandom().nextBoolean()){
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 4);
                        }else {
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 2);
                        }
                    }else {
                        tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 1);
                    }
                }
            }
        }
    }

    // 15x11 tiles, 2x2 offset

    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        chiselPosX = this.leftPos + 185;
        chiselPosXInit = prevChiselPosX = chiselPosX;
        chiselPosY = this.topPos + 63;
        chiselPosYInit = prevChiselPosY = chiselPosY;

        hammerPosX = this.leftPos + 185+18;
        hammerPosXInit = prevHammerPosX = hammerPosX;
        hammerPosY = this.topPos + 63;
        hammerPosYInit = prevHammerPosY = hammerPosY;

        pickPosX = this.leftPos + 185 + 18*2;
        pickPosXInit = prevPickPosX = pickPosX;
        pickPosY = this.topPos + 63;
        pickPosYInit = prevPickPosY = pickPosY;

//        for (SpelunkeryTableWordButton button : wordButtons) {
//            this.addRenderableWidget(button);
//        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        this.setFocused(null);
        int i = this.leftPos;
        int j = this.topPos;
        this.lastMouseX = pMouseX;
        this.lastMouseY = pMouseY;
        this.renderBG(pGuiGraphics, i, j);
        this.renderClockArm(pGuiGraphics, i, j);
        this.renderBones(pGuiGraphics);
        this.renderSoil(pGuiGraphics);
        this.renderTools(pGuiGraphics);
        this.renderSelection(pGuiGraphics, pMouseX, pMouseY);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    //13 x 13
    //7 x 7 uneven
    //6 x 6 even

    public void renderSoil(GuiGraphics pGuiGraphics){

        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                //dirt
                if (tileSoilLevel[x][y]>0){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208, 224, 16, 16);
                }
                //stone
                if (tileSoilLevel[x][y]>1){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208+16, 224, 16, 16);
                }
                //deepslate
                if (tileSoilLevel[x][y]>2){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208+32, 224, 16, 16);
                }

            }
        }
    }

    public void renderBones(GuiGraphics pGuiGraphics){
        //medium bone
        pGuiGraphics.blit(GUI, this.leftPos+32+(bone1TileX*16),
                this.topPos+32+(bone1TileY*16), isDud ? 0 : 48, 224, 32, 32);


        //small bone
        pGuiGraphics.blit(GUI, this.leftPos+32+(bone2TileX*16),
                this.topPos+32+(bone2TileY*16), 32, 224, 16, 32);


        //big bone
        pGuiGraphics.blit(GUI, this.leftPos+32+(bone3TileX*16),
                this.topPos+32+(bone3TileY*16), 80, 224, 48, 32);
    }

    public void renderSelection(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY){
        if (this.draggingChisel && pMouseX > this.leftPos+33 && pMouseX < this.leftPos+144
                && pMouseY > this.topPos+32 && pMouseY < this.topPos+140 ){
            pGuiGraphics.blit(GUI, getUnevenSlotX(pMouseX)*16+leftPos+32,
                    getUnevenSlotY(pMouseY)*16+this.topPos+32, 0, 192, 16, 16);
        }

        if (this.draggingPick && pMouseX > this.leftPos+33+16 && pMouseX < this.leftPos+144-16
                && pMouseY > this.topPos+32+16 && pMouseY < this.topPos+140-16){
            int slotX = getUnevenSlotX(pMouseX);
            int slotY = getUnevenSlotY(pMouseY);
            int pUoffset = 32;
            int pVOffset = 176;
            int width = 48;
            int height = 48;
            pGuiGraphics.blit(GUI, slotX*16+16+this.leftPos, slotY*16+16+this.topPos, pUoffset, pVOffset, width, height);
        }

        if (this.draggingHammer && pMouseX > this.leftPos+33+8 && pMouseX < this.leftPos+144-8
                && pMouseY > this.topPos+32+8 && pMouseY < this.topPos+140-8 ){
            pGuiGraphics.blit(GUI, getEvenSlotX(pMouseX)*16+leftPos+16,
                    getEvenSlotY(pMouseY)*16+this.topPos+16, 96, 176, 32, 32);
        }
    }

    public void renderTools(GuiGraphics pGuiGraphics){
        this.renderChisel(pGuiGraphics);
        this.renderHammer(pGuiGraphics);
        this.renderPick(pGuiGraphics);
    }

    public int getUnevenSlotX(int x){
        int totalX = ((x-this.leftPos)/16)-2;
        return totalX;
    }

    public int getUnevenSlotY(int y){
        int totalY = ((y-this.topPos)/16)-2;
        return totalY;
    }

    public int getEvenSlotX(int x){
        int totalX = ((x-this.leftPos+8)/16)-2;
        return totalX;
    }

    public int getEvenSlotY(int y){
        int totalY = ((y-this.topPos+8)/16)-2;
        return totalY;
    }

    public void renderBG(GuiGraphics pGuiGraphics, int i, int j){
        pGuiGraphics.blit(GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void renderClockArm(GuiGraphics pGuiGraphics, int i, int j){
        pGuiGraphics.blit(GUI, i+206, j+107, 169, 193, 5, 30);
    }

    public void renderChisel(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getChiselPosX(actualPartialTick);
        float lerpY = getChiselPosY(actualPartialTick);
        float zOffset = draggingChisel ? 500 : 200;
        this.renderTool(guiGraphics, lerpX, lerpY, zOffset, 208);
    }

    public void renderHammer(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getHammerPosX(actualPartialTick);
        float lerpY = getHammerPosY(actualPartialTick);
        float zOffset = draggingHammer ? 500 : 200;
        this.renderTool(guiGraphics, lerpX, lerpY, zOffset, 208+16);
    }

    public void renderPick(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getPickPosX(actualPartialTick);
        float lerpY = getPickPosY(actualPartialTick);
        float zOffset = draggingPick ? 500 : 200;
        this.renderTool(guiGraphics, lerpX, lerpY, zOffset, 208+32);
    }

    public  void renderTool(GuiGraphics guiGraphics, float lerpX, float lerpY, float zOffset, float u0){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, GUI);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        Matrix4f matrix4f = guiGraphics.pose().last().pose();
        float size = 16 / 256F;
        float u = u0 / 256F;
        float v = 240 / 256F;
        float minX = lerpX;
        float maxX = (lerpX + 16);
        float minY = lerpY;
        float maxY = (lerpY + 16);
        float minU = u;
        float maxu = (u + size);
        float minV = v;
        float maxV = (v + size);
        bufferbuilder.vertex(matrix4f, minX, minY, zOffset).uv(minU, minV).endVertex();
        bufferbuilder.vertex(matrix4f, minX, maxY, zOffset).uv(minU, maxV).endVertex();
        bufferbuilder.vertex(matrix4f, maxX, maxY, zOffset).uv(maxu, maxV).endVertex();
        bufferbuilder.vertex(matrix4f, maxX, minY, zOffset).uv(maxu, minV).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
    }

    public float getChiselPosX(float f) {
        return prevChiselPosX + (chiselPosX - prevChiselPosX) * f;
    }

    public float getChiselPosY(float f) {
        return prevChiselPosY + (chiselPosY - prevChiselPosY) * f;
    }

    public float getHammerPosX(float f) {
        return prevHammerPosX + (hammerPosX - prevHammerPosX) * f;
    }

    public float getHammerPosY(float f) {
        return prevHammerPosY + (hammerPosY - prevHammerPosY) * f;
    }

    public float getPickPosX(float f) {
        return prevPickPosX + (pickPosX - prevPickPosX) * f;
    }

    public float getPickPosY(float f) {
        return prevPickPosY + (pickPosY - prevPickPosY) * f;
    }

    public boolean isPauseScreen() {
        return false;
    }

    public final void tick() {
        super.tick();
        if (this.minecraft.player.isAlive() && !this.minecraft.player.isRemoved()) {
            this.containerTick();
        } else {
            this.minecraft.player.closeContainer();
        }
    }

    protected void containerTick() {

        this.prevChiselPosX = chiselPosX;
        this.prevChiselPosY = chiselPosY;
        int targetChiselX;
        int targetChiselY;
        if (draggingChisel) {
            targetChiselX = lastMouseX - 13;
            targetChiselY = lastMouseY + 2;
        } else {
            targetChiselX = (int) this.chiselPosXInit;
            targetChiselY = (int) this.chiselPosYInit;
        }
        Vec3 chiselVec3 = new Vec3(targetChiselX - this.chiselPosX, targetChiselY - this.chiselPosY, 0.0);

        this.chiselPosX += (float) chiselVec3.x;
        this.chiselPosY += (float) chiselVec3.y;



        this.prevHammerPosX = hammerPosX;
        this.prevHammerPosY = hammerPosY;

        int targetHammerX;
        int targetHammerY;
        if (draggingHammer) {
            targetHammerX = lastMouseX - 7;
            targetHammerY = lastMouseY - 3;
        } else {
            targetHammerX = (int) this.hammerPosXInit;
            targetHammerY = (int) this.hammerPosYInit;
        }
        Vec3 hammerVec3 = new Vec3(targetHammerX - this.hammerPosX, targetHammerY - this.hammerPosY, 0.0);
        this.hammerPosX += (float) hammerVec3.x;
        this.hammerPosY += (float) hammerVec3.y;


        this.prevPickPosX = pickPosX;
        this.prevPickPosY = pickPosY;

        int targetPickX;
        int targetPickY;
        if (draggingPick) {
            targetPickX = lastMouseX - 13;
            targetPickY = lastMouseY - 9;
        } else {
            targetPickX = (int) this.pickPosXInit;
            targetPickY = (int) this.pickPosYInit;
        }
        Vec3 pickVec3 = new Vec3(targetPickX - this.pickPosX, targetPickY - this.pickPosY, 0.0);
        this.pickPosX += (float) pickVec3.x;
        this.pickPosY += (float) pickVec3.y;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean prev = super.mouseClicked(mouseX, mouseY, button);
        if (!prev) {
            if (this.lastMouseX >= this.chiselPosXInit && this.lastMouseX <= this.chiselPosXInit + 16 && this.lastMouseY >= this.chiselPosYInit && this.lastMouseY <= this.chiselPosYInit + 16) {
                this.draggingChisel= !this.draggingChisel;

                if (this.draggingChisel){
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212995);
                }else {
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }

                if (this.draggingHammer)
                    this.draggingHammer = false;
                if (this.draggingPick)
                    this.draggingPick = false;
            }
            if (this.lastMouseX >= this.hammerPosXInit && this.lastMouseX <= this.hammerPosXInit + 16 && this.lastMouseY >= this.hammerPosYInit && this.lastMouseY <= this.hammerPosYInit + 16) {
                this.draggingHammer = !this.draggingHammer;

                if (this.draggingHammer){
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212995);
                }else {
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }

                if (this.draggingPick)
                    this.draggingPick = false;
                if (this.draggingChisel)
                    this.draggingChisel = false;
            }
            if (this.lastMouseX >= this.pickPosXInit && this.lastMouseX <= this.pickPosXInit + 16 && this.lastMouseY >= this.pickPosYInit && this.lastMouseY <= this.pickPosYInit + 16) {
                this.draggingPick = !this.draggingPick;

                if (this.draggingPick){
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212995);
                }else {
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }

                if (this.draggingChisel)
                    this.draggingChisel = false;
                if (this.draggingHammer)
                    this.draggingHammer = false;
            }
        }
        return prev;
    }

    @Override
    public void onClose() {
        super.onClose();
        this.player.playSound(SoundEvents.SHULKER_BOX_CLOSE);
    }

    //    private void drawFish(GuiGraphics guiGraphics, int centerX, int centerY, float radius) {
//        PoseStack poseStack = guiGraphics.pose();
//        poseStack.pushPose();
//        double x = radius * Math.cos(-(fishDeg / 180d) * Math.PI);
//        double y = radius * Math.sin(-(fishDeg / 180d) * Math.PI);
//        poseStack.translate(centerX + x, centerY + y, 0);
//        poseStack.mulPose(Axis.ZP.rotationDegrees(90 - fishDeg));
//        guiGraphics.blit(TEX, -2, -5, 326, fishSpeed < 0 ? 0 : 6, 11, 6, 512, 512);
//        poseStack.popPose();
//    }

}
