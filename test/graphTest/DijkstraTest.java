package graphTest;

import graph.Coordinate;
import graph.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.Path;
import graph.WeightType;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class DijkstraTest {

	@Test
	public void testDijkstraNull() {
		Path result = Dijkstra.dijkstra(null, null, null, WeightType.LENGTH);

		Assert.assertEquals(0, result.getSize());
	}

	@Test
	public void testShortestPathEmpty() {
		ArrayList<String> result = Dijkstra.shortestPath(new Graph(), "", "");

		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void testShortestPathABCD() {
		Graph g = buildPathABCD();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "A", "D");
		
		Assert.assertEquals(4, result.size());
		Assert.assertEquals("A", result.get(0));
		Assert.assertEquals("B", result.get(1));
		Assert.assertEquals("C", result.get(2));
		Assert.assertEquals("D", result.get(3));
	}
	
	@Test
	public void testShortestPathABC() {
		Graph g = buildPathABCD();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "A", "C");
		
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("A", result.get(0));
		Assert.assertEquals("B", result.get(1));
		Assert.assertEquals("C", result.get(2));
	}
	
	@Test
	public void testShortestPathFork() {
		Graph g = buildFork();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "A", "D");
		
		Assert.assertEquals(3, result.size());
		Assert.assertEquals("A", result.get(0));
		Assert.assertEquals("C", result.get(1));
		Assert.assertEquals("D", result.get(2));
	}
	
	@Test
	public void testShortestPathG1() {
		Graph g = buildG1();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "1", "4");
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("1", result.get(0));
		Assert.assertEquals("2", result.get(1));
		Assert.assertEquals("5", result.get(2));
		Assert.assertEquals("6", result.get(3));
		Assert.assertEquals("4", result.get(4));
	}
	
	@Test
	public void testFastestPath()
	{
		Graph g = buildWithSpeedLimit();
		
		ArrayList<String> result = Dijkstra.fastestPath(g, "A", "B");
		
		Assert.assertEquals("A", result.get(0));
		Assert.assertEquals("C", result.get(1));
		Assert.assertEquals("B", result.get(2));
	}
	
	@Test
	public void testAvoidTrafficJam()
	{
		Graph g = buildJammed();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "A", "B");
		
		Assert.assertEquals("A", result.get(0));
		Assert.assertEquals("C", result.get(1));
		Assert.assertEquals("B", result.get(2));
		
	}
	

	private Graph buildG1() {
		Graph g = new Graph();
		g.addNode(new Node(new Coordinate(0, 0), "1"));
		g.addNode(new Node(new Coordinate(4, 2), "2"));
		g.addNode(new Node(new Coordinate(14, 7), "3"));
		g.addNode(new Node(new Coordinate(6, 10), "4"));
		g.addNode(new Node(new Coordinate(4, 7), "5"));
		g.addNode(new Node(new Coordinate(1, 10), "6"));
		g.addNode(new Node(new Coordinate(-7, 5), "7"));
		g.addEdge("1", "2");
		g.addEdge("2", "3");
		g.addEdge("3", "4");
		g.addEdge("2", "5");
		g.addEdge("5", "3");
		g.addEdge("3", "5");
		g.addEdge("5", "6");
		g.addEdge("6", "4");
		g.addEdge("1", "7");
		g.addEdge("7", "6");
		
		return g;
	}

	private Graph buildPathABCD() {
		
		Graph g = new Graph();
		g.addNode(new Node(new Coordinate(1, 1), "A"));
		g.addNode(new Node(new Coordinate(2, 1), "B"));
		g.addNode(new Node(new Coordinate(3, 1), "C"));
		g.addNode(new Node(new Coordinate(4, 1), "D"));
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("C", "D");
		
		return g;
	}
	
	private Graph buildFork() {
		Graph g = new Graph();
		g.addNode(new Node(new Coordinate(1, 1), "A"));
		g.addNode(new Node(new Coordinate(2, 1), "B"));
		g.addNode(new Node(new Coordinate(1, 3), "C"));
		g.addNode(new Node(new Coordinate(1, 4), "D"));
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("C", "D");
		
		return g;
	}
	
	private Graph buildJammed()
	{
		Graph g = new Graph();
		Node n0 = new Node(new Coordinate(0, 0), "A");
		Node n = new Node(new Coordinate(0, 1), "B");
		
		
		Edge jammed = new Edge(n0, n, "Jammed");
		jammed.setTrafficJam(true);
		
		g.addEdge(jammed);
		g.addNode(new Node(new Coordinate(10,10), "C"));
		g.addEdge("A", "C");
		g.addEdge("C", "B");
		
		return g;
	}
	
	private Graph buildWithSpeedLimit()
	{
		Graph g = new Graph();
		Node n0 = new Node(new Coordinate(0, 0), "A");
		Node n = new Node(new Coordinate(0, 10), "B");
		Node n1 = new Node(new Coordinate(30, 30), "C");
		
		Edge slow = new Edge(n0, n, "Slow");
		slow.setSpeedLimit(10);
		Edge fast1 = new Edge(n0, n1, "RoadWay1");
		fast1.setSpeedLimit(100);
		Edge fast2 = new Edge(n1, n, "RoadWay2");
		fast2.setSpeedLimit(100);
		
		g.addEdge(slow);
		g.addEdge(fast1);
		g.addEdge(fast2);
		
		return g;
	}

}
