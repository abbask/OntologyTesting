package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpectedValueGson {
	
	@SerializedName("expectedValues")
	@Expose
	private ExpectedValue[] expectedValues;

	public ExpectedValue[] getExpectedValues() {
		return expectedValues;
	}

	public void setExpectedValues(ExpectedValue[] expectedValues) {
		this.expectedValues = expectedValues;
	}


	
	
	
}