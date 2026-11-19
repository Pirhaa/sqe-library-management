import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Lab 8 - TDD BookRanking Tests (Tasks 1, 2, 3 & 4)
 */
public class BookRankingTest {

    // --- Helpers ---
    private static Book book(String id, String title) {
        return new Book(id, title, "Author");
    }

    private static Map<String, Integer> counts(Object... idCountPairs) {
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < idCountPairs.length; i += 2) {
            m.put((String) idCountPairs[i], (Integer) idCountPairs[i + 1]);
        }
        return m;
    }

    // ==========================================
    // Task 1 (Cycle 1): Empty catalog
    // ==========================================
    @Test
    public void emptyCatalog_returnsEmptyList() {
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            new ArrayList<>(), new HashMap<>());
        assertTrue(ranked.isEmpty());
    }

    // ==========================================
    // Task 2 (Cycle 2): Single book
    // ==========================================
    @Test
    public void singleBook_returnsListWithThatBook() {
        Book b = book("B001", "Clean Code");
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            Arrays.asList(b), counts("B001", 4));
        
        assertEquals(1, ranked.size());
        assertEquals(b, ranked.get(0));
    }

    // ==========================================
    // Task 3 (Cycle 3): Descending Sort
    // ==========================================
    @Test
    public void threeBooks_orderedByBorrowCountDescending() {
        Book a = book("B001", "Clean Code");
        Book b = book("B002", "Refactoring");
        Book c = book("B003", "Pragmatic Programmer");
        
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            Arrays.asList(a, b, c), counts("B001", 3, "B002", 9, "B003", 5));
        
        assertEquals(Arrays.asList(b, c, a), ranked);
    }

    // ==========================================
    // Task 4 (Cycle 4): Tie-Breaking (Alphabetical)
    // ==========================================
    @Test
    public void equalBorrowCounts_sortedAlphabeticallyByTitle() {
        Book zebra = book("B001", "Zen of Python");
        Book apple = book("B002", "Algorithms");
        
        
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            Arrays.asList(zebra, apple), counts("B001", 5, "B002", 5));
            
        assertEquals(Arrays.asList(apple, zebra), ranked);
    }
}