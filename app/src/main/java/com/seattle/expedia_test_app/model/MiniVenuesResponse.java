package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class MiniVenuesResponse{

	@SerializedName("response")
	private MiniResponse response;

	public void setResponse(MiniResponse response){
		this.response = response;
	}

	public MiniResponse getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"MiniVenuesResponse{" + 
			"response = '" + response + '\'' +
			"}";
		}
}