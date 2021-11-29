package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IEmployeeReimbursementDAO {
	List<ReimbursementRequest> getAllReimbursements() throws SQLException;

	public List<ReimbursementType> getAllReimbursementTypes() throws SQLException;

	public boolean addEmployeeReimbursement(ReimbursementRequest newReimbursementRqeust)
			throws ParseException, SQLException;
}
