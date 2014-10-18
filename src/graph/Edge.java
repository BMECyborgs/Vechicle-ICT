package graph;

public class Edge
{
	private Node source;
	private Node destination;
	private String name;
	
	public Edge(Node source, Node destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getLength()
	{
		return Math.sqrt(
				Math.pow(source.getPosition().x - destination.getPosition().x, 2) +
				Math.pow(source.getPosition().y - destination.getPosition().y, 2));
	}
	
	public void print()
	{
		System.out.println("Edge:" + name + " starts from ");
		source.print();
		System.out.println(" ahead to ");
		destination.print();
	}
	
}
