package com.asdc.payroll_management.UserAuthentication;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserAuthenticationConcreteFactoryTest {


	@Test
	public void TestcheckUserAuthentication() throws SQLException {
			
		try
		{		
			UserAuthenticationConcreteFactory UACF=new UserAuthenticationConcreteFactory();		
			UserAuthentication ua=new UserAuthentication();
			ua.setUserEmail("test@test.com");
			ua.setUserPassword("abc@123");
			ua.setUserType("HR");
			String result=UACF.checkUserAuthentication(ua);		
			assertEquals("User Authenticate Successfully",result); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
}
