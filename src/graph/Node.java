package graph;

import java.util.ArrayList;

public class Node {
	
	private Coordinate position;
	private String name;
	private ArrayList<Edge> startingEdges;
	
	public Node(Coordinate position, String name)
	{
		this.position = position;
		this.name = name;
		this.startingEdges = new ArrayList<Edge>();
	}
	
	public void addStartingEdge(Edge newEdge)
	{
		if (!this.startingEdges.contains(newEdge))
			this.startingEdges.add(newEdge);
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
		
		for (Edge e : this.startingEdges)
		{
			neighbours.add(e.getDestination().getName());
		}
		
		return neighbours;
	}
}
