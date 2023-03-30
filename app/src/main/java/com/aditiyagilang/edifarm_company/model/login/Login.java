package com.aditiyagilang.edifarm_company.model.login;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("data")
	private LoginData data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("massage")
	private String massage;

	public void setData(LoginData data){
		this.data = data;
	}

	public LoginData getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMassage(String massage){
		this.massage = massage;
	}

	public String getMassage(){
		return massage;
	}
}