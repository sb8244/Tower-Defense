package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import geom.RangePolygon;

import org.junit.Test;

/**
 * 
 * Tests RangePolygon
 * @author Stephen Bussey
 *
 */
public class TestRangePolygon 
{
	@Test
	public void testBasic()
	{
		RangePolygon r = new RangePolygon(new Point(100, 100), 50);
		assertTrue(r.contains(new Point(150, 100)));
		assertTrue(r.contains(new Point(100, 150)));
		assertTrue(r.contains(new Point(50, 100)));
		assertTrue(r.contains(new Point(100, 50)));
	}
	
	@Test
	public void testContains()
	{
		RangePolygon r = new RangePolygon(new Point(100, 100), 50);
		assertTrue(r.contains(new Point(100,100)));
		assertTrue(r.contains(new Point(150, 100)));
		assertTrue(r.contains(new Point(50, 100)));
		assertTrue(r.contains(new Point(100, 150)));
		assertTrue(r.contains(new Point(100, 50)));
		assertTrue(r.contains(new Point(99, 57)));
		
		assertFalse(r.contains(new Point(49, 100)));
		assertFalse(r.contains(new Point(151, 100)));
		assertFalse(r.contains(new Point(100, 49)));
		assertFalse(r.contains(new Point(100, 151)));
	}
}
