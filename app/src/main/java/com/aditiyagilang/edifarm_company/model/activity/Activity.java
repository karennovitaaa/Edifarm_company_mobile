package com.aditiyagilang.edifarm_company.model.activity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Activity {

    @SerializedName("data")
    private List<ActivityDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<ActivityDataItem> getData() {
        return data;
    }

    public void setData(List<ActivityDataItem> data) {
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

    @Override
    public String toString() {
        return
                "Activity.Activity{" +
                        "data = '" + data + '\'' +
                        ",success = '" + success + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}