import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Lab 7 - Task 4
 * Parametrized edge-case sweep for borrowBook().
 * 7 cases in one test method (previously 7 separate tests).
 */
@RunWith(Parameterized.class)
public class LibraryCatalogParamTest {

    private LibraryCatalog catalog;
    private String bookId;
    private boolean expected;

    public LibraryCatalogParamTest(String bookId, boolean expected) {
        this.bookId = bookId;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "case {index}: id=''{0}'' -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"B001",  true},
                {"B002",  true},
                {"B003",  true},
                {"B999",  false},
                {"",      false},
                {"b001",  false},
                {"B0001", false}
        });
    }

    @Before
    public void setUp() {
        catalog = new LibraryCatalog();
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
    }

    @Test
    public void borrowBook_edgeCases() {
        boolean actual = catalog.borrowBook(bookId);
        System.out.printf(
            "[Task 4] bookId=%-6s | expected=%-5s | actual=%-5s | %s%n",
            "'" + bookId + "'", expected, actual,
            (expected == actual) ? "PASS" : "FAIL"
        );
        assertEquals(expected, actual);
    }
}
