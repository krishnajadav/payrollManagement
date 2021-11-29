package com.asdc.payroll_management.ReimbursementManagement;

import java.util.Date;

public class ReimbursementRequest {
	private String EmployeeID;
	private int ReimbursementTypeID;
	private String ReimbursementNote;
	private int ReimbursementAmount;
	private Date ReimbursementDate;
	private String isAccepted;

	public ReimbursementRequest(String employeeID, int reimbursementTypeID, String reimbursementNote,
			int reimbursementAmount, Date reimbursementDate, String isAccepted) {
		super();
		EmployeeID = employeeID;
		ReimbursementTypeID = reimbursementTypeID;
		ReimbursementNote = reimbursementNote;
		ReimbursementAmount = reimbursementAmount;
		ReimbursementDate = reimbursementDate;
		this.isAccepted = isAccepted;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public int getReimbursementTypeID() {
		return ReimbursementTypeID;
	}

	public void setReimbursementTypeID(int reimbursementTypeID) {
		ReimbursementTypeID = reimbursementTypeID;
	}

	public String getReimbursementNote() {
		return ReimbursementNote;
	}

	public void setReimbursementNote(String reimbursementNote) {
		ReimbursementNote = reimbursementNote;
	}

	public int getReimbursementAmount() {
		return ReimbursementAmount;
	}

	public void setReimbursementAmount(int reimbursementAmount) {
		ReimbursementAmount = reimbursementAmount;
	}

	public Date getReimbursementDate() {
		return ReimbursementDate;
	}

	public void setReimbursementDate(Date reimbursementDate) {
		ReimbursementDate = reimbursementDate;
	}
}
