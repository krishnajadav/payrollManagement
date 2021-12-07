package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.Leaves;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

import java.util.List;

public interface IEmployeeReimbursement {

    List<ReimbursementRequest> getAllReimbursements();

    public ReimbursementRequest validateReimbursementRequest(ReimbursementRequest reimbursementRequest);

	public boolean addReimbursementRequest(ReimbursementRequest reimbursementRequest);


}
