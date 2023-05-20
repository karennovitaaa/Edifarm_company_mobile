package com.aditiyagilang.edifarm_company.model.DeletePost;

import com.google.gson.annotations.SerializedName;

public class DeletePost {

    @SerializedName("data")
    private DeletePostData deletePostData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public DeletePostData getData() {
        return deletePostData;
    }

    public void setData(DeletePostData deletePostData) {
        this.deletePostData = deletePostData;
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