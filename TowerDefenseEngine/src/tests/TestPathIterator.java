package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import geom.PathIterator;

/**
 * 
 * Tests PathIterator
 * @author Stephen Bussey
 *
 */
public class TestPathIterator 
{
	@Test
	public void testConstructor()
	{
		PathIterator pi = new PathIterator(new Point(50, 50));
		assertEquals(new Point(50, 50), pi.peek());
		assertEquals(new Point(50, 50), pi.next());
		assertEquals(null, pi.next());
	}
	
	@Test
	public void testAddingIterating()
	{
		PathIterator pi = new PathIterator(new Point(50, 50));
		pi.addPoint(new Point(51, 51));
		pi.addPoint(new Point(51,52));
		pi.addPoint(new Point(51, 53));
		assertEquals(new Point(50, 50), pi.next());
		assertEquals(new Point(51, 51), pi.next());
		assertEquals(new Point(51, 52), pi.next());
		assertEquals(new Point(51, 53), pi.next());
		assertEquals(null, pi.next());
		assertEquals(null, pi.next());
	}
	
	@Test
	public void testRemoving()
	{
		PathIterator pi = new PathIterator(new Point(50, 50));
		pi.addPoint(new Point(51, 51));
		pi.addPoint(new Point(51,52));
		pi.addPoint(new Point(51, 53));
		assertEquals(new Point(50, 50), pi.next());
		pi.remove();
		assertEquals(new Point(51, 52), pi.next());
		pi.remove();
		assertEquals(null, pi.next());
		pi.remove();
		assertEquals(null, pi.peek());
	}
	
	@Test
	public void testIteratingReset()
	{
		PathIterator pi = new PathIterator(new Point(50, 50));
		pi.addPoint(new Point(51, 51));
		pi.addPoint(new Point(51,52));
		pi.addPoint(new Point(51, 53));
		int count = 0;
		while(pi.hasNext())
		{
			count++;
			pi.next();
		}
		assertEquals(4, count);
		
		pi.reset();
		while(pi.hasNext())
		{
			count++;
			pi.next();
		}
		assertEquals(8, count);
	}
}
