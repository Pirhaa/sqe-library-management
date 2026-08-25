// Member.java
import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private List<String> borrowedBooks;
    private int maxBooksAllowed;
    
    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        this.maxBooksAllowed = 5;
    }
    
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }
    
    public boolean canBorrow() {
        return borrowedBooks.size() < maxBooksAllowed;
    }
    
    public void borrowBook(String bookId) {
        if (canBorrow()) {
            borrowedBooks.add(bookId);
        }
    }
    
    public void returnBook(String bookId) {
        borrowedBooks.remove(bookId);
    }
    
    @Override
    public String toString() {
        return memberId + " | " + name + " | " + email + " | Books: " + borrowedBooks.size();
    }
}
