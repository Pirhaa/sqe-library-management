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

    public static boolean validateIsbn(String isbn) {
        if (isbn == null) return false;
        String s = isbn.replace("-", "").replace(" ", "");
        if (s.length() != 10) return false;
        for (int i = 0; i < 9; i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        char last = Character.toUpperCase(s.charAt(9));
        if (!Character.isDigit(last) && last != 'X') return false;
        int total = 0;
        for (int i = 0; i < 10; i++) {
            char c = Character.toUpperCase(s.charAt(i));
            int value = (c == 'X') ? 10 : Character.getNumericValue(c);
            total += (i + 1) * value;
        }
        return total % 11 == 0;
    }

    public static void main(String[] args) {
        System.out.println("=== Manual Testing: fineTier ===\n");
        System.out.println("--- Valid Classes ---");
        System.out.println("fineTier(0)  = " + fineTier(0));
        System.out.println("fineTier(20) = " + fineTier(20));
        System.out.println("fineTier(45) = " + fineTier(45));

        System.out.println("\n--- Invalid Class ---");
        try {
            System.out.println("fineTier(-3) = " + fineTier(-3));
        } catch (IllegalArgumentException e) {
            System.out.println("fineTier(-3) = Exception: " + e.getMessage());
        }

        System.out.println("\n--- Boundary Values ---");
        System.out.println("fineTier(1)  = " + fineTier(1));
        System.out.println("fineTier(7)  = " + fineTier(7));
        System.out.println("fineTier(8)  = " + fineTier(8));
        System.out.println("fineTier(14) = " + fineTier(14));
        System.out.println("fineTier(15) = " + fineTier(15));
        System.out.println("fineTier(30) = " + fineTier(30));
        System.out.println("fineTier(31) = " + fineTier(31));

        System.out.println("\n=== Manual Testing: validateIsbn ===\n");
        System.out.println("validateIsbn(\"0306406152\")   = " + validateIsbn("0306406152"));
        System.out.println("validateIsbn(\"0-306-40615-2\") = " + validateIsbn("0-306-40615-2"));
        System.out.println("validateIsbn(\"097522980X\")   = " + validateIsbn("097522980X"));
        System.out.println("validateIsbn(\"0306406153\")   = " + validateIsbn("0306406153"));
        System.out.println("validateIsbn(\"12345\")        = " + validateIsbn("12345"));
        System.out.println("validateIsbn(null)            = " + validateIsbn(null));

        System.out.println("\n Manual testing complete!");
    }
}
