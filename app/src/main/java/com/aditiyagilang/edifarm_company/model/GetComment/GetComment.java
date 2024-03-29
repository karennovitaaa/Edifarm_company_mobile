package com.aditiyagilang.edifarm_company.model.GetComment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetComment {

    @SerializedName("data")
    private List<GetCommentDataItem> data;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public List<GetCommentDataItem> getData() {
        return data;
    }

    public void setData(List<GetCommentDataItem> data) {
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