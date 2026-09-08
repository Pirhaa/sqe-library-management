public class SimpleManualTest {
    public static void main(String[] args) {
        BookIssueService s = new BookIssueService();
        Member m1 = new Member("1", "Pirha", "pirha@test.com");
        System.out.println("\n TEST 1: Member with 3 books borrows 1 more");
        for (int i = 0; i < 3; i++) {
            s.issueBook(new Book("B"+i, "Book "+i, "Author"), m1);
        }
        System.out.println("Before: " + m1.getBorrowedBooks().size() + " books");
        s.issueBook(new Book("B3", "Book 3", "Author"), m1);
        System.out.println("After: " + m1.getBorrowedBooks().size() + " books \n");
        
       
        Member m2 = new Member("2", "Pirha", "pirha@test.com");
        System.out.println(" TEST 2: Member with 5 books tries 6th");
        for (int i = 0; i < 5; i++) {
            s.issueBook(new Book("A"+i, "Book "+i, "Author"), m2);
        }
        System.out.println("Before: " + m2.getBorrowedBooks().size() + " books (MAX LIMIT)");
        
        boolean result = s.issueBook(new Book("A5", "Book 5", "Author"), m2);
        System.out.println("After: " + m2.getBorrowedBooks().size() + " books");
        System.out.println("6th book issued? " + result);
        System.out.println(result ? " FAIL - Should be blocked!" : " PASS - Blocked successfully!\n");
        
        System.out.println("ALL TESTS DONE!");
    }
}