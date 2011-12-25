package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import engine.TimeOverlord;
import engine.Observable;

/**
 * 
 * Tests TimeOverlord
 * @author Stephen Bussey
 *
 */
public class TestTimeOverlord 
{
	@Test
	public void testGameO()
	{
		MockObserver mo = new MockObserver();
		TimeOverlord go = new TimeOverlord();
		go.addObserver(mo);
		assertEquals(0, mo.count);
		go.start();
		try
		{
			Thread.sleep(1100);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
		assertEquals(10, mo.count);
	}
	
	@Test
	public void testRestart()
	{
		
	}
	@Test
	public void testRemoveObserver()
	{
		MockObserver mo = new MockObserver();
		TimeOverlord go = new TimeOverlord();
		go.addObserver(mo);
		assertEquals(0, mo.count);
		go.start();
		try
		{
			Thread.sleep(1100);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
		assertEquals(10, mo.count);
		go.removeObserver(mo);
		go.start();
		try
		{
			Thread.sleep(1100);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		go.stop();
		assertEquals(10, mo.count);
	}
	class MockObserver implements Observable
	{
		int count = 0;
		
		public void update(Object o) 
		{
			count++;
		}
	}
}
