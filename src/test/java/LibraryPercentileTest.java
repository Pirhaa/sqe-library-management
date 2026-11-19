import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LibraryPercentileTest {

    @Test
    public void singleMember_returns100Percent() {
        Member m1 = new Member("M1", "Ali", "ali@test.com");
        m1.getBorrowedBooks().add("B1");
        
        // Edge case: Sirf ek member hai
        double result = Library.borrowPercentile(m1, Collections.singletonList(m1));
        assertEquals(100.0, result, 0.01);
    }

    @Test
    public void multipleMembers_returnsCorrectPercentile() {
        Member m1 = new Member("M1", "Ali", "ali@test.com");
        Member m2 = new Member("M2", "Sara", "sara@test.com");
        Member m3 = new Member("M3", "John", "john@test.com");

        // M1 ne 3 books li hain, M2 ne 1, M3 ne 0
        m1.getBorrowedBooks().addAll(Arrays.asList("B1", "B2", "B3"));
        m2.getBorrowedBooks().add("B1");

        // M1 is the top borrower, so its rankIndex should be 0
        // Formula: 0 / (3-1) * 100 = 0%
        double result = Library.borrowPercentile(m1, Arrays.asList(m1, m2, m3));
        assertEquals(0.0, result, 0.01);

        // M3 is the lowest borrower. 2 members have higher counts.
        // Formula: 2 / 2 * 100 = 100%
        double result3 = Library.borrowPercentile(m3, Arrays.asList(m1, m2, m3));
        assertEquals(100.0, result3, 0.01);
    }
}