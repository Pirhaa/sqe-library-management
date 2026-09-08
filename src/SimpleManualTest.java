public class SimpleManualTest {
    public static void main(String[] args) {
        BookIssueService service = new BookIssueService();
        Member member = new Member("M001", "pirha", "pirha@gmail.com");        
        System.out.println(" TEST 1: Member at 3 books borrows 1 more");
        for (int i = 0; i < 3; i++) {
            service.issueBook(new Book("B00" + i, "Book" + i, "Author"), member);
        }
        System.out.println("Before: " + member.getBorrowedBooks().size() + " books"); // 3
        service.issueBook(new Book("B003", "Book3", "Author"), member);
        System.out.println("After: " + member.getBorrowedBooks().size() + " books"); // 4
        System.out.println(" Should be 4: " + (member.getBorrowedBooks().size() == 4 ? "PASS" : "FAIL"));
   
        System.out.println("TEST 2: Member at 5 books attempts 6th");
        Member member2 = new Member("M002", "Alice", "alice@test.com");
        for (int i = 0; i < 5; i++) {
            service.issueBook(new Book("B10" + i, "Book" + i, "Author"), member2);
        }
        System.out.println("Before: " + member2.getBorrowedBooks().size() + " books"); 
        boolean result = service.issueBook(new Book("B105", "Book5", "Author"), member2);
        System.out.println("After: " + member2.getBorrowedBooks().size() + " books"); 
        System.out.println("6th book issued? " + result); 
        System.out.println(" Should be false: " + (!result ? "PASS" : "FAIL"));
        
        System.out.println("\n TEST 3: Member at 3 books attempts 6th");
        Member member3 = new Member("M003", "Bob", "bob@test.com");
        for (int i = 0; i < 3; i++) {
            service.issueBook(new Book("B20" + i, "Book" + i, "Author"), member3);
        }
        System.out.println("Before: " + member3.getBorrowedBooks().size() + " books"); 
        boolean allSuccess = true;
        for (int i = 0; i < 3; i++) {
            if (!service.issueBook(new Book("B20" + (i+3), "Book" + (i+3), "Author"), member3)) {
                allSuccess = false;
            }
        }
        System.out.println("After: " + member3.getBorrowedBooks().size() + " books"); 
        System.out.println("All 3 books issued? " + allSuccess); 
        System.out.println("Should be false (limit 5): " + (!allSuccess ? "PASS" : "FAIL"));
    }
}
