package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.BookLink;
import org.joml.Vector3f;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Axis;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Optional;

public class PaleonomiconScreen extends Screen {

    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/gui/book/paleonomicon.png");
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
    protected int currentPageCounter = 0;
    int imageWidth;
    int imageHeight;
    int leftPos;
    int topPos;

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

    protected int xSize = 390;
    protected int ySize = 320;

    public PaleonomiconScreen(String openTo) {
        super(Component.translatable("item.marvelous_menagerie.paleonomicon"));
        currentEntryJSON = new ResourceLocation(MarvelousMenagerie.MODID, openTo);
        this.imageWidth = 314;
        this.imageHeight = 207;
        resetEntry();
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
//        this.width = 510;
//        this.height = 510;
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

        this.setFocused(null);
        this.renderBackground(guiGraphics);

        float partialTick = Minecraft.getInstance().getPartialTick();
        PoseStack poseStack = guiGraphics.pose();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        float bookScale = 221;
        int i = this.imageWidth / 2;
        int j = this.imageHeight / 2;
        float mouseLeanX = (mouseX - i) / bookScale;
        float mouseLeanY = (mouseY - j) / bookScale;

        guiGraphics.pose().pushPose();
        poseStack.translate(0, -10, 0);
        guiGraphics.pose().pushPose();
        guiGraphics.blit(BOOK_TEXTURE, this.leftPos, this.topPos, 16, 16, this.imageWidth, this.imageHeight, 510, 510);
        poseStack.popPose();

        poseStack.pushPose();
            poseStack.translate(this.leftPos+28, this.topPos+28, 0);
            poseStack.pushPose();
                poseStack.scale(0.85f, 0.85f, 0);
                renderBookContents(poseStack, mouseX, mouseY, partialTick);
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

        if(unlockTooltip){
            List<Component> list = new ArrayList<>();
            list.add(Component.translatable("book.marvelous_menagerie.page_locked_0").withStyle(ChatFormatting.GRAY));
            list.add(Component.translatable("book.marvelous_menagerie.page_locked_1").withStyle(ChatFormatting.GRAY));
            guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX - 5, mouseY - 5);
        }
        poseStack.popPose();
    }

    private void renderBookContents(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderForPageType(leftPageRenderer, 0, poseStack, mouseX, mouseY, partialTick);
        renderForPageType(rightPageRenderer, 1, poseStack, mouseX, mouseY, partialTick);
    }

    //"kind" - what kind of transform. 0 = left page, 1 = right page, 2 = right side of flipping page, 3 = left side of flipping page
    private void renderForPageType(PaleonomiconPage contents, int kind, PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        poseStack.pushPose();
        if (kind == 1){
            poseStack.translate(173F, 0, 0);
        }
        contents.renderPage(this, poseStack, mouseX, mouseY, partialTick, kind >= 2);
        poseStack.popPose();
    }

    public void updatePageRenderers() {
        boolean flag = prevEntryJSON != null && entryPageNumber == 0;
        int pgOffsetReturningFromLink = lastEntryPageBeforeLinkClick != -1 && entryPageNumber == 0 ? lastEntryPageBeforeLinkClick : 0;
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
        int i = this.width / 2;
        float distFromMiddleX = (float) ((mouseX - i) / (float) 220);
        boolean prev = super.mouseClicked(mouseX, mouseY, button);
        System.out.println(distFromMiddleX);
        if (!prev) {
            if(currentEntry != null && currentEntry.consumeMouseClick(this)){
                return true;
            }else{
                if (!decrementingPage && !incrementingPage) {
                    if (distFromMiddleX < -MOUSE_LEAN_THRESHOLD && distFromMiddleX > -MAX_MOUSE_LEAN_THRESHOLD && canGoLeft()) {
                        decrementingPage = true;
                        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
                        return true;
                    } else if (distFromMiddleX > MOUSE_LEAN_THRESHOLD  && distFromMiddleX < MAX_MOUSE_LEAN_THRESHOLD && canGoRight()) {
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
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PUT, 1.0F));
        }
        return  true;
    }

    public static void fixLighting(){
        Vector3f light0 = new Vector3f(1, 1.0F, -1.0F);
        Vector3f light1 = new Vector3f(1, 1.0F, -1.0F);
        RenderSystem.setShaderLights(light0, light1);
    }

}
