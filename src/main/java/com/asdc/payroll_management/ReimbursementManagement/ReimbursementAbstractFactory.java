package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public abstract class ReimbursementAbstractFactory implements IReimbursement {
	public List<ReimbursementRequest> getAllReimbursements() throws SQLException {
		return null;
	}

	public List<ReimbursementType> getAllReimbursementTypes() throws SQLException {
		return null;
	}

	public boolean addEmployeeReimbursement(ReimbursementRequest newReimbursementRqeust)
			throws ParseException, SQLException {
		return false;
	}
}
