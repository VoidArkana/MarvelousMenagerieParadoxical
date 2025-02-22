package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.data.codec.AbominationEntryManager;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;
import org.joml.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Optional;

public class PaleonomiconScreen extends Screen {

    public static final ResourceLocation BOOK_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/book/paleonomicon.png");
    public static final int PAGE_SIZE_IN_LINES = 15;

    public static final int TEXT_COLOR = 0X826A41;
    public static final int TEXT_LINK_COLOR = 0X111111;
    public static final int TEXT_LINK_HOVER_COLOR = 0X0094FF;
    public static final int TEXT_LINK_LOCKED_COLOR = 0XD3C9AB;
    public boolean unlockTooltip;
    private int tickCount = 0;
    protected ResourceLocation prevEntryJSON = null;
    protected ResourceLocation currentEntryJSON;
    protected ResourceLocation nextEntryJSON;
    protected final ResourceLocation rootEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, "books/root.json");


    protected final ResourceLocation animalsEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, "books/animals/index.json");
    protected final ResourceLocation plantsEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, "books/plants/index.json");
    protected final ResourceLocation anomaliesEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, "books/anomalies/index.json");

    int imageWidth;
    int imageHeight;
    int leftPos;
    int topPos;

    int nextButtonStartX=282;
    int nextButtonEndX=302;

    int prevButtonStartX=12;
    int prevButtonEndX=32;

    int buttonStartY =159-10;
    int buttonEndY =179;

    int homeButtonStartX=128;
    int homeButtonEndX=148;
    int homeButtonStartY=185;
    int homeButtonEndY=185+36;

    protected String entityTooltip;

    public static final float MOUSE_LEAN_THRESHOLD = 0.55F;
    public static final float MAX_MOUSE_LEAN_THRESHOLD = 0.70F;

    private boolean incrementingPage;
    private boolean decrementingPage;

    private PaleonomiconPage leftPageRenderer = new PaleonomiconPage(0);
    private PaleonomiconPage rightPageRenderer = new PaleonomiconPage(1);

    protected final List<LineData> lines = new ArrayList<>();

    private BookEntry currentEntry;
    private BookEntry prevEntry;
    private BookEntry nextEntry;
    private int entryPageNumber = 0;
    private int lastEntryPageBeforeLinkClick = -1;
    protected ResourceLocation currentPageText = null;

    private final List<AbominationEntryManager.AbominationEntryCodec> plantLinkData = new ArrayList<>();

    protected int xSize = 390;
    protected int ySize = 320;

    public PaleonomiconScreen(String openTo) {
        super(Component.translatable("item.marvelous_menagerie.paleonomicon"));
        currentEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, openTo);
        this.imageWidth = 314;
        this.imageHeight = 207;
        resetEntry();

        for (AbominationEntryManager.AbominationEntryCodec data : AbominationEntryManager.DATA) {
            this.plantLinkData.add(data);
        }

        Collections.sort(this.plantLinkData, Comparator.comparing(AbominationEntryManager.AbominationEntryCodec::getPlant));
    }

    public PaleonomiconScreen(){
        this("books/root.json");
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    public void tick() {
        tickCount++;

        if (incrementingPage) {
            if(nextEntryJSON != null){
                this.prevEntryJSON = this.currentEntryJSON;
                this.currentEntryJSON = nextEntryJSON;
                this.nextEntryJSON = null;
                this.nextEntry = null;
                resetEntry();
            }else{
                entryPageNumber++;
            }
            incrementingPage = false;
            updatePageRenderers();
        }
        if (decrementingPage) {
            entryPageNumber--;
            if(entryPageNumber < 0 && prevEntry != null){
                int i = lastEntryPageBeforeLinkClick == -1 ? 0 : lastEntryPageBeforeLinkClick;
                lastEntryPageBeforeLinkClick = -1;
                this.currentEntryJSON = prevEntryJSON;
                resetEntry();

                this.entryPageNumber = i;
            }
            decrementingPage = false;
            updatePageRenderers();
        }

        if(nextEntryJSON != null){
            this.prevEntryJSON = this.currentEntryJSON;
            this.currentEntryJSON = nextEntryJSON;
            this.nextEntryJSON = null;
            this.nextEntry = null;
            resetEntry();
        }
    }


    public void resetEntry() {
        if (currentEntryJSON != null) {
            currentEntry = readBookEntry(currentEntryJSON);
            if(currentEntry != null){
                currentEntry.init(this);
            }
        }
        if(this.currentEntry != null && this.currentEntry.getParent() != null && !this.currentEntry.getParent().isEmpty()){
            this.prevEntryJSON = new ResourceLocation(getBookFileDirectory() + this.currentEntry.getParent());
        }else{
            this.prevEntryJSON = null;
        }
        if (prevEntryJSON != null) {
            prevEntry = readBookEntry(prevEntryJSON);
            if(prevEntry != null){
                prevEntry.init(this);
            }
        }
        if (nextEntryJSON != null) {
            nextEntry = readBookEntry(nextEntryJSON);
            if(nextEntry != null){
                nextEntry.init(this);
            }
        }
        if (currentEntry != null) {
            entryPageNumber = 0;
        }
        updatePageRenderers();
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float fakePartialTickThatsZeroForSomeReason) {
        //System.out.println(this.entryPageNumber);
        this.setFocused(null);
        this.renderBackground(guiGraphics);

        float partialTick = Minecraft.getInstance().getPartialTick();
        PoseStack poseStack = guiGraphics.pose();

        guiGraphics.pose().pushPose();
        poseStack.translate(0, -10, 1);
        guiGraphics.pose().pushPose();

        guiGraphics.blit(BOOK_TEXTURE, this.leftPos, this.topPos, 16, 16, this.imageWidth, this.imageHeight, 510, 510);

        if (entryPageNumber == 0){
            guiGraphics.blit(PaleonomiconScreen.BOOK_TEXTURE, this.leftPos+27, this.topPos+24,
                    336, 16, 112, 32, 510, 510);
        }

        boolean hoveringOverRight = mouseX >= this.leftPos + nextButtonStartX && mouseX <= this.leftPos + nextButtonEndX
                && mouseY >= this.topPos + buttonStartY && mouseY <= this.topPos + buttonEndY;
        boolean hoveringOverLeft = mouseX >= this.leftPos + prevButtonStartX && mouseX <= this.leftPos + prevButtonEndX
                && mouseY >= this.topPos + buttonStartY && mouseY <= this.topPos + buttonEndY;

        boolean hoveringOverHome = mouseX >= this.leftPos + homeButtonStartX && mouseX <= this.leftPos + homeButtonEndX
                && mouseY >= this.topPos + homeButtonStartY-12 && mouseY <= this.topPos + homeButtonEndY-12;

        if (canGoLeft()){
            guiGraphics.blit(PaleonomiconScreen.BOOK_TEXTURE, this.leftPos+10, this.topPos+155,
                    336, hoveringOverLeft ? 96+64+32 : 96+64, 48, 32, 510, 510);
        }
        if (canGoRight()){
            guiGraphics.blit(PaleonomiconScreen.BOOK_TEXTURE, this.leftPos+265, this.topPos+155,
                    336, hoveringOverRight ? 96+32 :  96, 48, 32, 510, 510);
        }

        guiGraphics.blit(PaleonomiconScreen.BOOK_TEXTURE, this.leftPos+homeButtonStartX, this.topPos+homeButtonStartY,
                336, hoveringOverHome ? 48+8 : 48, 20, 36, 510, 510);


        poseStack.popPose();

        poseStack.pushPose();
            poseStack.translate(this.leftPos+28, this.topPos+28, 0);
            poseStack.pushPose();
                poseStack.scale(0.85f, 0.85f, 0);
                renderBookContents(guiGraphics, mouseX, mouseY, partialTick);
                guiGraphics.flush();
            poseStack.popPose();
        poseStack.popPose();

        guiGraphics.pose().pushPose();
            guiGraphics.pose().translate((float)this.leftPos, (float)this.topPos, 0.0F);
            RenderSystem.setShaderTexture(0, BOOK_TEXTURE);
        guiGraphics.pose().popPose();

        if(currentEntry != null){
            currentEntry.mouseOver(this, entryPageNumber, mouseX, mouseY);
        }
        super.render(guiGraphics, mouseX, mouseY, fakePartialTickThatsZeroForSomeReason);

        addLinkButtons();

        if (this.entityTooltip != null) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 550);
            guiGraphics.renderTooltip(font, Minecraft.getInstance().font.split(Component.translatable(entityTooltip),
                    Math.max(this.width / 2 - 43, 170)), mouseX, mouseY);
            entityTooltip = null;
            guiGraphics.pose().popPose();
        }

        if(unlockTooltip){
            List<Component> list = new ArrayList<>();
            list.add(Component.translatable("book.marvelous_menagerie.page_locked_0").withStyle(ChatFormatting.GRAY));
            list.add(Component.translatable("book.marvelous_menagerie.page_locked_1").withStyle(ChatFormatting.GRAY));
            guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX - 5, mouseY - 5);
        }
        poseStack.popPose();

    }

    private void renderBookContents(GuiGraphics graphics, int mouseX, int mouseY, float partialTick ) {
        renderForPageType(leftPageRenderer, 0, graphics, mouseX, mouseY, partialTick);
        renderForPageType(rightPageRenderer, 1, graphics, mouseX, mouseY, partialTick);
    }

    //"kind" - what kind of transform. 0 = left page, 1 = right page, 2 = right side of flipping page, 3 = left side of flipping page
    private void renderForPageType(PaleonomiconPage contents, int kind, GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();
        if (kind == 1){
            poseStack.translate(173F, 0, 0);
        }
        contents.renderPage(this, graphics, mouseX, mouseY, partialTick, kind >= 2);
        poseStack.popPose();
    }

    public void updatePageRenderers() {
        leftPageRenderer.setEntryPageNumber(entryPageNumber);
        leftPageRenderer.setEntry(currentEntry);
        rightPageRenderer.setEntryPageNumber(entryPageNumber);
        rightPageRenderer.setEntry(currentEntry);
    }


    private boolean isBookOpened() {
        return currentEntry != null;
    }

    public boolean canGoLeft() {
        return isBookOpened() && (entryPageNumber > 0 || prevEntryJSON != null);
    }

    public boolean canGoRight() {
        return isBookOpened() && entryPageNumber + 1 < currentEntry.getPageCount();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean prev = super.mouseClicked(mouseX, mouseY, button);
        if (!prev) {
            if(currentEntry != null && currentEntry.consumeMouseClick(this)){
                return true;
            }else{
                boolean hoveringOverHome = mouseX >= this.leftPos + homeButtonStartX && mouseX <= this.leftPos + homeButtonEndX
                        && mouseY >= this.topPos + homeButtonStartY-12 && mouseY <= this.topPos + homeButtonEndY-12;

                if (hoveringOverHome){
                    currentEntryJSON = rootEntryJSON;
                    resetEntry();
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                    return true;
                }

                if (!decrementingPage && !incrementingPage) {
                    boolean hoveringOverRight = mouseX >= this.leftPos + nextButtonStartX && mouseX <= this.leftPos + nextButtonEndX
                            && mouseY >= this.topPos + buttonStartY && mouseY <= this.topPos + buttonEndY;
                    boolean hoveringOverLeft = mouseX >= this.leftPos + prevButtonStartX && mouseX <= this.leftPos + prevButtonEndX
                            && mouseY >= this.topPos + buttonStartY && mouseY <= this.topPos + buttonEndY;
                    if (hoveringOverLeft && canGoLeft()) {
                        decrementingPage = true;
                        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                        return true;
                    } else if (hoveringOverRight && canGoRight()) {
                        incrementingPage = true;
                        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                        return true;
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }

    @Nullable
    protected BookEntry readBookEntry(ResourceLocation res) {
        Optional<Resource> resource = null;
        BookEntry page = null;
        try {
            resource = Minecraft.getInstance().getResourceManager().getResource(res);
            if (resource.isPresent()) {
                BufferedReader inputstream = resource.get().openAsReader();
                page = BookEntry.deserialize(inputstream);
            }
        } catch (IOException e1) {
            if(!(e1 instanceof AccessDeniedException)){
                e1.printStackTrace();
            }
        }
        return page;
    }

    public int getEntryPageNumber() {
        return entryPageNumber;
    }

    public static String getBookFileDirectory() {
        return MarvelousMenagerie.MODID + ":books/";
    }

    public boolean attemptChangePage(ResourceLocation changePageTo, boolean goingForwards) {
        if(goingForwards){
            prevEntryJSON = currentEntryJSON;
        }
        nextEntryJSON = changePageTo;
        if(goingForwards){
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        }
        return  true;
    }

    public static void fixLighting(){
        Vector3f light0 = new Vector3f(1, 1.0F, -1.0F);
        Vector3f light1 = new Vector3f(1, 1.0F, -1.0F);
        RenderSystem.setShaderLights(light0, light1);
    }

    public void setEntityTooltip(String hoverText) {
        this.entityTooltip = hoverText;
    }

    protected int getBindingColor() {
        return 0X6A3B16;
    }

    protected int getWidgetColor() {
        return getBindingColor();
    }

    private static final ResourceLocation BOOK_WIDGET_TEXTURE = new ResourceLocation("marvelous_menagerie:textures/gui/book/widgets.png");
    protected ResourceLocation getBookWidgetTexture() {
        return BOOK_WIDGET_TEXTURE;
    }

    private void addLinkButtons() {
        this.renderables.clear();
        this.clearWidgets();
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize + 128) / 2;

        if (this.currentEntryJSON.equals(anomaliesEntryJSON)) {
            int rowCount = 0;
            int columnCount = 0;

            int linkDataSize = this.plantLinkData.size();

            int startingEntity = 0;
            if (this.entryPageNumber != 0) {

                int startingSize = 5 * 5;

                if (this.entryPageNumber ==  1) {
                    startingEntity = linkDataSize - (linkDataSize - (startingSize * (startingSize - 0)));
                } else {
                    startingEntity = linkDataSize - (linkDataSize - ((startingSize * (startingSize - 0 * 2) * 2)));
                }

                if (startingEntity < 0) {
                    return;
                }
            }

            int startingOffsetX = 16;
            int startingOffsetY = 16;

            if (this.entryPageNumber == 0) {
                startingOffsetX += 57;
            }
            startingOffsetY += 20;
            if (this.entryPageNumber < 0) {
                return;
            }
            for (int i = startingEntity; i < linkDataSize; i++) {
                AbominationEntryManager.AbominationEntryCodec linkData = this.plantLinkData.get(i);
                if (rowCount == 5) {
                    break;
                }
                if (this.entryPageNumber != 0) {
                    if (i > (startingEntity + (5 * 5 * 2))) {
                        break;
                    }
                }
                this.addRenderableWidget(new EntityLinkButton(this, linkData.icon(), linkData.entityName(),
                        startingOffsetX + k + (columnCount * 24), startingOffsetY + l + (rowCount * 24), (p_213021_1_) -> {

                    this.attemptChangePage(new ResourceLocation(PaleonomiconScreen.getBookFileDirectory() + linkData.link()), true);

                }));

                columnCount++;


                if (columnCount == 5) {
                    rowCount++;
                    columnCount = 0;
                }

                if (0 != this.entryPageNumber && rowCount >= 5) {
                    startingOffsetX = 16 + 200;
                    columnCount = 0;
                    rowCount = 0;
                }
                if (rowCount == 5) {
                    break;
                }
            }
        }

    }

}
