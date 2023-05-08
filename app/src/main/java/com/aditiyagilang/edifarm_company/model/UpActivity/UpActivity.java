package com.aditiyagilang.edifarm_company.model.UpActivity;

import com.google.gson.annotations.SerializedName;

public class UpActivity {

    @SerializedName("data")
    private UpActivityData data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpActivityData getData() {
        return data;
    }

    public void setData(UpActivityData data) {
        this.data = data;
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