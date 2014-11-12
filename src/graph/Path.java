package graph;

import java.util.ArrayList;

public class Path {
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public void addEdge(Edge edge)
	{
		edges.add(edge);
	}
	
	public ArrayList<Edge> getAllEdges()
	{
		return edges;
	}
	
	public double getFullLength()
	{
		double length = 0;
		for (Edge edge : edges)
			length += edge.getLength();
		return length;
	}
	
	public double getFullDuration()
	{
		double time = 0;
		for (Edge edge : edges)
			time += edge.getTravelTime();
		return time;
	}
	
}
