package com.seattle.expedia_test_app.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created By Sandeep Rai on 2019-06-16
 */

public class Icon{

	@SerializedName("prefix")
	private String prefix;

	@SerializedName("suffix")
	private String suffix;

	public void setPrefix(String prefix){
		this.prefix = prefix;
	}

	public String getPrefix(){
		return prefix;
	}

	public void setSuffix(String suffix){
		this.suffix = suffix;
	}

	public String getSuffix(){
		return suffix;
	}

	@Override
 	public String toString(){
		return 
			"Icon{" + 
			"prefix = '" + prefix + '\'' + 
			",suffix = '" + suffix + '\'' + 
			"}";
		}
}