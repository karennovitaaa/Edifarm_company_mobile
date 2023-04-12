package com.aditiyagilang.edifarm_company.model;

public class listDataPost {
    private final String nama, time, caption;
    private final Integer image;

    public listDataPost(String nama, String time, String caption, Integer image) {
        this.nama = nama;
        this.time = time;
        this.caption = caption;
        this.image = image;
    }
    public String getNama() {
        return nama;
    }
    public String getTime() {
        return time;
    }
    public String getCaption() {
        return caption;
    }
    public Integer getImage() {
        return image;
    }
}
