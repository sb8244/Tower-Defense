package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JPanel;

import Runner.TDMain;


import renderers.CreepRenderer;
import renderers.TowerRenderer;

import engine.*;
import factory.TowerFactory;
import geom.PathIterator;


public class TDPanel extends JPanel
{
	private Game game;

	private boolean pointSnap;
	private Rectangle[][] snapGrid;
	public TDPanel(int w, int h, Game game)
	{
		this.setLocation(0, 0);
		this.setSize(w, h);
		this.game = game;
		this.setDoubleBuffered(true);

		pointSnap = false;

		snapGrid = new Rectangle[getWidth()/30][getHeight()/30];
		for(int i = 0; i < getWidth()/30; i += 1)
			for(int j = 0; j < getHeight()/30; j += 1)
			{
				snapGrid[i][j] = new Rectangle(i*30, j*30, 30, 30);
			}
	}

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setColor(Color.black);
		for(Tower t: game.getTowers())
		{
			TowerRenderer.paint(g, t);
		}
		g2d.setColor(Color.red);
		if(game.getLives() <= 0)
		{
			g2d.drawString("Game Over", 300, 300);
			return;
		}
		g2d.drawString("Gold: " + game.getGold(), getWidth()-100, 40);
		g2d.drawString("Lives: " + game.getLives(), getWidth()-100, 60);
		g2d.drawString("Level: " + game.getLevel(), getWidth()-100, 80);
		if(game.isActive())
		{
			g2d.setColor(Color.red);
			g2d.drawString("Creeps: " +game.getRemainingCreeps(), getWidth()-100, 20);
			//		paintPath(g2d, game.getCreepPath());

			Vector<Creep> creeps = game.getCreeps();
			if(creeps != null)
			{
				for(Creep c: creeps)
				{
					if(c != null)
						CreepRenderer.paint(g2d, c);
				}

			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
			if(arg0.getPoint().x <= getWidth() && arg0.getPoint().y <= getHeight() )
			{
				if(game.startLevel())
					System.out.println("Level started");
				else
					System.out.println("Path is blocked");
			}
		}
	}

	public boolean placeTower(Point p, String type, int cost)
	{
		p = getLocationInSnapGrid(p);
		if(game.isActive() || p.x > game.w || p.y > game.w)
			return false;
		p = new Point(p.x - 1, p.y - TDMain.OFFSET - 1);
		//p = new Point(p.x-15, p.y - TDMain.OFFSET - 15);
		Tower toAdd = TowerFactory.createTower(type, p);
		if(toAdd != null)
		{
			return game.addTower(toAdd, cost);
		}
		return false;
	}

	public boolean removeTower(Point p)
	{
		p = getLocationInSnapGrid(p);
		if(game.isActive() || p.x > game.w || p.y > game.w)
			return false;
		p = new Point(p.x - 1, p.y - TDMain.OFFSET - 1);
		Tower toRemove = findTower(p);
		if(toRemove != null)
		{
			return game.removeTower(toRemove);
		}
		return false;
	}

	private Tower findTower(Point p)
	{
		for(Tower t: game.getTowers())
		{
			if(t.getLocation().x == p.x && t.getLocation().y == p.y)
				return t;
		}
		return null;
	}

	@SuppressWarnings("unused")
	private void paintPath(Graphics g, PathIterator creepPath)
	{
		g.setColor(Color.cyan);
		while(creepPath.hasNext())
		{
			Point p = creepPath.next();
			g.drawRect(p.x, p.y, 1, 1);
		}
		creepPath.reset();
	}

	@SuppressWarnings("unused")
	private void paintGridSnap(Graphics g, Point p)
	{
		if(pointSnap)
		{
			Point snapPoint = getLocationInSnapGrid(p);
			if(snapPoint != null)
			{
				g.setColor(Color.white);
				g.fillRect(snapPoint.x, snapPoint.y, 5, 5);
			}
		}
	}

	private Point getLocationInSnapGrid(Point p)
	{
		if(p != null )
		{
			for(int i = 0; i < snapGrid.length; i++)
				for(int j = 0; j < snapGrid[i].length; j++)
					if(snapGrid[i][j].contains(p))
					{
						return new Point(snapGrid[i][j].x, snapGrid[i][j].y);
					}
		}
		return null;
	}

	public void setPointSnap(boolean val)
	{
		pointSnap = val;
	}

	/*
	 * Source of lag
	 */
	Point prevsnap;
	public void mouseMoved(MouseEvent e)
	{
		Graphics g = getGraphics();
		Point snap = getLocationInSnapGrid(new Point(e.getPoint().x, e.getPoint().y - TDMain.OFFSET));
		if(snap != prevsnap)
		{
			paint(g);
			paintGridSnap(g, new Point(e.getPoint().x, e.getPoint().y - TDMain.OFFSET));
			prevsnap = snap;
		}		
	}
}
