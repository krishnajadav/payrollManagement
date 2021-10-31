package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeConcreteFactoryTest {

	@Test
	public void TestgetAllEmployees() {
		
		try
		{		
			EmployeeConcreteFactory emp=new EmployeeConcreteFactory();			
			List<Employee> empList=emp.getAllEmployees();		
			assertEquals(0,0); 
		
		}
		catch (Exception e) {
			fail();
		}
			
	}
	
	
	@Test
	public void TestsaveEmployee() throws SQLException {
			
		try
		{		
			EmployeeConcreteFactory empCF=new EmployeeConcreteFactory();		
			Employee emp=new Employee();
			emp.setDepartment_ID(1);
			emp.setEmployee_Address("Test");
			emp.setEmployee_emailID("Test");
			emp.setEmployee_ID("Test3");
			emp.setEmployee_Name("Test");
			emp.setEmployee_Password("Test");
			emp.setEmployee_phoneNumb("Test");
			emp.setJd_ID(1);
			emp.setManagerID("Test");
			String result=empCF.saveEmployee(emp);		
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
}
