
package graph;

public class Coordinate {
	private double x;
	private double y;
	
	public Coordinate(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void print()
	{
		System.out.println("(" + x + "," + y + ")");		
	}
}
