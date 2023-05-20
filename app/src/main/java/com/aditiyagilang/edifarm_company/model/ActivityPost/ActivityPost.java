package com.aditiyagilang.edifarm_company.model.ActivityPost;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityPost {

    @SerializedName("data")
    private List<ActivityPostDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<ActivityPostDataItem> getData() {
        return data;
    }

    public void setData(List<ActivityPostDataItem> data) {
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