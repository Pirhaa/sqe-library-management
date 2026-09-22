import java.util.ArrayList;
import java.util.List;

/**
 * LibraryCatalog — instance-based catalog of books.
 * Added for Lab 7 so we have a mutable object to test.
 */
public class LibraryCatalog {

    private final List<Book> books = new ArrayList<>();

    /** Add a book to the catalog. */
    public void addBook(Book book) {
        books.add(book);
    }

    /** Read-only view of all books. */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * TASK 2 — sum of available books across the catalog.
     * A book is "available" if it is NOT currently issued.
     */
    public int totalAvailableCopies() {
        int count = 0;
        for (Book b : books) {
            if (!b.isIssued()) {
                count++;
            }
        }
        return count;
    }
}
