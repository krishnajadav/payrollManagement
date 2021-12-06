package com.asdc.payroll_management.DataBaseCache;

import java.util.Date;

public class ReimbursementRequest {
	private String RR_ID;
	private String RR_EmployeeID;
	private String RR_TypeID;
	private String RR_Note;
	private int RR_Amount;
	private Date RR_Date;
	private String isAccepted;
	private String error = null;

	public ReimbursementRequest(String RR_ID, String RR_EmployeeID, String RR_TypeID, String RR_Note, int RR_Amount,
			Date RR_Date, String isAccepted) {
		this.RR_ID = RR_ID;
		this.RR_EmployeeID = RR_EmployeeID;
		this.RR_TypeID = RR_TypeID;
		this.RR_Note = RR_Note;
		this.RR_Amount = RR_Amount;
		this.RR_Date = RR_Date;
		this.isAccepted = isAccepted;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRR_ID() {
		return RR_ID;
	}

	public void setRR_ID(String RR_ID) {
		this.RR_ID = RR_ID;
	}

	public String getRR_EmployeeID() {
		return RR_EmployeeID;
	}

	public void setRR_EmployeeID(String RR_EmployeeID) {
		this.RR_EmployeeID = RR_EmployeeID;
	}

	public String getRR_TypeID() {
		return RR_TypeID;
	}

	public void setRR_TypeID(String RR_TypeID) {
		this.RR_TypeID = RR_TypeID;
	}

	public String getRR_Note() {
		return RR_Note;
	}

	public void setRR_Note(String RR_Note) {
		this.RR_Note = RR_Note;
	}

	public int getRR_Amount() {
		return RR_Amount;
	}

	public void setRR_Amount(int RR_Amount) {
		this.RR_Amount = RR_Amount;
	}

	public Date getRR_Date() {
		return RR_Date;
	}

	public void setRR_Date(Date RR_Date) {
		this.RR_Date = RR_Date;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}
}
