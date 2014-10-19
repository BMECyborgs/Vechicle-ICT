package graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {
	private Map<String, Node> nodes;
	private Map<String, Edge> edges;
	
	public Graph()
	{
		this.nodes = new HashMap<String, Node>();
		this.edges = new HashMap<String, Edge>();
	}
	
	public static Graph CreateTestGraph()
	{
		Graph g = new Graph();
		
		
		
		return g;
	}
	
	public static Graph CreateRandomGraph()
	{
		return null;
	}
	
	public void addNode(Node newNode)
	{
		this.nodes.put(newNode.getName(), newNode);
	}
	
	public Node getNode(String name)
	{
		return this.nodes.get(name);
	}
	
	public void addEdge(Edge newEdge)
	{
		if (!this.nodes.containsValue(newEdge.getSource()))
			this.nodes.put(newEdge.getName(), newEdge.getSource());
		if (!this.nodes.containsValue(newEdge.getDestination()))
			this.nodes.put(newEdge.getName(), newEdge.getDestination());
		
		if (this.edges.containsValue(newEdge))
			System.out.println("Edge is already contained!");
		
		this.edges.put(newEdge.getName(), newEdge);
	}

	public void addBidirectionalEdge(Edge newEdge)
	{
		if (!nodes.containsValue(newEdge.getSource()))
			nodes.put(newEdge.getName(), newEdge.getSource());
		if (!nodes.containsValue(newEdge.getDestination()))
			nodes.put(newEdge.getName(), newEdge.getDestination());
		
		edges.put(newEdge.getName(), newEdge);
		edges.put(newEdge.getName(),
                new Edge(newEdge.getDestination(), newEdge.getSource(), newEdge.getName()));
	}
	public Edge getEdge(String name)
	{
		return this.edges.get(name);
	}
	
	public Map<String, Node> getNodes()
	{
		return this.nodes;
	}
	
	public Edge getEdge(String source, String destination)
	{
		Edge e = null;
		
		for (Edge edge : this.edges.values()) {
			if (edge.getSource().getName() == source &&
					edge.getDestination().getName() == destination) {
				e = edge;
				break;
			}
		}
		
		return e;
	}

	public void print()
	{
		System.out.println("#nodes:" + this.nodes.size() + " #edges:"+ this.edges.size());
		for (Edge oneEdge : this.edges.values())
		{
			oneEdge.print();
			System.out.println("");
		}
	}
}
