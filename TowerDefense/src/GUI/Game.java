package GUI;
import engine.*;
import engine.Observable;
import factory.CreepFactory;
import factory.TowerFactory;
import geom.*;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

import renderers.ColorCreep;
import renderers.ColorTower;


public class Game implements Observable
{
	GameOverlord gameOverlord;
	boolean[][] boardState;
	AStar astar;
	PathIterator creepPath;
	int w, h, level;
	boolean isActive;

	public Game(int w, int h)
	{
		gameOverlord = GameOverlord.getInstance();
		gameOverlord.setTimeInterval(20);
		boardState = new boolean[w][h];
		this.w = w;
		this.h = h;
		this.level = 0;
		isActive = false;
	}

	public boolean startLevel()
	{
		if(isActive) return false;
		level++;
		generateCreeps(level);
		if(!generatePath())
			return false;
		gameOverlord.startLevel(gameOverlord.getCreeps().size(), this);
		isActive = true;
		return true;
	}

	private void generateCreeps(int level)
	{
		gameOverlord.getCreeps().clear();

		if(level <= 7){
			generateCreepLevel(10, "basic", Color.red);
		}
		else if(level > 7 && level <= 14)
		{
			generateCreepLevel(4, "medium", Color.blue);
		}
		else if(level > 14 && level <= 25)
		{
			generateCreepLevel(6, "hard", Color.black);
		}
		else
		{
			generateCreepLevel(8, "basic", Color.red);
			generateCreepLevel(5, "medium", Color.blue);
			generateCreepLevel(4, "hard", Color.black);
		}
	}
	
	private void generateCreepLevel(int mul, String type, Color c)
	{
		int numCreeps = Math.min(level*mul, 1000);
		for(int i = 0; i < numCreeps; i++)
		{
			addCreep(new ColorCreep(CreepFactory.createCreep(type), c));
		}
	}
	
	public void setPositionUnvisitable(Point p)
	{
		boardState[p.x][p.y] = true;
	}

	public void setPositionVisitable(Point p)
	{
		boardState[p.x][p.y] = false; 
	}

	public boolean generatePath()
	{
		astar = new AStar(boardState, new Point(0, 0), new Point(w-60, h-60));
		creepPath = astar.computePath();
		if(creepPath == null)
			return false;
		gameOverlord.setAllCreepPath(creepPath);
		return true;
	}

	public PathIterator getCreepPath()
	{
		return creepPath;
	}

	public Vector<Creep> getCreeps()
	{
		return gameOverlord.getCreeps();
	}

	public void addCreep(Creep c)
	{
		gameOverlord.addCreep(c);
	}

	public Vector<Tower> getTowers()
	{
		return gameOverlord.getTowers();
	}

	public boolean addTower(Tower t, int cost)
	{
		int x = Math.max(0, t.getLocation().x);
		int y = Math.max(0, t.getLocation().y);
		for(int i = x; i < t.getLocation().x + 15; i++)
			for(int j = y; j < t.getLocation().y + 15; j++)
			{
				if(boardState[i][j])
					return false;
			}
		for(int i = x; i < t.getLocation().x + 30; i++)
			for(int j = y; j < t.getLocation().y + 30; j++)
			{
				boardState[i][j] = true;
			}
		//		if(checkPathExists())
		//		{
		gameOverlord.addTower(new ColorTower(t, TowerPurchasePanel.getColor(t.getType())));
		gameOverlord.setGold(getGold() - cost);
		//		}
		return true;
	}

	public boolean removeTower(Tower t)
	{
		int x = Math.max(0, t.getLocation().x);
		int y = Math.max(0, t.getLocation().y);
		for(int i = x; i < t.getLocation().x + 30; i++)
			for(int j = y; j < t.getLocation().y + 30; j++)
			{
				boardState[i][j] = false;
			}
		gameOverlord.removeTower(t);
		return true;
	}

	public void update(Object obj)
	{
		isActive = false;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public int getLives()
	{
		return gameOverlord.getLives();
	}

	public int getRemainingCreeps()
	{
		return gameOverlord.getRemainingCreeps();
	}

	public int getGold()
	{
		return gameOverlord.getGold();
	}
	
	public int getLevel()
	{
		return level;
	}
}
