package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class VenuePage{

	@SerializedName("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"VenuePage{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}