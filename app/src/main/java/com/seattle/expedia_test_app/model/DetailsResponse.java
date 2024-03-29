package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class DetailsResponse {

	@SerializedName("venue")
	private Venue venue;

	public void setVenue(Venue venue){
		this.venue = venue;
	}

	public Venue getVenue(){
		return venue;
	}

	@Override
 	public String toString(){
		return 
			"DetailsResponse{" +
			"venue = '" + venue + '\'' + 
			"}";
		}
}