package geom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * READ: I would use my own Iterator interface but then I would not be able to use Java's built in functionality
 * Iterator for the path a Creep will take
 * 
 * @author Stephen Bussey
 *
 */
public class PathIterator implements Iterator<Point>, Iterable<Point>
{
	ArrayList<Point> path;
	int currentPosition;

	/**
	 * Creates a new empty iterator
	 */
	public PathIterator()
	{
		path = new ArrayList<Point>();
		currentPosition = 0;
	}

	/**
	 * Creates an iterator with start as it's only value
	 * @param start
	 */
	public PathIterator(Point start)
	{
		this();
		addPoint(start);
	}

	/**
	 * add a point to this iterator
	 * @param p
	 */
	public void addPoint(Point p)
	{
		path.add(path.size(), p);
	}

	/**
	 * Get the next point and increase the position
	 */
	public Point next()
	{
		if(path.size() > currentPosition)
			return path.get(currentPosition++);
		return null;
	}

	/**
	 * Does not increase the position
	 * @return The next point
	 */
	public Point peek()
	{
		if(path.size() > currentPosition)
			return path.get(currentPosition);
		return null;
	}

	/**
	 * Checks if the iterator has more elements
	 */
	public boolean hasNext() {
		return path.size() > currentPosition;
	}

	/**
	 * remove the current point from this iterator
	 */
	public void remove() {
		if(path.size() > currentPosition)
			path.remove(currentPosition);
	}

	/**
	 * Set the iterator back to the beginning
	 */
	public void reset()
	{
		currentPosition = 0;
	}

	/**
	 * allows a for each loops
	 */
	public Iterator<Point> iterator() {
		return this;
	}
}
