package com.asdc.payroll_management.EmployeeRegistration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.UserAuthentication.UserAuthentication;

class EmployeeTest {

	@Test
	void testEmployeeExist() {
		try {
			Class classObject = Class.forName("com.asdc.payroll_management.EmployeeRegistration.Employee");
			assertNotNull(classObject);
		} catch (ClassNotFoundException e) {
			fail("class not exists");
		}
	}

	@Test
	public void TesUserAuthenticationEmail() {
		try {
			Employee employee = new Employee();
			employee.setEmployee_ID("1232");
			assertEquals("1232", employee.getEmployee_ID());
			assertNotNull(employee.getEmployee_ID());
			assertNotEquals("4342", employee.getEmployee_ID());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testValidate() {
		try {
			Employee employee = new Employee();
			employee.setEmployee_ID("1232");
			employee.setEmployee_Name("test");
			employee.setEmployee_emailID("test@gmail.com");
			employee.setEmployee_Password("test");
			employee.setEmployee_Address("test");
			employee.setEmployee_phoneNumb("test");
			assertEquals(true, employee.validate());
			employee.setEmployee_Address("");
			employee.setEmployee_phoneNumb("");
			assertEquals(false, employee.validate());
		} catch (Exception e) {
			fail();
		}
	}

}
