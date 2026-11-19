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
    // LAB 9 TASK 2: Flawed Method for Code Review
    // ==========================================
    // BUG 1: Missing Javadoc/Docstring
    // BUG 2: Logic bug (dividing by totalMembers instead of totalMembers - 1)
    // BUG 3: No edge case handling for single member
    public static double borrowPercentile(Member member, List<Member> allMembers) {
        int borrowedCount = member.getBorrowedBooks().size();
        int totalMembers = allMembers.size();
        int rankIndex = 0;
        
        for (Member m : allMembers) {
            if (m.getBorrowedBooks().size() > borrowedCount) {
                rankIndex++;
            }
        }
        
        return ((double) rankIndex / totalMembers) * 100;
    }
}