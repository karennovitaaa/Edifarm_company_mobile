package com.aditiyagilang.edifarm_company.model.addActivity;

import com.google.gson.annotations.SerializedName;

public class AddActivityData {

	@SerializedName("activity_name")
	private String activityName;

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

	@SerializedName("status")
	private String status;

	public void setActivityName(String activityName){
		this.activityName = activityName;
	}

	public String getActivityName(){
		return activityName;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEnd(String end){
		this.end = end;
	}

	public String getEnd(){
		return end;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}