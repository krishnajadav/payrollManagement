package com.asdc.payroll_management.HRRegistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.EmployeeRegistration.Employee;

class HRTest {

	@Test
	public void TestHRName() {
		
		HR hr=new HR();	
		hr.sethR_Name("Krishna");
		assertEquals("Krishna",hr.gethR_Name());
		
	}
	
	@Test
	public void Testvalidate() {
		
		HR hr=new HR("test", "test", "test", 0)	;
		assertEquals(true,hr.validate());
		
	}

}
