package com.asdc.payroll_management.DataBaseCache;

import java.sql.ResultSet;
import java.util.HashMap;

public class EmployeeCache {

	private static final HashMap<String, Employee> modelEmployees = new HashMap<String, Employee>();
	private static EmployeeCache employeeFactory = null;

	private EmployeeCache() {
		load();
	}

	public static EmployeeCache getInstance() {
		if (employeeFactory == null) {
			employeeFactory = new EmployeeCache();
		}
		return employeeFactory;
	}

	private void load() {
		try {
			ResultSet rs = DatabaseConnection.getInstance().getData(DBQueriesConstant.All_EMPLOYEE_QUERY);
			while (rs.next()) {
				String employeeId = rs.getString("Employee_ID");
				String employeeName = rs.getString("Employee_Name");
				String employeeEmailid = rs.getString("Employee_emailID");
				String employeePassword = rs.getString("Employee_Password");
				String employeeAddress = rs.getString("Employee_Address");
				String employeePhoneNumb = rs.getString("Employee_phoneNumb");
				String employeeSalary = rs.getString("Employee_Salary");
				String managerID = rs.getString("ManagerID");
				String departmentId = rs.getString("Department_ID");
				String designation = rs.getString("Designation");
				String accessLevel = rs.getString("Access_level");
				modelEmployees.put(employeeId,
						new Employee(employeeId, employeeName, employeeEmailid, employeePassword, employeeAddress,
								employeePhoneNumb, employeeSalary, managerID, departmentId, designation, accessLevel));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Employee> getAllEmployees() {

		return new HashMap<>(modelEmployees);
	}

	public Employee getEmployee(String id) {
		return modelEmployees.get(id);
	}

	public boolean insert(Employee employee) {
		String query = DBQueriesConstant.INSERT_EMPLOYEE_QUERY + " values('" + employee.getEmployeeID() + "','"
				+ employee.getEmployeeName() + "','" + employee.getEmployeeEmail() + "','"
				+ employee.getEmployeePassword() + "','" + employee.getEmployeeAddress() + "','"
				+ employee.getEmployeePhoneNumb() + "','" + employee.getEmployeeSalary() + "','"
				+ employee.getManagerID() + "','" + employee.getDepartmentID() + "','" + employee.getDesignation()
				+ "','" + employee.getAccessLevel() + "')";
		boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {
			modelEmployees.put(employee.getEmployeeID(), employee);
		}
		return insertStatus;
	}

	public boolean insertEmplpyeeGenerationDetails(Employee employee) {
		String query = DBQueriesConstant.INSERT_GENARATE_EMPLOYEE_QUERY + " ('" + employee.getEmployeeID() + "','"
				+ employee.getEmployeeName() + "','" + employee.getEmployeeSalary() + "','" + employee.getManagerID()
				+ "','" + employee.getDepartmentID() + "','" + employee.getDesignation() + "','"
				+ employee.getAccessLevel() + "')";
		boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {
			modelEmployees.put(employee.getEmployeeID(), employee);
		}
		return insertStatus;
	}

	public Boolean update(String query, Employee employee) {
		boolean insertStatus = DatabaseConnection.getInstance().insertData(query);
		if (insertStatus) {
			modelEmployees.replace(employee.getEmployeeID(), employee);
		}
		return insertStatus;
	}

}
