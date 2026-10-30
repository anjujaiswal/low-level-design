package day6;

//Member (id, name, MembershipType type)
//Why: just who they are. It does not hold the borrowed books. That’s the next class’s job.
//
public class Member {
    private final String memberId;
    private final String name;
    private final MembershipType membershipType;

    public Member(String memberId, String name, MembershipType membershipType) {
        if(memberId == null || name == null || membershipType == null || memberId.isBlank() || name.isBlank()){
            throw new IllegalArgumentException("Member inputs cannot be null or blank");
        }
        this.memberId = memberId;
        this.name = name;
        this.membershipType = membershipType;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }
}
