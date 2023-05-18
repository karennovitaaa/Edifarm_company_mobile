package com.aditiyagilang.edifarm_company.model.AddReason;

import com.google.gson.annotations.SerializedName;

public class AddReason {

    @SerializedName("data")
    private AddReasonData addReasonData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AddReasonData getData() {
        return addReasonData;
    }

    public void setData(AddReasonData addReasonData) {
        this.addReasonData = addReasonData;
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