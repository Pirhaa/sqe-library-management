import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * SCOPE EXPLANATION:
 * - @BeforeClass (MODULE scope): Used for the fine-tier BOUNDARY TABLE,
 *   which is IMMUTABLE read-only data shared across all tests.
 * - @Before (FUNCTION scope): Used to reset the pass/fail counters
 *   before EACH test — counters are MUTABLE state.
 */
public class LibraryBvaTest {

    private static Object[][] boundaryTable;
    private int passCount;
    private int failCount;

    @BeforeClass
    public static void moduleSetup() {
        boundaryTable = new Object[][]{
                {"domain-start", 0,  "None"},
                {"None|Low",     1,  "Low"},
                {"Low-max",      7,  "Low"},
                {"Low|Medium",   8,  "Medium"},
                {"Medium+1",     9,  "Medium"},
                {"Medium-max",   14, "Medium"},
                {"Medium|High",  15, "High"},
                {"High+1",       16, "High"},
                {"High-max",     30, "High"},
                {"High|Severe",  31, "Severe"},
                {"Severe+1",     32, "Severe"}
        };
        System.out.println(">>> @BeforeClass (module scope): " + boundaryTable.length + " rows");
    }

    @Before
    public void freshCounters() {
        passCount = 0;
        failCount = 0;
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDaysThrows() {
        Library.fineTier(-1);
    }

    @Test
    public void allBoundaries() {
        for (Object[] row : boundaryTable) {
            String boundary = (String) row[0];
            int days        = (Integer) row[1];
            String expected = (String) row[2];
            String actual   = Library.fineTier(days);
            System.out.printf("[BVA fineTier] %-18s days=%-4d expected=%-8s actual=%-8s%n",
                    boundary, days, expected, actual);
            assertEquals(boundary, expected, actual);
        }
    }
}
