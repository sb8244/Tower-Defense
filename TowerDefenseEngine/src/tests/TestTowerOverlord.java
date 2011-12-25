package tests;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import engine.*;

/**
 * 
 * Tests TowerOverlord
 * @author Stephen Bussey
 *
 */
public class TestTowerOverlord
{
	@Test
	public void testBasic()
	{
		TimeOverlord lo = new TimeOverlord();
		TowerOverlord to = new TowerOverlord(lo);

		GameOverlord go = GameOverlord.getInstance();

		Creep c = new Creep(1, 0, 3, 4, "TestCreep", new RecoveryNone());
		Tower t = new Tower(10, 5, 1, new Point(10, 100), "test");

		c.setLocation(10, 10);
		t.setLocation(10, 10);

		to.addTower(t);
		go.addCreep(c);

		MockDeathObserver mo = new MockDeathObserver();
		to.addDeathObserver(mo);

		assertEquals(0, mo.count);
		lo.start();
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		lo.stop();
		assertEquals(1, mo.count);
	}

	@Test
	public void testKilling10OutOf25()
	{
		TimeOverlord lo = new TimeOverlord();
		TowerOverlord to = new TowerOverlord(lo);

		GameOverlord go = GameOverlord.getInstance();

		for(int i = 0; i < 25; i++)
		{
			Creep c = new Creep(1, 0, 3, 4, "TestCreep", new RecoveryNone());
			c.setLocation(10, 10);
			go.addCreep(c);
		}
		Tower t = new Tower(10, 5, 1, new Point(10, 100), "test");
		Tower t2 = new Tower(10, 5, 1, new Point(0, 0), "t2");
		t.setLocation(10, 10);
		t2.setLocation(10, 10);

		to.addTower(t2);
		to.addTower(t);
		to.removeTower(t2);

		MockDeathObserver mo = new MockDeathObserver();
		to.addDeathObserver(mo);

		assertEquals(0, mo.count);
		lo.start();
		try
		{
			Thread.sleep(1100);
			to.removeDeathObserver(mo);
			Thread.sleep(500);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		lo.stop();
		assertEquals(10, mo.count);
	}

	@Test
	public void TestTower5AttackSpeed()
	{
		TimeOverlord lo = new TimeOverlord();
		TowerOverlord to = new TowerOverlord(lo);

		GameOverlord go = GameOverlord.getInstance();

		for(int i = 0; i < 25; i++)
		{
			Creep c = new Creep(1, 0, 3, 4, "TestCreep", new RecoveryNone());
			c.setLocation(10, 10);
			go.addCreep(c);
		}
		Tower t = new Tower(10, 5, 5, new Point(10, 100), "test");
		t.setLocation(10, 10);

		to.addTower(t);

		MockDeathObserver mo = new MockDeathObserver();
		to.addDeathObserver(mo);

		assertEquals(0, mo.count);
		lo.start();
		try
		{
			Thread.sleep(1100);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		lo.stop();
		assertEquals(2, mo.count);
	}
	
	@Test
	public void Test2Tower5AttackSpeed()
	{
		TimeOverlord lo = new TimeOverlord();
		TowerOverlord to = new TowerOverlord(lo);

		GameOverlord go = GameOverlord.getInstance();

		for(int i = 0; i < 25; i++)
		{
			Creep c = new Creep(1, 0, 3, 4, "TestCreep", new RecoveryNone());
			c.setLocation(10, 10);
			go.addCreep(c);
		}
		Tower t = new Tower(10, 5, 5, new Point(10, 100), "test");
		Tower t2 = new Tower(10, 5, 5, new Point(10, 100), "test");
		t.setLocation(10, 10);
		t2.setLocation(10, 10);

		to.addTower(t);
		to.addTower(t2);
		
		MockDeathObserver mo = new MockDeathObserver();
		to.addDeathObserver(mo);

		assertEquals(0, mo.count);
		lo.start();
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		lo.stop();
		assertEquals(4, mo.count);
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
