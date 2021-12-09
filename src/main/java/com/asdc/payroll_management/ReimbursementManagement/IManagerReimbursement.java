package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;

import java.util.List;

public interface IManagerReimbursement {

	public List<Employee> getAllEmployees();

	public List<ReimbursementRequest> getAllStaffReimbursements();

	public Boolean acceptReimbursement(String reimbersementID);

	public Boolean rejectReimbursement(String reimbersementID);

}
