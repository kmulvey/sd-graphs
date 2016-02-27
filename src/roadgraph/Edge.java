package roadgraph;

import geography.GeographicPoint;

public class Edge {
	private GeographicPoint location;
	private String roadName;
	private String roadType;
	private double length;

	public Edge(GeographicPoint loc, String name, String type, double len) {
		location = loc;
		roadName = name;
		roadType = type;
		length = len;
	}

	public GeographicPoint getLocation() {
		return location;
	}

	public void setLoc(GeographicPoint loc) {
		this.location = loc;
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
