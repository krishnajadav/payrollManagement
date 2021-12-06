package com.asdc.payroll_management.ReimbursementManagement;

import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Employee;

public interface IManagerReimbursements {
	public List<Employee> getAllStaff();
	
	public boolean acceptReimbursement(String RR_ID);
	
	public boolean rejectReimbursement(String RR_ID);
}
