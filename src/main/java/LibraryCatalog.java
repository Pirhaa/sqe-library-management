import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    /**
     * Task 2 - sum of available books in the catalog.
     */
    public int totalAvailableCopies() {
        int count = 0;
        for (Book b : books) {
            if (!b.isIssued()) count++;
        }
        return count;
    }

    /**
     * Task 4 - borrow a book by its ID.
     * Returns true if borrowed successfully, false otherwise.
     */
    public boolean borrowBook(String bookId) {
        if (bookId == null) return false;
        for (Book b : books) {
            if (b.getBookId().equals(bookId)) {
                if (b.isIssued()) return false;
                b.setIssued(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Task 3 - write the catalog to a file.
     * Wraps IOException into LibraryIOException.
     */
    public void exportCatalog(String path) throws LibraryIOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Book b : books) {
                writer.write(b.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new LibraryIOException("Failed to export catalog to " + path, e);
        }
    }
}
