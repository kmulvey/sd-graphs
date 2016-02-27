package roadgraph;

import org.junit.Test;

import geography.GeographicPoint;

public class MapGraphTest {
//	@Test
//	public void tester() {
//		MapGraph g = new MapGraph();
//		GeographicPoint zero = new GeographicPoint(0, 0);
//		GeographicPoint one = new GeographicPoint(1,1);
//		GeographicPoint two = new GeographicPoint(2,2);
//		GeographicPoint three = new GeographicPoint(3,3);
//		GeographicPoint four = new GeographicPoint(4,4);
//		GeographicPoint five = new GeographicPoint(5,5);
//		
//		g.addVertex(zero);
//		g.addVertex(one);
//		g.addVertex(two);
//		g.addVertex(three);
//		g.addVertex(four);
//		g.addVertex(five);
//		
//		g.addEdge(zero, five, "5", "asd", 1);
//		g.addEdge(zero, one, "1", "asd", 1);
//		
//		g.addEdge(two, zero, "0", "asd", 1);
//		g.addEdge(two, three, "3", "asd", 1);
//		
//		g.addEdge(three, five, "5", "asd", 1);
//		g.addEdge(three, two, "2", "asd", 1);
//		
//		g.addEdge(four, three, "3", "asd", 1);
//		g.addEdge(four, two, "2", "asd", 1);
//		
//		g.addEdge(five, four, "4", "asd", 1);
//		
//		g.print();
//		System.out.println("Edges: " + g.getNumEdges());
//		System.out.println("Verticies: " + g.getNumVertices());
//	}
	@Test
	public void straight() {
		MapGraph g = new MapGraph();
		GeographicPoint zeroZero = new GeographicPoint(0, 0);
		GeographicPoint zeroOne = new GeographicPoint(0,1);
		GeographicPoint zeroTwo = new GeographicPoint(0,2);
		GeographicPoint oneTwo = new GeographicPoint(1,2);
		GeographicPoint twoTwo = new GeographicPoint(2,2);
		GeographicPoint twoOne = new GeographicPoint(2,1);
		GeographicPoint twoZero = new GeographicPoint(2,0);
		GeographicPoint oneZero = new GeographicPoint(1,0);

		
		g.addVertex(zeroZero);
		g.addVertex(zeroOne);
		g.addVertex(zeroTwo);
		g.addVertex(oneTwo);
		g.addVertex(twoTwo);
		g.addVertex(twoOne);
		g.addVertex(twoZero);
		g.addVertex(oneZero);
		
		g.addEdge(zeroZero, zeroOne, "Road 1", "asd", 1);
		g.addEdge(zeroOne, zeroZero, "Road 1", "asd", 1);
		
		g.addEdge(zeroOne, zeroTwo, "Road 2", "asd", 1);
		g.addEdge(zeroTwo, zeroOne, "Road 2", "asd", 1);
		
		g.addEdge(zeroTwo, oneTwo, "Road 3", "asd", 1);
		g.addEdge(oneTwo, zeroOne, "Road 3", "asd", 1);
		
		g.addEdge(oneTwo, twoTwo, "Road 4", "asd", 1);
		g.addEdge(twoTwo, oneTwo, "Road 4", "asd", 1);
		
		g.addEdge(twoTwo, twoOne, "Road 5", "asd", 1);
		g.addEdge(twoOne, twoTwo, "Road 5", "asd", 1);
		
		g.addEdge(twoOne, twoZero, "Road 6", "asd", 1);
		g.addEdge(twoZero, twoOne, "Road 6", "asd", 1);
		
		g.addEdge(twoZero, oneZero, "Road 7", "asd", 1);
		g.addEdge(oneZero, twoZero, "Road 7", "asd", 1);
		
		g.addEdge(oneZero, zeroZero, "Road 8", "asd", 1);
		g.addEdge(zeroZero, oneZero, "Road 8", "asd", 1);


		/*
		0 0 0 1 "Road 1" edge
		0 1 0 0 "Road 1" edge
		
		0 1 0 2 "Road 2" edge
		0 2 0 1 "Road 2" edge
		
		0 2 1 2 "Road 3" edge
		1 2 0 2 "Road 3" edge
		
		1 2 2 2 "Road 4" edge
		2 2 1 2 "Road 4" edge
		
		2 2 2 1 "Road 5" edge
		2 1 2 2 "Road 5" edge
		
		2 1 2 0 "Road 6" edge
		2 0 2 1 "Road 6" edge
		
		2 0 1 0 "Road 7" edge
		1 0 2 0 "Road 7" edge
		
		1 0 0 0 "Road 8" edge
		0 0 1 0 "Road 8" edge
		]
		
		
										 
			20	========== 10 
							||
							||
							||
			21				00 ========= 01
										 ||
										 ||
										 ||
				22 ======== 12 ========= 02
		
		
		
		
		||
		||
		||
		22 
		
		Lat: 0.0, Lon: 0.0
		Lat: 0.0, Lon: 1.0
		Lat: 0.0, Lon: 2.0
		Lat: 1.0, Lon: 2.0
		
		Lat: 0.0, Lon: 0.0
		Lat: 0.0, Lon: 1.0
		Lat: 1.0, Lon: 0.0
		Lat: 2.0, Lon: 0.0
		Lat: 2.0, Lon: 1.0
		Lat: 2.0, Lon: 2.0
		Lat: 1.0, Lon: 2.0
		
		
		*/
		g.print();
		System.out.println("Edges: " + g.getNumEdges());
		System.out.println("Verticies: " + g.getNumVertices());
		
		g.bfs(zeroZero, oneZero);
	}
}


