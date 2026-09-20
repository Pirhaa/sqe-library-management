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

    public static void main(String[] args) {
        System.out.println("=== Manual Testing: fineTier ===\n");
        System.out.println("fineTier(0)  = " + fineTier(0));
        System.out.println("fineTier(20) = " + fineTier(20));
        System.out.println("fineTier(45) = " + fineTier(45));

        try {
            System.out.println("fineTier(-3) = " + fineTier(-3));
        } catch (IllegalArgumentException e) {
            System.out.println("fineTier(-3) = Exception: " + e.getMessage());
        }

        System.out.println("fineTier(1)  = " + fineTier(1));
        System.out.println("fineTier(7)  = " + fineTier(7));
        System.out.println("fineTier(8)  = " + fineTier(8));
        System.out.println("fineTier(14) = " + fineTier(14));
        System.out.println("fineTier(15) = " + fineTier(15));
        System.out.println("fineTier(30) = " + fineTier(30));
        System.out.println("fineTier(31) = " + fineTier(31));

        System.out.println("\n=== Manual Testing: validateIsbn ===\n");
        System.out.println("validateIsbn(\"9780306406157\")    = " + validateIsbn("9780306406157"));
        System.out.println("validateIsbn(\"978-0-306-40615-7\") = " + validateIsbn("978-0-306-40615-7"));
        System.out.println("validateIsbn(\"97803064061\")      = " + validateIsbn("97803064061"));
        System.out.println("validateIsbn(\"97803064061577\")   = " + validateIsbn("97803064061577"));
        System.out.println("validateIsbn(\"978030640615A\")    = " + validateIsbn("978030640615A"));
        System.out.println("validateIsbn(\"\")                 = " + validateIsbn(""));
        System.out.println("validateIsbn(null)                = " + validateIsbn(null));

        System.out.println("\n Manual testing complete!");
    }
}
