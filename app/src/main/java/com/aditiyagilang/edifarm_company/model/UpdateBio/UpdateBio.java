package com.aditiyagilang.edifarm_company.model.UpdateBio;

import com.google.gson.annotations.SerializedName;

public class UpdateBio{

	@SerializedName("data")
	private UpdateBioData updateBioData;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(UpdateBioData updateBioData){
		this.updateBioData = updateBioData;
	}

	public UpdateBioData getData(){
		return updateBioData;
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