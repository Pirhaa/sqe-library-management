
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
    // MANUAL TESTING 
      public static void main(String[] args) {
        
        System.out.println("=== Manual Testing: fineTier ===\n");
        
        // Test 1: Valid values
        System.out.println("--- Valid Classes ---");
        System.out.println("fineTier(0)  = " + fineTier(0));
        System.out.println("fineTier(20) = " + fineTier(20));
        System.out.println("fineTier(45) = " + fineTier(45));
        
        // Test 2: Invalid value
        System.out.println("\n--- Invalid Class ---");
        try {
            System.out.println("fineTier(-3) = " + fineTier(-3));
        } catch (IllegalArgumentException e) {
            System.out.println("fineTier(-3) = Exception: " + e.getMessage());
        }
        
        // Test 3: Boundaries
        System.out.println("\n--- Boundary Values ---");
        System.out.println("fineTier(1)  = " + fineTier(1));
        System.out.println("fineTier(7)  = " + fineTier(7));
        System.out.println("fineTier(8)  = " + fineTier(8));
        System.out.println("fineTier(14) = " + fineTier(14));
        System.out.println("fineTier(15) = " + fineTier(15));
        System.out.println("fineTier(30) = " + fineTier(30));
        System.out.println("fineTier(31) = " + fineTier(31));
        
        System.out.println("\n Manual testing complete!");
    }
}
     
        
    
