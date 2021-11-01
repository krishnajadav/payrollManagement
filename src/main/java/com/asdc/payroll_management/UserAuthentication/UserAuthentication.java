package com.asdc.payroll_management.UserAuthentication;

public class UserAuthentication {

	private String userEmail; 
	private String userPassword;
	private String userType;
	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAuthentication(String userEmail, String userPassword, String userType) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
}
