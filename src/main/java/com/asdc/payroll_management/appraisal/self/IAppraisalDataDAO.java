package com.asdc.payroll_management.appraisal.self;

import java.util.List;

public interface IAppraisalDataDAO {

	public List<Object> processInput(SelfReviewData appraisalData);

	public List<String> getPeerInfo(String employeeID);
}
