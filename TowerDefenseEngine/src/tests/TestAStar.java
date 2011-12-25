package tests;

import static org.junit.Assert.*;

import geom.AStar;
import geom.PathIterator;

import java.awt.Point;

import org.junit.Test;

/**
 * Tests A* algorithm
 * @author Stephen Bussey
 *
 */
public class TestAStar
{
	static boolean[][] simpleMaze = { {false, false, false, false, false, false, true},
									  {true, true, true, true, true, false, true},
									  {true, false, false, false, false, false, true},
									  {true, false, true, true, true, true, true},
									  {false, false, true, true, true, true, true}};
	
	static boolean[][] simpleMazeNoWalls = {
		{false, false, false, false},
		{false, false, false, false},
		{false, false, false, false},
		{false, false, false, false}
	};
	
	static boolean[][] simpleMazeNoPath = {
		{false, false, false ,false},
		{true, true, true, true},
		{false, false, false, false}
	};
	
	@Test
	public void testSimpleMaze()
	{
		AStar as = new AStar(simpleMaze, new Point(0, 0), new Point(4, 0));
		PathIterator pi = as.computePath();
		PathIterator expected = new PathIterator();
		expected.addPoint(new Point(0, 0));
		expected.addPoint(new Point(0, 1));
		expected.addPoint(new Point(0, 2));
		expected.addPoint(new Point(0, 3));
		expected.addPoint(new Point(0, 4));
		expected.addPoint(new Point(0, 5));
		expected.addPoint(new Point(1, 5));
		expected.addPoint(new Point(2, 5));
		expected.addPoint(new Point(2, 4));
		expected.addPoint(new Point(2, 3));
		expected.addPoint(new Point(2, 2));
		expected.addPoint(new Point(2, 1));
		expected.addPoint(new Point(3, 1));
		expected.addPoint(new Point(4, 1));
		expected.addPoint(new Point(4, 0));
			
		comparePathIterators(pi, expected);
	}
	@Test
	public void testSimpleMazeNoWalls()
	{
		AStar as = new AStar(simpleMazeNoWalls, new Point(0, 0), new Point(3, 3));
		PathIterator pi = as.computePath();
		PathIterator expected = new PathIterator();
		expected.addPoint(new Point(0, 0));
		expected.addPoint(new Point(1, 0));
		expected.addPoint(new Point(1, 1));
		expected.addPoint(new Point(1, 2));
		expected.addPoint(new Point(2, 2));
		expected.addPoint(new Point(2, 3));
		expected.addPoint(new Point(3, 3));
		
		comparePathIterators(expected, pi);
	}
	
	@Test
	public void testNoPath()
	{
		AStar as = new AStar(simpleMazeNoPath, new Point(0, 0), new Point(2, 3));
		assertEquals(null, as.computePath());	
	}
	
	/**
	 * allows 3 ms per 800 square maze
	 */
	@Test(timeout = 1000 * 3)
	public void testLargeMazeManyTimes()
	{
		boolean[][] emptySpace = new boolean[800][800];
		AStar as = new AStar(emptySpace, new Point(0, 0), new Point(799, 799));
		for(int i = 0; i < 1000; i++)
			as.computePath();
	}
	
	private void comparePathIterators(PathIterator pi, PathIterator expected)
	{
		while(pi.hasNext())
		{
			assertEquals(pi.next(), expected.next());
		}
		assertTrue(!pi.hasNext());
		assertTrue(!expected.hasNext());
	}
}
