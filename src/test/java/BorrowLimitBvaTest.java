import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Task 1: Fixture Refactor
 * 
 * SCOPE EXPLANATION:
 * - @BeforeClass (MODULE scope): Use for EXPENSIVE, immutable setup 
 *   shared across all tests (e.g. DB connection, config load).
 *   Runs ONCE per test class.
 * - @Before (FUNCTION scope, default): Use when each test needs a 
 *   FRESH mutable object. Runs before EVERY test.
 */
public class BorrowLimitBvaTest {

    private Member member;

    @BeforeClass
    public static void moduleSetup() {
        System.out.println(">>> module scope setup: runs once");
    }

    @Before
    public void freshMember() {
        member = new Member("S001", "Ali", "ali@test.com");
    }

    private void preloadBooks(int n) {
        for (int i = 1; i <= n; i++) {
            member.getBorrowedBooks().add("B" + i);
        }
    }

    @Test
    public void fourBooks_canBorrowOneMore() {
        preloadBooks(4);
        assertTrue(member.canBorrow());
    }

    @Test
    public void fiveBooks_canBorrowOneMore() {
        preloadBooks(5);
        assertTrue(member.canBorrow());
    }

    @Test
    public void sixBooks_cannotBorrowOneMore() {
        preloadBooks(6);
        assertFalse(member.canBorrow());
    }
}
