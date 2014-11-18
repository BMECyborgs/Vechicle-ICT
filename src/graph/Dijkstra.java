package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	
	private static double actualLengthOfPath;
	private static ArrayList<Double> actualCostsOfEdges;
	private static ArrayList<Double> actualDurationsOfEdges;
	private static double actualDurationsOfPath;	
	
	public static double ActualLengthOfPath (Graph g, String source, String destination)
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return actualLengthOfPath;
	}
	
	public static ArrayList<Double> ActualCostsOfEdges(Graph g, String source, String destination)
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
		return actualCostsOfEdges;
	}
	
	public static ArrayList<Double> ActualDurationsOfEdges(Graph g, String source, String destination)
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return actualDurationsOfEdges;
	}
	
	public static double ActualDurationsOfPath(Graph g, String source, String destination)
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return actualDurationsOfPath;
	}

/*	
	public static double shortestPathLength(Graph g, String source, String destination) 
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return 1;
	}
*/	

	public static ArrayList<String> shortestPath(Graph g, String source, String destination) {
		return Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
	}
	
	public static ArrayList<String> fastestPath(Graph g, String source, String destination) {
		return Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
	}
		
	public static ArrayList<String> cheapestPath(Graph g, String source, String destination) {
		return Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
	}

	public static ArrayList<String> dijkstra(Graph g, Node source, Node destination, WeightType wt) 
	{
		
		HashMap<String, Edge> previousInPath = new HashMap<String, Edge>();
		
		if (g == null || g.getNodes().size() == 0) return new ArrayList<String>();

		Map<String, Double> dist = new HashMap<String, Double>();

		Set<String> visited = new HashSet<String>();

		for (String s : g.getNodes().keySet()) {
			dist.put(s, Double.MAX_VALUE);
		}
		dist.remove(source.getName());
		dist.put(source.getName(), 0.0);

		for (int i = 0; i < dist.size(); i++) 
		{
			String next = minVertex(dist, visited);
			visited.add(next);

			ArrayList<String> neighbours = g.getNode(next).getNeighbours();
			for (String n : neighbours) 
			{
				double d = dist.get(next);

				// avoid traffic jam
				Edge edge = g.getEdge(next, n);
				if (edge.hasTrafficJam())
					continue;
				
				if (wt == WeightType.LENGTH) 
				{
					d += edge.getLength();
				}
				else if (wt == WeightType.COST) 
				{
					d += edge.getCost();
				} 
				else if (wt == WeightType.TIME) 
				{
					d += edge.getTravelTime();
				}

				if (dist.get(n) > d) 
				{
					// refresh distance of n
					dist.remove(n);
					dist.put(n, d);
					
					// refresh previous node of n
					if (previousInPath.containsKey(n))
						previousInPath.remove(n);
					previousInPath.put(n, edge);
				}
			}
		}

		ArrayList<Edge> edgesOfPath = getPath(previousInPath, source.getName(), destination.getName());
		
		actualLengthOfPath = 0;
		actualCostsOfEdges = new ArrayList<Double> ();
		actualDurationsOfEdges = new ArrayList<Double>();
		actualDurationsOfPath = 0;
		ArrayList<String> path = new ArrayList<String>();
		
		for (Edge edge : edgesOfPath) {
			
			if (wt == WeightType.LENGTH) {
				
				actualLengthOfPath += edge.getLength();	
				
			} else if (wt == WeightType.COST) {
				
				actualCostsOfEdges.add(edge.getCost());
				
			} else if (wt == WeightType.TIME) {
				
				double time = edge.getTravelTime();
				actualDurationsOfEdges.add(time);
				actualDurationsOfPath += time;
				
			}
			
			//Add source to path
			path.add(edge.getSource().getName());
			
			//Add destination to path if it was the last edge
			if (edgesOfPath.indexOf(edge) + 1 == edgesOfPath.size()) {
				path.add(edge.getDestination().getName());
			}
				
		}
					
		return path;
	}

	private static ArrayList<Edge> getPath(HashMap<String, Edge> previousInPath, String source, String destination) 
	{
		ArrayList<Edge> pathToDestination = new ArrayList<Edge>();
		
		if (previousInPath.containsKey(destination)) 
		{
			String current = destination;
			while (current != source) {
				pathToDestination.add(previousInPath.get(current));
				current = previousInPath.get(current).getSource().getName();
			}
		}
		Collections.reverse(pathToDestination);
			
		return pathToDestination;
	}

	private static String minVertex(Map<String, Double> dist, Set<String> visited) {
		double x = Double.MAX_VALUE;
		String nearest = null;
		for (String s : dist.keySet()) {
			if (!visited.contains(s) && dist.get(s) < x) {
				nearest = s;
				x = dist.get(s);
			}
		}

		return nearest;
	}
}
