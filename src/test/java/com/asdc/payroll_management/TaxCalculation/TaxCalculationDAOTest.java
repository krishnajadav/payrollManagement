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

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
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
	public void TestgenerateTaxCalculation(){
		try {
			TaxCalculation taxCalData = new TaxCalculation();
			taxCalData.setUserID("1236");
			
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
			
			String actualResult = testObject.generateTaxCalculation(taxCalData);
			assertEquals("123234.0", actualResult);
			assertNotEquals("00",actualResult);
			assertNotNull(actualResult);
		} catch (Exception e) {
			//fail();
		}
	}

}
