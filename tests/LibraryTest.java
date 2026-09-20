import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

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

    @Test public void validIsbn()                { assertTrue(Library.validateIsbn("0306406152")); }
    @Test public void validIsbnWithHyphens()     { assertTrue(Library.validateIsbn("0-306-40615-2")); }
    @Test public void validIsbnWithSpaces()      { assertTrue(Library.validateIsbn("0 306 40615 2")); }
    @Test public void validIsbnWithXCheckDigit() { assertTrue(Library.validateIsbn("097522980X")); }
    @Test public void validIsbnWithLowercaseX()  { assertTrue(Library.validateIsbn("097522980x")); }

    @Test public void invalidChecksum()          { assertFalse(Library.validateIsbn("0306406153")); }
    @Test public void tooShort()                 { assertFalse(Library.validateIsbn("12345")); }
    @Test public void tooLong()                  { assertFalse(Library.validateIsbn("12345678901")); }
    @Test public void emptyString()              { assertFalse(Library.validateIsbn("")); }
    @Test public void nonNumeric()               { assertFalse(Library.validateIsbn("abcdefghij")); }
    @Test public void xInMiddle()                { assertFalse(Library.validateIsbn("0306X06152")); }
    @Test public void nullInput()                { assertFalse(Library.validateIsbn(null)); }
}
