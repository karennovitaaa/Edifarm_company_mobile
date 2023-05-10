package com.aditiyagilang.edifarm_company.model.updateactivity;

import com.google.gson.annotations.SerializedName;

public class UpdateActivity {

    @SerializedName("data")
    private UpdateActivityData updateActivityData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpdateActivityData getData() {
        return updateActivityData;
    }

    public void setData(UpdateActivityData updateActivityData) {
        this.updateActivityData = updateActivityData;
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