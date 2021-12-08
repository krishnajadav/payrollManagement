package com.asdc.payroll_management.ReimbursementManagement;

import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.DataBaseCache.Reimbursement;
import com.asdc.payroll_management.DataBaseCache.ReimbursementCache;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequest;
import com.asdc.payroll_management.DataBaseCache.ReimbursementRequestCache;
import com.asdc.payroll_management.DataBaseCache.Employee;

import java.text.DateFormat;
import java.text.ParseException;
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

			if (reimbursementRequest.getRR_EmployeeID().equalsIgnoreCase(employee.getEmployee_ID())) {
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
	public ReimbursementRequest validateReimbursementRequest(ReimbursementRequest reimbursementRequest)
			throws ParseException {

		String RR_Amount = reimbursementRequest.getRR_Amount();
		String RR_Note = reimbursementRequest.getRR_Note();
		String RR_Date = reimbursementRequest.getRR_Date();

		StringBuilder ErrorMessage = new StringBuilder();
		Boolean validResponse = true;

		if (RR_Amount == null) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Amount is required\n");

		}
		if (Integer.valueOf(RR_Amount) < 0) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Amount is less than zero\n");

		}
		if (RR_Note == null) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Note is required\n");

		}
		if (RR_Date == null) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Date is required\n");

		}
		if (this.checkReimbursementAmount(reimbursementRequest)) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Amount is more than threshold\n");

		}
		if (this.checkReimbursementDate(reimbursementRequest)) {
			validResponse = false;
			ErrorMessage.append(" Reimbursement Date is future date\n");

		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(RR_Date);

		ReimbursementRequest newReimbursementRequest = null;
		if (validResponse) {
			newReimbursementRequest = new ReimbursementRequest(reimbursementRequest.getRR_ID(),
					reimbursementRequest.getRR_EmployeeID(), reimbursementRequest.getRR_TypeID(),
					reimbursementRequest.getRR_Note(), reimbursementRequest.getRR_Amount(), strDate,
					reimbursementRequest.getIsAccepted());
		} else {
			newReimbursementRequest = new ReimbursementRequest(reimbursementRequest.getRR_ID(),
					reimbursementRequest.getRR_EmployeeID(), reimbursementRequest.getRR_TypeID(),
					reimbursementRequest.getRR_Note(), reimbursementRequest.getRR_Amount(), strDate,
					reimbursementRequest.getIsAccepted());
			newReimbursementRequest.setError(ErrorMessage.toString());
		}
		return newReimbursementRequest;
	}

	@Override
	public boolean checkReimbursementDate(ReimbursementRequest reimbursementRequest) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date reimbursementDate = dateFormat.parse(reimbursementRequest.getRR_Date());
		if (reimbursementDate.after(Calendar.getInstance().getTime())) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkReimbursementAmount(ReimbursementRequest reimbursementRequest) {
		String RR_TypeID = reimbursementRequest.getRR_TypeID();
		Reimbursement reimbursementType = ReimbursementCache.getInstance().getOne(RR_TypeID);
		float threshold = Float.valueOf(reimbursementType.getReimbursement_Limit());
		float actualAmount = Float.valueOf(reimbursementRequest.getRR_Amount());
		if (threshold < actualAmount) {
			return false;
		} else {
			return true;
		}
	}

}
