import java.util.HashMap;
import java.util.Map;

public class BookIssueService {
    private Map<String, String> issuedBooks;
    private Map<String, Integer> issueDate;
    
    public BookIssueService() {
        this.issuedBooks = new HashMap<>();
        this.issueDate = new HashMap<>();
    }
    
    public boolean issueBook(Book book, Member member) {
        if (book.isIssued()) {
            System.out.println("❌ Book is already issued!");
            return false;
        }
        if (!member.canBorrow()) {
            System.out.println("❌ Member reached max books limit!");
            return false;
        }
        
        book.setIssued(true);
        issuedBooks.put(book.getBookId(), member.getMemberId());
        issueDate.put(book.getBookId(), 0);
        member.borrowBook(book.getBookId());
        
        System.out.println(" Book issued to " + member.getName());
        return true;
    }
    
    public boolean returnBook(Book book, Member member) {
        if (!book.isIssued()) {
            System.out.println(" Book is not issued!");
            return false;
        }
        if (!issuedBooks.get(book.getBookId()).equals(member.getMemberId())) {
            System.out.println(" Book not issued to this member!");
            return false;
        }
        
        book.setIssued(false);
        issuedBooks.remove(book.getBookId());
        issueDate.remove(book.getBookId());
        member.returnBook(book.getBookId());
        
        System.out.println("Book returned successfully!");
        return true;
    }
}
