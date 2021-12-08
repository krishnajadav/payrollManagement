package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerReimbursementDOA implements IManagerReimbursement {

	private Employee employee = null;

	public ManagerReimbursementDOA(String empID) {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		employee = employeeCache.getEmployee(empID);
	}

	@Override
	public List<Employee> getAllEmployees() {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		HashMap<String, Employee> employeeHashMap = employeeCache.getAllEmployees();
		List<Employee> employees = new ArrayList<Employee>();
		for (Map.Entry<String, Employee> mapElement : employeeHashMap.entrySet()) {
			Employee employeeTemp = (Employee) mapElement.getValue();
			if (employeeTemp.getManagerID().equalsIgnoreCase(employee.getEmployeeID())) {
				employees.add(employeeTemp);
			}
		}
		return employees;
	}

	@Override
	public Boolean acceptReimbursement(String reimbersementID) {
		ReimbursementRequest reimbursementRequest = ReimbursementRequestCache.getInstance().getOne(reimbersementID);
		Boolean updateStatus = ReimbursementRequestCache.getInstance()
				.updateReimbursementRequestTrue(reimbursementRequest);
		return updateStatus;
	}

	@Override
	public Boolean rejectReimbursement(String reimbersementID) {
		ReimbursementRequest reimbursementRequest = ReimbursementRequestCache.getInstance().getOne(reimbersementID);
		Boolean updateStatus = ReimbursementRequestCache.getInstance()
				.updateReimbursementRequestFalse(reimbursementRequest);
		return updateStatus;
	}

	@Override
	public List<ReimbursementRequest> getAllStaffReimbursements() {
		List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
		HashMap<String, ReimbursementRequest> allReimbursements = ReimbursementRequestCache.getInstance()
				.getAllReimbursementRequest();

		for (Map.Entry<String, ReimbursementRequest> mapElement : allReimbursements.entrySet()) {
			ReimbursementRequest reimbursementRequest = (ReimbursementRequest) mapElement.getValue();

			if (reimbursementRequest.getRrEmployeeid().equalsIgnoreCase(employee.getEmployeeID())
					&& reimbursementRequest.getIsAccepted().equals("Pending")) {
				reimbursementRequests.add(reimbursementRequest);
			}
		}
		return reimbursementRequests;
	}

}
