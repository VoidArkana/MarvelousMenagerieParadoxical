package net.voidarkana.marvelous_menagerie.client.screen.book.widget;

import net.voidarkana.marvelous_menagerie.data.manager.codec.IndexTagsCodec;

import java.util.List;

public class EntityData {

    public String entity;
    public String item;
    public String link;
    public List<IndexTagsCodec> tags;

    public EntityData(String pEntity, String pItem, String pLink){
        this.entity = pEntity;
        this.item = pItem;
        this.link = pLink;
        this.tags = null;
    }

    public EntityData(String pEntity, String pItem, String pLink, List<IndexTagsCodec> pTags){
        this.entity = pEntity;
        this.item = pItem;
        this.link = pLink;
        this.tags = pTags;
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

    public List<IndexTagsCodec> getTags(){
        return this.tags;
    }

    public void setTags(List<IndexTagsCodec> pTags){
        this.tags = pTags;
    }

}
