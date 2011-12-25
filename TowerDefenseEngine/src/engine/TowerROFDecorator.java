package engine;

/**
 * Decorates a tower with a rate of fire boost
 * @author Stephen Bussey
 *
 */
public class TowerROFDecorator extends TowerDecorator {

	/**
	 * Creates a new ROFDecorator 
	 * @param rof The rate of fire mod
	 * @param t The tower to decorate
	 */
	public TowerROFDecorator(int rof, Tower t) {
		super(0, rof, t);
	}

}
