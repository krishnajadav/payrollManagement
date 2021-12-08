package com.asdc.payroll_management.ReimbursementManagementTest;

import com.asdc.payroll_management.DataBaseCache.*;
import com.asdc.payroll_management.ReimbursementManagement.EmployeeReimbursementDOA;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

public class EmployeeReimbursementDOATest {
	private static EmployeeReimbursementDOA testClass = null;

	@BeforeAll
	public static void init() {
		DatabaseConnection databaseConnection = mock(DatabaseConnection.class);
		try (MockedStatic mocked = mockStatic(DatabaseConnection.class)) {
			mocked.when(DatabaseConnection::getInstance).thenReturn(databaseConnection);
			ResultSet rs = mock(ResultSet.class);
			when(databaseConnection.getData(DBQueriesConstant.All_EMPLOYEE_QUERY)).thenReturn(rs);
			when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
			when(rs.getString("Employee_ID")).thenReturn("1225").thenReturn("26118");
			when(rs.getString("Employee_Name")).thenReturn("jaswanth").thenReturn("Ali");
			when(rs.getString("Employee_emailID")).thenReturn("jaswanth@gmail.com").thenReturn("ali@gmail.com");
			when(rs.getString("Employee_Password")).thenReturn("26119").thenReturn("26118");
			when(rs.getString("Employee_Address")).thenReturn("26119").thenReturn("26118");
			when(rs.getString("Employee_phoneNumb")).thenReturn("7826410377").thenReturn("7826410388");
			when(rs.getString("Employee_Salary")).thenReturn("2611900").thenReturn("2611800");
			when(rs.getString("ManagerID")).thenReturn("26120").thenReturn("26117");
			when(rs.getString("Department_ID")).thenReturn("26").thenReturn("26");
			when(rs.getString("Designation")).thenReturn("26119").thenReturn("26118");
			EmployeeCache employeeFactory = EmployeeCache.getInstance();

			testClass = new EmployeeReimbursementDOA("1225");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Tests failed due to exception");
		}
	}

	@Test
	public void testEmployeeReimbursementConcreteExist() {
		try {
			Class C = Class.forName("com.asdc.payroll_management.ReimbursementManagement.EmployeeReimbursementDOA");
			assertNotNull(C);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertNull(e);
		}
	}

	@Test
	public void checkReimbursementDateTest() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = format.format(new Date());
			ReimbursementRequest reimbursementRequestTrue = new ReimbursementRequest(null, "1225", "1", "Note", "49",
					dateString, "Pending");
			testClass = new EmployeeReimbursementDOA("1225");
			assertTrue(testClass.checkReimbursementDate(reimbursementRequestTrue));

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			String futureDate = format.format(cal.getTime());
			ReimbursementRequest reimbursementRequestFalse = new ReimbursementRequest(null, "1225", "1", "Note", "49",
					futureDate.toString(), "Pending");
			testClass = new EmployeeReimbursementDOA("1225");
			assertFalse(testClass.checkReimbursementDate(reimbursementRequestFalse));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Tests failed due to exception");
		}
	}

	@Test
	public void checkReimbursementAmountTest() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = format.format(new Date());
			ReimbursementRequest reimbursementRequestTrue = new ReimbursementRequest(null, "1225", "1", "Note", "49",
					dateString, "Pending");
			testClass = new EmployeeReimbursementDOA("1225");
			assertTrue(testClass.checkReimbursementAmount(reimbursementRequestTrue));

			ReimbursementRequest reimbursementRequestFalse = new ReimbursementRequest(null, "1225", "1", "Note", "51",
					dateString, "Pending");
			testClass = new EmployeeReimbursementDOA("1225");
			assertFalse(testClass.checkReimbursementAmount(reimbursementRequestFalse));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Tests failed due to exception");
		}
	}

}
