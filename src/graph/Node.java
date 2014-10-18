package graph;

public class Node {
	private Coordinate position;
	private String name;
	
	public Node(Coordinate position, String name)
	{
		this.position = position;
		this.name = name;
	}
	
	public Coordinate getPosition()
	{
		return position;
	}
	public String getName()
	{
		return name;
	}
	
	public void print()
	{
		System.out.print(name);
		position.print();
	}
}
