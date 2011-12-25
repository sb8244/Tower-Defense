package engine;

import java.util.Vector;

/**
 * A TowerOverlord controls all of the towers in the game
 * @author Stephen Bussey
 *
 */
public class TowerOverlord implements Observable
{
	private Vector<Tower> allTowers;
	private Vector<Observable> deathObservers;

	/**
	 * Creates a new TowerOverlord with the given TimeOverlord
	 * @param lo The TimeOverlord that will be giving this updates
	 */
	public TowerOverlord(TimeOverlord lo)
	{
		lo.addObserver(this);
		allTowers = new Vector<Tower>();
		deathObservers = new Vector<Observable>();
	}

	/**
	 * Adds an Observable for when a Tower kills a creep
	 * @param o
	 */
	public void addDeathObserver(Observable o)
	{
		deathObservers.add(o);
	}

	/**
	 * Removes an Observable from the death listeners
	 * @param o
	 */
	public void removeDeathObserver(Observable o)
	{
		deathObservers.remove(o);
	}

	/**
	 * Remove a tower from the game
	 * @param t
	 */
	public void removeTower(Tower t)
	{
		allTowers.remove(t);
	}

	/**
	 * Add a tower to the game
	 * @param t
	 */
	public void addTower(Tower t)
	{
		allTowers.add(t);
	}

	/**
	 * @return A Vector containing all Towers in the game
	 */
	public Vector<Tower> getTowers()
	{
		return allTowers;
	}

	/**
	 * Will cause all towers to attack and will update the death observers when a creep is killed
	 */
	public synchronized void update(Object obj)
	{
		synchronized(allTowers)
		{
			Vector<Creep> creeps = GameOverlord.getInstance().getCreeps();
			for(Tower t: allTowers)
			{
				if(t.canAttack())
				{
					for(Creep c: creeps)
					{
						if(!c.isDead() && t.creepInRange(c))
						{
							t.attackCreep(c);
							if(c.isDead())
								creepKilled(c);
							break;
						}
					}
				}
				t.updateAttackCounter();
			}
		}
	}

	/**
	 * Update the deathObservers of a creep's death
	 * @param c The killed creep
	 */
	private void creepKilled(Creep c)
	{
		for(Observable o: deathObservers)
		{
			o.update(c);
		}
	}
}
