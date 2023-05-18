package com.aditiyagilang.edifarm_company.model.Download;

import com.google.gson.annotations.SerializedName;

public class DownloadData {

    @SerializedName("download_url")
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}