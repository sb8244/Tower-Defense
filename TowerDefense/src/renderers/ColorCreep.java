package renderers;
import java.awt.Color;

import engine.Creep;
import engine.RecoveryBehavior;


public class ColorCreep extends Creep
{
	private Color color;
	
	public ColorCreep(Creep c, Color col)
	{
		super(c.getMaxHealth(), c.getArmor(), c.getEworth(), c.getGworth(), c.getType(), c.getRecoveryBehavior());
		this.color = col;
	}

	public Color getColor()
	{
		return color;
	}
}
