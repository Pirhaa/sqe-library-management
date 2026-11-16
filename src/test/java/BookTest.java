import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    void testValidateIsbn_ValidCases() {
        assertTrue(Book.validateIsbn("0306406152"));
        assertTrue(Book.validateIsbn("0-306-40615-2"));
        assertTrue(Book.validateIsbn("097522980X")); // Ending with X
    }

    @Test
    void testValidateIsbn_InvalidCases() {
        assertFalse(Book.validateIsbn(null)); // Null check
        assertFalse(Book.validateIsbn("12345")); // Wrong length
        assertFalse(Book.validateIsbn("0306406153")); // Wrong checksum
        assertFalse(Book.validateIsbn("123456789A")); // Non-digit at end
    }

    @Test
    void testToString() {
        Book book = new Book("101", "Java", "Ali");
        assertTrue(book.toString().contains("Available"));
        
        book.setIssued(true);
        assertTrue(book.toString().contains("Issued"));
    }
}