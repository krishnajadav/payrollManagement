package com.asdc.payroll_management.employeecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Department;
import com.asdc.payroll_management.DataBaseCache.DepartmentCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.JobDesignation;
import com.asdc.payroll_management.DataBaseCache.JobDesignationCache;

public class GenerateEmployeeFrontEndDataDAOImpl implements IGenerateEmployeeFronEndDataDAO {

	private void generateEmployeeData(EmployeeData employeeData) {
		GenerateEmployeeCodeDAOImpl.getInstance().generateEmployeeCode(employeeData);
	}

	private boolean pushEmployeeDataToDB(EmployeeData employeeData) {
		boolean isDataInserted = EmployeeCache.getInstance()
				.insertEmplpyeeGenerationDetails(new Employee(employeeData.getEmployeeID(), employeeData.getFullName(),
						null, null, null, null, employeeData.getEmployeeSalary(), employeeData.getManagerID(),
						employeeData.getDepartmentID(), employeeData.getEmployeeDesignation(),
						employeeData.getAccessLevel()));
		return isDataInserted;
	}

	private boolean isGenerationSuccess(EmployeeData employeeData) {
		employeeData.validate();
		return employeeData.getError() == null ? true : false;
	}

	@Override
	public List<Object> processInput(EmployeeData employeeData) {
		String error = null;
		generateEmployeeData(employeeData);
		if (isGenerationSuccess(employeeData)) {
			boolean isDbPushSuccess = pushEmployeeDataToDB(employeeData);
			if (!isDbPushSuccess) {
				error = "Error occured while inserting data into DB.";
			}
		} else {
			error = employeeData.getError();
		}
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(employeeData);
		objectList.add(error);
		return objectList;
	}

	@Override
	public List<String> getDesignationsFromCache() {
		List<JobDesignation> designations = JobDesignationCache.getInstance().getAllDesignations();
		List<String> designationNames = new ArrayList<String>();
		for (JobDesignation designation : designations) {
			designationNames.add(designation.getDesignationName());
		}
		return designationNames;
	}

	@Override
	public List<String> getManagerNamesFromCache() {
		HashMap<String, Employee> employees = EmployeeCache.getInstance().getAllEmployees();
		List<String> managerNamesWithID = new ArrayList<String>();
		for (String key : employees.keySet()) {
			Employee employee = employees.get(key);
			if (!employee.getAccessLevel().equals("user")) {
				managerNamesWithID.add(employee.getEmployeeName() + " (" + employee.getEmployeeID() + ")");
			}
		}
		return managerNamesWithID;
	}

	@Override
	public List<String> getDepartmentsFromCache() {
		HashMap<String, Department> departments = DepartmentCache.getInstance().getDepartments();
		List<String> departmentNames = new ArrayList<String>();
		for (String key : departments.keySet()) {
			Department department = departments.get(key);
			departmentNames.add(department.getDepartmentName() + " (" + department.getDepartmentID() + ")");
		}
		return departmentNames;
	}

}
