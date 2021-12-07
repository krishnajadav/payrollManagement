package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.Employee;

import java.util.List;


public interface IManagerReimbursement {

    public List<Employee> getAllEmployees();

    public Boolean acceptReimbursement(String reimbersementID);

    public Boolean rejectReimbursement(String reimbersementID);

}
