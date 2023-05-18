package com.aditiyagilang.edifarm_company.model.History;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class History {

    @SerializedName("data")
    private List<HistoryDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<HistoryDataItem> getData() {
        return data;
    }

    public void setData(List<HistoryDataItem> data) {
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