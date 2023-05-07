package com.aditiyagilang.edifarm_company.model.activity;

import com.google.gson.annotations.SerializedName;

public class ActivityDataItem {

	@SerializedName("activity_name")
	private String activityName;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("start")
	private String start;

	@SerializedName("created_at")
	private Object createdAt;

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

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
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

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"activity_name = '" + activityName + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",start = '" + start + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",end = '" + end + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}