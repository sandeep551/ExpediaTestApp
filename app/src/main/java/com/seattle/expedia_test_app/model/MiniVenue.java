package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class MiniVenue {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"MiniVenue{" +
			"name = '" + name + '\'' +
			",id = '" + id + '\'' +
			"}";
		}
}