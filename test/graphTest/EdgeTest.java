package graphTest;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import graph.Coordinate;
import graph.Edge;
import graph.Node;

public class EdgeTest {

	@Test
	public void EdgeCreationAndParameterCheck()
	{
		Node n0 = new Node(new Coordinate(0,0), "Origo");
		Node n1 = new Node(new Coordinate(4,3), "Other");
		
		Edge e = new Edge(n0, n1, "Test");
		
		assertEquals("Test", e.getName());
		
		assertEquals(n0, e.getSource());
		assertEquals(n1, e.getDestination());
		
		assertEquals(5.0, e.getLength(), 0.01);
	}
	
	@Test
	public void EdgePropertyTrafficJamTest()
	{
		Node n0 = new Node(new Coordinate(0,0), "Origo");
		Node n1 = new Node(new Coordinate(4,3), "Other");
		
		Edge e = new Edge(n0, n1, "Test");
		assertFalse(e.hasTrafficJam());
		
		e.setTrafficJam(true);
		
		assertTrue(e.hasTrafficJam());
	}
	
	@Test
	public void EdgePropertyTimeLimitTest()
	{
		Node n0 = new Node(new Coordinate(0,0), "Origo");
		Node n1 = new Node(new Coordinate(4,3), "Other");
		Edge e = new Edge(n0, n1, "Test");
		
		assertEquals(50, e.getSpeedLimit()); // default
		
		e.setSpeedLimit(30);
		
		assertEquals(30, e.getSpeedLimit());
	}
	
	@Test
	public void EdgeTravelTimeTest()
	{
		Node n0 = new Node(new Coordinate(0,0), "Origo");
		Node n1 = new Node(new Coordinate(40,30), "Other");
		
		Edge e = new Edge(n0, n1, "Test");
		
		e.setSpeedLimit(25);
		
		assertEquals(2, e.getTravelTime(), 0.01);
	}
}
