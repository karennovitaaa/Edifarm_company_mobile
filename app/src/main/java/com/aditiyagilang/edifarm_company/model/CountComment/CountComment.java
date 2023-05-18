package com.aditiyagilang.edifarm_company.model.CountComment;

import com.google.gson.annotations.SerializedName;

public class CountComment{

	@SerializedName("data")
	private int data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
		return data;
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