package com.asdc.payroll_management.ReimbursementManagement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.ReimbursementManagementOld.ReimbursementRequest;

class ReimbursementRequestTest {

	private Date date = new Date();
	private ReimbursementRequest rr = new ReimbursementRequest("F01234", 1, "Note", 50, date, 0);

	@Test
	void notNullTest() {
		assertNotNull(rr, "Class is null");
	}

	@Test
	void getIsAcceptedTest() {
		assertEquals(rr.getIsAccepted(), 0);
	}

	@Test
	void setIsAcceptedTest() {
		rr.setIsAccepted(0);
		assertEquals(rr.getIsAccepted(), 0);
	}

	@Test
	void getEmployeeIDTest() {
		assertEquals(rr.getEmployeeID(), "F01234");
	}

	@Test
	void setEmployeeIDTest() {
		rr.setEmployeeID("F01234");
		assertEquals(rr.getEmployeeID(), "F01234");
	}

	@Test
	void getReimbursementTypeIDTest() {
		assertEquals(rr.getReimbursementTypeID(), 1);
	}

	@Test
	void setReimbursementTypeIDTest() {
		rr.setReimbursementTypeID(1);
		assertEquals(rr.getReimbursementTypeID(), 1);
	}

	@Test
	void getReimbursementNoteTest() {
		assertEquals(rr.getReimbursementNote(), "Note");
	}

	@Test
	void setReimbursementNoteTest() {
		rr.setReimbursementNote("Note");
		assertEquals(rr.getReimbursementNote(), "Note");
	}

	@Test
	void getReimbursementAmountTest() {
		assertEquals(rr.getReimbursementAmount(), 50);
	}

	@Test
	void setReimbursementAmountTest() {
		rr.setReimbursementAmount(50);
		assertEquals(rr.getReimbursementAmount(), 50);
	}

	@Test
	void getReimbursementDateTest() {
		assertEquals(rr.getReimbursementDate(), date);
	}

	@Test
	void setReimbursementDateTest() {
		rr.setReimbursementDate(date);
		assertEquals(rr.getReimbursementDate(), date);
	}

}
