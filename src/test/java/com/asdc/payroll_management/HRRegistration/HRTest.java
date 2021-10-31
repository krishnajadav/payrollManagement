package com.asdc.payroll_management.HRRegistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HRTest {

	@Test
	public void TestHRName() {
		
		HR hr=new HR();	
		hr.sethR_Name("Krishna");
		assertEquals("Krishna",hr.gethR_Name());
		
	}

}
