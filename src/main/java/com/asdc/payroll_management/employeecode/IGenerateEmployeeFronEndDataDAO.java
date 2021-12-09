package com.asdc.payroll_management.employeecode;

import java.util.List;

public interface IGenerateEmployeeFronEndDataDAO {

	public List<Object> processInput(EmployeeData employeeData);

	public List<String> getDesignationsFromCache();

	public List<String> getDepartmentsFromCache();

	public List<String> getManagerNamesFromCache();
}
