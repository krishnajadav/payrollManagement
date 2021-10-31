package com.asdc.payroll_management.HRRegistration;

public class HR {

	private String hR_Name; 
	private String hR_EmailID;
	private String hR_Password;
	private int hR_ID;
	public HR() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HR(String hR_Name, String hR_EmailID, String hR_Password, int hR_ID) {
		super();
		this.hR_Name = hR_Name;
		this.hR_EmailID = hR_EmailID;
		this.hR_Password = hR_Password;
		this.hR_ID = hR_ID;
	}
	public String gethR_Name() {
		return hR_Name;
	}
	public void sethR_Name(String hR_Name) {
		this.hR_Name = hR_Name;
	}
	public String gethR_EmailID() {
		return hR_EmailID;
	}
	public void sethR_EmailID(String hR_EmailID) {
		this.hR_EmailID = hR_EmailID;
	}
	public String gethR_Password() {
		return hR_Password;
	}
	public void sethR_Password(String hR_Password) {
		this.hR_Password = hR_Password;
	}
	public int gethR_ID() {
		return hR_ID;
	}
	public void sethR_ID(int hR_ID) {
		this.hR_ID = hR_ID;
	}
	
	
}
