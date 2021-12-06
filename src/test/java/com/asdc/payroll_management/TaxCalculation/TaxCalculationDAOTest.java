package com.asdc.payroll_management.TaxCalculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mockStatic;

import java.sql.SQLException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.asdc.payroll_management.DataBaseCache.SalaryHist;
import com.asdc.payroll_management.DataBaseCache.SalaryHistCache;

class TaxCalculationDAOTest {
	
	TaxCalculationDAO testObject = new TaxCalculationDAO();
	
	@Test
	void testTaxCalculationDAOExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.TaxCalculation.TaxCalculationDAO");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	
	
	@Test
	public void TestgenerateTaxCalculation() throws SQLException {
		try {
			TaxCalculation taxCalData = new TaxCalculation();
			taxCalData.setUserID("1236");
			
			SalaryHistCache salaryHistCache = Mockito.mock(SalaryHistCache.class);
			MockedStatic<SalaryHistCache> mocked = mockStatic(SalaryHistCache.class);
			mocked.when(SalaryHistCache::getInstance).thenReturn(salaryHistCache);

			HashMap<String,SalaryHist> salaryHistoryMap = new HashMap<String, SalaryHist>();
			SalaryHist salaryHist1 = new SalaryHist("1","1000","2021-11-01","2021-11-30","1236");
			SalaryHist salaryHist2 = new SalaryHist("2","1000","2021-11-01","2021-11-30","1236");
			SalaryHist salaryHist3 = new SalaryHist("3","1000","2021-11-01","2021-11-30","1236");

			salaryHistoryMap.put(salaryHist1.getSH_ID(), salaryHist1);
			salaryHistoryMap.put(salaryHist2.getSH_ID(), salaryHist2);
			salaryHistoryMap.put(salaryHist3.getSH_ID(), salaryHist3);
			
			Mockito.when(salaryHistCache.getAllSalaries()).thenReturn(salaryHistoryMap);

				
			String actualResult = testObject.generateTaxCalculation(taxCalData);
			assertEquals("3000.0", actualResult);
			assertNotEquals("00",actualResult);
			assertNotNull(actualResult);
		} catch (Exception e) {
			fail();
		}
	}

}
