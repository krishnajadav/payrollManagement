package com.asdc.payroll_management.TaxCalculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.SalaryHist;
import com.asdc.payroll_management.DataBaseCache.SalaryHistCache;

class TaxCalculationControllerTest {

	TaxCalculationController testObject = new TaxCalculationController();

	@Test
	void testTaxCalculationControllerExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.TaxCalculation.TaxCalculationController");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}
	
	@Test
	void testTaxCalculation() {
		try {
			ModelAndView actualResult=new ModelAndView();
			MockHttpServletRequest mockRequest = new MockHttpServletRequest();
			actualResult.setViewName(testObject.TaxCalculation(mockRequest).getViewName());
			assertEquals("redirect:/LoginSignup", actualResult.getViewName());
			
			mockRequest.getSession().setAttribute("userInfo", "1236#Krishna#SuperAdmin");
			actualResult.setViewName(testObject.TaxCalculation(mockRequest).getViewName());
			assertEquals("TaxCalculation", actualResult.getViewName());
			assertNotNull(actualResult);	
		} catch (Exception e) {
			fail("Error " + e.getStackTrace());
		}
	}
	
	@Test
	void testGenerateTaxCalculation() {

		try {
			String actualResult="";
			
			SalaryHistCache salaryHistCache = Mockito.mock(SalaryHistCache.class);
			MockedStatic<SalaryHistCache> mocked = mockStatic(SalaryHistCache.class);
			mocked.when(SalaryHistCache::getInstance).thenReturn(salaryHistCache);

			HashMap<String,SalaryHist> salaryHistoryMap = new HashMap<String, SalaryHist>();
			SalaryHist salaryHist1 = new SalaryHist("1","1000","2021-11-01","2021-11-30","1236");
			SalaryHist salaryHist2 = new SalaryHist("2","1000","2021-11-01","2021-11-30","1236");
			SalaryHist salaryHist3 = new SalaryHist("3","1000","2021-11-01","2021-11-30","1236");

			salaryHistoryMap.put(salaryHist1.getShId(), salaryHist1);
			salaryHistoryMap.put(salaryHist2.getShId(), salaryHist2);
			salaryHistoryMap.put(salaryHist3.getShId(), salaryHist3);
			
			Mockito.when(salaryHistCache.getAllSalaries()).thenReturn(salaryHistoryMap);		
			
			MockHttpServletRequest mockRequest = new MockHttpServletRequest();
			mockRequest.getSession().setAttribute("userInfo", "1236#Krishna#SuperAdmin");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertEquals("3450.0#3000.0",actualResult);
			
			mockRequest.getSession().setAttribute("userInfo", "1266#Ali#User");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertEquals("0.0#0.0",actualResult);
			
			mockRequest.getSession().setAttribute("userInfo", "125656#Jaswanth#Admin");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertNotEquals("1.0#1.0",actualResult);
			assertNotNull(actualResult);	
			
		} catch (Exception e) {
			fail("Error " + e.getStackTrace());
		}
	}
}
