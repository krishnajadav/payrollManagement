package com.asdc.payroll_management.ReimbursementManagement;

public class ReimbursementEmployee {
	private String EmployeeID;
	private String EmployeeName;
	private String ManagerID;

	public ReimbursementEmployee(String EmployeeID, String EmployeeName, String ManagerID) {
		this.EmployeeID = EmployeeID;
		this.EmployeeName = EmployeeName;
		this.ManagerID = ManagerID;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getManagerID() {
		return ManagerID;
	}

	public void setManagerID(String managerID) {
		ManagerID = managerID;
	}
}
