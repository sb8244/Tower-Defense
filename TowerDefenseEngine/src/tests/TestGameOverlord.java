package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;
import engine.*;
import factory.CreepFactory;
import geom.PathIterator;

/**
 * 
 * Tests GameOverlord
 * @author Stephen Bussey
 *
 */
public class TestGameOverlord 
{
	
	@Test
	public void testSingleton()
	{
		assertNotNull(GameOverlord.getInstance());
		GameOverlord.reset();
		assertNotNull(GameOverlord.getInstance());
		
		GameOverlord go = GameOverlord.getInstance();
		GameOverlord go2 = GameOverlord.getInstance();
		
		assertEquals(go, go2);
		
		GameOverlord.reset();
		go = GameOverlord.getInstance();
		
		assertTrue(go != go2);
	}
	@Test
	public void testGetSet()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		assertEquals(1000, go.getGold());
		assertEquals(50, go.getLives());
		
		go.setGold(2000);
		assertEquals(2000, go.getGold());
		
		Creep c = new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone());
		go.addCreep(c);
		assertEquals(1, go.getCreeps().size());
		assertEquals(c, go.getCreeps().get(0));
		go.removeCreep(c);
		assertEquals(0, go.getCreeps().size());
		
		Tower t = new Tower(10, 100, 1, new Point(10, 10), "test");
		go.addTower(t);
		assertEquals(1, go.getTowers().size());
		assertEquals(t, go.getTowers().get(0));
		go.removeTower(t);
		assertEquals(0, go.getTowers().size());
	}
	
	@Test
	public void testSetAllCreepsPath()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		assertEquals(1000, go.getGold());
		assertEquals(50, go.getLives());
		for(int i = 0; i < 25; i++)
			go.addCreep(new Creep(1, 2, 3, 5, "TestCreep", new RecoveryNone()));
		
		PathIterator pi = new PathIterator();
		for(int i = 0; i < 1000; i++)
		{
			pi.addPoint(new Point(i, i));
		}
		
		go.setAllCreepPath(pi);
		
		go.startLevel();
		try
		{
			Thread.sleep(100 * 10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.endLevel();
		
		assertEquals(50, go.getLives());
	}
	
	@Test
	public void testMinionsMoveToEnd()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		
		ArrayList<Creep> creeps = new ArrayList<Creep>(25);
		for(int i = 0; i < 25; i++)
			creeps.add(new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone()));

		PathIterator pi = new PathIterator();
		for(int i = 1; i <= 100; i++)
		{
			pi.addPoint(new Point(0, i));
		}

		for(Creep c: creeps)
		{
			c.setPath(pi);
			go.addCreep(c);
		}
		
		go.startLevel();
		try
		{
			Thread.sleep(100 * 103);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.endLevel();
		
		assertEquals(25, go.getLives());
		assertEquals(1000, go.getGold());
	}
	
	@Test
	public void testTowersKill25Minions()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		assertEquals(1000, go.getGold());
		assertEquals(50, go.getLives());
		
		ArrayList<Creep> creeps = new ArrayList<Creep>(25);
		for(int i = 0; i < 25; i++)
			creeps.add(new Creep(1, 2, 3, 5, "TestCreep", new RecoveryNone()));

		PathIterator pi = new PathIterator();
		for(int i = 0; i < 100; i++)
		{
			pi.addPoint(new Point(0, 0));
			pi.addPoint(new Point(1, 0));
		}
		
		for(Creep c: creeps)
		{
			c.setPath(pi);
			go.addCreep(c);
		}
		
		Tower t = new Tower(10, 100, 1, new Point(10, 10), "test");
		go.addTower(t);
		go.setTimeInterval(5);
		go.startLevel(creeps.size(), null);
		try
		{
			Thread.sleep(1100);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.endLevel();
		
		assertEquals(0, go.getRemainingCreeps());
		assertEquals(50, go.getLives());
		assertEquals(1000+(5*25), go.getGold());
	}
	
	@Test
	public void testSetTimeInterval()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		go.setTimeInterval(1000);
		MockDeathObserver mdo = new MockDeathObserver();
		go.addTimeObserver(mdo);
		
		go.startLevel();
		try
		{
			Thread.sleep(1100);
			go.removeTimeObserver(mdo);
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.endLevel();
		
		assertEquals(1, mdo.count);
	}
	
	@Test
	public void testAlternativeStartLevel()
	{
		GameOverlord.reset();
		GameOverlord go = GameOverlord.getInstance();
		MockDeathObserver mdo = new MockDeathObserver();
		for(int i = 0; i < 10; i++)
			go.addCreep(CreepFactory.createCreep("basic"));
		go.startLevel(10, mdo);
		try
		{
			Thread.sleep(1100);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		assertEquals(0, go.getRemainingCreeps());
		assertEquals(1, mdo.count);
		
	}
	class MockDeathObserver implements Observable
	{
		int count = 0;
		public void update(Object o)
		{
			count++;
		}
	}
}
