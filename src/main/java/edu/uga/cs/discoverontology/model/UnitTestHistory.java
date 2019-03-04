package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;

public class UnitTestHistory {
	
	private int ID;
	private SystemTestHistory systemTestHistory;
	private MyUnitTest myUnitTest;
	private String status;
	private String message;
	
	public UnitTestHistory() {
	}

	public UnitTestHistory(int iD, SystemTestHistory systemTestHistory, MyUnitTest myUnitTest, String status,
			String message) {
		super();
		ID = iD;
		this.systemTestHistory = systemTestHistory;
		this.myUnitTest = myUnitTest;
		this.status = status;
		this.message = message;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public SystemTestHistory getSystemTestHistory() {
		return systemTestHistory;
	}

	public void setSystemTestHistory(SystemTestHistory systemTestHistory) {
		this.systemTestHistory = systemTestHistory;
	}

	public MyUnitTest getMyUnitTest() {
		return myUnitTest;
	}

	public void setMyUnitTest(MyUnitTest myUnitTest) {
		this.myUnitTest = myUnitTest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UnitTestHistory [ID=" + ID + ", systemTestHistory=" + systemTestHistory + ", myUnitTest=" + myUnitTest
				+ ", status=" + status + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((myUnitTest == null) ? 0 : myUnitTest.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((systemTestHistory == null) ? 0 : systemTestHistory.hashCode());
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
		UnitTestHistory other = (UnitTestHistory) obj;
		if (ID != other.ID)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (myUnitTest == null) {
			if (other.myUnitTest != null)
				return false;
		} else if (!myUnitTest.equals(other.myUnitTest))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (systemTestHistory == null) {
			if (other.systemTestHistory != null)
				return false;
		} else if (!systemTestHistory.equals(other.systemTestHistory))
			return false;
		return true;
	}

	
	
}
