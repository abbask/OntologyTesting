package edu.uga.cs.discoverontology.model;

import java.util.ArrayList;

public class MyTestSystem {
	private int ID;
	private String name;
	private String endPoint;
	private String graph;
	private ArrayList<MyUnitTest> units;
	
	public MyTestSystem() {
		
	}
	
	public MyTestSystem(String name, String endPoint, String graph, ArrayList<MyUnitTest> units) {
		this.name = name;
		this.endPoint = endPoint;
		this.graph = graph;
		this.units = units;
	}
	
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
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public ArrayList<MyUnitTest> getSuites() {
		return units;
	}
	public void setSuites(ArrayList<MyUnitTest> units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "MyTestSystem [name=" + name + ", endPoint=" + endPoint + ", graph=" + graph + ", suites=" + units
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((endPoint == null) ? 0 : endPoint.hashCode());
		result = prime * result + ((graph == null) ? 0 : graph.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
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
		MyTestSystem other = (MyTestSystem) obj;
		if (ID != other.ID)
			return false;
		if (endPoint == null) {
			if (other.endPoint != null)
				return false;
		} else if (!endPoint.equals(other.endPoint))
			return false;
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}
	
	

}
