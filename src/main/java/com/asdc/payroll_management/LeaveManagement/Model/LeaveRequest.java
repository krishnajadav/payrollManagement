package com.asdc.payroll_management.LeaveManagement.Model;

import java.util.*;

public class LeaveRequest {
    private String EmployeeID;
    private int LeaveDuration;
    private int leaveTypeID;
    private Date LeaveStartdate;
    private Date leaveEndDate;
    private boolean isAccepted;

    public LeaveRequest(String ID, int Duration, int type, Date startDate, Boolean isAccepted, Date Leave_End_Date){
        this.EmployeeID=ID;
        this.LeaveDuration=Duration;
        this.leaveTypeID=type;
        this.LeaveStartdate=startDate;
        this.isAccepted=isAccepted;
        this.leaveEndDate =Leave_End_Date;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public void setLeaveDuration(int leaveDuration) {
        LeaveDuration = leaveDuration;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public void setLeaveStartdate(Date leaveStartdate) {
        LeaveStartdate = leaveStartdate;
    }

    public void setLeaveEndDate(Date Leave_End_Date) {
        Leave_End_Date = Leave_End_Date;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public int getLeaveDuration() {
        return LeaveDuration;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public Date getLeaveStartdate() {
        return LeaveStartdate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }


}