package com.asdc.payroll_management.ReimbursementManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.ReimbursementManagementOld.ReimbursementEmployee;

class ReimbursementEmployeeTest {

	private ReimbursementEmployee re = new ReimbursementEmployee("F01234", "ABC", "F04321");

	@Test
	void notNullTest() {
		assertNotNull(re, "Class is null");
	}

	@Test
	void getEmployeeIDTest() {
		assertEquals("F01234", re.getEmployeeID());
	}
	
	@Test
	void setEmployeeIDTest()
	{
		re.setEmployeeID("F01234");
		assertEquals(re.getEmployeeID(),"F01234");
	}
	
	@Test
	void getEmployeeNameTest() {
		assertEquals("ABC", re.getEmployeeName());
	}
	
	@Test
	void setEmployeeNameTest()
	{
		re.setEmployeeName("ABC");
		assertEquals(re.getEmployeeName(),"ABC");
	}
	
	@Test
	void getManagerIDTest() {
		assertEquals("F04321", re.getManagerID());
	}
	
	@Test
	void setManagerIDTest()
	{
		re.setManagerID("F04321");
		assertEquals(re.getManagerID(),"F04321");
	}
	
}
