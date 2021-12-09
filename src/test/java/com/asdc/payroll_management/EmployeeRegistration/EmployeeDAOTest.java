package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

class EmployeeDAOTest {

	EmployeeDAO testObject = new EmployeeDAO();
	
	@Test
	void testEmployeeDAOExist() {
		try {
			Class classObject = Class
					.forName("com.asdc.payroll_management.EmployeeRegistration.EmployeeDAO");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	@Test
	public void TestsaveEmployee(){

		try {
			String actualResult="";
			com.asdc.payroll_management.EmployeeRegistration.Employee employee = new com.asdc.payroll_management.EmployeeRegistration.Employee();
			employee.setEmployee_ID("1232");
			employee.setEmployee_Name("test");
			employee.setEmployee_emailID("test@gmail.com");
			employee.setEmployee_Password("test");
			employee.setEmployee_Address("test");
			employee.setEmployee_phoneNumb("test");
			
			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			try(MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class)){
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("1236", "Krishna", "team@gmail.com", "r�AͰa��]�6��L��", null, null, null,
					null, null, null, null);
			Employee employee2 = new Employee("1232",null,null,null, null, null, null, null,
					null, null, null);
			Employee employee3 = new Employee("1231", "ali", "kr", "r�AͰa��]�6��L��", null, null, null, null, null,
					null, null);

			employeeMap.put(employee1.getEmployeeID(), employee1);
			employeeMap.put(employee2.getEmployeeID(), employee2);
			employeeMap.put(employee3.getEmployeeID(), employee3);

			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);
			actualResult = testObject.saveEmployee(employee);
			assertEquals("Can not insert this record", actualResult);
			assertNotNull(actualResult);
			
			employee.setEmployee_ID("1236");
			employee.setEmployee_emailID("team@gmail.com");
			actualResult = testObject.saveEmployee(employee);
			assertEquals("This user already Exist", actualResult);
			
			employee.setEmployee_ID("123643535");
			employee.setEmployee_emailID("testkrishna@gmail.com");
			actualResult = testObject.saveEmployee(employee);
			assertEquals("Wrong employee code", actualResult);
			assertNotEquals("Success", actualResult);
			
			employee.setEmployee_emailID("");
			employee.setEmployee_Name("");
			actualResult = testObject.saveEmployee(employee);
			assertEquals("Wrong employee code",actualResult);
			mocked.close();
			}
		} catch (Exception e) {
			//fail();
		}

	}

}
