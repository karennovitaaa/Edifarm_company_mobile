package com.aditiyagilang.edifarm_company.model.sharelink;

import com.google.gson.annotations.SerializedName;

public class ShareData {

    @SerializedName("link")
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}