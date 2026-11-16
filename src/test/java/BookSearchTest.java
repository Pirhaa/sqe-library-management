import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookSearchTest {

    private BookSearch searchEngine;
    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        bookList = new ArrayList<>();
        bookList.add(new Book("101", "Java Programming", "Ali Khan"));
        bookList.add(new Book("102", "Python Basics", "Sara Ahmed"));
        bookList.add(new Book("103", "Advanced Java", "Ali Khan"));
        
        searchEngine = new BookSearch(bookList);
    }

    @Test
    void testSearchByTitle_FoundAndNotFound() {
        // Case-insensitive match
        List<Book> results = searchEngine.searchByTitle("java");
        assertEquals(2, results.size()); // "Java Programming" and "Advanced Java"

        // Not found
        List<Book> emptyResults = searchEngine.searchByTitle("Ruby");
        assertTrue(emptyResults.isEmpty());
    }

    @Test
    void testSearchByAuthor_FoundAndNotFound() {
        List<Book> results = searchEngine.searchByAuthor("Ali");
        assertEquals(2, results.size());

        List<Book> emptyResults = searchEngine.searchByAuthor("Unknown");
        assertTrue(emptyResults.isEmpty());
    }

    @Test
    void testSearchById_FoundAndNotFound() {
        Book found = searchEngine.searchById("101");
        assertNotNull(found);
        assertEquals("Java Programming", found.getTitle());

        Book notFound = searchEngine.searchById("999");
        assertNull(notFound);
    }
}