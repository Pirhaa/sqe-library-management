import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Lab 7 - Task 2: totalAvailableCopies() tests.
 *
 * FIXTURE PATTERN:
 * - @Before (FUNCTION scope): creates a FRESH LibraryCatalog before
 *   EVERY test. Guarantees test isolation — no state leaks.
 *
 * Each test prints a self-explanatory line showing:
 *   setup -> action -> expected vs actual result
 */
public class LibraryCatalogTest {

    // STEP 1: Field — every test will use it
    private LibraryCatalog catalog;

    // STEP 2: Fixture — fresh empty catalog
    @Before
    public void freshCatalog() {
        catalog = new LibraryCatalog();
        System.out.println("\n--- Fixture: created fresh empty LibraryCatalog ---");
    }

 
    private void log(String testName, String arrange, String action,
                     int expected, int actual) {
        System.out.printf(
            "[Task 2] %-40s | arrange=%-30s | action=%-25s | expected=%-3d actual=%-3d -> %s%n",
            testName, arrange, action, expected, actual,
            expected == actual ? "PASS" : "FAIL"
        );
    }


    @Test
    public void totalAvailableCopies_emptyCatalog_returnsZero() {
        // Arrange — fixture gives us an empty catalog
        String arrange = "empty catalog";
        // Act
        int actual = catalog.totalAvailableCopies();
        // Assert
        int expected = 0;
        log("emptyCatalog_returnsZero", arrange,
            "totalAvailableCopies()", expected, actual);
        assertEquals("Empty catalog -> 0", expected, actual);
    }

    @Test
    public void totalAvailableCopies_singleBook_returnsOne() {
        // Arrange — add one book
        catalog.addBook(new Book("B100", "Solo Book", "Author X"));
        String arrange = "1 book added";
        // Act
        int actual = catalog.totalAvailableCopies();
        // Assert
        int expected = 1;
        log("singleBook_returnsOne", arrange,
            "totalAvailableCopies()", expected, actual);
        assertEquals("1 book -> 1 available", expected, actual);
    }

    @Test
    public void totalAvailableCopies_multipleBooks_returnsThree() {
        // Arrange — add three books
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
        String arrange = "3 books added";
        // Act
        int actual = catalog.totalAvailableCopies();
        // Assert
        int expected = 3;
        log("multipleBooks_returnsThree", arrange,
            "totalAvailableCopies()", expected, actual);
        assertEquals("3 books -> 3 available", expected, actual);
    }

    @Test
    public void totalAvailableCopies_afterIssuingBook_returnsTwo() {
        // Arrange — add three books, then issue one
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
        catalog.getBooks().get(0).setIssued(true);  // issue the first book
        String arrange = "3 books, 1 issued";
        // Act
        int actual = catalog.totalAvailableCopies();
        // Assert
        int expected = 2;
        log("afterIssuingBook_returnsTwo", arrange,
            "totalAvailableCopies()", expected, actual);
        assertEquals("3 books, 1 issued -> 2 available", expected, actual);
    }
}
