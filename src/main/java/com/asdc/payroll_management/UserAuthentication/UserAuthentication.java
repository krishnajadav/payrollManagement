package com.asdc.payroll_management.UserAuthentication;

import com.asdc.payroll_management.Utility.EncryptionDecription;

public class UserAuthentication {

	private String userEmail;
	private String userPassword;

	public UserAuthentication() {
		super();
	}

	public UserAuthentication(String userEmail, String userPassword) {
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

	public String getEncriptedPassword(String userPassword) {
		EncryptionDecription objEncrypation = new EncryptionDecription();
		return objEncrypation.GetCipherText(userPassword);
	}

	public boolean validate() {
		if (userEmail == "" || userPassword == "") {
			return false;
		} else {
			return true;
		}
	}

}
