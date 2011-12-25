package renderers;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.*;

public class CreepRenderer 
{
	public static void paint(Graphics g, Creep c)
	{
		if(!c.isDead())
		{
			Point location = c.getLocation();
			if(location != null)
			{
				g.setColor(((ColorCreep) c).getColor());
				g.drawRect(location.x, location.y, 6, 6);
			}
		}
	}
}
