package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	public static ArrayList<String> shortestPath(Graph g, String source, String destination) {
		return Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.LENGTH);
	}
	
	public static ArrayList<String> cheapestPath(Graph g, String source, String destination) {
		return Dijkstra.dijkstra(g, g.getNode(source), g.getNode(destination), WeightType.COST);
	}

	public static ArrayList<String> dijkstra(Graph g, Node source, Node destination, WeightType wt) {

		Map<String, Double> dist = new HashMap<String, Double>();

		ArrayList<String> path = new ArrayList<String>();

		Set<String> visited = new HashSet<String>();

		for (String s : g.getNodes().keySet()) {
			dist.put(s, Double.MAX_VALUE);
		}
		dist.remove(source.getName());
		dist.put(source.getName(), 0.0);

		for (int i = 0; i < dist.size(); i++) {
			String next = minVertex(dist, visited);
			visited.add(next);

			ArrayList<String> neighbours = g.getNode(next).getNeighbours();
			for (String n : neighbours) {
				double d = dist.get(next);

				if (wt == WeightType.LENGTH) {
					d += g.getEdge(next, n).getLength();
				} else if (wt == WeightType.COST) {
					d += g.getEdge(next, n).getCost();
				}

				if (dist.get(n) > d) {

					dist.remove(n);
					dist.put(n, d);

					if (path.contains(n)) {
						path.remove(n);
					}
					path.add(n);
				}
			}
		}
		return path;
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
