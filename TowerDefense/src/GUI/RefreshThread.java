package GUI;

import Runner.TDMain;


public class RefreshThread extends Thread
{
	private boolean shouldRun = true;
	private TDMain invoker;
	
	public RefreshThread(TDMain invoker)
	{
		this.invoker = invoker;
	}
	public synchronized void run()
	{
		while(shouldRun)
		{
			try {
				Thread.sleep(100);
				invoker.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void stopThis()
	{
		shouldRun = false;
	}
}
