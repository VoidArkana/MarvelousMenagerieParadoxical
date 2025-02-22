package net.voidarkana.marvelous_menagerie.client.screen.book;

public class EntityLinkData {
    private String item;
    private int x;
    private int y;
    private float offset_x;
    private float offset_y;
    private double item_scale;
    private double scale;
    private int page;
    private String linked_page;
    private String hover_text;

    public EntityLinkData(String item, int x, int y, double scale, double item_scale, int page, String linked_page, String hover_text, float offset_x, float offset_y) {
        this.item = item;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.item_scale = item_scale;
        this.page = page;
        this.linked_page = linked_page;
        this.hover_text = hover_text;
        this.offset_x = offset_x;
        this.offset_y = offset_y;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String model) {
        this.item = model;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getEntityScale() {
        return item_scale;
    }

    public void setEntityScale(double scale) {
        this.item_scale = scale;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getLinkedPage() {
        return linked_page;
    }

    public void setLinkedPage(String linkedPage) {
        this.linked_page = linkedPage;
    }

    public String getHoverText() {
        return hover_text;
    }

    public void setHoverText(String titleText) {
        this.hover_text = titleText;
    }

    public float getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(float offset_y) {
        this.offset_y = offset_y;
    }

    public float getOffset_x() {
        return offset_x;
    }

    public void setOffset_x(float offset_x) {
        this.offset_x = offset_x;
    }
}
