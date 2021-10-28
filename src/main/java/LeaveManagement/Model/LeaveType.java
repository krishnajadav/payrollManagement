package LeaveManagement.Model;

public class LeaveType {

    private String leaveName;
    private int LeaveDuration;

    public LeaveType(String name, int duration){
        this.leaveName=name;
        this.LeaveDuration=duration;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public int getLeaveDuration() {
        return LeaveDuration;
    }

    public void setLeaveDuration(int leaveDuration) {
        LeaveDuration = leaveDuration;
    }

}
