package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class MiniResponse{

	@SerializedName("minivenues")
	private List<MiniVenue> minivenues;

	public void setMinivenues(List<MiniVenue> minivenues){
		this.minivenues = minivenues;
	}

	public List<MiniVenue> getMinivenues(){
		return minivenues;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"minivenues = '" + minivenues + '\'' + 
			"}";
		}
}