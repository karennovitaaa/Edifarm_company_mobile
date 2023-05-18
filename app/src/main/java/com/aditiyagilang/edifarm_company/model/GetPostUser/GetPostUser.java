package com.aditiyagilang.edifarm_company.model.GetPostUser;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostUser {

    @SerializedName("data")
    private List<GetPostUserDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<GetPostUserDataItem> getData() {
        return data;
    }

    public void setData(List<GetPostUserDataItem> data) {
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