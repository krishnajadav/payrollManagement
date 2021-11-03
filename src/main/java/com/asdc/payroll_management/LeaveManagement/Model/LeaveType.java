package com.asdc.payroll_management.LeaveManagement.Model;

public class LeaveType {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String leaveName;
    private int LeaveDuration;

    public LeaveType(int id, String name, int duration){
        this.id = id;
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
