package edu.uga.cs.discoverontology.model;

public class ExpectedValue {
	
	private int ID;
	private String originalName;
	private String useName;
	private String index;
	private String value;
	private int groupId;
	private ExpectedValuesGroup expectedValuesGroup;
	
	public ExpectedValue() {
	}
	
	public ExpectedValue(String originalName, String useName, String index, String value) {
		super();
		this.originalName = originalName;
		this.useName = useName;
		this.index = index;
		this.value = value;
	}
	public ExpectedValue(int iD, String originalName, String useName, String index, String value, ExpectedValuesGroup expectedValuesGroup) {
		super();
		ID = iD;
		this.originalName = originalName;
		this.useName = useName;
		this.index = index;
		this.value = value;
		this.expectedValuesGroup = expectedValuesGroup;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	public ExpectedValuesGroup getExpectedValuesGroup() {
		return expectedValuesGroup;
	}

	public void setExpectedValuesGroup(ExpectedValuesGroup expectedValuesGroup) {
		this.expectedValuesGroup = expectedValuesGroup;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "ExpectedValue [ID=" + ID + ", originalName=" + originalName + ", useName=" + useName + ", index="
				+ index + ", value=" + value + ", expectedValuesGroup=" + expectedValuesGroup + "]";
	}
		

}
