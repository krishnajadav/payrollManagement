package com.asdc.payroll_management.UserAuthentication;


import static org.junit.Assert.assertEquals;
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
			ua.setUserEmail("krishna@gmail.com");
			ua.setUserPassword("qwed");			
			String result=UADAO.checkUserAuthentication(ua);	
			assertEquals("1232#dfgdfg#Senior Manager", result);  
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
	
	
}
