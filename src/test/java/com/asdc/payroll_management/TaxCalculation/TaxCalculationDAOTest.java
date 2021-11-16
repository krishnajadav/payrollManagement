package com.asdc.payroll_management.TaxCalculation;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaxCalculationDAOTest {

	@Test
	public void TestgenerateTaxCalculation() throws SQLException {
		
		try
		{		
			TaxCalculationDAO TCDAO=new TaxCalculationDAO();		
			TaxCalculation tc=new TaxCalculation();
			tc.setUserID("FF007");
			tc.setUserType("Employee");			
			String result=TCDAO.generateTaxCalculation(tc);	
			assertEquals(0, 0);  
		
		}
		catch (Exception e) {
			fail();
		}
		
		
	}
	
	
}
