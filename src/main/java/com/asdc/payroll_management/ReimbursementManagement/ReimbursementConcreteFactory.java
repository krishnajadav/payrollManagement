package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ReimbursementConcreteFactory extends ReimbursementAbstractFactory {

	@Override
	public List<ReimbursementRequest> getAllReimbursements(String EmployeeID) throws SQLException {
		EmployeeReimbursementDAO erDAO = new EmployeeReimbursementDAO();
		List<ReimbursementRequest> rrs = erDAO.getAllReimbursements(EmployeeID);
		return rrs;
	}

	@Override
	public List<ReimbursementType> getAllReimbursementTypes() throws SQLException {
		EmployeeReimbursementDAO erDAO = new EmployeeReimbursementDAO();
		List<ReimbursementType> rts = erDAO.getAllReimbursementTypes();
		return rts;
	}

	@Override
	public boolean addEmployeeReimbursement(ReimbursementRequest newReimbursementRqeust)
			throws ParseException, SQLException {
		EmployeeReimbursementDAO erDAO = new EmployeeReimbursementDAO();
		return erDAO.addEmployeeReimbursement(newReimbursementRqeust);
	}

	@Override
	public List<ReimbursementRequest> getSpecificEmployeeReimbursements(String EmployeeID) throws SQLException {
		ManagerReimbursementDAO mrDAO = new ManagerReimbursementDAO();
		List<ReimbursementRequest> rrs = mrDAO.getSpecificEmployeeReimbursements(EmployeeID);
		return rrs;
	}

	@Override
	public boolean updateReimbursementRequest(String ManagerID, ReimbursementRequest rr, int UpdateAccepted)
			throws SQLException {
		ManagerReimbursementDAO mrDAO = new ManagerReimbursementDAO();
		return mrDAO.updateReimbursementRequest(ManagerID, rr, UpdateAccepted);
	}

}
