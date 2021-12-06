package com.asdc.payroll_management.UserAuthentication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

class UserAuthenticationDAOTest {

	UserAuthenticationDAO testObject = new UserAuthenticationDAO();

	@Test
	void testUserAuthenticationDAOExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.UserAuthentication.UserAuthenticationDAO");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	
	@Test
	public void TestcheckUserAuthentication() {

		try {
			String actualResult="";
			UserAuthentication user = new UserAuthentication();
			user.setUserEmail("team@gmail.com");
			user.setUserPassword("Abc@123");

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class);
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("1236", "Krishna", "team@gmail.com", "ròAÍ°a½„]¿6±‘LàÉ", null, null, null,
					null, null, null, null);
			Employee employee2 = new Employee("1232", "jaswanth", "kr", "ròAÍ°a½„]¿6±‘LàÉ", null, null, null, null,
					null, null, null);
			Employee employee3 = new Employee("1231", "ali", "kr", "ròAÍ°a½„]¿6±‘LàÉ", null, null, null, null, null,
					null, null);

			employeeMap.put(employee1.getEmployee_ID(), employee1);
			employeeMap.put(employee2.getEmployee_ID(), employee2);
			employeeMap.put(employee3.getEmployee_ID(), employee3);

			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);
			actualResult = testObject.checkUserAuthentication(user);
			assertEquals("1236#Krishna#null",actualResult);
			assertNotEquals("Invalid User",actualResult);
			
			user.setUserEmail("teamsss@gmail.com");
			user.setUserPassword("Absssc@123");
			actualResult = testObject.checkUserAuthentication(user);
			assertEquals("Invalid User",actualResult);
			assertNotNull(actualResult);
		} catch (Exception e) {
			fail("Error " + e.getStackTrace());
		}
	}

}
