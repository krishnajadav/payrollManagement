package com.asdc.payroll_management.UserAuthentication;

public class UserAuthentication {

	private String userEmail; 
	private String userPassword;
	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAuthentication(String userEmail, String userPassword, String userType) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
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
	
	public boolean validate()
	{
	   if(userEmail=="" || userPassword=="")
	   {
		 return false;   
	   }
	   else
	   {
		return true;   
	   }
	}
	
	
}
