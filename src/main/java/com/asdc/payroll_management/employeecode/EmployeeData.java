package com.asdc.payroll_management.employeecode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.asdc.payroll_management.db.DBUtils;

public class EmployeeData {

	String fullName;
	String employeeSalary;
	String employeeDesignation;
	String employeeJoiningDate;
	String employeeManager;
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

	public String getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(String employeeManager) {
		this.employeeManager = employeeManager;
	}

	public String getError() {
		return error;
	}

	public void validate() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fullName.length(); i++) {
			if (!Character.isLetter(fullName.charAt(i))) {
				if(fullName.charAt(i) == ' ') {
					continue;
				}
				sb.append("Full Name should only have Alphabets<br>");
				break;
			}
		}

		for (int i = 0; i < employeeManager.length(); i++) {
			if (!Character.isLetter(employeeManager.charAt(i))) {
				if(employeeManager.charAt(i) == ' ') {
					continue;
				}
				sb.append("Manager name should only have Alphabets<br>");
				break;
			}
		}

		try {
	        Double.parseDouble(employeeSalary);
	      } catch (Exception e) {
	    	  sb.append("salary should only have numbers<br>");
	      }

		if (!DBUtils.getDesignations().contains(employeeDesignation)) {
			sb.append("Designation not found<br>");
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	      dateFormat.setLenient(false);
	      try {
	        dateFormat.parse(employeeJoiningDate);
	      } catch (Exception e) {
	    	  sb.append("joining date should be in the format dd/MM/yyyy<br>");
	      }

		error = sb.toString().isEmpty() ? null : sb.toString();
	}

}
