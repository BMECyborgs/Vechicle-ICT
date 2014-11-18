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
	
	public int getSize()
	{
		return edges.size();
	}
	
	public double getFullLength()
	{
		double length = 0;
		for (Edge edge : edges)
			length += edge.getLength();
		return length;
	}
	
	public ArrayList<Double> getSeparateLengths()
	{
		ArrayList<Double> lengths = new ArrayList<Double>();
		for (Edge edge : edges)
			lengths.add(edge.getLength());
		return lengths;
	}
	
	public double getFullDuration()
	{
		double time = 0;
		for (Edge edge : edges)
			time += edge.getTravelTime();
		return time;
	}
	
	public ArrayList<Double> getSeparateDurations()
	{
		ArrayList<Double> durations = new ArrayList<Double>();
		for (Edge edge : edges)
			durations.add(edge.getTravelTime());
		return durations;
	}
	
	public double getFullCost()
	{
		double cost = 0;
		for (Edge edge : edges)
			cost += edge.getCost();
		return cost;
	}
	
	public ArrayList<Double> getSeparateCosts()
	{
		ArrayList<Double> costs = new ArrayList<Double>();
		for (Edge edge : edges)
			costs.add(edge.getCost());
		return costs;
	}
	
	public ArrayList<String> getStringListOfNodes()
	{
		ArrayList<String> stringList = new ArrayList<String>();
		if (edges.isEmpty())
			return stringList;
		
		stringList.add(edges.get(0).getSource().getName());
		
		for (Edge edge : edges)
			stringList.add(edge.getDestination().getName());
		
		return stringList;
	}
	
}
