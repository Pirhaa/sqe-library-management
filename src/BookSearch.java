// BookSearch.java
import java.util.ArrayList;
import java.util.List;

public class BookSearch {
    private List<Book> books;
    
    public BookSearch(List<Book> books) {
        this.books = books;
    }
    
    // Search by title (case-insensitive)
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
    
    // Search by author
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
    
    // Search by book ID (exact match)
    public Book searchById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}
