package com.asdc.payroll_management.DataBaseCache;

public class Leaves {
    private String leavesID;
    private String leavesName;
    private String leavesDuartionLimit;

    public Leaves(String leavesID, String leavesName, String leavesDuartionLimit) {
        this.leavesID = leavesID;
        this.leavesName = leavesName;
        this.leavesDuartionLimit = leavesDuartionLimit;
    }

    public String getLeavesID() {
        return leavesID;
    }

    public void setLeavesID(String leavesID) {
        this.leavesID = leavesID;
    }

    public String getLeavesName() {
        return leavesName;
    }

    public void setLeavesName(String leavesName) {
        this.leavesName = leavesName;
    }

    public String getLeavesDuartionLimit() {
        return leavesDuartionLimit;
    }

    public void setLeavesDuartionLimit(String leavesDuartionLimit) {
        this.leavesDuartionLimit = leavesDuartionLimit;
    }
}
