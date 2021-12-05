package com.asdc.payroll_management.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DBUtilsTest {

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
	void getEmployeeCodesTest() {
		try {
			ArrayList<String> expectedEmpCodes = new ArrayList<String>();
			expectedEmpCodes.add("1");
			expectedEmpCodes.add("2");
			expectedEmpCodes.add("3");
			assertEquals(expectedEmpCodes, DBUtils.getEmployeeCodes());
		} catch (Exception e) {
			fail("Exception occured: " + e.getStackTrace());
		}
	}
	
	@Test
	void getDesignationsTest() {
		try {
			ArrayList<String> expectedDesignations = new ArrayList<String>();
			expectedDesignations.add("SE 1");
			expectedDesignations.add("SE 2");
			expectedDesignations.add("SE 3");
			assertEquals(expectedDesignations, DBUtils.getDesignations());
		} catch (Exception e) {
			fail("Exception occured: " + e.getStackTrace());
		}
	}
}
