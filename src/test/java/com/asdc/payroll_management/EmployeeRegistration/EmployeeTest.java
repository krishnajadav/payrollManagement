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

}
