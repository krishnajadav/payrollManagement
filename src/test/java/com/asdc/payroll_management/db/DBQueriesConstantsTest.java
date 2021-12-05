package com.asdc.payroll_management.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DBQueriesConstantsTest {

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class.forName("com.asdc.payroll_management.db.DBQueriesConstants");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}
	
	@Test
	void constantsTest() {
		try {
			assertEquals("Select employee_id from employees;", DBQueriesConstants.getEmployeeCodesQuery);
			assertEquals("Select designation_name from designation;", DBQueriesConstants.getDesignationsCodesQuery);
		} catch (Exception e) {
			fail("Exception occured: " + e.getStackTrace());
		}
	}

}
