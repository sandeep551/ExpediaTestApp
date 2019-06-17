package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class Response{

	@SerializedName("venues")
	private List<Venue> venues;

	public void setVenues(List<Venue> venues){
		this.venues = venues;
	}

	public List<Venue> getVenues(){
		return venues;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"venues = '" + venues + '\'' + 
			"}";
		}
}