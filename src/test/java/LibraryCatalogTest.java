import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Lab 7 - Task 2: totalAvailableCopies() tests.
 *
 * FIXTURE PATTERN:
 * - @Before (FUNCTION scope): creates a FRESH LibraryCatalog before
 *   EVERY test. Guarantees test isolation — no state leaks.
 */
public class LibraryCatalogTest {

    // STEP 1: Field — every test will use it
    private LibraryCatalog catalog;

    // STEP 2: Fixture — empty catalog before every test
    @Before
    public void freshCatalog() {
        catalog = new LibraryCatalog();
    }

    @Test
    public void totalAvailableCopies_emptyCatalog_returnsZero() {
        // Arrange: fixture already gives an empty catalog
        // Act
        int result = catalog.totalAvailableCopies();
        // Assert
        assertEquals("Empty catalog -> 0", 0, result);
    }

    @Test
    public void totalAvailableCopies_singleBook_returnsOne() {
        // Arrange: add one book to the fixture
        catalog.addBook(new Book("B100", "Solo Book", "Author X"));
        // Act
        int result = catalog.totalAvailableCopies();
        // Assert
        assertEquals("1 book -> 1 available", 1, result);
    }

    @Test
    public void totalAvailableCopies_multipleBooks_returnsThree() {
        // Arrange: add three books
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
        // Act
        int result = catalog.totalAvailableCopies();
        // Assert
        assertEquals("3 books -> 3 available", 3, result);
    }
}
