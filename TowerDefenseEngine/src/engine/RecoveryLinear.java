package engine;

/**
 * Linear recovery provides a set amount of recovery 
 * 
 * @author Stephen Bussey
 *
 */
public class RecoveryLinear implements RecoveryBehavior
{
	private int step;
	
	/**
	 * Create a new Recoverylinear
	 * @param step The recovery amount 
	 */
	public RecoveryLinear(int step)
	{
		this.step = step;
	}
	
	/**
	 * Recover health linearly (maxes at maxHealth)
	 */
	public int calculateRecovery(int currentHealth, int maxHealth)
	{
		return Math.min(currentHealth + step, maxHealth);
	}

}
