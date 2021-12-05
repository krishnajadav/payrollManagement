package com.asdc.payroll_management.employeecode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeCodeGeneratorControllerTest {

	@Test
	void classExistanceTest() {
		try {
			Class<?> classFinder = Class
					.forName("com.asdc.payroll_management.employeecode.EmployeeCodeGeneratorController");
			assertNotNull(classFinder);
		} catch (ClassNotFoundException e) {
			fail("class not found");
		}
	}

	@Test
	void employeeCodeGeneratorTest() {
		try {
			EmployeeCodeGeneratorController controller = new EmployeeCodeGeneratorController();
			EmployeeData employeeData = new EmployeeData();
			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswanth Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertNull(employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswanth Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("ABC");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswanth Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("Designation not found<br>", employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("ABC", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswanth Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/122/2002");
			employeeData.setEmployeeManager("Chnadan Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswanth Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("joining date should be in the format dd/MM/yyyy<br>", employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/122/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswanth Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan2 Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswanth Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("Manager name should only have Alphabets<br>", employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan2 Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswanth Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan Jha");
			employeeData.setEmployeeSalary("12a344");
			employeeData.setFullName("Jaswanth Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("salary should only have numbers<br>", employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan Jha", employeeData.getEmployeeManager());
			assertEquals("12a344", employeeData.getEmployeeSalary());
			assertEquals("Jaswanth Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswant1 Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("Full Name should only have Alphabets<br>", employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswant1 Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/12/2002");
			employeeData.setEmployeeManager("Chnadan1 Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswant1 Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals("Full Name should only have Alphabets<br>Manager name should only have Alphabets<br>",
					employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/12/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan1 Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswant1 Mandava", employeeData.getFullName());

			employeeData.setEmployeeDesignation("SE 1");
			employeeData.setEmployeeJoiningDate("12/121/2002");
			employeeData.setEmployeeManager("Chnadan1 Jha");
			employeeData.setEmployeeSalary("12344");
			employeeData.setFullName("Jaswant1 Mandava");
			controller.employeeCodeGenerator(employeeData);

			assertEquals(
					"Full Name should only have Alphabets<br>Manager name should only have Alphabets<br>joining date should be in the format dd/MM/yyyy<br>",
					employeeData.getError());
			assertNotNull(employeeData.getEmployeeID());
			assertEquals("SE 1", employeeData.getEmployeeDesignation());
			assertEquals("12/121/2002", employeeData.getEmployeeJoiningDate());
			assertEquals("Chnadan1 Jha", employeeData.getEmployeeManager());
			assertEquals("12344", employeeData.getEmployeeSalary());
			assertEquals("Jaswant1 Mandava", employeeData.getFullName());

		} catch (Exception e) {
			fail("Exception occured: " + e.getStackTrace());
		}
	}

}
