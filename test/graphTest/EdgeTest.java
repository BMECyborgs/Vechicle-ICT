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
		
		Assert.assertEquals("Test", e.getName());
		
		Assert.assertEquals(n0, e.getSource());
		Assert.assertEquals(n1, e.getDestination());
		
		Assert.assertEquals(5.0, e.getLength(), 0.01);
	}
}
