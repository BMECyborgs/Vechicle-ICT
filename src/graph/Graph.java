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
	
	public Node getNode(String name)
	{
		return this.nodes.get(name);
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
}
