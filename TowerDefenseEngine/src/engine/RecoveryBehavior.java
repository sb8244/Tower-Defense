package engine;

/**
 * Interface for Recovery
 * @author Stephen Bussey
 *
 */
public interface RecoveryBehavior
{
	/**
	 * Recovers health based on the implementation
	 * @param currentHealth 
	 * @param maxHealth
	 * @return The new health
	 */
	public int calculateRecovery(int currentHealth, int maxHealth);
}
