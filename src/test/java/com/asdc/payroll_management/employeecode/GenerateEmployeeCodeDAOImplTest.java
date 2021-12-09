package com.asdc.payroll_management.employeecode;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

class GenerateEmployeeCodeDAOImplTest {

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class
					.forName("com.asdc.payroll_management.employeecode.GenerateEmployeeCodeDAOImpl");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void getInstanceTest() {
		try {
			GenerateEmployeeCodeDAOImpl instance = GenerateEmployeeCodeDAOImpl.getInstance();
			assertEquals(instance, GenerateEmployeeCodeDAOImpl.getInstance());
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

	@Test
	void generateEmployeeCodeTest() {

		try {
			EmployeeData employeeData = new EmployeeData();
			employeeData.setDepartmentID("DEPT_ID");
			employeeData.setDepartmentName("DEPT NAME");
			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2020");
			employeeData.setEmployeeSalary("1234567");
			employeeData.setFullName("Jaswanth* Mandava");
			employeeData.setManagerID("EMP_ID");
			employeeData.setManagerName("MANAGER_NAME");

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class);
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);

			GenerateEmployeeCodeDAOImpl.getInstance().generateEmployeeCode(employeeData);
			assertNull(employeeData.getEmployeeID());

			employeeData.setFullName("Jaswanth Mandava");
			GenerateEmployeeCodeDAOImpl.getInstance().generateEmployeeCode(employeeData);
			assertEquals("1", employeeData.getEmployeeID());

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("2", null, null, null, null, null, null, null, null, null, null);
			Employee employee2 = new Employee("4", null, null, null, null, null, null, null, null, null, null);
			Employee employee3 = new Employee("7", null, null, null, null, null, null, null, null, null, null);
			Employee employee4 = new Employee("1", null, null, null, null, null, null, null, null, null, null);
			Employee employee5 = new Employee("3", null, null, null, null, null, null, null, null, null, null);
			Employee employee6 = new Employee("8", null, null, null, null, null, null, null, null, null, null);

			employeeMap.put(employee1.getEmployeeID(), employee1);
			employeeMap.put(employee2.getEmployeeID(), employee2);
			employeeMap.put(employee3.getEmployeeID(), employee3);
			employeeMap.put(employee4.getEmployeeID(), employee4);
			employeeMap.put(employee5.getEmployeeID(), employee5);
			employeeMap.put(employee6.getEmployeeID(), employee6);

			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);
			GenerateEmployeeCodeDAOImpl.getInstance().generateEmployeeCode(employeeData);
			assertEquals("9", employeeData.getEmployeeID());
			mocked.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
