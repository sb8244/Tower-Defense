package geom;

import java.awt.Point;
import java.util.ArrayList;

import structure.TestableFibonacciHeap;

/**
 * Contains the A* algorithm for efficient paths
 * @author Stephen Bussey
 *
 */
public class AStar 
{
	private int[][] GFunction;
	Point start, end;
	private AStarNode[][] nodes;
	private int w, h;
	private boolean[][] points;

	/**
	 * Create a new AStar 
	 * @param points a boolean array containing whether the points at (r, c) is walkable or not
	 * @param start The starting point
	 * @param end The end point
	 */
	public AStar(boolean[][] points, Point start, Point end)
	{
		w = points.length;
		h = points[0].length;

		this.start = start;
		this.end = end;
		this.points = points;

		GFunction = new int[points.length][points[0].length];
		nodes = new AStarNode[points.length][points[0].length];

		//initialize the GFunction values
		for(int i = 0; i < points.length; i++)
		{
			for(int j = 0; j < points[i].length; j++)
			{
				if(!points[i][j])
					GFunction[i][j] = computeDistance(i, j, end.x, end.y);
				if(points[i][j])
					GFunction[i][j] = Integer.MAX_VALUE - 10000;
				nodes[i][j] = new AStarNode(new Point(i, j), GFunction[i][j]);
			}
		}
	}

	/**
	 * Convenience method to wrap an AStarNode into a FibonacciHeap node
	 * @param n an AStarNode
	 * @return The FibonacciHeap.Node with n as it's data
	 */
	private TestableFibonacciHeap.Node<AStarNode> convert(AStarNode n)
	{
		return new TestableFibonacciHeap.Node<AStarNode>(n.getWeight(), n);
	}

	/**
	 * Implements the A* algorithm
	 * @return a PathIterator containing a valid path or null if there is no valid path
	 */
	public PathIterator computePath()
	{
		PathIterator ret = new PathIterator();
		TestableFibonacciHeap<AStarNode> open = new TestableFibonacciHeap<AStarNode>();
		open.insert(convert(nodes[start.x][start.y]));

		while(!nodes[end.x][end.y].closed && !open.isEmpty())
		{
			AStarNode n = open.extractMin().data;
			n.closed = true;
			if(n == nodes[end.x][end.y]){
				break;
			}

			//calculate the current node's neighbors
			ArrayList<AStarNode> neighbors = new ArrayList<AStarNode>();
			int i = n.location.x, j = n.location.y;
			if(isValid(i-1, j))
				neighbors.add(nodes[i-1][j]);
			if(isValid(i+1, j))
				neighbors.add(nodes[i+1][j]);
			if(isValid(i, j-1))
				neighbors.add(nodes[i][j-1]);
			if(isValid(i, j+1))
				neighbors.add(nodes[i][j+1]);

			for(AStarNode neigh: neighbors)
			{
				if(neigh.open)
				{
					if(neigh.Gweight < n.Gweight)
					{
						neigh.parent = n;
						neigh.Fweight = neigh.parent.Fweight+1;
					}
				}
				else if(!neigh.closed)
				{
					neigh.closed = true;
					neigh.parent = n;
					neigh.Fweight = neigh.parent.Fweight+1;
					open.insert(convert(neigh));
				}
			}
		}

		//generate the path
		ArrayList<Point> reversePath = new ArrayList<Point>();
		AStarNode expanding = nodes[end.x][end.y];
		while(expanding.parent != null)
		{
			reversePath.add(expanding.location);
			expanding = expanding.parent;
		}
		if(expanding != nodes[start.x][start.y])
			return null;
		else
			reversePath.add(start);
		for(int i = reversePath.size()-1; i >= 0; i--)
		{
			ret.addPoint(reversePath.get(i));
		}
		return ret;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return true if i,j are in the range of points
	 */
	private boolean isValid(int i, int j)
	{
		return i >= 0 && i < w && j >= 0 && j < h && !points[i][j];
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return The distance from (x1,y1) -> (x2, y2)
	 */
	private int computeDistance(int x1, int y1, int x2, int y2)
	{
		return (int)Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}


}
