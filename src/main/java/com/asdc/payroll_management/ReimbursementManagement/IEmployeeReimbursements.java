package com.asdc.payroll_management.ReimbursementManagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Reimbursement;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

public interface IEmployeeReimbursements {
	public List<ReimbursementRequest> getAllReimbursements();

	//public List<Reimbursement> getAllReimbursementTypes();
	
	public Reimbursement getReimbursementType(String reimbursementName);

	public boolean addEmployeeReimbursement(ReimbursementRequest reimbursementRequest);
	
	public ReimbursementRequest validateReimbursementRequest (ReimbursementRequest reimbursementRequest);
}
