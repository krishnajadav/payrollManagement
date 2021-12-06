package com.asdc.payroll_management.ReimbursementManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.Reimbursement;
import com.asdc.payroll_management.DataBaseCache.ReimbursementCache;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache;

public class EmployeeReimbursementsDAO implements IEmployeeReimbursements {

//	private ReimbursementRequest reimbursementRequest = null;
	private Employee employee = null;

	public EmployeeReimbursementsDAO(String empID) {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		employee = employeeCache.getEmployee(empID);
	}

	@Override
	public List<ReimbursementRequest> getAllReimbursements() {
		List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
		ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();
		HashMap<String, ReimbursementRequest> allReimbursements = reimbursementRequestCache.getAllReimbursements();

		for (Map.Entry mapElement : allReimbursements.entrySet()) {
			ReimbursementRequest reimbursementRequest = (ReimbursementRequest) mapElement.getValue();

			if (reimbursementRequest.getRR_EmployeeID().equalsIgnoreCase(employee.getEmployee_ID())
					&& reimbursementRequest.getIsAccepted() == null) {
				reimbursementRequests.add(reimbursementRequest);
			}
		}
		return reimbursementRequests;
	}

	@Override
	public boolean addEmployeeReimbursement(ReimbursementRequest reimbursementRequest) {
		ReimbursementRequestCache reimbursementRequestCache = ReimbursementRequestCache.getInstance();
		Boolean insertStatus = reimbursementRequestCache.insert(reimbursementRequest);
		return insertStatus;
	}

	@Override
	public Reimbursement getReimbursementType(String reimbursementName) {
		Reimbursement reimbursementType = null;
		ReimbursementCache reimbursementCache = ReimbursementCache.getInstance();
		HashMap<String, Reimbursement> reimbursementsHashMap = reimbursementCache.getAllReimbursements();
		for (Map.Entry mapElement : reimbursementsHashMap.entrySet()) {
			reimbursementType = (Reimbursement) mapElement.getValue();
			if (reimbursementType.getReimbursement_Type().equalsIgnoreCase(reimbursementName)) {
				break;
			}
		}
		return reimbursementType;
	}

	@Override
	public ReimbursementRequest validateReimbursementRequest(ReimbursementRequest reimbursementRequest) {

		String RR_EmployeeID = reimbursementRequest.getRR_EmployeeID();
		int RR_Amount = reimbursementRequest.getRR_Amount();
		Date RR_Date = reimbursementRequest.getRR_Date();
		String RR_Note = reimbursementRequest.getRR_Note();
		String RR_TypeID = reimbursementRequest.getRR_TypeID();
		Date today = new Date();

		StringBuilder ErrorMessage = new StringBuilder();
		Boolean validResponse = true;

		ReimbursementCache reimbursementCache = ReimbursementCache.getInstance();
		int reimbursementLimit = reimbursementCache.getReimbursement_Limit(RR_TypeID);

		if (RR_TypeID == null) {
			validResponse = false;
			ErrorMessage.append("Reimbursement Type is null");
		} else if (RR_Date == null || RR_Date.after(today)) {
			validResponse = false;
			ErrorMessage.append("Future Reimbursement is added");
		} else if (RR_Amount > reimbursementLimit) {
			validResponse = false;
			ErrorMessage.append("Reimbursement limit is higher than allowed limit");
		}

		ReimbursementRequest newReimbursementRequest = null;
		if (validResponse) {
			newReimbursementRequest = new ReimbursementRequest(null, RR_EmployeeID, RR_TypeID, RR_Note, RR_Amount,
					RR_Date, null);
		} else {
			newReimbursementRequest = new ReimbursementRequest(null, RR_EmployeeID, RR_TypeID, RR_Note, RR_Amount,
					RR_Date, null);
			newReimbursementRequest.setError(ErrorMessage.toString());
		}

		return newReimbursementRequest;
	}

}
