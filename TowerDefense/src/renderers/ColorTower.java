package renderers;

import java.awt.Color;
import engine.Tower;

public class ColorTower extends Tower
{
	private Color color;
	
	public ColorTower(Tower t, Color c)
	{
		super(t.getDamage(), t.getRange(), t.getRateOfFire(), t.getLocation(), t.getType());
		color = c;
	}
	
	public Color getColor()
	{
		return color;
	}
}
