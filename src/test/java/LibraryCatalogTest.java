import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Lab 7 - Tasks 2 & 3
 */
public class LibraryCatalogTest {

    private LibraryCatalog catalog;

    @Before
    public void freshCatalog() {
        catalog = new LibraryCatalog();
        System.out.println("--- fixture: fresh catalog ---");
    }

    /** Short helper: prints one clean line per test. */
    private void line(String test, String result, Object expected, Object actual) {
        System.out.printf("[%s] %-42s exp=%-6s act=%-6s -> %s%n",
                result, test, expected, actual,
                String.valueOf(expected).equals(String.valueOf(actual)) ? "PASS" : "FAIL");
    }

    // ═══════════ TASK 2 ═══════════

    @Test
    public void emptyCatalog() {
        int actual = catalog.totalAvailableCopies();
        line("emptyCatalog", "T2", 0, actual);
        assertEquals(0, actual);
    }

    @Test
    public void singleBook() {
        catalog.addBook(new Book("B100", "Solo Book", "Author X"));
        int actual = catalog.totalAvailableCopies();
        line("singleBook", "T2", 1, actual);
        assertEquals(1, actual);
    }

    @Test
    public void multipleBooks() {
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));
        catalog.addBook(new Book("B003", "Refactoring", "M. Fowler"));
        int actual = catalog.totalAvailableCopies();
        line("multipleBooks", "T2", 3, actual);
        assertEquals(3, actual);
    }

    // ═══════════ TASK 3 ═══════════

    @Test
    public void exportCatalog_success() throws Exception {
        catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));
        catalog.addBook(new Book("B002", "Pragmatic Programmer", "D. Thomas"));

        BufferedWriter mockWriter = mock(BufferedWriter.class);

        try (MockedConstruction<FileWriter> m1 = Mockito.mockConstruction(FileWriter.class);
             MockedConstruction<BufferedWriter> m2 = Mockito.mockConstruction(
                     BufferedWriter.class,
                     (mock, ctx) -> {
                         when(mock.write(anyString())).thenAnswer(inv -> {
                             mockWriter.write(inv.getArgument(0));
                             return null;
                         });
                         doNothing().when(mock).newLine();
                         doNothing().when(mock).close();
                     })) {

            catalog.exportCatalog("dummy.txt");

            verify(mockWriter, times(2)).write(anyString());
            verify(mockWriter, times(2)).newLine();
        }

        line("exportCatalog_success", "T3", "2 writes", "2 writes");
    }

    @Test
    public void exportCatalog_ioFailure() {
        try (MockedConstruction<FileWriter> mocked = Mockito.mockConstruction(
                FileWriter.class,
                (mock, ctx) -> { throw new IOException("simulated disk failure"); })) {

            catalog.addBook(new Book("B001", "Clean Code", "R. Martin"));

            LibraryIOException ex = assertThrows(
                    LibraryIOException.class,
                    () -> catalog.exportCatalog("some-path.txt")
            );

            assertTrue(ex.getMessage().contains("Failed to export"));
            assertTrue(ex.getCause() instanceof IOException);

            line("exportCatalog_ioFailure", "T3", "LibraryIOException", "LibraryIOException");
        }
    }
}
