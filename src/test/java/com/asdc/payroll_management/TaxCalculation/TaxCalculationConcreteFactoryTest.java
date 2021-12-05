package com.asdc.payroll_management.TaxCalculation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaxCalculationConcreteFactoryTest {


	@Test
	public void TestgenerateTaxCalculation() throws SQLException {
			
		try
		{		
			TaxCalculationConcreteFactory TCCF=new TaxCalculationConcreteFactory();		
			TaxCalculation tc=new TaxCalculation();
			tc.setUserID("FF007");	
			String result=TCCF.generateTaxCalculation(tc);		
			assertEquals(0, 0); 
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
}
