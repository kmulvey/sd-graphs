/**
 *
 */
package roadgraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team
 *
 *         A class which represents a graph of geographic locations Nodes in the
 *         graph are intersections of multiple roads. Edges are the roads.
 *
 */
public class MapGraph {

	// Maintain both nodes and edges as you will need to
	// be able to look up nodes by lat/lon or by roads
	// that contain those nodes.
	private HashMap<GeographicPoint, Vertex> pointNodeMap;
	private HashSet<Edge> edges;

	/**
	 * Create a new empty MapGraph
	 *
	 */
	public MapGraph() {
		pointNodeMap = new HashMap<GeographicPoint, Vertex>();
		edges = new HashSet<Edge>();
	}

	/**
	 * Get the number of vertices (road intersections) in the graph
	 * 
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return pointNodeMap.values().size();
	}

	/**
	 * Get the number of road segments in the graph
	 * 
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges() {
		return edges.size();
	}

	// For us in DEBUGGING. Print the Nodes in the graph
	public void printNodes() {
		System.out.println("****PRINTING NODES ********");
		System.out.println("There are " + getNumVertices() + " Nodes: \n");
		for (GeographicPoint pt : pointNodeMap.keySet()) {
			Vertex n = pointNodeMap.get(pt);
			System.out.println(n);
		}
	}

	// For us in DEBUGGING. Print the Edges in the graph
	public void printEdges() {
		System.out.println("******PRINTING EDGES******");
		System.out.println("There are " + getNumEdges() + " Edges:\n");
		for (Edge e : edges) {
			System.out.println(e);
		}

	}

	/**
	 * Add a node corresponding to an intersection
	 *
	 * @param latitude
	 *          The latitude of the location
	 * @param longitude
	 *          The longitude of the location
	 */
	public void addVertex(double latitude, double longitude) {
		GeographicPoint pt = new GeographicPoint(latitude, longitude);
		this.addVertex(pt);
	}

	/**
	 * Add a node corresponding to an intersection at a Geographic Point
	 *
	 * @param location
	 *          The location of the intersection
	 */
	public void addVertex(GeographicPoint location) {
		Vertex n = pointNodeMap.get(location);
		if (n == null) {
			n = new Vertex(location);
			pointNodeMap.put(location, n);
		} else {
			System.out.println("Warning: Node at location " + location + " already exists in the graph.");
		}

	}

	/**
	 * Add an edge representing a segment of a road. Precondition: The
	 * corresponding Nodes must have already been added to the graph.
	 * 
	 * @param roadName
	 *          The name of the road
	 * @param roadType
	 *          The type of the road
	 */
	public void addEdge(double lat1, double lon1, double lat2, double lon2, String roadName, String roadType) {
		// Find the two Nodes associated with this edge.
		GeographicPoint pt1 = new GeographicPoint(lat1, lon1);
		GeographicPoint pt2 = new GeographicPoint(lat2, lon2);

		Vertex n1 = pointNodeMap.get(pt1);
		Vertex n2 = pointNodeMap.get(pt2);

		// check nodes are valid
		if (n1 == null) throw new NullPointerException("addEdge: pt1:" + pt1 + "is not in graph");
		if (n2 == null) throw new NullPointerException("addEdge: pt2:" + pt2 + "is not in graph");

		addEdge(n1, n2, roadName, roadType, Edge.DEFAULT_LENGTH);

	}

	public void addEdge(GeographicPoint pt1, GeographicPoint pt2, String roadName, String roadType) {

		Vertex n1 = pointNodeMap.get(pt1);
		Vertex n2 = pointNodeMap.get(pt2);

		// check nodes are valid
		if (n1 == null) throw new NullPointerException("addEdge: pt1:" + pt1 + "is not in graph");
		if (n2 == null) throw new NullPointerException("addEdge: pt2:" + pt2 + "is not in graph");

		addEdge(n1, n2, roadName, roadType, Edge.DEFAULT_LENGTH);
	}

	public void addEdge(GeographicPoint pt1, GeographicPoint pt2, String roadName, String roadType, double length) {
		Vertex n1 = pointNodeMap.get(pt1);
		Vertex n2 = pointNodeMap.get(pt2);

		// check nodes are valid
		if (n1 == null) throw new NullPointerException("addEdge: pt1:" + pt1 + "is not in graph");
		if (n2 == null) throw new NullPointerException("addEdge: pt2:" + pt2 + "is not in graph");

		addEdge(n1, n2, roadName, roadType, length);
	}

	/** Given a point, return if there is a corresponding Vertex **/
	public boolean isNode(GeographicPoint point) {
		return pointNodeMap.containsKey(point);
	}

	// Add an edge when you already know the nodes involved in the edge
	private void addEdge(Vertex n1, Vertex n2, String roadName, String roadType, double length) {
		Edge edge = new Edge(roadName, roadType, n1, n2, length);
		edges.add(edge);
		n1.addEdge(edge);
	}

	/** Returns the nodes in terms of their geographic locations */
	public Collection<GeographicPoint> getVertices() {
		return pointNodeMap.keySet();
	}

	// get a set of neighbor nodes from a Vertex
	private Set<Vertex> getNeighbors(Vertex node) {
		return node.getNeighbors();
	}

	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return bfs(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using Breadth First Search
	 *
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched) {
		// Setup - check validity of inputs
		if (start == null || goal == null) throw new NullPointerException("Cannot find route from or to null node");
		Vertex startNode = pointNodeMap.get(start);
		Vertex endNode = pointNodeMap.get(goal);
		if (startNode == null) {
			System.err.println("Start node " + start + " does not exist");
			return null;
		}
		if (endNode == null) {
			System.err.println("End node " + goal + " does not exist");
			return null;
		}

		// setup to begin BFS
		HashMap<Vertex, Vertex> parentMap = new HashMap<Vertex, Vertex>();
		Queue<Vertex> toExplore = new LinkedList<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();
		toExplore.add(startNode);
		Vertex next = null;

		while (!toExplore.isEmpty()) {
			next = toExplore.remove();

			// hook for visualization
			nodeSearched.accept(next.getLocation());

			if (next.equals(endNode)) break;
			Set<Vertex> neighbors = getNeighbors(next);
			for (Vertex neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					parentMap.put(neighbor, next);
					toExplore.add(neighbor);
				}
			}
		}
		if (!next.equals(endNode)) {
			System.out.println("No path found from " + start + " to " + goal);
			return null;
		}

		// Reconstruct the parent path
		List<GeographicPoint> path = reconstructPath(parentMap, startNode, endNode);

		return path;
	}

	/**
	 * Reconstruct a path from start to goal using the parentMap
	 *
	 * @param parentMap
	 *          the HashNode map of children and their parents
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */

	private List<GeographicPoint> reconstructPath(HashMap<Vertex, Vertex> parentMap, Vertex start, Vertex goal) {
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		Vertex current = goal;

		while (!current.equals(start)) {
			path.addFirst(current.getLocation());
			current = parentMap.get(current);
		}

		// add start
		path.addFirst(start.getLocation());
		return path;
	}

	/**
	 * Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return dijkstra(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @param nodeSearched
	 *          A hook for visualization. See assignment instructions for how to
	 *          use it.
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		Vertex startNode = pointNodeMap.get(start);
		startNode.setDistance(0);
		vertexQueue.add(startNode);
    while (!vertexQueue.isEmpty()) {
      Vertex u = vertexQueue.poll();
      // Visit each edge exiting u
      for (Edge e : u.getEdges()){
      	 Vertex v = e.getEndNode();
         double weight = e.getLength();
         double distanceThroughU = u.getDistance() + weight;
         if (distanceThroughU < v.getDistance()) {
           vertexQueue.remove(v);

           v.setDistance(distanceThroughU);
           v.setPrevious(u);
           vertexQueue.add(v);
       }
      }
    }

    Vertex goalNode = pointNodeMap.get(goal);
    List<GeographicPoint> path = new ArrayList<GeographicPoint>();
    for (Vertex vertex = goalNode; vertex != null; vertex = vertex.getPrevious())
        path.add(vertex.getLocation());

    Collections.reverse(path);
    return path;
	}

	/**
	 * Find the path from start to goal using A-Star search
	 * 
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		Consumer<GeographicPoint> temp = (x) -> {
		};
		return aStarSearch(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using A-Star search
	 * 
	 * @param start
	 *          The starting location
	 * @param goal
	 *          The goal location
	 * @param nodeSearched
	 *          A hook for visualization. See assignment instructions for how to
	 *          use it.
	 * @return The list of intersections that form the shortest path from start to
	 *         goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 3

		// Hook for visualization. See writeup.
		// nodeSearched.accept(next.getLocation());

		return null;
	}

	// main method for testing
	public static void main(String[] args) {
		/*
		 * Basic testing System.out.print("Making a new map..."); MapGraph theMap =
		 * new MapGraph(); System.out.print("DONE. \nLoading the map...");
		 * GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		 * System.out.println("DONE.");
		 */

		// more advanced testing
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");

		GraphLoader.loadRoadMap("data/testdata/simpletest.map", theMap);
		System.out.println("DONE.");

		System.out.println("Num nodes: " + theMap.getNumVertices());
		System.out.println("Num edges: " + theMap.getNumEdges());

		List<GeographicPoint> route = theMap.bfs(new GeographicPoint(1.0, 1.0), new GeographicPoint(8.0, -1.0));

		System.out.println(route);

		/*
		 * // Use this code in Week 3 End of Week Quiz MapGraph theMap = new
		 * MapGraph(); System.out.print("DONE. \nLoading the map...");
		 * GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		 * System.out.println("DONE.");
		 * 
		 * GeographicPoint start = new GeographicPoint(32.868629, -117.215393);
		 * GeographicPoint end = new GeographicPoint(32.868629, -117.215393);
		 * 
		 * List<GeographicPoint> route = theMap.dijkstra(start,end);
		 * List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
		 */

	}

}
