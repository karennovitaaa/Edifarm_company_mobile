package com.aditiyagilang.edifarm_company.model.Stalking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StalkingAcount {

    @SerializedName("data")
    private List<StalkingAcountDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<StalkingAcountDataItem> getData() {
        return data;
    }

    public void setData(List<StalkingAcountDataItem> data) {
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