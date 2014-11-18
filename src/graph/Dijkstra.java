package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	public static double ActualLengthOfPath (Graph g, String source, String destination)
	{
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return path.getFullLength();
	}
	
	public static ArrayList<Double> ActualCostsOfEdges(Graph g, String source, String destination)
	{
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
		return path.getSeparateCosts();
	}
	
	public static ArrayList<Double> ActualDurationsOfEdges(Graph g, String source, String destination)
	{
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return path.getSeparateDurations();
	}
	
	public static double ActualDurationsOfPath(Graph g, String source, String destination)
	{
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return path.getFullDuration();
	}

/*	
	public static double shortestPathLength(Graph g, String source, String destination) 
	{
		Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return 1;
	}
*/	

	public static ArrayList<String> shortestPath(Graph g, String source, String destination) {
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
		return path.getStringListOfNodes();
	}
	
	public static ArrayList<String> fastestPath(Graph g, String source, String destination) {
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.TIME);
		return path.getStringListOfNodes();
	}
		
	public static ArrayList<String> cheapestPath(Graph g, String source, String destination) {
		Path path = Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
		return path.getStringListOfNodes();
	}

	public static Path dijkstra(Graph g, Node source, Node destination, WeightType wt) 
	{
		
		HashMap<String, Edge> previousInPath = new HashMap<String, Edge>();
		
		if (g == null || g.getNodes().size() == 0)
			return new Path();

		Map<String, Double> distanceMap = new HashMap<String, Double>();

		Set<String> visited = new HashSet<String>();

		for (String s : g.getNodes().keySet()) {
			distanceMap.put(s, Double.MAX_VALUE);
		}
		distanceMap.remove(source.getName());
		distanceMap.put(source.getName(), 0.0);

		for (int i = 0; i < distanceMap.size(); i++) 
		{
			String next = GetNearestNode(distanceMap, visited);
			visited.add(next);

			ArrayList<String> neighbours = g.getNode(next).getNeighbours();
			for (String node : neighbours) 
			{
				double distance = distanceMap.get(next);

				// avoid traffic jam
				Edge edge = g.getEdge(next, node);
				if (edge.hasTrafficJam())
					continue;
				switch (wt)
				{
					case LENGTH: 
						distance += edge.getLength();
						break;
					case COST:
						distance += edge.getCost();
						break;
					case TIME: 
						distance += edge.getTravelTime();
						break;
					default:
						break;
				}
				
				if (distanceMap.get(node) > distance) 
				{
					// refresh distance of n
					distanceMap.remove(node);
					distanceMap.put(node, distance);
					
					// refresh previous node of n
					if (previousInPath.containsKey(node))
						previousInPath.remove(node);
					previousInPath.put(node, edge);
				}
			}
		}

		ArrayList<Edge> edgesOfPath = getPath(previousInPath, source.getName(), destination.getName());
		Path path = new Path();
		for (Edge edge : edgesOfPath)
			path.addEdge(edge);
		
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

	private static String GetNearestNode(Map<String, Double> nodes, Set<String> visitedNodes) {
		double distance = Double.MAX_VALUE;
		String nearestNode = null;
		for (String nodeName : nodes.keySet()) {
			if (!visitedNodes.contains(nodeName) && nodes.get(nodeName) < distance) {
				nearestNode = nodeName;
				distance = nodes.get(nodeName);
			}
		}

		return nearestNode;
	}
}
