
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
}
