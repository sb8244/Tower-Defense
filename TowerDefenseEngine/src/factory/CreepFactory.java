package factory;

import engine.*;

/**
 * Factory for the creation of Creeps by name
 * 
 * @author Stephen Bussey
 *
 */
public class CreepFactory 
{
	/**
	 * Creates a Creep given a type
	 * @param type the type of creep to create
	 * @return The new Creep
	 */
	public static Creep createCreep(String type)
	{
		type = type.toLowerCase();
		Creep ret = null;
		if(type.equals("basic"))
		{
			ret = new Creep(30, 0, 3, 5, "Basic", new RecoveryNone());
		}
		else if(type.equals("medium"))
		{
			ret = new Creep(100, 5, 10, 10, "Medium", new RecoveryLinear(1));
		}
		else if(type.equals("hard"))
		{
			ret = new Creep(150, 10, 50, 30, "Hard", new RecoveryLinear(25));
		}
		return ret;
	}
}
