package com.aditiyagilang.edifarm_company.model.GetFullActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFullActivity {

    @SerializedName("data")
    private List<GetFullActivityDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<GetFullActivityDataItem> getData() {
        return data;
    }

    public void setData(List<GetFullActivityDataItem> data) {
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