package engine;

import geom.PathIterator;
import java.awt.Point;

/**
 * Holds information for a Creep
 * @author Stephen Bussey
 *
 */
public class Creep 
{
	private int currentHealth, maxHealth, armor, eworth, gworth;
	private String type;
	private RecoveryBehavior rb;
	private Point location, prevLocation;
	private PathIterator path;

	/**
	 * Create a new Creep
	 * @param maxH The max health of this Creep
	 * @param arm The armor of this creep
	 * @param expw The experience that this creep is worth
	 * @param gw The gold that this creep is worth
	 * @param type The type of creep
	 * @param r The recovery that this Creep will use
	 */
	public Creep(int maxH, int arm, int expw, int gw, String type, RecoveryBehavior r)
	{
		this.currentHealth = maxH;
		this.maxHealth = maxH;
		this.armor = arm;
		this.eworth = expw;
		this.gworth = gw;
		this.type = type;
		this.rb = r;
		prevLocation = location = new Point(0, 0);
		path = new PathIterator(location);
	}
	/**
	 * 
	 * @return The Recovery Behavior that this creep uses
	 */
 	public RecoveryBehavior getRecoveryBehavior()
	{
		return rb;
	}

 	/**
 	 * Moves this creep to the next location in it's path
 	 * Will move again with a 50% chance (recursively)
 	 */
	public void move()
	{
		prevLocation = location;
		location = path.next();
		if(Math.random() > .5)
			move();

	}

	/**
	 * Appends p to this creep's path
	 * @param p The point to append
	 */
	public void addPointToPath(Point p)
	{
		path.addPoint(p);
	}
 
	/**
	 * Set the Creep's path
	 * @param path The new path
	 */
	public void setPath(PathIterator path)
	{
		this.path = new PathIterator();
		while(path.hasNext())
		{
			this.path.addPoint(path.next());
		}
		path.reset();
	}

	/**
	 * Sets the Creep's location
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void setLocation(int x, int y)
	{
		setLocation(new Point(x, y));
	}

	/**
	 * Sets the Creep's location
	 * @param p The location as a Point
	 */
	public void setLocation(Point p)
	{
		prevLocation = location;
		location = p;
	}

	/**
	 * 
	 * @return The last known location of the Creep before where it is now
	 */
	public Point getPreviousLocation()
	{
		return prevLocation;
	}
	
	/**
	 * 
	 * @return The current location of the Creep
	 */
	public Point getLocation()
	{
		return location;
	}

	/**
	 * Takes a hit with adjustment for armor
	 * @param damage The damage to take
	 */
	public void takeHit(int damage)
	{
		int adjustedDamage = Math.max(0, damage - armor);
		currentHealth = Math.max(0, currentHealth - adjustedDamage);
	}

	/**
	 * 
	 * @return true if the creep is dead
	 */
	public boolean isDead()
	{
		return currentHealth == 0;
	}

	/**
	 * 
	 * @return The current health of the creep
	 */
	public int getCurrentHealth()
	{
		return currentHealth;
	}

	/**
	 * 
	 * @return The max health of the creep
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * 
	 * @return The armor of the Creep
	 */
	public int getArmor()
	{
		return armor;
	}

	/**
	 * 
	 * @return The experience worth of the creep
	 */
	public int getEworth() {
		return eworth;
	}

	/**
	 * 
	 * @return The gold worth of the creep
	 */
	public int getGworth() {
		return gworth;
	}

	/**
	 * 
	 * @return The type of this creep
	 */
	public String getType() {
		return type;
	}

	/**
	 * Recover using the Recovery Behavior
	 */
	public void recover()
	{
		if(currentHealth > 0)
			currentHealth = rb.calculateRecovery(currentHealth, maxHealth);
	}
}
