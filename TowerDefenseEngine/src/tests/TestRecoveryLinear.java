package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import engine.RecoveryBehavior;
import engine.RecoveryLinear;

/**
 * 100% coverage of RecoveryLinear
 * Test class for RecoveryLinear
 * @author Stephen Bussey
 *
 */
public class TestRecoveryLinear 
{
	/**
	 * TESTS:
	 * 	constructor()
	 * 	recoverHealth() when at full health (no recovery)
	 */
	@Test
	public void testNoRecoveryNotHurt()
	{
		RecoveryBehavior rl = new RecoveryLinear(3);
		int maxLife = 30;
		assertEquals(maxLife, rl.calculateRecovery(maxLife, maxLife));
	}
	
	/**
	 * TESTS:
	 * 	recoverHealth() when just enuogh health is recovered to hit max
	 */
	@Test
	public void testRecoveryToMax()
	{
		RecoveryBehavior rl = new RecoveryLinear(3);
		int maxLife = 30;
		assertEquals(maxLife, rl.calculateRecovery(maxLife-3, maxLife));
	}
	
	/**
	 * TESTS:
	 * 	recoverHealth() from 0
	 */
	@Test
	public void testRecoveryFromZero()
	{
		RecoveryBehavior rl = new RecoveryLinear(3);
		int maxLife = 30;
		assertEquals(3, rl.calculateRecovery(0, maxLife));
	}
	
	/**
	 * TESTS:
	 * 	recoverHealth() when not enough health is recovered to hit max
	 */
	@Test
	public void testMiddleRecovery()
	{
		RecoveryBehavior rl = new RecoveryLinear(3);
		int maxLife = 30;
		assertEquals(18, rl.calculateRecovery(15, maxLife));
	}
	
	/**
	 * TESTS:
	 * 	recoverHealth() when more than enough health is recovered to hit max (won't go over)
	 */
	@Test
	public void testWontGoOverMax()
	{
		RecoveryBehavior rl = new RecoveryLinear(3);
		int maxLife = 30;
		assertEquals(30, rl.calculateRecovery(maxLife-2, maxLife));
	}
}
