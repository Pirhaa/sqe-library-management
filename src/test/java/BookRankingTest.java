import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Lab 8 - Task 1 (Cycle 1): Empty catalog returns empty list
 * RED phase: BookRanking class does not exist yet -> compilation fails.
 */
public class BookRankingTest {

    @Test
    public void emptyCatalog_returnsEmptyList() {
        List<Book> ranked = BookRanking.rankBooksByPopularity(
            new ArrayList<>(), new HashMap<>());
        assertTrue(ranked.isEmpty());
    }
}