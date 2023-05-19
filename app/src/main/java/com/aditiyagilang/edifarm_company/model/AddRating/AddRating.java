package com.aditiyagilang.edifarm_company.model.AddRating;

import com.google.gson.annotations.SerializedName;

public class AddRating {

    @SerializedName("data")
    private AddRatingData addRatingData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AddRatingData getData() {
        return addRatingData;
    }

    public void setData(AddRatingData addRatingData) {
        this.addRatingData = addRatingData;
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