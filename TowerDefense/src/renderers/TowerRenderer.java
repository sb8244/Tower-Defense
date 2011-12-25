package renderers;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.Tower;


public class TowerRenderer 
{
	public static void paint(Graphics g, Tower t)
	{
		Point location = t.getLocation();
		if(location != null)
		{
			g.setColor(((ColorTower) t).getColor());
			g.fillRect(location.x, location.y, 30, 30);
			g.setColor(Color.magenta);
			g.drawRect(location.x, location.y, 30, 30);
		}
	}
}
