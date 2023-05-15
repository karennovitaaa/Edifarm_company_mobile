package com.aditiyagilang.edifarm_company.model.getSession;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSession {

    @SerializedName("data")
    private List<GetSessionDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<GetSessionDataItem> getData() {
        return data;
    }

    public void setData(List<GetSessionDataItem> data) {
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