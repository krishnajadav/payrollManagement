package com.asdc.payroll_management.UserAuthentication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserAuthenticationTest {

	@Test
	public void TesUserAuthenticationEmail() {		
		
		UserAuthentication ua=new UserAuthentication();	
		ua.setUserEmail("Krishna@gmail.com");
		assertEquals("Krishna@gmail.com",ua.getUserEmail());		
	}

}
