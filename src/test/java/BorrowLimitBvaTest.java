import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BorrowLimitBvaTest {

    @Test
    public void fourBooks_canBorrowOneMore() {
        Member m = new Member("S001", "Ali", "ali@test.com");
        m.getBorrowedBooks().add("B1");
        m.getBorrowedBooks().add("B2");
        m.getBorrowedBooks().add("B3");
        m.getBorrowedBooks().add("B4");

        int count = m.getBorrowedBooks().size();
        boolean actual = m.canBorrow();
        System.out.printf(
            "[BVA borrow] preload=%d | canBorrow()=%s | expected=true%n",
            count, actual);

        assertTrue(
            "Member with 4 books (below max) SHOULD be allowed to borrow a 5th book",
            actual);
    }

    // ================================================================
    // Boundary 2: 5 books on loan -> AT the max
    // Expected (per Lab 5 rule 0-5 valid): canBorrow() should STILL return true
    // THIS TEST IS EXPECTED TO FAIL -> reveals the off-by-one defect
    // ================================================================
    @Test
    public void fiveBooks_canBorrowOneMore() {
        Member m = new Member("S002", "Sara", "sara@test.com");
        m.getBorrowedBooks().add("B1");
        m.getBorrowedBooks().add("B2");
        m.getBorrowedBooks().add("B3");
        m.getBorrowedBooks().add("B4");
        m.getBorrowedBooks().add("B5");

        int count = m.getBorrowedBooks().size();
        boolean actual = m.canBorrow();
        System.out.printf(
            "[BVA borrow] preload=%d | canBorrow()=%s | expected=true%n",
            count, actual);

        assertTrue(
            "Member with 5 books (AT max per Lab 5: 0-5 valid) SHOULD be allowed to borrow a 6th book",
            actual);
    }

    // ================================================================
    // Boundary 3: 6 books on loan -> one OVER the max
    // Expected: canBorrow() should return false
    // ================================================================
    @Test
    public void sixBooks_cannotBorrowOneMore() {
        Member m = new Member("S003", "Hina", "hina@test.com");
        m.getBorrowedBooks().add("B1");
        m.getBorrowedBooks().add("B2");
        m.getBorrowedBooks().add("B3");
        m.getBorrowedBooks().add("B4");
        m.getBorrowedBooks().add("B5");
        m.getBorrowedBooks().add("B6");

        int count = m.getBorrowedBooks().size();
        boolean actual = m.canBorrow();
        System.out.printf(
            "[BVA borrow] preload=%d | canBorrow()=%s | expected=false%n",
            count, actual);

        assertFalse(
            "Member with 6 books (OVER max) should NOT be allowed to borrow a 7th book",
            actual);
    }
}