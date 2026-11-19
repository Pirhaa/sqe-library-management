import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Lab 8 - TDD Implementation (Task 1 & Task 2)
 */
public class BookRanking {

    public static List<Book> rankBooksByPopularity(List<Book> books,
                                                   Map<String, Integer> borrowCounts) {
        // Task 2 GREEN phase: return a copy (not fully generalised yet)
        return new ArrayList<>(books);
    }
}