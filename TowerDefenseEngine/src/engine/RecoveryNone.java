package engine;

/**
 * Provides no recovery
 * 
 * @author Stephen Bussey
 *
 */
public class RecoveryNone implements RecoveryBehavior
{
	/**
	 * effectively does nothing
	 */
	public int calculateRecovery(int currentHealth, int maxHealth)
	{
		return currentHealth;
	}

}
