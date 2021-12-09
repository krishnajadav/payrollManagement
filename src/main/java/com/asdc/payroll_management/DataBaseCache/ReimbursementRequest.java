package com.asdc.payroll_management.DataBaseCache;

public class ReimbursementRequest {
    private String rrId;
    private String rrEmployeeid;
    private String RRTypeID;
    private String rrNote;
    private String rrAmount;
    private String rrDate;
    private String isAccepted;
    private String errorMsg;

    public ReimbursementRequest(String rrId, String rrEmployeeid, String RRTypeID, String rrNote, String rrAmount, String rrDate, String isAccepted) {
        this.rrId = rrId;
        this.rrEmployeeid = rrEmployeeid;
        this.RRTypeID = RRTypeID;
        this.rrNote = rrNote;
        this.rrAmount = rrAmount;
        this.rrDate = rrDate;
        this.isAccepted = isAccepted;
    }

    public ReimbursementRequest(String RRTypeID, String rrNote, String rrAmount, String rrDate) {
        this.RRTypeID = RRTypeID;
        this.rrNote = rrNote;
        this.rrAmount = rrAmount;
        this.rrDate = rrDate;
	}

	public String getRrId() {
        return rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId;
    }

    public String getRrEmployeeid() {
        return rrEmployeeid;
    }

    public void setRrEmployeeid(String rrEmployeeid) {
        this.rrEmployeeid = rrEmployeeid;
    }

    public String getRRTypeID() {
        return RRTypeID;
    }

    public void setRRTypeID(String RRTypeID) {
        this.RRTypeID = RRTypeID;
    }

    public String getRrNote() {
        return rrNote;
    }

    public void setRrNote(String rrNote) {
        this.rrNote = rrNote;
    }

    public String getRrAmount() {
        return rrAmount;
    }

    public void setRrAmount(String rrAmount) {
        this.rrAmount = rrAmount;
    }

    public String getRrDate() {
        return rrDate;
    }

    public void setRrDate(String rrDate) {
        this.rrDate = rrDate;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String error) {
		this.errorMsg = error;
	}
    
}
