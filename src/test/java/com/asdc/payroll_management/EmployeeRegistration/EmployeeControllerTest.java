package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeControllerTest {

	@Test
	public void TestgetAllEmployees() throws SQLException {
		
		EmployeeController emp=new EmployeeController();
		
		List<Employee> empList=emp.getALLEmployees();
		
		assertNotNull(empList);
		
	}
	
	@Test
	public void TestsaveEmployee() throws SQLException {
		
		EmployeeController empC=new EmployeeController();
		
		Employee emp=new Employee();
		emp.setDepartment_ID(1);
		emp.setEmployee_Address("Test");
		emp.setEmployee_emailID("Test");
		emp.setEmployee_ID("Test2");
		emp.setEmployee_Name("Test");
		emp.setEmployee_Password("Test");
		emp.setEmployee_phoneNumb("Test");
		emp.setJd_ID(1);
		emp.setManagerID("Test");
		
		String result=empC.saveEmployee(emp);
		
		assertEquals("Success",result);
		
	}
	
}
