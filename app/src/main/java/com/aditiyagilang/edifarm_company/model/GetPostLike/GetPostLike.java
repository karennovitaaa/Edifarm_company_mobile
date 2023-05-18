package com.aditiyagilang.edifarm_company.model.GetPostLike;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostLike {

    @SerializedName("data")
    private List<GetPostLikeDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<GetPostLikeDataItem> getData() {
        return data;
    }

    public void setData(List<GetPostLikeDataItem> data) {
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