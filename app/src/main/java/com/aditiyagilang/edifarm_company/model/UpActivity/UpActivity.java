package com.aditiyagilang.edifarm_company.model.UpActivity;

import com.google.gson.annotations.SerializedName;

public class UpActivity {

    @SerializedName("data")
    private UpActivityData upActivityData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpActivityData getData() {
        return upActivityData;
    }

    public void setData(UpActivityData upActivityData) {
        this.upActivityData = upActivityData;
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