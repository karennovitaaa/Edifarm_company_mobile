package com.aditiyagilang.edifarm_company.model.GetRate;

import com.google.gson.annotations.SerializedName;

public class GetRating{

	@SerializedName("data")
	private Object data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
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