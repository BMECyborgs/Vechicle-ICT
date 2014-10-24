import java.util.ArrayList;

import junit.framework.Assert;

import graph.Coordinate;
import graph.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.Node;


public class VechicleICT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Vechicle ICT project by Cyborgs");
		
		Coordinate c1 = new Coordinate(1, 9.2);
		c1.print();
		
		Node firstNode = new Node(c1, "Starting Node");
		firstNode.print();
		
		Node secondNode = new Node(new Coordinate(7, 4), "Other Node");
		secondNode.print();
		
		Edge firstEdge = new Edge(firstNode, secondNode, "MyWay");
		firstEdge.print();
		
		System.out.println("Length of " + firstEdge.getName() + ": " + firstEdge.getLength());
		
		System.out.println("--- Graph ---");
		
		Graph myCity = new Graph();
		
		// Locations
		Node gellert = new Node(new Coordinate(0, -12), "Szent Gellért Tér");
		Node moricz = new Node(new Coordinate(-2, -14), "Móricz");
		Node ujbuda = new Node(new Coordinate(-2, -16), "Újbuda Központ");
		Node kosztolanyi = new Node(new Coordinate(-4, -15), "Kosztolányi tér");
		

		myCity.addBidirectionalEdge(new Edge(gellert, moricz, "Bartók Béla út"));
		myCity.addBidirectionalEdge(new Edge(moricz, ujbuda, "Fehérvári út"));
		myCity.addBidirectionalEdge(new Edge(ujbuda, kosztolanyi, "Bocskai út"));
		myCity.addBidirectionalEdge(new Edge(moricz, kosztolanyi, "Bartók Béla út+"));
		myCity.print();
		

		ArrayList<String> path = Dijkstra.shortestPath(myCity, "Szent Gellért Tér", "Újbuda Központ");

		System.out.println("\r\n--Your shortest path:");
		for (String road : path)
		{
			System.out.println(road);
		}
		
	}

}
