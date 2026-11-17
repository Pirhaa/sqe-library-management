package edu.sqe.libraryhub;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    public static final int MAX_LOANS = 5;
    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<Integer, List<String>> loans = new HashMap<>();

    public synchronized Book addBook(Book book) {
        if (books.containsKey(book.getIsbn()))
            throw new ApiException(HttpStatus.CONFLICT, "ISBN already exists");
        books.put(book.getIsbn(), book);
        return book;
    }

    public synchronized List<Book> allBooks() {
        return new ArrayList<>(books.values());
    }

    public synchronized Book getBook(String isbn) {
        Book b = books.get(isbn);
        if (b == null)
            throw new ApiException(HttpStatus.NOT_FOUND, "Unknown ISBN: " + isbn);
        return b;
    }

    public synchronized Book borrow(int memberId, String isbn) {
        Book b = getBook(isbn);
        List<String> mine = loans.computeIfAbsent(memberId, k -> new ArrayList<>());
        if (b.getAvailableCopies() == 0)
            throw new ApiException(HttpStatus.BAD_REQUEST, "No copies available");
        if (mine.size() >= MAX_LOANS)
            throw new ApiException(HttpStatus.BAD_REQUEST, "Loan limit of 5 reached");
        mine.add(isbn);
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        return b;
    }

    public synchronized Book giveBack(int memberId, String isbn) {
        Book b = getBook(isbn);
        List<String> mine = loans.getOrDefault(memberId, new ArrayList<>());
        if (!mine.remove(isbn))
            throw new ApiException(HttpStatus.BAD_REQUEST, "Book is not on loan to this member");
        b.setAvailableCopies(b.getAvailableCopies() + 1);
        return b;
    }

    public static String fineTier(int daysOverdue) {
        if (daysOverdue < 0) throw new IllegalArgumentException("daysOverdue cannot be negative");
        if (daysOverdue == 0) return "None";
        if (daysOverdue <= 7) return "Low";
        if (daysOverdue <= 14) return "Medium";
        if (daysOverdue <= 30) return "High";
        return "Severe";
    }

    synchronized void reset() {   // for tests only
        books.clear();
        loans.clear();
    }
}
