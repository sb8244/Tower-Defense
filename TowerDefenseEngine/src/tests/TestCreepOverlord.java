package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import engine.Creep;
import engine.CreepOverlord;
import engine.TimeOverlord;
import engine.Observable;
import engine.RecoveryNone;
import geom.PathIterator;

/**
 * Test the CreepOverlord
 * @author Stephen Bussey
 *
 */
public class TestCreepOverlord 
{
	@Test
	public void firstTest()
	{
		TimeOverlord go = new TimeOverlord();
		CreepOverlord co = new CreepOverlord(go);
		Creep c = new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone());
		PathIterator pi = new PathIterator(c.getLocation());
		for(int i = 1; i <= 10; i++)
			pi.addPoint( new Point(0, i));
		c.setPath(pi);
		co.addCreep(c);
		go.start();
		try
		{
			Thread.sleep(100 * 13);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
	}

	@Test
	public void Test25CreepsHitNexus()
	{
		TimeOverlord go = new TimeOverlord();
		CreepOverlord co = new CreepOverlord(go);
		ArrayList<Creep> creeps = new ArrayList<Creep>();
		
		MockGameOverlord mgo = new MockGameOverlord();
		co.addObserver(mgo);
		
		for(int i = 0; i < 25; i++)
			creeps.add(new Creep(1, 2, 3, 4, "TestCreep", new RecoveryNone()));

		PathIterator pi = new PathIterator();
		for(int i = 1; i <= 10; i++)
		{
			pi.addPoint(new Point(0, i));
		}

		for(Creep c: creeps)
		{
			c.setPath(pi);
			co.addCreep(c);
		}
		
		assertEquals(0, mgo.count);
		
		go.start();
		try
		{
			Thread.sleep(100 * 13);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
		
		assertEquals(25, mgo.count);
	}
	
	@Test
	public void Test20CreepsHitNexusLongPath()
	{
		TimeOverlord go = new TimeOverlord();
		CreepOverlord co = new CreepOverlord(go);
		ArrayList<Creep> creeps = new ArrayList<Creep>();
		
		MockGameOverlord mgo = new MockGameOverlord();
		co.addObserver(mgo);
		
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
			co.addCreep(c);
		}
		
		for(int i = 0; i < 5; i++)
		{
			co.removeCreep(creeps.get(i));
		}
		
		assertEquals(0, mgo.count);
		
		go.start();
		try
		{
			Thread.sleep(100 * 103);
			co.removeObserver(mgo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
		
		assertEquals(20, mgo.count);
	}
	
	class MockGameOverlord implements Observable
	{
		int count = 0;
		public void update(Object o)
		{
			count++;
		}
	}
}
