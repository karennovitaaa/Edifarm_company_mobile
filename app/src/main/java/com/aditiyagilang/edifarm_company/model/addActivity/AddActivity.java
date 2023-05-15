package com.aditiyagilang.edifarm_company.model.addActivity;

import com.google.gson.annotations.SerializedName;

public class AddActivity {

    @SerializedName("data")
    private AddActivityData addActivityData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AddActivityData getData() {
        return addActivityData;
    }

    public void setData(AddActivityData addActivityData) {
        this.addActivityData = addActivityData;
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