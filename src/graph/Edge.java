package graph;

public class Edge
{
	private Node source;
	private Node destination;
	private String name;
	
	// Routing properties
	private boolean trafficJam = false;
	private int speedLimit = 50;
	private double cost = 0;
	
	public Edge(Node source, Node destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.name = name;
	}
	
	public Edge(Node source, Node destination, String name, double cost)
	{
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.cost = cost;
	}
	
	public Edge(Node source, Node destination, String name, double cost, int speedLimit)
	{
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.cost = cost;
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
	
	public Node getSource()
	{
		return this.source;
	}
	
	public Node getDestination()
	{
		return this.destination;
	}
	
	public boolean equals(Edge other)
	{
		System.out.println(name + "==" + other.getName());
		if (this.name != other.getName())
			return false;
		if (!this.getSource().equals( other.getSource() ))
			return false;
		if (!this.getDestination().equals( other.getDestination() ))
			return false;
		return true;
	}
	
	public int hashCode()
	{
		return Integer.parseInt(name);
	}
	
	public void print()
	{
		System.out.print("'" + name + "' starts from ");
		source.print();
		System.out.print(" ahead to ");
		destination.print();
	}

	public double getCost() {
		return this.cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setTrafficJam(boolean jam)
	{
		this.trafficJam = jam;
	}
	
	public boolean hasTrafficJam()
	{
		return this.trafficJam;
	}
	
	public int getSpeedLimit()
	{
		return this.speedLimit;
	}

	public void setSpeedLimit(int limit)
	{
		this.speedLimit = limit;
	}

	public double getTravelTime() {
		return this.getLength()/this.speedLimit;
	}
	
}
