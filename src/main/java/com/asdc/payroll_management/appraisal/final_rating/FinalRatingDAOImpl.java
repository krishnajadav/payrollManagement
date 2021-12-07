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
		try {
			Appraisal appraisal = AppraisalCache.getInstance().getIndividualAppraisals(employeeID);
			Double finalRating = getDoubleValue(appraisal.getFinal_rating());
			FinalRatingData finalRatingData = new FinalRatingData(appraisal.getEployee_comments(),
					Double.parseDouble(appraisal.getEmployee_rating()), appraisal.getManager_comments(),
					Double.parseDouble(appraisal.getManager_rating()),
					EmployeeCache.getInstance().getEmployee(appraisal.getManager_ID()).getEmployee_Name(),
					appraisal.getManager_ID(),
					EmployeeCache.getInstance().getEmployee(appraisal.getEmployee_ID()).getEmployee_Name(),
					appraisal.getEmployee_ID(), finalRating);
			finalRatingData.validate();
		} catch (Exception e) {
			e.printStackTrace();
			error = "Error occured while trying to fetch appraisal data.";
		}
		List<Object> finalRatingData = new ArrayList<Object>();
		finalRatingData.add(finalRatingData);
		finalRatingData.add(error);
		return finalRatingData;
	}

	private Double getDoubleValue(String finalRating) {
		try {
			return Double.valueOf(finalRating);
		} catch (Exception e) {
			return null;
		}
	}

}
