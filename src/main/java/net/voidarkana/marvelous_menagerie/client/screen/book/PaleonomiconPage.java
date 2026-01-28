package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.voidarkana.marvelous_menagerie.client.screen.BookLink;
import net.voidarkana.marvelous_menagerie.client.screen.book.widget.BookWidget;
import net.voidarkana.marvelous_menagerie.client.screen.book.widget.ItemWidget;
import org.joml.Matrix4f;

public class PaleonomiconPage {

    public boolean enteringNewPageFlag;
    public boolean leavingNewPageFlag;
    private int relativePageNumber;
    private int entryPageNumber;

    private BookEntry entry;

    public PaleonomiconPage(int relativePageNumber) {
        this.relativePageNumber = relativePageNumber;
    }

    public void setEntryPageNumber(int entryPageNumber) {
        this.entryPageNumber = entryPageNumber;
    }

    public int getDisplayPageNumber() {
        int i = relativePageNumber;
        if (enteringNewPageFlag) {
            i -= 2;
        }
        if (leavingNewPageFlag) {
            i += 2;
        }
        return 1 + i + (entryPageNumber * 2);
    }

    public void setEntry(BookEntry entry) {
        this.entry = entry;
    }

    protected void renderPage(PaleonomiconScreen screen, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks, boolean isLeftPage) {
        PoseStack poseStack = guiGraphics.pose();
        int pgNumber = getDisplayPageNumber();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        if(entry != null){
            if(pgNumber == 1 && !entry.getTranslatableTitle().isEmpty()){
                Component title = Component.translatable(entry.getTranslatableTitle());
                poseStack.pushPose();
                int titleLength = Math.max(screen.getMinecraft().font.width(title), 1);
                float titleScale =  Math.min(135F / (float) titleLength, 2.5F) / 1.5f;
                poseStack.translate(65, 7 - 5 * titleScale, 0);
                poseStack.scale(titleScale, titleScale, 1F);
                poseStack.translate(-titleLength / 2F, 0, 0);

                //guiGraphics.blit(PaleonomiconScreen.BOOK_TEXTURE, screen.leftPos, screen.topPos, 336, 16, 112, 32, 510, 510);

                screen.getMinecraft().font.drawInBatch8xOutline(title.getVisualOrderText(), 0.0F, 0.0F, 0XFFE7BF, 0XAA977F, poseStack.last().pose(), bufferSource, 15728880);
                poseStack.popPose();
            }
            if (!entry.getEntryText().isEmpty()) {
                int startReadingAt = (pgNumber - 1) * PaleonomiconScreen.PAGE_SIZE_IN_LINES;
                printLinesFromEntry(screen.getMinecraft().font, poseStack, bufferSource, entry, startReadingAt);
            }
            int numberWidth = screen.getMinecraft().font.width(String.valueOf(pgNumber));
            poseStack.pushPose();
            poseStack.scale(0.74F, 0.74F, 0.74F);
            screen.getMinecraft().font.drawInBatch(String.valueOf(pgNumber), 86.0F - numberWidth * 0.5F, 210.0F,
                    PaleonomiconScreen.TEXT_COLOR, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL,
                    0, 15728880);
            poseStack.popPose();

            for (BookWidget widget : entry.getWidgets()) {
                if (widget.getDisplayPage() == pgNumber) {
                    widget.render(poseStack, bufferSource, partialTicks, isLeftPage, mouseX, mouseY);

                    int k = screen.leftPos + 38;
                    int l = screen.topPos + 18;
                    float scale = 0.85f;

                    if (widget instanceof ItemWidget itemWidget &&
                            (mouseX >= (k + widget.getX())*scale && mouseY >= (l + widget.getY())*scale
                                    && mouseX < (k + widget.getX() + (16*widget.getScale()))*scale && mouseY < (l + widget.getY() + (16*widget.getScale()))*scale )){


                        screen.setCurrentItemTooltip(itemWidget.getItemName());

                    }
                }
            }
        }
    }

    private void printLinesFromEntry(Font font, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, BookEntry bookEntry, int startReadingAt) {
        if (startReadingAt >= 0) {
            for (int i = startReadingAt; i < startReadingAt + PaleonomiconScreen.PAGE_SIZE_IN_LINES; i++) {
                if (bookEntry.getEntryText().size() > i) {
                    String printLine = bookEntry.getEntryText().get(i);
                    font.drawInBatch(printLine, 0, (i - startReadingAt) * 10,
                            PaleonomiconScreen.TEXT_COLOR, false, poseStack.last().pose(),
                            bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
                    for (BookLink bookLink : bookEntry.getEntryLinks()) {
                        if (bookLink.getLineNumber() == i) {
                            int fontWidth = font.width(printLine.substring(0, bookLink.getCharacterStartsAt()));
                            Component component = Component.literal(bookLink.getDisplayText()).withStyle(ChatFormatting.UNDERLINE);
                            font.drawInBatch(component, fontWidth, (i - startReadingAt) * 10, bookLink.isEnabled()
                                    ? bookLink.isHovered() ? PaleonomiconScreen.TEXT_LINK_HOVER_COLOR : PaleonomiconScreen.TEXT_LINK_COLOR : PaleonomiconScreen.TEXT_LINK_LOCKED_COLOR, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
                        }
                    }
                }
            }
        }
    }

}
