import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Lab 7 - Tasks 2 & 3
 * - Task 2: totalAvailableCopies() tests (fixture pattern)
 * - Task 3: exportCatalog() mocking tests (success + failure)
 */
public class LibraryCatalogTest {

    private LibraryCatalog catalog;

    @Before
    public void freshCatalog() {
        catalog = new LibraryCatalog();
        System.out.println("--- fixture: fresh catalog ---");
    }

    /** Short helper: prints one clean line per test. */
    private void line(String task, String test, Object expected, Object actual) {
        System.out.printf("[%s] %-28s exp=%-10s act=%-10s -> %s%n",
                task, test, expected, actual,
                String.valueOf(expected).equals(String.valueOf(actual)) ? "PASS" : "FAIL");
    }

    // ══════════════════════════════════════════════════════════════
    //  TASK 2 — 3 tests for totalAvailableCopies()
    // ══════════════════════════════════════════════════════════════

    @Test
    public void emptyCatalog() {
        int actual = catalog.totalAvailableCopies();
        line("T2", "emptyCatalog", 0, actual);
        assertEquals(0, actual);
    }

    @Test
    public void singleBook() {
        catalog.addBook(new Book("B100", "Solo Book", "Author X"));
        int actual = catalog.totalAvailableCopies();
        line("T2", "singleBook", 1, actual);
        assertEquals(1, actual);
    }

    @Test
    public void multipleBooks() {
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
        int actual = catalog.totalAvailableCopies();
        line("T2", "multipleBooks", 3, actual);
        assertEquals(3, actual);
    }

    // ══════════════════════════════════════════════════════════════
    //  TASK 3 — Mocking File I/O
    // ══════════════════════════════════════════════════════════════

    /**
     * TASK 3 (Test 1) — Success path.
     * Mock BufferedWriter + FileWriter so no real file is written.
     * Verify write() and newLine() were called twice each (2 books).
     */
    @Test
    public void exportCatalog_success() throws Exception {
        // Arrange — add 2 books
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));

        try (MockedConstruction<FileWriter> m1 = Mockito.mockConstruction(FileWriter.class);
             MockedConstruction<BufferedWriter> m2 = Mockito.mockConstruction(
                     BufferedWriter.class,
                     (mock, ctx) -> {
                         doNothing().when(mock).write(anyString());
                         doNothing().when(mock).newLine();
                         doNothing().when(mock).close();
                     })) {

            // Act
            catalog.exportCatalog("dummy.txt");

            // Assert — BufferedWriter was constructed exactly once
            assertEquals(1, m2.constructed().size());

            // Verify write() + newLine() were called twice each
            BufferedWriter created = m2.constructed().get(0);
            verify(created, times(2)).write(anyString());
            verify(created, times(2)).newLine();
        }

        line("T3", "exportCatalog_success", "2 writes", "2 writes");
    }

    /**
     * TASK 3 (Test 2) — Failure path.
     * Mock FileWriter construction to throw IOException (Python's OSError).
     * Assert exportCatalog wraps it in LibraryIOException.
     */
    @Test
    public void exportCatalog_ioFailure() {
        try (MockedConstruction<FileWriter> mocked = Mockito.mockConstruction(
                FileWriter.class,
                (mock, ctx) -> { throw new IOException("simulated disk failure"); })) {

            catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));

            // Act + Assert
            LibraryIOException ex = assertThrows(
                    LibraryIOException.class,
                    () -> catalog.exportCatalog("some-path.txt")
            );

            assertTrue(ex.getMessage().contains("Failed to export"));
            assertTrue(ex.getCause() instanceof IOException);

            line("T3", "exportCatalog_ioFailure",
                    "LibraryIOException", "LibraryIOException");
        }
    }
}