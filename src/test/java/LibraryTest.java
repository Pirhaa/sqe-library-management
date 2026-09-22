import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

    // ================= fineTier =================

    @Test public void zeroDaysIsNone()        { assertEquals("None",   Library.fineTier(0)); }
    @Test public void oneDayIsLow()           { assertEquals("Low",    Library.fineTier(1)); }
    @Test public void sevenDaysIsLow()        { assertEquals("Low",    Library.fineTier(7)); }
    @Test public void eightDaysIsMedium()     { assertEquals("Medium", Library.fineTier(8)); }
    @Test public void fourteenDaysIsMedium()  { assertEquals("Medium", Library.fineTier(14)); }
    @Test public void fifteenDaysIsHigh()     { assertEquals("High",   Library.fineTier(15)); }
    @Test public void thirtyDaysIsHigh()      { assertEquals("High",   Library.fineTier(30)); }
    @Test public void thirtyOneDaysIsSevere() { assertEquals("Severe", Library.fineTier(31)); }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDaysThrows()          { Library.fineTier(-1); }

    // ================= validateIsbn  =================

    @Test public void valid13DigitIsbn()        { assertTrue(Library.validateIsbn("9780306406157")); }
    @Test public void valid13DigitWithHyphens() { assertTrue(Library.validateIsbn("978-0-306-40615-7")); }
    @Test public void valid13DigitWithSpaces()  { assertTrue(Library.validateIsbn("978 0 306 40615 7")); }

    @Test public void emptyString()             { assertFalse(Library.validateIsbn("")); }
    @Test public void nullInput()               { assertFalse(Library.validateIsbn(null)); }
    @Test public void length12Invalid()         { assertFalse(Library.validateIsbn("978030640615")); }
    @Test public void length11Invalid()         { assertFalse(Library.validateIsbn("97803064061")); }
    @Test public void length14Invalid()         { assertFalse(Library.validateIsbn("97803064061577")); }
    @Test public void length15Invalid()         { assertFalse(Library.validateIsbn("978030640615777")); }
    @Test public void containsLetter()          { assertFalse(Library.validateIsbn("978030640615A")); }
    @Test public void containsSymbol()          { assertFalse(Library.validateIsbn("97803064061@7")); }
}
