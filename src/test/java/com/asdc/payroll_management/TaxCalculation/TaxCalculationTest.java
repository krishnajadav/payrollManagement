package com.asdc.payroll_management.TaxCalculation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaxCalculationTest {

	@Test
	public void TesTaxCalculationEmail() {
		
		TaxCalculation tc=new TaxCalculation();	
		tc.setUserID("1");
		assertEquals("1",tc.getUserID());
		
	}

}
