package com.asdc.payroll_management.employeecode;

import java.util.HashMap;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class GenerateEmployeeCodeDAOImpl implements IGenerateEmployeeCodeDAO {

	private static GenerateEmployeeCodeDAOImpl instance = null;

	private GenerateEmployeeCodeDAOImpl() {
	}

	public static GenerateEmployeeCodeDAOImpl getInstance() {
		if (instance == null) {
			instance = new GenerateEmployeeCodeDAOImpl();
		}
		return instance;
	}

	@Override
	public void generateEmployeeCode(EmployeeData employeeData) {
		employeeData.validate();
		if (employeeData.getError() != null) {
			return;
		}
		int employeeCode = 0;
		HashMap<String, Employee> employees = EmployeeCache.getInstance().getAllEmployees();
		for (String key : employees.keySet()) {
			Employee employee = employees.get(key);
			String employeeNumber = employee.getEmployeeID();
			if (employeeCode < Integer.valueOf(employeeNumber)) {
				employeeCode = Integer.valueOf(employeeNumber);
			}
		}
		employeeCode = employeeCode + 1;
		employeeData.setEmployeeID(String.valueOf(employeeCode));
	}

}
