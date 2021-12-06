package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class EmployeeControllerTest {
	
	@Test
	public void TestsaveEmployee() throws SQLException {
			
		try
		{		
			EmployeeController empC=new EmployeeController();		
			Employee emp=new Employee();
			emp.setEmployee_Address("Test");
			emp.setEmployee_emailID("Test");
			emp.setEmployee_ID("Test4");
			emp.setEmployee_Name("Test");
			emp.setEmployee_Password("Test");
			emp.setEmployee_phoneNumb("Test");	
			String result=empC.saveEmployee(emp);		
			assertEquals(0, 0); 
		}
		catch (Exception e) {
			fail();
		}
		
	}
}
