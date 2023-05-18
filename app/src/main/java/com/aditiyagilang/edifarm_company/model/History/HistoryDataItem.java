package com.aditiyagilang.edifarm_company.model.History;

import com.google.gson.annotations.SerializedName;

public class HistoryDataItem {

    @SerializedName("session_created_at")
    private String sessionCreatedAt;

    @SerializedName("pdf_file")
    private String pdfFile;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("session_id")
    private int sessionId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("documentation_created_at")
    private String documentationCreatedAt;

    @SerializedName("id")
    private int id;

    @SerializedName("plant_name")
    private String plantName;

    public String getSessionCreatedAt() {
        return sessionCreatedAt;
    }

    public void setSessionCreatedAt(String sessionCreatedAt) {
        this.sessionCreatedAt = sessionCreatedAt;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDocumentationCreatedAt() {
        return documentationCreatedAt;
    }

    public void setDocumentationCreatedAt(String documentationCreatedAt) {
        this.documentationCreatedAt = documentationCreatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}