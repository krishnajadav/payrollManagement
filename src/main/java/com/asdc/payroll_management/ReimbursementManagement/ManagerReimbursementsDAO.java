package com.asdc.payroll_management.ReimbursementManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache;

public class ManagerReimbursementsDAO implements IManagerReimbursements {
	private Employee employee = null;

	public ManagerReimbursementsDAO(String employeeID) {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		employee = employeeCache.getEmployee(employeeID);
	}

	@Override
	public List<Employee> getAllStaff() {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
		List<Employee> employees = new ArrayList<>();
		for (Map.Entry mapElement : employeeHashMap.entrySet()) {
			Employee employeeTemp = (Employee) mapElement.getValue();
			if (employeeTemp.getManagerID().equalsIgnoreCase(employee.getEmployee_ID())) {
				employees.add(employeeTemp);
			}
		}
		return employees;

	}

	@Override
	public boolean acceptReimbursement(String RR_ID) {
		ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();
		ReimbursementRequest reimbursementRequest = reimbursementRequestCache.getOne(RR_ID);
		Boolean updateStatus = reimbursementRequestCache.updateReimbursementRequestTrue(reimbursementRequest);
		return updateStatus;

	}

	@Override
	public boolean rejectReimbursement(String RR_ID) {
		ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();
		ReimbursementRequest reimbursementRequest = reimbursementRequestCache.getOne(RR_ID);
		Boolean updateStatus = reimbursementRequestCache.updateReimbursementRequestFalse(reimbursementRequest);
		return updateStatus;
	}

}
