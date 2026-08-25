public class BookIdGenerator {
    private static int counter = 1000;
    private static final String PREFIX = "B";
    
    public static String generateBookId() {
        counter++;
        return PREFIX + counter;
    }
    
    public static String generateBookId(String category) {
        counter++;
        String catPrefix = category.length() >= 2 ? 
                          category.substring(0, 2).toUpperCase() : 
                          category.toUpperCase();
        return catPrefix + counter;
    }
}
