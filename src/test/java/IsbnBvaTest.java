import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * SCOPE EXPLANATION:
 * - @BeforeClass (MODULE scope): Used for IMMUTABLE shared test data
 *   (here: valid/invalid ISBN arrays). Loaded ONCE for the class.
 * - No @Before needed: Library.validateIsbn() is STATIC with no
 *   mutable state, so a per-test fixture would add only noise.
 */
public class IsbnBvaTest {

    private static String[] validIsbns;
    private static String[] invalidIsbns;

    @BeforeClass
    public static void moduleSetup() {
        validIsbns = new String[]{
                "9780306406157",
                "978-0-306-40615-7",
                "978 0 306 40615 7"
        };
        invalidIsbns = new String[]{
                "97803064061",      // 11 digits
                "978030640615",     // 12 digits
                "97803064061577",   // 14 digits
                "978030640615777",  // 15 digits
                "978030640615A",    // letter
                "97803064061@7",    // symbol
                "",                 // empty
                null                // null
        };
        System.out.println(">>> @BeforeClass (module scope): loaded ISBN fixtures");
    }

    @Test public void valid13Digit()           { assertTrue(Library.validateIsbn(validIsbns[0])); }
    @Test public void validWithHyphens()       { assertTrue(Library.validateIsbn(validIsbns[1])); }
    @Test public void validWithSpaces()        { assertTrue(Library.validateIsbn(validIsbns[2])); }

    @Test public void elevenDigitsRejected()   { assertFalse(Library.validateIsbn(invalidIsbns[0])); }
    @Test public void twelveDigitsRejected()   { assertFalse(Library.validateIsbn(invalidIsbns[1])); }
    @Test public void fourteenDigitsRejected() { assertFalse(Library.validateIsbn(invalidIsbns[2])); }
    @Test public void fifteenDigitsRejected()  { assertFalse(Library.validateIsbn(invalidIsbns[3])); }
    @Test public void letterRejected()         { assertFalse(Library.validateIsbn(invalidIsbns[4])); }
    @Test public void symbolRejected()         { assertFalse(Library.validateIsbn(invalidIsbns[5])); }
    @Test public void emptyRejected()          { assertFalse(Library.validateIsbn(invalidIsbns[6])); }
    @Test public void nullRejected()           { assertFalse(Library.validateIsbn(invalidIsbns[7])); }
}
