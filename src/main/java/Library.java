import java.util.List;

public class Library {

    public static String fineTier(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Days overdue cannot be negative");
        }
        if (daysOverdue == 0) {
            return "None";
        } else if (daysOverdue >= 1 && daysOverdue <= 7) {
            return "Low";
        } else if (daysOverdue >= 8 && daysOverdue <= 14) {
            return "Medium";
        } else if (daysOverdue >= 15 && daysOverdue <= 30) {
            return "High";
        } else {
            return "Severe";
        }
    }

    // Lab 5 rule: exactly 13 numeric digits, no letters or symbols.
    public static boolean validateIsbn(String isbn) {
        if (isbn == null) return false;
        String s = isbn.replace("-", "").replace(" ", "");
        if (s.length() != 13) return false;
        for (int i = 0; i < 13; i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // ==========================================
    // LAB 9 TASK 4: Fixed Method after Code Review
    // ==========================================
    /**
     * Calculates the borrow percentile of a member compared to all members.
     * 
     * @param member The member to calculate the percentile for.
     * @param allMembers The list of all members in the library.
     * @return The percentile rank (0 to 100).
     */
    public static double borrowPercentile(Member member, List<Member> allMembers) {
        // Edge case handling for empty or single member list (Division by zero fix)
        if (allMembers == null || allMembers.size() <= 1) {
            return 100.0;
        }

        int borrowedCount = member.getBorrowedBooks().size();
        int totalMembers = allMembers.size();
        int rankIndex = 0;
        
        // Improved readability: renamed 'm' to 'otherMember'
        for (Member otherMember : allMembers) {
            if (otherMember.getBorrowedBooks().size() > borrowedCount) {
                rankIndex++;
            }
        }
        
        // Off-by-one fix: dividing by (totalMembers - 1)
        return ((double) rankIndex / (totalMembers - 1)) * 100;
    }
}