import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class MemberManagement {
    private Set<String> rollNumbers;
    private List<Member> members;
    
    public MemberManagement() {
        this.rollNumbers = new HashSet<>();
        this.members = new ArrayList<>();
    }
    
    public boolean addMember(Member member) {
        String roll = member.getMemberId();
        
        if (rollNumbers.contains(roll)) {
            System.out.println("ERROR: Roll number " + roll + " already exists!");
            return false;
        }
        
        rollNumbers.add(roll);
        members.add(member);
        System.out.println(" Member added successfully!");
        return true;
    }
    
    public boolean isDuplicateRoll(String rollNumber) {
        return rollNumbers.contains(rollNumber);
    }
}
