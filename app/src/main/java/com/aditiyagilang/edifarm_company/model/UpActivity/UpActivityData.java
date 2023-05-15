package com.aditiyagilang.edifarm_company.model.UpActivity;

import com.google.gson.annotations.SerializedName;

public class UpActivityData {

    @SerializedName("activity_name")
    private String activityName;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("start")
    private String start;

    @SerializedName("session_id")
    private int sessionId;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("end")
    private String end;

    @SerializedName("id")
    private int id;

    @SerializedName("status")
    private String status;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}