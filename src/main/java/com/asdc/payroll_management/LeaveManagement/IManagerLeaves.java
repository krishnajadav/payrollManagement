package com.asdc.payroll_management.LeaveManagement;

import com.asdc.payroll_management.DataBaseCache.Employee;

import java.util.List;


public interface IManagerLeaves {

    public List<Employee> getAllStaff();

    public Boolean acceptLeave(String LR_ID);

    public Boolean rejectLeave(String LR_ID);

}
