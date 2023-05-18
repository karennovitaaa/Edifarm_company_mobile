package com.aditiyagilang.edifarm_company.model.Download;

import com.google.gson.annotations.SerializedName;

public class Download {

    @SerializedName("data")
    private DownloadData downloadData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public DownloadData getData() {
        return downloadData;
    }

    public void setData(DownloadData downloadData) {
        this.downloadData = downloadData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}