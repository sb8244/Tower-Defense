package geom;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Contains information needed for A*
 * @author Stephen Bussey
 *
 */
public class AStarNode
{
	Point location;
	int Gweight;
	int Fweight;
	AStarNode parent;
	boolean open, closed;
	ArrayList<AStarNode> neighbors;

	/**
	 * creates a new node 
	 * @param location The location this Node holds
	 * @param Gweight The GFunction value of this node
	 */
	public AStarNode(Point location, int Gweight)
	{
		this.location = location;
		this.Gweight = Gweight;
		this.Fweight = 0;
		neighbors = new ArrayList<AStarNode>();
	}

	/**
	 * 
	 * @return The sum of the GFunction value and the Heuristic value
	 */
	public int getWeight()
	{
		return Gweight + Fweight;
	}
	/**
	 * return the summed weight as a String (for debug purposes)
	 */
	public String toString()
	{
		return getWeight()+"";
	}
}

