package com.asdc.payroll_management.employeecode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EmployeeData {

	String fullName;
	String employeeSalary;
	String employeeDesignation;
	String employeeJoiningDate;
	String managerName;
	String managerID;
	String departmentName;
	String departmentID;
	String accessLevel;
	String employeeID = null;
	String error = null;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeJoiningDate() {
		return employeeJoiningDate;
	}

	public void setEmployeeJoiningDate(String employeeJoiningDate) {
		this.employeeJoiningDate = employeeJoiningDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getError() {
		return error;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();

		if (!isValidName(fullName)) {
			sb.append("Full Name should only have alphabets and it cannot be empty.<br>");
		}
		if (managerName.isBlank()) {
			sb.append("Manager Name cannot be empty.<br>");
		}
		if (!isValidNumber(employeeSalary)) {
			sb.append("salary should be a valid number that is greater than 0.<br>");
		}
		if (departmentName.isBlank()) {
			sb.append("Department cannot be empty.<br>");
		}
		if (employeeDesignation.isBlank()) {
			sb.append("Designation cannot be empty.<br>");
		}
		if (!isValidDateFormat(employeeJoiningDate)) {
			sb.append("joining date should be in the format dd/MM/yyyy.<br>");
		}

		error = sb.toString().isEmpty() ? null : sb.toString();
	}

	private boolean isValidDateFormat(String date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidNumber(String numberInStringFormat) {
		try {
			if (Double.parseDouble(numberInStringFormat) <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidName(String name) {
		if (name.isBlank()) {
			return false;
		} else {
			for (int i = 0; i < name.length(); i++) {
				if (!Character.isLetter(name.charAt(i))) {
					if (name.charAt(i) == ' ') {
						continue;
					}
					return false;
				}
			}
		}
		return true;
	}

}
