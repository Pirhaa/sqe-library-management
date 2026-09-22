import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * SCOPE EXPLANATION:
 * - @BeforeClass (MODULE scope): Used for fine-tier boundary pairs
 *   (days -> expected tier). This data is IMMUTABLE and shared.
 * - No @Before needed: Library methods are STATIC with no mutable state.
 */
public class LibraryTest {

    private static int[] fineTierDays;
    private static String[] expectedTiers;

    @BeforeClass
    public static void moduleSetup() {
        fineTierDays = new int[]{0, 1, 7, 8, 14, 15, 30, 31};
        expectedTiers = new String[]{"None", "Low", "Low", "Medium",
                                     "Medium", "High", "High", "Severe"};
        System.out.println(">>> @BeforeClass (module scope): loaded fine-tier boundaries");
    }

    @Test
    public void allFineTierBoundaries() {
        for (int i = 0; i < fineTierDays.length; i++) {
            assertEquals("fineTier(" + fineTierDays[i] + ")",
                    expectedTiers[i], Library.fineTier(fineTierDays[i]));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDaysThrows() {
        Library.fineTier(-1);
    }

    @Test public void valid13Digit()      { assertTrue(Library.validateIsbn("9780306406157")); }
    @Test public void validWithHyphens()  { assertTrue(Library.validateIsbn("978-0-306-40615-7")); }
    @Test public void validWithSpaces()   { assertTrue(Library.validateIsbn("978 0 306 40615 7")); }

    @Test public void emptyRejected()     { assertFalse(Library.validateIsbn("")); }
    @Test public void nullRejected()      { assertFalse(Library.validateIsbn(null)); }
    @Test public void length11Rejected()  { assertFalse(Library.validateIsbn("97803064061")); }
    @Test public void length12Rejected()  { assertFalse(Library.validateIsbn("978030640615")); }
    @Test public void length14Rejected()  { assertFalse(Library.validateIsbn("97803064061577")); }
    @Test public void length15Rejected()  { assertFalse(Library.validateIsbn("978030640615777")); }
    @Test public void letterRejected()    { assertFalse(Library.validateIsbn("978030640615A")); }
    @Test public void symbolRejected()    { assertFalse(Library.validateIsbn("97803064061@7")); }
}
