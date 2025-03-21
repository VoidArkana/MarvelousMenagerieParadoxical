package net.voidarkana.marvelous_menagerie.client.screen.book;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.BookLink;
import net.voidarkana.marvelous_menagerie.client.screen.book.widget.BookWidget;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookEntry {

    public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(BookEntry.class, new BookEntry.Deserializer()).excludeFieldsWithoutExposeAnnotation().create();
    private static Pattern pattern = Pattern.compile("\\{.*?\\}");
    @Expose
    private String translatableTitle;
    @Expose
    private String parent;
    @Expose
    private String textFileToReadFrom;
    @Expose
    private String requiredProgress;
    @Expose
    private BookWidget[] widgets;
    private List<String> entryText = new ArrayList<>();
    private List<BookLink> bookLinks = new ArrayList<>();
    private List<EntityLinkData> linkedEntites = new ArrayList<>();

    private int pageCount = 0;


    public BookEntry(String translatableTitle, String parent, String textFileToReadFrom, String requiredProgress, BookWidget[] widgets, List<EntityLinkData> linkedEntities) {
        this.translatableTitle = translatableTitle;
        this.parent = parent;
        this.textFileToReadFrom = textFileToReadFrom;
        this.requiredProgress = requiredProgress;
        this.widgets = widgets;
        this.linkedEntites = linkedEntities;
    }

    public static BookEntry deserialize(Reader readerIn) {
        return GsonHelper.fromJson(GSON, readerIn, BookEntry.class);
    }

    public String getTranslatableTitle() {
        return translatableTitle;
    }

    public String getParent() {
        return parent;
    }

    public List<String> getEntryText() {
        return entryText;
    }

    public List<BookLink> getEntryLinks() {
        return bookLinks;
    }

    public BookWidget[] getWidgets() {
        return widgets;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<EntityLinkData> getLinkedEntities() {
        return linkedEntites;
    }

    public void init(PaleonomiconScreen screen) {
        this.entryText = getRawTextFromFile(textFileToReadFrom, screen, 30);
        this.pageCount = (int) Math.ceil(entryText.size() / (float) (PaleonomiconScreen.PAGE_SIZE_IN_LINES * 2));
    }

    private List<String> getRawTextFromFile(String fileName, PaleonomiconScreen screen, int maxLineSize) {
        String lang = Minecraft.getInstance().getLanguageManager().getSelected().toLowerCase();
        ResourceLocation fileRes;
        try {
            fileRes = new ResourceLocation(PaleonomiconScreen.getBookFileDirectory() + lang + "/" + fileName);
            //test if it exists. if no exception, then the language is supported
            InputStream is = Minecraft.getInstance().getResourceManager().open(fileRes);
            is.close();
        } catch (Exception e) {
            MarvelousMenagerie.LOGGER.warn("Could not find language file for translation, defaulting to en_us");
            fileRes = new ResourceLocation(PaleonomiconScreen.getBookFileDirectory() + "en_us/" + fileName);
        }
        List<String> strings = new ArrayList<>();
        Font font = Minecraft.getInstance().font;
        try {
            BufferedReader bufferedreader = Minecraft.getInstance().getResourceManager().openAsReader(fileRes);
            List<String> readIn = IOUtils.readLines(bufferedreader);
            int currentLineCount = 0;
            for (String readString : readIn) {
                Matcher m = pattern.matcher(readString);
                boolean skipLineEntirely = false;
                boolean noOverflow = false;
                while (m.find()) {
                    String[] found = m.group().split("\\|");
                    if (found.length >= 1) {
                        String linkTo = found[1].substring(0, found[1].length() - 1);
                        String display = found[0].substring(1);
                        bookLinks.add(new BookLink(currentLineCount, m.start(), display, linkTo, true));
                        readString = m.replaceFirst(display);
                        noOverflow = true;
                    }
                }
                if(readString.isEmpty() && !skipLineEntirely){
                    strings.add(readString);
                    currentLineCount++;
                }
                while (font.width(readString) > maxLineSize) {
                    int spaceScanIndex = 0;
                    int lastSpace = -1;
                    while(spaceScanIndex < readString.length()){
                        if(readString.charAt(spaceScanIndex) == ' ' && font.width(readString.substring(0, spaceScanIndex)) > 92){
                            lastSpace = noOverflow ? readString.length() : spaceScanIndex;
                            break;
                        }
                        spaceScanIndex++;
                    }
                    int cutIndex = lastSpace == -1 ? Math.min(maxLineSize, readString.length()) : lastSpace;
                    strings.add(readString.substring(0, cutIndex));
                    currentLineCount++;
                    readString = readString.substring(cutIndex);
                    if (readString.startsWith(" ")) {
                        readString = readString.substring(1);
                    }
                }
                if(!readString.isEmpty()){
                    strings.add(readString);
                    currentLineCount++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public void mouseOver(PaleonomiconScreen screen, int page, float mouseX, float mouseY){
        boolean hoverFlag = false;
        screen.unlockTooltip = false;
        for(BookLink link : bookLinks){
            int minLine = page * PaleonomiconScreen.PAGE_SIZE_IN_LINES;

            link.setHovered(false);
            if(link.getLineNumber() >= minLine && link.getLineNumber() <= minLine + PaleonomiconScreen.PAGE_SIZE_IN_LINES * 2){
                String line = entryText.get(link.getLineNumber());
                boolean rightPage = link.getLineNumber() > minLine + PaleonomiconScreen.PAGE_SIZE_IN_LINES;
                float textStartsX = rightPage ? screen.leftPos+175-8 : screen.leftPos+20;
                float textsStartsY = screen.topPos + 15;
                float wordStartAt = textStartsX + Minecraft.getInstance().font.width(line.substring(0, link.getCharacterStartsAt()));
                float wordEndAt = wordStartAt + Minecraft.getInstance().font.width(link.getDisplayText())*0.9f;
                float wordTopAt = textsStartsY + (link.getLineNumber() % PaleonomiconScreen.PAGE_SIZE_IN_LINES)*8.5f;
                float wordBottomAt = wordTopAt + 9F;

                if(mouseX > wordStartAt && mouseX < wordEndAt && mouseY > wordTopAt && mouseY < wordBottomAt){
                    if(link.isEnabled()){
                        link.setHovered(!hoverFlag);
                        hoverFlag = true;
                    }else{
                        screen.unlockTooltip = true;
                    }
                }
            }
        }
    }

    public boolean consumeMouseClick(PaleonomiconScreen screen){
        for(BookLink link : bookLinks) {
            int minLine = screen.getEntryPageNumber() * PaleonomiconScreen.PAGE_SIZE_IN_LINES;
            if(link.isEnabled() && link.isHovered() && link.getLineNumber() >= minLine && link.getLineNumber() <= minLine + PaleonomiconScreen.PAGE_SIZE_IN_LINES * 2) {
                return screen.attemptChangePage(new ResourceLocation(PaleonomiconScreen.getBookFileDirectory() + link.getLinksTo()), true);
            }
        }
        return false;
    }

    public static class Deserializer implements JsonDeserializer<BookEntry> {

        public BookEntry deserialize(JsonElement mainElement, Type deserializeType, JsonDeserializationContext context) throws JsonParseException {

            JsonObject jsonobject = GsonHelper.convertToJsonObject(mainElement, "book entry");
            BookWidget[] bookWidgets = new BookWidget[0];

            if(jsonobject.has("widgets")){
                JsonArray jsonArray = jsonobject.getAsJsonArray("widgets");
                bookWidgets = new BookWidget[jsonArray.size()];
                for(int i = 0; i < jsonArray.size(); i++){
                    JsonObject widgetJson = jsonArray.get(i).getAsJsonObject();
                    BookWidget.Type type = GsonHelper.getAsObject(widgetJson, "type", context, BookWidget.Type.class);
                    bookWidgets[i] = GsonHelper.convertToObject(widgetJson, "", context, type.getWidgetClass());
                }
            }

            String parent = null;
            if (jsonobject.has("parent")) {
                parent = GsonHelper.getAsString(jsonobject, "parent");
            }

            String text = "";
            if (jsonobject.has("text")) {
                text = GsonHelper.getAsString(jsonobject, "text");
            }

            String title = "";
            if (jsonobject.has("title")) {
                title = GsonHelper.getAsString(jsonobject, "title");
            }

            String progress = null;
            if (jsonobject.has("required_progression")) {
                progress = GsonHelper.getAsString(jsonobject, "required_progression");
            }

            EntityLinkData[] linkedEntitesRead = GsonHelper.getAsObject(jsonobject, "entity_buttons", new EntityLinkData[0], context, EntityLinkData[].class);

            BookEntry bookEntry = new BookEntry(title, parent, text, progress, bookWidgets, Arrays.asList(linkedEntitesRead));
            return bookEntry;
        }
    }
}
