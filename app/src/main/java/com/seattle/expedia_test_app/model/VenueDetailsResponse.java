package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class VenueDetailsResponse{

	@SerializedName("response")
	private DetailsResponse response;

	public void setResponse(DetailsResponse response){
		this.response = response;
	}

	public DetailsResponse getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"VenueDetailsResponse{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}