package com.aditiyagilang.edifarm_company.model.AddComment;

import com.google.gson.annotations.SerializedName;

public class AddComment {

    @SerializedName("data")
    private AddCommentData addCommentData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public AddCommentData getData() {
        return addCommentData;
    }

    public void setData(AddCommentData addCommentData) {
        this.addCommentData = addCommentData;
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