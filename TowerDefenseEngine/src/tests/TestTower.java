package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import engine.Creep;
import engine.RecoveryNone;
import engine.Tower;

/**
 * 
 * Tests Tower
 * @author Stephen Bussey
 *
 */
public class TestTower
{
	@Test
	public void testConstructor()
	{
		Tower t = new Tower(10, 5, 10, new Point(10, 100), "test");
		assertEquals(10, t.getDamage());
		assertEquals(new Point(10, 100), t.getLocation());
		assertEquals(0, t.getExp());
		assertEquals(100, t.getExpToLevel());
		assertEquals(1, t.getLevel());
		assertEquals("test", t.getType());
		t.setLocation(100, 100);
		assertEquals(new Point(100,100), t.getLocation());
		assertEquals(10, t.getRateOfFire());
		assertTrue(t.canAttack());
	}

	@Test
	public void testAddExp()
	{
		Tower t = new Tower(10, 5,10, new Point(10, 100), "test");
		t.addExp(99);
		assertEquals(1, t.getLevel());
		assertEquals(99, t.getExp());
		t.addExp(1);
		assertEquals(2, t.getLevel());
		assertEquals(0, t.getExp());
		assertEquals(200, t.getExpToLevel());
		t.addExp(200);
		assertEquals(3, t.getLevel());
		assertEquals(0, t.getExp());
		assertEquals(300, t.getExpToLevel());
		t.addExp(300);
		assertEquals(4, t.getLevel());
		assertEquals(400, t.getExpToLevel());
		t.addExp(400);
		assertEquals(5, t.getLevel());
		assertEquals(0, t.getExp());
		t.addExp(500);
		assertEquals(5, t.getLevel());
		assertEquals(0, t.getExp());
	}
	
	@Test
	public void testPointInRange()
	{
		Tower t = new Tower(10, 50,10, new Point(100, 100), "test");
		assertTrue(t.pointInRange(new Point(100,100)));
		assertTrue(t.pointInRange(new Point(150, 100)));
		assertTrue(t.pointInRange(new Point(50, 100)));
		assertTrue(t.pointInRange(new Point(100, 150)));
		assertTrue(t.pointInRange(new Point(100, 50)));
		
		assertFalse(t.pointInRange(new Point(49, 100)));
		assertFalse(t.pointInRange(new Point(151, 100)));
		assertFalse(t.pointInRange(new Point(100, 49)));
		assertFalse(t.pointInRange(new Point(100, 151)));
	}
	
	@Test
	public void testCreepInRange()
	{
		Tower t = new Tower(10, 50, 10, new Point(100, 100), "test");
		Creep c = new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone());
		c.setLocation(100, 100);
		assertTrue(t.creepInRange(c));
		c.setLocation(150, 100);
		assertTrue(t.creepInRange(c));
		c.setLocation(50, 100);
		assertTrue(t.creepInRange(c));
		c.setLocation(100, 50);
		assertTrue(t.creepInRange(c));
		c.setLocation(100, 150);
		assertTrue(t.creepInRange(c));
		
		c.setLocation(151, 100);
		assertFalse(t.creepInRange(c));
		c.setLocation(49, 100);
		assertFalse(t.creepInRange(c));
		c.setLocation(100, 151);
		assertFalse(t.creepInRange(c));
		c.setLocation(100, 49);
		assertFalse(t.creepInRange(c));
	}
	
	@Test
	public void testAttackCreepNoArmor()
	{
		Tower t = new Tower(5, 50, 10, new Point(100, 100), "test");
		Creep c = new Creep(10, 0, 3, 4, "TestCreep", new RecoveryNone());
		assertEquals(10, c.getCurrentHealth());
		t.attackCreep(c);
		assertEquals(5, c.getCurrentHealth());
		t.attackCreep(c);
		assertEquals(0, c.getCurrentHealth());
		t.attackCreep(c);
		assertEquals(0, c.getCurrentHealth());
	}
	
	@Test
	public void testAttackCreepArmor()
	{
		Tower t = new Tower(10, 50, 10, new Point(100, 100), "test");
		Creep c = new Creep(10, 2, 3, 4, "TestCreep", new RecoveryNone());
		t.attackCreep(c);
		assertEquals(2, c.getCurrentHealth());
		t.attackCreep(c);
		assertEquals(0, c.getCurrentHealth());
	}
	
	@Test
	public void testAttackMoreArmorThanDamage()
	{
		Tower t = new Tower(1, 50, 10, new Point(100, 100), "test");
		Creep c = new Creep(10, 2, 3, 4, "TestCreep", new RecoveryNone());
		t.attackCreep(c);
		assertEquals(10, c.getCurrentHealth());
		c.takeHit(5);
		assertEquals(7, c.getCurrentHealth());
		t.attackCreep(c);
		assertEquals(7, c.getCurrentHealth());
	}
	
	@Test
	public void testRateOfFire1()
	{
		Tower t = new Tower(10, 5, 1, new Point(10, 100), "test");
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
		Tower t = new Tower(10, 5, 2, new Point(10, 100), "test");
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
		Tower t = new Tower(10, 5, 3, new Point(10, 100), "test");
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

