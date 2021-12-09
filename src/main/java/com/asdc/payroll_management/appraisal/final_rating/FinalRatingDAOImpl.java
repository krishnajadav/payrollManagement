package com.asdc.payroll_management.appraisal.final_rating;

import java.util.ArrayList;
import java.util.List;

import com.asdc.payroll_management.DataBaseCache.Appraisal;
import com.asdc.payroll_management.DataBaseCache.AppraisalCache;
import com.asdc.payroll_management.DataBaseCache.EmployeeCache;

public class FinalRatingDAOImpl implements IFinalRatingDAO {

	@Override
	public List<Object> getFinalRatingData(String employeeID) {
		String error = null;
		FinalRatingData finalRatingData = null;
		try {
			Appraisal appraisal = AppraisalCache.getInstance().getIndividualAppraisals(employeeID);
			Double finalRating = getDoubleValue(appraisal.getFinalRating());
			finalRatingData = new FinalRatingData(appraisal.getEployeeComments(),
					Double.parseDouble(appraisal.getEmployeeRating()), appraisal.getManagerComments(),
					Double.parseDouble(appraisal.getManagerRating()),
					EmployeeCache.getInstance().getEmployee(appraisal.getManagerID()).getEmployeeName(),
					appraisal.getManagerID(),
					EmployeeCache.getInstance().getEmployee(appraisal.getEmployeeID()).getEmployeeName(),
					appraisal.getEmployeeID(), finalRating);
			finalRatingData.validate();
		} catch (Exception e) {
			e.printStackTrace();
			error = "Error occured while trying to fetch appraisal data. Final Rating is only given after your self appraisal and manager review are done.";
		}
		List<Object> finalRatingDataList = new ArrayList<Object>();
		finalRatingDataList.add(finalRatingData);
		finalRatingDataList.add(error);
		return finalRatingDataList;
	}

	private Double getDoubleValue(String finalRating) {
		try {
			return Double.valueOf(finalRating);
		} catch (Exception e) {
			return null;
		}
	}

}
