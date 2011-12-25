package engine;

import java.awt.Point;

/**
 * Decorator pattern
 * Abstract class for Tower Decorators
 * @author Stephen Bussey
 *
 */
public abstract class TowerDecorator extends Tower
{
	Tower theTower;
	int damageMod, rofMod;

	/**
	 * Creates a new decorator
	 * @param damageM The damage mod
	 * @param rof The rate of fire mod
	 * @param t The Tower to decorate
	 */
	public TowerDecorator(int damageM, int rof, Tower t)
	{
		super(0, 0, 0, t.getLocation(), t.getType());
		this.counter = t.counter;
		damageMod = damageM;
		rofMod = rof;
		theTower = t;
	}

	public int getDamage()
	{
		return damageMod + theTower.getDamage();
	}

	public boolean canAttack()
	{
		return counter % getRateOfFire() == 0;
	}

	public void updateAttackCounter()
	{
		if(counter % getRateOfFire() == 0)
			counter = 1;
		else
			counter++;
	}

	public int getRateOfFire()
	{
		return Math.max(1,theTower.getRateOfFire() - rofMod);
	}

	public String getType()
	{
		return theTower.getType();
	}

	public int getLevel() {
		return theTower.getLevel();
	}

	public int getExp() {
		return theTower.getExp();
	}

	public int getExpToLevel() {
		return theTower.getExpToLevel();
	}

	public boolean addExp(int amount)
	{
		return theTower.addExp(amount);
	}

	public boolean creepInRange(Creep c)
	{
		return theTower.creepInRange(c);
	}

	public boolean pointInRange(Point p)
	{
		return theTower.pointInRange(p);
	}

	public void attackCreep(Creep c)
	{
		theTower.attackCreep(c);
	}
}
