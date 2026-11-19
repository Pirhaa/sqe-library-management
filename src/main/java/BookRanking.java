import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Lab 8 - TDD Implementation (Tasks 1, 2, 3 & 4)
 */
public class BookRanking {

    public static List<Book> rankBooksByPopularity(List<Book> books,
                                                   Map<String, Integer> borrowCounts) {
        List<Book> ranked = new ArrayList<>(books);
        
        // Task 4 GREEN phase: Sort by count DESC, then by Title ASC
        ranked.sort(Comparator
            .comparing((Book b) -> borrowCounts.get(b.getBookId())).reversed()
            .thenComparing(Book::getTitle));
            
        return ranked;
    }
}