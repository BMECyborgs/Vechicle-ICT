package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraOld {
	
	private static double actualLengthOfPath;
	private static ArrayList<Double> actualCostsOfEdges;
	private static ArrayList<Double> actualDurationsOfEdges;
	private static double actualDurationsOfPath;	
	
	public static double ActualLengthOfPath (Graph g, String source, String destination)
	{
		DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return actualLengthOfPath;
	}
	
	public static ArrayList<Double> ActualCostsOfEdges(Graph g, String source, String destination)
	{
		DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
		return actualCostsOfEdges;
	}
	
	public static ArrayList<Double> ActualDurationsOfEdges(Graph g, String source, String destination)
	{
		DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return actualDurationsOfEdges;
	}
	
	public static double ActualDurationsOfPath(Graph g, String source, String destination)
	{
		DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
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
		return DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
	}
	
	public static ArrayList<String> fastestPath(Graph g, String source, String destination) {
		return DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
	}
		
	public static ArrayList<String> cheapestPath(Graph g, String source, String destination) {
		return DijkstraOld.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
	}

	public static ArrayList<String> dijkstra(Graph g, Node source, Node destination, WeightType wt) 
	{
		
		HashMap<String, String> previousInPath = new HashMap<String, String>();
		
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
				if (g.getEdge(next, n).hasTrafficJam())
					continue;
				
				if (wt == WeightType.LENGTH) 
				{
					d += g.getEdge(next, n).getLength();
				}
				else if (wt == WeightType.COST) 
				{
					d += g.getEdge(next, n).getCost();
				} 
				else if (wt == WeightType.TIME) 
				{
					d += g.getEdge(next, n).getTravelTime();
				}

				if (dist.get(n) > d) 
				{
					// refresh distance of n
					dist.remove(n);
					dist.put(n, d);
					
					// refresh previous node of n
					if (previousInPath.containsKey(n))
						previousInPath.remove(n);
					previousInPath.put(n, next);
				}
			}
		}

		ArrayList<String> path = getPath(previousInPath, source.getName(), destination.getName());
		
		if (wt == WeightType.LENGTH) 
		{
			actualLengthOfPath = 0;
			for (int i = 0; i < path.size() - 1; i++) 
			{
				actualLengthOfPath += g.getEdge(path.get(i), path.get(i+1)).getLength();				
			}
		}
		else if (wt == WeightType.COST) 
		{
			actualCostsOfEdges = new ArrayList<Double> ();
			for (int i = 0; i < path.size() - 1; i++) 
			{
				actualCostsOfEdges.add(g.getEdge(path.get(i), path.get(i+1)).getCost());
			}
		} 
		else if (wt == WeightType.TIME) 
		{
			actualDurationsOfEdges = new ArrayList<Double> ();
			actualDurationsOfPath = 0;	
			for (int i = 0; i < path.size() - 1; i++) 
			{
				double time = g.getEdge(path.get(i), path.get(i+1)).getTravelTime();
				actualDurationsOfEdges.add(time);
				actualDurationsOfPath += time;
			}
		}				
		return path;
	}

	private static ArrayList<String> getPath(HashMap<String, String> previousInPath, String source, String destination) 
	{
		ArrayList<String> pathToDestination = new ArrayList<String>();
		
		if (previousInPath.containsKey(destination)) 
		{
			String current = destination;
			while (current != source) {
				pathToDestination.add(current);
				current = previousInPath.get(current);
			}
			pathToDestination.add(source);
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
