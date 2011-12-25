package factory;

import java.awt.Point;

import engine.Tower;

/**
 * Factory for the creation of Towers
 * @author Stephen Bussey
 *
 */
public class TowerFactory
{
	/**
	 * Create a new tower given a type
	 * @param type The type of tower
	 * @param loc The tower's center point on screen
	 * @return The new tower
	 */
	public static Tower createTower(String type, Point loc)
	{
		type = type.toLowerCase();
		Tower ret = null;
		if(type.equals("chogath"))
		{
			ret = new Tower(25, 100, 10, loc, "Chogath");
		}
		else if(type.equals("ezreal"))
		{
			ret = new Tower(75, 200, 6, loc, "Ezreal");
		}
		return ret;
	}
}
