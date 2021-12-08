package com.asdc.payroll_management.EmployeeRegistration;

import com.asdc.payroll_management.Utility.EncryptionDecription;

public class Employee {

	private String employee_ID;
	private String employee_Name;
	private String employee_emailID;
	private String employee_Password;
	private String employee_Address;
	private String employee_phoneNumb;

	public Employee() {
		super();
	}

	public Employee(String employee_ID, String employee_Name, String employee_emailID, String employee_Password,
			String employee_Address, String employee_phoneNumb, int jd_ID, String managerID, int department_ID) {
		super();
		this.employee_ID = employee_ID;
		this.employee_Name = employee_Name;
		this.employee_emailID = employee_emailID;
		this.employee_Password = employee_Password;
		this.employee_Address = employee_Address;
		this.employee_phoneNumb = employee_phoneNumb;
	}

	public String getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}

	public String getEmployee_Name() {
		return employee_Name;
	}

	public void setEmployee_Name(String employee_Name) {
		this.employee_Name = employee_Name;
	}

	public String getEmployee_emailID() {
		return employee_emailID;
	}

	public void setEmployee_emailID(String employee_emailID) {
		this.employee_emailID = employee_emailID;
	}

	public String getEmployee_Password() {
		return employee_Password;
	}

	public void setEmployee_Password(String employee_Password) {
		this.employee_Password = employee_Password;
	}

	public String getEmployee_Address() {
		return employee_Address;
	}

	public void setEmployee_Address(String employee_Address) {
		this.employee_Address = employee_Address;
	}

	public String getEmployee_phoneNumb() {
		return employee_phoneNumb;
	}

	public void setEmployee_phoneNumb(String employee_phoneNumb) {
		this.employee_phoneNumb = employee_phoneNumb;
	}

	public String getEncriptedPassword(String employee_Password) {
		EncryptionDecription objEncrypation = new EncryptionDecription();
		return objEncrypation.GetCipherText(employee_Password);
	}

	public boolean validate() {
		if (employee_ID == "" || employee_Name == "" || employee_emailID == "" || employee_Password == ""
				|| employee_Address == "" || employee_phoneNumb == "") {
			return false;
		} else {
			return true;
		}
	}

}
