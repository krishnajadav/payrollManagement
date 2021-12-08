package com.asdc.payroll_management.appraisal.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class AppraisalDataDAO implements IAppraisalDataDAO {

	private String pushAppraisalDataToDB(SelfReviewData reviewData) {
		boolean isDataInserted = AppraisalCache.getInstance()
				.insert(new Appraisal(reviewData.getEmployeeID(), null, reviewData.getRating(),
						reviewData.getComments(), null, null, reviewData.getProjectsParticipated().toString(),
						reviewData.getTechnologiesLearnt().toString(), null, null, null));
		return isDataInserted ? null : "Error occured while inserting data into DB.";
	}

	private boolean isInputValid(SelfReviewData reviewData) {
		reviewData.validate();
		return reviewData.getError() == null ? true : false;
	}

	@Override
	public List<Object> processInput(SelfReviewData reviewData) {
		String error = null;
		if (isInputValid(reviewData)) {
			error = pushAppraisalDataToDB(reviewData);
		}
		List<Object> processedInput = new ArrayList<Object>();
		processedInput.add(reviewData);
		processedInput.add(error);
		return processedInput;
	}

	@Override
	public List<String> getPeerInfo(String employeeID) {
		ArrayList<String> peerInfo = new ArrayList<String>();
		HashMap<String, Employee> employeesMap = EmployeeCache.getInstance().getAllEmployees();
		Employee employee = EmployeeCache.getInstance().getEmployee(employeeID);
		for (String key : employeesMap.keySet()) {
			Employee employeeInMap = employeesMap.get(key);
			if (!employee.getEmployeeID().equals(employeeInMap.getEmployeeID())
					&& employee.getDepartmentID().equals(employeeInMap.getDepartmentID())) {
				peerInfo.add(employeeInMap.getEmployeeID() + ", " + employeeInMap.getEmployeeName());
			}
		}
		return peerInfo;
	}
}
