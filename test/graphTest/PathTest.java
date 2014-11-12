package graphTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;
import graph.Coordinate;
import graph.Edge;
import graph.Node;
import graph.Path;

import org.junit.Test;

public class PathTest
{
	@Test
	public void addEdgesToPathTest()
	{
		Path path = buildSimplePath();
		
		ArrayList<Edge> allEdges = path.getAllEdges();
		Assert.assertEquals("0-1", allEdges.get(0).getName());
		Assert.assertEquals("1-2", allEdges.get(1).getName());
	}
	
	@Test
	public void getFullLengthTest()
	{
		Path path = buildSimplePath();
		
		Assert.assertEquals(2, path.getFullLength(), 0.01);
	}
	
	@Test
	public void getFullDurationTest()
	{
		Path path = buildSimplePath();
		
		Assert.assertEquals(2.0/50, path.getFullDuration(), 0.01);
	}
	
	private Path buildSimplePath()
	{
		Node node0 = new Node(new Coordinate(0,0), "origo");
		Node node1 = new Node(new Coordinate(0,1), "Node1");
		Node node2 = new Node(new Coordinate(0,2), "Node2");
		
		Path path = new Path();
		path.addEdge(new Edge(node0, node1, "0-1"));
		path.addEdge(new Edge(node1, node2, "1-2"));
		
		return path;
		
	}
	
}
