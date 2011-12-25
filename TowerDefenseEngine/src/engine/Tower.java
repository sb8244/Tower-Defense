package engine;

import geom.RangePolygon;

import java.awt.Point;

/**
 * Contains information and behavior for a Tower in the game
 * 
 * @author Stephen Bussey
 *
 */
public class Tower
{
	private int damage, range, level, exp, expToLevel, rate ;
	protected int counter;
	private Point location;
	private RangePolygon radius;
	private String type;

	/**
	 * Creates a new Tower
	 * @param d The damage this tower does
	 * @param r The range (radius) of this tower
	 * @param rate The rate of fire
	 * @param p The center location for this tower
	 * @param name The type of this Tower
	 */
	public Tower(int d, int r, int rate, Point p, String name)
	{
		this.damage = d;
		this.range = r;
		this.rate = rate;
		level = 1;
		exp = 0;
		expToLevel = calculateExpToLevel(level);
		setLocation(p);
		type = name;
		counter = 0;
	}

	/**
	 * 
	 * @return This tower's range
	 */
	public int getRange()
	{
		return range;
	}
	
	/**
	 * Uses the rate of fire and a counter to check if the Tower can fire
	 * @return true if the tower can attack
	 */
	public boolean canAttack()
	{
		return counter % rate == 0;
	}

	/**
	 * Increments the attack counter
	 */
	public void updateAttackCounter()
	{
		if(counter % rate == 0)
			counter = 1;
		else
			counter++;
	}

	/**
	 * @return the rate of fire
	 */
	public int getRateOfFire()
	{
		return rate;
	}

	/**
	 * @return This tower's type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return The damage this tower does
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @return The level of this tower
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return The experience this Tower has
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @return The experience to the next level
	 */
	public int getExpToLevel() {
		return expToLevel;
	}

	/**
	 * Adds experience and level up (if exp > exp needed)
	 * @param amount
	 * @return true if this tower leveled up
	 */
	public boolean addExp(int amount)
	{
		if(level < 5)
		{
			exp += amount;
			if(exp >= expToLevel)
			{
				exp = 0;
				level++;
				expToLevel = calculateExpToLevel(level);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param c Creep to check
	 * @return True if c is in range of this Tower
	 */
	public boolean creepInRange(Creep c)
	{
		return pointInRange(c.getLocation());
	}

	/**
	 * @param p The point to check
	 * @return true if p is in range of the tower
	 */
	public boolean pointInRange(Point p)
	{
		return radius.contains(p);
	}

	/**
	 * Sets this tower's location
	 * @param x 
	 * @param y
	 */
	public void setLocation(int x, int y)
	{
		setLocation(new Point(x,y));
	}

	/**
	 * Sets this tower's location
	 * @param p
	 */
	public void setLocation(Point p)
	{
		location = p;
		radius = new RangePolygon(location, range);
	}

	/**
	 * @return This tower's location
	 */ 
	public Point getLocation()
	{
		return location;
	}

	/**
	 * Calculate the experience needed for a level (crappy now)
	 * @param level
	 * @return The needed experience
	 */
	private int calculateExpToLevel(int level)
	{
		return level * 100;
	}

	/**
	 * Attack a creep
	 * @param c The creep to attack
	 */
	public void attackCreep(Creep c)
	{
		if(c != null)
			c.takeHit(getDamage());
	}
}
