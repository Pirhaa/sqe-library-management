import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
public class TestRunner {
    static int pass = 0, fail = 0;
    public static void main(String[] args) {
        MemberManagement mm1 = new MemberManagement();
        Member m1 = new Member("R1", "Alice", "alice@mail.com");
        boolean r1 = mm1.addMember(m1);
        report("TC-001", r1 == true);
        Member m1dup = new Member("R1", "Alice2", "alice2@mail.com");
        boolean r2 = mm1.addMember(m1dup);
        report("TC-002", r2 == false);
        Member mBlank = new Member("", "NoRoll", "x@mail.com");
        boolean r3 = mm1.addMember(mBlank);
        report("TC-003", r3 == false);
        BookIssueService svc = new BookIssueService();
        Book b1 = new Book("B1", "Clean Code", "Robert Martin");
        Member m2 = new Member("R2", "Bob", "bob@mail.com");
        boolean r4 = svc.issueBook(b1, m2);
        report("TC-004", r4 == true);

        Member m3 = new Member("R3", "Carl", "carl@mail.com");
        boolean r5 = svc.issueBook(b1, m3);
        report("TC-005", r5 == false);

        boolean r6 = svc.returnBook(b1, m2);
        report("TC-006", r6 == true);

        Book b2 = new Book("B2", "Refactoring", "Martin Fowler");
        Member m4 = new Member("R4", "Dave", "dave@mail.com");
        Member m5 = new Member("R5", "Eve", "eve@mail.com");
        svc.issueBook(b2, m4);
        boolean r7 = svc.returnBook(b2, m5);
        report("TC-007", r7 == false);
        svc.returnBook(b2, m4);

        BookIssueService svc2 = new BookIssueService();
        Member m6 = new Member("R6", "Frank", "frank@mail.com");
        Book[] books4 = new Book[4];
        for (int i = 0; i < 4; i++) {
            books4[i] = new Book("BB" + i, "Book" + i, "Auth" + i);
            svc2.issueBook(books4[i], m6);
        }
        Book fifthBook = new Book("BB5", "FifthBook", "AuthX");
        boolean r8 = svc2.issueBook(fifthBook, m6);
        report("TC-008", r8 == true);

        Book sixthBook = new Book("BB6", "SixthBook", "AuthY");
        boolean r9 = svc2.issueBook(sixthBook, m6);
        report("TC-009", r9 == false);

        BookIssueService svc3 = new BookIssueService();
        Book b10 = new Book("B10", "T1", "A1");
        Member m10 = new Member("R10", "Gina", "gina@mail.com");
        svc3.issueBook(b10, m10);
        svc3.setIssueDateForTest("B10", 0);
        double f10 = svc3.calculateFine("B10");
        report("TC-010", f10 == 0.0);

        Book b11 = new Book("B11", "T2", "A2");
        Member m11 = new Member("R11", "Hank", "hank@mail.com");
        svc3.issueBook(b11, m11);
        svc3.setIssueDateForTest("B11", 20);
        double f11 = svc3.calculateFine("B11");
        report("TC-011", f11 == 12.0);

        Book b12 = new Book("B12", "T3", "A3");
        Member m12 = new Member("R12", "Ivy", "ivy@mail.com");
        svc3.issueBook(b12, m12);
        svc3.setIssueDateForTest("B12", 14);
        double f12 = svc3.calculateFine("B12");
        report("TC-012", f12 == 0.0);

        System.out.println("\n===== SUMMARY =====");
        System.out.println("PASS: " + pass + "  FAIL: " + fail);
    }

    static void report(String id, boolean passed) {
        System.out.println(id + " -> " + (passed ? "PASS" : "FAIL"));
        if (passed) pass++; else fail++;
    }
}
public class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void setIssued(boolean issued) { isIssued = issued; }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

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


public class MemberManagement {
    private Set<String> rollNumbers;
    private List<Member> members;

    public MemberManagement() {
        this.rollNumbers = new HashSet<>();
        this.members = new ArrayList<>();
    }

    public boolean addMember(Member member) {
        String roll = member.getMemberId();

        if (rollNumbers.contains(roll)) {
            System.out.println("ERROR: Roll number " + roll + " already exists!");
            return false;
        }

        rollNumbers.add(roll);
        members.add(member);
        System.out.println(" Member added successfully!");
        return true;
    }

    public boolean isDuplicateRoll(String rollNumber) {
        return rollNumbers.contains(rollNumber);
    }
}

public class BookIssueService {
    private Map<String, String> issuedBooks;
    private Map<String, Integer> issueDate;

    public BookIssueService() {
        this.issuedBooks = new HashMap<>();
        this.issueDate = new HashMap<>();
    }

    public boolean issueBook(Book book, Member member) {
        if (book.isIssued()) {
            System.out.println("Book is already issued!");
            return false;
        }
        if (!member.canBorrow()) {
            System.out.println(" Member reached max books limit!");
            return false;
        }
        book.setIssued(true);
        issuedBooks.put(book.getBookId(), member.getMemberId());
        issueDate.put(book.getBookId(), 0);
        member.borrowBook(book.getBookId());
        System.out.println("Book issued to " + member.getName());
        return true;
    }

    public double calculateFine(String bookId) {
        int daysBorrowed = issueDate.get(bookId);
        int allowedDays = 14;
        double finePerDay = 2.0;
        if (daysBorrowed <= allowedDays) {
            return 0.0;
        }
        return (daysBorrowed - allowedDays) * finePerDay;
    }

    public void setIssueDateForTest(String bookId, int days) {
        issueDate.put(bookId, days);
    }

    public boolean returnBook(Book book, Member member) {
        if (!book.isIssued()) {
            System.out.println("Book is not issued!");
            return false;
        }
        if (!issuedBooks.get(book.getBookId()).equals(member.getMemberId())) {
            System.out.println("Book not issued to this member!");
            return false;
        }
        double fine = calculateFine(book.getBookId());
        if (fine > 0) {
            System.out.println("Fine due: " + fine);
        }
        book.setIssued(false);
        issuedBooks.remove(book.getBookId());
        issueDate.remove(book.getBookId());
        member.returnBook(book.getBookId());
        System.out.println("Book returned successfully!");
        return true;
    }
}
