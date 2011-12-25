package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite
 * @author Stephen Bussey
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCreep.class, TestRecoveryLinear.class, TestRecoveryNone.class, TestTower.class,
	TestCreepFactory.class, TestTowerFactory.class, TestCreepOverlord.class, TestTimeOverlord.class,
	TestPathIterator.class, TestTowerOverlord.class, TestGameOverlord.class, TestAStar.class,
	TestFibonacciHeap.class, TestTowerDamageDecorator.class, TestTowerROFDecorator.class,
	TestRangePolygon.class})
public class AllTests {

}
