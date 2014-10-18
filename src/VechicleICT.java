import graph.Coordinate;
import graph.Edge;
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
		
	}

}
