package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IReimbursement {
	public List<ReimbursementRequest> getAllReimbursements(String EmployeeID) throws SQLException;

	public List<ReimbursementType> getAllReimbursementTypes() throws SQLException;

	public boolean addEmployeeReimbursement(ReimbursementRequest newReimbursementRqeust)
			throws ParseException, SQLException;

	public List<ReimbursementRequest> getSpecificEmployeeReimbursements(String EmployeeID) throws SQLException;

	public boolean updateReimbursementRequest(String ManagerID, ReimbursementRequest rr, int UpdateAccepted)
			throws SQLException;
}
