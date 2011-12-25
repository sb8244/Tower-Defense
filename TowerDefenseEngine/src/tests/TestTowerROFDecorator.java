package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import engine.Tower;
import engine.TowerROFDecorator;

/**
 * 
 * Tests TowerROFDecorator
 * @author Stephen Bussey
 *
 */
public class TestTowerROFDecorator
{
	@Test
	public void testBasic()
	{
		Tower t = new Tower(10, 5, 10, new Point(10, 100), "test");
		assertEquals(10, t.getRateOfFire());
		t= new TowerROFDecorator(5, t);
		assertEquals(5, t.getRateOfFire());
		t = new TowerROFDecorator(4, t);
		assertEquals(1, t.getRateOfFire());
	}
	@Test
	public void testStopsAt1()
	{
		Tower t = new Tower(10, 5, 10, new Point(10, 100), "test");
		assertEquals(10, t.getRateOfFire());
		t= new TowerROFDecorator(5, t);
		assertEquals(5, t.getRateOfFire());
		t = new TowerROFDecorator(4, t);
		assertEquals(1, t.getRateOfFire());
		t = new TowerROFDecorator(100, t);
		assertEquals(1, t.getRateOfFire());
	}
	
	@Test
	public void testRateOfFire1()
	{
		Tower t = new Tower(10, 5, 10, new Point(10, 100), "test");
		t = new TowerROFDecorator(9, t);
		assertTrue(t.canAttack());
		t.attackCreep(null);
		t.updateAttackCounter();
		assertTrue(t.canAttack());
		t.attackCreep(null);
		t.updateAttackCounter();
		assertTrue(t.canAttack());
	}
	

	@Test
	public void testRateOfFire2()
	{
		Tower t = new Tower(10, 5, 4, new Point(10, 100), "test");
		t = new TowerROFDecorator(2, t);
		assertTrue(t.canAttack());
		t.updateAttackCounter();
		assertFalse(t.canAttack());
		t.updateAttackCounter();
		assertTrue(t.canAttack());
		t.updateAttackCounter();
		assertFalse(t.canAttack());
		t.updateAttackCounter();
		assertTrue(t.canAttack());
	}
	
	@Test
	public void testRateOfFire3()
	{
		Tower t = new Tower(10, 5, 6, new Point(10, 100), "test");
		assertTrue(t.canAttack());
		t.updateAttackCounter();
		assertFalse(t.canAttack());
		t.updateAttackCounter();
		t = new TowerROFDecorator(3, t);
		assertFalse(t.canAttack());
		t.updateAttackCounter();
		assertTrue(t.canAttack());
		for(int i = 0; i < 100; i++)
		{
			assertTrue(t.canAttack());
			t.updateAttackCounter();
			assertFalse(t.canAttack());
			t.updateAttackCounter();
			assertFalse(t.canAttack());
			t.updateAttackCounter();
			assertTrue(t.canAttack());
		}
	}
}
