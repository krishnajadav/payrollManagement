package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class UserAuthenticationControllerTest {
	
	@Test
	public void TestcheckUserAuthentication() throws SQLException {
			
		try
		{		
			UserAuthenticationController UAC=new UserAuthenticationController();
			MockHttpServletRequest request = new MockHttpServletRequest();			
			UserAuthentication ua=new UserAuthentication();
			ua.setUserEmail("krishna@gmail.com");
			ua.setUserPassword("qwed");			
			String result=UAC.checkUserAuthentication(ua,request);	
			assertEquals("Success", result);  	
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
}
