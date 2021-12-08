package com.asdc.payroll_management.DataBaseCache;

public class LeaveRequest {
    private String lrId;
    private String lrEmployeeid;
    private String lrDuration;
    private String lrType;
    private String isAccepted ;
    private String leaveRequestDate;
    private String leaveEndDate;
    private String error=null;

    public LeaveRequest(String lrId, String lrEmployeeid, String lrDuration, String lrType, String isAccepted, String leaveRequestDate, String leaveEndDate) {
        this.lrId = lrId;
        this.lrEmployeeid = lrEmployeeid;
        this.lrDuration = lrDuration;
        this.lrType = lrType;
        this.isAccepted = isAccepted;
        this.leaveRequestDate = leaveRequestDate;
        this.leaveEndDate = leaveEndDate;
    }

    public String getLrId() {
        return lrId;
    }

    public void setLrId(String lrId) {
        this.lrId = lrId;
    }

    public String getLrEmployeeid() {
        return lrEmployeeid;
    }

    public void setLrEmployeeid(String lrEmployeeid) {
        this.lrEmployeeid = lrEmployeeid;
    }

    public String getLrDuration() {
        return lrDuration;
    }

    public void setLrDuration(String lrDuration) {
        this.lrDuration = lrDuration;
    }

    public String getLrType() {
        return lrType;
    }

    public void setLrType(String lrType) {
        this.lrType = lrType;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getLeaveRequestDate() {
        return leaveRequestDate;
    }

    public void setLeaveRequestDate(String leaveRequestDate) {
        this.leaveRequestDate = leaveRequestDate;
    }

    public String getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
