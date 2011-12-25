package Runner;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import GUI.Game;
import GUI.RefreshThread;
import GUI.TDPanel;
import GUI.TowerPurchasePanel;

import engine.GameOverlord;
import engine.Observable;
import engine.TimeOverlord;


public class TDMain extends JFrame implements Observable, MouseListener, MouseMotionListener
{
	Game game;
	//The w/h of the entire window
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 600;

	public static final int FRAMEWIDTH = 600;
	protected static final int FRAMEHEIGHT = 600;

	public static final int OFFSET = 29;

	TDPanel gamePanel;
	TowerPurchasePanel purchasePanel;

	private RefreshThread rt;

	public TDMain()
	{
		super("Tower Defense");
		this.setSize(WIDTH, HEIGHT);
		game = new Game(FRAMEWIDTH, FRAMEHEIGHT);

		this.setLayout(null);

		gamePanel = new TDPanel(FRAMEWIDTH, FRAMEHEIGHT , game);
		purchasePanel = new TowerPurchasePanel(WIDTH-FRAMEWIDTH, FRAMEHEIGHT, game);
		purchasePanel.setLocation(FRAMEWIDTH, 0);

		this.add(gamePanel);
		this.add(purchasePanel);
		GameOverlord.getInstance().addTimeObserver(this);
		addMouseListener(this);

		rt = new RefreshThread(this);
	}

	public static void main(String[] args)
	{
		TDMain win = new TDMain();
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paint(Graphics g)
	{
		purchasePanel.repaint();
		gamePanel.repaint();
	}

	public void update(Object obj) {
		this.repaint();
	}

	private boolean buyNextClick = false;
	private String towerBought;
	public void mouseClicked(MouseEvent arg0) {
		this.repaint();
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
			if(buyNextClick)
			{
				if(!towerBought.equals("Not enough gold"))
				{
					boolean towerAdded = gamePanel.placeTower(arg0.getPoint(), towerBought, purchasePanel.getCost(towerBought));
					if(towerAdded)
						System.out.println(towerBought + " tower added at " + arg0.getPoint());
				}
				buyNextClick = false;
				removeMouseMotionListener(this);
				gamePanel.setPointSnap(false);
				return;
			}
			towerBought = purchasePanel.mouseClicked(arg0);
			if(towerBought != null)
			{
				addMouseMotionListener(this);
				gamePanel.setPointSnap(true);
				buyNextClick = true;
				return;
			}
			gamePanel.mouseClicked(arg0);
		}
		else if(arg0.getButton() == MouseEvent.BUTTON3)
		{
			gamePanel.removeTower(arg0.getPoint());
		}
	}


	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

	public void mouseDragged(MouseEvent arg0) {}

	public void mouseMoved(MouseEvent arg0) {
		gamePanel.mouseMoved(arg0);
	}
}
