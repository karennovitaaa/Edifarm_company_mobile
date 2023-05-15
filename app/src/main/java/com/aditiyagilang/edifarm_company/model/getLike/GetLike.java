package com.aditiyagilang.edifarm_company.model.getLike;

import com.google.gson.annotations.SerializedName;

public class GetLike {

    @SerializedName("data")
    private GetLikeData getLikeData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public GetLikeData getData() {
        return getLikeData;
    }

    public void setData(GetLikeData getLikeData) {
        this.getLikeData = getLikeData;
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