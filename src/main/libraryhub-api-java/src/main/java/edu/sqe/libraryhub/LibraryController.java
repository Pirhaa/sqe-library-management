package edu.sqe.libraryhub;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    private final LibraryService service;

    public LibraryController(LibraryService service) { 
        this.service = service; 
    }

    // 1. POST /books
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Map<String, Object> body) {
        String title = text(body, "title");
        String isbn = text(body, "isbn");
        String author = text(body, "author");
        Object copies = body.get("totalCopies");
        if (!(copies instanceof Integer n) || n < 1)
            throw new ApiException(HttpStatus.BAD_REQUEST, "totalCopies must be an integer >= 1");
        return service.addBook(new Book(isbn, title, author, n));
    }

    // 2. GET /books
    @GetMapping("/books")
    public List<Book> allBooks() {
        return service.allBooks();
    }

    // 3. GET /books/{isbn}
    @GetMapping("/books/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return service.getBook(isbn);
    }

    // 4. POST /members/{id}/borrow
    @PostMapping("/members/{id}/borrow")
    public Map<String, Object> borrow(@PathVariable int id, @RequestBody Map<String, String> body) {
        String isbn = body.get("isbn");
        if (isbn == null || isbn.isBlank())
            throw new ApiException(HttpStatus.BAD_REQUEST, "isbn is required");
        Book b = service.borrow(id, isbn);
        return Map.of("isbn", b.getIsbn(), "availableCopies", b.getAvailableCopies());
    }

    // 5. POST /members/{id}/return
    @PostMapping("/members/{id}/return")
    public Map<String, Object> giveBack(@PathVariable int id, @RequestBody Map<String, String> body) {
        String isbn = body.get("isbn");
        if (isbn == null || isbn.isBlank())
            throw new ApiException(HttpStatus.BAD_REQUEST, "isbn is required");
        Book b = service.giveBack(id, isbn);
        return Map.of("isbn", b.getIsbn(), "availableCopies", b.getAvailableCopies());
    }

    // 6. GET /members/{id}/fines
    @GetMapping("/members/{id}/fines")
    public Map<String, Object> fines(@PathVariable int id, @RequestParam int daysOverdue) {
        try {
            String tier = LibraryService.fineTier(daysOverdue);
            return Map.of("memberId", id, "daysOverdue", daysOverdue, "tier", tier);
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private static String text(Map<String, Object> body, String key) {
        Object v = body.get(key);
        if (!(v instanceof String s) || s.isBlank())
            throw new ApiException(HttpStatus.BAD_REQUEST, key + " is required");
        return s;
    }
}