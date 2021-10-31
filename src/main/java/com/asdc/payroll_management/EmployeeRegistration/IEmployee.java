package com.asdc.payroll_management.EmployeeRegistration;

import java.util.List;

public interface IEmployee {

	List<Employee> getAllEmployees();
	String saveEmployee(Employee emp);
	
}
