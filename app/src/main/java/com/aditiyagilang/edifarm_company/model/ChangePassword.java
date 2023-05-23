package com.aditiyagilang.edifarm_company.model;

import com.google.gson.annotations.SerializedName;

public class ChangePassword{

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}