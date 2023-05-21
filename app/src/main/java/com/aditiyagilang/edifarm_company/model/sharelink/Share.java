package com.aditiyagilang.edifarm_company.model.sharelink;

import com.google.gson.annotations.SerializedName;

public class Share {

    @SerializedName("data")
    private ShareData shareData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public ShareData getData() {
        return shareData;
    }

    public void setData(ShareData shareData) {
        this.shareData = shareData;
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