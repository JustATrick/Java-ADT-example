package in.sinking.ADTExample;

import static in.sinking.ADTExample.CSet.empty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CSetTest {
	@Test
	public void emptyShouldHaveNothingInIt() {
		final CSet empty = empty();
		
		assertMissing(empty, -5, 0, 1, 2, 3, 100, 235265);
		// that's pretty much all integers, right? :-)
		//   testing for all int values does work, but was slow and
		//   likely to test my laptop's CPU fan to destruction...
	}
	
	@Test
	public void aSingleInsertShouldAddAValueWhenTheValueWasMissingBefore() {
		final CSet empty = empty();
		assertMissing(empty, 3);
		
		final CSet a = empty.insert(3);
		assertContains(a, 3);
	}
	
	@Test
	public void repeatedInsertsShouldNotCauseProblems() {
		final CSet initial = empty().insert(3);
		assertContains(initial, 3);
		
		final CSet a = initial.insert(3);
		assertContains(a, 3);
	}
	
	@Test
	public void chainedInsertsShouldAddAllValues() {
		final CSet empty = empty();
		assertMissing(empty, 1, 3, 5);
		
		final CSet a = empty().insert(1).insert(3).insert(5);
		assertContains(a, 1, 3, 5);
	}
	
	
	@Test
	public void unionOfSetsShouldWork() {
		final CSet a = empty().insert(1).insert(3);
		final CSet b = empty().insert(3).insert(5);
	
		final CSet underTest = a.union(b);
		assertContains(underTest, 1, 3, 5);
	}
	
	@Test
	public void unionOfEmptyShouldBeFineToo() {
		final CSet a = empty().insert(-1).insert(-2);
		final CSet empty = empty();
		
		assertContains(a.union(empty), -1, -2);
		assertContains(empty.union(a), -1, -2);
	}
	
	private void assertMissing(final CSet underTest, int... values) {
		for (int value : values) {
			assertFalse("Set should not contain " + value, underTest.contains(value)); 
		}
	}
	
	private void assertContains(final CSet underTest, int... values) {
		for (int value : values) {
			assertTrue("Set should contain " + value, underTest.contains(value)); 
		}
	}
}
