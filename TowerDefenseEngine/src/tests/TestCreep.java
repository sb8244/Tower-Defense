package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import engine.Creep;
import engine.RecoveryLinear;
import engine.RecoveryNone;
import geom.PathIterator;

/**
 * tests that Creep is functioning
 * @author Stephen Bussey
 *
 */
public class TestCreep 
{
	@Test
	public void testConstructor()
	{
		Creep c = new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone());
		assertEquals(1, c.getCurrentHealth());
		assertEquals(1, c.getMaxHealth());
		assertEquals(2, c.getArmor());
		assertEquals(3, c.getEworth());
		assertEquals(4, c.getGworth());
		assertEquals("TestCreep", c.getType());
		assertFalse(c.isDead());
		assertEquals(new Point(0,0), c.getLocation());
		assertEquals(new Point(0,0), c.getPreviousLocation());
	}
	
	@Test
	public void testSetLocation()
	{
		Creep c = new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone());
		c.setLocation(100, 50);
		assertEquals(new Point(0, 0), c.getPreviousLocation());
		assertEquals(new Point(100, 50), c.getLocation());
		Point p = new Point(200, 250);
		c.setLocation(p);
		assertEquals(new Point(100,50), c.getPreviousLocation());
		assertEquals(p, c.getLocation());
	}
	
	@Test
	public void testTakeHitNoArmor()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryNone());
		assertEquals(10, c.getCurrentHealth());
		c.takeHit(5);
		assertEquals(5, c.getCurrentHealth());
		c.takeHit(5);
		assertEquals(0, c.getCurrentHealth());
		c.takeHit(5);
		assertEquals(0, c.getCurrentHealth());
	}
	
	@Test
	public void testTakeHitArmor()
	{
		Creep c = new Creep(10, 5, 3, 4, "TestCreep", new RecoveryNone());
		assertEquals(10, c.getCurrentHealth());
		c.takeHit(10);
		assertEquals(5, c.getCurrentHealth());
		c.takeHit(10);
		assertEquals(0, c.getCurrentHealth());
		assertTrue(c.isDead());
	}
	
	@Test
	public void testTakeHitFullArmor()
	{
		Creep c = new Creep(10, 10, 3, 4, "TestCreep", new RecoveryNone());
		assertEquals(10, c.getCurrentHealth());
		c.takeHit(10);
		assertEquals(10, c.getCurrentHealth());
	}
	
	@Test
	public void testRecoveryNone()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryNone());
		c.takeHit(5);
		assertEquals(5, c.getCurrentHealth());
		c.recover();
		assertEquals(5, c.getCurrentHealth());
	}
	
	@Test
	public void testRecoveryLinearToMax()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		c.takeHit(5);
		assertEquals(5, c.getCurrentHealth());
		c.recover();
		assertEquals(10, c.getCurrentHealth());
	}
	
	@Test
	public void testRecoveryLinearOverMax()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		c.takeHit(4);
		assertEquals(6, c.getCurrentHealth());
		c.recover();
		assertEquals(10, c.getCurrentHealth());
	}
	
	@Test
	public void testRecoveryLinearMiddle()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		c.takeHit(6);
		assertEquals(4, c.getCurrentHealth());
		c.recover();
		assertEquals(9, c.getCurrentHealth());
	}
	
	@Test
	public void testRecoveryLinearFrom0()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		c.takeHit(10);
		assertEquals(0, c.getCurrentHealth());
		c.recover();
		assertEquals(0, c.getCurrentHealth());
	}
	
	/*
	 * Disabled for now because randomness added to movement 
	 */
	
	//@Test
	public void testMoving()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		c.addPointToPath(new Point(50, 50));
		c.addPointToPath(new Point(50, 100));
		assertEquals(new Point(0, 0), c.getLocation());
		c.move();
		assertEquals(new Point(0, 0), c.getLocation());
		assertEquals(new Point(0, 0), c.getPreviousLocation());
		c.move();
		assertEquals(new Point(50, 50), c.getLocation());
		assertEquals(new Point(0, 0), c.getPreviousLocation());
		c.move();
		assertEquals(new Point(50, 100), c.getLocation());
		assertEquals(new Point(50, 50), c.getPreviousLocation());
		c.move();
		assertEquals(null, c.getLocation());
		assertEquals(new Point(50, 100), c.getPreviousLocation());
	}
	
	//@Test
	public void testSetPath()
	{
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryLinear(5));
		PathIterator pi = new PathIterator(new Point(50, 50));
		pi.addPoint(new Point(50, 100));
		c.setPath(pi);
		c.move();
		assertEquals(new Point(50, 50), c.getLocation());
		assertEquals(new Point(0, 0), c.getPreviousLocation());
		c.move();
		assertEquals(new Point(50, 100), c.getLocation());
		assertEquals(new Point(50, 50), c.getPreviousLocation());
		c.move();
		assertEquals(null, c.getLocation());
		assertEquals(new Point(50, 100), c.getPreviousLocation());
	}
}
