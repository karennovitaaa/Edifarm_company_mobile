package com.aditiyagilang.edifarm_company.model.SearchingUser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchibUser {

    @SerializedName("data")
    private List<SearchingUserDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<SearchingUserDataItem> getData() {
        return data;
    }

    public void setData(List<SearchingUserDataItem> data) {
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