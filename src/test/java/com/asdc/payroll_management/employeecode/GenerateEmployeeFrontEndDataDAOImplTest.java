package com.asdc.payroll_management.employeecode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.asdc.payroll_management.DataBaseCache.Department;
import com.asdc.payroll_management.DataBaseCache.DepartmentCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.JobDesignation;
import com.asdc.payroll_management.DataBaseCache.JobDesignationCache;

class GenerateEmployeeFrontEndDataDAOImplTest {

	private GenerateEmployeeFrontEndDataDAOImpl underTest = new GenerateEmployeeFrontEndDataDAOImpl();

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class
					.forName("com.asdc.payroll_management.employeecode.GenerateEmployeeFrontEndDataDAOImpl");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void getDepartmentsFromCacheTest() {
		try {
			HashMap<String, Department> departments = new HashMap<String, Department>();
			Department department1 = new Department("DEPT_ID1", "DEPT_NAME1", "HR_ID1");
			Department department2 = new Department("DEPT_ID2", "DEPT_NAME2", "HR_ID2");
			Department department3 = new Department("DEPT_ID3", "DEPT_NAME3", "HR_ID3");
			departments.put("DEPT_ID1", department1);
			departments.put("DEPT_ID2", department2);
			departments.put("DEPT_ID3", department3);
			DepartmentCache departmentCache = Mockito.mock(DepartmentCache.class);
			MockedStatic<DepartmentCache> mocked = mockStatic(DepartmentCache.class);
			mocked.when(DepartmentCache::getInstance).thenReturn(departmentCache);
			Mockito.when(departmentCache.getDepartments()).thenReturn(departments);
			List<String> actualOutput = underTest.getDepartmentsFromCache();
			for (String value : actualOutput) {
				if (!(value.equals("DEPT_NAME1 (DEPT_ID1)") || value.equals("DEPT_NAME2 (DEPT_ID2)")
						|| value.equals("DEPT_NAME3 (DEPT_ID3)"))) {
					fail("unexpected value found.");
				}
			}
			mocked.close();
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

	@Test
	void getManagerNamesFromCacheTest() {
		try {
			HashMap<String, Employee> employees = new HashMap<String, Employee>();
			Employee employee1 = new Employee("MANAGER_ID1", "MANAGER_NAME1", null, null, null, null, null, null, null,
					null, "admin");
			Employee employee2 = new Employee("MANAGER_ID2", "MANAGER_NAME2", null, null, null, null, null, null, null,
					null, "admin");
			Employee employee3 = new Employee("MANAGER_ID3", "MANAGER_NAME3", null, null, null, null, null, null, null,
					null, "admin");
			employees.put("MANAGER_ID1", employee1);
			employees.put("MANAGER_ID2", employee2);
			employees.put("MANAGER_ID3", employee3);
			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class);
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employees);
			List<String> actualOutput = underTest.getManagerNamesFromCache();
			for (String value : actualOutput) {
				if (!(value.equals("MANAGER_NAME1 (MANAGER_ID1)") || value.equals("MANAGER_NAME2 (MANAGER_ID2)")
						|| value.equals("MANAGER_NAME3 (MANAGER_ID3)"))) {
					fail("unexpected value found.");
				}
			}
			mocked.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getDesignationsFromCacheTest() {
		try {
			List<JobDesignation> designations = new ArrayList<JobDesignation>();
			JobDesignation designation1 = new JobDesignation("DESIGNATION_1");
			JobDesignation designation2 = new JobDesignation("DESIGNATION_2");
			JobDesignation designation3 = new JobDesignation("DESIGNATION_3");
			designations.add(designation1);
			designations.add(designation2);
			designations.add(designation3);
			JobDesignationCache jobDesignation = Mockito.mock(JobDesignationCache.class);
			MockedStatic<JobDesignationCache> mocked = mockStatic(JobDesignationCache.class);
			mocked.when(JobDesignationCache::getInstance).thenReturn(jobDesignation);
			Mockito.when(jobDesignation.getAllDesignations()).thenReturn(designations);
			List<String> actualOutput = underTest.getDesignationsFromCache();
			for (String value : actualOutput) {
				if (!(value.equals("DESIGNATION_1") || value.equals("DESIGNATION_2")
						|| value.equals("DESIGNATION_3"))) {
					fail("unexpected value found.");
				}
			}
			mocked.close();
		} catch (Exception e) {
			fail("Exception occured");
			e.printStackTrace();
		}
	}

	@Test
	void processInputTest() {
		try {
			EmployeeData employeeData = new EmployeeData();
			employeeData.setDepartmentID("DEPT_ID");
			employeeData.setDepartmentName("DEPT NAME");
			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeID("EMP_ID");
			employeeData.setEmployeeJoiningDate("12/12/2020");
			employeeData.setEmployeeSalary("1234567");
			employeeData.setFullName("Jaswanth Mandava");
			employeeData.setManagerID("EMP_ID");
			employeeData.setManagerName("MANAGER_NAME");

			GenerateEmployeeCodeDAOImpl generateEmployeeCodeDAO = Mockito.mock(GenerateEmployeeCodeDAOImpl.class);
			MockedStatic<GenerateEmployeeCodeDAOImpl> mocked1 = mockStatic(GenerateEmployeeCodeDAOImpl.class);
			mocked1.when(GenerateEmployeeCodeDAOImpl::getInstance).thenReturn(generateEmployeeCodeDAO);

			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked2 = mockStatic(EmployeeCache.class);
			mocked2.when(EmployeeCache::getInstance).thenReturn(employeeCache);
			Mockito.when(employeeCache.insertEmplpyeeGenerationDetails(Mockito.any())).thenReturn(true);

			List<Object> actualOutput = underTest.processInput(employeeData);
			assertNull(actualOutput.get(1));

			employeeData.setFullName("Jaswanth@ Mandava");
			actualOutput = underTest.processInput(employeeData);
			assertEquals("Full Name should only have alphabets and it cannot be empty.<br>", actualOutput.get(1));

			employeeData.setFullName("Jaswanth Mandava");
			Mockito.when(employeeCache.insertEmplpyeeGenerationDetails(Mockito.any())).thenReturn(false);
			actualOutput = underTest.processInput(employeeData);
			assertEquals("Error occured while inserting data into DB.", actualOutput.get(1));
			mocked1.close();
			mocked2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
