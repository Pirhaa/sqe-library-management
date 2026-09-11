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
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    public String toString() {
        return bookId + " | " + title + " | " + author + " | " +
               (isIssued ? "Issued" : "Available");
    }

    // ISBN validation
    public static boolean validateIsbn(String isbn) {
        if (isbn == null) return false;
        isbn = isbn.replace("-", "").trim();
        if (isbn.length() != 10) return false;

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char c = isbn.charAt(i);
            int value;
            if (i == 9 && (c == 'X' || c == 'x')) value = 10;
            else if (Character.isDigit(c))        value = c - '0';
            else                                   return false;
            sum = sum + value * (10 - i);
        }
        return sum % 11 == 0;
    }

    // ---------- Test Helper ----------
    static int passed = 0, failed = 0;

    static void check(String name, boolean condition) {
        if (condition) { System.out.println("PASS : " + name); passed++; }
        else           { System.out.println("FAIL : " + name); failed++; }
    }

    // ---------- Main — 5 Tests ----------
    public static void main(String[] args) {
        System.out.println("===== Book Tests =====\n");

        Book b = new Book("B001", "Clean Code", "Robert Martin");

        check("Test 1: bookId is B001",     b.getBookId().equals("B001"));
        check("Test 2: title is Clean Code", b.getTitle().equals("Clean Code"));
        check("Test 3: valid ISBN",         validateIsbn("0306406152"));
        check("Test 4: invalid ISBN",      !validateIsbn("12345"));
        check("Test 5: bookId is B999",     b.getBookId().equals("B999")); // FAIL

        System.out.println("\n----- Summary -----");
        System.out.println("Passed : " + passed);
        System.out.println("Failed : " + failed);
        System.out.println("Total  : " + (passed + failed));
    }
}
