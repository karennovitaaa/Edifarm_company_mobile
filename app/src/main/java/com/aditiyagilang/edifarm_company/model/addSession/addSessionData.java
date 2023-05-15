package com.aditiyagilang.edifarm_company.model.addSession;

import com.google.gson.annotations.SerializedName;

public class addSessionData {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("start")
    private String start;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("end")
    private String end;

    @SerializedName("id")
    private int id;

    @SerializedName("plant_name")
    private String plantName;

    @SerializedName("status")
    private String status;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}