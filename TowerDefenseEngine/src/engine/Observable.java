package engine;

/**
 * Interface to implement the Observer pattern
 * @author Stephen Bussey
 *
 */
public interface Observable
{
	/**
	 * Updates the listener
	 * @param obj Used for different purposes, specific to each implementation
	 */
	public void update(Object obj);
}
