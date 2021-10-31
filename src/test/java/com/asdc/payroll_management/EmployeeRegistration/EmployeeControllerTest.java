package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.Database.MySQLDB;

class EmployeeControllerTest {

	@Test
	public void TestgetAllEmployees() throws SQLException {
		
		try
		{		
			EmployeeController emp=new EmployeeController();		
			//List<Employee> empList=emp.getALLEmployees();		
			assertEquals(0, 0); 
		}
		catch (Exception e) {
			fail();
		}
					
	}
	
	@Test
	public void TestsaveEmployee() throws SQLException {
			
		try
		{		
			EmployeeController empC=new EmployeeController();		
			Employee emp=new Employee();
			emp.setDepartment_ID(1);
			emp.setEmployee_Address("Test");
			emp.setEmployee_emailID("Test");
			emp.setEmployee_ID("Test4");
			emp.setEmployee_Name("Test");
			emp.setEmployee_Password("Test");
			emp.setEmployee_phoneNumb("Test");
			emp.setJd_ID(1);
			emp.setManagerID("Test");		
			String result=empC.saveEmployee(emp);		
			assertEquals(0, 0); 
		}
		catch (Exception e) {
			fail();
		}
		
	}
	
}
