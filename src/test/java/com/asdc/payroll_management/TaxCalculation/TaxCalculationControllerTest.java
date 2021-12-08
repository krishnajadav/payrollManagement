package com.asdc.payroll_management.TaxCalculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
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
			
			EmployeeCache employeeCache = Mockito.mock(EmployeeCache.class);
			MockedStatic<EmployeeCache> mocked = mockStatic(EmployeeCache.class);
			mocked.when(EmployeeCache::getInstance).thenReturn(employeeCache);

			HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
			Employee employee1 = new Employee("1236", "Krishna", "team@gmail.com", "ròAÍ°a½„]¿6±‘LàÉ", null, null,"123234",
					null, null, null, null);
			Employee employee2 = new Employee("1232", "jaswanth", "kr", "ròAÍ°a½„]¿6±‘LàÉ", null, null,"123234", null,
					null, null, null);
			Employee employee3 = new Employee("1231", "ali", "kr", "ròAÍ°a½„]¿6±‘LàÉ", null, null, "123", null, null,
					null, null);

			employeeMap.put(employee1.getEmployeeID(), employee1);
			employeeMap.put(employee2.getEmployeeID(), employee2);
			employeeMap.put(employee3.getEmployeeID(), employee3);

			Mockito.when(employeeCache.getAllEmployees()).thenReturn(employeeMap);		
			
			MockHttpServletRequest mockRequest = new MockHttpServletRequest();
			mockRequest.getSession().setAttribute("userInfo", "1236#Krishna#SuperAdmin");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertEquals("141719.1#123234.0",actualResult);
			
			mockRequest.getSession().setAttribute("userInfo", "1266#Ali#User");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertEquals("0.0#0.0",actualResult);
			
			mockRequest.getSession().setAttribute("userInfo", "125656#Jaswanth#Admin");
			actualResult=testObject.generateTaxCalculation(mockRequest);
			assertNotEquals("1.0#1.0",actualResult);
			assertNotNull(actualResult);	
			
		} catch (Exception e) {
			//fail("Error " + e.getStackTrace());
		}
	}
}
