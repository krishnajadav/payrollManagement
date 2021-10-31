package com.asdc.payroll_management.EmployeeRegistration;

import java.sql.*;
import java.util.List;

public interface IEmployeeDAO {
	
	 List<Employee> getAllEmployees() throws SQLException;
	 
	 String saveEmployee(Employee emp);

}
