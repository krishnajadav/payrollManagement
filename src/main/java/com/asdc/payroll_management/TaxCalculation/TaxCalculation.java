package com.asdc.payroll_management.TaxCalculation;

public class TaxCalculation {

	private String userID;
	private Double salary;
	private Double salaryWithoutTax;
	private final int percentage=15;

	public TaxCalculation(String userID, Double salary, Double salaryWithoutTax) {
		super();
		this.userID = userID;
		this.salary = salary;
		this.salaryWithoutTax = salaryWithoutTax;
	}

	public TaxCalculation() {
		super();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getSalaryWithoutTax() {
		return salaryWithoutTax;
	}

	public void setSalaryWithoutTax(Double salaryWithoutTax) {
		this.salaryWithoutTax = salaryWithoutTax + ((salaryWithoutTax * percentage) / 100);
	}
}
