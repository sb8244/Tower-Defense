package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import engine.RecoveryNone;

/**
 * 
 * Tests RecoveryNone
 * @author Stephen Bussey
 *
 */
public class TestRecoveryNone
{
	@Test
	public void test()
	{
		RecoveryNone rn = new RecoveryNone();
		assertEquals(5, rn.calculateRecovery(5, 10));
	}
}
