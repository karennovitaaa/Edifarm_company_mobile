package com.aditiyagilang.edifarm_company.model.register;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("data")
	private RegisterData data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("massage")
	private String massage;

	public void setData(RegisterData data){
		this.data = data;
	}

	public RegisterData getData(){
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