import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Lab 6 - Task 2
 * Boundary Value Analysis tests for Library.fineTier()

 */
public class LibraryBvaTest {

    /** Helper: prints a boundary line so output is self-explanatory. */
    private void log(String boundary, int days, String expected, String actual, boolean pass) {
        System.out.printf(
            "[BVA fineTier] boundary=%-18s days=%-4d expected=%-8s actual=%-8s -> %s%n",
            boundary, days, expected, actual, pass ? "PASS" : "FAIL"
        );
    }

 
    @Test(expected = IllegalArgumentException.class)
    public void boundary_minus1_shouldThrow() {
        System.out.println("[BVA fineTier] boundary=domain-lower  days=-1   expected=Exception");
        Library.fineTier(-1);
    }

    @Test
    public void boundary_0_shouldReturnNone() {
        String actual = Library.fineTier(0);
        log("domain-start", 0, "None", actual, "None".equals(actual));
        assertEquals("0 days = no fine tier (domain start)", "None", actual);
    }

    @Test
    public void boundary_1_shouldReturnLow() {
        String actual = Library.fineTier(1);
        log("None|Low", 1, "Low", actual, "Low".equals(actual));
        assertEquals("1 day = first value of Low tier", "Low", actual);
    }

   
    @Test
    public void boundary_7_shouldReturnLow() {
        String actual = Library.fineTier(7);
        log("Low-max", 7, "Low", actual, "Low".equals(actual));
        assertEquals("7 days = last value still in Low", "Low", actual);
    }

    @Test
    public void boundary_8_shouldReturnMedium() {
        String actual = Library.fineTier(8);
        log("Low|Medium", 8, "Medium", actual, "Medium".equals(actual));
        assertEquals("8 days = first value of Medium", "Medium", actual);
    }

    @Test
    public void boundary_9_shouldReturnMedium() {
        String actual = Library.fineTier(9);
        log("Medium+1", 9, "Medium", actual, "Medium".equals(actual));
        assertEquals("9 days = just inside Medium", "Medium", actual);
    }


    @Test
    public void boundary_14_shouldReturnMedium() {
        String actual = Library.fineTier(14);
        log("Medium-max", 14, "Medium", actual, "Medium".equals(actual));
        assertEquals("14 days = last value still in Medium", "Medium", actual);
    }

    @Test
    public void boundary_15_shouldReturnHigh() {
        String actual = Library.fineTier(15);
        log("Medium|High", 15, "High", actual, "High".equals(actual));
        assertEquals("15 days = first value of High tier", "High", actual);
    }

    @Test
    public void boundary_16_shouldReturnHigh() {
        String actual = Library.fineTier(16);
        log("High+1", 16, "High", actual, "High".equals(actual));
        assertEquals("16 days = just inside High", "High", actual);
    }


    @Test
    public void boundary_30_shouldReturnHigh() {
        String actual = Library.fineTier(30);
        log("High-max", 30, "High", actual, "High".equals(actual));
        assertEquals("30 days = last value still in High", "High", actual);
    }

    @Test
    public void boundary_31_shouldReturnSevere() {
        String actual = Library.fineTier(31);
        log("High|Severe", 31, "Severe", actual, "Severe".equals(actual));
        assertEquals("31 days = first value of Severe tier", "Severe", actual);
    }

    @Test
    public void boundary_32_shouldReturnSevere() {
        String actual = Library.fineTier(32);
        log("Severe+1", 32, "Severe", actual, "Severe".equals(actual));
        assertEquals("32 days = just inside Severe", "Severe", actual);
    }
}
