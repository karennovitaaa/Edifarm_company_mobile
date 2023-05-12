package com.aditiyagilang.edifarm_company.model.FilterActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterActivity {

    @SerializedName("data")
    private List<FilterActivityDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<FilterActivityDataItem> getData() {
        return data;
    }

    public void setData(List<FilterActivityDataItem> data) {
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