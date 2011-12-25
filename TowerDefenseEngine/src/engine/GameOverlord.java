package engine;

import geom.PathIterator;

import java.util.Vector;

/**
 * A GameOverlord is a singleton class that consists of Time,Creep, and Tower Overlords.
 * It is the simulation for the game and controls everything that happens.  It is the brains.
 * @author Stephen Bussey
 *
 */
public class GameOverlord implements Observable
{
	//singleton pattern
	private static GameOverlord instance;

	private int gold;
	private int lives;
	private int levelCount;

	private TimeOverlord lo;
	private CreepOverlord co;
	private TowerOverlord to;

	private Vector<Observable> observers;

	/**
	 * Singleton method to either create a new GameOverlord or get the current one
	 * @return THE GameOverlord instance
	 */
	public static GameOverlord getInstance()
	{
		if(instance == null)
		{
			instance = new GameOverlord();
		}
		return instance;
	}

	/**
	 * Singleton method
	 * Private constructor
	 */
	private GameOverlord()
	{
		gold = 1000;
		lives = 50;
		lo = new TimeOverlord();
		co = new CreepOverlord(lo);
		to = new TowerOverlord(lo);
		observers = new Vector<Observable>();
		to.addDeathObserver(this);
		co.addObserver(this);
	}

	/**
	 * set the TimeOverlord's sleep interval
	 * @param s The sleep time of the TimeOverlord
	 */
	public void setTimeInterval(int s)
	{
		lo.setSleepTime(s);
	}

	/**
	 * Reset the GameOverlord
	 */
	public static void reset()
	{
		instance = null;
	}

	/**
	 * @return The gold the player has
	 */
	public int getGold()
	{
		return gold;
	}

	/**
	 * @return The lives the player has
	 */
	public int getLives()
	{
		return lives;
	}

	/**
	 * sets the player's gold to a new amount
	 * @param newGold The new gold amount
	 */
	public void setGold(int newGold)
	{
		gold = newGold;
	}

	/**
	 * Adds a Tower to the game
	 * @param t The Tower to add
	 */
	public void addTower(Tower t)
	{
		to.addTower(t);
	}

	/**
	 * Adds an Observer to the game's TimeObserver
	 * @param o
	 */
	public void addTimeObserver(Observable o)
	{
		lo.addObserver(o);
	}

	/**
	 * Removes an Observer from the game's TimeOverlord
	 * @param o
	 */
	public void removeTimeObserver(Observable o)
	{
		lo.removeObserver(o);
	}

	/**
	 * Adds a creep to the CreepOverlord
	 * @param c
	 */
	public void addCreep(Creep c)
	{
		co.addCreep(c);
	}

	/**
	 * @return A Vector of the game's Creeps
	 */
	public Vector<Creep> getCreeps()
	{
		return co.getCreeps();
	}

	/**
	 * @return A Vector of the game's Towers
	 */
	public Vector<Tower> getTowers()
	{
		return to.getTowers();
	}

	/**
	 * Removes a tower from the TowerOverlord
	 * @param t
	 */
	public void removeTower(Tower t)
	{
		to.removeTower(t);
	}

	/**
	 * Removes a Creep from the CreepOverlord
	 * @param c
	 */
	public void removeCreep(Creep c)
	{
		co.removeCreep(c);
	}

	/**
	 * Sets all the Creep's path to pi
	 * @param pi The new path
	 */
	public void setAllCreepPath(PathIterator pi)
	{
		for(Creep c: co.getCreeps()) {
			c.setPath(pi);
		}
	}

	/**
	 * Starts the current level
	 */
	public void startLevel()
	{
		lo.start();
	}

	/**
	 * Starts the current level.
	 * There are creeps number of Creeps, and obs will be updated when the round is over
	 * @param creeps The number of creeps
	 * @param obs The Observable to update when this round is over
	 */
	public void startLevel(int creeps, Observable obs)
	{
		levelCount = creeps;
		if(obs != null)
			observers.add(obs);
		lo.start();
	}

	/**
	 * Ends the current level and updates all observables listening for this
	 */
	public void endLevel()
	{
		lo.stop();
		for(Observable o: observers) {
			o.update(null);
		}
		observers.clear();
	}

	/**
	 * Updates the Game and handles events
	 * obj == null (creep hit the end)
	 * obj is a Creep (creep was killed)
	 * Ends the game if the number of Creeps in the level is 0
	 */
	public void update(Object obj)
	{
		//obj is null when a creep hits the end
		if(obj == null)
		{
			lives--;
		}
		//obj is a Creep when a creep is killed
		else if(obj instanceof Creep)
		{
			gold += ((Creep)obj).getGworth();
		}
		levelCount--;
		if(levelCount == 0) {
			endLevel();
		}
	}

	/**
	 * @return The remaining Creeps to kill
	 */
	public int getRemainingCreeps()
	{
		return levelCount;
	}
}
