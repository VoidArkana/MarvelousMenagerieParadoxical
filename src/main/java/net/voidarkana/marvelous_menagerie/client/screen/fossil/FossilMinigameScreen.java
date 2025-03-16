package net.voidarkana.marvelous_menagerie.client.screen.fossil;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.guiparticle.*;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.FossilBlockEntity;
import net.voidarkana.marvelous_menagerie.util.Mathf;
import net.voidarkana.marvelous_menagerie.util.network.ChangeFossilMessage;
import net.voidarkana.marvelous_menagerie.util.network.MMMessages;
import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class FossilMinigameScreen extends Screen {


    public GUIParticleSystem particles;

    public static final ResourceLocation GUI =
            new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/fossil/fossil_minigame.png");

    public static final ResourceLocation VIGNETTE =
            new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/fossil/fossil_minigame_2.png");

    final int dirtSize = 176;
    int imageWidth;
    int imageHeight;
    int leftPos;
    int topPos;

    Player player;

    BlockPos clickedPos;

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

    int bone1TileX=0;
    int bone1TileY=0;
    private int bone1Damage=0;
    private boolean bone1Undiscovered=true;

    int bone2TileX=0;
    int bone2TileY=0;
    private int bone2Damage=0;
    private boolean bone2Undiscovered=true;

    int bone3TileX=0;
    int bone3TileY=0;
    private int bone3Damage=0;
    private boolean bone3Undiscovered=true;

    boolean hasAppliedDamage;

    int maxHits = 15;
    int currentHits; //multiply by 24

    public Boolean isDud;

    int successLevel = 0;

    public int[][] tileHasFossil = new int[7][7];
    public int[][] tileSoilLevel = new int[7][7];
    public int[][] dirtParticleTicks = new int[7][7];
    public int[][] stoneParticleTicks = new int[7][7];
    public int[][] deepslateParticleTicks = new int[7][7];

    int fossilProgress1;
    int fossilProgress2;
    int fossilProgress3;

    double lastClicked1X;
    double lastClicked1Y;

    double lastClicked2X;
    double lastClicked2Y;

    double lastClicked3X;
    double lastClicked3Y;

    int shakeProgress = 0;
    double shakeAmount = 0;
    int shakeX = 1;
    int shakeY = 1;

    public FossilMinigameScreen(Player pPlayer, BlockPos clicked) {
        super(Component.translatable("fossil.title"));
        this.player = pPlayer;
        this.imageWidth = 241;
        this.imageHeight = dirtSize;
        this.plantFalseEvidence();
        this.currentHits = 0;
        this.clickedPos = clicked;
        particles = new GUIParticleSystem();
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

        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                tileHasFossil[x][y] = 0;
                dirtParticleTicks[x][y] = 0;
                stoneParticleTicks[x][y] = 0;
                deepslateParticleTicks[x][y] = 0;
            }
        }

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
        tileHasFossil[tempxMed][tempyMed] = 1;
        tileHasFossil[tempxMed+1][tempyMed] = 1;
        tileHasFossil[tempxMed][tempyMed+1] = 1;
        tileHasFossil[tempxMed+1][tempyMed+1] = 1;

        bone2TileX = tempxSmall;
        bone2TileY = tempySmall;
        tileHasFossil[tempxSmall][tempySmall] = 2;
        tileHasFossil[tempxSmall][tempySmall+1] = 2;

        bone3TileX = tempxBig;
        bone3TileY = tempyBig;

        tileHasFossil[tempxBig][tempyBig] = 3;
        tileHasFossil[tempxBig+1][tempyBig] = 3;
        tileHasFossil[tempxBig+2][tempyBig] = 3;
        tileHasFossil[tempxBig][tempyBig+1] = 3;
        tileHasFossil[tempxBig+1][tempyBig+1] = 3;
        tileHasFossil[tempxBig+2][tempyBig+1] = 3;

        int spreadChanceX;
        int spreadChanceY;

        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                if (tileHasFossil[x][y]>0){
                    if (this.player.getRandom().nextBoolean()){
                        tileSoilLevel[x][y] = this.player.getRandom().nextInt(2, 4);
                    }else {
                        if (this.player.getRandom().nextBoolean()){
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(1, 4);
                        }else {
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 2);
                        }
                    }
                }else {

                    if (this.player.getRandom().nextBoolean()){
                        if (this.player.getRandom().nextBoolean()){
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(1, 4);
                        }else {
                            tileSoilLevel[x][y] = this.player.getRandom().nextInt(1, 3);
                        }
                    }else {
                        tileSoilLevel[x][y] = this.player.getRandom().nextInt(0, 2);
                    }

                    spreadChanceX = this.player.getRandom().nextInt(6);
                    spreadChanceY = this.player.getRandom().nextInt(6);
                    if (x>0){
                        if (((tileSoilLevel[x-1][y]>0 && tileSoilLevel[x-1][y]<3) ||
                                (tileSoilLevel[x-1][y]==3 && player.getRandom().nextBoolean()))
                                && spreadChanceX==0){
                            tileSoilLevel[x][y] = tileSoilLevel[x-1][y];
                        }
                    }
                    if (y>0){
                        if (((tileSoilLevel[x][y-1]>0 && tileSoilLevel[x][y-1]<3) ||
                                (tileSoilLevel[x][y-1]==3 && player.getRandom().nextBoolean()))
                                && spreadChanceY==0){
                            tileSoilLevel[x][y] = tileSoilLevel[x][y-1];
                        }
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

//        attractionPoint = (ParticleAttractor)new ParticleAttractor(200,this.leftPos+88, this.topPos+176,0.1f).setStartTime(5).setToLayer(1);
//        //repulsionPoint = (ParticleAttractor)new ParticleAttractor(40,leftPos+88,this.topPos+88+50,-0.1f).setEndTime(50).setToLayer(1);
//        particles.addAffector(attractionPoint);
        //particles.addAffector(ParticleDeleter.create(85,this.leftPos+88,this.topPos+88).setInvert(true).setToLayer(1));
        //particles.addAffector(repulsionPoint);
//        particles.addAffector(new ParticleAccelerator(100,this.leftPos+88,this.topPos+50,0.90f).setEndTime(50).setToLayer(1));
//        particles.addAffector(new ParticleAttractor(100,this.leftPos+88,this.topPos+50,0.15f));
//        particles.addAffector(new ParticleAccelerator(100,this.leftPos+88,this.topPos+50,0.97f));

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

        particles.update();
        //this.updateLogic();

        this.setFocused(null);
        this.renderBackground(pGuiGraphics);

        pGuiGraphics.pose().pushPose();
            pGuiGraphics.pose().translate((shakeAmount)*shakeX, (shakeAmount)*shakeY, 0.0F);
            int i = this.leftPos;
            int j = this.topPos;
            this.lastMouseX = pMouseX;
            this.lastMouseY = pMouseY;

            pGuiGraphics.blit(GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);

            this.renderClockArm(pGuiGraphics);
            this.renderBones(pGuiGraphics);
            this.renderSoil(pGuiGraphics);

            this.drawTexturedQuadColor(pGuiGraphics, VIGNETTE, this.leftPos+32, this.leftPos+32+112,
                    this.topPos+32, this.topPos+32+112, 0, 0, (float) 112 /256, 0, (float) 112 /256,
                    0, 0, 0, 0.8f);

            this.renderTools(pGuiGraphics);
            this.renderSelection(pGuiGraphics, pMouseX, pMouseY);
            this.renderParticles(pGuiGraphics);
        pGuiGraphics.pose().popPose();

        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((float)i, (float)j, 0.0F);
        RenderSystem.setShaderTexture(0, GUI);
        pGuiGraphics.pose().popPose();

        RenderSystem.disableDepthTest();
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((float)i, (float)j, 0.0F);
        pGuiGraphics.pose().popPose();
        RenderSystem.enableDepthTest();

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

//    public void updateLogic(){
//
//    }

    //13 x 13
    //7 x 7 uneven
    //6 x 6 even

    public void renderSoil(GuiGraphics pGuiGraphics){
        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                //dirt
                if (tileSoilLevel[x][y]>0){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208, 224-16, 16, y == 6 ? 16 : 20);
                }
            }
        }
        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                //stone
                if (tileSoilLevel[x][y]>1){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208+16, 224-16, 16, y == 6 ? 16 : 20);
                }

            }
        }
        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                //deepslate
                if (tileSoilLevel[x][y]>2){
                    pGuiGraphics.blit(GUI, this.leftPos+32+(x*16),
                            this.topPos+32+(y*16), 208+32, 224-16, 16, y == 6 ? 16 : 20);
                }
            }
        }
    }

    public void renderBones(GuiGraphics pGuiGraphics){
        //medium bone
        if (bone1Damage<2 && (bone1Undiscovered || fossilProgress1>0)){

            PoseStack poseStack = pGuiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(this.leftPos+32+(bone1TileX*16)+16, this.topPos+32+(bone1TileY*16)+16, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress1*(360/10f)));
            if (fossilProgress1>0)
                poseStack.scale(((float) fossilProgress1/10), ((float) fossilProgress1/10), ((float) fossilProgress1/10));
            pGuiGraphics.blit(VIGNETTE, -16, -16, isDud ? 0 : 48, 192+(32*bone1Damage), 32, 32);
            poseStack.popPose();
        }

        //small bone
        if (bone2Damage<2 && (bone2Undiscovered || fossilProgress2>0)){
            PoseStack poseStack = pGuiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(this.leftPos+32+(bone2TileX*16)+8, this.topPos+32+(bone2TileY*16)+16, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress2*(360/10f)));
            if (fossilProgress2>0)
                poseStack.scale(((float) fossilProgress2/10), ((float) fossilProgress2/10), ((float) fossilProgress2/10));
            pGuiGraphics.blit(VIGNETTE, -8, -16, 32, 192+(32*bone2Damage), 16, 32);
            poseStack.popPose();
        }

        //big bone
        if (bone3Damage<2 && (bone3Undiscovered || fossilProgress3>0)){
            PoseStack poseStack = pGuiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(this.leftPos+32+(bone3TileX*16)+16+8, this.topPos+32+(bone3TileY*16)+16, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress3*(360/10f)));
            if (fossilProgress3>0)
                poseStack.scale(((float) fossilProgress3/10), ((float) fossilProgress3/10), ((float) fossilProgress3/10));
            pGuiGraphics.blit(VIGNETTE, -16-8, -16, 80, 192+(32*bone3Damage), 48, 32);
            poseStack.popPose();
        }

        //medium bone
//        if (bone1Damage<2 && (bone1Undiscovered || fossilProgress1 > 0)){
//            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, this.leftPos+32+(bone1TileX*16), this.leftPos+32+(bone1TileX*16)+32,
//                    this.topPos+32+(bone1TileY*16), this.topPos+32+(bone1TileY*16)+32, 0,
//                    isDud ? 0 : 48/256F, isDud ? 32/256F : (48+32)/256F, (192+(32*bone1Damage))/256F,
//                    (192+32+(32*bone1Damage))/256F, 1, 1, 1, 1f);
//        }
//
//        //small bone
//        if (bone2Damage<2 && (bone2Undiscovered || fossilProgress2 > 0)){
//            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, this.leftPos+32+(bone2TileX*16), this.leftPos+32+(bone2TileX*16)+16,
//                    this.topPos+32+(bone2TileY*16), this.topPos+32+(bone2TileY*16)+32, 0,
//                    32/256F, (32+16)/256F, (192+(32*bone2Damage))/256F,
//                    (192+32+(32*bone2Damage))/256F, 1, 1, 1, 1f);
//        }
//
//        //big bone
//        if (bone3Damage<2 && (bone3Undiscovered || fossilProgress3 > 0)){
//            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, this.leftPos+32+(bone3TileX*16), this.leftPos+32+(bone3TileX*16)+48,
//                    this.topPos+32+(bone3TileY*16), this.topPos+32+(bone3TileY*16)+32, 0,
//                    80/256F, (80+48)/256F, (192+(32*bone3Damage))/256F,
//                    (192+32+(32*bone3Damage))/256F, 1, 1, 1, 1f);
//        }
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
        return Math.max(Math.min(totalX, 6), 0);
    }

    public int getUnevenSlotY(int y){
        int totalY = ((y-this.topPos)/16)-2;
        return Math.max(Math.min(totalY, 6), 0);
    }

    public int getEvenSlotX(int x){
        int totalX = ((x-this.leftPos+8)/16)-2;
        return Math.max(Math.min(totalX, 6), 0);
    }

    public int getEvenSlotY(int y){
        int totalY = ((y-this.topPos+8)/16)-2;
        return Math.max(Math.min(totalY, 6), 0);
    }

    public void renderParticles(GuiGraphics pGuiGraphics){
        PoseStack poseStack = pGuiGraphics.pose();

        RenderSystem.setShaderTexture(0, GUI);

        pGuiGraphics.pose().pushPose();
            particles.draw(poseStack, this);
        pGuiGraphics.pose().popPose();


        if (fossilProgress1>0){
            pGuiGraphics.pose().pushPose();
            poseStack.translate(lastClicked1X, lastClicked1Y, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress1*(360/10f)));
            poseStack.scale(((float) 1/fossilProgress1)+0.5f, ((float) 1/fossilProgress1)+0.5f, ((float) 1/fossilProgress1)+0.5f);
            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, -16, 16, -16, 16, 1,
                    0, 32/256F, 112/256F, (112+32)/256F, 1, 1, 1, ((float) fossilProgress1 /10));
            pGuiGraphics.pose().popPose();
        }

        if (fossilProgress2>0){
            pGuiGraphics.pose().pushPose();
            poseStack.translate(lastClicked2X, lastClicked2Y, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress2*(360/10f)));
            poseStack.scale(((float) 1/fossilProgress2)+0.5f, ((float) 1/fossilProgress2)+0.5f, ((float) 1/fossilProgress2)+0.5f);
            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, -16, 16, -16, 16, 1,
                    0, 32/256F, 112/256F, (112+32)/256F, 1, 1, 1, ((float) fossilProgress2 /10));
            pGuiGraphics.pose().popPose();
        }

        if (fossilProgress3>0){
            pGuiGraphics.pose().pushPose();
            poseStack.translate(lastClicked3X, lastClicked3Y, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(fossilProgress3*(360/10f)));
            poseStack.scale(((float) 1/fossilProgress3)+0.5f, ((float) 1/fossilProgress3)+0.5f, ((float) 1/fossilProgress3)+0.5f);
            drawTexturedQuadColor(pGuiGraphics, VIGNETTE, -16, 16, -16, 16, 1,
                    0, 32/256F, 112/256F, (112+32)/256F, 1, 1, 1, ((float) fossilProgress3 /10));
            pGuiGraphics.pose().popPose();
        }

    }

    public void renderClockArm(GuiGraphics pGuiGraphics){
        PoseStack poseStack = pGuiGraphics.pose();
        poseStack.pushPose();
        float centerX = this.leftPos+208.5f;
        float centerY = this.topPos+119.5f;
        poseStack.translate(centerX, centerY, 0);
            float actualPartialTick = Minecraft.getInstance().getFrameTime();
            float red = (float) ((Math.sin((player.tickCount + actualPartialTick) * 0.2F * (currentHits-12))+1)/2);
            if (currentHits > maxHits-3){
                poseStack.scale(1*red/2.5f+1, 1*red/2.5f+1, 1*red/2.5f+1);
            }
//            drawTexturedQuadColor(pGuiGraphics, GUI, -16.5f, 16.5f, -22.5f, 18.5f, 0,
//                    192/256F, (192+33)/256F, 97/256F, (97+41)/256F,
//                    1, currentHits > maxHits-3 ? red : 1, currentHits > maxHits-3 ? red : 1, 1f);

            poseStack.pushPose();
                poseStack.mulPose(Axis.ZP.rotationDegrees(Math.min(360, currentHits*24)));
                drawTexturedQuadColor(pGuiGraphics, GUI, -3, 3, -13, 13, 0,
                        169/256F, (169+6)/256F, 194/256F, (194+26)/256F,
                        1, currentHits > maxHits-3 ? red : 1, currentHits > maxHits-3 ? red : 1, 1f);
            poseStack.popPose();
        poseStack.popPose();
    }

    public void renderChisel(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getChiselPosX(actualPartialTick);
        float lerpY = getChiselPosY(actualPartialTick);
        float zOffset = draggingChisel ? 500 : 200;
        drawTexturedQuadColor(guiGraphics, GUI, lerpX, lerpX+16, lerpY, lerpY+16, zOffset,
                208/256F, (208+16)/256F, 240/256F, 1, 1, 1, 1, 1f);
    }

    public void renderHammer(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getHammerPosX(actualPartialTick);
        float lerpY = getHammerPosY(actualPartialTick);
        float zOffset = draggingHammer ? 500 : 200;
        drawTexturedQuadColor(guiGraphics, GUI, lerpX, lerpX+16, lerpY, lerpY+16, zOffset,
                (208+16)/256F, (208+32)/256F, 240/256F, 1, 1, 1, 1, 1f);
    }

    public void renderPick(GuiGraphics guiGraphics){
        float actualPartialTick = Minecraft.getInstance().getFrameTime();
        float lerpX = getPickPosX(actualPartialTick);
        float lerpY = getPickPosY(actualPartialTick);
        float zOffset = draggingPick ? 500 : 200;
        drawTexturedQuadColor(guiGraphics, GUI, lerpX, lerpX+16, lerpY, lerpY+16, zOffset,
                (208+32)/256F, (208+32+16)/256F, 240/256F, 1, 1, 1, 1, 1f);
    }

    public void drawTexturedQuadColor(GuiGraphics pGuiGraphics, ResourceLocation pAtlasLocation, float pX1, float pX2,
                                      float pY1, float pY2, float pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV, float pRed, float pGreen, float pBlue, float pAlpha) {
        RenderSystem.setShaderTexture(0, pAtlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.enableBlend();
        Matrix4f matrix4f = pGuiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix4f, pX1, pY1, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMinU, pMinV).endVertex();
        bufferbuilder.vertex(matrix4f, pX1, pY2, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMinU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, pX2, pY2, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMaxU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, pX2, pY1, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMaxU, pMinV).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
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
        if (this.currentHits>this.maxHits){
            this.minecraft.player.closeContainer();
            this.player.playSound(SoundEvents.ITEM_BREAK);
            this.breakFossils();
        }
        if (this.successLevel>=3){
            this.minecraft.player.closeContainer();
            this.player.playSound(SoundEvents.PLAYER_LEVELUP);
            this.breakFossils();
        }
        if (this.minecraft.player.isAlive() && !this.minecraft.player.isRemoved()) {
            this.containerTick();
        } else {
            this.minecraft.player.closeContainer();
        }
    }

    protected void containerTick() {

        if (shakeProgress>0){
            shakeProgress--;
            shakeAmount = -shakeAmount*0.75;
        }

        if (fossilProgress1>0)
            fossilProgress1--;

        if (fossilProgress2>0)
            fossilProgress2--;

        if (fossilProgress3>0)
            fossilProgress3--;

        for (int x = 0; x<7; x++){
            for (int y = 0; y<7; y++){
                if (dirtParticleTicks[x][y]>0)
                    dirtParticleTicks[x][y]--;

                if (stoneParticleTicks[x][y]>0)
                    stoneParticleTicks[x][y]--;

                if (deepslateParticleTicks[x][y]>0)
                    deepslateParticleTicks[x][y]--;
            }
        }

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
            if (button == 1){
                if (this.draggingPick){
                    this.draggingPick = false;
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }
                if (this.draggingHammer){
                    this.draggingHammer = false;
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }
                if (this.draggingChisel){
                    this.draggingChisel = false;
                    GLFW.glfwSetInputMode(this.minecraft.getWindow().getWindow(), 208897, 212993);
                }
            }else {

                //selecting or unselecting tools
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

                //destroying blocks
                if (this.draggingChisel && mouseX > this.leftPos+33 && mouseX < this.leftPos+144 && mouseY > this.topPos+32 && mouseY < this.topPos+140){
                    if (tileSoilLevel[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]>0){
                        switch (tileSoilLevel[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]){
                            case 1:
                                player.playSound(SoundEvents.GRAVEL_BREAK);
                                break;
                            case 2:
                                player.playSound(SoundEvents.STONE_BREAK);
                                break;
                            default:
                                player.playSound(SoundEvents.DEEPSLATE_BREAK);
                        }
                        makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY),
                                tileSoilLevel[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]);

                        tileSoilLevel[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]--;
                        currentHits++;
                    } else if (tileHasFossil[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]>0) {
                        switch (tileHasFossil[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]){
                            case 1:
                                if (bone1Undiscovered && bone1Damage<2){
                                    bone1Damage++;
                                    makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                    damageFossil();
                                }
                                break;
                            case 2:
                                if (bone2Undiscovered && bone2Damage<2){
                                    bone2Damage++;
                                    makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                    damageFossil();
                                }
                                break;
                            default:
                                if (bone3Undiscovered && bone3Damage<2){
                                    bone3Damage++;
                                    makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                    damageFossil();
                                }
                        }
                    }
                }
                hasAppliedDamage = false;
                int hits = 0;

                if (this.draggingPick && mouseX > this.leftPos+33+16 && mouseX < this.leftPos+144-16
                        && mouseY > this.topPos+32+16 && mouseY < this.topPos+140-16){
                    for (int x = -1; x<2; x++){
                        for (int y = -1; y<2; y++){
                            if (x==y && x==0){
                                hasAppliedDamage = false;
                            }
                            if ((x == 0 && y == 0) || (x!=0 && y==0) || (x==0 && y!=0)){
                                if (tileSoilLevel[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]>0){
                                    switch (tileSoilLevel[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]){
                                        case 1:
                                            player.playSound(SoundEvents.GRAVEL_BREAK);
                                            break;
                                        case 2:
                                            player.playSound(SoundEvents.STONE_BREAK);
                                            break;
                                        default:
                                            player.playSound(SoundEvents.DEEPSLATE_BREAK);
                                    }

                                    makeBlockParticles(getUnevenSlotX((int) mouseX)+x, getUnevenSlotY((int) mouseY)+y,
                                            tileSoilLevel[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]);

                                    tileSoilLevel[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]--;
                                    hits=1;
                                }else if (tileHasFossil[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]>0 && !hasAppliedDamage){
                                    switch (tileHasFossil[getUnevenSlotX((int) mouseX)+x][getUnevenSlotY((int) mouseY)+y]){
                                        case 1:
                                            if (bone1Undiscovered && bone1Damage<2){
                                                bone1Damage++;
                                                makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                                damageFossil();
                                            }
                                            break;
                                        case 2:
                                            if (bone2Undiscovered && bone2Damage<2){
                                                bone2Damage++;
                                                makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                                damageFossil();
                                            }
                                            break;
                                        default:
                                            if (bone3Undiscovered && bone3Damage<2){
                                                bone3Damage++;
                                                makeBlockParticles(getUnevenSlotX((int) mouseX), getUnevenSlotY((int) mouseY), 0);
                                                damageFossil();
                                            }
                                    }
                                    hasAppliedDamage = true;
                                }
                            }
                        }
                    }
                    currentHits+=hits;
                }

                if (this.draggingHammer && mouseX > this.leftPos+33+8 && mouseX < this.leftPos+144-8
                        && mouseY > this.topPos+32+8 && mouseY < this.topPos+140-8 ){
                    for (int x = 0; x<2; x++){
                        for (int y = 0; y<2; y++){
                            if (tileSoilLevel[getEvenSlotX((int) mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]>0){
                                switch (tileSoilLevel[getEvenSlotX((int) mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]){
                                    case 1:
                                        player.playSound(SoundEvents.GRAVEL_BREAK);
                                        break;
                                    case 2:
                                        player.playSound(SoundEvents.STONE_BREAK);
                                        break;
                                    default:
                                        player.playSound(SoundEvents.DEEPSLATE_BREAK);
                                }

                                makeBlockParticles(getEvenSlotX((int) mouseX)+x-1, getEvenSlotY((int) mouseY)+y-1,
                                        tileSoilLevel[getEvenSlotX((int) mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]);

                                tileSoilLevel[getEvenSlotX((int) mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]--;
                                hits=1;
                            }else if (tileHasFossil[getEvenSlotX((int) mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]>0 && !hasAppliedDamage){
                                switch (tileHasFossil[getEvenSlotX((int)mouseX)+x-1][getEvenSlotY((int) mouseY)+y-1]){
                                    case 1:
                                        if (bone1Undiscovered && bone1Damage<2){
                                            bone1Damage++;
                                            makeBlockParticles(getEvenSlotX((int) mouseX), getEvenSlotY((int) mouseY), 0);
                                            damageFossil();
                                        }
                                        break;
                                    case 2:
                                        if (bone2Undiscovered && bone2Damage<2){
                                            bone2Damage++;
                                            makeBlockParticles(getEvenSlotX((int) mouseX), getEvenSlotY((int) mouseY), 0);
                                            damageFossil();
                                        }
                                        break;
                                    default:
                                        if (bone3Undiscovered && bone3Damage<2){
                                            bone3Damage++;
                                            makeBlockParticles(getEvenSlotX((int) mouseX), getEvenSlotY((int) mouseY), 0);
                                            damageFossil();
                                        }
                                }
                                hasAppliedDamage = true;
                            }
                        }
                    }
                    currentHits+=hits;
                }

                //collecting fossils
                if (!draggingChisel && !draggingHammer && !draggingPick &&
                        mouseX > this.leftPos+33 && mouseX < this.leftPos+144 && mouseY > this.topPos+32 && mouseY < this.topPos+140){

                    boolean isCovered = false;
                    if (tileHasFossil[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)] >0
                            && tileSoilLevel[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]<=0){

                        switch (tileHasFossil[getUnevenSlotX((int) mouseX)][getUnevenSlotY((int) mouseY)]){
                            case 1:
                                for (int x = bone1TileX; x<bone1TileX+2; x++){
                                    for (int y = bone1TileY; y<bone1TileY+2; y++){
                                        if (tileSoilLevel[x][y] > 0) {
                                            isCovered = true;
                                            break;
                                        }
                                    }
                                }
                                if (!isCovered && bone1Undiscovered){
                                    bone1Undiscovered = false;
                                    fossilProgress1 = 10;
                                    successLevel++;
                                    player.playSound(SoundEvents.ITEM_PICKUP);
                                    this.lastClicked1X = mouseX;
                                    this.lastClicked1Y = mouseY;
                                    this.makeSparks(mouseX, mouseY);
                                }
                                break;
                            case 2:
                                for (int y = bone2TileY; y<bone2TileY+2; y++){
                                    if (tileSoilLevel[bone2TileX][y] > 0) {
                                        isCovered = true;
                                        break;
                                    }
                                }

                                if (!isCovered && bone2Undiscovered){
                                    bone2Undiscovered = false;
                                    fossilProgress2 = 10;
                                    successLevel++;
                                    player.playSound(SoundEvents.ITEM_PICKUP);
                                    this.lastClicked2X = mouseX;
                                    this.lastClicked2Y = mouseY;
                                    this.makeSparks(mouseX, mouseY);
                                }
                                break;
                            default:
                                for (int x = bone3TileX; x<bone3TileX+3; x++){
                                    for (int y = bone3TileY; y<bone3TileY+2; y++){
                                        if (tileSoilLevel[x][y] > 0) {
                                            isCovered = true;
                                            break;
                                        }
                                    }
                                }
                                if (!isCovered && bone3Undiscovered){
                                    bone3Undiscovered = false;
                                    fossilProgress3 = 10;
                                    successLevel++;
                                    player.playSound(SoundEvents.ITEM_PICKUP);
                                    this.lastClicked3X = mouseX;
                                    this.lastClicked3Y = mouseY;
                                    this.makeSparks(mouseX, mouseY);
                                }
                        }
                    }
                }
            }

        }

        return prev;
    }

    private void damageFossil() {
        player.playSound(SoundEvents.IRON_GOLEM_DAMAGE);
        shakeAmount = 10;
        shakeProgress = 20;
        shakeX = player.getRandom().nextBoolean() ? 1 : -1;
        shakeY = player.getRandom().nextBoolean() ? 1 : -1;
    }

    public void makeBlockParticles(int tilex, int tiley, int soilLevel){
        int tileCenterX = this.leftPos + (tilex*16) + 32 + 12;
        int tileCenterY = this.topPos + (tiley*16) + 32 + 8;
        for (int i = 0; i<10; i++){
            Vec2 randp = Mathf.randVec2().scale(6);
            int life = player.getRandom().nextInt(5, 30);
            particles.add(new DistortableGUIParticle(6, life,tileCenterX + randp.x, tileCenterY + randp.y, 4+player.getRandom().nextInt(-1, 2),
                            randp.x/25, 1f, 208+ Mathf.randInt(12) + (16*(soilLevel-1)), 224 + Mathf.randInt(12) - 16 , 256, 256,
                            DistortableGUIParticle.distortTowardsPoint(88, 50, 1,60)))
                    .setLayer(0, false).setLayer(1, true).renderAffectors
                    = new GUIParticle.RenderAffectors[]{GUIParticle.RenderAffectors.FADE_IN, GUIParticle.RenderAffectors.ADD_BLEND};
        }
    }

    public void makeSparks(double mouseX, double mouseY){
        for (int i = 0; i<10; i++){
            Vec2 randp = Mathf.randVec2().scale(2);
            int life = player.getRandom().nextInt(5, 20);
            particles.add(new DistortableGUIParticle(0, life, (float) (mouseX + randp.x), (float) (mouseY + randp.y), 7,
                            randp.x,randp.y,
                            0+(Mathf.randInt(5)*7), 240, 256, 256,
                            DistortableGUIParticle.distortTowardsPoint(88, 50, 1,60)))
                    .setLayer(0, false).setLayer(1, true).renderAffectors
                    = new GUIParticle.RenderAffectors[]{GUIParticle.RenderAffectors.FADE_IN, GUIParticle.RenderAffectors.ADD_BLEND};
        }
    }

    @Override
    public void onClose() {
        super.onClose();
        if (this.currentHits>0){
            breakFossils();
        }
        this.player.playSound(SoundEvents.SHULKER_BOX_CLOSE);
    }

    public void breakFossils(){
       //System.out.println(clickedPos);

        MMMessages.sendToServer(new ChangeFossilMessage(this.successLevel, this.clickedPos));

//        if (player.level().getBlockState(clickedPos).getBlock() instanceof FossilBlock fossilBlock){
//            fossilBlock.destroyOriginalWithSuccessLevel(player, successLevel, clickedPos);
//        }

    }

    public void drawTexturedQuad(Matrix4f matrices, float[][] pos, float u0, float u1, float v0, float v1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        //Matrix4f matrices = guiGraphics.pose().last().pose();
        bufferBuilder.vertex(matrices, pos[0][0], pos[0][1], 0).uv(u0, v1).endVertex();
        bufferBuilder.vertex(matrices, pos[1][0], pos[1][1], 0).uv(u1, v1).endVertex();
        bufferBuilder.vertex(matrices, pos[2][0], pos[2][1], 0).uv(u1, v0).endVertex();
        bufferBuilder.vertex(matrices, pos[3][0], pos[3][1], 0).uv(u0, v0).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
    }


}
