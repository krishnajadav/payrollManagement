package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	public void TestEmployeeName() {
		
		Employee emp=new Employee();
		
		emp.setEmployee_Name("Krishna");
		
		assertEquals("Krishna",emp.getEmployee_Name());
		
	}
	
	@Test
	public void Testvalidate() {
		
		Employee emp=new Employee("test","test","test","test","test","test", 0,"test", 0);		
		assertEquals(true,emp.validate());
		
	}

}
