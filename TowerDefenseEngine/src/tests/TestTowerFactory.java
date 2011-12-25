package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import engine.Tower;
import factory.TowerFactory;

/**
 * 
 * Tests TowerFactory
 * @author Stephen Bussey
 *
 */
public class TestTowerFactory 
{
	@Test
	public void testChogath()
	{
		Tower t = TowerFactory.createTower("Chogath", new Point(0, 0));
		assertEquals(25, t.getDamage());
		assertEquals(new Point(0,0), t.getLocation());
		assertEquals("Chogath", t.getType());
		assertEquals(100, t.getRange());
		assertEquals(10, t.getRateOfFire());
	}
	
	@Test
	public void testEzreal()
	{
		Tower t = TowerFactory.createTower("Ezreal", new Point(0, 0));
		assertEquals(75, t.getDamage());
		assertEquals(new Point(0,0), t.getLocation());
		assertEquals("Ezreal", t.getType());	
		assertEquals(200, t.getRange());
		assertEquals(6, t.getRateOfFire());
	}
}
