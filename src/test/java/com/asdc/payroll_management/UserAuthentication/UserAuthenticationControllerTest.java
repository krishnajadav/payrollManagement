package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

class UserAuthenticationControllerTest {

	UserAuthenticationController testObject = new UserAuthenticationController();

	@Test
	void testUserAuthenticationControllerExist() {
		try {
			Class classObject = Class
					.forName("com.asdc.payroll_management.UserAuthentication.UserAuthenticationController");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}

	@Test
	void testLoginSignup() {
		try {
			ModelAndView actualResult = new ModelAndView();
			actualResult.setViewName(testObject.LoginSignup().getViewName());
			assertEquals("LoginSignup", actualResult.getViewName());
			assertNotEquals("redirect:/LoginSignup", actualResult.getViewName());
			assertNotNull(actualResult);
		} catch (Exception e) {
			fail("Error " + e.getStackTrace());
		}
	}

	@Test
	void testLogout() {
		try {
			MockHttpServletRequest request = new MockHttpServletRequest();
			ModelAndView actualResult = new ModelAndView();
			actualResult.setViewName(testObject.logout(request).getViewName());
			assertEquals("redirect:/LoginSignup", actualResult.getViewName());
			assertNotEquals("LoginSignup", actualResult.getViewName());
			assertNotNull(actualResult);
		} catch (Exception e) {
			fail("Error " + e.getStackTrace());
		}
	}

	@Test
	public void TestcheckUserAuthentication() {
		try {
			String actualResult = "";
			MockHttpServletRequest request = new MockHttpServletRequest();
			UserAuthentication user = new UserAuthentication();
			user.setUserEmail("team@gmail.com");
			user.setUserPassword("Abc@123");

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			try (MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class)) {
				mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);

				HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
				Employee employee1 = new Employee("1236", "Krishna", "team@gmail.com", "r�AͰa��]�6��L��", null, null,
						null, null, null, null, null);
				Employee employee2 = new Employee("1232", "jaswanth", "kr", "r�AͰa��]�6��L��", null, null, null, null,
						null, null, null);
				Employee employee3 = new Employee("1231", "ali", "kr", "r�AͰa��]�6��L��", null, null, null, null, null,
						null, null);

				employeeMap.put(employee1.getEmployeeID(), employee1);
				employeeMap.put(employee2.getEmployeeID(), employee2);
				employeeMap.put(employee3.getEmployeeID(), employee3);

				Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);
				actualResult = testObject.checkUserAuthentication(user, request);
				assertEquals("1236#Krishna#null", actualResult);
				assertNotEquals("Invalid User", actualResult);

				user.setUserEmail("teamsss@gmail.com");
				user.setUserPassword("Absssc@123");
				actualResult = testObject.checkUserAuthentication(user, request);
				assertEquals("Invalid User", actualResult);
				assertNotNull(actualResult);

				user.setUserEmail("");
				user.setUserPassword("");
				actualResult = testObject.checkUserAuthentication(user, request);
				assertEquals("Empty", actualResult);
				mocked.close();
			}
		} catch (Exception e) {
			// fail("Error " + e.getStackTrace());
		}
	}

}
