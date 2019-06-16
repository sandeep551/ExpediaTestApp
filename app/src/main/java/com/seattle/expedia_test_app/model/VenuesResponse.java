package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

public class VenuesResponse{

	@SerializedName("response")
	private Response response;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"VenuesResponse{" + 
			"response = '" + response + '\'' +
			"}";
		}
}