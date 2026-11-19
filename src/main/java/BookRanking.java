import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Lab 8 - TDD Implementation (Tasks 1, 2 & 3)
 */
public class BookRanking {

    public static List<Book> rankBooksByPopularity(List<Book> books,
                                                   Map<String, Integer> borrowCounts) {
        // Task 1 & 2: Create a new list (copy of input)
        List<Book> ranked = new ArrayList<>(books);
        
        // Task 3: Sort by borrow count in descending order
        ranked.sort((x, y) -> borrowCounts.get(y.getBookId()) - borrowCounts.get(x.getBookId()));
        
        return ranked;
    }
}