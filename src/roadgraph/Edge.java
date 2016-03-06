/**
 * 
 */
package roadgraph;

import geography.GeographicPoint;

/**
 * @author UCSD Intermediate Programming MOOC team
 *
 * A directed edge in a map graph from Node start to Node end
 */
class Edge 
{
	/** The name of the road */
	private String roadName;
	
	/** The type of the road */
	private String roadType;
	
	/** The two endpoints of the edge */
	private Vertex start;
	private Vertex end;
	
	
	/** The length of the road segment, in km */
	private double length;
	
	static final double DEFAULT_LENGTH = 0.01;
	
	
	/** Create a new Edge object
	 * 
	 * @param roadName
	 * @param n1  The point at one end of the segment
	 * @param n2  The point at the other end of the segment
	 * 
	 */
	Edge(String roadName, Vertex n1, Vertex n2) 
	{
		this(roadName, "", n1, n2, DEFAULT_LENGTH);
	}
	
	Edge(String roadName, String roadType, Vertex n1, Vertex n2) 
	{
		this(roadName, roadType, n1, n2, DEFAULT_LENGTH);
	}
	
	Edge(String roadName, String roadType,
			Vertex n1, Vertex n2, double length) 
	{
		this.roadName = roadName;
		start = n1;
		end = n2;
		this.roadType = roadType;
		this.length = length;
	}
	
	// return the Vertex for the end point
	Vertex getEndNode() {
	   return end;
	}
	
	// return the location of the start point
	GeographicPoint getStartPoint()
	{
		return start.getLocation();
	}
	
	// return the location of the end point
	GeographicPoint getEndPoint()
	{
		return end.getLocation();
	}
	
	// return the length
	double getLength()
	{
		return length;
	}
	
	
	
	// return road name
	public String getRoadName()
	{
		return roadName;
	}
	
	// given one node in an edge, return the other node
	Vertex getOtherNode(Vertex node)
	{
		if (node.equals(start)) 
			return end;
		else if (node.equals(end))
			return start;
		throw new IllegalArgumentException("Looking for " +
			"a point that is not in the edge");
	}
	
	// return String containing details about the edge
	public String toString()
	{
		String toReturn = "[EDGE between ";
		toReturn += "\n\t" + start.getLocation();
		toReturn += "\n\t" + end.getLocation();
		toReturn += "\nRoad name: " + roadName + " Road type: " + roadType +
				" Segment length: " + String.format("%.3g", length) + "km";
		
		return toReturn;
	}

}
