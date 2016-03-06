



/**
 * A class to represent a node in the map
 */
package roadgraph;

import java.util.HashSet;
import java.util.Set;

import geography.GeographicPoint;

/**
 * @author UCSD MOOC development team
 * 
 * Class representing a vertex (or node) in our MapGraph
 *
 */
// WEEK 3 SOLUTIONS implements Comparable
class Vertex implements Comparable
{
	/** The list of edges out of this node */
	private HashSet<Edge> edges;
		
	/** the latitude and longitude of this node */
	private GeographicPoint location;
	
	// WEEK 3 SOLUTIONS
	
	/** the predicted distance of this node (used in Week 3 algorithms) */
	private double distance;
	
	/** the actual distance of this node from start (used in Week 3 algorithms) */
	private double actualDistance;
	
  private Vertex previous;
	
	// END WEEK 3 SOLUTIONS
	
	Vertex(GeographicPoint loc)
	{
		location = loc;
		edges = new HashSet<Edge>();
		distance = Double.POSITIVE_INFINITY;;
		actualDistance = 0.0;
	}
		
	public Vertex getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	void addEdge(Edge edge)
	{
		edges.add(edge);
	}
	
	/** Return the neighbors of this Vertex */
	Set<Vertex> getNeighbors()
	{
		Set<Vertex> neighbors = new HashSet<Vertex>();
		for (Edge edge : edges) {
			neighbors.add(edge.getOtherNode(this));
		}
		return neighbors;
	}
	
	/** get the location of a node */
	GeographicPoint getLocation()
	{
		return location;
	}
	
	/** return the edges out of this node */
	Set<Edge> getEdges()
	{
		return edges;
	}
	
	/** Returns whether two nodes are equal.
	 * Nodes are considered equal if their locations are the same, 
	 * even if their street list is different.
	 */
	public boolean equals(Object o)
	{
		if (!(o instanceof Vertex) || (o == null)) {
			return false;
		}
		Vertex node = (Vertex)o;
		return node.location.equals(this.location);
	}
	
	/** Because we compare nodes using their location, we also 
	 * may use their location for HashCode.
	 * @return The HashCode for this node, which is the HashCode for the 
	 * underlying point
	 */
	public int HashCode()
	{
		return location.hashCode();
	}
	
	/** ToString to print out a Vertex method
	 *  @return the string representation of a Vertex
	 */
	public String toString()
	{
		String toReturn = "[NODE at location (" + location + ")";
		toReturn += " intersects streets: ";
		for (Edge e: edges) {
			toReturn += e.getRoadName() + ", ";
		}
		toReturn += "]";
		return toReturn;
	}

	// For debugging, output roadNames as a String.
	public String roadNamesAsString()
	{
		String toReturn = "(";
		for (Edge e: edges) {
			toReturn += e.getRoadName() + ", ";
		}
		toReturn += ")";
		return toReturn;
	}

	//  WEEK 3 SOLUTIONS 
	
	// get node distance (predicted)
	public double getDistance() {
		return this.distance;
	}
	
	// set node distance (predicted)
	public void setDistance(double distance) {
	    this.distance = distance;
	}

	// get node distance (actual)
	public double getActualDistance() {
		return this.actualDistance;
	}
	
	// set node distance (actual)	
	public void setActualDistance(double actualDistance) {
	    this.actualDistance = actualDistance;
	}
	
    // Code to implement Comparable
	public int compareTo(Object o) {
		// convert to map node, may throw exception
		Vertex m = (Vertex)o; 
		return ((Double)this.getDistance()).compareTo((Double) m.getDistance());
	}

	// END WEEK 3 SOLUTIONS
}
