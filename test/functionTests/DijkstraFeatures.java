package functionTests;

import static org.junit.Assert.*;
import graph.Coordinate;
import graph.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class DijkstraFeatures {


	@Test
	public void testShortestCostsOfEdges() {
		Graph g = buildCost();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "1", "4");
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("1", result.get(0));
		Assert.assertEquals("2", result.get(1));
		Assert.assertEquals("5", result.get(2));
		Assert.assertEquals("6", result.get(3));
		Assert.assertEquals("4", result.get(4));

		ArrayList<Double> costResults = Dijkstra.ActualCostsOfEdges(g, "1", "4");

		Assert.assertEquals(4, costResults.size());
		Assert.assertEquals(1.0, costResults.get(0));
		Assert.assertEquals(1.0, costResults.get(1));
		Assert.assertEquals(1.0, costResults.get(2));
		Assert.assertEquals(1.0, costResults.get(3));
		
	}
	
	@Test
	public void testShortestLengthOfPath() {
		Graph g = buildLengthOfPath();
		
		double result = Dijkstra.ActualLengthOfPath(g, "1", "4");
		
		Assert.assertEquals(18.714776642118863, result);
	}
	
	@Test
	public void testShortestDurationsOfEdges() {
		Graph g = buildDurations();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "1", "4");
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("1", result.get(0));
		Assert.assertEquals("2", result.get(1));
		Assert.assertEquals("5", result.get(2));
		Assert.assertEquals("6", result.get(3));
		Assert.assertEquals("4", result.get(4));

		ArrayList<Double> durationResults = Dijkstra.ActualDurationsOfEdges(g, "1", "4");

		Assert.assertEquals(4, durationResults.size());
		Assert.assertEquals(0.08944271909999159, durationResults.get(0));
		Assert.assertEquals(0.1, durationResults.get(1));
		Assert.assertEquals(0.0848528137423857, durationResults.get(2));
		Assert.assertEquals(0.1, durationResults.get(3));
		
	}

	@Test
	public void testShortestDurationsOfPath() {
		Graph g = buildDurations();
		
		ArrayList<String> result = Dijkstra.shortestPath(g, "1", "4");
		
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("1", result.get(0));
		Assert.assertEquals("2", result.get(1));
		Assert.assertEquals("5", result.get(2));
		Assert.assertEquals("6", result.get(3));
		Assert.assertEquals("4", result.get(4));

		double durationResult = Dijkstra.ActualDurationsOfPath(g, "1", "4");

		Assert.assertEquals(0.37429553284237727, durationResult);
		
	}

		
	private Graph buildLengthOfPath() {
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
	
	private Graph buildCost() {
		Graph g = new Graph();
		
		Node n1 = new Node(new Coordinate(0, 0), "1");
		Node n2 = new Node(new Coordinate(4, 2), "2");
		Node n3 = new Node(new Coordinate(14, 7), "3");
		Node n4 = new Node(new Coordinate(6, 10), "4");
		Node n5 = new Node(new Coordinate(4, 7), "5");
		Node n6 = new Node(new Coordinate(1, 10), "6");
		Node n7 = new Node(new Coordinate(-7, 5), "7");
				
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);

		Edge e12 = new Edge(n1, n2, "e12", 1);
		Edge e25 = new Edge(n2, n5, "e25", 1);
		Edge e56 = new Edge(n5, n6, "e56", 1);
		Edge e64 = new Edge(n6, n4, "e64", 1);

		Edge e23 = new Edge(n1, n3, "e23", 4);
		Edge e34 = new Edge(n3, n4, "e34", 5);
		Edge e53 = new Edge(n5, n3, "e53", 6);
		Edge e35 = new Edge(n3, n5, "e35", 7);
		Edge e17 = new Edge(n1, n7, "e17", 8);
		Edge e76 = new Edge(n7, n6, "e76", 7);
		
		g.addEdge(e12);
		g.addEdge(e23);
		g.addEdge(e34);
		g.addEdge(e25);
		g.addEdge(e53);
		g.addEdge(e35);
		g.addEdge(e56);
		g.addEdge(e64);
		g.addEdge(e17);
		g.addEdge(e76);
		
		return g;
	}
	
	private Graph buildDurations() {
		Graph g = new Graph();
		
		Node n1 = new Node(new Coordinate(0, 0), "1");
		Node n2 = new Node(new Coordinate(4, 2), "2");
		Node n3 = new Node(new Coordinate(14, 7), "3");
		Node n4 = new Node(new Coordinate(6, 10), "4");
		Node n5 = new Node(new Coordinate(4, 7), "5");
		Node n6 = new Node(new Coordinate(1, 10), "6");
		Node n7 = new Node(new Coordinate(-7, 5), "7");
				
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);

		Edge e12 = new Edge(n1, n2, "e12", 1, 100);
		Edge e25 = new Edge(n2, n5, "e25", 1, 100);
		Edge e56 = new Edge(n5, n6, "e56", 1, 100);
		Edge e64 = new Edge(n6, n4, "e64", 1, 100);

		Edge e23 = new Edge(n1, n3, "e23", 4, 10);
		Edge e34 = new Edge(n3, n4, "e34", 5, 30);
		Edge e53 = new Edge(n5, n3, "e53", 6, 30);
		Edge e35 = new Edge(n3, n5, "e35", 7, 50);
		Edge e17 = new Edge(n1, n7, "e17", 8, 60);
		Edge e76 = new Edge(n7, n6, "e76", 7, 90);
		
		g.addEdge(e12);
		g.addEdge(e23);
		g.addEdge(e34);
		g.addEdge(e25);
		g.addEdge(e53);
		g.addEdge(e35);
		g.addEdge(e56);
		g.addEdge(e64);
		g.addEdge(e17);
		g.addEdge(e76);
		
		return g;
	}

}
