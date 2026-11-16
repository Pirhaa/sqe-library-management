import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookIssueServiceTest {

    private BookIssueService issueService;
    private Book book;
    private Member member;

    @BeforeEach
    void setUp() {
        issueService = new BookIssueService();
        book = new Book("201", "Test Book", "Author");
        
        member = new Member("M1", "John Doe", "03001234567"); 
    }

    @Test
    void testIssueBook_Success() {
        // Arrange & Act
        boolean result = issueService.issueBook(book, member);
        
        // --- ORIGINAL ASSERTIONS ---
        assertTrue(result);
        assertTrue(book.isIssued(), "Book should be marked as issued");
        
        // --- 🚀 TASK 4: STRENGTHENED ASSERTION (Closing the Coverage Trap) ---
        // We prove the book is issued to THIS specific member by successfully 
        // returning it. If it were issued to someone else (or not linked), this would fail.
        boolean returnResult = issueService.returnBook(book, member);
        assertTrue(returnResult, "Book should be returned successfully by the same member");
        
        // Verify final state
        assertFalse(book.isIssued(), "Book should be available again after return");
    }

    @Test
    void testIssueBook_AlreadyIssued_Fails() {
        book.setIssued(true); // Make it already issued
        boolean result = issueService.issueBook(book, member);
        assertFalse(result, "Should not issue a book that is already issued"); 
    }

    @Test
    void testReturnBook_Success() {
        issueService.issueBook(book, member); // Issue first
        boolean result = issueService.returnBook(book, member);
        
        assertTrue(result, "Book return should succeed for the correct member");
        assertFalse(book.isIssued(), "Book should be available again");
    }

    @Test
    void testReturnBook_NotIssued_Fails() {
        // Book is not issued initially
        boolean result = issueService.returnBook(book, member);
        assertFalse(result, "Should not return a book that was never issued");
    }

    @Test
    void testReturnBook_WrongMember_Fails() {
        issueService.issueBook(book, member); // Issued to member1
        
        Member otherMember = new Member("M3", "Jane Doe", "03007654321");
        boolean result = issueService.returnBook(book, otherMember);
        
        assertFalse(result, "Should fail because book was issued to a different member");
    }

    @Test
    void testCalculateFine() {
        issueService.issueBook(book, member);
        
        // Initially, 0 days borrowed (should be 0 fine because allowedDays is 14)
        assertEquals(0.0, issueService.calculateFine("201"), "No fine for returning within 14 days");
    }
}