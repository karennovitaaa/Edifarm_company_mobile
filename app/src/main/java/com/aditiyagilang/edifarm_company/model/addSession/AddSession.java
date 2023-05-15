package com.aditiyagilang.edifarm_company.model.addSession;

import com.google.gson.annotations.SerializedName;

public class AddSession {

    @SerializedName("data")
    private addSessionData addSessionData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public addSessionData getData() {
        return addSessionData;
    }

    public void setData(addSessionData addSessionData) {
        this.addSessionData = addSessionData;
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