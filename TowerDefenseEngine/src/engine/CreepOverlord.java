package engine;

import java.util.Vector;

/**
 * Contains all the Creeps in the game and controls their behavior
 * @author Stephen Bussey
 *
 */
public class CreepOverlord implements Observable
{
	private Vector<Creep> allCreeps;
	private Vector<Observable> nexusObservers;

	/**
	 * Creates a new empty CreepOverlord
	 * @param overlord The TimeOverlord that will be sending this Creep update messages
	 */
	public CreepOverlord(TimeOverlord overlord)
	{
		allCreeps = new Vector<Creep>();
		nexusObservers = new Vector<Observable>();
		overlord.addObserver(this);
	}

	/**
	 * Adds an observer for when the Creeps hit the "nexus" end point
	 * @param o The Observable to add
	 */
	public void addObserver(Observable o)
	{
		nexusObservers.add(o);
	}

	
	/**
	 * Removes a nexus Observer
	 * @param o The Observable to remove
	 */
	public void removeObserver(Observable o)
	{
		nexusObservers.remove(o);
	}

	/**
	 * Removes a Creep from the game
	 * @param c The creep to remove
	 */
	public void removeCreep(Creep c)
	{
		allCreeps.remove(c);
	}

	/**
	 * Adds a Creep to the game
	 * @param c The creep to add
	 */
	public void addCreep(Creep c)
	{
		allCreeps.add(c);
	}

	/**
	 * @return A Vector of all the Creeps
	 */
	public Vector<Creep> getCreeps()
	{
		return allCreeps;
	}

	/*
	 * Move the creeps to the next point in their path iterator
	 * If dead or at the end of the path, remove them
	 * 	If end of path: must of hit nexus so update game overlord of this
	 * 
	 * (non-Javadoc)
	 * @see engine.Observable#update()
	 */
	/**
	 * Moves all the Creeps and handles their hitting the nexus
	 */
	public void update(Object o)
	{		
		synchronized(allCreeps)
		{
			int size = allCreeps.size();
			for(int i = 0; i < size; i++)
			{
				Creep c = allCreeps.get(i);
				if(c == null) continue;
				if(c.isDead() || c.getLocation() == null)
				{
					//do nothing
				}
				else
				{
					c.move();
					//	System.out.println(c + "::" + c.getPreviousLocation() + " to " + c.getLocation());
					if(c.getLocation() == null)
					{
						creepHitNexus();
					}
				}
			}
		}
	}

	/**
	 * Update the nexus Observers that a Creep hit the "nexus"
	 */
	private void creepHitNexus()
	{
		synchronized(nexusObservers)
		{
			for(Observable o: nexusObservers)
			{
				o.update(null);
			}
		}
	}
}
