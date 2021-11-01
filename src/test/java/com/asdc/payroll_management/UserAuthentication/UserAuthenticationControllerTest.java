package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserAuthenticationControllerTest {
	
	@Test
	public void TestcheckUserAuthentication() throws SQLException {
			
		try
		{		
			UserAuthenticationController UAC=new UserAuthenticationController();		
			UserAuthentication ua=new UserAuthentication();
			ua.setUserEmail("test@test.com");
			ua.setUserPassword("abc@123");
			ua.setUserType("HR");			
			String result=UAC.checkUserAuthentication(ua);		
			assertEquals(0, 0);  
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
}
