package in.sinking.ADTExample;

import static in.sinking.ADTExample.ASet.contains;
import static in.sinking.ADTExample.ASet.empty;
import static in.sinking.ADTExample.ASet.insert;
import static in.sinking.ADTExample.ASet.union;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ASetTest {
    @Test
    public void emptyShouldHaveNothingInIt() {
        final ASet empty = empty();

        assertMissing(empty, -5, 0, 1, 2, 3, 100, 235265);
        // that's pretty much all integers, right? :-)
        //   testing for all int values does work, but was slow and
        //   likely to test my laptop's CPU fan to destruction...
    }

    @Test
    public void aSingleInsertShouldAddAValueWhenTheValueWasMissingBefore() {
        final ASet empty = empty();
        assertMissing(empty, 3);

        final ASet a = insert(empty, 3);
        assertContains(a, 3);
    }

    @Test
    public void repeatedInsertsShouldNotCauseProblems() {
        final ASet initial = insert(empty(), 3);
        assertContains(initial, 3);

        final ASet a = insert(initial, 3);
        assertContains(a, 3);
    }

    @Test
    public void chainedInsertsShouldAddAllValues() {
        final ASet empty = empty();
        assertMissing(empty, 1, 3, 5);

        final ASet a = insert(insert(insert(empty(), 1), 3), 5);
        assertContains(a, 1, 3, 5);
    }


    @Test
    public void unionOfSetsShouldWork() {
        final ASet a = insert(insert(empty(), 1), 3);
        final ASet b = insert(insert(empty(), 3), 5);

        final ASet underTest = union(a, b);
        assertContains(underTest, 1, 3, 5);
    }

    @Test
    public void unionOfEmptyShouldBeFineToo() {
        final ASet a = insert(insert(empty(), -1), -2);
        final ASet empty = empty();

        assertContains(union(a, empty), -1, -2);
        assertContains(union(empty, a), -1, -2);
    }

    private void assertMissing(final ASet underTest, int... values) {
        for (int value : values) {
            assertFalse("Set should not contain " + value, contains(underTest, value));
        }
    }

    private void assertContains(final ASet underTest, int... values) {
        for (int value : values) {
            assertTrue("Set should contain " + value, contains(underTest, value));
        }
    }
}
