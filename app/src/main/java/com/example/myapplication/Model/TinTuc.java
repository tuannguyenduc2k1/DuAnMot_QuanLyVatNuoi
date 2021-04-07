package com.example.myapplication.Model;

public class TinTuc {
    public String id;
    public String title;//tieu de
    public String des;//mieu ta
    public String pubDate;//ngay ra tin
    public String link;

    public TinTuc() {
    }

    public TinTuc(String id, String title, String des, String pubDate, String link) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
