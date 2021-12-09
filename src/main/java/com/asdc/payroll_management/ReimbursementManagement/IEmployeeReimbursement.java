package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

import java.text.ParseException;
import java.util.List;

public interface IEmployeeReimbursement {

	List<ReimbursementRequest> getAllReimbursements();

	public ReimbursementRequest validateReimbursementRequest(ReimbursementRequest reimbursementRequest)
			throws ParseException;

	public boolean addReimbursementRequest(ReimbursementRequest reimbursementRequest);

	public boolean checkReimbursementDate(ReimbursementRequest reimbursementRequest) throws ParseException;

	public boolean checkReimbursementAmount(ReimbursementRequest reimbursementRequest);
}
