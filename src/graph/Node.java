package graph;

import java.util.ArrayList;
import java.util.Map;

public class Node {
	
	private Coordinate position;
	private String name;
	private Map<String, Edge> startingEdges;
	
	public Node(Coordinate position, String name)
	{
		this.position = position;
		this.name = name;
	}
	
	public Coordinate getPosition()
	{
		return position;
	}
	public String getName()
	{
		return name;
	}
	
	public void print()
	{
		System.out.print("'"+name+"'");
		position.print();
	}
	
	public ArrayList<String> getNeighbours()
	{
		ArrayList<String> neighbours = new ArrayList<String>();
		
		for (Edge e : this.startingEdges.values())
		{
			neighbours.add(e.getDestination().getName());
		}
		
		return neighbours;
	}
}
