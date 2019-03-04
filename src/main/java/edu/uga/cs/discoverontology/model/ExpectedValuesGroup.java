package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;

public class ExpectedValuesGroup {

	private int ID;
	private MyUnitTest unitTest;
	private ArrayList<ExpectedValue> expectedValues;
	
	public ExpectedValuesGroup() {
	}

	public ExpectedValuesGroup(int iD, MyUnitTest unitTest) {
		super();
		ID = iD;
		this.unitTest = unitTest;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public MyUnitTest getUnitTest() {
		return unitTest;
	}

	public void setUnitTest(MyUnitTest unitTest) {
		this.unitTest = unitTest;
	}

	public ArrayList<ExpectedValue> getExpectedValues() {
		return expectedValues;
	}

	public void setExpectedValues(ArrayList<ExpectedValue> expectedValues) {
		this.expectedValues = expectedValues;
	}

	@Override
	public String toString() {
		return "ExpectedValuesGroup [ID=" + ID + ", unitTest=" + unitTest + ", expectedValues=" + expectedValues + "]";
	}
	
	
	
}
