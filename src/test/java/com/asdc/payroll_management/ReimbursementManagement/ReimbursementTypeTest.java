package com.asdc.payroll_management.ReimbursementManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.asdc.payroll_management.ReimbursementManagementOld.ReimbursementType;

class ReimbursementTypeTest {

	private ReimbursementType rt = new ReimbursementType(1, "Food", 50);

	@Test
	void notNullTest() {
		assertNotNull(rt, "Class is null");
	}

	@Test
	void getIdTest() {
		assertEquals(rt.getId(), 1);
	}

	@Test
	void setIdTest() {
		rt.setId(1);
		assertEquals(rt.getId(), 1);
	}

	@Test
	void getReimbursementTypeTest() {
		assertEquals(rt.getReimbursementType(), "Food");
	}

	@Test
	void setReimbursementTypeTest() {
		rt.setReimbursementType("Food");
		assertEquals(rt.getReimbursementType(), "Food");
	}

	@Test
	void getReimbursementLimitTest() {
		assertEquals(rt.getReimbursementLimit(), 50);
	}

	@Test
	void setReimbursementLimitTest() {
		rt.setReimbursementLimit(50);
		assertEquals(rt.getReimbursementLimit(), 50);
	}

}
