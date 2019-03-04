package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;

public class MyUnitTest {
	
	private int ID;
	private String name;
	private String assertType;
	private String query;
	private String message;
	private MyTestSystem systemTest;
	private ArrayList<ExpectedValuesGroup> expectedValueGroups;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAssertType() {
		return assertType;
	}
	public void setAssertType(String assertType) {
		this.assertType = assertType;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MyTestSystem getSystemTest() {
		return systemTest;
	}
	public void setSystemTest(MyTestSystem systemTest) {
		this.systemTest = systemTest;
	}
	
	
	public ArrayList<ExpectedValuesGroup> getExpectedValueGroups() {
		return expectedValueGroups;
	}
	public void setExpectedValueGroups(ArrayList<ExpectedValuesGroup> expectedValueGroups) {
		this.expectedValueGroups = expectedValueGroups;
	}
	@Override
	public String toString() {
		return "MyUnitTest [ID=" + ID + ", name=" + name + ", assertType=" + assertType + ", query=" + query
				+ ", message=" + message + ", systemTest=" + systemTest + ", expectedValues=" + expectedValueGroups + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((assertType == null) ? 0 : assertType.hashCode());
		result = prime * result + ((expectedValueGroups == null) ? 0 : expectedValueGroups.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((systemTest == null) ? 0 : systemTest.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyUnitTest other = (MyUnitTest) obj;
		if (ID != other.ID)
			return false;
		if (assertType == null) {
			if (other.assertType != null)
				return false;
		} else if (!assertType.equals(other.assertType))
			return false;
		if (expectedValueGroups == null) {
			if (other.expectedValueGroups != null)
				return false;
		} else if (!expectedValueGroups.equals(other.expectedValueGroups))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (systemTest == null) {
			if (other.systemTest != null)
				return false;
		} else if (!systemTest.equals(other.systemTest))
			return false;
		return true;
	}
	
	

	


}
