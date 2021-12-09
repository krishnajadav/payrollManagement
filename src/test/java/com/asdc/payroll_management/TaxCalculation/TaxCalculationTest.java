package com.asdc.payroll_management.TaxCalculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

class TaxCalculationTest {

	@Test
	void testTaxCalculationExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.TaxCalculation.TaxCalculation");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	
	@Test
	public void TestTaxCalculationUserID() {
		try {
			TaxCalculation taxObject = new TaxCalculation();
			taxObject.setUserID("1236");
			assertEquals("1236", taxObject.getUserID());		
		} catch (Exception e) {
			fail();
		}		
	}
	
	@Test
	public void TestSetSalaryWithoutTax() {
		try {
			TaxCalculation taxObject = new TaxCalculation();
			taxObject.setSalaryWithoutTax(2050.0);
			assertEquals("2357.5", taxObject.getSalaryWithoutTax().toString());	
		} catch (Exception e) {
			fail();
		}
	}
}
