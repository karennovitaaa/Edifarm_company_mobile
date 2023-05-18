package com.aditiyagilang.edifarm_company.model.documentation;

import com.google.gson.annotations.SerializedName;

public class Documentation {

    @SerializedName("data")
    private DocumentationData documentationData;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public DocumentationData getData() {
        return documentationData;
    }

    public void setData(DocumentationData documentationData) {
        this.documentationData = documentationData;
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