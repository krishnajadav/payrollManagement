package com.asdc.payroll_management.ReimbursementManagement;

public class ReimbursementType {
	private int id;
	private String ReimbursementType;
	private int ReimbursementLimit;

	public ReimbursementType(int id, String reimbursementType, int reimbursementLimit) {
		super();
		this.id = id;
		ReimbursementType = reimbursementType;
		ReimbursementLimit = reimbursementLimit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReimbursementType() {
		return ReimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		ReimbursementType = reimbursementType;
	}

	public int getReimbursementLimit() {
		return ReimbursementLimit;
	}

	public void setReimbursementLimit(int reimbursementLimit) {
		ReimbursementLimit = reimbursementLimit;
	}
}
