package roadgraph;

import org.junit.Test;

import geography.GeographicPoint;

public class MapGraphTest {
	@Test
	public void tester() {
		MapGraph g = new MapGraph();
		GeographicPoint zero = new GeographicPoint(0, 0);
		GeographicPoint one = new GeographicPoint(1,1);
		GeographicPoint two = new GeographicPoint(2,2);
		GeographicPoint three = new GeographicPoint(3,3);
		GeographicPoint four = new GeographicPoint(4,4);
		GeographicPoint five = new GeographicPoint(5,5);
		
		g.addVertex(zero);
		g.addVertex(one);
		g.addVertex(two);
		g.addVertex(three);
		g.addVertex(four);
		g.addVertex(five);
		
		g.addEdge(zero, five, "5", "asd", 1);
		g.addEdge(zero, one, "1", "asd", 1);
		
		g.addEdge(two, zero, "0", "asd", 1);
		g.addEdge(two, three, "3", "asd", 1);
		
		g.addEdge(three, five, "5", "asd", 1);
		g.addEdge(three, two, "2", "asd", 1);
		
		g.addEdge(four, three, "3", "asd", 1);
		g.addEdge(four, two, "2", "asd", 1);
		
		g.addEdge(five, four, "4", "asd", 1);
		
		g.print();
		System.out.println("Edges: " + g.getNumEdges());
		System.out.println("Verticies: " + g.getNumVertices());
	}
}


