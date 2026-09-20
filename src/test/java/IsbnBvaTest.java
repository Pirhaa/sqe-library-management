import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Lab 6 - Task 4
 * Boundary Value Analysis tests for Library.validateIsbn().
 *
 * Rule: valid ISBN = exactly 13 numeric digits (hyphens/spaces stripped).
 * Boundaries: 11, 12, 13, 14, 15 digits.
 */
public class IsbnBvaTest {

    // 11 digits -> below minimum, must be rejected
    @Test
    public void elevenDigits_shouldBeRejected() {
        assertFalse(Library.validateIsbn("97803064061"));
    }

    // 12 digits -> one below minimum, must be rejected
    @Test
    public void twelveDigits_shouldBeRejected() {
        assertFalse(Library.validateIsbn("978030640615"));
    }

    // 13 digits -> exactly at boundary, must be accepted
    @Test
    public void thirteenDigits_shouldBeAccepted() {
        assertTrue(Library.validateIsbn("9780306406157"));
    }

    // 14 digits -> one above maximum, must be rejected
    @Test
    public void fourteenDigits_shouldBeRejected() {
        assertFalse(Library.validateIsbn("97803064061577"));
    }

    // 15 digits -> above maximum, must be rejected
    @Test
    public void fifteenDigits_shouldBeRejected() {
        assertFalse(Library.validateIsbn("978030640615777"));
    }

    // null input -> domain edge, must be rejected
    @Test
    public void nullInput_shouldBeRejected() {
        assertFalse(Library.validateIsbn(null));
    }

    // empty input -> domain edge, must be rejected
    @Test
    public void emptyInput_shouldBeRejected() {
        assertFalse(Library.validateIsbn(""));
    }
}