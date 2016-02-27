package roadgraph;

import geography.GeographicPoint;

public class Edge {
	private GeographicPoint loc;
	private String roadName;
	private String roadType;
	private double length;
	
	public Edge(GeographicPoint l, String name, String type, double len){
		loc = l;
		roadName=name;
		roadType=type;
		length=len;
	}
	public GeographicPoint getLoc() {
		return loc;
	}
	public void setLoc(GeographicPoint loc) {
		this.loc = loc;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
}
