package engine;

/**
 * Tower decorate for damage mod
 * @author Stephen Bussey
 *
 */
public class TowerDamageDecorator extends TowerDecorator {

	/**
	 * Creates a new Decorate with the given damage mod
	 * @param damageM The damage mod
	 * @param t The tower to wrap
	 */
	public TowerDamageDecorator(int damageM, Tower t) {
		super(damageM, 0, t);
	}

}
