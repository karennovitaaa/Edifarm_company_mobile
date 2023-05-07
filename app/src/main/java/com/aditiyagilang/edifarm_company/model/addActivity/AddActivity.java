package com.aditiyagilang.edifarm_company.model.addActivity;

import com.google.gson.annotations.SerializedName;

public class AddActivity{

	@SerializedName("data")
	private AddActivityData addActivityData;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(AddActivityData addActivityData){
		this.addActivityData = addActivityData;
	}

	public AddActivityData getData(){
		return addActivityData;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}