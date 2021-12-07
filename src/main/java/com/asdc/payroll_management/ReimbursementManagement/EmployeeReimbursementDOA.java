package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.Leaves;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeReimbursementDOA implements IEmployeeReimbursement {
	private Employee employee = null;

	public EmployeeReimbursementDOA(String empID) {
		EmployeeCache employeeCache = EmployeeCache.getInstance();
		employee = employeeCache.getEmployee(empID);
	}

	@Override
	public List<ReimbursementRequest> getAllReimbursements() {
		List<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
		HashMap<String, ReimbursementRequest> allReimbursements = ReimbursementRequestCache.getInstance()
				.getAllReimbursementRequest();

		for (Map.Entry<String, ReimbursementRequest> mapElement : allReimbursements.entrySet()) {
			ReimbursementRequest reimbursementRequest = (ReimbursementRequest) mapElement.getValue();

			if (reimbursementRequest.getRR_EmployeeID().equalsIgnoreCase(employee.getEmployee_ID())
					&& (reimbursementRequest.getIsAccepted() == null || reimbursementRequest.getIsAccepted().equals("null"))) {
				reimbursementRequests.add(reimbursementRequest);
			}
		}
		return reimbursementRequests;
	}
	@Override
	public boolean addReimbursementRequest(ReimbursementRequest reimbursementRequest) {
		Boolean insertStatus = ReimbursementRequestCache.getInstance().insert(reimbursementRequest);
		return insertStatus;
	}

	@Override
	public ReimbursementRequest validateReimbursementRequest(ReimbursementRequest reimbursementRequest) {

		Date reimbursementDate = reimbursementRequest.getRR_Date() == null ? null
				: new Date(Long.parseLong(reimbursementRequest.getRR_Date()));

		StringBuilder ErrorMessage = new StringBuilder();
		Boolean validResponse = true;

		try {
			Double.valueOf(reimbursementRequest.getRR_Amount());
		} catch (Exception e) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement ammount should be valid.\n");
		}

		if (reimbursementDate.after(Calendar.getInstance().getTime())) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement date should be a past date.\n");
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(reimbursementDate);

		ReimbursementRequest newReimbursementRequest = null;
		if (validResponse) {
			newReimbursementRequest = new ReimbursementRequest(reimbursementRequest.getRR_ID(),
					reimbursementRequest.getRR_EmployeeID(), reimbursementRequest.getRR_TypeID(),
					reimbursementRequest.getRR_Note(), reimbursementRequest.getRR_Amount(),
					strDate, reimbursementRequest.getIsAccepted());
		} else {
			newReimbursementRequest = new ReimbursementRequest(reimbursementRequest.getRR_ID(),
					reimbursementRequest.getRR_EmployeeID(), reimbursementRequest.getRR_TypeID(),
					reimbursementRequest.getRR_Note(), reimbursementRequest.getRR_Amount(),
					strDate, reimbursementRequest.getIsAccepted());
			newReimbursementRequest.setError(ErrorMessage.toString());
		}
		return newReimbursementRequest;
	}

}
