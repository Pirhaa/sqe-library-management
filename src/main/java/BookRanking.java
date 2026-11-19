import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Lab 8 - TDD Implementation (Final for Tasks 1 to 5)
 */
public class BookRanking {

    public static List<Book> rankBooksByPopularity(List<Book> books,
                                                   Map<String, Integer> borrowCounts) {
        List<Book> ranked = new ArrayList<>(books);
        
        // Task 5 GREEN phase: use getOrDefault() to avoid NPE
        ranked.sort(Comparator
            .comparing((Book b) -> borrowCounts.getOrDefault(b.getBookId(), 0)).reversed()
            .thenComparing(Book::getTitle));
            
        return ranked;
    }
}