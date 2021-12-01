package com.asdc.payroll_management.TaxCalculation;

public class TaxCalculation {

	private String userID; 
	private double salary;
	private double salaryWithoutTax;
	
	public TaxCalculation(String userID, double salary, double salaryWithoutTax) {
		super();
		this.userID = userID;
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
