package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;
import java.util.Date;

public class SystemTestHistory {
	
	private int ID;
	private MyTestSystem mySystemTest;
	private Date date;
	private ArrayList<UnitTestHistory> unitTestHistorys;

	public SystemTestHistory() {
	}

	public SystemTestHistory(int iD, MyTestSystem mySystemTest, Date date,
			ArrayList<UnitTestHistory> unitTestHistorys) {
		super();
		ID = iD;
		this.mySystemTest = mySystemTest;
		this.date = date;
		this.unitTestHistorys = unitTestHistorys;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public MyTestSystem getMySystemTest() {
		return mySystemTest;
	}

	public void setMySystemTest(MyTestSystem mySystemTest) {
		this.mySystemTest = mySystemTest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<UnitTestHistory> getUnitTestHistorys() {
		return unitTestHistorys;
	}

	public void setUnitTestHistorys(ArrayList<UnitTestHistory> unitTestHistorys) {
		this.unitTestHistorys = unitTestHistorys;
	}

	@Override
	public String toString() {
		return "SystemTestHistory [ID=" + ID + ", mySystemTest=" + mySystemTest + ", date=" + date
				+ ", unitTestHistorys=" + unitTestHistorys + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((mySystemTest == null) ? 0 : mySystemTest.hashCode());
		result = prime * result + ((unitTestHistorys == null) ? 0 : unitTestHistorys.hashCode());
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
		SystemTestHistory other = (SystemTestHistory) obj;
		if (ID != other.ID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mySystemTest == null) {
			if (other.mySystemTest != null)
				return false;
		} else if (!mySystemTest.equals(other.mySystemTest))
			return false;
		if (unitTestHistorys == null) {
			if (other.unitTestHistorys != null)
				return false;
		} else if (!unitTestHistorys.equals(other.unitTestHistorys))
			return false;
		return true;
	}

	
	
}
