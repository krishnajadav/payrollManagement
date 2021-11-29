package com.asdc.payroll_management.DataBaseCache;

public class LeaveRequest {
    private String LR_ID ;
    private String LR_EmployeeID ;
    private String LR_Duration ;
    private String LR_Type ;
    private String isAccepted ;
    private String Leave_Request_Date ;
    private String Leave_End_Date ;
    private String error;

    public LeaveRequest(String LR_ID, String LR_EmployeeID, String LR_Duration, String LR_Type, String isAccepted, String leave_Request_Date, String leave_End_Date) {
        this.LR_ID = LR_ID;
        this.LR_EmployeeID = LR_EmployeeID;
        this.LR_Duration = LR_Duration;
        this.LR_Type = LR_Type;
        this.isAccepted = isAccepted;
        this.Leave_Request_Date = leave_Request_Date;
        this.Leave_End_Date = leave_End_Date;
    }

    public String getLR_ID() {
        return LR_ID;
    }

    public void setLR_ID(String LR_ID) {
        this.LR_ID = LR_ID;
    }

    public String getLR_EmployeeID() {
        return LR_EmployeeID;
    }

    public void setLR_EmployeeID(String LR_EmployeeID) {
        this.LR_EmployeeID = LR_EmployeeID;
    }

    public String getLR_Duration() {
        return LR_Duration;
    }

    public void setLR_Duration(String LR_Duration) {
        this.LR_Duration = LR_Duration;
    }

    public String getLR_Type() {
        return LR_Type;
    }

    public void setLR_Type(String LR_Type) {
        this.LR_Type = LR_Type;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getLeave_Request_Date() {
        return Leave_Request_Date;
    }

    public void setLeave_Request_Date(String leave_Request_Date) {
        Leave_Request_Date = leave_Request_Date;
    }

    public String getLeave_End_Date() {
        return Leave_End_Date;
    }

    public void setLeave_End_Date(String leave_End_Date) {
        Leave_End_Date = leave_End_Date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
