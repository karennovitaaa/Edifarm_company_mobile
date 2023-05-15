package com.aditiyagilang.edifarm_company.model.Like;

import com.google.gson.annotations.SerializedName;

public class Like {

    @SerializedName("data")
    private LikeData likeData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public LikeData getData() {
        return likeData;
    }

    public void setData(LikeData likeData) {
        this.likeData = likeData;
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