package com.aditiyagilang.edifarm_company.model.UP;

import com.google.gson.annotations.SerializedName;

public class Up {

    @SerializedName("data")
    private UpData upData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpData getData() {
        return upData;
    }

    public void setData(UpData upData) {
        this.upData = upData;
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