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

	public List<ReimbursementRequest> getSpecificEmployeeReimbursements(String EmployeeID) throws SQLException {
		return null;
	}

	public boolean updateReimbursementRequest(String ManagerID, ReimbursementRequest rr, int UpdateAccepted)
			throws SQLException {
		return false;
	}
}
