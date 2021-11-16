package com.asdc.payroll_management.TaxCalculation;

public class TaxCalculation {

	private String userID; 
	private String userType;
	private double salary;
	private double salaryWithoutTax;
	
	public TaxCalculation(String userID, String userType, double salary, double salaryWithoutTax) {
		super();
		this.userID = userID;
		this.userType = userType;
		this.salary = salary;
		this.salaryWithoutTax = salaryWithoutTax;
	}

	public TaxCalculation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getSalaryWithoutTax() {
		return salaryWithoutTax;
	}

	public void setSalaryWithoutTax(double salaryWithoutTax) {
		this.salaryWithoutTax =salaryWithoutTax+((salaryWithoutTax*15)/100);
	}	
}
