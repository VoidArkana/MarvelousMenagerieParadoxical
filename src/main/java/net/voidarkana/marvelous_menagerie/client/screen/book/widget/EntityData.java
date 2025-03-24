package net.voidarkana.marvelous_menagerie.client.screen.book.widget;

public class EntityData {

    public String entity;
    public String item;
    public String link;

    public EntityData(String pEntity, String pItem, String pLink){
        this.entity = pEntity;
        this.item = pItem;
        this.link = pLink;
    }

    public String getEntity(){
        return this.entity;
    }

    public void setEntity(String pEntity){
        this.entity = pEntity;
    }

    public String getItem(){
        return this.item;
    }

    public void setItem(String pItem){
        this.entity = pItem;
    }

    public String getLink(){
        return this.link;
    }

    public void setLink(String pLink){
        this.entity = pLink;
    }

}
