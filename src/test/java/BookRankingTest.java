import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Lab 8 - TDD BookRanking Tests
 */
public class BookRankingTest {

    // ==========================================
    // Task 1 (Cycle 1): Empty catalog returns empty list
    // ==========================================
    @Test
    public void emptyCatalog_returnsEmptyList() {
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            new ArrayList<>(), new HashMap<>());
        assertTrue(ranked.isEmpty());
    }

    // ==========================================
    // Task 2 (Cycle 2): Single book returns list with that book
    // ==========================================
    @Test
    public void singleBook_returnsListWithThatBook() {
        Book b = new Book("B001", "Clean Code", "R. Martin");
        Map<String, Integer> counts = new HashMap<>();
        counts.put("B001", 4);
        
        List<Book> ranked = BookRanking.rankBooksByPopularity(Arrays.asList(b), counts);
        
        assertEquals(1, ranked.size());
        assertEquals(b, ranked.get(0));
    }
}