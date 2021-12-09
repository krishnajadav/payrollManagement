package com.asdc.payroll_management.appraisal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.Employee;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;
import com.asdc.payroll_management.appraisal.final_rating.EmployeeAppraisalRatingConcreteFactory;
import com.asdc.payroll_management.appraisal.self.SelfReviewData;

public class ManagerReviewDataDAO implements IManagerReviewDataDAO {

	private boolean pushAppraisalDataToDB(ManagerReviewData managerReviewData) {
		boolean isDataInserted = AppraisalCache.getInstance()
				.updateManagerReview(new Appraisal(managerReviewData.getEmployeeID(),
						EmployeeCache.getInstance().getEmployee(managerReviewData.getEmployeeID()).getManagerID(), null,
						null, managerReviewData.getRating(), managerReviewData.getComments(), null, null, null,
						managerReviewData.getCommunicationSkillsRating(),
						managerReviewData.getProjectsParticipated().toString()));
		return isDataInserted;
	}

	private boolean isInputValid(ManagerReviewData appraisalData) {
		appraisalData.validate();
		return appraisalData.getError() == null ? true : false;
	}

	@Override
	public List<Object> processInput(ManagerReviewData appraisalData) {
		String error = null;
		if (isInputValid(appraisalData)) {
			error = pushAppraisalDataToDB(appraisalData) ? null : "Error occured while inserting data into DB.";
			if (error == null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Double finalRating = new EmployeeAppraisalRatingConcreteFactory()
								.getFinalRating(appraisalData.getEmployeeID());
						AppraisalCache.getInstance().updateFinalRating(finalRating, appraisalData.getEmployeeID());
					}
				}).start();
			}
		}
		List<Object> processedInput = new ArrayList<Object>();
		processedInput.add(appraisalData);
		processedInput.add(error);
		return processedInput;
	}

	@Override
	public List<Object> getSelfReviewInfo(String employeeID) {
		List<Object> reviewInfo = new ArrayList<Object>();
		Appraisal appraisalData = AppraisalCache.getInstance().getIndividualAppraisals(employeeID);
		String error = null;
		SelfReviewData reviewData = new SelfReviewData();
		if (appraisalData == null) {
			error = "Employee should complete his self review before you can add your review.";
		} else {
			reviewData = new SelfReviewData();
			reviewData.setComments(appraisalData.getEployeeComments());
			reviewData.setEmployeeID(appraisalData.getEmployeeID());
			reviewData.setEmployeeName(
					EmployeeCache.getInstance().getEmployee(appraisalData.getEmployeeID()).getEmployeeName());
			reviewData.setProjectsParticipated(getAsList(appraisalData.getEmployeeProjects()));
			reviewData.setRating(appraisalData.getEmployeeRating());
			reviewData.setTechnologiesLearnt(getAsList(appraisalData.getTechnologiesLearnt()));
		}
		reviewInfo.add(reviewData);
		reviewInfo.add(error);
		return reviewInfo;
	}

	private List<String> getAsList(String listAsString) {
		if (listAsString == null || listAsString.isBlank()) {
			return new ArrayList<String>();
		}
		String[] listElements = listAsString.substring(1, listAsString.length() - 1).split(",");
		List<String> convertedList = new ArrayList<String>();
		for (String listElement : listElements) {
			convertedList.add(listElement.trim());
		}
		return convertedList;
	}

	@Override
	public List<String> getEmployeeNamesWithID(String ManagerID) {
		List<String> employeeNamesWithID = new ArrayList<String>();
		HashMap<String, Employee> employees = EmployeeCache.getInstance().getAllEmployees();
		for (String key : employees.keySet()) {
			Employee employee = employees.get(key);
			if (employee.getManagerID().equals(ManagerID)) {
				employeeNamesWithID.add(employee.getEmployeeName() + " (" + employee.getEmployeeID() + ")");
			}
		}
		return employeeNamesWithID;
	}
}
