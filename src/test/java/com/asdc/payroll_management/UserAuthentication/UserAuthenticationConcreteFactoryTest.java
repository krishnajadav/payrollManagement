package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class UserAuthenticationConcreteFactoryTest {


	@Test
	public void TestcheckUserAuthentication() throws SQLException {
					
			UserAuthenticationConcreteFactory UACF=new UserAuthenticationConcreteFactory();		
			UserAuthentication ua=new UserAuthentication();
			ua.setUserEmail("krishna@gmail.com");
			ua.setUserPassword("qwed");	
			String result=UACF.checkUserAuthentication(ua);		
			assertEquals("1232#dfgdfg#Senior Manager", result);  				
	}
}
