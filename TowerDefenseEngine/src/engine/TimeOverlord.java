package engine;

import java.util.Vector;

/**
 * The TimeOverlord controls the timing and frame rate of the game
 * @author Stephen Bussey
 *
 */
public class TimeOverlord
{
	Vector<Observable> observers;
	GameThread gameThread;
	private int sleepTime;

	/**
	 * Creates a new empty TimeOverlord with a given sleep interval
	 * @param s The sleep interval
	 */
	public TimeOverlord(int s)
	{
		sleepTime = s;
		observers = new Vector<Observable>();
	}
	
	/**
	 * Default sleep interval is 100 ms
	 */
	public TimeOverlord()
	{
		this(100);
	}

	/**
	 * Starts the game thread
	 */
	public void start()
	{
		gameThread = new GameThread();
		gameThread.start();
	}

	/**
	 * ends the game thread
	 */
	public void stop()
	{
		gameThread.stopThis();
	}

	/**
	 * updates all observers
	 */
	private void update()
	{
		synchronized(observers)
		{
			for(Observable o: observers)
			{
				o.update(null);
			}
		}
	}

	/**
	 * Add an observable to the observers
	 * @param o 
	 */
	public void addObserver(Observable o)
	{
		observers.add(o);
	}

	/**
	 * Removes an observable from the observers
	 * @param o
	 */
	public void removeObserver(Observable o)
	{
		observers.remove(o);
	}
	
	/**
	 * Sets the sleep time
	 * @param s The new sleep time
	 */
	public void setSleepTime(int s)
	{
		sleepTime = s;
	}

	/**
	 * Thread that calls update every sleep interval
	 * @author Stephen Bussey
	 *
	 */
	class GameThread extends Thread
	{
		private boolean shouldRun = true;
		/**
		 * Called when the thread starts
		 * The thread stops when shouldRun is false
		 */
		public synchronized void run()
		{
			while(shouldRun)
			{
				try {
					Thread.sleep(sleepTime);
					update();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * Stops the thread by setting shouldRun to false
		 */
		public void stopThis()
		{
			shouldRun = false;
		}
	}

}
