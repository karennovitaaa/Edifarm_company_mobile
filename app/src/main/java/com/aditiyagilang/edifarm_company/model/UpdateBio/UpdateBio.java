package com.aditiyagilang.edifarm_company.model.UpdateBio;

import com.google.gson.annotations.SerializedName;

public class UpdateBio {

    @SerializedName("data")
    private UpdateBioData updateBioData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public UpdateBioData getData() {
        return updateBioData;
    }

    public void setData(UpdateBioData updateBioData) {
        this.updateBioData = updateBioData;
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