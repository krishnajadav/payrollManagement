package com.asdc.payroll_management.UserAuthentication;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserAuthenticationDAOTest {

	@Test
	public void TestcheckUserAuthentication() throws SQLException {
		
		try
		{		
			UserAuthenticationDAO UADAO=new UserAuthenticationDAO();		
			UserAuthentication ua=new UserAuthentication();
			ua.setUserEmail("test@test.com");
			ua.setUserPassword("abc@123");
			ua.setUserType("HR");	
			
			String result=UADAO.checkUserAuthentication(ua);	
			assertEquals("User Authenticate Successfully",result); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
	
	
}
