package com.aditiyagilang.edifarm_company.model.addActivity;

import com.google.gson.annotations.SerializedName;

public class AddActivityData {

    @SerializedName("activity_name")
    private String activityName;

    @SerializedName("start")
    private String start;

    @SerializedName("session_id")
    private String sessionId;

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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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