package com.asdc.payroll_management.appraisal.manager;

import java.util.List;

public interface IManagerReviewDataDAO {

	public List<Object> processInput(ManagerReviewData appraisalData);

	public List<Object> getSelfReviewInfo(String employeeID);

	public List<String> getEmployeeNamesWithID(String managerID);
}
