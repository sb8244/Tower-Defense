package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Creep;
import factory.CreepFactory;

/**
 * Test the creep factory
 * @author Stephen Bussey
 *
 */
public class TestCreepFactory
{
	@Test
	public void testBasicCreep()
	{
		Creep c = CreepFactory.createCreep("Basic");
		assertEquals(30, c.getCurrentHealth());
		assertEquals(3, c.getEworth());
		assertEquals(5, c.getGworth());
		assertEquals(0, c.getArmor());
		assertEquals(30, c.getMaxHealth());
		assertEquals("Basic", c.getType());	
	}
	
	@Test
	public void testMediumCreep()
	{
		Creep c = CreepFactory.createCreep("Medium");
		assertEquals(100, c.getCurrentHealth());
		assertEquals(10, c.getEworth());
		assertEquals(10, c.getGworth());
		assertEquals(5, c.getArmor());
		assertEquals(100, c.getMaxHealth());
		assertEquals("Medium", c.getType());	
	}
	
	@Test
	public void testHardCreep()
	{
		Creep c = CreepFactory.createCreep("hard");
		assertEquals(150, c.getCurrentHealth());
		assertEquals(50, c.getEworth());
		assertEquals(30, c.getGworth());
		assertEquals(10, c.getArmor());
		assertEquals(150, c.getMaxHealth());
		assertEquals("Hard", c.getType());	
	}
	
	
}
