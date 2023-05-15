package com.aditiyagilang.edifarm_company.model.CountLike;

import com.google.gson.annotations.SerializedName;

public class CountLike {

    @SerializedName("data")
    private int data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public int getData() {
        return data;
    }

    public void setData(int data) {
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