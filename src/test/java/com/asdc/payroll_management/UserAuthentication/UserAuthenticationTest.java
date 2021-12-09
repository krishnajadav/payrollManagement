package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

class UserAuthenticationTest {

	@Test
	void testUserAuthenticationExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.UserAuthentication.UserAuthentication");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	
	@Test
	public void testUserAuthenticationEmail() {
		try {
			UserAuthentication user = new UserAuthentication();
			user.setUserEmail("Krishna@gmail.com");
			assertEquals("Krishna@gmail.com", user.getUserEmail());		
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testValidate() {
		try {
			UserAuthentication user = new UserAuthentication();
			user.setUserEmail("Krishna@gmail.com");
			user.setUserPassword("dfgdfgdfg");
			assertEquals(true,user.validate());
			
			user.setUserEmail("");
			user.setUserPassword("");
			assertEquals(false,user.validate());
		} catch (Exception e) {
			fail();
		}
	}

}
